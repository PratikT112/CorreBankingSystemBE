package com.pratikt112.correbankingsystembe.service;

import com.pratikt112.correbankingsystembe.config.SystemDateProvider;
import com.pratikt112.correbankingsystembe.exception.DuplicateRecordException;
import com.pratikt112.correbankingsystembe.exception.IncompleteDataException;
import com.pratikt112.correbankingsystembe.exception.ValidationException;
import com.pratikt112.correbankingsystembe.model.cuid.Cuid;
import com.pratikt112.correbankingsystembe.repo.CuidRepo;
import com.pratikt112.correbankingsystembe.utility.NullBlankUtility;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class CuidService {

    @Autowired
    private CuidRepo cuidRepo;


    public List<Cuid> getCuidByCustNo(String socNo, String custNo) {
        List<Cuid> idList = cuidRepo.getBySocNoAndCustNo(socNo, custNo);
        return idList;
    }

    public Cuid saveCuid(Cuid newCuid) {
        validateCuid(newCuid);
        return persistCuid(newCuid);
    }

    @Transactional
    public List<Cuid> saveCuid(List<Cuid> cuidList){
        List<Cuid> savedCuid = new ArrayList<Cuid>();
        transformCuidListToSave(cuidList);
        for(Cuid entry: cuidList) validateCuid(entry);
        savedCuid = cuidRepo.saveAll(cuidList);
        log.info("All entries persisted for CIF: {}", cuidList.getFirst().getId().getCustNo());
        return savedCuid;
    }

    private void transformCuidListToSave(List<Cuid> cuidList) {
        Cuid idMain = cuidList.stream()
                .filter(x->"Y".equals(x.getIdMain()))
                .findFirst()
                .orElse(null);
        if(idMain != null){
            cuidList.remove(idMain);
            cuidList.addFirst(idMain);
        } else {
            throw new IncompleteDataException("REQUIRED_ID_NOT_PROVIDED",
                    "Main ID not provided for customer",
                    "At least one of the IDs provided has to have ID_MAIN as Y");
        }

        List<Cuid> remaining = cuidList.subList(1, cuidList.size());
        remaining.sort(Comparator.comparingInt(c->Integer.parseInt(c.getId().getIdType())));

    }

    public void validateCuid( Cuid newCuid){
        NullBlankUtility.validateNotNull(newCuid.getId(),
                () -> new IncompleteDataException("CUID", "CUID_KEY"));
        NullBlankUtility.validateNotBlank(newCuid.getIdNumber(),
                () -> new IncompleteDataException("CUID", "ID_NUMBER"));
        NullBlankUtility.validateNotNull(newCuid.getIdIssueDate(),
                () -> new IncompleteDataException("CUID", "ID_ISSUE_DATE"));
        NullBlankUtility.validateNotNull(newCuid.getIdExpiryDate(),
                () -> new IncompleteDataException("CUID", "ID_EXPIRY_DATE"));
        NullBlankUtility.validateNotBlank(newCuid.getIdIssueAt(),
                () -> new IncompleteDataException("CUID", "ID_ISSUED_AT"));
        NullBlankUtility.validateNotBlank(newCuid.getIdMain(),
                () -> new IncompleteDataException("CUID", "ID_MAIN"));


        if(cuidRepo.existsById(newCuid.getId())){
            throw new DuplicateRecordException("CUID", newCuid.getId().getCustNo());
        }

        if(Objects.equals(newCuid.getIdMain(), "Y")){
            if(cuidRepo.mainIdExists(newCuid.getId().getInstNo(), newCuid.getId().getCustNo())){
                throw new ValidationException("VALIDATION_ERROR",
                        "Main ID already exists for customer",
                        "Invalid ID Issue Date provided");
            }
        }

        if(newCuid.getIdIssueDate().isAfter(SystemDateProvider.getSystemDate())){
            throw new ValidationException("VALIDATION_ERROR",
                    "Id Issue Date should not be future date",
                    "Invalid ID Issue Date provided");
        }

        if(newCuid.getIdExpiryDate().isBefore(SystemDateProvider.getSystemDate())){
            throw new ValidationException("VALIDATION_ERROR",
                    "Id Expiry Date must be future date",
                    "Invalid ID Expiry Date provided");
        }

        List<String> dupCIFList = cuidRepo.IdAlreadyExistsCIF(newCuid.getId().getIdType(), newCuid.getIdNumber());
        String dupCIFNo = dupCIFList.isEmpty() ? null : dupCIFList.getFirst();
        if(dupCIFNo!=null){
//            throw new IllegalArgumentException("Id already exists for CIF: "+ dupCIFNo);
            throw new DuplicateRecordException("DUPLICATE_RECORD_ERROR",
                    "Id already exists for CIF: "+ dupCIFNo,
                    "Id already exists for other CIF. Recheck and try again.");
        }

    }

    public Cuid persistCuid(Cuid newCuid){
        return cuidRepo.save(newCuid);
    }
}
