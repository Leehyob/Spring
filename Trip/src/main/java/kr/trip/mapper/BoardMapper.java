package kr.trip.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import kr.trip.domain.BoardVO;
import kr.trip.domain.Criteria;

public interface BoardMapper {

	public BoardVO selectImg(int board_id);
	

	   // 게시물 등록
	   public void insert(BoardVO vo);

	   // 게시물 읽기
	   public BoardVO read(int board_id);


	
}
