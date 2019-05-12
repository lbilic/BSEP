package com.kits.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kits.project.model.CertificateStatus;

public interface CertificateStatusRepository extends JpaRepository<CertificateStatus, Long>{
	CertificateStatus findBySerialNumber(String serialNumber);
}
