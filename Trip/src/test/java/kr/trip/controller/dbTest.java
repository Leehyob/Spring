package kr.trip.controller;


import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.trip.domain.AuthVO;
import kr.trip.domain.MemberVO;
import kr.trip.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@RequiredArgsConstructor
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml","file:src/main/webapp/WEB-INF/spring/security-context.xml"})
public class dbTest {

	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private BCryptPasswordEncoder bc;

	@Test
	public void testInsertMember() {
		
		for(int i=1; i<=10; i++) {
			
			MemberVO memberVO = new MemberVO();
			
			memberVO.setMember_email("member_"+i);
			memberVO.setPwd(bc.encode("1234"));
			memberVO.setName("USER"+i);
			
			List<AuthVO> authList = new ArrayList<AuthVO>();
			AuthVO userAuth = new AuthVO("user"+i, "ROLE_MEMBER");
			authList.add(userAuth);
			
			if(i>50) {
				AuthVO managerAuth = new AuthVO("user"+i, "ROLE_MANAGER");
				authList.add(managerAuth);				
			}
			if(i>=90) {
				AuthVO adminAuth = new AuthVO("user"+i, "ROLE_ADMIN");
				authList.add(adminAuth);								
			}
			
			memberVO.setAuthList(authList);
			
			memberMapper.insert(memberVO);
			
			authList.stream().forEach(authVO -> {
				memberMapper.insertAuth(authVO);
			});
		}
		
	}
	
	@Test
	public void testPassword() {

		String str = "1234";

		log.info(str);
		
		String encodeStr = bc.encode(str);

		log.info("--------------------------------------------");
		log.info(encodeStr);

		boolean match = bc.matches(str, encodeStr);
		log.info(match);

	}

	@Test
	public void testRead() {
		
		kr.trip.domain.MemberVO vo = memberMapper.read("test1234");
		
		log.info(vo);
	}
	
}
