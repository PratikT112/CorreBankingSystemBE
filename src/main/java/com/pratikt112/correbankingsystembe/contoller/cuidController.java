package com.pratikt112.correbankingsystembe.contoller;


import com.pratikt112.correbankingsystembe.model.cuid.Cuid;
import com.pratikt112.correbankingsystembe.service.CuidService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class cuidController {
    @Autowired
    private CuidService cuidService;

    @GetMapping("/cuid/{socNo}/{custNo}")
    public ResponseEntity<List<Cuid>> getCuidByCustNo(@PathVariable("socNo") String socNo, @PathVariable("custNo") String custNo){
        List<Cuid> idList = cuidService.getCuidByCustNo(socNo, custNo);
        return new ResponseEntity<>(idList, HttpStatus.OK);
    }

    @PostMapping("/cuid/new")
    public ResponseEntity<?> saveCuid(@Valid @RequestBody Cuid newCuid){
        try{
            Cuid savedCuid = cuidService.saveCuid(newCuid);
            return new ResponseEntity<>(savedCuid, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<String>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
