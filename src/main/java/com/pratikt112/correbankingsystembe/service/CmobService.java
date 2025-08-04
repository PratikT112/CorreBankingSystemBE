package com.pratikt112.correbankingsystembe.service;


import com.pratikt112.correbankingsystembe.model.cmob.Cmob;
import com.pratikt112.correbankingsystembe.repo.CmobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CmobService {

    @Autowired
    private CmobRepo cmobRepo;

    public static List<Cmob> searchCmobByCustNo(String custNo) {
        return cmobRepo.findAllByCustNo(cust_no);
    }

}
