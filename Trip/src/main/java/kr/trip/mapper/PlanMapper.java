package kr.trip.mapper;

import java.util.Date;
import java.util.List;

import kr.trip.domain.ContentVO;
import kr.trip.domain.TravelContentVO;
import kr.trip.domain.TravelPlanVO;

public interface PlanMapper {

	public void insertContentIntoPlan(TravelContentVO tc);
	
	public void insertTravelPlan(TravelPlanVO tp);
	
	public int updateDayOfTravelPlan(TravelPlanVO tp);
	
	public int updateTravelPlan(TravelPlanVO tp);
	
	public int updateTravelContent(TravelContentVO tc);
	
	public int deleteFromTravelContent(int plan_id, String content_id);
	
	public List<ContentVO> getContentListOfPlan(int plan_id);
	
}
