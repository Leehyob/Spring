package org.zerock.mapper;

import java.util.List;

import org.zerock.domain.BoardVO;

public interface BoardMapper {
	
	public List<BoardVO> list();
	public BoardVO read(long bno);
	public void insert(BoardVO board);
	public void insertSelectKey(BoardVO board);
	public int update(BoardVO board);
	public int delete(long bno);
}
