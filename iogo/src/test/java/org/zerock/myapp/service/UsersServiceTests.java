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
	private UserService Service;

	@Before
	public void setup() {
		log.trace("setup() 인보크");
		assertNotNull(this.Service);
		log.info("\tthis.mapper : {}", this.Service);
	
	}

	@Test
	public void testSelectList() throws DAOException, ServiceException {
		log.trace("111 invoked");
		UserDTO dto = new UserDTO();
		String inputId = "admin@naver.com";
		String inputPw = "123qwe321";
		String right ="12312321";
		dto.setId(inputId);
		dto.setPw(right);
		this.Service.authenticate(dto);
	}

	

} // end class