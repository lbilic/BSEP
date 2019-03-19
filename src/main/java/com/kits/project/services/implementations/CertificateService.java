package com.kits.project.services.implementations;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kits.project.DTOs.CertificateDTO;
import com.kits.project.model.City;
import com.kits.project.model.Office;
import com.kits.project.model.Software;
import com.kits.project.repositories.CityRepository;
import com.kits.project.repositories.OfficeRepository;
import com.kits.project.repositories.SoftwareRepository;
import com.kits.project.util.CertificateGenerator;
import com.kits.project.util.IssuerData;
import com.kits.project.util.KeyStoreHandler;
import com.kits.project.util.SubjectData;

@Service
public class CertificateService {
	
	@Autowired
	CityRepository cityRep;
	
	@Autowired
	OfficeRepository officeRep;

	@Autowired
	SoftwareRepository softwareRep;

	public String generateCert(String nameId) {
		City city = cityRep.findByNameId(nameId);
		Office office = officeRep.findByNameId(nameId);
		Software software = softwareRep.findByNameId(nameId);
		
		String parentAlias = "";
		if(city != null) {
			parentAlias = "MyTravelROOT";
			if(city.getHasCert()) {
				return "Error! Already has certificate";
			}
		}else if(office != null) {
			parentAlias = office.getCity().getNameId();
			if(office.getHasCert()) {
				return "Error! Already has certificate";
			}
			if(!office.getCity().getHasCert()) {
				return "Error ! Issuer has no certificate";
			}
		}else if(software != null) {
			parentAlias = software.getOffice().getNameId();
			if(software.getHasCert()) {
				return "Error! Already has certificate";
			}
			if(!software.getOffice().getHasCert()) {
				return "Error ! Issuer has no certificate";
			}
		}else {
			return "No given ID in database";
		}

		// TREBA PROVERITI STA STAVITI OVDE ZA JKS / SUN!
		KeyStore store;
		try {
			store = KeyStore.getInstance("JKS", "SUN");
		} catch (KeyStoreException | NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Error";
		}
		
		try {
			store.load(new FileInputStream("my_cert_data"), "random_password".toCharArray());
		} catch (NoSuchAlgorithmException | CertificateException | IOException e) {
			return "System error, no certificate file.";
		}
		
		
		KeyPair keyPair;
        try {
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA"); 
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
			keyGen.initialize(2048, random);
			keyPair = keyGen.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			keyPair = null;
			return "Keys generated badly";
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
			keyPair = null;
			return "Keys generated badly";
		}
        
        PrivateKey pk = null;
        Certificate found_cert = null;
		try {
			if(store.isKeyEntry(parentAlias)) {
				pk = (PrivateKey) store.getKey(parentAlias, "random_password".toCharArray());
				found_cert = store.getCertificate(parentAlias);
			}
		} catch (UnrecoverableKeyException | KeyStoreException | NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "No certificate for issuer";
		}
		
		if(pk == null) {
			return "No certificate for issuer";
		}


        IssuerData issuer = new IssuerData(pk,parentAlias);
        
		SimpleDateFormat iso8601Formater = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = new Date();
		Date endDate = new Date();
		try {
			startDate = iso8601Formater.parse("2017-12-31");
			endDate = iso8601Formater.parse("2022-12-31");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		SubjectData subject = new SubjectData(keyPair.getPublic(),nameId,startDate,endDate);
        
        CertificateGenerator cg = new CertificateGenerator();
        X509Certificate certificate = cg.generateCertificate(issuer, subject);

        try {
			certificate.verify(found_cert.getPublicKey());
			System.out.println("Radi budala");
		} catch (Exception e) {
			System.out.println("Ne radi");
		}

		try {
			store.setKeyEntry(nameId, keyPair.getPrivate(), "random_password".toCharArray(), new Certificate[] {certificate});
		} catch (KeyStoreException e1) {
			e1.printStackTrace();
		}

		try {
			store.store(new FileOutputStream("my_cert_data"), "random_password".toCharArray());
		} catch (NoSuchAlgorithmException | CertificateException | IOException | KeyStoreException e) {
			e.printStackTrace();
		}

		if(city != null) {
			city.setHasCert(true);
			cityRep.save(city);
		}else if(office != null) {
			office.setHasCert(true);
			officeRep.save(office);
		}else if(software != null) {
			software.setHasCert(true);
			softwareRep.save(software);
		}else {
			return "No given ID in database";
		}

		return "Success";
	}

	public List<City> getAllData() {

		System.out.println(cityRep.findAll());
		return cityRep.findAll();
	}
	
	public String revokeCert(String nameId) {
		ArrayList<String> revokeList = new ArrayList<String>();
		
		City city = cityRep.findByNameId(nameId);
		Office office = officeRep.findByNameId(nameId);
		Software software = softwareRep.findByNameId(nameId);
		
		if(city != null) {
			if(!city.getHasCert()) {
				return "Error! No certificate for ID";
			}else {
				this.revokeCity(revokeList,city);
				cityRep.save(city);
			}
		}else if(office != null) {
			if(!office.getHasCert()) {
				return "Error! No certificate for ID";
			}else {
				this.revokeOffice(revokeList, office);
				officeRep.save(office);
			}
		}else if(software != null) {
			if(!software.getHasCert()) {
				return "Error! No certificate for ID";
			}else {
				revokeList.add(software.getNameId());
				software.setHasCert(false);
				softwareRep.save(software);
			}
		}else {
			return "No given ID in database";
		}
		
		KeyStoreHandler ksh = new KeyStoreHandler();
		
		ksh.removeCertificates(revokeList);
		
		return "Success";
	}
	
	public void revokeCity(ArrayList<String> aliasList,City c) {
		if(c.getHasCert()) {
			aliasList.add(c.getNameId());
			c.setHasCert(false);
		}else {
			return;
		}
		for(Office of : c.getOffices()) {
			if(of.getHasCert()) {
				revokeOffice(aliasList,of);
			}
		}
	}

	public void revokeOffice(ArrayList<String> aliasList,Office o) {
		if(o.getHasCert()) {
			aliasList.add(o.getNameId());
			o.setHasCert(false);
		}else {
			return;
		}
		for(Software s : o.getSoftwares()) {
			if(s.getHasCert()) {
				aliasList.add(s.getNameId());
				s.setHasCert(false);
			}
		}
	}

	public void revokeSoftware() {
		
	}
	
	
	public CertificateDTO getCertificate(String name_id) {
		KeyStoreHandler ksh = new KeyStoreHandler();
		
		X509Certificate certificate = ksh.getCertificate(name_id);
		
		
		CertificateDTO dto = new CertificateDTO();

		if(certificate == null) {
			return null;
		}
		
		dto.setIssuedBy(certificate.getIssuerX500Principal().getName());
		dto.setOwnedBy(certificate.getSubjectX500Principal().getName());
		dto.setEndDate(certificate.getNotAfter());
		dto.setStartDate(certificate.getNotBefore());
		String public_key = certificate.getPublicKey().toString();
		public_key = public_key.substring(public_key.indexOf("modulus:") + 9 , public_key.lastIndexOf("public exponent:") - 4);
		dto.setPublicKey(public_key);
		dto.setSerialNum(certificate.getSerialNumber().toString());
		
		return dto;
	}
}
