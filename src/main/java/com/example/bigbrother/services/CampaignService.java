package com.example.bigbrother.services;
import javax.servlet.http.HttpSession;

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
import com.example.bigbrother.models.Dependent;
import com.example.bigbrother.models.SystemUser;
import com.example.bigbrother.repositories.CampaignRepository;
import com.example.bigbrother.repositories.DependentRepository;

@RestController
@CrossOrigin(origins = { "*" }, allowCredentials = "true",allowedHeaders = "*")
public class CampaignService {
	@Autowired
	CampaignRepository campaignRepository;
	@Autowired
	DependentRepository dependentRepository;
	
	@PostMapping("/api/dependent/{dependentId}/campaign")
	public Campaign createCampaign(@RequestBody Campaign campaign,@PathVariable("dependentId") int id, HttpSession session){
		Optional<Dependent> dependent = dependentRepository.findById(id);
		if(dependent.isPresent()) {
			SystemUser currentUser = (SystemUser) session.getAttribute("currentUser");
			Dependent dep = dependent.get();
			campaign.setDependent(dep);
			campaign.setUser(currentUser);
			campaign.setCreator(currentUser.getId());
			dep.setEnabled(false);
			Campaign result =  campaignRepository.save(campaign);
			dep.setCampaignId(result.getId());
			dependentRepository.save(dep);
			return result;
		}
		return null;
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