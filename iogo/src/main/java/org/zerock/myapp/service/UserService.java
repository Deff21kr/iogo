package org.zerock.myapp.service;

import org.zerock.myapp.domain.UserDTO;
import org.zerock.myapp.domain.UserVO;
import org.zerock.myapp.exception.ServiceException;

public interface UserService {
	
	// 1.회원가입
	public abstract Integer register(UserDTO user) throws ServiceException;
	
	// 2.id중복체크
	public abstract Integer idCheck(String id) throws ServiceException;
	// 2.로그인
	public abstract UserVO authenticate(UserDTO user) throws ServiceException;
	// 3. 유저 겟
	public abstract UserVO getUser(String id) throws ServiceException;
	
	

}
