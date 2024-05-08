package kr.trip.service;

import kr.trip.domain.AuthVO;
import kr.trip.domain.MemberVO;

public interface MemberService {

	public void register(MemberVO member);
	
	public void insertAuth(AuthVO auth);
	
	public boolean selectId(String id);
	
	public String findId(String name, String phone);
	
	public boolean update(MemberVO member);
}
