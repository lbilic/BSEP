package com.kits.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kits.project.DTOs.SoftwareConnections;
import com.kits.project.services.implementations.SoftwareService;

@RestController
@CrossOrigin(value = "http://localhost:4200")
@RequestMapping("api/software")
public class SoftwareController {

    @Autowired
    private SoftwareService softwareService;

    @RequestMapping(
    		value = "/{alias}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity getConnections(@PathVariable(value="alias") String alias) {
        return new ResponseEntity(softwareService.getConnectedSoftwares(alias), HttpStatus.OK);
    }

    @RequestMapping(
    		value = "/{alias}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity changeConnections(@PathVariable(value="alias") String alias, @RequestBody SoftwareConnections connections) {
        return new ResponseEntity(softwareService.changeSoftwareConnections(alias, connections), HttpStatus.OK);
    }
    
}
