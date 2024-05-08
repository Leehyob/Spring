package kr.trip.mapper;

import org.apache.ibatis.annotations.Param;

import kr.trip.domain.AuthVO;
import kr.trip.domain.MemberVO;

public interface MemberMapper {
	
	public MemberVO read(String member_email);
	
	public void insert(MemberVO member);
	
	public void insertAuth(AuthVO auth);
	
	public boolean selectId(String id);
	
	public String findId(@Param("name") String name,@Param("phone") String phone);
	
	 public int update(MemberVO member);
	/*
	 *
	 * 
	 * public int delete(String member_email);
	 * 
	 * public int getAmount();
	 */
}
