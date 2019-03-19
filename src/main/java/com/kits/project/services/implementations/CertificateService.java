package com.kits.project.services.implementations;

import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchProviderException;

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
	
	public String generateCert() {
		// TREBA PROVERITI STA STAVITI OVDE ZA JKS / SUN!
		try {
			KeyStore store = KeyStore.getInstance("JKS", "SUN");
		} catch (KeyStoreException | NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Error";
		}
		
		return "";
	}
}
