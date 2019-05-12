package com.kits.project.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class CertificateNode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(unique = true)
    private String alias;
    
    @Column
    private String locality;

    @Column
    private String stateName;

    @Column
    private String countryName;
    
    @Column
	@JsonFormat(pattern="yyyy-MM-dd")
    private Date dateIssued;
    
    @Column
	@JsonFormat(pattern="yyyy-MM-dd")
    private Date endDate;
    
    @Column
    private Boolean isSoftware;

    @Column
    @OneToMany(cascade = CascadeType.REMOVE)
    private List<CertificateNode> children;
    
    @Column
    @ManyToMany
    @JsonBackReference
    private List<CertificateNode> connectedSoftwares;
    
    public CertificateNode() {
    }
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public Boolean getIsSoftware() {
		return isSoftware;
	}

	public void setIsSoftware(Boolean isSoftware) {
		this.isSoftware = isSoftware;
	}

	public List<CertificateNode> getChildren() {
		return children;
	}

	public void setChildren(List<CertificateNode> children) {
		this.children = children;
	}

	public Date getDateIssued() {
		return dateIssued;
	}

	public void setDateIssued(Date dateIssued) {
		this.dateIssued = dateIssued;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public List<CertificateNode> getConnectedSoftwares() {
		return connectedSoftwares;
	}

	public void setConnectedSoftwares(List<CertificateNode> connectedSoftwares) {
		this.connectedSoftwares = connectedSoftwares;
	}
	
	
}
