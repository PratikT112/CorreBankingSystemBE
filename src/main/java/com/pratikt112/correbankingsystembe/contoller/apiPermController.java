package com.pratikt112.correbankingsystembe.contoller;

import com.pratikt112.correbankingsystembe.model.apiPerm.ApiPerm;
import com.pratikt112.correbankingsystembe.model.usty.Usty;
import com.pratikt112.correbankingsystembe.service.ApiPermService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class apiPermController {


    private final ApiPermService apiPermService;

    public apiPermController(ApiPermService apiPermService) {
        this.apiPermService = apiPermService;
    }

    @PostMapping("/apiPerm/new")
    public ResponseEntity<ApiPerm> createUserType(@RequestBody ApiPerm apiPerm){
        ApiPerm savedApiPerm = apiPermService.saveApiPerm(apiPerm);
        return new ResponseEntity<>(savedApiPerm, HttpStatus.CREATED);
    }
}
