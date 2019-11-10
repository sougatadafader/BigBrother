package com.example.bigbrother.services;
import javax.servlet.http.HttpSession;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.bigbrother.models.Campaign;
import com.example.bigbrother.models.SystemUser;
import com.example.bigbrother.models.User;
import com.example.bigbrother.repositories.CampaignRepository;

@RestController
@CrossOrigin(origins = { "*" }, allowCredentials = "true",allowedHeaders = "*")
public class CampaignService {
	@Autowired
	CampaignRepository campaignRepository;
	
	@PostMapping("/api/campaign")
	public Campaign createCampaign(@RequestBody Campaign campaign, HttpSession session){
		SystemUser currentUser = (SystemUser) session.getAttribute("currentUser");
		campaign.setUser(currentUser);
		Campaign camp = campaignRepository.save(campaign);
		return camp;
	}
	
	@GetMapping("/api/campaigns")
	public List<Campaign> getAllCampaigns(){
		return (List<Campaign>) campaignRepository.findAll();
	}
	
	@GetMapping("/api/campaign/{campaignId}")
	public Optional<Campaign> getCampaign(@PathVariable("campaignId") int id){
		return campaignRepository.findById(id);
	}
	
	@PutMapping("/api/campaign/{campaignId}")
	public Campaign updateCampaign(@PathVariable("campaignId") int id,@RequestBody Campaign newCampaign) {
		Optional<Campaign> optional = campaignRepository.findById(id);
		if(optional.isPresent()) {
			Campaign camp = optional.get();
			camp.set(newCampaign);
			return campaignRepository.save(camp);
		}
        return null;
	}
}