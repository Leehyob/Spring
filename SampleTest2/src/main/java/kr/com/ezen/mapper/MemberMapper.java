package kr.com.ezen.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import kr.com.ezen.dto.MemberVO;

public interface MemberMapper {

	int insertMember(MemberVO vo);
	int updateMember(MemberVO vo);
	int deleteMember(int id);
	
	MemberVO selectOneMember(int id);
	List<MemberVO> selectAllMember();
	
	@Select("select sysdate from dual")
	public String getTime();
	
	public String getTime2();
}
