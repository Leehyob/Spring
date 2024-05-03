package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardMapper boardMapper;
	
	@Override
	public List<BoardVO> getList() {
		log.info("getList");
		return boardMapper.getList();
	}

	@Override
	public BoardVO get(long bno) {
		log.info("get"+bno);
		return boardMapper.read(bno);
	}

	@Override
	public void register(BoardVO board) {
		log.info("register"+board);
		boardMapper.insertSelectKey(board);
	}

	@Override
	public boolean modify(BoardVO board) {
		log.info("modify"+board);
		return boardMapper.update(board)==1;
	}

	@Override
	public boolean remove(long bno) {
		log.info("remove"+bno);
		return boardMapper.delete(bno)==1;
	}

	@Override
	public List<BoardVO> getListWithPaging(Criteria cri) {
		return boardMapper.getListWithPaging(cri);
	}

	@Override
	public int getTotalCount(Criteria cri) {
		return boardMapper.getCount(cri);
	}



}
