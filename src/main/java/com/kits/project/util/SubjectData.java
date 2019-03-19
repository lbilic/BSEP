package com.kits.project.util;

import java.security.PublicKey;
import java.util.Date;

import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;

public class SubjectData {
	private PublicKey publicKey;
	private X500Name x500name;
	private String serialNumber;
	private Date startDate;
	private Date endDate;

	public SubjectData() {

	}

	public SubjectData(PublicKey publicKey, String subjectID, Date startDate, Date endDate) {
		this.publicKey = publicKey;
		this.serialNumber = subjectID;
		X500NameBuilder builderIssuer = new X500NameBuilder(BCStyle.INSTANCE);
	    builderIssuer.addRDN(BCStyle.CN, subjectID);
	    builderIssuer.addRDN(BCStyle.UID, subjectID + "_ID");
	    this.x500name = builderIssuer.build();
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public X500Name getX500name() {
		return x500name;
	}

	public void setX500name(X500Name x500name) {
		this.x500name = x500name;
	}

	public PublicKey getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(PublicKey publicKey) {
		this.publicKey = publicKey;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
