package kr.trip.persistence;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

public class JDBCTest {
   
   final String DRIVER = "com.mysql.cj.jdbc.Driver";
     final String URL = "jdbc:mysql://192.168.0.60:3306/project";
     final String USER = "test";
     final String PASSWORD = "test1234!";
     
     @Test
     public void testConnection() throws Exception{
      Class.forName(DRIVER);
      
      try(Connection con = DriverManager.getConnection(URL, USER, PASSWORD)){
       System.out.println(con);
      } catch(Exception e) {
       e.printStackTrace();
      }
     }
}