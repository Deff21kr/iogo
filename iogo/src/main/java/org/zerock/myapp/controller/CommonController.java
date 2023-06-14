package org.zerock.myapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.zerock.myapp.domain.UserDTO;
import org.zerock.myapp.domain.UserVO;
import org.zerock.myapp.exception.ControllerException;
import org.zerock.myapp.service.UserService;

import lombok.extern.log4j.Log4j2;

@Log4j2

@Controller
public class CommonController {
	
	@Autowired
	private UserService user;
	@GetMapping("/login")
	public String login() {
		return "/common/login";
	}
	@PostMapping("/login")
	public String login(UserDTO dto , Model model) throws ControllerException {
		
		try {
//			UsersVO vo = this.login.authenticate(this.service.get(dto.getID()).toDTO());
			UserVO vo = this.user.authenticate(dto);
			log.info("\tvo: {}", vo);

			if (vo != null) { // 로그인을 성공했다면 (why? 영속성까지 들어가서 객체를 반환한다는건 데이터가 맞게 들어갔다는 뜻)
				model.addAttribute("__AUTH__", vo); // Request Scope
				log.info("\ndto : {} ,model : {}", dto, model);
				return "/main";
			} else {
				return "redirect:/common/loginPost"; // 다시 로그인 페이지로
			}
		} catch (Exception e) {
			throw new ControllerException(e);
		}
		
	}

}
