package com.example.demo.controller;

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

import com.example.demo.services.RevokeService;

@RestController
@CrossOrigin(value = "http://localhost:4200")
@RequestMapping("api/status")
public class RevokeController {

	@Autowired
	private RevokeService revokeService;
	
    @RequestMapping(
			value = "/{serialNumber}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<String> getIsRevoked(@PathVariable("serialNumber") String serialNumber) {
		return new ResponseEntity<String>(revokeService.isCertificateValid(serialNumber), HttpStatus.OK);
	}

}
