package org.zerock.myapp.mapper;

import static org.junit.Assert.assertNotNull;

import java.util.Objects;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.myapp.domain.UserDTO;
import org.zerock.myapp.domain.UserVO;
import org.zerock.myapp.exception.DAOException;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

// Spring Framework 구동
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { 
		"file:src/main/webapp/WEB-INF/**/root-*.xml",
		"file:src/main/webapp/WEB-INF/**/servlet-*.xml"
})
public class UsersMapperTests {

	@Setter(onMethod_ = { @Autowired })
	private UserMapper mapper;

	@Before
	public void setup() {
		log.trace("setup() 인보크");
		assertNotNull(this.mapper);
		log.info("\tthis.mapper : {}", this.mapper);
	
	}

	@Test
	public void testSelect() throws DAOException {
		log.trace("\n1명의 유저 반환 및 로그인");
		String input = "admin@naver.com";
		UserVO users = this.mapper.selectUser(input);
		Objects.requireNonNull(users);
		this.mapper.selectUserIdPw(users.toDTO());
	}
	
	@Test
	public void testInsert() throws DAOException {
		log.trace("\n회원가입");
		UserDTO dto = new UserDTO();
		String id = "test2222";
		String pw = "2222test";
		String name  = "멍청이";
		String gender= "M";
		String auth = "01";
		String dept = "A001";
		dto.setId(id);
		dto.setAuth(auth);
		dto.setDept(dept);
		dto.setGender(gender);
		dto.setName(name);
		dto.setPw(pw);
		log.info("\ndto : {}\n",dto);
		this.mapper.insertUser(dto);
	}
	@Test
	public void testIdCheck() throws DAOException {
		log.trace("\nid 중복체크");
		String id = "serviceTest";
		this.mapper.selectIdCheck(id);
	}

	

} // end class