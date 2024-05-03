package kr.trip.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import kr.trip.domain.MemberVO;
import kr.trip.mapper.MemberMapper;

public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private MemberMapper mapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println(username);
		
		MemberVO vo = mapper.read(username);
		
		System.out.println(mapper.read(username));
		return vo;
	}

}
