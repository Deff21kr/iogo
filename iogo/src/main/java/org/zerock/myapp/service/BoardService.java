package org.zerock.myapp.service;

import java.util.List;

import org.zerock.myapp.domain.BoardDTO;
import org.zerock.myapp.domain.SearchDTO;
import org.zerock.myapp.exception.ServiceException;

public interface BoardService {
	
	// 1. 게시글 리스트
	public abstract List<BoardDTO> getList() throws ServiceException;
	
	// 2. 게시글 등록
	public abstract Integer registerBoard(BoardDTO dto ) throws ServiceException;
	// 3. 게시글 조회
	public abstract BoardDTO getBoard(String bno) throws ServiceException;
	// 4. 게시글 검색
	public abstract List<BoardDTO> getSearch(SearchDTO search) throws ServiceException;

}
