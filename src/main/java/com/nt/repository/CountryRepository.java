package com.nt.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entities.CountryEntity;

public interface CountryRepository extends JpaRepository<CountryEntity, Serializable>{

}
