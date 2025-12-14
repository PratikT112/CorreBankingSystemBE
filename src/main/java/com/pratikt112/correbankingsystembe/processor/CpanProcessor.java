package com.pratikt112.correbankingsystembe.processor;

import com.pratikt112.correbankingsystembe.DTOs.CobData;
import com.pratikt112.correbankingsystembe.model.cpan.Cpan;
import com.pratikt112.correbankingsystembe.model.cpan.CpanId;
import com.pratikt112.correbankingsystembe.service.CpanService;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service
@Order(9)
public class CpanProcessor implements CustomerProcessingRule{

    private final CpanService cpanService;

    public CpanProcessor(CpanService cpanService) {
        this.cpanService = cpanService;
    }

    @Override
    public String getProcessorName() {
        return "CPAN processor";
    }

    @Override
    public boolean supports(CobData cobData, String newCIF) {
        return cobData.getCustPanNo() != null;
    }

    @Override
    public void process(CobData cobData, String newCIF) {
        Cpan constructed = constructCpanFromCobData(cobData, newCIF);
        cpanService.saveCpan(constructed);
    }

    private Cpan constructCpanFromCobData(CobData cobData, String newCIF) {
        Cpan constructed = new Cpan();
        constructed.setId(new CpanId("003", newCIF));
        constructed.setCustPanNo(cobData.getCustPanNo());
        return constructed;
    }
}
