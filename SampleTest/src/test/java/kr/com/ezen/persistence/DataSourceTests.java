package kr.com.ezen.persistence;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class DataSourceTests {

	@Autowired //필드 주기
	private DataSource dataSource;
	
	@Test
	public void testConnection() {
		try {
			Connection con = dataSource.getConnection();
			log.info(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

	
