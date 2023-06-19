package org.zerock.myapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.zerock.myapp.domain.BoardDTO;
import org.zerock.myapp.domain.SearchDTO;
import org.zerock.myapp.exception.DAOException;

@Repository
public interface BoardMapper {
	
	// 1. 게시판 조회
	@Select("""
			SELECT 
				bno,
				id,
				title,
				content,
				status
			FROM 
				tbl_board
			""")
	public abstract List<BoardDTO> selectList() throws DAOException;
	
	// 2. 게시글 등록
	public abstract Integer insertBoard(BoardDTO dto) throws DAOException;
	// 3. 게시글 조회
	public abstract BoardDTO selectBoard(String bno) throws DAOException;
	// 4. 검색
	public abstract List<BoardDTO> selectSearch(SearchDTO search) throws DAOException;
}
