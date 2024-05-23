package kr.trip.controller;

import java.text.ParseException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.trip.domain.AllOfPlanDTO;
import kr.trip.domain.ContentVO;
import kr.trip.domain.TravelContentVO;
import kr.trip.domain.TravelPlanVO;
import kr.trip.service.PlanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
@RequestMapping("/plan")
@Controller
@RequiredArgsConstructor
@Log4j
public class PlanController {
	
	private final PlanService service;
	
	
	@GetMapping("/place")
	public void plan2(Model model) {
		log.info("테스트 내용" + service.getContentAreaList("경주"));
	model.addAttribute("list",service.getContentAreaList("경주"));
	model.addAttribute("areaname", "경주");
	}
	
	
	@ResponseBody
	@DeleteMapping(value="/delete/{tcontent_id}", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String>delete(@PathVariable("tcontent_id")int tcontent_id){
		log.info("delete..." + tcontent_id);
		
		return service.deleteContent(tcontent_id) == 1 ? new ResponseEntity<String>("result", HttpStatus.OK) :
			                                             new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@ResponseBody
	@DeleteMapping(value="/drop/{plan_id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String>drop(@PathVariable("plan_id")int plan_id){
		
		log.info("drop.."+plan_id);
		
		return service.deleteContentAll(plan_id) != 0 ? new ResponseEntity<String>("result", HttpStatus.OK) :
			                                            new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping("/plan/place")
    @Transactional
    @ResponseBody
    public String choosePlace(TravelPlanVO tp, List<TravelContentVO> tcList, RedirectAttributes rttr) throws ParseException {
    	
		service.insertTravelPlan(tp);
		
        service.insertContentIntoPlan(tcList,tp);

        List<AllOfPlanDTO> list = service.getContentListOfPlanByPlanId(tp.getPlan_id());
        
    	rttr.addAttribute("travelPlan",tp);
    	rttr.addAttribute("allList",list);
    	
    	return "redirect:/plan/library";
    }
	
	@GetMapping("plan/list")
	public void getPlan(List<AllOfPlanDTO> planList, TravelPlanVO tp,String areaname, Model model) {
		
		model.addAttribute("areaname",areaname);
		model.addAttribute("planList",planList);
		
	}
	
	

}
