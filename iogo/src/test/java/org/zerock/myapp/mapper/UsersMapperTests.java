package org.zerock.myapp.mapper;

import static org.junit.Assert.assertNotNull;

import java.util.Objects;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
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
	public void testSelectList() throws DAOException {
		log.trace("111 invoked");
		String input = "admin@naver.com";
		UserVO users = this.mapper.selectUser(input);
		Objects.requireNonNull(users);
		this.mapper.selectUserIdPw(users.toDTO());
	}

	

} // end class