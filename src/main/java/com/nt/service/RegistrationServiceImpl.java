package com.nt.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.binding.UserBinding;
import com.nt.constants.AppConstant;
import com.nt.entities.CityEntity;
import com.nt.entities.CountryEntity;
import com.nt.entities.StateEntity;
import com.nt.entities.UserEntity;
import com.nt.props.AppProperties;
import com.nt.repository.CityRepository;
import com.nt.repository.CountryRepository;
import com.nt.repository.StateRepository;
import com.nt.repository.UserRepository;
import com.nt.utils.EmailUtil;

@Service
public class RegistrationServiceImpl implements IRegistrartionService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CountryRepository countryRepo;
	
	@Autowired
	private StateRepository stateRepo;
	
	@Autowired
	private CityRepository cityRepo;
	
	@Autowired
	private EmailUtil emailUtil;
	
	@Autowired
	private AppProperties appProp; 

	@Override
	public Boolean verifyMail(String mail) {
		UserEntity isPresent = userRepo.findByEmail(mail);
		if(isPresent==null) {
			return true;
		}
		return false;
	}

	@Override
	public Map<Integer, String> getCountries() {
		List<CountryEntity> findAll = countryRepo.findAll();
		
		Map<Integer, String> countryMap = new HashMap<>();
		
		for (CountryEntity entity : findAll) {
			countryMap.put(entity.getCountryId(), entity.getCountryName());	
		}
		return countryMap;
	}

	@Override
	public Map<Integer, String> getStates(Integer countryId) {
		List<StateEntity> findAll = stateRepo.findByCountryId(countryId);
		Map<Integer, String> stateMap = new HashMap<>();
		
		for (StateEntity entity : findAll) {
			stateMap.put(entity.getStateId(), entity.getStateName());	
		}
		return stateMap;
	}

	@Override
	public Map<Integer, String> getCities(Integer stateId) {
		List<CityEntity> findAll = cityRepo.findByStateId(stateId);
		
		Map<Integer, String> cityMap = new HashMap<>();
		for(CityEntity entity : findAll) {
			cityMap.put(entity.getStateId(), entity.getCityName());
		}
		return cityMap;
	}

	@Override
	public Boolean register(UserBinding form) {
		
		form.setPwd(tempPassword());
		form.setAccStatues(AppConstant.LOCKED);
		
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(form, userEntity);
		
		 UserEntity save = userRepo.save(userEntity);
		
		 if(save.getId()!=null) {
			 return sendRegEmail(form);
		 }
		return false;
	}
	
	private String tempPassword() {
		String tempPwd = null;
		
	    int leftLimit = 97; // letter 'a'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 6;
	    Random random = new Random();
	    StringBuilder buffer = new StringBuilder(targetStringLength);
	    for (int i = 0; i < targetStringLength; i++) {
	        int randomLimitedInt = leftLimit + (int) 
	          (random.nextFloat() * (rightLimit - leftLimit + 1));
	        buffer.append((char) randomLimitedInt);
	    }
	    tempPwd = buffer.toString();	
		return tempPwd;
	}
	
	private Boolean sendRegEmail(UserBinding user) {
		
		boolean sendEmail = false;
		
		Map<String, String> messages = appProp.getMessages();
		String subject = messages.get(AppConstant.REG_MAIL_SUBJECT);
		String bodyFileName = messages.get(AppConstant.REG_MAIL_BODY_TEMPLATE_FILE);
		String body = readMailBody(bodyFileName, user);
		
		emailUtil.sendEmail(subject, body, user.getEmail());
		
		return sendEmail;
	}
	
	public String readMailBody(String fileName, UserBinding  user) {
		String mailBody=null;
		StringBuffer buffer = new StringBuffer();
		Path path = Paths.get(fileName);
		try(Stream<String> stream = Files.lines(path)){
			stream.forEach(line ->{
				buffer.append(line);
			});
			mailBody = buffer.toString();
			mailBody = mailBody.replace(AppConstant.FNAME, user.getFirstName());
			mailBody = mailBody.replace(AppConstant.EMAIL, user.getEmail());
			mailBody = mailBody.replace(AppConstant.TEMP_PWD, user.getPwd());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return mailBody;
	}
}
