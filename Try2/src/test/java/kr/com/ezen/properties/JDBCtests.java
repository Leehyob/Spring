package kr.com.ezen.properties;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class JDBCtests {
	
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testConnection() {
		try {
			
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","test2","1234");
			
			System.out.println(con);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
