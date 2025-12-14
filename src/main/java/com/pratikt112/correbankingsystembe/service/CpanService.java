package com.pratikt112.correbankingsystembe.service;

import com.pratikt112.correbankingsystembe.exception.DuplicateRecordException;
import com.pratikt112.correbankingsystembe.model.cpan.Cpan;
import com.pratikt112.correbankingsystembe.repo.CpanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CpanService {

    private final CpanRepo cpanRepo;

    @Autowired
    public CpanService(CpanRepo cpanRepo) {
        this.cpanRepo = cpanRepo;
    }

    public Cpan saveCpan(Cpan newCpan){
        validateCpan(newCpan);
        return cpanRepo.save(newCpan);
    }

    private void validateCpan(Cpan newCpan) {
        String existingCustomer = cpanRepo.getExistingCustomerByCustPanNo(newCpan.getCustPanNo());
        if(existingCustomer != null){
            throw new DuplicateRecordException("DUPLICATE_RECORD_ERROR",
                    "PAN already exists for CIF: "+ existingCustomer.substring(6),
                    "PAN already exists for other CIF. Recheck and try again.");
        }
    }
}
