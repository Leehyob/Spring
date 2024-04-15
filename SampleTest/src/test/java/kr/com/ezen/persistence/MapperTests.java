package kr.com.ezen.persistence;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.com.ezen.dto.MemberVO;
import kr.com.ezen.mapper.MemberMapper;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class MapperTests {

	@Autowired
	private MemberMapper memberMapper;

	@Test
	public void testGetTime() {
		log.info("-----------------------------");
		log.info(memberMapper.getClass().getName());
		log.info(memberMapper.getTime());
		log.info("-----------------------------");
	}

	@Test
	public void testInsert() {
		for( int i=0; i<10; i++){
		MemberVO vo = MemberVO.builder()
				.id(10+i).name("user"+i).phone("010-2424-013"+i).address("서울시 장안구").
				build();
		memberMapper.insertMember(vo);
		}
	}
	
	@Test
	public void testUpdate() {
		MemberVO vo = MemberVO.builder()
				.id(10).name("user").phone("010-2424-013").address("서울시 장안구").
				build();
		memberMapper.insertMember(vo);
	}
	
	@Test
	public void testDelete() {
		memberMapper.deleteMember(10);
	}
	
	@Test
	public void testSelectOne() {
		MemberVO vo = memberMapper.selectOneMember(6);
		
		log.info("vo" + vo);
	}
	
	@Test
	public void testSelectAll() {
//		List<MemberVO> list = memberMapper.selectAllMember();
//		
//		for(MemberVO vo : list) {
//			log.info(vo);
//		}
		
		memberMapper.selectAllMember().forEach(vo -> log.info(vo)); //람다식
	}
	
	

}
