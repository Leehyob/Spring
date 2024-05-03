package org.zerock.service;

import java.util.List;

import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

public interface BoardService {

	public List<BoardVO> getListWithPaging(Criteria cri);
	public BoardVO get(long bno);
	public int getTotalCount(Criteria cri);
	public void register(BoardVO board);
	public boolean modify(BoardVO board);
	public boolean remove(long bno);
	
}
