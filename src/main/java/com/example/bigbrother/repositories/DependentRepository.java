package com.example.bigbrother.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.bigbrother.models.Dependent;

public interface DependentRepository extends CrudRepository<Dependent, Integer>{

	@Query("SELECT dep from Dependent dep WHERE dep.creatorId=:creatorId ")
	public List<Dependent> findDependentsByUser(@Param("creatorId") int creatorId);
}

