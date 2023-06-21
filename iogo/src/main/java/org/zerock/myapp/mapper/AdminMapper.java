package org.zerock.myapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.zerock.myapp.domain.BoardDTO;
import org.zerock.myapp.domain.UserDTO;
import org.zerock.myapp.exception.DAOException;

public interface AdminMapper {
	
	// 1. 게시판 조회
		@Select("""
				SELECT /*+ index_desc(tbl_board) */
				    b.bno,
				    b.id,
				    b.title,
				    b.content,
				    b.status
				FROM 
				    tbl_board b, tbl_user u
				WHERE 
					b.id=u.id
				AND
					u.dept =#{dept}
				AND
					b.status IN ('0','1')
				""")
		public abstract List<BoardDTO> selectList(String dept) throws DAOException;
		
		// 담당자 셀렉박스 회원 조회
		public abstract List<UserDTO> adminList(String dept) throws DAOException;
		// 담당자 지정
		public abstract Integer adminModify(BoardDTO dto) throws DAOException;
}
