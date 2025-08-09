package com.pratikt112.correbankingsystembe.service;


import com.pratikt112.correbankingsystembe.enums.Identifier;
import com.pratikt112.correbankingsystembe.enums.VerifyFlag;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

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
    public List<Cmob> saveCmob(List<Cmob> cmobList){
        List<Cmob> savedCmob = new ArrayList<Cmob>();

        if(cmobList.size() == 2){
            Cmob first = cmobList.get(0);
            Cmob second = cmobList.get(1);
            if(first.getId().getIdentifier().equals(second.getId().getIdentifier())){
                throw new IllegalArgumentException("Both Mobile Numbers cannot be Permanent/Temporary");
            } else {
                if(first.getCustMobNo().equals(second.getCustMobNo()) && first.getIsdCode().equals(second.getIsdCode())){
                    throw new IllegalArgumentException("Both temporary and permanent mobile numbers cannot be same");
                } else {
                    if((first.getOldMobIsdCode()!=null && first.getOldCustMobNo()!=null) || (second.getOldMobIsdCode()!=null && second.getOldCustMobNo()!=null)){
                        throw new IllegalArgumentException("Old Mobile numbers not applicable during creation.");
                    } else{
                        if(first.getVerifyFlag().equals(VerifyFlag.N) && second.getVerifyFlag().equals(VerifyFlag.N)){
                            savedCmob.add(cmobRepo.save(first));
                            savedCmob.add(cmobRepo.save(second));
                            Mobh mobhFirst = new Mobh(new MobhId(first.getId().getSocNo(), first.getId().getCustNo(), dateUtil.getCurrentDateInDDMMYYYY(), timeUtil.getCurrentTimeInHHMMSSSSS()), first.getCustMobNo(), first.getOldCustMobNo(), first.getIsdCode(), first.getMakerId(), first.getCheckerId(), first.getChnlId(), first.getId().getIdentifier(), first.getVerifyFlag().toString());
                            Mobh mobhSecond = new Mobh(new MobhId(second.getId().getSocNo(), second.getId().getCustNo(), dateUtil.getCurrentDateInDDMMYYYY(), timeUtil.getCurrentTimeInHHMMSSSSS()), second.getCustMobNo(), second.getOldCustMobNo(), second.getIsdCode(), second.getMakerId(), second.getCheckerId(), second.getChnlId(), second.getId().getIdentifier(), second.getVerifyFlag().toString());
                            mobhRepo.save(mobhFirst);
                            mobhRepo.save(mobhSecond);
                            return savedCmob;

                        } else {
                            throw new IllegalArgumentException("Verify Flag other than N not applicable during creation");
                        }
                    }
                }
            }
        } else {
            Cmob theOne = cmobList.getFirst();
            if(theOne.getId().getIdentifier().equals("P")){
                if(theOne.getOldMobIsdCode() != null || theOne.getOldCustMobNo() != null){
                    throw new IllegalArgumentException("Old Mobile number not applicable during creation");
                } else {
                    if(theOne.getVerifyFlag().equals(VerifyFlag.N)){
                        savedCmob.add(cmobRepo.save(theOne));
                        Mobh mobh = new Mobh(new MobhId(theOne.getId().getSocNo(), theOne.getId().getCustNo(), dateUtil.getCurrentDateInDDMMYYYY(), timeUtil.getCurrentTimeInHHMMSSSSS()), theOne.getCustMobNo(), theOne.getOldCustMobNo(), theOne.getIsdCode(), theOne.getMakerId(), theOne.getCheckerId(), theOne.getChnlId(), theOne.getId().getIdentifier(), theOne.getVerifyFlag().toString());
                        mobhRepo.save(mobh);
                        return savedCmob;
                    }
                }
            } else {
                throw new IllegalArgumentException("Temporary mobile number cannot be created without Permanent mobile number");
            }
        }
        return List.of(new Cmob());
    }


//    @Transactional
//    public Cmob saveCmob(List<Cmob> cmobList){
//
//        try{
//            if(cmobRepo.existsById(cmobList.getId())) {
//                throw new IllegalArgumentException("Cmob entry with this key already exists");
//            }
//            Cmob savedCmob = cmobRepo.save(cmob);
//            Mobh mobh = new Mobh(new MobhId(cmob.getId().getSocNo(), cmob.getId().getCustNo(), dateUtil.getCurrentDateInDDMMYYYY(), timeUtil.getCurrentTimeInHHMMSSSSS()), cmob.getCustMobNo(), cmob.getOldCustMobNo(), cmob.getIsdCode(), cmob.getMakerId(), cmob.getCheckerId(), cmob.getChnlId(), cmob.getId().getIdentifier(), cmob.getVerifyFlag().toString());
//            mobhRepo.save(mobh);
//            return savedCmob;
//        } catch(IllegalArgumentException e){
//            throw e;
//        } catch(DataIntegrityViolationException e){
//            throw new RuntimeException("Database constraint violated while saving CMOB or MOBH: " + e.getMostSpecificCause().getMessage(), e);
//        } catch(Exception e){
//            throw new RuntimeException("Unexpected error while saving CMOB and MOBH", e);
//        }
//    }

    public List<Cmob> findForVerification(String socNo, String custNo, String isdCode, String custMobNo){
        return cmobRepo.findByIdSocNoAndIdCustNoAndIsdCodeAndCustMobNo(socNo, custNo, isdCode, custMobNo);
    }

    public List<Cmob> amendMobileNumber(String socNo, String custNo, String identifier, String isdCode, String custMobNo) {
        return List.of(new Cmob());

    }


//    public List<String> verifyMobile(String socNo, String custNo, String isdCode, String custMobNo, String verifyFlag) {
//        List<Cmob> fetched = cmobRepo.findByIdSocNoAndIdCustNoAndIsdCodeAndCustMobNo(socNo, custNo, isdCode, custMobNo);
//        if(fetched == null){
//            throw new IllegalArgumentException("Record not found.");
//        }
//        if(fetched.size() == 2){
//            if(Objects.equals(fetched.get(0).getIsdCode(), fetched.get(1).getIsdCode()) && Objects.equals(fetched.get(0).getCustMobNo(), fetched.get(1).getCustMobNo())){
//                throw new IllegalArgumentException("Both temporary and permanent mobile numbers are same.");
//            } else {
//                if(fetched.get(0).getIsdCode() != isdCode && fetched.get(0).getCustMobNo() != custMobNo || fetched.get(1).getIsdCode() != isdCode  && fetched.get(1).getCustMobNo() != custMobNo){
//
//                }
//            }
//        }
//        fetched.setVerifyFlag(VerifyFlag.Y);
//        cmobRepo.save(fetched);
//        return List.of(fetched.getIsdCode(), fetched.getCustMobNo(), fetched.getVerifyFlag().toString());
//    }

//    public Cmob amendCmob(String custNo, String identifier, String newMobile, String newIsd){
//        private id = {}
//        boolean exists = cmobRepo.existsById()
//    }

}
