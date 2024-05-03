package kr.trip.service;

import java.io.IOException;
import java.io.ObjectInputFilter.Status;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;

import kr.trip.controller.kakaoOAuth;
import kr.trip.domain.KakaoOAuthTokenDto;
import kr.trip.domain.KakaoUserInfoDto;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class oAuthService {

	public kakaoOAuth kakaoOAuth = new kakaoOAuth();
	
	public KakaoUserInfoDto getKakaoUserInfoDto(String code) throws JsonProcessingException{
		ResponseEntity<String> accessTokenResponse = kakaoOAuth.requestAccessToken(code);
		KakaoOAuthTokenDto oAuthToken = kakaoOAuth.getAccessToken(accessTokenResponse);
		ResponseEntity<String> userInfoResponse = kakaoOAuth.requestUserInfo(oAuthToken);
		KakaoUserInfoDto kakaoUser = kakaoOAuth.getUserInfo(userInfoResponse);
		return kakaoUser;
	}
	
	@GetMapping("/login/kakao")
	public ResponseEntity<Status> kakaoLogin(
			@RequestParam(name = "code") String code) throws IOException{
		log.info("카카오 API 서버 code : " + code);
		return kakaoLogin(code);
	}
	
}
