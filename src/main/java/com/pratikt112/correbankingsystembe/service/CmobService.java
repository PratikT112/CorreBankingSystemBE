package com.pratikt112.correbankingsystembe.service;


import com.pratikt112.correbankingsystembe.enums.VerifyFlag;
import com.pratikt112.correbankingsystembe.model.cmob.Cmob;
import com.pratikt112.correbankingsystembe.model.cmob.CmobId;
import com.pratikt112.correbankingsystembe.model.mobh.Mobh;
import com.pratikt112.correbankingsystembe.model.mobh.MobhId;
import com.pratikt112.correbankingsystembe.repo.ChnlMobVerifyRepo;
import com.pratikt112.correbankingsystembe.repo.CmobRepo;
import com.pratikt112.correbankingsystembe.repo.MobhRepo;
import com.pratikt112.correbankingsystembe.utility.DateUtilityDDMMYYYY;
import com.pratikt112.correbankingsystembe.utility.TimeUtilityHHMMSSmmm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class CmobService {

    @Autowired
    private CmobRepo cmobRepo;

    @Autowired
    private ChnlMobVerifyRepo chnlMobVerifyRepo;

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

    public List<String> OcomFromCmob(String socNo, String custNo) {
        List<Cmob> mobileNos = cmobRepo.findByIdSocNoAndIdCustNo(socNo, custNo);
        if (mobileNos == null || mobileNos.isEmpty()) return Collections.emptyList();

        Cmob selected = null;
        if (mobileNos.size() == 2) {
            selected = mobileNos.get(0).getId().getIdentifier().equals("T") ? mobileNos.get(0) : mobileNos.get(1);
            if (!selected.getCustMobNo().equals(" ") && !selected.getIsdCode().equals(" ")) {
                if (List.of("Y", "E", "S").contains(selected.getVerifyFlag().toString())) {
                    return List.of(selected.getIsdCode(), selected.getCustMobNo());
                } else if (!selected.getOldCustMobNo().equals(" ") && !selected.getOldMobIsdCode().equals(" ")) {
                    return List.of(selected.getOldMobIsdCode(), selected.getOldCustMobNo());
                } else {
                    selected = mobileNos.get(0).getId().getIdentifier().equals("P") ? mobileNos.get(0) : mobileNos.get(1);
                    if (!selected.getCustMobNo().equals(" ") && !selected.getIsdCode().equals(" ")) {
                        if (List.of("Y", "E", "S").contains(selected.getVerifyFlag().toString())) {
                            return List.of(selected.getIsdCode(), selected.getCustMobNo());
                        } else if (!selected.getOldCustMobNo().equals(" ") && !selected.getOldMobIsdCode().equals(" ")) {
                            return List.of(selected.getOldMobIsdCode(), selected.getOldCustMobNo());
                        }
                    }
                }
            }
        } else {
            selected = mobileNos.get(0);
            if (!selected.getCustMobNo().equals(" ") && !selected.getIsdCode().equals(" ")) {
                if (List.of("Y", "E", "S").contains(selected.getVerifyFlag().toString())) {
                    return List.of(selected.getIsdCode(), selected.getCustMobNo());
                } else if (!selected.getOldCustMobNo().equals(" ") && !selected.getOldMobIsdCode().equals(" ")) {
                    return List.of(selected.getOldMobIsdCode(), selected.getOldCustMobNo());
                }
            }
        }
        return null;
    }

    public List<Cmob> searchCmobByCustNo(String socNo, String custNo) {
        return cmobRepo.findByIdSocNoAndIdCustNo(socNo, custNo);
    }

    public List<Cmob> saveTwoCmobEntries(Cmob first, Cmob second){
        validateTwoCmobEntries(first, second);
        checkChnlMobVerify(first);
        checkChnlMobVerify(second);
        return persistCmobAndMobh(List.of(first, second));
    }



    public List<Cmob> saveSingleCmobEntry(Cmob theOne){
        validateSingleCmobEntry(theOne);
        checkChnlMobVerify(theOne);
        return persistCmobAndMobh(List.of(theOne));
    }

    private void checkChnlMobVerify(Cmob theOne) {
        if(Objects.equals(theOne.getChnlId(), " ")){
            if(chnlMobVerifyRepo.existsById("SPACE")){
                theOne.setVerifyFlag(VerifyFlag.Y);
                theOne.setDov(dateUtil.getCurrentDateInDDMMYYYY());
            }
        } else {
            if(chnlMobVerifyRepo.existsById(theOne.getChnlId())){
                theOne.setVerifyFlag(VerifyFlag.Y);
                theOne.setDov(dateUtil.getCurrentDateInDDMMYYYY());
            }
        }
    }

    private void validateTwoCmobEntries(Cmob first, Cmob second) {

        if(cmobRepo.existsById(first.getId()) || cmobRepo.existsById(second.getId())){
            throw new IllegalArgumentException("Customer Record already exists");
        }

        if(!(first.getId().getSocNo().equals(second.getId().getSocNo()) && first.getId().getCustNo().equals(second.getId().getCustNo()))){
            throw new IllegalArgumentException("Both Mobile Numbers should be provided for the same customer");
        }

        if(first.getId().getIdentifier().equals(second.getId().getIdentifier())){
            throw new IllegalArgumentException("Both Mobile Numbers cannot be Permanent/Temporary");
        }

        if(first.getCustMobNo().equals(second.getCustMobNo()) && first.getIsdCode().equals(second.getIsdCode())){
            throw new IllegalArgumentException("Both Mobile Numbers cannot be the same");
        }

        if((first.getOldMobIsdCode()!= null && first.getOldCustMobNo()!= null)
                ||(second.getOldMobIsdCode()!= null && second.getOldCustMobNo()!= null)){
            throw new IllegalArgumentException("Old Mobile numbers not applicable during creation.");
        }

        if (!first.getVerifyFlag().equals(VerifyFlag.N) ||
                !second.getVerifyFlag().equals(VerifyFlag.N)) {
            throw new IllegalArgumentException("Verify Flag other than N not applicable during creation");
        }
    }

    public void validateSingleCmobEntry(Cmob theOne){
        if(cmobRepo.existsById(theOne.getId())){
            throw new IllegalArgumentException("Customer Record already exists");
        }

        if(!"P".equals(theOne.getId().getIdentifier())){
            throw new IllegalArgumentException("Temporary mobile number cannot be created without Permanent mobile number");
        }

        if(theOne.getOldMobIsdCode() != null || theOne.getOldCustMobNo()!= null){
            throw new IllegalArgumentException("Old Mobile numbers not applicable during creation.");
        }

        if(!theOne.getVerifyFlag().equals(VerifyFlag.N)){
            throw new IllegalArgumentException("Verify Flag other than N not applicable during creation");
        }
    }

    public List<Cmob> persistCmobAndMobh(List<Cmob> cmobList){
        List<Cmob> saved = new ArrayList<>();
        for (Cmob cmob: cmobList){
            saved.add(cmobRepo.save(cmob));
            mobhRepo.save(createMobhFromCmob(cmob));
        }
        return saved;
    }

    public Mobh createMobhFromCmob(Cmob cmob){
        return new Mobh(
                new MobhId(
                        cmob.getId().getSocNo(),
                        cmob.getId().getCustNo(),
                        dateUtil.getCurrentDateInDDMMYYYY(),
                        timeUtil.getCurrentTimeInHHMMSSSSS()
                ),
                cmob.getCustMobNo(),
                cmob.getOldCustMobNo(),
                cmob.getIsdCode(),
                cmob.getMakerId(),
                cmob.getCheckerId(),
                cmob.getChnlId(),
                cmob.getId().getIdentifier(),
                cmob.getVerifyFlag().toString()
        );
    }


    @Transactional
    public List<Cmob> saveCmob(List<Cmob> cmobList) {
        try{
            List<Cmob> savedCmob = new ArrayList<Cmob>();
            if (cmobList.size() == 2) {
                Cmob first = cmobList.get(0);
                Cmob second = cmobList.get(1);
                return saveTwoCmobEntries(first, second);
            } else if(cmobList.size() == 1){
                Cmob theOne = cmobList.get(0);
                return saveSingleCmobEntry(theOne);
            } else {
                throw new IllegalArgumentException("Invalid Mobile Number count");
            }
        } catch (IllegalArgumentException e){
            throw e;
        } catch (DataIntegrityViolationException e){
            throw new RuntimeException("Database constraint violated while saving CMOB or MOBH: " + e.getMostSpecificCause().getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error while saving CMOB and MOBH", e);
        }
    }


    public List<Cmob> findForVerification(String socNo, String custNo, String isdCode, String custMobNo) {
        return cmobRepo.findByIdSocNoAndIdCustNoAndIsdCodeAndCustMobNo(socNo, custNo, isdCode, custMobNo);
    }

    @Transactional
    public Cmob amendMobileNumber(String socNo, String custNo, String identifier, String newIsdCode, String newCustMobNo) {
        try {
            if(socNo == null || custNo == null || identifier == null || newIsdCode == null || newCustMobNo == null){
                throw new IllegalArgumentException("Required Parameters not provided.");
            }

            Cmob toBeAmended = cmobRepo.findById(new CmobId(socNo, custNo, identifier)).orElseThrow(()-> new IllegalArgumentException("Customer Record not found"));
            if(Objects.equals(toBeAmended.getIsdCode(), newIsdCode) && Objects.equals(toBeAmended.getCustMobNo(), newCustMobNo)){
                throw new IllegalArgumentException("Previous and New Mobile numbers cannot be the same");
            }

            if(!Objects.equals(toBeAmended.getVerifyFlag(), VerifyFlag.Y)){
                throw new IllegalArgumentException("Previous Mobile number not verified");
            }

            toBeAmended.setOldMobIsdCode(toBeAmended.getIsdCode());
            toBeAmended.setOldCustMobNo(toBeAmended.getCustMobNo());
            toBeAmended.setIsdCode(newIsdCode);
            toBeAmended.setCustMobNo(newCustMobNo);
            toBeAmended.setVerifyFlag(VerifyFlag.N);
            toBeAmended.setDov("0");
            return (Cmob) persistCmobAndMobh(List.of(toBeAmended)).get(0);
        } catch (IllegalArgumentException e){
            throw e;
        } catch (DataIntegrityViolationException e){
            throw new RuntimeException("Database constraint violated while updating CMOB or MOBH: " + e.getMostSpecificCause().getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error while updating CMOB and MOBH", e);
        }
    }

    @Transactional
    public Cmob verifyMobile(String socNo, String custNo, String isdCode, String custMobNo, String verifyFlag) {
        Cmob toBeVerified = (Cmob) findForVerification(socNo, custNo, isdCode, custMobNo).getFirst();
        if (toBeVerified == null) {
            throw new IllegalArgumentException("Record not found for customer and mobile");
        }

        if (toBeVerified.getVerifyFlag().equals(VerifyFlag.Y)) {
            throw new IllegalArgumentException("Mobile number is already verified");
        }

        if (toBeVerified.getVerifyFlag().equals(VerifyFlag.S)) {
            throw new IllegalArgumentException("Verification not applicable for Non Personal Customers");
        }

        if (toBeVerified.getVerifyFlag().equals(VerifyFlag.X)) {
            throw new IllegalArgumentException("Verification not applicable for Cancelled Mobile Number");
        }

        toBeVerified.setVerifyFlag(VerifyFlag.Y);
        toBeVerified.setDov(dateUtil.getCurrentDateInDDMMYYYY());
        return persistCmobAndMobh(List.of(toBeVerified)).get(0);
    }
}
