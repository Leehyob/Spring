package kr.com.ezen.mapper;

import java.util.List;

import kr.com.ezen.dto.MemberVO;

public interface MemberMapper {
	
	public int insertMember();
	public int updateMember(int id);
	public int deleteMember(int id);
	
	public String getTime();
	public List<MemberVO> selectAllMember();
	public MemberVO selectOneMember(int id);
	
	
}
