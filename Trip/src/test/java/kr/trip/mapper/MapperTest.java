package kr.trip.mapper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.trip.domain.MemberVO;
import kr.trip.domain.TravelPlanVO;
import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MapperTest {

	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private PlanMapper planMapper;
	
	@Test
	public void testFindId() {
		String name = "이효빈";
		String phone = "01025521653";
		
		log.info("------------------------------");
		
		List<String> str = memberMapper.findId(name, phone);
		
		log.info(str);
	}
	
	@Test
	public void updateTest() {
		String member_email = "gyqls1653@daum.net";
		String changePwd = "123456789";
		System.out.println(memberMapper.read(member_email));
		
		MemberVO vo = memberMapper.read(member_email);
		vo.setPwd(changePwd);
		memberMapper.update(vo);
		System.out.println(memberMapper.update(vo));
	}
	
	@Test
	public void insertTest() throws ParseException {
		String member_email = "dlgyqls11@naver.com";
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date go = df.parse("2024-06-03");
		Date end = df.parse("2024-06-08");

		TravelPlanVO tp = new TravelPlanVO();
		tp.setMember_email(member_email);
		tp.setGo(go);
		tp.setEnd(end);
		planMapper.insertTravelPlan(tp);
		
		planMapper.updateDayOfTravelPlan(tp);
		
		System.out.println(tp.getDay());

	}
}
