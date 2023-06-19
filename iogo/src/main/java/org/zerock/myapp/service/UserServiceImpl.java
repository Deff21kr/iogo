package org.zerock.myapp.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.UserDTO;
import org.zerock.myapp.domain.UserVO;
import org.zerock.myapp.exception.ServiceException;
import org.zerock.myapp.mapper.UserMapper;

import lombok.extern.log4j.Log4j2;
@Log4j2
@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper mapper;

	@Override
	public Integer register(UserDTO user) throws ServiceException {
		log.info("\nregister 회원가입 성공시 1을 반환\n");
		try {
			Integer joinUser =this.mapper.insertUser(user);
			Objects.requireNonNull(joinUser);
			if( joinUser==1 ) {
				return 1;
			} else {
				return 0;
			}
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	} // 회원가입
	
	@Override
	public Integer idCheck(String id) throws ServiceException {
		log.info("\nidCheck : 사용가능하다면 1을 반환\n");
		try {
			Integer idCount =this.mapper.selectIdCheck(id);
			Objects.requireNonNull(idCount);
			if( idCount==1 ) {
				log.info("중복된 아이디입니다.");
				return 0;
			} else {
				log.info("사용가능한 아이디입니다.");
				return 1;
			}
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}  // id중복체크


	@Override
	public UserVO authenticate(UserDTO user) throws ServiceException {
		log.info("\nauthenticate\n");
		try {
			String input = user.getId(); // 입력받은 아이디
			UserVO vo = this.mapper.selectUser(input); // 로 vo객체 획득
			log.info("\nvo : {}",vo);
			log.info("\ninputSelect : {}",user);
			if(  vo.getPw().equals( user.getPw() )  ) {
				log.info("\n\n비즈니스 계층 로그인 성공\n");
				return this.mapper.selectUserIdPw(vo.toDTO());
			} else {
				log.info("\n\n비즈니스 계층 로그인 *** 실패 ***\n");
				return null;
			}
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	} // 로그인

	@Override
	public UserVO getUser(String id) throws ServiceException {
		log.info("\ngetUser\n");
		try {
				return this.mapper.selectUser(id); 
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	
} // end class 
