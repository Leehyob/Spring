package kr.trip.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.trip.domain.ContentVO;
import kr.trip.service.ContentService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class PlanController {

	private final ContentService contentService;
	
	@GetMapping("plan/place")
	public void map(Model model) {
		
		String areaname = "경주";
		
		List<ContentVO> list = contentService.contentList(areaname);
		
		System.out.println(list);
		
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
	    
	    @PostMapping("plan/choose")
	    @ResponseBody
	    public ResponseEntity<String> choosePlace(@RequestBody String content_id,@RequestBody String addr2,
	    		@RequestBody String contentType, RedirectAttributes rttr) {
	    	
	    	ContentVO content_c = new ContentVO();
	    	content_c.setContent_id(content_id);
	    	content_c.setAddr2(addr2);
	    	content_c.setContentType(contentType);
	    	
	    	
	    	rttr.addAttribute("content_c",content_c);
	    	
	    	return new ResponseEntity<String> ("result",HttpStatus.OK);
	    }
}
