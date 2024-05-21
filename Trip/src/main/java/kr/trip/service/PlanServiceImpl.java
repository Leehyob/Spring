package kr.trip.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.trip.domain.ContentVO;
import kr.trip.domain.TravelContentVO;
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

}
