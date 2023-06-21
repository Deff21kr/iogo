package org.zerock.myapp.mapper;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.myapp.domain.BoardDTO;
import org.zerock.myapp.domain.SearchDTO;
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
public class AdminMapperTests {

	@Setter(onMethod_ = { @Autowired })
	private AdminMapper mapper;

	@Before
	public void setup() {
		log.trace("setup() 인보크");
		assertNotNull(this.mapper);
		log.info("\tthis.mapper : {}", this.mapper);
	
	}
	
	@Test
	public void selectList () throws DAOException {
		log.trace("selectList() 인보크");
		
		String dept1 = "A001";
		String dept2 = "B001";
		String dept3 = "C001";
		this.mapper.selectList(dept3);
	}
	
	@Test
	public void adminList () throws DAOException {
		log.trace("adminList() 인보크");
		
		String dept1 = "A001";
		String dept2 = "B001";
		String dept3 = "C001";
		this.mapper.adminList(dept1);
	}
	
	@Test
	public void adminSelect() throws DAOException {
		log.trace("adminList() 인보크");
		BoardDTO dto = new BoardDTO();
		dto.setBno(4);
		dto.setSup("test5555@naver.com");
		Integer a = this.mapper.adminModify(dto);
		System.out.println("\n\tt숫자는? "+a);
		
	}
}
