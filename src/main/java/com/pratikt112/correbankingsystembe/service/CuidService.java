package com.pratikt112.correbankingsystembe.service;

import com.pratikt112.correbankingsystembe.config.SystemDateProvider;
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
            throw new IllegalArgumentException("Required data not provided for ID");
        }

        if(cuidRepo.existsById(newCuid.getId())){
            throw new IllegalArgumentException("Customer Record already exists.");
        }

        if(Objects.equals(newCuid.getIdMain(), "Y")){
            if(cuidRepo.mainIdExists(newCuid.getId().getInstNo(), newCuid.getId().getCustNo())){
                throw new IllegalArgumentException("Main ID already exists for customer");
            }
        }

        if(newCuid.getIdIssueDate().isAfter(SystemDateProvider.getSystemDate())){
            throw new IllegalArgumentException("Id Issue Date should not be future date");
        }

        if(newCuid.getIdExpiryDate().isBefore(SystemDateProvider.getSystemDate())){
            throw new IllegalArgumentException("Id Expiry Date should not be past date");
        }
    }

    public Cuid persistCuid(Cuid newCuid){
        return cuidRepo.save(newCuid);
    }
}
