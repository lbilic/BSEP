package com.kits.project.services.implementations;

import java.io.BufferedInputStream;
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
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kits.project.model.City;
import com.kits.project.model.Office;
import com.kits.project.model.Software;
import com.kits.project.repositories.CityRepository;
import com.kits.project.repositories.OfficeRepository;
import com.kits.project.repositories.SoftwareRepository;
import com.kits.project.util.CertificateGenerator;
import com.kits.project.util.IssuerData;
import com.kits.project.util.SubjectData;

import java.util.List;

@Service
public class CertificateService {
	
	@Autowired
	CityRepository cityRep;
	
	@Autowired
	OfficeRepository officeRep;

	@Autowired
	SoftwareRepository softwareRep;

	public String generateCert(String nameId) {
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
			e.printStackTrace();
		}
		
		City city = cityRep.findByNameId(nameId);
		Office office = officeRep.findByNameId(nameId);
		Software software = softwareRep.findByNameId(nameId);
		
		String parentAlias = "";
		if(city != null) {
			parentAlias = "MyTravelROOT";
		}else if(office != null) {
			parentAlias = office.getCity().getNameId();
		}else if(software != null) {
			parentAlias = software.getOffice().getNameId();
		}else {
			return "Greska1";
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
			return "Greska2";
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
			keyPair = null;
			return "Greska3";
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
			return "Greska4";
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

		return "Success";
	}

	public List<City> getAllData() {

		System.out.println(cityRep.findAll());
		return cityRep.findAll();
	}
}
