package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.CertificateStatus;

public interface CertificateStatusRepository extends JpaRepository<CertificateStatus, Long>{
	CertificateStatus findBySerialNumber(String serialNumber);
}
