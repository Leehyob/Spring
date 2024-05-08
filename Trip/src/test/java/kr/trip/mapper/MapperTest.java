package kr.trip.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
		String phone = "010-1111-1111";
		
		log.info("------------------------------");
		
		String str = memberMapper.findId(name, phone);
		
		log.info(str);
	}
	
}
