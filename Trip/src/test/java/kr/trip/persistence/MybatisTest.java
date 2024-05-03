package kr.trip.persistence;

import java.sql.Connection;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MybatisTest {
   
	@Inject
	private SqlSessionFactory sqlsession;
	
	@Test
	public void testbatis() {
		try {
			SqlSession session = sqlsession.openSession();
			
			System.out.println(session);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	

}