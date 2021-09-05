package com.example.crud;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserInfoService {
	
	final UserInfoDomainService userInfoDomainService;
	public UserInfoService(UserInfoDomainService userInfoDomainService) { 
		this.userInfoDomainService = userInfoDomainService; 
	} 
	
	public UserInfoDto selectUserInfo(String name) { 
		UserInfo userInfo = userInfoDomainService.getUserInfo(name); 
		UserInfoDto userInfoDto = new UserInfoDto(); 
		userInfoDto.setId(userInfo.getId()); 
		userInfoDto.setName(userInfo.getName()); 
		userInfoDto.setAge(userInfo.getAge());
		return userInfoDto; 
	} 
	
	public UserInfoDto createUserInfo(UserInfoDto userInfoDto) {
		
		UserInfo userInfo = userInfoDomainService.getUserInfo(userInfoDto.getName()); 
		
		if(userInfo == null)
			userInfo = new UserInfo();
		  
		log.debug("userInfoDto.getId():"+userInfoDto.getId()); 
		userInfo.setId(userInfoDto.getId()); 
		userInfo.setName(userInfoDto.getName()); 
		userInfo.setAge(userInfoDto.getAge()); 
		userInfo = userInfoDomainService.createUserInfo(userInfo); 
		userInfoDto.setId(userInfo.getId()); 
		userInfoDto.setName(userInfo.getName()); 
		userInfoDto.setAge(userInfo.getAge()); 
		return userInfoDto;
	} 
	
	public UserInfoDto updateUserInfo(String name, UserInfoDto userInfoDto) { 
		UserInfo userInfo = new UserInfo(); 
		userInfo.setId(userInfoDto.getId()); 
		userInfo.setName(userInfoDto.getName()); 
		userInfo.setAge(userInfoDto.getAge()); 
		log.debug("userInfo created, id:"+userInfoDto.getId()); 
		userInfoDomainService.updateUserInfo(userInfo); 
		return userInfoDto; 
	} 
	
	public void deleteUserInfo(String name) { 
		userInfoDomainService.deleteUserInfo(name); 
	}

}
