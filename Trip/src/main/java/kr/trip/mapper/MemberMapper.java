package kr.trip.mapper;

import kr.trip.domain.AuthVO;
import kr.trip.domain.MemberVO;

public interface MemberMapper {
	
	public MemberVO read(String member_email);
	
	public void insert(MemberVO member);
	
	public void insertAuth(AuthVO auth);
	
	public boolean selectId(String id);
	/*
	 * public int update(MemberVO member);
	 * 
	 * public int delete(String member_email);
	 * 
	 * public int getAmount();
	 */
}
