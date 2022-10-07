package com.nt.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Serializable>{

	public UserEntity findByEmail(String mail);
}
