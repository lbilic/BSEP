package com.kits.project.util;

import java.security.PrivateKey;

import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;

public class IssuerData {
	private X500Name x500name;
	private PrivateKey privateKey;

	public IssuerData() {
	}

	public IssuerData(PrivateKey privateKey, String issuerID) {
		this.privateKey = privateKey;
		X500NameBuilder builderIssuer = new X500NameBuilder(BCStyle.INSTANCE);
	    builderIssuer.addRDN(BCStyle.CN, issuerID);
	    builderIssuer.addRDN(BCStyle.UID, issuerID + "_ID");
	    this.x500name = builderIssuer.build();
	}

	public X500Name getX500name() {
		return x500name;
	}

	public void setX500name(X500Name x500name) {
		this.x500name = x500name;
	}

	public PrivateKey getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(PrivateKey privateKey) {
		this.privateKey = privateKey;
	}

}
