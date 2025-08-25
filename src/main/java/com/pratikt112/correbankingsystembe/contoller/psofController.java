package com.pratikt112.correbankingsystembe.contoller;


import com.pratikt112.correbankingsystembe.model.psof.Psof;
import com.pratikt112.correbankingsystembe.service.PsofService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class psofController {
    @Autowired
    private PsofService psofService;

    @PostMapping("/psof/new")
    public ResponseEntity<?> savePsof(@RequestBody Psof psof){
        try {
            Psof saved = psofService.savePsof(psof);
            return new ResponseEntity<Psof>(saved, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/psof/{socNo}/{custAcctNo}")
    public ResponseEntity<?> amendPsof(@PathVariable("socNo") String socNo, @PathVariable("custAcctNo") String custAcctNo, @RequestBody Psof psofNew){
        try{
            Psof amendedPsof = psofService.amendPsof(psofNew);
            return new ResponseEntity<Psof>(amendedPsof, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
