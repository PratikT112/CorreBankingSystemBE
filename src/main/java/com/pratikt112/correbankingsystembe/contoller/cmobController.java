package com.pratikt112.correbankingsystembe.contoller;


import com.pratikt112.correbankingsystembe.model.cmob.Cmob;
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

//    @GetMapping("/cmob/{custNo}")
//    public ResponseEntity<List<Cmob>> searchCmobByCustNo(@PathVariable String custNo){
//        List<Cmob> mobileNos = CmobService.searchCmobByCustNo(custNo);
//        return new ResponseEntity<>(mobileNos, HttpStatus.OK);
//    }

    @PostMapping("/cmob/new")
    public ResponseEntity<Cmob> saveCmob(@RequestBody Cmob cmob){
        try {
            Cmob saved = cmobService.saveCmob(cmob);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }


    }
}
