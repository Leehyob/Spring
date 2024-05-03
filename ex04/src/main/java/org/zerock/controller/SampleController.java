package org.zerock.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.SampleVO;

import lombok.extern.log4j.Log4j;

//@Controller
@RestController		//@Controller + @ResponseBody
@Log4j
@RequestMapping("/sample")
public class SampleController {

								  // 반환타입(MIME TYPE)
	@GetMapping(value="/getText", produces = "text/plain; charset=utf-8")
	//@ResponseBody
	public String getText() {
		log.info("MIME TYPE : " + MediaType.TEXT_PLAIN_VALUE);
		
		return "안녕하세요";
	}
	
	@GetMapping(value="/getSample", produces= MediaType.APPLICATION_JSON_VALUE)
	public SampleVO getSample() {
		return new SampleVO(111, "스타2" , "로드2");
	}
	
	@GetMapping(value="/getSample2")
	public SampleVO getSample2() {
		return new SampleVO(111, "스타2" , "로드2");
	}
	
	@GetMapping(value="/getList", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<SampleVO> getList() {
		
		/*
		 * List<SampleVO> list = new ArrayList<SampleVO>();
		 *  for(int i=1; i<10; i++) {
		 * SampleVO vo = new SampleVO(i, i+"First",i+"Last");
		 *  list.add(vo); 
		 *  }
		 * ->  return 으로 보내는 식이랑 출력값 같음
		 */
		
		return IntStream.range(1, 10)	//1~10전까지 전달
				.mapToObj(i->new SampleVO(i, i+"First", i+"Last"))	//객체 생성
				.collect(Collectors.toList());	//생성된 객체를 수집해 List로 만든 후 반환
	}
	
	@GetMapping(value="/getMap",produces=MediaType.APPLICATION_JSON_VALUE)
	public Map<String, SampleVO> getMap(){
		Map<String , SampleVO> map = new HashMap<String, SampleVO>();
		
		map.put("First", new SampleVO(111, "그루트","주니어"));
		
		return map;
	}
	
	@GetMapping(value="/getMap2",produces=MediaType.APPLICATION_JSON_VALUE)
	public Map<String, String> getMap2(){
		
		return Map.of("name : ","kim","age : ","20");
	}
	
	@GetMapping(value="/check", params= {"height","weight"},produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SampleVO> check(double height, double weight){
		SampleVO vo = new SampleVO(0,"" + height, "" + weight);
		
		ResponseEntity<SampleVO> result = null;
		
		if(height<150) {
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo);
		}else {
			result = ResponseEntity.status(HttpStatus.OK).body(vo);
		}
		
		return result;
	}
	
	@GetMapping(value="/product/{name}/{age}",produces=MediaType.APPLICATION_JSON_VALUE)
	public String[] getPath(
			@PathVariable("name") String name,
			@PathVariable("age") Integer age
			) {
		return new String[] {"name :" + name, "age : " + age};
	}
	
	//json값을 요청 받아서 json으로 반환
	@PostMapping(value="/ticket", produces = MediaType.APPLICATION_JSON_VALUE)
	public SampleVO convert(@RequestBody SampleVO vo) {
		log.info(vo);
		return vo;
	}
	
	
}
