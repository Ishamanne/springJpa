package com.boot.dao;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public class UserRepoCustomImpl  implements UserRepoCustom{
	
	@PersistenceContext
	EntityManager entitymanager;
	

	@Override
	@Cacheable(value="users")
	public List<User> getList() {
		Query query = entitymanager.createNativeQuery("SELECT * FROM USER",User.class);
		return query.getResultList();
	}

	
	
}
