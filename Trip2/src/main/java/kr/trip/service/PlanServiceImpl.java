package kr.trip.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.zone.*;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.maven.doxia.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.trip.domain.AllOfPlanDTO;
import kr.trip.domain.AreaVO;
import kr.trip.domain.ContentVO;
import kr.trip.domain.TravelContentVO;
import kr.trip.domain.TravelPlanVO;
import kr.trip.mapper.PlanMapper;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class PlanServiceImpl implements PlanService{
	
	@Autowired
	private PlanMapper mapper;

	@Override
	public List<ContentVO> getContentAreaList(String areaname) {
		log.info(areaname);
		return mapper.getContentAreaList(areaname);
	}

	
	@Override
	public void insertContentIntoPlan(List<TravelContentVO> tcList, TravelPlanVO tp) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		SimpleDateFormat format_t = new SimpleDateFormat("hh:mm:ss");
		String startTp = format.format(tp.getGo());
		String endTp = format.format(tp.getEnd());
		Date start_d =format.parse(startTp);
		Date end_d = format.parse(endTp); 
		LocalDate start = DateUtils.convertToLocalDate(start_d);
		LocalDate end = DateUtils.convertToLocalDate(end_d);
		
		List<LocalDate> dates =	getDatesBetweenTwoDates(start,end);
		assignDatesToTravelContent(tcList,dates);
		saveTravelContentList(tcList);
	}
	
	public List<LocalDate> getDatesBetweenTwoDates(LocalDate start, LocalDate end){
		return start.datesUntil(end.plusDays(1)).collect(Collectors.toList());
	}

	private void assignDatesToTravelContent(List<TravelContentVO> tcList, List<LocalDate> dates) {
		int dateCount = dates.size();
		int tcCount = tcList.size();
		
		for(int i=0; i<tcCount; i++) {
			TravelContentVO tc = tcList.get(i);
			LocalDate date = dates.get(i % dateCount);
			tc.setDay(DateUtils.convertToDate(date));
		}
	}
	
	private void saveTravelContentList(List<TravelContentVO> tcList) {
		for(TravelContentVO vo : tcList) {
			mapper.insertContentIntoPlan(vo);
		}
	}
	
	@Override
	public List<AllOfPlanDTO> getContentListOfPlanByEmail(String member_email) {
		
		return mapper.getContentListOfPlanByEmail(member_email);
	}
	
	@Override
	public List<AllOfPlanDTO> getContentListOfPlanByPlanId(int plan_id) {
		
		return mapper.getContentListOfPlanByPlanId(plan_id);
	}

	@Override
	public void insertTravelPlan(TravelPlanVO tp) {
		mapper.insertTravelPlan(tp);
		mapper.updateDayOfTravelPlan(tp);
	}

	@Override
	public boolean updateTravelPlan(TravelPlanVO tp) {
		return mapper.updateTravelPlan(tp)==1;
	}

	@Override
	public boolean updateTravelContent(TravelContentVO tc) {
		return mapper.updateTravelContent(tc)==1;
	}

	@Override
	public boolean deleteFromTravelContent(int plan_id, String content_id) {
		return mapper.deleteFromTravelContent(plan_id, content_id)==1;
	}


	@Override
	public int deleteContent(int tContent_id) {
		return mapper.deleteContent(tContent_id);
	}

	@Override
	public int deleteContentAll(int plan_id) {
		log.info("drop" + plan_id);
		return mapper.deleteContentAll(plan_id);
	}

	@Override
	public void insert(ContentVO content) {
		System.out.println("실행");
		mapper.insert(content);
	}


	@Override
	public AreaVO getAreaContent(String areaname) {
		return mapper.getAreaContent(areaname);
	}
	
}