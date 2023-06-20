package org.zerock.myapp.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.myapp.domain.BoardDTO;
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
public class AdminServiceTests {

	@Setter(onMethod_ = { @Autowired })
	private AdminService service;

	@Before
	public void setup() {
		log.trace("setup() 인보크");
		assertNotNull(this.service);
		log.info("\tthis.mapper : {}", this.service);
	
	}

	@Test
	public void getList() throws ServiceException {
		log.trace("\ngetList:::::");
		this.service.getList("C001");
	}
	
	
	
	
} // end class