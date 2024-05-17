package kr.trip.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import kr.trip.domain.BoardVO;

public interface BoardService {
	 //게시물삽입
	   public void register(BoardVO board);
	   //게시물상세보기
	   public BoardVO get(int board_id);
	   
	   public BoardVO selectImg(int board_id);

}
