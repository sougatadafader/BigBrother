package com.example.bigbrother.services;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.bigbrother.models.Dependent;
import com.example.bigbrother.models.SystemUser;
import com.example.bigbrother.repositories.DependentRepository;

@RestController
@CrossOrigin(origins = { "*" }, allowCredentials = "true",allowedHeaders = "*")
public class DependentService {

	@Autowired
	DependentRepository dependentRepository;
	@PostMapping("/api/depedent")
	public Dependent createDependent(@RequestBody Dependent dependent, HttpSession session){
		SystemUser currentUser = (SystemUser) session.getAttribute("currentUser");
		dependent.setUser(currentUser);
		Dependent dept = dependentRepository.save(dependent);
		return dept;
	}
	
}
