package com.example.bigbrother.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.bigbrother.models.SystemUser;
import com.example.bigbrother.models.User;


public interface SystemUserRepository extends CrudRepository<SystemUser, Integer>{

	@Query("SELECT user from SystemUser user WHERE user.enabled=:enabled AND user.username=:username AND user.password=:password")
	public User findUserByCredentials(@Param("enabled") boolean enabled, @Param("username") String username, @Param("password") String password);
	
}
