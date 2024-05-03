package org.zerock.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyPageDTO;
import org.zerock.domain.ReplyVO;
import org.zerock.service.ReplyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/reply")
@Log4j
@RequiredArgsConstructor
public class ReplyController {
	
	private final ReplyService replyService;
	
	@PostMapping(value="/new" , produces = {MediaType.TEXT_PLAIN_VALUE}, consumes = "application/json" )
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<String> create(@RequestBody ReplyVO reply){
		
		log.info("create"+reply);
		
		int insertCount = replyService.register(reply);
		//== replyService.register(reply)==1
		return insertCount==1 ? new ResponseEntity<String>("success",HttpStatus.OK)
								: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value="/{rno}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ReplyVO> get(@PathVariable("rno") long rno){
		
		log.info("get"+rno);
		
		return new ResponseEntity<ReplyVO>(replyService.get(rno),HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{rno}", consumes = "application/json" , produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> delete(@RequestBody ReplyVO reply, @PathVariable("rno") long rno){
		
		log.info("delete"+rno);
		
		return replyService.remove(rno)==1 ? 
				new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 입력된 값을 받을 때 json 타입으로 받음 	// 값을 보여줄 때 plain 타입으로 보여줌
	@PutMapping(value="/{rno}", consumes = "application/json" ,produces = {MediaType.TEXT_PLAIN_VALUE})
	public  ResponseEntity<String> update(@RequestBody ReplyVO reply, @PathVariable("rno") long rno){
										//@PathVariable : url {} 사이에 입력된 값을 변수 초기화 값으로 끌고 오고 싶을 때 사용, 어노테이션 삽입 안해주면 값 못가져옴
		log.info("update" + rno + reply);
		
		reply.setRno(rno);
		
		return replyService.modify(reply)==1 ? 
				new ResponseEntity<String>("success",HttpStatus.OK) :
					new ResponseEntity<String>("fail",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value="/pages/{bno}/{page}",produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ReplyPageDTO> getList(
			@PathVariable("bno") long bno, @PathVariable("page") int page
			){
		
		log.info("getList"+bno+"page : " + page);
		
		Criteria cri = new Criteria(page, 10);
		
		ReplyPageDTO result = replyService.getList(cri, bno);
		
		return new ResponseEntity<ReplyPageDTO>(result, HttpStatus.OK);
	}
	
}
