package kr.trip.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.GetMapping;

import io.github.bonigarcia.wdm.WebDriverManager;
import kr.trip.controller.GoogleMapsScraper;
import kr.trip.domain.ContentVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class googleMapCrollingTest {

	@Autowired
	private PlanMapper planMapper;

	// WebDriver 설정
	@Test
	public void main() {
		System.out.println("test1");
		GoogleMapsScraper scraper = new GoogleMapsScraper();
		System.out.println("test2");
//		for(int i=0; i<50; i++) {
		List<ContentVO> list = scraper.scrapeDataAndSaveToDB();
//		}
		System.out.println(list);
		for(ContentVO vo : list) {
			planMapper.insert(vo);
		}
	}

	public void scrapeDataAndSaveToDBtest() {
		// WebDriver 설정
		System.out.println("test3");
		WebDriverManager.chromedriver().setup();
		System.out.println("여긴 옴?");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		System.out.println("test4");
		// Google Maps 접속
		driver.get("https://www.google.co.kr/maps");

		// 검색어 입력
		WebElement searchBox = driver.findElement(By.id("searchboxinput"));
		searchBox.sendKeys("음식점 near 33.4953422832796,126.703588865808");
		searchBox.sendKeys(Keys.ENTER);
		
		System.out.println("test5");
		
		// 데이터 저장 리스트
		List<ContentVO> data = new ArrayList<>();
		
		try {
			Thread.sleep(5000); // 페이지 로딩 대기

			System.out.println("test6");
			
			// 페이지 스크롤 및 데이터 스크래핑
			for (int i = 0; i < 5; i++) { // 5 페이지 스크롤
				Thread.sleep(3000); // 데이터 로딩 대기
				List<WebElement> places = driver.findElements(By.cssSelector("div.section-result-content"));
				
				String title = "#QA0Szd > div > div > div.w6VYqd > div.bJzME.tTVLSc > div > div.e07Vkf.kA9KIf > div > div > div.TIHn2 > div > div.lMbq3e > div:nth-child(1) > h1 > span.a5H0ec";
				String selectLoca = "#QA0Szd > div > div > div.w6VYqd > div.bJzME.tTVLSc > div > div.e07Vkf.kA9KIf > div > div > div:nth-child(9) > div:nth-child(3) > button > div > div.rogA2c > div.Io6YTe.fontBodyMedium.kR99db";
				String phoneNum = "#QA0Szd > div > div > div.w6VYqd > div.bJzME.tTVLSc > div > div.e07Vkf.kA9KIf > div > div > div:nth-child(9) > div:nth-child(5) > button > div > div.rogA2c > div.Io6YTe.fontBodyMedium.kR99db";
				String review = "#QA0Szd > div > div > div.w6VYqd > div.bJzME.Hu9e2e.tTVLSc > div > div.e07Vkf.kA9KIf > div > div > div.m6QErb.DxyBCb.kA9KIf.dS8AEf.XiKgde > div.TIHn2 > div > div.lMbq3e > div.LBgpqf > div > div.fontBodyMedium.dmRWX > div.F7nice > span:nth-child(1) > span:nth-child(1)";
				
				for (WebElement place : places) {
					try {
						System.out.println("test7");
						
						String name = place.findElement(By.cssSelector(title)).getText();
						String addr2 = place.findElement(By.cssSelector(selectLoca)).getText();
						String phone = place.findElement(By.cssSelector(phoneNum)).getText();
						
						ContentVO content = new ContentVO();
						content.setContent_id(name); // 임시로 content_id에 이름 설정
						content.setAddr1(""); // addr1이 없으므로 빈 문자열 설정
						content.setAddr2(addr2);
						content.setPhone(phone);
						content.setAreaname("제주");
						content.setContentType("");

						System.out.println("test8");
						
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
		} finally {
			driver.quit();
		}

		System.out.println("test11");
		
		// MyBatis를 통해 데이터베이스에 데이터 저장
		for (ContentVO content : data) {
			planMapper.insert(content);
		}

		System.out.println("Data saved to MySQL database");
	}

}
