package com.pratikt112.correbankingsystembe.service;


import com.pratikt112.correbankingsystembe.model.cmob.Cmob;
import com.pratikt112.correbankingsystembe.model.cmob.CmobId;
import com.pratikt112.correbankingsystembe.model.mobh.Mobh;
import com.pratikt112.correbankingsystembe.model.mobh.MobhId;
import com.pratikt112.correbankingsystembe.repo.CmobRepo;
import com.pratikt112.correbankingsystembe.repo.MobhRepo;
import com.pratikt112.correbankingsystembe.utility.DateUtilityDDMMYYYY;
import com.pratikt112.correbankingsystembe.utility.TimeUtilityHHMMSSmmm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class CmobService {

    @Autowired
    private CmobRepo cmobRepo;

    @Autowired
    private DateUtilityDDMMYYYY dateUtil;

    @Autowired
    private TimeUtilityHHMMSSmmm timeUtil;

    @Autowired
    private MobhRepo mobhRepo;

//    Cmob test = new Cmob();
//    test.getId(); // If this gives an error, it's Lombok or import issue

    public List<Cmob> searchCmobById(CmobId id) {
        return cmobRepo.searchCmobsById(id);
    }

    public List<String> OcomFromCmob(String socNo, String custNo){
        List<Cmob> mobileNos = cmobRepo.findByIdSocNoAndIdCustNo(socNo, custNo);
        if(mobileNos == null || mobileNos.isEmpty()) return Collections.emptyList();

        Cmob selected = null;
        if(mobileNos.size()==2){
            selected = mobileNos.get(0).getId().getIdentifier().equals("T")?mobileNos.get(0):mobileNos.get(1);
            if(!selected.getCustMobNo().equals(" ") && !selected.getIsdCode().equals(" ")){
                if(List.of("Y","E","S").contains(selected.getVerifyFlag().toString())){
                    return List.of(selected.getIsdCode(), selected.getCustMobNo());
                } else if(!selected.getOldCustMobNo().equals(" ") && !selected.getOldMobIsdCode().equals(" ")) {
                    return List.of(selected.getOldMobIsdCode(), selected.getOldCustMobNo());
                } else {
                    selected = mobileNos.get(0).getId().getIdentifier().equals("P")?mobileNos.get(0):mobileNos.get(1);
                    if(!selected.getCustMobNo().equals(" ") && !selected.getIsdCode().equals(" ")){
                        if(List.of("Y","E","S").contains(selected.getVerifyFlag().toString())){
                            return List.of(selected.getIsdCode(), selected.getCustMobNo());
                        } else if (!selected.getOldCustMobNo().equals(" ") && !selected.getOldMobIsdCode().equals(" ")) {
                            return List.of(selected.getOldMobIsdCode(), selected.getOldCustMobNo());
                        }
                    }
                }
            }
        } else {
            selected = mobileNos.get(0);
            if(!selected.getCustMobNo().equals(" ") && !selected.getIsdCode().equals(" ")){
                if(List.of("Y","E","S").contains(selected.getVerifyFlag().toString())){
                    return List.of(selected.getIsdCode(), selected.getCustMobNo());
                } else if (!selected.getOldCustMobNo().equals(" ") && !selected.getOldMobIsdCode().equals(" ")) {
                    return List.of(selected.getOldMobIsdCode(), selected.getOldCustMobNo());
                }
            }
        }
        return null;
    }

    public List<Cmob> searchCmobByCustNo(String socNo, String custNo){
        return cmobRepo.findByIdSocNoAndIdCustNo(socNo, custNo);
    }

    @Transactional
    public Cmob saveCmob(Cmob cmob){

        try{
            if(cmobRepo.existsById(cmob.getId())) {
                throw new IllegalArgumentException("Cmob entry with this key already exists");
            }
            Cmob savedCmob = cmobRepo.save(cmob);
            Mobh mobh = new Mobh(new MobhId(cmob.getId().getSocNo(), cmob.getId().getCustNo(), dateUtil.getCurrentDateInDDMMYYYY(), timeUtil.getCurrentTimeInHHMMSSSSS()), cmob.getCustMobNo(), cmob.getOldCustMobNo(), cmob.getIsdCode(), cmob.getMakerId(), cmob.getCheckerId(), cmob.getChnlId(), cmob.getId().getIdentifier(), cmob.getVerifyFlag().toString());
            mobhRepo.save(mobh);
            return savedCmob;
        } catch(IllegalArgumentException e){
            throw e;
        } catch(DataIntegrityViolationException e){
            throw new RuntimeException("Database constraint violated while saving CMOB or MOBH: " + e.getMostSpecificCause().getMessage(), e);
        } catch(Exception e){
            throw new RuntimeException("Unexpected error while saving CMOB and MOBH", e);
        }
    }

//    public Cmob amendCmob(String custNo, String identifier, String newMobile, String newIsd){
//        private id = {}
//        boolean exists = cmobRepo.existsById()
//    }

}
