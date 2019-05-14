package com.kits.project.services.implementations;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.nio.file.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kits.project.model.CertificateNode;
import com.kits.project.model.CertificateStatus;
import com.kits.project.repositories.CertificateNodeRepository;
import com.kits.project.repositories.CertificateStatusRepository;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

@Service
public class CertificateService {
	@Autowired
	CertificateNodeRepository certificateRep;
	
	@Autowired
	CertificateStatusRepository certificateStatusRep;
	
	public String generateCert(String issued_by,CertificateNode certNode) {
		String regex = "^[a-zA-Z ]+$";
		String aliasRegex = "^[a-zA-Z0-9 ]+$";
		Pattern pattern = Pattern.compile(regex);
		Pattern aliasPattern = Pattern.compile(aliasRegex);
		
		if(certNode.getIsSoftware() == null) {
			return "Data not valid1";
		}else if(certNode.getAlias() == null || !aliasPattern.matcher(certNode.getAlias()).matches()) {
			return "Data not valid2";
		}else if(certNode.getLocality() == null || !pattern.matcher(certNode.getLocality()).matches()) {
			return "Data not valid3";
		}else if(certNode.getStateName() == null || !pattern.matcher(certNode.getStateName()).matches()) {
			return "Data not valid4";
		}else if(certNode.getDateIssued() == null) {
			return "Data not valid5";
		}else if(certNode.getEndDate() == null) {
			return "Data not valid6";
		}else if(certNode.getCountryName() == null || !pattern.matcher(certNode.getCountryName()).matches()) {
			return "Data not vali7";
		}
		
		CertificateNode parentNode = certificateRep.findByAlias(issued_by);
		
		if(parentNode == null){
			return "Invalid issuer";
		}
		
		if(parentNode.getIsSoftware()) {
			return "Invalid request. Only CA can issue certificate";
		}
		
		
		
		// IZGENERISI SERTIFIKAT
		// Imas issued_by ( alias roditelja ) i certNode ( podatke za cert )
		if(certNode.getIsSoftware()) {
			// Generisi obican sertifikat
			try{
				long validity = (certNode.getEndDate().getTime() - certNode.getDateIssued().getTime())/1000;

				String certPath = new File("src/main/resources/certs/a").getAbsolutePath();
				certPath = certPath.substring(0, certPath.length()-1);

				Process p0 = Runtime.getRuntime().exec(String.format("cmd /c start cmd.exe /K \"cd \"%s\" && keytool -genkey -alias \"%s\" -keyalg RSA -keystore \"%s.jks\" -storetype JKS -dname \"CN=%s.megatravel.com,OU=%s,O=MegaTravel,L=%s,ST=%s,C=%s\" -keypass password -storepass password &&" +
								"keytool -certreq -alias \"%s\" -keystore \"%s.jks\" -file \"%s.csr\" -storepass password && " +
								"openssl x509 -CA ..\\CA\\caroot.cer -CAkey ..\\CA\\cakey.pem -CAserial ..\\CA\\serial.txt -req -in \"%s.csr\" -out \"%sCA.cer\" -days %d -passin pass:password && " +
								"copy \"%s.jks\" \"%s_work.jks\" /y &&" +
								"exit\"",
						certPath, certNode.getAlias(), certNode.getAlias(),certNode.getAlias(),certNode.getAlias(), certNode.getLocality(), certNode.getStateName(), certNode.getCountryName(),
						certNode.getAlias(), certNode.getAlias(), certNode.getAlias(),
						certNode.getAlias(), certNode.getAlias(), validity/3600/24,
						certNode.getAlias(), certNode.getAlias()));
			}catch(Exception ex){
				ex.printStackTrace();
			}
		} else {
			// Generisi CA
			try {
				String caPath = new File("src/main/resources/CA/a").getAbsolutePath();
				caPath = caPath.substring(0, caPath.length() - 1);
				Process p0 = Runtime.getRuntime().exec(String.format("cmd /c start cmd.exe /K \"cd \"%s\" && set RANDFILE=rand && " +
								"openssl req -new -keyout \"%scakey.pem\" -out \"%scareq.pem\" -config \"C:\\Program Files\\OpenSSL-Win64\\bin\\openssl.cfg\" -subj \"/C=%s/ST=%s/L=%s/O=MegaTravel/OU=%s/CN=%s.megatravel.com\" -passout pass:\"password\" && " +
								"openssl x509 -signkey \"%scakey.pem\" -req -days 3650 -in \"%scareq.pem\" -out \"%sca.cer\" -extensions v3_ca -passin pass:password && exit\"",
						caPath,
						certNode.getAlias(), certNode.getAlias(), certNode.getCountryName(), certNode.getStateName(), certNode.getLocality(), certNode.getAlias(), certNode.getAlias(),
						certNode.getAlias(), certNode.getAlias(), certNode.getAlias()));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		// Upisivanje u bazu za OCSP sa serijskim brojem i flag-om da li je povucen
		// dodati serial number i u certNode 
		try {
			certNode.setSerialNumber(Files.readAllLines(Paths.get("src/main/resources/CA/serial.txt")).get(0));
		} catch (IOException e) {
			e.printStackTrace();
		}
		CertificateStatus status = new CertificateStatus();
		status.setIsRevoked(false);
		status.setSerialNumber(certNode.getSerialNumber());
		certificateStatusRep.save(status);
		certificateRep.save(certNode);		
		parentNode.getChildren().add(certNode);
		certificateRep.save(parentNode);
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
		
		ArrayList<String> serialNumList = new ArrayList<String>();
		this.getChildrenSerialNumbers(certNode, serialNumList);
		
		
		for (String serialNumber : serialNumList) {
			CertificateStatus status = certificateStatusRep.findBySerialNumber(serialNumber);
			status.setIsRevoked(true);
			certificateStatusRep.save(status);
		}
		certificateRep.delete(certNode);
		
		return "Success";
	}
	
	public void getChildrenSerialNumbers(CertificateNode node, ArrayList<String> serialNumList) {
		if(node.getIsSoftware()) {
			return;
		}
		if(node.getChildren() == null) {
			return;
		}
		for (CertificateNode child : node.getChildren()) {
			serialNumList.add(child.getSerialNumber());
			this.getChildrenSerialNumbers(child, serialNumList);
		}
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


	public void updateTrustStoreEach(String alias) {
		try{
			String certPath = new File("src/main/resources/certs/a").getAbsolutePath();
			certPath = certPath.substring(0, certPath.length()-1);
			CertificateNode cert = certificateRep.findByAlias(alias);

			Path original = FileSystems.getDefault().getPath(certPath + "\\" + alias + "_work.jks");
			Path target = FileSystems.getDefault().getPath(certPath + "\\" + alias + ".jks");
			Files.copy(original, target, StandardCopyOption.REPLACE_EXISTING);

			for(CertificateNode c : cert.getConnectedSoftwares()) {
				// Dodaj svaki u truststore
				Process p1 = Runtime.getRuntime().exec(String.format("cmd /c start cmd.exe /K \"cd \"%s\" && " +
								"keytool -importcert -alias \"%s\" -keystore \"%s.jks\" -file \"%sCA.cer\" -storepass password -noprompt && exit\"",
						certPath, c.getAlias(), alias, c.getAlias()));
				p1.waitFor();
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	public void updateTrustStore(ArrayList<String> updatedSoftwares) {
		for(String alias : updatedSoftwares)
			this.updateTrustStoreEach(alias);
	}
}
