package com.pratikt112.correbankingsystembe.contoller;

import com.pratikt112.correbankingsystembe.model.telm.Telm;
import com.pratikt112.correbankingsystembe.service.TelmService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TelmController {

    private final TelmService telmService;

    public TelmController(TelmService telmService){
        this.telmService = telmService;
    }

    @PostMapping("/telm/new")
    public ResponseEntity<?> saveTelm(@RequestBody Telm telm){
        try {
            Telm saved = telmService.saveTelm(telm);
            return new ResponseEntity<Telm>(saved, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
