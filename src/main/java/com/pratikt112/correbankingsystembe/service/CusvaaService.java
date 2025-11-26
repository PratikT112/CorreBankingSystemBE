package com.pratikt112.correbankingsystembe.service;

import com.pratikt112.correbankingsystembe.model.cusvaa.Cusvaa;
import com.pratikt112.correbankingsystembe.repo.CusvaaRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CusvaaService {
    @Autowired
    private CusvaaRepo cusvaaRepo;

//    public CusvaaService(CusvaaRepo cusvaaRepo){
//        this.cusvaaRepo = cusvaaRepo;
//    }

    public Cusvaa saveCusvaa(Cusvaa cusvaa){
        validateCusvaa(cusvaa);
        try {
            log.info("persisted entry in cusvaa for customer: {}", cusvaa.getId().getCustNo());
            return cusvaaRepo.save(cusvaa);

        } catch (Exception e){
            log.error("Failed to save Cusvaa entry for customer: {}", cusvaa.getId().getCustNo());
        }
        return null;
    }

    public void validateCusvaa(Cusvaa cusvaa) {
        log.info("Cusvaa validated successfully for customer: {}", cusvaa.getId().getCustNo());
    }
}
