package kr.com.ezen.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.com.ezen.dto.MemberVO;
import kr.com.ezen.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	
	//생성자 주입 -> @RequiredArgsConstructor 꼭 넣어줘야 함
	private final MemberMapper memberMapper;
	
	//필드 주입
//	@Autowired
//	private MemberMapper mapper;
	//보통 잘 안씀	
	
	
	@Override
	public void insertMember(MemberVO vo) {
		memberMapper.insertMember(vo);
	}

	@Override
	public void updateMember(MemberVO vo) {
		memberMapper.updateMember(vo);
	}

	@Override
	public void deleteMember(int id) {
		memberMapper.deleteMember(id);
	}

	@Override
	public MemberVO selectOneMember(int id) {
		MemberVO vo = memberMapper.selectOneMember(id);
		return vo;
	}

	@Override
	public List<MemberVO> selectAllMember() {
		List<MemberVO> list = memberMapper.selectAllMember();
		return list;
	}
	
	
	
}
