package com.kits.project.services.implementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kits.project.repositories.CertificateNodeRepository;

@Service
public class SoftwareService {
	@Autowired
	CertificateNodeRepository certificateRep;

	public List<String> getConnectedSoftwares(String alias){
		//ArrayList<CertificateNode> softwares = certificateRep.findAll
		
		return new ArrayList<String>();
	}

}
