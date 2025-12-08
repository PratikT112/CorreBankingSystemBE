package com.pratikt112.correbankingsystembe.service;


import com.pratikt112.correbankingsystembe.enums.Identifier;
import com.pratikt112.correbankingsystembe.enums.VerifyFlag;
import com.pratikt112.correbankingsystembe.exception.*;
import com.pratikt112.correbankingsystembe.model.cmob.Cmob;
import com.pratikt112.correbankingsystembe.model.cmob.CmobId;
import com.pratikt112.correbankingsystembe.model.mbex.Mbex;
import com.pratikt112.correbankingsystembe.model.mbex.MbexId;
import com.pratikt112.correbankingsystembe.model.mobh.Mobh;
import com.pratikt112.correbankingsystembe.model.mobh.MobhId;
import com.pratikt112.correbankingsystembe.repo.ChnlMobVerifyRepo;
import com.pratikt112.correbankingsystembe.repo.CmobRepo;
import com.pratikt112.correbankingsystembe.repo.MbexRepo;
import com.pratikt112.correbankingsystembe.repo.MobhRepo;
import com.pratikt112.correbankingsystembe.utility.DateConverter;
import com.pratikt112.correbankingsystembe.utility.DateUtilityDDMMYYYY;
import com.pratikt112.correbankingsystembe.utility.TimeUtilityHHMMSSmmm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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

    @Autowired
    private MbexRepo mbexRepo;

    @Autowired
    private DateConverter dateConverter;

//    Cmob test = new Cmob();
//    test.getId(); // If this gives an error, it's Lombok or import issue

    public List<Cmob> searchCmobById(CmobId id) {
        return cmobRepo.searchCmobsById(id);
    }

