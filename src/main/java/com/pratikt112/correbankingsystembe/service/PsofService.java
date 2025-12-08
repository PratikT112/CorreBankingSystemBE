package com.pratikt112.correbankingsystembe.service;

import com.pratikt112.correbankingsystembe.exception.DuplicateRecordException;
import com.pratikt112.correbankingsystembe.exception.IncompleteDataException;
import com.pratikt112.correbankingsystembe.exception.RecordNotFoundException;
import com.pratikt112.correbankingsystembe.exception.ValidationException;
import com.pratikt112.correbankingsystembe.model.psof.Psof;
import com.pratikt112.correbankingsystembe.repo.PsofRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class PsofService {
    @Autowired
    private PsofRepo psofRepo;

    @Transactional
    public Psof amendPsof(Psof psofNew) {
        if(psofNew.getPsofId().getSocNo() == null || psofNew.getPsofId().getCustAcctNo() == null || psofNew.getSourceFunds() == null || psofNew.getBranchNo() == null || psofNew.getMakerId() == null || psofNew.getCheckerId() == null){
//            throw new IllegalArgumentException("Required Parameters not provided for Source of Funds Amendment.");
            throw new IncompleteDataException("PSOF", "Required Parameters not provided for Source of Funds Amendment.");    // To be fixed for individual field name. COnsider making incomplete request exception class
        }
        if(!psofRepo.existsById(psofNew.getPsofId())){
//            throw new IllegalArgumentException("Record Not Found");
            throw new RecordNotFoundException("PSOF", psofNew.getPsofId().getCustAcctNo());
        }
        try{
            validateAmendPsof(psofNew);
            return persistPsof(psofNew);
        } catch (IllegalArgumentException e){
            throw e;
        } catch (DataIntegrityViolationException e){
            throw new RuntimeException("Database constraint violated while saving PSOF: " + e.getMostSpecificCause().getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error while saving PSOF: ", e);
        }
    }



    @Transactional
    public Psof savePsof(Psof psof) {
        try{
            validatePsof(psof);
            return persistPsof(psof);
        } catch (IllegalArgumentException e){
            throw e;
        } catch (DataIntegrityViolationException e){
            throw new RuntimeException("Database constraint violated while saving PSOF: " + e.getMostSpecificCause().getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error while saving PSOF: ", e);
        }
    }

    private void validatePsof(Psof psof) {
        if(psofRepo.existsById(psof.getPsofId())){
//            throw new IllegalArgumentException("Record already exists");
            throw new DuplicateRecordException("PSOF", psof.getPsofId().getCustAcctNo());
        }

        if(Objects.equals(psof.getCustomerType(), "01")){
            if(!List.of("01","02","03","04","05","06").contains(psof.getSourceFunds())){
//                throw new IllegalArgumentException("Invalid Source of Funds for Customer Type");
                throw new ValidationException("PSOF_SOURCE_OF_FUNDS","Invalid Source of Funds for Customer Type");
            }
        } else if(Objects.equals(psof.getCustomerType(), "02")){
            if(!List.of("01","02","03","04","05").contains(psof.getSourceFunds())){
//                throw new IllegalArgumentException("Invalid Source of Funds for Customer Type");
                throw new ValidationException("PSOF_SOURCE_OF_FUNDS","Invalid Source of Funds for Customer Type");
            }
        }
        if(!psof.getCreateDate().equals(LocalDate.now())){
//            throw new IllegalArgumentException("Only current date allowed");
            throw new ValidationException("PSOF_CREATE_DT", "Only current date allowed");
        }
    }

    private void validateAmendPsof(Psof psofNew) {

        if(Objects.equals(psofNew.getCustomerType(), "01")){
            if(!List.of("01","02","03","04","05","06").contains(psofNew.getSourceFunds())){
//                throw new IllegalArgumentException("Invalid Source of Funds for Customer Type");
                throw new ValidationException("PSOF_SOURCE_OF_FUNDS","Invalid Source of Funds for Customer Type");
            }
        } else if(Objects.equals(psofNew.getCustomerType(), "02")){
            if(!List.of("01","02","03","04","05").contains(psofNew.getSourceFunds())){
//                throw new IllegalArgumentException("Invalid Source of Funds for Customer Type");
                throw new ValidationException("PSOF_SOURCE_OF_FUNDS","Invalid Source of Funds for Customer Type");
            }
        }
        if(!psofNew.getCreateDate().equals(LocalDate.now())){
//            throw new IllegalArgumentException("Only current date allowed");
            throw new ValidationException("PSOF_CREATE_DT", "Only current date allowed");
        }
    }


    public Psof persistPsof(Psof psof){
        Psof saved = psofRepo.save(psof);
        psofRepo.flush();
        return psofRepo.findById(saved.getPsofId()).orElseThrow();
    }
}
