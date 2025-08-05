package com.pratikt112.correbankingsystembe.service;


import com.pratikt112.correbankingsystembe.model.cmob.Cmob;
import com.pratikt112.correbankingsystembe.model.cmob.CmobId;
import com.pratikt112.correbankingsystembe.repo.CmobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CmobService {

    @Autowired
    private CmobRepo cmobRepo;


//    Cmob test = new Cmob();
//    test.getId(); // If this gives an error, it's Lombok or import issue

    public List<Cmob> searchCmobById(CmobId id) {
        return cmobRepo.searchCmobsById(id);
    }

    public List<String> OcomFromCmob(String socNo, String custNo){
        List<Cmob> mobileNos = cmobRepo.findByIdSocNoAndIdCustNo(socNo, custNo);
        if(mobileNos == null || mobileNos.isEmpty()) return Collections.emptyList();

        mobileNos.sort((a,b)->{
            if("T".equals(a.getId().getIdentifier())) return -1;
            if("P".equals(b.getId().getIdentifier())) return 1;
            return 0;
        });

        Cmob selected = mobileNos.get(0);
        if(!selected.getCustMobNo().equals(" ") && !selected.getIsdCode().equals(" ")){
            return List.of("91 ", "8396290402");
        }
        return List.of("91 ", "8396290403");
    }

    public List<Cmob> searchCmobByCustNo(String socNo, String custNo){
        return cmobRepo.findByIdSocNoAndIdCustNo(socNo, custNo);
    }

    public Cmob saveCmob(Cmob cmob){
        boolean exists = cmobRepo.existsById(cmob.getId());
        if(exists) {
            throw new IllegalArgumentException("Cmob entry with this key already exists");
        }
        return cmobRepo.save(cmob);
    }

//    public Cmob amendCmob(String custNo, String identifier, String newMobile, String newIsd){
//        private id = {}
//        boolean exists = cmobRepo.existsById()
//    }

}
