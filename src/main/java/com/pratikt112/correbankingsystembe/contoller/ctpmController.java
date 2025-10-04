package com.pratikt112.correbankingsystembe.contoller;

import com.pratikt112.correbankingsystembe.model.ctpm.Ctpm;
import com.pratikt112.correbankingsystembe.service.CtpmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class ctpmController {

    @Autowired
    private CtpmService ctpmService;

    @GetMapping("/ctpm/{tieredCust}")
    public ResponseEntity<String> getDocReqd(@PathVariable String tieredCust){
        String docReqd = ctpmService.fetchDocReqd(tieredCust);
        return new ResponseEntity<String>(docReqd, HttpStatus.OK);
    }

    @GetMapping("/ctpm/{tieredCust}")
    public ResponseEntity<Ctpm> getCtpm(@PathVariable String tieredCust){
        Ctpm ctpm = ctpmService.getCtpm(tieredCust);
        return new ResponseEntity<Ctpm>(ctpm, HttpStatus.OK);
    }

    @PostMapping("/ctpm/new")
    public ResponseEntity<Ctpm> newCtpm(@RequestBody Ctpm ctpm){
        Ctpm savedCtpm= ctpmService.saveCtpm(ctpm);
        return new ResponseEntity<>(savedCtpm, HttpStatus.CREATED);
    }



}
