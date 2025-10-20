package com.pratikt112.correbankingsystembe.service;

import com.pratikt112.correbankingsystembe.config.SystemDateProvider;
import com.pratikt112.correbankingsystembe.exception.BankingSystemException;
import com.pratikt112.correbankingsystembe.exception.DuplicateRecordException;
import com.pratikt112.correbankingsystembe.exception.IncompleteDataException;
import com.pratikt112.correbankingsystembe.exception.ValidationException;
import com.pratikt112.correbankingsystembe.model.cuid.Cuid;
import com.pratikt112.correbankingsystembe.repo.CuidRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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

    public void validateCuid(Cuid newCuid){

        if(newCuid.getId()==null ||
                newCuid.getIdNumber()==null ||
                newCuid.getIdIssueDate() == null ||
                newCuid.getIdExpiryDate() == null ||
                newCuid.getIdIssueAt() == null ||
                newCuid.getIdMain() == null){
            throw new IncompleteDataException("CUID", "IdIssueDate");      //To be fixed
        }

        if(cuidRepo.existsById(newCuid.getId())){
            throw new DuplicateRecordException("CUID", newCuid.getId().getCustNo());
        }

        if(Objects.equals(newCuid.getIdMain(), "Y")){
            if(cuidRepo.mainIdExists(newCuid.getId().getInstNo(), newCuid.getId().getCustNo())){
                throw new IllegalArgumentException("Main ID already exists for customer");
//                throw new ValidationException("VALIDATION_ERROR",
//                        "Main ID already exists for customer",
//                        "Invalid ID Issue Date provided");
            }
        }

        if(newCuid.getIdIssueDate().isAfter(SystemDateProvider.getSystemDate())){
//            throw new IllegalArgumentException("Id Issue Date should not be future date");
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
            throw new IllegalArgumentException("Id already exists for CIF: "+ dupCIFNo);
        }

    }

    public Cuid persistCuid(Cuid newCuid){
        return cuidRepo.save(newCuid);
    }
}
