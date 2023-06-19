package org.zerock.myapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.BoardDTO;
import org.zerock.myapp.domain.SearchDTO;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.mapper.BoardMapper;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service("boardService")
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardMapper mapper;

	@Override
	public List<BoardDTO> getList() throws ServiceException {
		log.info("\ngetList::::: SERVICE\n");
		try {
			return this.mapper.selectList();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Integer registerBoard(BoardDTO dto) throws ServiceException {
		log.info("\nregisterBoard::::: SERVICE\ndto: {} ",dto);
		try {
			return this.mapper.insertBoard(dto);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	

	@Override
	public BoardDTO getBoard(String bno) throws ServiceException {
		log.info("\ngetBoard::::: SERVICE\n{} bno : {}",bno);
		try {
			return this.mapper.selectBoard(bno);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<BoardDTO> getSearch(SearchDTO search) throws ServiceException {
		log.info("\ngetSearch::::: SERVICE\nsearch : {}",search);
		try {
			return this.mapper.selectSearch(search);
			
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
	}
	
	
	
	

}
