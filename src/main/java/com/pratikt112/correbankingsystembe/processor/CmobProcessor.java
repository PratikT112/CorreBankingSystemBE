package com.pratikt112.correbankingsystembe.processor;

import com.pratikt112.correbankingsystembe.DTOs.CobData;
import com.pratikt112.correbankingsystembe.DTOs.MobileNumber;
import com.pratikt112.correbankingsystembe.config.SystemDateProvider;
import com.pratikt112.correbankingsystembe.enums.VerifyFlag;
import com.pratikt112.correbankingsystembe.model.cmob.Cmob;
import com.pratikt112.correbankingsystembe.model.cmob.CmobId;
import com.pratikt112.correbankingsystembe.repo.ChnlMobVerifyRepo;
import com.pratikt112.correbankingsystembe.repo.CmobRepo;
import com.pratikt112.correbankingsystembe.repo.If3501Repo;
import com.pratikt112.correbankingsystembe.service.CmobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Order(3)
public class CmobProcessor implements CustomerProcessingRule{
    private final CmobRepo cmobRepo;
    private final If3501Repo if3501Repo;
    private final CmobService cmobService;
    private final ChnlMobVerifyRepo chnlMobVerifyRepo;

    @Autowired
    public CmobProcessor(CmobRepo cmobRepo, CmobService cmobService, If3501Repo if3501Repo, ChnlMobVerifyRepo chnlMobVerifyRepo){
        this.cmobRepo = cmobRepo;
        this.cmobService = cmobService;
        this.if3501Repo = if3501Repo;
        this.chnlMobVerifyRepo = chnlMobVerifyRepo;
    }

    @Override
    public String getProcessorName() {
        return "CMOB processor";
    }

    @Override
    public boolean supports(CobData cobData, String newCIF) {
        return true;
    }

    @Override
    public void process(CobData cobData, String newCIF) {
        Cmob constructed = constructCmobFromCobData(cobData, newCIF, if3501Repo, chnlMobVerifyRepo);
        cmobService.saveCmob(List.of(constructed));
    }

    static Cmob constructCmobFromCobData(CobData cobData, String newCIF, If3501Repo if3501Repo, ChnlMobVerifyRepo chnlMobVerifyRepo) {
        MobileNumber dtoMobile = cobData.getCustMobNo();
        Cmob constructed = new Cmob();
        constructed.setId(new CmobId("003", newCIF, "P"));
        constructed.setCustMobNo(dtoMobile.getMobNo());
        constructed.setIsdCode(dtoMobile.getIsd());
        constructed.setOldCustMobNo(null);
        constructed.setOldMobIsdCode(null);
        constructed.setVerifyFlag(getVerifyFlagFromCobData(cobData, if3501Repo, chnlMobVerifyRepo));
        constructed.setChnlId(cobData.getBatchTandem());
        constructed.setChangeDate(SystemDateProvider.getSystemDate());
        constructed.setMakerId("2199690");
        constructed.setCheckerId("2199691");
        constructed.setDov(constructed.getVerifyFlag().equals(VerifyFlag.Y) ? SystemDateProvider.getSystemDate() : null);
        return constructed;
    }

    static VerifyFlag getVerifyFlagFromCobData(CobData cobData, If3501Repo if3501Repo, ChnlMobVerifyRepo chnlMobVerifyRepo){
        if(cobData.getCustMobNo().getMobNo().isEmpty() && cobData.getCustMobNo().getIsd().isEmpty()) return VerifyFlag.valueOf(" ");
        if(Objects.equals(cobData.getCustTierType().substring(0, 2), "02")){
            return VerifyFlag.S;
        }
        if(if3501Repo.existsById(cobData.getTransactionBranch())){
            if(chnlMobVerifyRepo.existsById(cobData.getBatchTandem())){
                return VerifyFlag.Y;
            }
            return VerifyFlag.E;
        }
        return VerifyFlag.N;
    }
}
