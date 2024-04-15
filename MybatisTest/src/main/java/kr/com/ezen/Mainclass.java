package kr.com.ezen;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Mainclass {

	public static void main(String[] args) {

		try {

			String resource = "kr/com/ezen/mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

			SqlSession session = sqlSessionFactory.openSession(true);
			MapperInterface mapper = session.getMapper(MapperInterface.class);
			
//			MemberVO vo = new MemberVO();
//			
//			vo.setId(7);
//			vo.setName("나나");
//			vo.setPhone("010-9999-8888");
//			vo.setAddress("서울시 노원구");
//			
//			mapper.updateMember(vo);
//			
//			mapper.deleteMember(7);
			
			List<MemberVO> list = mapper.selectMember();
			
			for(MemberVO vo : list) {
				System.out.println(vo);
			}
			
			System.out.println(list);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
