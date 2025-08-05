package com.pratikt112.correbankingsystembe.contoller;


import com.pratikt112.correbankingsystembe.model.cmob.Cmob;
import com.pratikt112.correbankingsystembe.model.cmob.CmobId;
import com.pratikt112.correbankingsystembe.service.CmobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class cmobController {

    @Autowired
    private CmobService cmobService;

    @GetMapping("/cmob/{socNo}/{custNo}")
    public ResponseEntity<List<Cmob>> searchCmobByCustNo(@PathVariable("socNo") String socNo, @PathVariable("custNo") String custNo){
        List<Cmob> mobileNos = cmobService.searchCmobByCustNo(socNo, custNo);
        return new ResponseEntity<>(mobileNos, HttpStatus.OK);
    }

    @PostMapping("/cmob/new")
    public ResponseEntity<?> saveCmob(@RequestBody Cmob cmob){
        try {
            Cmob saved = cmobService.saveCmob(cmob);
            return new ResponseEntity<Cmob>(saved, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/cmob/{socNo}/{custNo}/{identifier}")
    public ResponseEntity<?> amendCmob(@PathVariable("socNo") String socNo, @PathVariable("custNo") String custNo, @PathVariable("identifier") String identifier){
//        boolean exists = cmobService.amendCmob(socNo, custNo, identifier);
            return null;
    }
}
