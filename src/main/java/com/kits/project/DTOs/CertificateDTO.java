package com.kits.project.DTOs;

import java.util.Date;

public class CertificateDTO {
	private String issuedBy;
	private String ownedBy;
	private Date startDate;
	private Date endDate;
	private String publicKey;
	private String SerialNum;
	
	public CertificateDTO() {}
	
	public CertificateDTO(String issuedBy, String ownedBy, Date startDate, Date endDate, String publicKey,
			String serialNum) {
		super();
		this.issuedBy = issuedBy;
		this.ownedBy = ownedBy;
		this.startDate = startDate;
		this.endDate = endDate;
		this.publicKey = publicKey;
		SerialNum = serialNum;
	}
	public String getIssuedBy() {
		return issuedBy;
	}
	public void setIssuedBy(String issuedBy) {
		this.issuedBy = issuedBy;
	}
	public String getOwnedBy() {
		return ownedBy;
	}
	public void setOwnedBy(String ownedBy) {
		this.ownedBy = ownedBy;
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
	public String getPublicKey() {
		return publicKey;
	}
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}
	public String getSerialNum() {
		return SerialNum;
	}
	public void setSerialNum(String serialNum) {
		SerialNum = serialNum;
	}

}
