package kr.trip.controller;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.compress.compressors.FileNameUtil;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.trip.domain.BoardVO;
import kr.trip.service.BoardService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BoardController {

	private final BoardService boardService;
	
	@GetMapping("/board/register")
	   public void register() {}

	   @PostMapping("/board/register")
	  public String register(@RequestParam("image") MultipartFile image, @RequestParam("member_email")String member_email ,
			  @RequestParam("title") String title, @RequestParam("content") String content,
			  RedirectAttributes rttr) throws IllegalStateException, IOException {
		   System.out.println(image);
		 
		   BoardVO vo = new BoardVO();
		   vo.setTitle(title);
		   vo.setMember_email(member_email);
		   vo.setContent(content);
		   String imageName = image.getOriginalFilename();
		   System.out.println(imageName);
		   vo.setImage(imageName.getBytes());
		 
		  
		  boardService.register(vo);
		   
		   return "redirect:/imageTest";
	   }
	   
	   @GetMapping("/imageTest")
	   public void imageTest(Model model) throws IOException {
		   
		   int board_id = 181;
		   
		   BoardVO vo = boardService.selectImg(board_id);
		   System.out.println(vo);
		   byte[] bytes = vo.getImage();
		   String image = Base64.getEncoder().encodeToString(bytes);
		   model.addAttribute("image","/resources/image/common.jpg");
		   
	   }
	   
}