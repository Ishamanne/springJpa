package com.boot.dao;

import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


@Transactional
public interface UserRepo   extends JpaRepository<User,Long>,UserRepoCustom{
	
	 public User findByEmail(String email);
	 public User findByEmailAndAddress(String email,String address);

	 
}


