package kr.trip.mapper;

import java.util.List;

import kr.trip.domain.ContentVO;

public interface ContentMapper {

	public void insert(ContentVO content);
	
	public ContentVO read(String content_id);
	
	public List<ContentVO> contentList(String areaname);
	
}
