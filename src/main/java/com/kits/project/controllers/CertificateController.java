package com.kits.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kits.project.services.implementations.CertificateService;

@RestController
@CrossOrigin(value = "http://localhost:4200")
@RequestMapping("api/cert")
public class CertificateController {

	@Autowired
	CertificateService certificateService;
	
	
	
}
