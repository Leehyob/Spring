package kr.trip.mapper;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import com.google.gson.Gson;

import kr.trip.domain.ContentDTO;
import kr.trip.domain.ContentVO;
import lombok.extern.log4j.Log4j;

@Log4j
public class dbTest {
	
	public static void main(String[] args) {
		
		String addr = "https://apis.data.go.kr/5050000/hwangridangilFoodHotPlaceService/getHwangridangilFoodHotPlace?serviceKey=TYVP9QA2OJqxkuw0Gl5UsfO17ssZsqgwvLEzpZkK9KNAhVJ5MzQuMdbDlPUdgd6MfR1jkhDTMm7vQC3V8r6SEQ%253D%253D&pageNo=1&numOfRows=10";
		
		try {
			URL url = new URL(addr);
			
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			
			BufferedReader br = new BufferedReader(
					new InputStreamReader(con.getInputStream(), "utf-8"));
					
			String responseJson = br.readLine();
			
			Gson gson = new Gson();
			
			
			Connection condb = DriverManager.getConnection("jdbc:mysql://192.168.0.60:3306/project?useSSL=false&amp;serverTimezone=UTC");
			
			String sql = "insert into content(content_id, addr1, addr2, areaname, contentType)"
					+ "values(?,?,?,?,?)";
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
