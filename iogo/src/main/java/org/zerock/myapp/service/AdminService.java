package org.zerock.myapp.service;

import java.util.List;

import org.zerock.myapp.domain.BoardDTO;
import org.zerock.myapp.exception.ServiceException;

public interface AdminService {
	
	// 1. 게시글 리스트
		public abstract List<BoardDTO> getList(String dept) throws ServiceException;

}
