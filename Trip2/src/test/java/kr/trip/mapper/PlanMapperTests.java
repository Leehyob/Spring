package kr.trip.mapper;

import java.util.List;

import javax.swing.Spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.trip.domain.AllOfPlanDTO;
import kr.trip.domain.ContentVO;
import lombok.extern.log4j.Log4j;

@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Log4j
public class PlanMapperTests {
	@Autowired
	private PlanMapper mapper;
	
	@Test
	public void getTests() {
		log.info(mapper.getContentAreaList("경주"));
	}
	
	@Test
	public void insertTest() {
		ContentVO contentVO = new ContentVO();
		
		contentVO.setContent_id("성산"); // 임시로 content_id에 이름 설정
		contentVO.setAddr1("제주특별자치도"); // addr1이 없으므로 빈 문자열 설정
		contentVO.setAddr2("제주주");
		contentVO.setPhone("1");
		contentVO.setAreaname("제주");
		contentVO.setContentType("제주");
		contentVO.setLikeNum(12345);
		contentVO.setCExplain("제주도입니다,.");
		
		mapper.insert(contentVO);
	}

	@Test
	public void selectTest() {
		int plan_id = 2;
		
		List<AllOfPlanDTO> list = mapper.getContentListOfPlanByPlanId(plan_id);
		
		for(AllOfPlanDTO dto : list) {
			System.out.println(dto);
		}
	}
	
}
