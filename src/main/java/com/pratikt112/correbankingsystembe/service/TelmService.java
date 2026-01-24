package com.pratikt112.correbankingsystembe.service;


import com.pratikt112.correbankingsystembe.DTOs.TellerCreateRequest;
import com.pratikt112.correbankingsystembe.exception.DuplicateRecordException;
import com.pratikt112.correbankingsystembe.exception.IncompleteDataException;
import com.pratikt112.correbankingsystembe.exception.RecordNotFoundException;
import com.pratikt112.correbankingsystembe.model.telm.Telm;
import com.pratikt112.correbankingsystembe.model.telm.TelmId;
import com.pratikt112.correbankingsystembe.repo.TelmRepo;
import com.pratikt112.correbankingsystembe.repo.UstyRepo;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;

@Service
public class TelmService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TelmService.class);

    private final TelmRepo telmRepo;
    private final UstyRepo ustyRepo;
    private final PasswordEncoder passwordEncoder;


    public TelmService(TelmRepo telmRepo, UstyRepo ustyRepo, PasswordEncoder passwordEncoder){
        this.telmRepo = telmRepo;
        this.ustyRepo = ustyRepo;
        this.passwordEncoder = passwordEncoder;
    }


    @Transactional
    public Telm saveTelm(TellerCreateRequest reqDto) {
        try{
            Telm entry = processTelmDto(reqDto);
            Telm saved = telmRepo.save(entry);
            LOGGER.info("Teller created successfully. Teller ID: {}", saved.getId().getTellerNo());
            return saved;
        } catch (Exception e){
            LOGGER.error("Teller creation failed for request: {}. Error: {}", reqDto.getTellerNo(), e.getMessage());
            throw e;
        }

    }

    private Telm processTelmDto(TellerCreateRequest reqDto) {
        validateRequest(reqDto);
        return constructTelmFromRequest(reqDto);
    }

    private Telm constructTelmFromRequest(TellerCreateRequest reqDto) {
        Telm constructed = new Telm();
        constructed.setId(new TelmId("003", reqDto.getTellerNo()));
        constructed.setBrchNo(reqDto.getBrchNo());
        constructed.setPostDate(LocalDate.now());
        constructed.setSignonFlag("N");
        constructed.setCapable(reqDto.getCapable());
        constructed.setTellerName(reqDto.getTellerName());
        constructed.setTellerPword(passwordEncoder.encode(reqDto.getTellerPassword()));
        constructed.setTellerPwordRetry("No");
        constructed.setTellerAuthority("V");
        constructed.setUserType(reqDto.getUserType());
        constructed.setTellerDob(reqDto.getTellerDOB());
        return constructed;
    }

    private void validateRequest(TellerCreateRequest reqDto) {
        LOGGER.info("Validating teller request with tellerNo: {}", reqDto.getTellerNo());
        if(telmRepo.existsById(new TelmId("003",reqDto.getTellerNo()))){
            throw new DuplicateRecordException("TELM", reqDto.getTellerNo());
        }
        LOGGER.info("No duplicate record found for tellerNo: {}, proceeding...", reqDto.getTellerNo());
        if(ustyRepo.existsById(reqDto.getUserType())){
            throw new RecordNotFoundException("USTY", reqDto.getUserType());
        }
        LOGGER.info("Validation completed for tellerNo: {}, proceeding to persist...", reqDto.getTellerNo());
    }

    public void validateTelmEntry(@Valid Telm telm){
        if(Period.between(telm.getTellerDob(), LocalDate.now()).getYears() < 18){
            throw new ValidationException("Teller should be atleast 18 years of age");
        }
    }
}
