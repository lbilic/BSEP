package com.kits.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kits.project.model.CertificateNode;

public interface CertificateNodeRepository extends JpaRepository<CertificateNode, Long>{
	CertificateNode findByAlias(String alias);
	CertificateNode findByIsSoftware(Boolean isSoftware);
}
