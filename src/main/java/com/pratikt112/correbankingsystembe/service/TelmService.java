package com.pratikt112.correbankingsystembe.service;


import com.pratikt112.correbankingsystembe.config.SystemDateProvider;
import com.pratikt112.correbankingsystembe.model.telm.Telm;
import com.pratikt112.correbankingsystembe.repo.TelmRepo;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
public class TelmService {

    private final TelmRepo telmRepo;
    private final SystemDateProvider systemDateProvider;

    public TelmService(TelmRepo telmRepo, SystemDateProvider systemDateProvider){
        this.telmRepo = telmRepo;
        this.systemDateProvider = systemDateProvider;
    }


    public Telm saveTelm(Telm telm) {
        validateTelmEntry(telm);
        return telmRepo.save(telm);
    }

    public void validateTelmEntry(@Valid Telm telm){
        if(Period.between(telm.getTellerDob(), LocalDate.now()).getYears() < 18){
            throw new ValidationException("Teller should be atleast 18 years of age");
        }
    }
}
