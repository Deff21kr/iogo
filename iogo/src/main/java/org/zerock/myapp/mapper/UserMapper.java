package org.zerock.myapp.mapper;

import org.springframework.stereotype.Repository;
import org.zerock.myapp.domain.UserDTO;
import org.zerock.myapp.domain.UserVO;
import org.zerock.myapp.exception.DAOException;

@Repository
public interface UserMapper {
	
	// 1.회원가입
	public abstract Integer join(UserDTO user) throws DAOException;
	
	// 2.로그인
//	@Select("""
//			SELECT 
//				id,
//				pw,
//				name,
//				gender,
//				auth,
//				dept,
//				regdate
//			FROM tbl_user
//			WHERE id = #{id} and pw = #{pw}
//			""")
	public abstract UserVO selectUserIdPw(UserDTO user) throws DAOException;
	
	public abstract UserVO selectUser(String id) throws DAOException;	
	

}