//    public List<String> OcomFromCmob(String socNo, String custNo) {
//        List<Cmob> mobileNos = cmobRepo.findByIdSocNoAndIdCustNo(socNo, custNo);
//        if (mobileNos == null || mobileNos.isEmpty()) return Collections.emptyList();
//
//        Cmob selected = null;
//        if (mobileNos.size() == 2) {
//            selected = mobileNos.get(0).getId().getIdentifier().equals("T") ? mobileNos.get(0) : mobileNos.get(1);
//            if (!selected.getCustMobNo().equals(" ") && !selected.getIsdCode().equals(" ")) {
//                if (List.of("Y", "E", "S").contains(selected.getVerifyFlag().toString())) {
//                    String mbexExpDt = mbexRepo.getMobExpDtById(new MbexId(selected.getId().getSocNo(), selected.getId().getCustNo())).orElseThrow(() -> new IllegalArgumentException("No expiry date found for temporary mobile"));
//                    if (DateConverter.compareDate(mbexExpDt, dateUtil.getCurrentDateInDDMMYYYY()) > 0) {
//                        return List.of(selected.getIsdCode(), selected.getCustMobNo());
//                    } else if (!selected.getOldCustMobNo().trim().isEmpty() && !selected.getOldMobIsdCode().trim().isEmpty()) {
//                        return List.of(selected.getOldMobIsdCode(), selected.getOldCustMobNo());
//                    } else {
//                        selected = mobileNos.get(0).getId().getIdentifier().equals("P") ? mobileNos.get(0) : mobileNos.get(1);
//                        if (!selected.getCustMobNo().equals(" ") && !selected.getIsdCode().equals(" ")) {
//                            if (List.of("Y", "E", "S").contains(selected.getVerifyFlag().toString())) {
//                                return List.of(selected.getIsdCode(), selected.getCustMobNo());
//                            } else if (!selected.getOldCustMobNo().equals(" ") && !selected.getOldMobIsdCode().equals(" ")) {
//                                return List.of(selected.getOldMobIsdCode(), selected.getOldCustMobNo());
//                            }
//                        }
//                    }
//                }
//            } else {
//                selected = mobileNos.get(0);
//                if (!selected.getCustMobNo().equals(" ") && !selected.getIsdCode().equals(" ")) {
//                    if (List.of("Y", "E", "S").contains(selected.getVerifyFlag().toString())) {
//                        return List.of(selected.getIsdCode(), selected.getCustMobNo());
//                    } else if (!selected.getOldCustMobNo().equals(" ") && !selected.getOldMobIsdCode().equals(" ")) {
//                        return List.of(selected.getOldMobIsdCode(), selected.getOldCustMobNo());
//                    }
//                }
//            }
//        }
//        return null;
//    }

    public List<String> OcomFromCmob(String socNo, String custNo){
        List<Cmob> mobileNos = cmobRepo.findByIdSocNoAndIdCustNo(socNo, custNo).orElseThrow(()-> new RecordNotFoundException("CMOB", "No customer record found"));
        if(mobileNos.size() == 1){
            return tryPermanent(mobileNos);
        } else if (mobileNos.size() == 2){
            Cmob selected = pickByIdentifier(mobileNos, "T");
            if(isValidNumber(selected)){
                if(isVerified(selected)){
                    if(isTempMobileValid(selected)){
                        return currMobileAsList(selected);
                    }
                    if(hasOldNumber(selected)){
                        return oldMobileAsList(selected);
                    }
                    return tryPermanent(mobileNos);
                }
            }
        }
        return null;
    }

    public Cmob pickByIdentifier(List<Cmob> mobiles, String identifier){
        return mobiles.get(0).getId().getIdentifier().equals(identifier)
                ? mobiles.get(0)
                : mobiles.get(1);
    }

    public boolean isValidNumber(Cmob cmob){
        return cmob != null
                && !cmob.getCustMobNo().trim().isEmpty()
                && !cmob.getIsdCode().trim().isEmpty();
    }


    public boolean isVerified(Cmob cmob){
        return List.of("Y","E","S").contains(cmob.getVerifyFlag().toString());
    }

    public boolean isTempMobileValid(Cmob cmob){
        String mbexExpDt = mbexRepo.getMobExpDtById(new MbexId(cmob.getId().getSocNo(), cmob.getId().getCustNo()))
                .orElseThrow(()->new RecordNotFoundException("RECORD_NOT_FOUND", "No expiry date found for temporary mobile", "Kindly check mobile number and try again"));
        return DateConverter.compareDate(mbexExpDt, dateUtil.getCurrentDateInDDMMYYYY()) > 0;
    }

    public boolean hasOldNumber(Cmob cmob){
        return !cmob.getOldMobIsdCode().trim().isEmpty()
                && !cmob.getOldCustMobNo().trim().isEmpty();
    }

    public List<String> currMobileAsList(Cmob cmob){
        return List.of(cmob.getIsdCode(), cmob.getCustMobNo());
    }

    public List<String> oldMobileAsList(Cmob cmob){
        return List.of(cmob.getOldMobIsdCode(), cmob.getOldCustMobNo());
    }

    public List<String> tryPermanent(List<Cmob> mobileNos){
        Cmob permanent = pickByIdentifier(mobileNos, "P");
        if(isValidNumber(permanent)){
            if(isVerified(permanent)){
                return currMobileAsList(permanent);
            }
            if(hasOldNumber(permanent)){
                return oldMobileAsList(permanent);
            }
        }
        return null;
    }

    public List<Cmob> searchCmobByCustNo(String socNo, String custNo) {
        return cmobRepo.findByIdSocNoAndIdCustNo(socNo, custNo).orElseThrow(()->new RecordNotFoundException("CMOB", custNo));
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

    public void checkChnlMobVerify(Cmob theOne) {
        if(Objects.equals(theOne.getChnlId(), " ")){
            if(chnlMobVerifyRepo.existsById("SPACE")){
                theOne.setVerifyFlag(VerifyFlag.Y);
                theOne.setDov(LocalDate.now());
            }
        } else {
            if(chnlMobVerifyRepo.existsById(theOne.getChnlId())){
                theOne.setVerifyFlag(VerifyFlag.Y);
                theOne.setDov(LocalDate.now());
            }
        }
    }

    public void validateTwoCmobEntries(Cmob first, Cmob second) {

//        if(cmobRepo.existsById(first.getId()) || cmobRepo.existsById(second.getId())){
//            throw new IllegalArgumentException("Customer Record already exists");
//        }

        if(cmobRepo.existsById(first.getId())){
            throw new DuplicateRecordException("CMOB", first.getCustMobNo());
        }

        if(cmobRepo.existsById(second.getId())){
            throw new DuplicateRecordException("CMOB", second.getCustMobNo());
        }




        if(!(first.getId().getSocNo().equals(second.getId().getSocNo()) && first.getId().getCustNo().equals(second.getId().getCustNo()))){
            throw new IncompleteDataException("INCOMPLETE_DATA_EXCEPTION",
                    "Both Mobile Numbers should be provided for the same customer",
                    "Please check your data and try again");
        }

        if(first.getMbexExpDt()==null){
            throw new IncompleteDataException("Expiry date is needed for Temporary mobile number",
                    "Provide expiry date for temporary mobile number and try again", false);
        }

        if(first.getId().getIdentifier().equals(second.getId().getIdentifier())){
            throw new ProcessingException("PROCESSING_ERROR",
                    "Both Mobile Numbers cannot be Permanent/Temporary",
                    "Please provide different identifiers for both mobile numbers");
        }

        if(first.getCustMobNo().equals(second.getCustMobNo()) && first.getIsdCode().equals(second.getIsdCode())){
            throw new ProcessingException("PROCESSING_ERROR",
                    "Both Mobile Numbers cannot be the same",
                    "Please provide different mobile numbers and try again");
        }

        if((first.getOldMobIsdCode()!= null && first.getOldCustMobNo()!= null)
                ||(second.getOldMobIsdCode()!= null && second.getOldCustMobNo()!= null)){
            throw new ProcessingException("PROCESSING_ERROR",
                    "Old Mobile numbers not applicable during creation.",
                    "Please remove old mobile number an try again.");
        }

        if (!first.getVerifyFlag().equals(VerifyFlag.N) ||
                !second.getVerifyFlag().equals(VerifyFlag.N)) {
            throw new ProcessingException("PROCESSING_ERROR",
                    "Verify Flag other than N not applicable during creation",
                    "Please check verify flag and try again");
        }
    }

    public void validateSingleCmobEntry(Cmob theOne){
        if(cmobRepo.existsById(theOne.getId())){
            throw new DuplicateRecordException("CMOB", theOne.getCustMobNo());
        }

        if(!"P".equals(theOne.getId().getIdentifier())){
            throw new ProcessingException("PROCESSING_ERROR",
                    "Temporary mobile number cannot be created without Permanent mobile number.",
                    "Please provide permanent mobile number and try again.");
        }

        if(theOne.getOldMobIsdCode() != null || theOne.getOldCustMobNo()!= null){
            throw new ProcessingException("PROCESSING_ERROR",
                    "Old Mobile numbers not applicable during creation.",
                    "Please remove old mobile number an try again.");
        }

//        if(!theOne.getVerifyFlag().equals(VerifyFlag.N)){
//            throw new IllegalArgumentException("Verify Flag other than N not applicable during creation");
//        }
    }

    public List<Cmob> persistCmobAndMobh(List<Cmob> cmobList){
        List<Cmob> saved = new ArrayList<>();
        for (Cmob cmob: cmobList){
            saved.add(cmobRepo.save(cmob));
            mobhRepo.save(createMobhFromCmob(cmob));

            if(Objects.equals(cmob.getId().getIdentifier(), Identifier.T.toString()) && cmob.getMbexExpDt() != null){
                Mbex mbex = new Mbex(new MbexId(cmob.getId().getSocNo(), cmob.getId().getCustNo()), cmob.getMbexExpDt());
                mbexRepo.save(mbex);
            }
        }
        return saved;
    }

    public Mobh createMobhFromCmob(Cmob cmob){
        return new Mobh(
                new MobhId(
                        cmob.getId().getSocNo(),
                        cmob.getId().getCustNo(),
                        LocalDate.now(),
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
                Cmob first;
                Cmob second;
                if (Objects.equals(cmobList.get(0).getId().getIdentifier(), Identifier.T.toString())) {
                    first = cmobList.get(0);
                    second = cmobList.get(1);
                } else {
                    first = cmobList.get(1);
                    second = cmobList.get(0);
                }

                return saveTwoCmobEntries(first, second);
            } else if(cmobList.size() == 1){
                Cmob theOne = cmobList.get(0);
                return saveSingleCmobEntry(theOne);
            } else {
                throw new ValidationException("VALIDATION_ERROR",
                        "Invalid Mobile Number count.",
                        "Either 1 or 2 mobile numbers can be provided for a customer during creation.");
            }
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

            Cmob toBeAmended = cmobRepo.findById(new CmobId(socNo, custNo, identifier)).orElseThrow(()-> new RecordNotFoundException("CMOB", custNo));
            if(Objects.equals(toBeAmended.getIsdCode(), newIsdCode) && Objects.equals(toBeAmended.getCustMobNo(), newCustMobNo)){
//                throw new IllegalArgumentException();
                throw new ValidationException("AMENDMENT_EXCEPTION",
                        "Previous and New Mobile numbers cannot be the same",
                        "Provide a different mobile number for amendment");
            }

            if(!Objects.equals(toBeAmended.getVerifyFlag(), VerifyFlag.Y)){
//                throw new IllegalArgumentException("Previous Mobile number not verified");
                throw new ValidationException("AMENDMENT_EXCEPTION",
                        "Previous Mobile number not verified",
                        "Previous Mobile number should be verified before amendment");
            }

            toBeAmended.setOldMobIsdCode(toBeAmended.getIsdCode());
            toBeAmended.setOldCustMobNo(toBeAmended.getCustMobNo());
            toBeAmended.setIsdCode(newIsdCode);
            toBeAmended.setCustMobNo(newCustMobNo);
            toBeAmended.setVerifyFlag(VerifyFlag.N);
            toBeAmended.setDov(null);
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
//            throw new IllegalArgumentException("Record not found for customer and mobile");
            throw new RecordNotFoundException("CMOB", "Record not found for CIF: " + custNo + " and mobile: " + custMobNo);
        }

        if (toBeVerified.getVerifyFlag().equals(VerifyFlag.Y)) {
//            throw new IllegalArgumentException("Mobile number is already verified");
            throw new ValidationException("CMOB_VERIFY_FLAG", "Mobile number is already verified");
        }

        if (toBeVerified.getVerifyFlag().equals(VerifyFlag.S)) {
//            throw new IllegalArgumentException("Verification not applicable for Non Personal Customers");
            throw new ProcessingException("NOT_APPLICABLE_EXCEPTION",
                    "Verification not applicable for Non Personal Customers",
                    "Verification is only applicable for personal customers");
        }

        if (toBeVerified.getVerifyFlag().equals(VerifyFlag.X)) {
//            throw new IllegalArgumentException("Verification not applicable for Cancelled Mobile Number");
            throw new ProcessingException("NOT_APPLICABLE_EXCEPTION",
                    "Verification not applicable for cancelled mobile numbers.",
                    "Verification is only applicable for active mobile numbers.");
        }

        toBeVerified.setVerifyFlag(VerifyFlag.Y);
        toBeVerified.setDov(LocalDate.now());
        return persistCmobAndMobh(List.of(toBeVerified)).get(0);
    }
}
