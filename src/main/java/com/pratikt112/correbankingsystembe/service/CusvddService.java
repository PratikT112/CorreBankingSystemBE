package com.pratikt112.correbankingsystembe.service;

import com.pratikt112.correbankingsystembe.exception.DuplicateRecordException;
import com.pratikt112.correbankingsystembe.exception.IncompleteDataException;
import com.pratikt112.correbankingsystembe.exception.ValidationException;
import com.pratikt112.correbankingsystembe.model.cusvdd.Cusvdd;
import com.pratikt112.correbankingsystembe.repo.CusvddRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CusvddService {

    private CusvddRepo cusvddRepo;

    public CusvddService(CusvddRepo cusvddRepo){
        this.cusvddRepo = cusvddRepo;
    }

    public Cusvdd saveCusvdd(Cusvdd newCusvdd){
        validateCusvdd(newCusvdd);
        return persistCusvdd(newCusvdd);
    }

    public void validateCusvdd(Cusvdd newCusvdd) {
        if(newCusvdd.getId1().isEmpty()){
            throw new IncompleteDataException("CUSVDD", "ID1");
        }
        if(newCusvdd.getBirthDate1().isAfter(LocalDate.now())){
            throw new ValidationException("Date of Birth", "Date of Birth cannot be future date.");
        }
        if(newCusvdd.getDeathDate() != null){
            throw new ValidationException("Date of Death", "Date of death not applicable while customer creation");
        }
        if(!List.of("M","F","T").contains(newCusvdd.getSexCode())){
            throw new ValidationException("GENDER_CODE", "Gender can only be Male, Female or Third Gender");
        }
        if(newCusvdd.getMaritalStatus() == null || newCusvdd.getMaritalStatus().isBlank()){
            throw new IncompleteDataException("CUSVDD", "MARITAL_STATUS");
        }
        if(newCusvdd.getOccupDesc() == null || newCusvdd.getOccupDesc().isBlank()){
            throw new IncompleteDataException("CUSVDD", "OCCUPATION_DESC");
        }
        if(newCusvdd.getOccupationCode() == null || newCusvdd.getOccupationCode().isBlank()){
            throw new IncompleteDataException("CUSVDD", "OCCUPATION_CODE");
        }
        if(newCusvdd.getBirthPlace() == null || newCusvdd.getBirthPlace().isBlank()){
            throw new IncompleteDataException("CUSVDD", "BIRTH_PLACE");
        }
        if(newCusvdd.getPostcode() == null || newCusvdd.getPostcode().isBlank()){
            throw new IncompleteDataException("CUSVDD", "POST_CODE");
        }
        if(cusvddRepo.existsById(newCusvdd.getKey1())){
            throw new DuplicateRecordException("CUSVDD", newCusvdd.getKey1());
        }
    }

    public Cusvdd persistCusvdd(Cusvdd newCusvdd){
        return cusvddRepo.save(newCusvdd);
    }
}
