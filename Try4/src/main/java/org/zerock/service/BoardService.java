package org.zerock.service;

import java.util.List;

import org.zerock.domain.BoardVO;

public interface BoardService {

	public List<BoardVO> getList();
	public BoardVO get(long bno);
	public void register(BoardVO board);
	public boolean modify(BoardVO board);
	public boolean remove(long bno);
	
}
