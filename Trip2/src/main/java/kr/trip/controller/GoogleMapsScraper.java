package kr.trip.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import io.github.bonigarcia.wdm.WebDriverManager;
import kr.trip.domain.ContentVO;
import kr.trip.mapper.PlanMapper;
import kr.trip.service.PlanService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class GoogleMapsScraper {
	
	@Autowired
	private PlanMapper planMapper;
	
	@Autowired
	private PlanService planService;

	// WebDriver 설정
	@GetMapping("/testpage")
	public void main() {
		System.out.println("test1");
		GoogleMapsScraper scraper = new GoogleMapsScraper();
		System.out.println("test2");
		scraper.scrapeDataAndSaveToDB();
	}

	
	public List<ContentVO> scrapeDataAndSaveToDB() {
		// WebDriver 설정
		System.out.println("test3");
		WebDriverManager.chromedriver().setup();
		System.out.println("여긴 옴?");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		System.out.println("test4");
		// Google Maps 접속
		driver.get("https://www.myro.co.kr/planning/jeju");
		
		WebElement searchButton = driver.findElement(By.cssSelector("#link1 > div > div.flex.w-full.h-full > div > div.flex.flex-col.items-center.justify-center.w-full.false > div.flex.flex-col.w-full.h-\\[calc\\(100vh_-_290px\\)\\].overflow-y-auto.md\\:mb-2 > ul > div:nth-child(3) > div > div > div.flex.items-center.w-\\[calc\\(100\\%_-_50px\\)\\] > div.h-\\[40px\\].md\\:h-\\[48px\\].xl\\:h-\\[60px\\].\\32 xl\\:h-\\[72px\\].flex.flex-col.text-left.items-start.justify-between.\\32 xl\\:justify-around.w-\\[calc\\(100\\%_-_70px\\)\\].xl\\:w-\\[calc\\(100\\%_-_80px\\)\\].\\32 xl\\:w-\\[calc\\(100\\%_-_90px\\)\\] > h4" )); 
		searchButton.click();
		
		
		System.out.println("test5");
		
		// 데이터 저장 리스트
		List<ContentVO> data = new ArrayList<>();
		
		

		try {
			Thread.sleep(5000); // 페이지 로딩 대기

			System.out.println("test6");
			
			// 페이지 스크롤 및 데이터 스크래핑
			for (int i = 0; i <50; i++) { // 5 페이지 스크롤
				System.out.println("test진입");
				
				Thread.sleep(3000); // 데이터 로딩 대기
				List<WebElement> places = driver.findElements(By.cssSelector("#modal-root > div > div > div > div"));
				
				String title = "#modal-root > div > div > div > div > div > div:nth-child(1) > div.flex.flex-col.items-baseline.w-full > div > div > h1";
				String selectLoca = "#modal-root > div > div > div > div > div > div.my-2 > div:nth-child(1) > span";
				/*
				 * String phoneNum =
				 * "inline-flex items-center mx-1 text-xs md:text-sm font-Montserrat";
				 */
				String likeNumG = "#modal-root > div > div > div > div > div > div:nth-child(1) > div.flex.my-2 > div:nth-child(1) > span";
				String explain = "#modal-root > div > div > div > div > div > div.w-full.text-xs.md\\:text-sm.line-clamp-3.md\\:line-clamp-none.md\\:my-1";
				String type = "#modal-root > div > div > div > div > div > div:nth-child(1) > div.flex.flex-col.items-baseline.w-full > div > div > div";
				
				for (WebElement place : places) {
					try {
						System.out.println("test7");
						
						String name = place.findElement(By.cssSelector(title)).getText();
						String addr2 = place.findElement(By.cssSelector(selectLoca)).getText();
						/* String phone = place.findElement(By.cssSelector(phoneNum)).getText(); */
						int likeNum = Integer.parseInt(place.findElement(By.cssSelector(likeNumG)).getText());
						String cExplain = place.findElement(By.cssSelector(explain)).getText();
						String contentType = place.findElement(By.cssSelector(type)).getText();
						
						ContentVO content = new ContentVO();
						content.setContent_id(name); // 임시로 content_id에 이름 설정
						content.setAddr1("제주특별자치도"); // addr1이 없으므로 빈 문자열 설정
						content.setAddr2(addr2);
						content.setPhone("1");
						content.setAreaname("제주");
						content.setContentType(contentType);
						content.setLikeNum(likeNum);
						content.setCExplain(cExplain);

						System.out.println("test8");
						
						
						System.out.println(content);
						data.add(content);
						
						System.out.println("test9");
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
				
				System.out.println("test10");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		System.out.println("test11");
		
		System.out.println(data);
		System.out.println("Data saved to MySQL database");
		return data;
	}
    }
