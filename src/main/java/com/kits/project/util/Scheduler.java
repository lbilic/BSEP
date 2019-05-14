package com.kits.project.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.kits.project.model.CertificateStatus;
import com.kits.project.repositories.CertificateStatusRepository;

@Component
public class Scheduler {

	@Autowired
	CertificateStatusRepository certStatusRep;
	
	@Scheduled(fixedRate = 500)
	public void moveVehiclesFirst() {
		String certPath = new File("src/main/resources/certs/a").getAbsolutePath();
		certPath = certPath.substring(0, certPath.length()-1);
		
		try {
			InputStream in = new FileInputStream(certPath+"mycert.cer");
			BouncyCastleProvider provider = new BouncyCastleProvider();
			CertificateFactory certificateFactory = CertificateFactory.getInstance("X509", provider);
			X509Certificate certificate = (X509Certificate) certificateFactory.generateCertificate(in);
			BigInteger serialNum = certificate.getSerialNumber();
			if(certStatusRep.findBySerialNumber(serialNum.toString()) == null) {
				CertificateStatus certStat = new CertificateStatus();
				certStat.setSerialNumber(serialNum.toString());
				certStat.setIsRevoked(false);
				certStatusRep.save(certStat);
			}
		}catch(Exception e) {
			
		}
	}
}
