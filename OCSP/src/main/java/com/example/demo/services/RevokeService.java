package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.CertificateStatus;
import com.example.demo.repository.CertificateStatusRepository;

@Service
public class RevokeService {

	@Autowired
	private CertificateStatusRepository certificateStatusRep;
	
	public String isCertificateValid(String serialNumber) {
		if(serialNumber == null) {
			return "Data invalid";
		}
		
		CertificateStatus certStatus = certificateStatusRep.findBySerilNumber(serialNumber);
		
		if(certStatus == null) {
			return "Doesn't exist";
		}
		
		if(certStatus.getIsRevoked()) {
			return "Certificate revoked";
		}else {
			return "Certificate not revoked";
		}		
	}
}
