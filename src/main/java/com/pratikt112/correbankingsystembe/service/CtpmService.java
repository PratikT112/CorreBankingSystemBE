package com.pratikt112.correbankingsystembe.service;

import com.pratikt112.correbankingsystembe.model.ctpm.Ctpm;
import com.pratikt112.correbankingsystembe.repo.CtpmRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CtpmService {
    @Autowired
    private CtpmRepo ctpmRepo;

    public String fetchDocReqd(String tieredCust){
        return ctpmRepo.getDocReqdByTieredCust(tieredCust);
    }

    public Ctpm getCtpm(String tieredCust) {
        return ctpmRepo.getCtpmByTieredCust(tieredCust);
    }

    public Ctpm saveCtpm(Ctpm ctpm) {
        return ctpmRepo.save(ctpm);
    }
}
