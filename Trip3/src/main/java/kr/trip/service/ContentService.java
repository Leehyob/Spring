package kr.trip.service;

import java.util.List;

import kr.trip.domain.ContentVO;

public interface ContentService {

	public void insert(ContentVO content);
	
	public ContentVO read(String content_id);
	
	public List<ContentVO> contentList(String areaname);
	
}
