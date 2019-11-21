package com.example.bigbrother.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.example.bigbrother.models.Donation;

public interface DonationRepository extends CrudRepository<Donation, Integer>{

	@Query("SELECT don from Donation don WHERE don.campaignNumber=:campaignNumber ")
	public List<Donation> getDonationsByCampaign(@Param("campaignNumber") int campaignNumber);

}