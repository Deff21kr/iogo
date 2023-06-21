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
public class BoardMapperTests {

	@Setter(onMethod_ = { @Autowired })
	private BoardMapper mapper;

	@Before
	public void setup() {
		log.trace("setup() 인보크");
		assertNotNull(this.mapper);
		log.info("\tthis.mapper : {}", this.mapper);
	
	}
	
	@Test
	public void selectList() throws DAOException {
		log.trace("\nselectList :::: ");
		this.mapper.selectList();
	}
	@Test
	public void insertBoard() throws DAOException {
		log.trace("\ninsertBoard:::: ");
		BoardDTO dto = new BoardDTO();
		dto.setId("test555@naver.com");
		dto.setTitle("테스트e");
		dto.setContent("ㅎ2ㅎㅇ하이");
		dto.setStatus("2");
		this.mapper.insertBoard(dto);
		
		this.mapper.selectList();
		
	}
	
	@Test
	public void selectBoard() throws DAOException {
		log.trace("\nselectBoard::::");
		this.mapper.selectBoard("1");
	}
	
	@Test
	public void search() throws DAOException {
		log.trace("\nsearch::::");
		
		SearchDTO searchDTO = new SearchDTO();
		searchDTO.setStatus("0");
		searchDTO.setTitle("e");
		log.info("\n SearchDTO : {} \n",searchDTO);
		this.mapper.selectSearch(searchDTO);
	}
	
	
}
