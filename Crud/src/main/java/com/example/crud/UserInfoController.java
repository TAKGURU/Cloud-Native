package com.example.crud;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserInfoController {
	
	final UserInfoService userInfoService;
	
	public UserInfoController(UserInfoService userInfoService) { 
		this.userInfoService = userInfoService; 
	} 
	
	@PostMapping("/userinfo") 
	public UserInfoDto createUserInfo(@RequestBody UserInfoDto userInfoDto){ 
		return userInfoService.createUserInfo(userInfoDto); 
	} 
	
	@GetMapping("/userinfo/{name}") 
	public UserInfoDto selectUserInfo(@PathVariable String name){ 
		return userInfoService.selectUserInfo(name); 
	} 
	
	@PutMapping("/userinfo/{name}") 
	public UserInfoDto updateUserInfo(@PathVariable String name, @RequestBody UserInfoDto userInfoDto){ 
		return userInfoService.updateUserInfo(name,userInfoDto); 
	} 
	
	@DeleteMapping("/userinfo/{name}") 
	public String deleteUserInfo(@PathVariable String name){ 
		userInfoService.deleteUserInfo(name); 
		return "deleted "+name; 
	}

		
}
