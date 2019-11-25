package com.example.bigbrother.services;
import javax.servlet.http.HttpSession;

import java.util.Collections;
import java.util.Comparator;
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
import com.example.bigbrother.models.User;
import com.example.bigbrother.repositories.CampaignRepository;
import com.example.bigbrother.repositories.DependentRepository;
import com.example.bigbrother.repositories.UserRepository;

@RestController
@CrossOrigin(origins = { "*" }, allowCredentials = "true",allowedHeaders = "*")
public class CampaignService {
	@Autowired
	CampaignRepository campaignRepository;
	@Autowired
	DependentRepository dependentRepository;
	
	@Autowired
	UserRepository userRepository;
	
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
	
	@PostMapping("/api/campaign/{cId}/user/{uId}")
    public boolean addCampaignToFavorite( @PathVariable("cId") int cId,
            						      @PathVariable("uId") int uId) {
        Campaign camp = campaignRepository.findById(cId).get();
        User user = userRepository.findById(uId).get();
        if(camp!=null && user!=null) {
        	camp.addToFavoriteByUser(user);
        	campaignRepository.save(camp);
        	return true;
        }
    	return false;
     }
	
	@GetMapping("/api/campaign/{cId}/user/{uId}")
	public boolean isAddedToFavoriteByUser(@PathVariable("cId") int cId,
                                           @PathVariable("uId") int uId){
		User user = userRepository.findById(uId).get();
		List<Campaign>favoriteCampaigns = user.getFavoriteCampaigns();
		for(Campaign camp:favoriteCampaigns) {
			if(camp.getId() == cId) {
				return true;
			}
		}
		return false;
	}
	
	@GetMapping("/api/campaigns/{campaignId}/likes/count/")
	public int getNumberOfLikes(@PathVariable("campaignId") int id){
		Optional<Campaign> c= campaignRepository.findById(id);
		Campaign camp = c.get();
		return camp.getUsersWhoLiked().size();
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/api/campaign/top/{n}")
	public List<Campaign> getTopNCampaign(@PathVariable("n") int n){
		List<Campaign> camps = (List<Campaign>) campaignRepository.findAll();
		
		if(camps.size()==0) {
			return null;
		}
		Collections.sort(camps, new Comparator() 
		{
			@Override
		    public int compare(Object o1, Object o2) 
		    {
		    	return ( (((Campaign) o1).getUsersWhoLiked().size()) < ((Campaign) o2).getUsersWhoLiked().size()) ? 1:
		    		( (((Campaign) o1).getUsersWhoLiked().size()) > ((Campaign) o2).getUsersWhoLiked().size()) ? -1:0;
		        
		    }
		});
		if(n<camps.size()) {
			return camps.subList(0,n);
		}
		return camps;
	}
}