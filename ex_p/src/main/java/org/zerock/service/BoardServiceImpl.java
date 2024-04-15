package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.zerock.domain.BoardVO;
import org.zerock.mapper.BoardMapper;

public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper boardMapper;
	
	@Override
	public List<BoardVO> list() {
		return boardMapper.list();
	}

	@Override
	public BoardVO get(long bno) {
		return boardMapper.read(bno);
	}

	@Override
	public void register(BoardVO board) {
		boardMapper.insertSelectKey(board);
	}

	@Override
	public boolean modify(BoardVO board) {
		return boardMapper.update(board) == 1;
	}

	@Override
	public boolean remove(long bno) {
		return boardMapper.delete(bno)==1;
	}
	
	
	
}
