package kr.trip.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.trip.domain.ContentVO;
import kr.trip.mapper.ContentMapper;

@Service
public class ContentServiceImpl implements ContentService{

	@Autowired
	private ContentMapper contentMapper;
	
	@Override
	public void insert(ContentVO content) {
		contentMapper.insert(content);
	}

	@Override
	public ContentVO read(String areaname) {
		return contentMapper.read(areaname);
	}

	@Override
	public List<ContentVO> contentList(String areaname) {
		return contentMapper.contentList(areaname);
	}
	
	
}
