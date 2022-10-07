package com.nt.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nt.binding.UserBinding;
import com.nt.constants.AppConstant;
import com.nt.service.RegistrationServiceImpl;

@RestController
public class RegistrationRestController {

	@Autowired
	private RegistrationServiceImpl regService;
	
	@GetMapping("/emailcheck/{email}")
	public String checkEmail(@PathVariable String email) {
		
		boolean uniqueEmail = regService.verifyMail(email);
		
		if(uniqueEmail) {
			return AppConstant.UNIQYE;
		}else{
			return AppConstant.DUPLICATE;
		}
		
	}
		@GetMapping("/countries")
		public Map<Integer, String> getCountries(){
		return regService.getCountries();
	}
		@GetMapping("/states/{countryId}")
		public Map<Integer, String> getStates(@PathVariable Integer CountryId){
		return regService.getStates(CountryId);
	}
		@GetMapping("/states/{stateId}")
		public Map<Integer, String> getCities(@PathVariable Integer stateId){
		return regService.getCities(stateId);
	}
		@PostMapping("/saveuser")
		public String saveUser(@RequestBody UserBinding user) {
			boolean registerUser = regService.register(user);
			if(registerUser) {
				return AppConstant.SUCCESS;
			}else {
				return AppConstant.FAIL;
			}
		}
}	
