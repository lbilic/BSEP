package com.kits.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kits.project.model.Software;

public interface SoftwareRepository extends JpaRepository<Software, Long>{
    Software getSoftwareByNameId(String nameId);
}
