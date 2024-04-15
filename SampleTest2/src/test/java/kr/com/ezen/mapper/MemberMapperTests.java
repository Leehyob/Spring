package kr.com.ezen.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.com.ezen.dto.MemberVO;
import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MemberMapperTests {

	
	@Autowired
	private MemberMapper memberMapper;
	
	@Test
	public void testSelectOneMember() {
		MemberVO vo =  memberMapper.selectOneMember(3);
		
		log.info(vo);
	}
	
	@Test
	public void testSelectAllMember() {
//		List<MemberVO> list = memberMapper.selectAllMember();
//		
//		for(MemberVO vo : list) {
//			log.info(vo);
//		}
		
		memberMapper.selectAllMember().forEach(vo -> log.info(vo));
	}
		
	@Test
	public void testGetTime() {
		log.info(memberMapper.getTime());
		
		log.info(memberMapper.getTime2());
	}
	
	@Test
	public void testDeleteMember() {
		memberMapper.deleteMember(19);
	}
	
	
	@Test
	public void testInsertMember() {
		
		MemberVO vo = MemberVO.builder().id(19).name("도로").phone("111-1111-1111").address("수원시").build();
		
		memberMapper.insertMember(vo);
	}
}
