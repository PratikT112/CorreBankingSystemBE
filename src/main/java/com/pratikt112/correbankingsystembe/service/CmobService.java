package com.pratikt112.correbankingsystembe.service;


import com.pratikt112.correbankingsystembe.model.Cmob;
import com.pratikt112.correbankingsystembe.repo.CmobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CmobService {

    @Autowired
    private CmobRepo cmobRepo;

    public List<Cmob> getCmobByCustomer(String cust_no) { return cmobRepo.findAllByCustNo(cust_no)}
}
