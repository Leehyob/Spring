package kr.trip.domain;

import lombok.Data;

@Data
public class KakaoUserInfoDto {
	private Long id;
	private String connected_at;
	private Properties properties;
	private KakaoAccount kakao_account;
	
	@Data
	public class Properties {
		private String nickname;
		private String profile_image;
		private String thumbnail_image;
	}

	@Data
	public class KakaoAccount{
		private String email;
		private boolean profile_nickname_needs_agreement;
		private boolean profile_image_needs_agreement;
		private profile profile;
		private boolean name_needs_agreement;
		private boolean email_needs_agreement;
		private boolean has_email;
		private boolean is_email_valid;
		private boolean is_email_verified;
		
		@Data
		public class profile{
			private String nickname;
			private String thumbnail_image_url;
			private String profile_image_url;
			private boolean is_default_image;
		}
	}


}





