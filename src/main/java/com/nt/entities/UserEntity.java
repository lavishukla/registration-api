package com.nt.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name="USER_DTLS")
public class UserEntity {

	@Id
	@GeneratedValue
	@Column(name="USER_ID")
	private Integer id;
	
	@Column(name="USER_FIRSTNAME")
	private String firstName;
	
	@Column(name="USER_LASTNAME")
	private String lastName;
	
	@Column(name="USER_EMAIL", unique = true)
	private String email;
	
	@Column(name="USER_PHNO")
	private Long phno;
	
	@Column(name="USER_PWD")
	private String pwd;
	
	@Column(name="USER_GENDER")
	private String gender;
	
	@Column(name="USER_COUNTRY")
	private Long country;
	
	@Column(name="USER_STATE")
	private String state;
	
	@Column(name="USER_CITY")
	private String city;
	
	@Column(name="USER_ACC_STATUS")
	private String accStatues;
	
	@Column(name="USER_DOB")
	private LocalDateTime dob;
	
	@Column(name="CREATED_DATE")
	@CreationTimestamp
	private LocalDateTime createdDate;
	
	@Column(name="UPDATED_DATE")
	@UpdateTimestamp
	private LocalDateTime updatedDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getPhno() {
		return phno;
	}

	public void setPhno(Long phno) {
		this.phno = phno;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Long getCountry() {
		return country;
	}

	public void setCountry(Long country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAccStatues() {
		return accStatues;
	}

	public void setAccStatues(String accStatues) {
		this.accStatues = accStatues;
	}

	public LocalDateTime getDob() {
		return dob;
	}

	public void setDob(LocalDateTime dob) {
		this.dob = dob;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	
}
