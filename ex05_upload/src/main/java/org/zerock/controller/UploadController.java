package org.zerock.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

@Controller
@Log4j
@RequiredArgsConstructor
public class UploadController {

	@GetMapping("/uploadForm")
	public void uploadForm() {
		log.info("upload form ========================");
	}
	
	@PostMapping("/uploadFormAction")
	public void uploadFormAction(MultipartFile[] uploadFile, Model model) {
		
		String uploadFolder = "c:\\upload";
		
		for( MultipartFile multipartFile : uploadFile) {
			log.info("--------------------------------");
			log.info("upload file name : " + multipartFile.getOriginalFilename());
			log.info("upload file size : " + multipartFile.getSize());
			
										// 업로드 경로, 파일 이름
			File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());	//저장 대상
			
			try {
				multipartFile.transferTo(saveFile);	// 파일 저장
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	@GetMapping("/uploadAjax")
	public void uploadAjax() {
		log.info("upload ajax =====================");
	}
	
	@PostMapping("/uploadAjaxAction")
	@ResponseBody
	public void uploadAjaxAction(MultipartFile[] uploadFile) {
		log.info("upload action ===========================");
		String uploadFolder = "c:\\upload";
		
		File uploadPath = new File(uploadFolder, getFolder());
		
		log.info(uploadPath);
		
		if(uploadPath.exists()==false) {
			uploadPath.mkdirs();	//폴더 생성
		}
		
		for( MultipartFile multipartFile : uploadFile) {
			log.info("--------------------------------");
			log.info("upload file name : " + multipartFile.getOriginalFilename());
			log.info("upload file size : " + multipartFile.getSize());
			
			String uploadFileName = multipartFile.getOriginalFilename();
			log.info(uploadFileName);
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\")+1);
			log.info(uploadFileName);
			
			UUID uuid = UUID.randomUUID();
			
			log.info("uuid : " + uuid);
			
			//파일명 앞에 랜덤한 영문자 32자를 붙임 ex)werwdsdfwerwerwesdf_01Apple.jpg
			uploadFileName = uuid.toString()+ "_" + uploadFileName;
			
										// 업로드 경로, 파일 이름
			File saveFile = new File(uploadPath, uploadFileName);	//저장 대상
			
			try {
				multipartFile.transferTo(saveFile);	// 파일 저장
				
				//썸네일 처리
				if(checkImageType(saveFile)) {	
					FileOutputStream thumbnail = new FileOutputStream(
							new File(uploadPath, "s_" + uploadFileName)
							);
					Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 100,100);
					thumbnail.close();
				}
				
				
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	//폴더 저장명, 생성 폴더 단위 분리
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date date = new Date();
		
		String str = sdf.format(date);	//2024-04-29
		
		return str.replace("-",File.separator);	//2024\04\29
	}
	
	private boolean checkImageType(File file) {
		try {
			String contentType = Files.probeContentType(file.toPath());
			log.info("thumb=========================");
			log.info(file);
			log.info(contentType);
			
			return contentType.startsWith("image");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}
