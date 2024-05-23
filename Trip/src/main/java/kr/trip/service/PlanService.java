package kr.trip.service;

import java.util.List;

import kr.trip.domain.ContentVO;
import kr.trip.domain.TravelContentVO;
import kr.trip.domain.TravelPlanVO;

public interface PlanService {

	public void insertContentIntoPlan(TravelContentVO tc);
	
	public void insertTravelPlan(TravelPlanVO tp);
	
	public boolean updateTravelPlan(TravelPlanVO tp);
	
	public boolean updateTravelContent(TravelContentVO tc);
	
	public boolean deleteFromTravelContent(int plan_id, String content_id);
	
	public List<ContentVO> getContentListOfPlan(int plan_id);
}
