package kr.trip.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.trip.domain.BoardVO;
import kr.trip.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardMapper boardMapper;

	@Override
	public void register(BoardVO board) {
		boardMapper.insert(board);
	}

	@Override
	public BoardVO get(int board_id) {
		return boardMapper.read(board_id);
	}

	@Override
	public BoardVO selectImg(int board_id) {
		return  boardMapper.selectImg(board_id);
	}
	
	
}
