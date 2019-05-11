package com.kits.project.controllers;

import java.util.List;

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

import com.kits.project.model.CertificateNode;
import com.kits.project.services.implementations.CertificateService;

@RestController
@CrossOrigin(value = "http://localhost:4200")
@RequestMapping("api/cert")
public class CertificateController {

	@Autowired
	CertificateService certificateService;
	
    @RequestMapping(
    		value = "/{alias}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity generateCertificate(@PathVariable String alias, @RequestBody CertificateNode certNode) {
    	return new ResponseEntity<String>(certificateService.generateCert(alias,certNode),HttpStatus.OK);
    }

	@RequestMapping(
			value = "/{alias}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity revoke(@PathVariable String alias) {
		//return new ResponseEntity<String>(certificateService.revokeCert(node_id),HttpStatus.OK);
		return null;
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
