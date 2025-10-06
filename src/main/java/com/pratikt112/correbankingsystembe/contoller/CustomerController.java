package com.pratikt112.correbankingsystembe.contoller;

import com.pratikt112.correbankingsystembe.DTOs.CobData;
import com.pratikt112.correbankingsystembe.service.CustomerOrchestratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
    private final CustomerOrchestratorService customerOrchestratorService;

    @Autowired
    public CustomerController(CustomerOrchestratorService customerOrchestratorService) {
        this.customerOrchestratorService = customerOrchestratorService;
    }

    @PostMapping("/CIF/new")
    public ResponseEntity<String> createNewCustomer(@RequestBody CobData cobData){
        try{
            customerOrchestratorService.createCustomer(cobData);
            return new ResponseEntity<>("Customer created Successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
