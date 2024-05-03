package kr.trip.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.trip.domain.AuthVO;
import kr.trip.domain.MemberVO;
import kr.trip.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public void register(MemberVO member) {
		
		memberMapper.insert(member);
	}

	@Override
	public void insertAuth(AuthVO auth) {
		memberMapper.insertAuth(auth);
	}
	
	

}
