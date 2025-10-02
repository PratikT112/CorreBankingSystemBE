package com.pratikt112.correbankingsystembe.service;

import com.pratikt112.correbankingsystembe.repo.CtpmRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CtpmService {
    @Autowired
    private CtpmRepo ctpmRepo;

    public String fetchDocReqd(String tieredCust){
        String docReqd = ctpmRepo.getDocReqdByTieredCust(tieredCust);
        return docReqd;
    }
}
