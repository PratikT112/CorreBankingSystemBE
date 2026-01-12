package com.pratikt112.correbankingsystembe.service;


import com.pratikt112.correbankingsystembe.config.SystemDateProvider;
import com.pratikt112.correbankingsystembe.model.telm.Telm;
import com.pratikt112.correbankingsystembe.repo.TelmRepo;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class TelmService {

    private final TelmRepo telmRepo;
    private final SystemDateProvider systemDateProvider;

    public TelmService(TelmRepo telmRepo, SystemDateProvider systemDateProvider){
        this.telmRepo = telmRepo;
        this.systemDateProvider = systemDateProvider;
    }


    public Telm saveTelm(Telm telm) {

    }

    public void validateTelmEntry(@Valid Telm telm){

    }
}
