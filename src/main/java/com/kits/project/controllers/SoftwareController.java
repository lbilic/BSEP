package com.kits.project.controllers;

import com.kits.project.services.implementations.SoftwareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "http://localhost:4200")
@RequestMapping("api/software")
public class SoftwareController {

    @Autowired
    private SoftwareService softwareService;

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity login(@RequestParam(value="alias") String alias) {
        List<String> connectedSoftware = softwareService.getConnectedSoftwares(alias);

        return new ResponseEntity(connectedSoftware, HttpStatus.OK);
    }
}
