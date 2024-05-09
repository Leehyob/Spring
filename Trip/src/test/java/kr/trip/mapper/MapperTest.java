package kr.trip.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.trip.domain.MemberVO;
import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MapperTest {

	@Autowired
	private MemberMapper memberMapper;
	
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
	
}
