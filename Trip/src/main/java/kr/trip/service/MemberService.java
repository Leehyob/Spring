package kr.trip.service;

import kr.trip.domain.AuthVO;
import kr.trip.domain.MemberVO;

public interface MemberService {

	public void register(MemberVO member);
	
	public void insertAuth(AuthVO auth);
}
