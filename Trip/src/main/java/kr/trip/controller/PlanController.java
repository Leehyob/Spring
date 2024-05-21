package kr.trip.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.trip.domain.ContentVO;
import kr.trip.domain.TravelContentVO;
import kr.trip.domain.TravelPlanVO;
import kr.trip.service.ContentService;
import kr.trip.service.PlanService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class PlanController {

	private final ContentService contentService;
	
	private final PlanService planService;
	
	@GetMapping("/plan/place")
	public void map(Model model) {
		
		String areaname = "경주";
		
		List<ContentVO> list = contentService.contentList(areaname);
		
		model.addAttribute("list",list);
		
	}
	

	    private final String GEOCODING_API_URL = "https://maps.googleapis.com/maps/api/geocode/json";

	    @GetMapping("/geocode")
	    public String geocodeAddress(@RequestParam("addr2") String addr2) {
	        RestTemplate restTemplate = new RestTemplate();
	        String requestUrl = String.format("%s?address=%s&key=AIzaSyD-nI2V_bsNjQF5ZQ4mlq8o8sr1oZ6bLi0", GEOCODING_API_URL, addr2);
	        String response = restTemplate.getForObject(requestUrl, String.class);
	        return response;
	    }
	    
	    @PostMapping("/plan/choose")
	    @Transactional
	    @ResponseBody
	    public ResponseEntity<List<ContentVO>> choosePlace(int plan_id, String content_id, Model model) {
	    	
	    	System.out.println(plan_id);
	    	
	    	System.out.println(content_id);
	    	
	    	return null;
//	    	
//	    	TravelContentVO tc = new TravelContentVO();
//	    	tc.setContent_id(content_id);
//	    	tc.setPlan_id(plan_id);
//	    	
//	    	planService.insertContentIntoPlan(tc);
//	    	
//	    	List<ContentVO> list = planService.getContentListOfPlan(plan_id);
//	    	
//	    	model.addAttribute("contentList",list);
//	    	
//	    	return new ResponseEntity<List<ContentVO>>(list, HttpStatus.OK);
	    }
	    
	    
	    
	    
}



