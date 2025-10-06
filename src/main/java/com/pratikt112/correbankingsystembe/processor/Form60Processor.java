package com.pratikt112.correbankingsystembe.processor;

import com.pratikt112.correbankingsystembe.DTOs.CobData;
import com.pratikt112.correbankingsystembe.DTOs.Form60;
import com.pratikt112.correbankingsystembe.model.cr60.Cr60;
import com.pratikt112.correbankingsystembe.repo.Cr60Repo;
import com.pratikt112.correbankingsystembe.service.Cr60Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class Form60Processor implements CustomerProcessingRule{

    private final Cr60Repo cr60Repo;
    private final Cr60Service cr60Service;
//    private final String newCIF;

    @Autowired
    public Form60Processor(Cr60Repo cr60Repo,  Cr60Service cr60Service/*, String newCIF*/) {
        this.cr60Repo = cr60Repo;
        this.cr60Service = cr60Service;
//        this.newCIF = newCIF;
    }

    @Override
    public boolean supports(CobData cobData, String newCIF) {
        return cobData.getCustForm60() != null;
    }

    @Override
    public void process(CobData cobData, String newCIF) {
        Cr60 constructed = constructCr60FromCobData(cobData, newCIF);
        cr60Service.saveCr60(constructed);
    }

    static Cr60 constructCr60FromCobData(CobData cobData, String newCIF){
        Form60 dtoForm60 = cobData.getCustForm60();
        Cr60 constructed = new Cr60();
        constructed.setKey1("003" + newCIF);
        constructed.setCr60SubmitDate(dtoForm60.getSubDate());
        constructed.setTxnDate(dtoForm60.getTranDate());
        constructed.setAgriIncome(dtoForm60.getAgriIncome());
        constructed.setOtherIncome(dtoForm60.getOtherIncome());
        constructed.setPanApply(dtoForm60.getPanAppliedFlag());
        constructed.setPanApplyDate(dtoForm60.getPanAppliedDate());
        constructed.setPanAckNo(dtoForm60.getPanAckNo());
        constructed.setTxnBranch("04234");   // Hardcoded, to be amended later
        constructed.setCr60AckNo("6239532095056023563794");  // Hardcoded, to be amended later
        constructed.setCustName(cobData.getCustNameMain().getFirstName() + " " + cobData.getCustNameMain().getMiddleName() + " " + cobData.getCustNameMain().getLastName());
        constructed.setFatherName(cobData.getCustFatherName().getFirstName());
        constructed.setCustDob(cobData.getCustDob());
        constructed.setCustMobileNo(cobData.getCustMobNo().getMobNo());
        constructed.setCustTelephoneNo(cobData.getCustTeleRes());
        constructed.setCustIdType(cobData.getCustOvdDetails().getOvdDocType());
        constructed.setCustIdNum(cobData.getCustOvdDetails().getOvdDocNumber());
        constructed.setAddr1(cobData.getCustMainAddressDetails().getAddressLine1());
        constructed.setAddr2(cobData.getCustMainAddressDetails().getAddressLine2());
        constructed.setAddr3(cobData.getCustMainAddressDetails().getAddressLine3());
        constructed.setCityCode(cobData.getCustMainAddressDetails().getCity());
        constructed.setCountryCode(cobData.getCustMainAddressDetails().getCountry());
        constructed.setStateCode(cobData.getCustMainAddressDetails().getState());
        constructed.setPostcode(cobData.getCustMainAddressDetails().getPincode());
        constructed.setMakerId("2199690");
        constructed.setCheckerId("2199691");
        return constructed;
    }
}
