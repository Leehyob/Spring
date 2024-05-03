package kr.trip.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class KakaoLoginServiceImpl implements KakaoLoginService{

	/*
	 * @Autowired public IACDao dao;
	 */
	
	@Override
	public String getAccessToken(String authorize_code) throws Throwable {
		String access_Token = "";
		String refresh_Token = "";
		String reqURL = "https://kauth.kakao.com/oauth/token";

		try {
			URL url = new URL(reqURL);

			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			// POST 요청을 위해 기본값이 false인 setDoOutput을 true로

			con.setRequestMethod("POST");
			con.setDoOutput(true);
			// POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송

			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(con.getOutputStream()));
			StringBuilder sb = new StringBuilder();
			sb.append("grant_type=authorization_code");

			sb.append("&client_id=3334abd3f2359d5a2d50f1d20357c04f"); // REST_API키 본인이 발급받은 key 넣어주기
			sb.append("&redirect_uri=http://localhost:8181/login/kakao"); // REDIRECT_URI 본인이 설정한 주소 넣어주기

			sb.append("&code=" + authorize_code);
			bw.write(sb.toString());
			bw.close();

			int responseCode = con.getResponseCode();
			System.out.println("responseCode : " + responseCode);

			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line = "";
			String result = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}
			System.out.println("response body : " + result);

			ObjectMapper objectMapper = new ObjectMapper();
			Map<String, Object> jsonMap = objectMapper.readValue(result, new TypeReference<Map<String, Object>>() {
			});
			access_Token = jsonMap.get("access_token").toString();
			refresh_Token = jsonMap.get("refresh_token").toString();

			System.out.println("access_Token : " + access_Token);
			System.out.println("refresh_Token : " + refresh_Token);

			br.close();
			bw.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return access_Token;
	}

	@Override
	public HashMap<String, Object> getUserInfo(String access_Token) throws Throwable {
		// 요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
				HashMap<String, Object> userInfo = new HashMap<String, Object>();
				String reqURL = "https://kapi.kakao.com/v2/user/me";

				try {
					URL url = new URL(reqURL);
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setRequestMethod("GET");

					// 요청에 필요한 Header에 포함될 내용
					conn.setRequestProperty("Authorization", "Bearer " + access_Token);

					int responseCode = conn.getResponseCode();
					System.out.println("responseCode : " + responseCode);

					BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

					String line = "";
					String result = "";

					while ((line = br.readLine()) != null) {
						result += line;
					}
					System.out.println("response body : " + result);
					System.out.println("result type" + result.getClass().getName()); // java.lang.String

					try {
						// jackson objectmapper 객체 생성
						ObjectMapper objectMapper = new ObjectMapper();
						// JSON String -> Map
						Map<String, Object> jsonMap = objectMapper.readValue(result, new TypeReference<Map<String, Object>>() {
						});

						System.out.println(jsonMap.get("properties"));

						Map<String, Object> properties = (Map<String, Object>) jsonMap.get("properties");
						Map<String, Object> kakao_account = (Map<String, Object>) jsonMap.get("kakao_account");

						// System.out.println(properties.get("nickname"));
						// System.out.println(kakao_account.get("email"));

						String nickname = properties.get("nickname").toString();
						String email = kakao_account.get("email").toString();

						userInfo.put("nickname", nickname);
						userInfo.put("email", email);

					} catch (Exception e) {
						e.printStackTrace();
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
				return userInfo;
		
	}

}
