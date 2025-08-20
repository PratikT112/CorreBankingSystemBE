package com.pratikt112.correbankingsystembe.contoller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class cusmController {
    @Autowired
    private CusmService cusmService;
}
