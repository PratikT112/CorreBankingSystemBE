package com.pratikt112.correbankingsystembe.contoller;

import com.pratikt112.correbankingsystembe.service.CtpmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class ctpmController {

    @Autowired
    private CtpmService ctpmService;

    @GetMapping("/ctpm/{tieredCust}")
    public ResponseEntity<String> getCtpm(@PathVariable String tieredCust){
        String docReqd = ctpmService.fetchDocReqd(tieredCust);
        return new ResponseEntity<String>(docReqd, HttpStatus.OK);
    }

}
