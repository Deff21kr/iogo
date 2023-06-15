package org.zerock.myapp.mapper;

import org.springframework.stereotype.Repository;
import org.zerock.myapp.domain.UserDTO;
import org.zerock.myapp.domain.UserVO;
import org.zerock.myapp.exception.DAOException;

@Repository
public interface UserMapper {
	
	// 1.회원가입
	public abstract Integer insertUser (UserDTO user) throws DAOException;
	// 2. id 중복체크
	public abstract Integer selectIdCheck(String id) throws DAOException;	

	// 3.로그인
	public abstract UserVO selectUserIdPw(UserDTO user) throws DAOException;
	// 4. 1명의 유저객체 by id
	public abstract UserVO selectUser(String id) throws DAOException;	
	
	
}
