package com.pratikt112.correbankingsystembe.processor;

import com.pratikt112.correbankingsystembe.DTOs.CobData;
import com.pratikt112.correbankingsystembe.model.psof.Psof;
import com.pratikt112.correbankingsystembe.model.psof.PsofId;
import com.pratikt112.correbankingsystembe.service.PsofService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Order(7)
public class PsofProcessor implements CustomerProcessingRule{

    private final PsofService psofService;

    @Autowired
    public PsofProcessor(PsofService psofService) {
        this.psofService = psofService;
    }

    @Override
    public String getProcessorName() {
        return "PSOF processor";
    }

    @Override
    public boolean supports(CobData cobData, String newCIF) {
        return true;
    }

    @Override
    public void process(CobData cobData, String newCIF) {
        Psof constructed = constructPsofFromCobData(cobData, newCIF);
        psofService.savePsof(constructed);
    }

    private Psof constructPsofFromCobData(CobData cobData, String newCIF) {
        Psof constructed = new Psof();
        constructed.setPsofId(new PsofId("003", newCIF));
        constructed.setCustomerType(cobData.getCustTierType().substring(0, 2));
        constructed.setSourceFunds(cobData.getCustSourceFunds());
        constructed.setCreateDate(LocalDate.now());
        constructed.setMakerId("2199690");
        constructed.setCheckerId("2199691");
        constructed.setBranchNo(cobData.getTransactionBranch());
        return constructed;
    }
}
