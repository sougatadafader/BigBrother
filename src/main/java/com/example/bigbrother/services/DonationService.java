package com.example.bigbrother.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.bigbrother.models.Campaign;
import com.example.bigbrother.models.Donation;
import com.example.bigbrother.models.SystemUser;
import com.example.bigbrother.repositories.CampaignRepository;
import com.example.bigbrother.repositories.DonationRepository;

@RestController
@CrossOrigin(origins = { "*" }, allowCredentials = "true",allowedHeaders = "*")
public class DonationService {
	
	@Autowired
	DonationRepository donationRepository;
	
	@Autowired
	CampaignRepository campaignRepository;
	
	@PostMapping("/api/campaign/{campaignId}/donate")
	public List<Donation> donateToCampaignId(@RequestBody Donation donation,@PathVariable("campaignId") int id, HttpSession session) {
		Optional<Campaign> campaign = campaignRepository.findById(id);
		if(campaign.isPresent()) {
			SystemUser currentUser = (SystemUser) session.getAttribute("currentUser");
			Campaign camp = campaign.get();
			donation.setCampaign(camp);
			donation.setUser(currentUser);
			donation.setUserNumber(currentUser.getId());
			donation.setCampaignNumber(id);
			List<Donation> donations = camp.getDonations();
			donations.add(donation);
			donationRepository.save(donation);
			List<Donation> donationList = camp.getDonations();
			Collections.reverse(donationList);
			return donationList;
		}
		return null;
	}
	
	@GetMapping("/api/campaign/{campaignId}/donations")
	public List<Donation> getDonationsOfCampaign(@PathVariable("campaignId") int id){
		List<Donation> donations = donationRepository.getDonationsByCampaign(id);
		if(donations.size()>0) {
			Collections.reverse(donations);
			return donations;
		}
		return null;
	}
	
	@GetMapping("/api/user/{userId}/donations")
	public List<Donation> getDonationsOfUser(@PathVariable("userId") int id){
		List<Donation> donations = donationRepository.getDonationsByUser(id);
		System.out.println(donations.size());
		if(donations.size()>0) {
			Collections.reverse(donations);
			return donations;
		}
		return null;
	}

}
