package com.kits.project.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kits.project.repositories.CityRepository;
import com.kits.project.repositories.OfficeRepository;
import com.kits.project.repositories.SoftwareRepository;

@Service
public class CertificateService {
	
	@Autowired
	CityRepository cityRep;
	
	@Autowired
	OfficeRepository officeRep;

	@Autowired
	SoftwareRepository softwareRep;
}
