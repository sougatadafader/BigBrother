package com.example.bigbrother.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.bigbrother.models.Campaign;

public interface CampaignRepository extends CrudRepository<Campaign, Integer>{
	
}
