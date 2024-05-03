package org.zerock.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class UploadControllerPractice {

	@PostMapping("/uploadForm")
	public void uploadForm(MultipartFile[] uploadFile, Model model) {
		
		//저장 위치
		String uploadFolder = "c:\\upload";
		
		for(MultipartFile multipartFile : uploadFile) {
			
			File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());
			
			try {
				multipartFile.transferTo(saveFile);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	@PostMapping("/uploadAjaxAction")
	public void uploadAjaxAction(MultipartFile[] uploadFile) {
		String uploadFolder = "c:\\upload";
		
		File uploadPath = new File(uploadFolder, getFolder());
		
		if(uploadPath.exists()==false) {
			uploadPath.mkdirs();	//폴더 생성
		}
		
		for(MultipartFile multipartFile : uploadFile) {
			String uploadFileName = multipartFile.getOriginalFilename();
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\")+1);	// 뒤에 1은 왜??
			
			UUID uuid = UUID.randomUUID();
			
			uploadFileName = uuid.toString()+"_"+uploadFileName;
			
			File saveFile = new File(uploadPath, uploadFileName);
			
			try {
				multipartFile.transferTo(saveFile);
				
				if(checkImageType(saveFile)) {	//이미지 타입이 맞으면
					FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_"+uploadFileName));
													//썸네일 파일 생성		//썸네일이 생성될 폴더 주소, "s_"(썸네일 구분) + 썸네일명
					thumbnail.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date date = new Date();
		// 데이트 포멧으로 집어넣기
		String str = sdf.format(date);
		// - 를 기준으로 폴더 분리
		return str.replace("-", File.separator);
		
	}
	
	private boolean checkImageType(File file) {
		try {
			// 컨텐츠 타입 검사 -> probeContentType이 타입 검사 해줌	//file.toPath -> 경로 지정
			String contentType = Files.probeContentType(file.toPath());
			// 파일 타입이 이미지인지 아닌지 boolean 타입으로 반환
			return contentType.startsWith("image");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
}
