package kr.trip.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.trip.domain.KakaoOAuthTokenDto;
import kr.trip.domain.KakaoUserInfoDto;

public class kakaoOAuth {

	@Value("${app.kakao.restApikey}")
	private String restApiKey;
	
	@Value("${app.kakao.redirectUrl}")
	private String kakaoRedirectUrl;
	
	private final String KAKAO_TOKEN_REQUEST_URL = "https://kauth.kakao.com/oauth/token";
	
	public String responseUrl() {
		
		String kakaoLoginUrl = "https://kauth.kakao.com/oauth/authorize?client id=" + restApiKey+
				"%redirect_uri="+kakaoRedirectUrl+"%response_type=code";
		
		return kakaoLoginUrl;
	}
	
	public ResponseEntity<String> requestAccessToken(String code){
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headersAccess = new HttpHeaders();
		headersAccess.add( "content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", restApiKey);
		params.add("redirect_uri", kakaoRedirectUrl);
		params.add("code", code);
		
		HttpEntity<MultiValueMap<String, String>> kakaoRequest = new HttpEntity<MultiValueMap<String,String>>(params, headersAccess);
		
		ResponseEntity<String> responseEntity = restTemplate.postForEntity(KAKAO_TOKEN_REQUEST_URL, kakaoRequest, String.class);
		
		return responseEntity;
		
	}
	
	public KakaoOAuthTokenDto getAccessToken(ResponseEntity<String> response) throws JsonProcessingException{
		ObjectMapper objectMapper = new ObjectMapper();
		KakaoOAuthTokenDto kakaoOAuthTokenDto = objectMapper.readValue(response.getBody(), KakaoOAuthTokenDto.class);
		return kakaoOAuthTokenDto;
	}
	
	public ResponseEntity<String> requestUserInfo(KakaoOAuthTokenDto oAuthToken){
		HttpHeaders headers = new HttpHeaders();
		RestTemplate restTemplate = new RestTemplate();
		
		headers.add("Authorization", "Bearer"+oAuthToken.getAccess_token());
		
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(headers);
		ResponseEntity<String> response = restTemplate.exchange("https://kapi.kakao.com/v2/user/me", HttpMethod.GET,request, String.class);
		return response;
		
	}
	
	public KakaoUserInfoDto getUserInfo(ResponseEntity<String> response) throws JsonProcessingException{
		ObjectMapper objectMapper = new ObjectMapper();
		KakaoUserInfoDto kakaoUserInfoDto = objectMapper.readValue(response.getBody(), KakaoUserInfoDto.class);
		return kakaoUserInfoDto;
		
	}
	
}
