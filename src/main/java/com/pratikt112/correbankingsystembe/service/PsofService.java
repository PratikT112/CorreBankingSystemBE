package com.pratikt112.correbankingsystembe.service;

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
            throw new IllegalArgumentException("Required Parameters not provided for Source of Funds Amendment.");
        }
        if(!psofRepo.existsById(psofNew.getPsofId())){
            throw new IllegalArgumentException("Record Not Found");
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
            throw new IllegalArgumentException("Record already exists");
        }

        if(Objects.equals(psof.getCustomerType(), "01")){
            if(!List.of("01","02","03","04","05","06").contains(psof.getSourceFunds())){
                throw new IllegalArgumentException("Invalid Source of Funds for Customer Type");
            }
        } else if(Objects.equals(psof.getCustomerType(), "02")){
            if(!List.of("01","02","03","04","05").contains(psof.getSourceFunds())){
                throw new IllegalArgumentException("Invalid Source of Funds for Customer Type");
            }
        }
        if(!psof.getCreateDate().equals(LocalDate.now())){
            throw new IllegalArgumentException("Only current date allowed");
        }
    }

    private void validateAmendPsof(Psof psofNew) {

        if(Objects.equals(psofNew.getCustomerType(), "01")){
            if(!List.of("01","02","03","04","05","06").contains(psofNew.getSourceFunds())){
                throw new IllegalArgumentException("Invalid Source of Funds for Customer Type");
            }
        } else if(Objects.equals(psofNew.getCustomerType(), "02")){
            if(!List.of("01","02","03","04","05").contains(psofNew.getSourceFunds())){
                throw new IllegalArgumentException("Invalid Source of Funds for Customer Type");
            }
        }
        if(!psofNew.getCreateDate().equals(LocalDate.now())){
            throw new IllegalArgumentException("Only current date allowed");
        }
    }


    public Psof persistPsof(Psof psof){
        return psofRepo.save(psof);
    }
}
