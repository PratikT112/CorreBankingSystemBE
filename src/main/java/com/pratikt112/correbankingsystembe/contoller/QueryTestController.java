package com.pratikt112.correbankingsystembe.contoller;


import com.pratikt112.correbankingsystembe.DTOs.projections.custIdDetailsPrimary;
import com.pratikt112.correbankingsystembe.repo.CuidRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@CrossOrigin
public class QueryTestController {
    @Autowired
    private CuidRepo cuidRepo;

    @GetMapping("/queryTest/{custNo}")
    public ResponseEntity<?> testQueryMethod(@PathVariable("custNo") String custNo){
        List<custIdDetailsPrimary> cuids = cuidRepo.testQuery(custNo);
        return new ResponseEntity<>(cuids, HttpStatus.OK);
    }
}
