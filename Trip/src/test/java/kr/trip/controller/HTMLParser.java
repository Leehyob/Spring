package kr.trip.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import kr.trip.domain.ContentVO;
import kr.trip.mapper.ContentMapper;
import kr.trip.mapper.MemberMapper;

public class HTMLParser {
	public static void main(String[] args) throws Exception{
		Connection con = Jsoup.connect("https://www.google.co.kr/maps/?hl=ko");
		Document doc = con.get();
		Elements elements = doc.select(".li box");
		
		for(int i=0; i<elements.size(); i++) {
			Element el = elements.get(i);
			String content_id = el.selectFirst(".DUwDvf lfPIob").text();
			String info = el.selectFirst(".Io6YTe fontBodyMedium kR99db ").text();
			String address = el.selectFirst(".address").text();
			String phone = el.selectFirst(".list").text();
			Element img = el.selectFirst(".list_img img");
			
			Map<String, String> map = new HashMap<String, String>();
			map.put("content_id", content_id);
			map.put("cExplain", info);
			map.put("addr2", address);
			map.put("phone", phone);
			
			saveDB(map);
			saveFile(content_id,img.attr("data-original"));
			
		}
	}
	
	static void saveFile(String content_id, String imgSrc) throws Exception{
		
		URL url = new URL(imgSrc);
		
		BufferedInputStream bis = new BufferedInputStream(url.openStream());
		File file = new File("C:\\mu",content_id);
		if(!file.exists()) {
			file.mkdirs();
		}
		
		file = new File(file, "thumb.jpg");
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
		
		int b = 0;
		while((b=bis.read()) != -1) {
			bos.write(b);
		}
		bos.close();
	
	}
	
	@Autowired
	private static ContentMapper contentMapper;
		
	static void saveDB(Map<String, String> map) throws Exception{
		
		ContentVO content = new ContentVO();
		content.setContent_id(map.get("content_id"));
		content.setCExplain("cExplain");
		content.setAddr2("addr2");
		content.setPhone("phone");
		
		contentMapper.insert(content);
	
	}
}
