package kr.trip.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.trip.domain.ContentVO;
import kr.trip.domain.TravelContentVO;
import kr.trip.domain.TravelPlanVO;
import kr.trip.mapper.PlanMapper;

@Service
public class PlanServiceImpl implements PlanService{

	@Autowired
	private PlanMapper planMapper;
	
	@Override
	public void insertContentIntoPlan(TravelContentVO tc) {
		planMapper.insertContentIntoPlan(tc);
	}

	@Override
	public List<ContentVO> getContentListOfPlan(int plan_id) {
		
		plan_id = 1;
		
		return planMapper.getContentListOfPlan(plan_id);
	}

	@Override
	public void insertTravelPlan(TravelPlanVO tp) {
		planMapper.insertTravelPlan(tp);
		planMapper.updateDayOfTravelPlan(tp);
	}

	@Override
	public boolean updateTravelPlan(TravelPlanVO tp) {
		return planMapper.updateTravelPlan(tp)==1;
	}

	@Override
	public boolean updateTravelContent(TravelContentVO tc) {
		return planMapper.updateTravelContent(tc)==1;
	}

	@Override
	public boolean deleteFromTravelContent(int plan_id, String content_id) {
		return planMapper.deleteFromTravelContent(plan_id, content_id)==1;
	}

}
