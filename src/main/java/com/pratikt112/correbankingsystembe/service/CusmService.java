package com.pratikt112.correbankingsystembe.service;

import com.pratikt112.correbankingsystembe.model.cusm.Cusm;
import com.pratikt112.correbankingsystembe.repo.CusmRepo;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CusmService {
    @Autowired
    private CusmRepo cusmRepo;

    public Cusm saveCusm(Cusm cusm) {
        if(!List.of("01", "02").contains(cusm.getCustomerType())){
            throw new IllegalArgumentException("Customer Type can either be 01 or 02");
        }
        if(!List.of("000", "999").contains(cusm.getCustomerStatus())){
            throw new IllegalArgumentException("Customer Status can only be initiated as 000(Active) or 999(Inactive)");
        }
        if(!cusm.getCreateDt().equals(LocalDate.now())){
            throw new IllegalArgumentException("Backdated customer creation not allowed.");
        }
        if(hasCrossBorderRiskWithoutRiskCountry(cusm)){
            throw new IllegalArgumentException("Country of risk needed if cross border risk is selected.");
        }

        return null;
    }

    // Helper Methods
    private boolean hasCrossBorderRiskWithoutRiskCountry(Cusm cusm) {
        return cusm.getCrossBorderRisk() != null && cusm.getCountryOfRisk() == null;
    }
}
