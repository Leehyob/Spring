package kr.trip.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import kr.trip.domain.ContentVO;
import kr.trip.mapper.ContentMapper;

public class ContentServiceImpl implements ContentService{

	@Autowired
	private ContentMapper contentMapper;
	
	@Override
	public void insert(ContentVO content) {
		contentMapper.insert(content);
	}

	@Override
	public ContentVO read(String content_id) {
		return contentMapper.read(content_id);
	}

	@Override
	public List<ContentVO> contentList(String areaname) {
		return contentMapper.contentList(areaname);
	}
	
	
	
}
