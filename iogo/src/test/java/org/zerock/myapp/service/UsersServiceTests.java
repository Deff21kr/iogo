package org.zerock.myapp.service;

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
import org.zerock.myapp.exception.ServiceException;

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
public class UsersServiceTests {

	@Setter(onMethod_ = { @Autowired })
	private UserService service;

	@Before
	public void setup() {
		log.trace("setup() 인보크");
		assertNotNull(this.service);
		log.info("\tthis.mapper : {}", this.service);
	
	}

	@Test
	public void testSelectList() throws ServiceException {
		log.trace("111 invoked");
		UserDTO dto = new UserDTO();
		String inputId = "admin@naver.com";
		String inputPw = "123qwe321";
		String right ="12312321";
		dto.setId(inputId);
		dto.setPw(right);
		this.service.authenticate(dto);
	}
	
	@Test
	public void testGetUser() throws ServiceException {
		log.trace("testGetUser");
		this.service.getUser("test2222");
	}
	
	@Test
	public void testInsertUser() throws ServiceException {
		log.trace("testInsertUser");
		
		UserDTO dto = new UserDTO();
		String id = "seviceTest";
		String pw = "2222test";
		String name  = "서비스";
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
		
		Integer test = this.service.register(dto);
		log.info("\n\n\t\t****** 1? : {} *******\n",test);
	}
	
	
	@Test
	public void testIdCheck () throws ServiceException {
		log.trace("\ntestIdCheck");
		Integer test =this.service.idCheck("seviceTest");
		log.info("\n\n\t\t****** 1? : {} *******\n",test);
	}

} // end class