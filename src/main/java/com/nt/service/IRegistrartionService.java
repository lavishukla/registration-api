package com.nt.service;

import java.util.Map;

import com.nt.binding.UserBinding;

public interface IRegistrartionService {

	public Boolean verifyMail(String mail);
	
	public Map<Integer, String> getCountries();
	
	public Map<Integer, String> getStates(Integer countryId);
	
	public Map<Integer, String> getCities(Integer stateId);
	
	public Boolean register(UserBinding form);
}
