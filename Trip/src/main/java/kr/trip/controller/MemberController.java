package kr.trip.controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.trip.domain.AuthVO;
import kr.trip.domain.MemberVO;
import kr.trip.service.KakaoLoginService;
import kr.trip.service.MemberService;
import kr.trip.service.certifiedPhoneNumber;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import net.nurigo.java_sdk.Coolsms;

@Controller
@Log4j
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;
	
	@Autowired
	private BCryptPasswordEncoder bc;
	
	
	
	@GetMapping("/member/register")
	public void join() {
		
	}
	
	
	
	@PostMapping("/member/register")
	@Transactional
	public String join(MemberVO member, RedirectAttributes rttr) {
		
		member.setPwd(bc.encode(member.getPwd()));
		
		memberService.register(member);
		memberService.insertAuth(new AuthVO(member.getMember_email(),"ROLE_MEMBER"));
		
		
		return "redirect:/customLogin";
	}
	
	@Autowired
	public KakaoLoginService kakaoS;
	
	@GetMapping("/login/kakao")
	public String kakaoOauth(@RequestParam(required = false) String code) throws Throwable {
		System.out.println(code);
		
		String access_Token = kakaoS.getAccessToken(code);
		System.out.println("###access_Token#### : " + access_Token);
		
		// 3번
		HashMap<String, Object> userInfo = kakaoS.getUserInfo(access_Token);
		System.out.println("###nickname#### : " + userInfo.get("nickname"));
		System.out.println("###email#### : " + userInfo.get("email"));
		
		return "redirect:/member/register";
	}
	

	@PostMapping("/ConfirmId")
	@ResponseBody
	public ResponseEntity<Boolean> confirmId(String id) {
		log.info("ConfirmId.........");
		log.info("id : " + id);
		boolean result = true;
		
		if(id.trim().isEmpty()) {
			log.info("id : " + id);
			result = false;
		} else {
			if (memberService.selectId(id)) {
				result = false;
			} else {
				result = true;
			}
		}
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@Autowired
	private JavaMailSenderImpl mailSender;
	
	@PostMapping("/mailCheck")
	@ResponseBody
	public int mailCheck(@RequestParam("email") String email) {
log.info("전달 받은 이메일 주소 : " + email);
		
		//난수의 범위 111111 ~ 999999 (6자리 난수)
		Random random = new Random();
		int checkNum = random.nextInt(888888)+111111;
		
		//이메일 보낼 양식
		String setFrom = "dlgyqls11@naver.com"; //2단계 인증 x, 메일 설정에서 POP/IMAP 사용 설정에서 POP/SMTP 사용함으로 설정o
		String toMail = email;
		String title = "트립GO 인증 이메일 입니다.";
		String content = "인증 코드는 " + checkNum + " 입니다." +
						 "<br>" +
						 "해당 인증 코드를 인증 코드 확인란에 기입하여 주세요.";
		
		try {
			MimeMessage message = mailSender.createMimeMessage(); //Spring에서 제공하는 mail API
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            helper.setFrom(setFrom);
            helper.setTo(toMail);
            helper.setSubject(title);
            helper.setText(content, true);
            mailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		log.info("랜덤숫자 : " + checkNum);
		return checkNum;
	}
	
	@GetMapping("/member/findid")
	public void findid() {
		
	}
	
	@Autowired
	private certifiedPhoneNumber certifiedPhoneNumber;
	
	@PostMapping("/member/checkPhone")
	@ResponseBody
	public String sendSMS(String phone,@RequestParam("name") String name) {

		log.info(name);
		log.info(phone);
		
		Random rand  = new Random(); //랜덤숫자 생성하기 !!
        String numStr = "";
        for(int i=0; i<4; i++) {
            String ran = Integer.toString(rand.nextInt(10));
            numStr+=ran;
        }
        
          certifiedPhoneNumber.certifiedPhoneNumber(phone, numStr); //휴대폰 api 쪽으로 가기 !!
// // 밑에 자세한 설명나옴
         
          return numStr;
	}
	
	@PostMapping("/member/findid")
	public String findId(@RequestParam("name") String name,@RequestParam("phone") String phone,RedirectAttributes rttr) {
		
		log.info("---------------------------------");
		log.info("name : " + name);
		log.info(phone);

		String str = memberService.findId(name, phone);
		
		System.out.println("str..............." + str);
		rttr.addAttribute("member_email",str);
		
		return "redirect:/member/id";
	}
	
	@GetMapping("/member/id")
	public void id( String member_email, Model model) {
		model.addAttribute("member_email",member_email);
		log.info("member 아이디 : " + member_email);
	}
	
	@GetMapping("/member/findPwd")
	public void findPwd() {
		
	}
	
	@PostMapping("/member/findPwd")
	public String findPwd(@RequestParam("member_email") String member_email,RedirectAttributes rttr) {
		
		MemberVO vo = new MemberVO();
		
		if(vo.getMember_email()==member_email) {
			vo.setMember_email(member_email);			
		}
		memberService.update(vo);
		rttr.addAttribute("member_email",vo.getMember_email());
		return "redirect:/member/updatePwd";
	}
	
	@GetMapping("/member/updatePwd")
	public void updatePwd(String member_email, Model model) {
		model.addAttribute("member_email",member_email);
		log.info(member_email);
	}
	
	
}