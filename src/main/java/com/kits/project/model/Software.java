package com.kits.project.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Software {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column
    private String nameId;

    @Column
    private String certPath;

    @Column
    private Boolean hasCert;

    @ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
    private Office office;

    @ManyToMany
	private List<Software> connectedSoftware;

    public Software() {
    	this.connectedSoftware = new ArrayList<Software>();
	}

	public Software(String nameId, String certPath, Boolean hasCert, Office office, List<Software> connectedSoftware) {
		this.nameId = nameId;
		this.certPath = certPath;
		this.hasCert = hasCert;
		this.office = office;
		this.connectedSoftware = connectedSoftware;
	}

	public Software(String nameId, String certPath, Boolean hasCert, Office office) {
		this.nameId = nameId;
		this.certPath = certPath;
		this.hasCert = hasCert;
		this.office = office;
		this.connectedSoftware = new ArrayList<Software>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCertPath() {
		return certPath;
	}

	public void setCertPath(String certPath) {
		this.certPath = certPath;
	}

	public Boolean getHasCert() {
		return hasCert;
	}

	public void setHasCert(Boolean hasCert) {
		this.hasCert = hasCert;
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	public String getNameId() {
		return nameId;
	}

	public void setNameId(String nameId) {
		this.nameId = nameId;
	}

	public List<Software> getConnectedSoftware() {
		return connectedSoftware;
	}

	public void setConnectedSoftware(List<Software> connectedSoftware) {
		this.connectedSoftware = connectedSoftware;
	}
}
