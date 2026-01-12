package com.pratikt112.correbankingsystembe.contoller;


import com.pratikt112.correbankingsystembe.DTOs.projections.custIdDetailsPrimary;
import com.pratikt112.correbankingsystembe.model.cmob.Cmob;
import com.pratikt112.correbankingsystembe.model.cmob.CmobId;
import com.pratikt112.correbankingsystembe.repo.CmobRepo;
import com.pratikt112.correbankingsystembe.repo.CuidRepo;
import org.hibernate.validator.internal.util.actions.LoadClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@RestController
@CrossOrigin
public class QueryTestController {
    @Autowired
    private CuidRepo cuidRepo;

    @Autowired
    private CmobRepo cmobRepo;

    @GetMapping("/queryTest/{custNo}")
    public ResponseEntity<?> testQueryMethod(@PathVariable("custNo") String custNo){
        List<custIdDetailsPrimary> cuids = cuidRepo.testQuery(custNo);
        return new ResponseEntity<>(cuids, HttpStatus.OK);
    }

    @PatchMapping("/queryTest/verifyFromKafka/{socNo}/{custNo}/{mobileNumber}/{isdCode}")
    public ResponseEntity<?> testQueryMethod(@PathVariable("socNo") String socNo,
                                             @PathVariable("custNo") String custNo,
                                             @PathVariable("mobileNumber") String mobileNumber,
                                             @PathVariable("isdCode") String isdCode){
        LocalDate today = LocalDate.now();
        int rowsUpdated = cmobRepo.verifyMobileNumber(socNo, custNo, "P", mobileNumber, isdCode,  today);
        return new ResponseEntity<>(rowsUpdated, HttpStatus.OK);
    }

    @GetMapping("/queryTest/fetchCmob/{socNo}/{custNo}")
    public ResponseEntity<Cmob> testQueryMethod(@PathVariable("socNo") String socNo,
                                                @PathVariable("custNo") String custNo){
        Cmob fetched = cmobRepo.findById(new CmobId(socNo, custNo, "P")).orElse(null);
        return new ResponseEntity<>(fetched, HttpStatus.OK);
    }
}
