package com.example.crud;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo,Long>{
	
	Optional<UserInfo> findByName(String name); 
	void deleteByName(String name);
}
