package com.kits.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kits.project.model.City;

public interface CityRepository extends JpaRepository<City, Long>{

}
