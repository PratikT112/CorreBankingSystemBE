package com.pratikt112.correbankingsystembe.processor;


import com.pratikt112.correbankingsystembe.DTOs.CobData;
import com.pratikt112.correbankingsystembe.model.cusvdd.Cusvdd;
import com.pratikt112.correbankingsystembe.service.CusvddService;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@Order(6)
public class CusvddProcessor implements CustomerProcessingRule{
    //Only constructor injection allowed for final fields
    private final CusvddService cusvddService;

    public CusvddProcessor(CusvddService cusvddService) {
        this.cusvddService = cusvddService;
    }

    @Override
    public String getProcessorName() {
        return "CUSVDD processor";
    }

    @Override
    public boolean supports(CobData cobData, String newCIF) {
        return true;
    }

    @Override
    public void process(CobData cobData, String newCIF) {
        Cusvdd constructed = constructCusvddFromCobData(cobData, newCIF);
        cusvddService.saveCusvdd(constructed);
    }

    static Cusvdd constructCusvddFromCobData(CobData cobData, String newCIF){
        Cusvdd constructed = new Cusvdd();
        constructed.setKey1("003" + newCIF);
        constructed.setId1(cobData.getCustOvdDetails().getOvdDocNumber());
        constructed.setBirthDate1(cobData.getCustDob());
        constructed.setDeathDate(null);
        constructed.setSexCode(cobData.getCustGender());
        constructed.setMaritalStatus(cobData.getCustMaritalStatus());
        constructed.setOccupDesc("DUMMY DESCRIPTION");
        constructed.setOccupationCode(cobData.getCustOccSubType());
        constructed.setResCountryCode(cobData.getCustMainAddressDetails().getCountry());
        constructed.setBirthPlace(cobData.getCustPOB());
        constructed.setPostcode(cobData.getCustMainAddressDetails().getPincode());
        constructed.setThreLim(cobData.getCustAnnIncome()
                .divide(BigDecimal.valueOf(12), 2, RoundingMode.HALF_UP));
        return constructed;
    }
}
