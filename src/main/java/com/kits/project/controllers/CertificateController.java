package com.kits.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kits.project.DTOs.CertificateDTO;
import com.kits.project.services.implementations.CertificateService;

@RestController
@CrossOrigin(value = "http://localhost:4200")
@RequestMapping("api/cert")
public class CertificateController {

	@Autowired
	CertificateService certificateService;
	
    @RequestMapping(
    		value = "/{node_id}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity generateCertificate(@PathVariable String node_id) {
    	return new ResponseEntity<String>(certificateService.generateCert(node_id),HttpStatus.OK);
    }

	@RequestMapping(
			value = "/{node_id}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity revoke(@PathVariable String node_id) {
		return new ResponseEntity<String>(certificateService.revokeCert(node_id),HttpStatus.OK);
	}

    @RequestMapping(
    		value = "/{node_id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getCertificate(@PathVariable String node_id) {
    	return new ResponseEntity<CertificateDTO>(certificateService.getCertificate(node_id),HttpStatus.OK);
    }

    @RequestMapping(
			value = "/all-data",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<List> getAllData() {
		return new ResponseEntity(certificateService.getAllData(), HttpStatus.OK);
	}

}
