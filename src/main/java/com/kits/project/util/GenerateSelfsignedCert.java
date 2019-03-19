package com.kits.project.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GenerateSelfsignedCert {

	public static void main(String[] args) {
		KeyPair keyPair;
        try {
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA"); 
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
			keyGen.initialize(2048, random);
			keyPair = keyGen.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			keyPair = null;
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
			keyPair = null;
		}

        IssuerData issuer = new IssuerData(keyPair.getPrivate(),"MegaTravelROOT");
        
		SimpleDateFormat iso8601Formater = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = new Date();
		Date endDate = new Date();
		try {
			startDate = iso8601Formater.parse("2017-12-31");
			endDate = iso8601Formater.parse("2022-12-31");
		} catch (ParseException e) {
			e.printStackTrace();
		}
        SubjectData subject = new SubjectData(keyPair.getPublic(),"MegaTravelROOT",startDate,endDate);
        
        CertificateGenerator cg = new CertificateGenerator();
        X509Certificate certificate = cg.generateCertificate(issuer, subject);
        
		System.out.println("-------------------------------------------------------");
		System.out.println(certificate);
		System.out.println("-------------------------------------------------------");
        
		try {
			certificate.verify(keyPair.getPublic());
			System.out.println("Radi");
		} catch (Exception e) {
			System.out.println("Ne radi");
		}

		KeyStore store = null;
		try {
			store = KeyStore.getInstance("JKS", "SUN");
		} catch (KeyStoreException | NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error");
		}

		try {
			store.load(null, "random_password".toCharArray());
		} catch (NoSuchAlgorithmException | CertificateException | IOException e) {
			e.printStackTrace();
		}

		try {
			store.setKeyEntry("MyTravelROOT", issuer.getPrivateKey(), "random_password".toCharArray(), new Certificate[] {certificate});
		} catch (KeyStoreException e1) {
			e1.printStackTrace();
		}

		try {
			store.store(new FileOutputStream("init_keyStore"), "random_password".toCharArray());
		} catch (NoSuchAlgorithmException | CertificateException | IOException | KeyStoreException e) {
			e.printStackTrace();
		}
	
	
	}

}
