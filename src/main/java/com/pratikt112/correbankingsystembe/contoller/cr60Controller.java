package com.pratikt112.correbankingsystembe.contoller;


import com.pratikt112.correbankingsystembe.model.cr60.Cr60;
import com.pratikt112.correbankingsystembe.service.Cr60Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

@RestController
public class cr60Controller {

    @Autowired
    private Cr60Service cr60Service;

    @PostMapping("/cr60/new")
    public ResponseEntity<?> saveCr60(@RequestBody Cr60 cr60){
        try{
            Cr60 saved = cr60Service.saveCr60(cr60);
            return new ResponseEntity<Cr60>(saved, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/cr60/{socNo}/{custNo}")
    public ResponseEntity<?> getCr60ByCustNo(@PathVariable("socNo") String socNo, @PathVariable("custNo") String custNo){
        try{
            Cr60 fetchedCr60 = cr60Service.findCr60ByCustNo(socNo, custNo);
        }
    }
}
