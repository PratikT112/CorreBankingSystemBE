package com.pratikt112.correbankingsystembe.contoller;


import com.pratikt112.correbankingsystembe.model.cmob.Cmob;
import com.pratikt112.correbankingsystembe.model.cmob.CmobId;
import com.pratikt112.correbankingsystembe.model.mobh.Mobh;
import com.pratikt112.correbankingsystembe.model.mobh.MobhId;
import com.pratikt112.correbankingsystembe.repo.MobhRepo;
import com.pratikt112.correbankingsystembe.service.CmobService;
import com.pratikt112.correbankingsystembe.utility.DateUtilityDDMMYYYY;
import com.pratikt112.correbankingsystembe.utility.TimeUtilityHHMMSSmmm;
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

    @Autowired
    private MobhRepo mobhRepo;

    @Autowired
    private DateUtilityDDMMYYYY dateUtil;

    @Autowired
    private TimeUtilityHHMMSSmmm timeUtil;

    @GetMapping("/cmob/{socNo}/{custNo}")
    public ResponseEntity<List<Cmob>> searchCmobByCustNo(@PathVariable("socNo") String socNo, @PathVariable("custNo") String custNo){
        List<Cmob> mobileNos = cmobService.searchCmobByCustNo(socNo, custNo);
        return new ResponseEntity<>(mobileNos, HttpStatus.OK);
    }

    @GetMapping("/ocom/{socNo}/{custNo}")
    public ResponseEntity<List<String>> ocomByCustNo(@PathVariable("socNo") String socNo, @PathVariable("custNo") String custNo){
        List<String> ocomMobile = cmobService.OcomFromCmob(socNo, custNo);
        return new ResponseEntity<>(ocomMobile, HttpStatus.OK);
    }



    @PostMapping("/cmob/new")
    public ResponseEntity<?> saveCmob(@RequestBody List<Cmob> cmobList){
        try {
            List<Cmob> saved = cmobService.saveCmob(cmobList);
            return new ResponseEntity<List<Cmob>>(saved, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

//    @PutMapping("/cmob/{socNo}/{custNo}/{isdCode}/{custMobNo}/{verifyFlag}")
//    public ResponseEntity<?> verifyMobile(@PathVariable("socNo") String socNo, @PathVariable("custNo") String custNo,@PathVariable("isdCode") String isdCode, @PathVariable("custMobNo") String custMobNo, @PathVariable("verifyFlag") String verifyFlag){
//        List<String> updatedMobileFlag = cmobService.verifyMobile(socNo, custNo, isdCode, custMobNo, verifyFlag);
//        return new ResponseEntity<List<String>>(updatedMobileFlag, HttpStatus.OK);
//    }

    @GetMapping("/cmob/{socNo}/{custNo}/{isdCode}/{custMobNo}")
    public ResponseEntity<?> findForVerification(@PathVariable("socNo") String socNo, @PathVariable("custNo") String custNo,@PathVariable("isdCode") String isdCode, @PathVariable("custMobNo") String custMobNo){
        List<Cmob> fetched = null;
        try{
            fetched = cmobService.findForVerification(socNo, custNo, isdCode, custMobNo);
            return new ResponseEntity<List<Cmob>>(fetched, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
        }
    }


    @PutMapping("/cmob/{socNo}/{custNo}/{identifier}")
    public ResponseEntity<?> amendCmob(@PathVariable("socNo") String socNo, @PathVariable("custNo") String custNo, @PathVariable("identifier") String identifier){
//        boolean exists = cmobService.amendCmob(socNo, custNo, identifier);
            return null;
    }
}
