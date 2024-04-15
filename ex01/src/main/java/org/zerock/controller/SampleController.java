package org.zerock.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.TodoDTO;

import com.google.gson.Gson;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller		
// @RestController // RestController = @Controller + @ResponseBody
@RequestMapping("/sample/")
public class SampleController {
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-mm-dd");
		binder.registerCustomEditor(java.util.Date.class,new CustomDateEditor(dataFormat, false));
	}
	
	@RequestMapping("")
	public void basic() {
		log.info("basic");
	}
	
	@RequestMapping(value="/basic", method= {RequestMethod.GET})
	public void basicGetPost() {
		log.info("basic get");
	}

	@RequestMapping(value="/basic", method= {RequestMethod.POST})
	public void basicGetPost2() {
		log.info("basic post");
	}
	
	@GetMapping("/basic2")
	public void basicGet2() {
		log.info("basic get2");
	}

	@PostMapping("/basic2")
	public void basicPost2() {
		log.info("basic post2");
	}
	
	@GetMapping("/ex01")
	public String ex01(SampleDTO dto) {
		log.info(dto);
		
		return "ex01";
	}

	@PostMapping("/ex01")
	public String ex01_(SampleDTO dto) {
		log.info(dto);
		
		return "ex01";
	}
	
	@GetMapping("/ex02")
	public String ex02(@RequestParam("name") String name,@RequestParam("age") int age) {
		log.info(name);
		log.info(age);
		
		return "ex02";
	}

	@PostMapping("/ex02")
	public String ex02_(@RequestParam("name") String name,@RequestParam("age") int age) {
		log.info(name);
		log.info(age);
		
		return "ex02";
	}
	
	@GetMapping("/ex03")
	public String ex03(TodoDTO dto) {
		log.info(dto);		
		log.info(dto.getTitle());
		log.info(dto.getDueDate());
		
		return "ex03";
	}
	
	@GetMapping("/ex04")
	public String ex04(SampleDTO dto, int page,Model model) {
		log.info(dto);
		log.info(page);
		
		model.addAttribute("sample",dto);
		model.addAttribute("page",page);
		
		return "/sample/ex04";
	}
	
	@GetMapping("/ex44")
	public String ex04_(@RequestParam("name2") String name2,@RequestParam("age2") int age2, RedirectAttributes rttr) {
		
		rttr.addAttribute("name2",name2);
		rttr.addFlashAttribute("age2",age2);
		
		return "redirect:/sample/ex01";
	}
	
	@GetMapping("/ex05")	//void 타입으로 메소드를 만들면 겟매핑한 값으로 이동
	public void ex05() {
		log.info("ex05");
	}
	
	@GetMapping("/ex06")
	public @ResponseBody SampleDTO ex06() {
		SampleDTO sampleDTO = new SampleDTO();
		
		sampleDTO.setName("aaa");
		sampleDTO.setAge(20);
		
		return sampleDTO;
	}
	
//	@GetMapping("/ex07")	//responseBody를 쓰는 대신 클래스 맨 앞에 @restController를 사용해도 json 타입으로 출력
//	public @ResponseBody SampleDTO ex07() {
//		SampleDTO sampleDTO = new SampleDTO();
//		
//		sampleDTO.setName("ccc");
//		sampleDTO.setAge(40);
//		
//		return sampleDTO;
//	}
	
	@GetMapping("/ex066") //Json 값을 java객체로 변환해서 dto전달
	public String ex066(@RequestBody SampleDTO dto) {
		log.info("------------ex066");
		log.info(dto.getName());
		log.info(dto.getAge());
		log.info(dto);
		
		return "ex066";
	}
	
	
	
	@GetMapping("/ex07")
	public ResponseEntity<String> ex07(){
		//ResponseEntity = 상태값까지 같이 전송, 값의 이상 여부 판단 가능 -> f12 : network에서 확인 가능 (200:정상)
		//json = 값만 전송, 값이 정상 데이터인지 비정상 데이터인지 판별 불가
		
		log.info("ex07");
		
		//{"name" : "홍길동"} -> json 타입
		String msg = "{\"name\" : \"홍길동\"}";
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");
		
		return new ResponseEntity<String>(msg, headers, HttpStatus.OK);
		
	}
	
	@GetMapping("/ex08")
	public ResponseEntity<String> ex08(){
		
		log.info("ex07");
		
		String msg = "{\"name\" : \"홍길동\"}";
		
		SampleDTO dto = new SampleDTO();
		
		dto.setName("홍길동");
		dto.setAge(45);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(dto);
		
		log.info(jsonStr);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");
		
		return new ResponseEntity<String>(msg, headers, HttpStatus.OK);
		
	}
	
	@GetMapping("/exUpload")	//localhost8181/sample/exUpload
	public void exUpload() {
		log.info("exUpload------------");
	}
	
	@PostMapping("exUploadPost")
	public void exUploadPost(ArrayList<MultipartFile> files) {
		files.forEach(file -> {
			log.info("-------------");
			log.info("name : " + file.getOriginalFilename());
			log.info("size : " + file.getSize());
		});
	}
	
	
	
}
