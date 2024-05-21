package kr.trip.mapper;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;

import kr.trip.domain.ContentVO;
import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class dbTest {
	
	@Autowired
	private ContentMapper contentMapper;
	
	@Test
	public void insertdb() {
		
		/*
		 * String addr =
		 * "https://apis.data.go.kr/5050000/hwangridangilFoodHotPlaceService/getHwangridangilFoodHotPlace?serviceKey=TYVP9QA2OJqxkuw0Gl5UsfO17ssZsqgwvLEzpZkK9KNAhVJ5MzQuMdbDlPUdgd6MfR1jkhDTMm7vQC3V8r6SEQ%3D%3D&pageNo=3&numOfRows=10";
		 * 
		 * try { URL url = new URL(addr);
		 * 
		 * System.out.println("url : " +url);
		 * 
		 * HttpURLConnection con = (HttpURLConnection)url.openConnection();
		 * 
		 * System.out.println("con : " + con);
		 * 
		 * BufferedReader br = new BufferedReader( new
		 * InputStreamReader(con.getInputStream(), "utf-8"));
		 * 
		 * StringBuilder responseJson = new StringBuilder(); String line; while ((line =
		 * br.readLine()) != null) { responseJson.append(line); }
		 * 
		 * System.out.println("responseJson : " + responseJson);
		 * 
		 * Gson gson = new Gson(); ContentDTO contentDto =
		 * gson.fromJson(responseJson.toString(), ContentDTO.class);
		 * 
		 * System.out.println("gson : " + gson); System.out.println("contentDTO : " +
		 * contentDto);
		 * 
		 * List<ContentVO> result =
		 * contentDto.getResponse().getBody().getItems().getItem();
		 * 
		 * System.out.println(result);
		 * 
		 * for(int i = result.size() - 1; i >= 0; i--) { Content content = new
		 * Content();
		 * 
		 * content.setContent_id(result.get(i).getBSSH_NM());
		 * content.setAddr1("경상북도 경주시"); content.setAddr2(result.get(i).getADRES());
		 * content.setPhone(result.get(i).getMBTLNUM()); content.setAreaname("경주");
		 * content.setContentType("식당"); content.setCExplain("설명");
		 * 
		 * contentMapper.insert(content); }
		 * 
		 * 
		 * 
		 * }catch(Exception e) { e.printStackTrace(); }
		 */
	}
	
}
