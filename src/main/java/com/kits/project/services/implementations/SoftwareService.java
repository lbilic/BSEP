package com.kits.project.services.implementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kits.project.DTOs.SoftwareConnections;
import com.kits.project.model.CertificateNode;
import com.kits.project.repositories.CertificateNodeRepository;

@Service
public class SoftwareService {
	@Autowired
	CertificateNodeRepository certificateRep;

	public SoftwareConnections getConnectedSoftwares(String alias){
		if(alias == null) {
			return null;
		}
		List<CertificateNode> softwares = certificateRep.findAllByIsSoftware(true);
		CertificateNode requestedSoftware = certificateRep.findByAlias(alias);

		SoftwareConnections sc = new SoftwareConnections();
		sc.setConnectedWith(new ArrayList<String>());
		sc.setOthers(new ArrayList<String>());
		for (CertificateNode certificateNode : softwares) {
			if(certificateNode.getAlias().equals(alias)) {
				continue;
			}
			if(requestedSoftware.getConnectedSoftwares().contains(certificateNode)) {
				sc.getConnectedWith().add(certificateNode.getAlias());
			}else {
				sc.getOthers().add(certificateNode.getAlias());
			}
		}
		
		return sc;
	}
	
	public String changeSoftwareConnections(String alias, SoftwareConnections connections) {
		if(alias == null) {
			return "Data invalid";
		}
		
		CertificateNode requestedNode = certificateRep.findByAlias(alias);
		CertificateNode helpNode;		
		
		if(requestedNode == null) {
			return "Data invalid";
		}

		for (String cert_alias : connections.getConnectedWith()) {
			if((helpNode = certificateRep.findByAlias(cert_alias)) == null) {
				return "Data invalid";
			}
			if(!requestedNode.getConnectedSoftwares().contains(helpNode)) {
				requestedNode.getConnectedSoftwares().add(helpNode);
				helpNode.getConnectedSoftwares().add(requestedNode);
				certificateRep.save(helpNode);
				certificateRep.save(requestedNode);
			}
		}
		
		for (String cert_alias : connections.getOthers()) {
			if((helpNode = certificateRep.findByAlias(cert_alias)) == null) {
				return "Data invalid";
			}
			if(requestedNode.getConnectedSoftwares().contains(helpNode)) {
				requestedNode.getConnectedSoftwares().remove(helpNode);
				helpNode.getConnectedSoftwares().remove(requestedNode);
				certificateRep.save(helpNode);
				certificateRep.save(requestedNode);
			}
		}

		return "Success";
	}

}
