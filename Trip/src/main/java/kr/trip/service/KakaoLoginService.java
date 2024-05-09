package kr.trip.service;

import java.util.HashMap;

import kr.trip.domain.MemberVO;

public interface KakaoLoginService {

	String getAccessToken(String authorize_code) throws Throwable;

	public MemberVO getUserInfo(String access_Token) throws Throwable;
}
