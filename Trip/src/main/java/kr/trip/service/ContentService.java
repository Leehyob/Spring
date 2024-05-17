package kr.trip.service;

import java.util.List;

import kr.trip.domain.ContentVO;

public interface ContentService {

	public void insert(ContentVO content);
	
	public ContentVO read(String areaname);
	
	public List<ContentVO> contentList(String areaname);
	
}
