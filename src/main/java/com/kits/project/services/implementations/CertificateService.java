package com.kits.project.services.implementations;

import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kits.project.model.CertificateNode;
import com.kits.project.repositories.CertificateNodeRepository;

@Service
public class CertificateService {
	@Autowired
	CertificateNodeRepository certificateRep;
	
	public String generateCert(String issued_by,CertificateNode certNode) {
		if(certNode.getIsSoftware() == null) {
			return "Data not valid1";
		}else if(certNode.getAlias() == null) {
			return "Data not valid2";
		}else if(certNode.getLocality() == null) {
			return "Data not valid3";
		}else if(certNode.getStateName() == null) {
			return "Data not valid4";
		}else if(certNode.getDateIssued() == null) {
			return "Data not valid5";
		}else if(certNode.getEndDate() == null) {
			return "Data not valid6";
		}else if(certNode.getCountryName() == null) {
			return "Data not vali7";
		}
		
		CertificateNode parentNode = certificateRep.findByAlias(issued_by);
		
		if(parentNode == null){
			return "Invalid issuer";
		}
		
		if(parentNode.getIsSoftware()) {
			return "Invalid request. Only CA can issue certificate";
		}
		
		certificateRep.save(certNode);		
		parentNode.getChildren().add(certNode);
		certificateRep.save(parentNode);
		
		// IZGENERISI SERTIFIKAT
		// Imas issued_by ( alias roditelja ) i certNode ( podatke za cert )
		
		// Upisivanje u bazu za OCSP sa serijskim brojem i flag-om da li je povucen

		return "Success";
	}
	
	public String revokeCert(String alias) {
		if(alias == null) {
			return "Data invalid";
		}
		CertificateNode certNode;
		
		certNode = certificateRep.findByAlias(alias);
		
		if(certNode == null) {
			return "Data invalid";
		}
		
		certificateRep.delete(certNode);
		
		return "Success";
	}
	
	public CertificateNode getAllData() {
		return certificateRep.findByAlias("ROOT");
	}
	
	public CertificateNode getCertificate(String alias) {
		if(alias == null) {
			return null;
		}
		CertificateNode cert = certificateRep.findByAlias(alias);
		
		if(cert == null) {
			return null;
		}
		
		cert.setConnectedSoftwares(null);
		cert.setChildren(null);		
		
		return cert;
	}
	
	
	
}
