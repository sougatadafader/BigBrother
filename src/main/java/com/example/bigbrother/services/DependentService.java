package com.example.bigbrother.services;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.bigbrother.models.Dependent;
import com.example.bigbrother.models.SystemUser;
import com.example.bigbrother.models.User;
import com.example.bigbrother.repositories.DependentRepository;

@RestController
@CrossOrigin(origins = { "*" }, allowCredentials = "true",allowedHeaders = "*")
public class DependentService {

	@Autowired
	DependentRepository dependentRepository;
	@PostMapping("/api/dependent")
	public Dependent createDependent(@RequestBody Dependent dependent, HttpSession session){
		SystemUser currentUser = (SystemUser) session.getAttribute("currentUser");
		dependent.setCreatorId(currentUser.getId());
		Dependent dep = dependentRepository.save(dependent);
		return dep;
	}
	
	@GetMapping("/api/dependents")
	public List<Dependent> getAllDependents(){
		return (List<Dependent>) dependentRepository.findAll();
	}
	
	@GetMapping("/api/dependents/{dependentId}")
	public Optional<Dependent> getDependentById(@PathVariable("dependentId") int id){
		return dependentRepository.findById(id);
	}
	
	@PutMapping("/api/dependent/{dependentId}")
	public Dependent updateDependent(@PathVariable("dependentId") int id,@RequestBody Dependent newDependent) {
		Optional<Dependent> optional = dependentRepository.findById(id);
		if(optional.isPresent()) {
			Dependent dep = optional.get();
			dep.set(newDependent);
			return dependentRepository.save(dep);
		}
        return null;
	}
	
	@GetMapping("/api/user/{userId}/dependents")
	public List<Dependent> getDependentsOfUser(@PathVariable("userId") int id) {
		 List<Dependent> dependents = dependentRepository.findDependentsByUser(id);
		 return dependents;
	}
	
	
}
