package com.example.crud.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.crud.domain.model.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo,Long> {
	
	Optional<UserInfo> findByName(String name);
	
	void deleteByName(String name);

}
