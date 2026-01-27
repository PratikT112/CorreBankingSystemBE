package com.pratikt112.correbankingsystembe.contoller;

import com.pratikt112.correbankingsystembe.model.usty.Usty;
import com.pratikt112.correbankingsystembe.service.UstyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UstyController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UstyController.class);
    private final UstyService ustyService;

    public UstyController(UstyService ustyService) {
        this.ustyService = ustyService;
    }

    @PostMapping("/usty/new")
    public ResponseEntity<Usty> createUserType(@RequestBody Usty usty){
        Usty savedUsty = ustyService.saveUsty(usty);
        return new ResponseEntity<>(savedUsty, HttpStatus.CREATED);
    }
}
