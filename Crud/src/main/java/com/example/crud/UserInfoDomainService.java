package com.example.crud;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service 
@RequiredArgsConstructor 
@Slf4j
public class UserInfoDomainService {
	
	private final UserInfoRepository userInfoRepository; 
	
	@Transactional 
	public UserInfo getUserInfo(String name) { 
		Optional<UserInfo> userInfo = userInfoRepository.findByName(name); 
		if(!userInfo.isPresent()) 
			throw new RuntimeException("findByName, not found :"+name); 
		return userInfo.get(); 
	} 
	
	@Transactional 
	public UserInfo createUserInfo(UserInfo userInfo) {
		userInfo = userInfoRepository.save(userInfo); 
		
		return userInfo; 
	}
	@Transactional 
	public void updateUserInfo(UserInfo userInfo) { 
		log.debug("userInfo.getId() : "+ userInfo.getId()); 
		UserInfo userinfo1 = userInfoRepository.findById(userInfo.getId()).orElseThrow(()-> new RuntimeException("not found")); 
		log.debug("userinfo1.getId() : "+ userinfo1.getId()); 
		log.debug("userinfo1.getName() : "+ userinfo1.getName()); 
		log.debug("userinfo1.getAge() : "+ userinfo1.getAge());
		userinfo1.setId(userInfo.getId()); 
		userinfo1.setName(userInfo.getName()); 
		userinfo1.setAge(userInfo.getAge()); userInfoRepository.save(userinfo1); 
	} 
		
		
	@Transactional 
	public void deleteUserInfo(String name) { 
		userInfoRepository.deleteByName(name); 
	}
}
