package kr.trip.mapper;

import java.util.List;

import kr.trip.domain.ContentVO;
import kr.trip.domain.TravelContentVO;

public interface PlanMapper {

	public void insertContentIntoPlan(TravelContentVO tc);
	
	public List<ContentVO> getContentListOfPlan(int plan_id);
	
}
