package com.pratikt112.correbankingsystembe.service;

import com.pratikt112.correbankingsystembe.model.cusm.Cusm;
import com.pratikt112.correbankingsystembe.repo.CusmRepo;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CusmService {
    @Autowired
    private CusmRepo cusmRepo;

    public Cusm saveCusm(Cusm cusm) {
        try {
            return saveCusmEntry(cusm);
        } catch (IllegalArgumentException e){
            throw e;
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Database constraint violated while saving CR60: " + e.getMostSpecificCause().getMessage());
        } catch (Exception e){
            throw new RuntimeException("Unexpected error while saving CUSM: ", e);
        }
//        return null;
    }

    // Helper Methods


    public Cusm saveCusmEntry(Cusm cusm){
        validateCusmEntry(cusm);
        return cusmRepo.save(cusm);
    }


    public void validateCusmEntry(Cusm cusm){
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
        if(!List.of("D", "G", "P", "S", "B").contains(cusm.getSegmentCode())){
            throw new IllegalArgumentException("Invalid customer segment");
        }
        if(cusm.getTierCustType().isEmpty()){        // Implement ctpm live fetch
            throw new IllegalArgumentException("Invalid customer tier type");
        }
    }

    private boolean hasCrossBorderRiskWithoutRiskCountry(Cusm cusm) {
        return cusm.getCrossBorderRisk() != null && cusm.getCountryOfRisk() == null;
    }
}
