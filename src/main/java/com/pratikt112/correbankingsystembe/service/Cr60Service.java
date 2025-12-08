package com.pratikt112.correbankingsystembe.service;

import com.pratikt112.correbankingsystembe.config.SystemDateProvider;
import com.pratikt112.correbankingsystembe.exception.DuplicateRecordException;
import com.pratikt112.correbankingsystembe.exception.ValidationException;
import com.pratikt112.correbankingsystembe.model.cr60.Cr60;
import com.pratikt112.correbankingsystembe.repo.Cr60Repo;
import com.sun.jdi.request.DuplicateRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class Cr60Service {
    @Autowired
    Cr60Repo cr60Repo;

    @Autowired
    SystemDateProvider systemDateProvider;


    @Transactional
    public Cr60 saveCr60(Cr60 cr60) {
        try {
            return saveCr60Entry(cr60);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (DataIntegrityViolationException e){
            throw new RuntimeException("Database constraint violated while saving CR60: " + e.getMostSpecificCause().getMessage(), e);
        } catch (Exception e){
            throw new RuntimeException("Unexpected error while saving CR60: ", e);
        }
    }

    public Optional<Cr60> findCr60ByCustNo(String socNo, String custNo) {
        try{
            return cr60Repo.findById(socNo+custNo);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public Cr60 saveCr60Entry(Cr60 cr60){
        validateCr60Entry(cr60);
        return cr60Repo.save(cr60);
    }


    public void validateCr60Entry(Cr60 cr60){
        if(cr60Repo.existsById(cr60.getKey1())){
//            throw new IllegalArgumentException("Customer Record already exists in CR60");
            throw new DuplicateRecordException("CR60", cr60.getKey1());
        }

        if(cr60.getCr60SubmitDate().isAfter(SystemDateProvider.getSystemDate())){
//            throw new IllegalArgumentException("Form60 submit date cannot be future date");
            throw new ValidationException("CR60_SBMT_DT", "Form60 submit date cannot be future date");
        }

        if(cr60.getTxnDate().isAfter(SystemDateProvider.getSystemDate())){
//            throw new IllegalArgumentException("Form60 transaction date cannot be future date");
            throw new ValidationException("CR60_TXN_DT", "Form60 transaction date cannot be future date");
        }

        if(!List.of("Y","N").contains(cr60.getPanApply())){
//            throw new IllegalArgumentException("Invalid PAN applied flag");
            throw new ValidationException("CR60_PAN_APPLY", "Invalid PAN applied flag");
        }

        if(cr60.getPanApply().equals("Y") && cr60.getPanApplyDate().isAfter(SystemDateProvider.getSystemDate())){
//            throw new IllegalArgumentException("PAN applied date cannot be future");
            throw new ValidationException("CR60_PAN_APl_DT", "PAN applied date cannot be future");
        }

        if(cr60.getPanApply().equals("Y") && cr60.getPanAckNo().isEmpty()){
//            throw new IllegalArgumentException("Pan Acknowledgement Number is required when PAN applied flag is Y");
            throw new ValidationException("CR60_PAN_ACK_NO", "Pan Acknowledgement Number is required when PAN applied flag is Y");
        }
    }
}
