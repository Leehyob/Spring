package kr.com.ezen.properties;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.com.ezen.dto.MemberVO;
import kr.com.ezen.mapper.MemberMapper;
import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MapperTests {
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Test
	public void testgetTime() {
		
		log.info(memberMapper.getTime());
	}
	
	@Test
	public void testinsertMember() {
		
		MemberVO vo = new MemberVO();
		
		vo.setId(8);
		vo.setName("라라");
		vo.setPhone("111-2222-3333");
		vo.setAddress("수원시 권선구");
		
		memberMapper.insertMember(vo);
		
		log.info(vo);
		
	}
	
	@Test
	public void testupdateMember() {
		
		MemberVO vo = new MemberVO();
		
		vo.setId(8);
		vo.setName("라라");
		vo.setPhone("111-1111-1111");
		vo.setAddress("수원시 팔달구");
		
		memberMapper.updateMember(vo);
		
		log.info(vo);
	}
	
	@Test
	public void testDeleteMember() {
		
		memberMapper.deleteMember(8);
	}
	
	@Test
	public void testSelectOneMember() {
		
		log.info( memberMapper.selectOneMember(6));
	}
	
	@Test
	public void testSelectAllMember() {
		List<MemberVO> list = memberMapper.selectAllMember();
		
		for(MemberVO vo : list ) {
			log.info(vo);
		}
	}
}
