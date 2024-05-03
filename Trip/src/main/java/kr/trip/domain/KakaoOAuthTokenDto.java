package kr.trip.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KakaoOAuthTokenDto {

	private String access_token;
	private String refresh_token;
	private Integer refresh_token_expires_in;
	private Integer expires_in;
	private String scope;
	private String token_type;
	
}
