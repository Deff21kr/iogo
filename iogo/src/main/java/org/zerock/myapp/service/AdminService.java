package org.zerock.myapp.service;

import java.util.List;

import org.zerock.myapp.domain.BoardDTO;
import org.zerock.myapp.domain.UserDTO;
import org.zerock.myapp.exception.ServiceException;

public interface AdminService {
	
	// 1. 게시글 리스트
		public abstract List<BoardDTO> getList(String dept) throws ServiceException;
		// 셀렉박스 담당자 목록
		public abstract List<UserDTO> getAdminList(String dept) throws ServiceException;
		// 담당자 지정
		public abstract Integer modifySup(BoardDTO dto) throws ServiceException;

}
