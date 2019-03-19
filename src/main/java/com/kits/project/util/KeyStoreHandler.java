package com.kits.project.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;

public class KeyStoreHandler {
	private String password = "random_password";
	private String fileName = "my_cert_data";
	private KeyStore store = null;

	public KeyStoreHandler(){
			try {
				store = KeyStore.getInstance("JKS", "SUN");
			} catch (KeyStoreException e) {
				e.printStackTrace();
			} catch (NoSuchProviderException e) {
				e.printStackTrace();
			}
	}
	
	public void removeCertificates(ArrayList<String> revokeList) {
		try {
			store.load(new FileInputStream(fileName), password.toCharArray());
		} catch (NoSuchAlgorithmException | CertificateException | IOException e) {
			e.printStackTrace();
		}
		for(String alias : revokeList) {
			try {
				if(store.isKeyEntry(alias)) {
					//store.getCertificate(alias);
					store.deleteEntry(alias);	
					System.out.println(alias + " -- DELETED FROM DATA");
				}
			} catch (KeyStoreException e) {
				e.printStackTrace();
			}
		}
		
		try {
			store.store(new FileOutputStream(fileName), password.toCharArray());
		} catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException e) {
			System.out.println("error saving changed my_cery_data");
			e.printStackTrace();
		}

	}
	
	public X509Certificate getCertificate(String alias) {
		try {
			store.load(new FileInputStream(fileName), password.toCharArray());
			if(store.isKeyEntry(alias)) {
				return (X509Certificate) store.getCertificate(alias);
			}else {
				return null;
			}
		} catch (NoSuchAlgorithmException | CertificateException | IOException | KeyStoreException e) {
			e.printStackTrace();
		}
		return null;
	}

}
