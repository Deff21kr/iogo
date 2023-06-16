package org.zerock.myapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
	
	@GetMapping("/main")
	public void main() {
		System.out.println("main");
	}
	
	@GetMapping("/login")
	public String login() {
		return "/common/login";
	} // get_login
	
	@PostMapping("/login")
	public String login(UserDTO dto , Model model,HttpServletRequest req,RedirectAttributes rttrs) throws ControllerException {
		
		try {
//			UsersVO vo = this.login.authenticate(this.service.get(dto.getID()).toDTO());
			UserVO vo = this.user.authenticate(dto);
			log.info("\tvo: {}", vo);

			if (vo != null) { 
				HttpSession session = req.getSession(false);
				if(session != null) {
					session.invalidate();
				}
				session = req.getSession();
				session.setAttribute("SESS_UESR_ID", vo.getId() );
				session.setAttribute("SESS_AUTH_CD", vo.getAuth() );
				session.setAttribute("SESS_USER_NM", vo.getName() );
				session.setAttribute("SESS_DEPT_CD", vo.getDept() );
				model.addAttribute("__AUTH__", vo); // Request Scope
				log.info("\nSESS_UESR_ID : {} ",session.getAttribute("SESS_UESR_ID"));
				log.info("\nSESS_AUTH_CD : {} ",session.getAttribute("SESS_AUTH_CD"));
				log.info("\nSESS_USER_NM : {} ",session.getAttribute("SESS_USER_NM"));
				log.info("\nSESS_DEPT_CD : {} ",session.getAttribute("SESS_DEPT_CD"));
				
				log.info("\ndto : {} ,model : {}", dto, model);
				return "redirect:/main";
			} else {
				model.addAttribute("__RESULT__", "로그인 실패.");
				return "/common/login"; // 다시 로그인 페이지로
			}
		} catch (Exception e) {
			throw new ControllerException(e);
		}
	} // post_login
	
	@PostMapping("/logout")
	public ResponseEntity<Integer> logout(HttpServletRequest req) throws ControllerException {
		log.info("logout ::::");
		
		try {
			HttpSession session = req.getSession(false);
			if(session !=null ) {
				log.info("로그아웃 성공");
				session.invalidate();
				return ResponseEntity.ok(1);
			} else {
				log.info("로그아웃 실패");
				return ResponseEntity.ok(0);
			}
			
		} catch (Exception e) {
			throw new ControllerException(e);
		}
		
	}
	
	@GetMapping("/join")
	public String join() {
		return "/common/join";
	}// get_join
	
//	@PostMapping("/join")
//	public Integer join(UserDTO dto) throws ControllerException {
//		log.info("\n 컨트롤러 회원가입 정보 : {} ",dto);
//		try {
//			return this.user.register(dto);
//		} catch (Exception e) {
//			throw new ControllerException(e);
//		}
//	}// post_join
	
	@PostMapping("/join")
	@ResponseBody
	public ResponseEntity<Integer> join(UserDTO dto) throws ControllerException {
	    log.info("\n 컨트롤러 회원가입 정보 : {} ", dto);
	    try {
	        Integer result = this.user.register(dto);
	        return ResponseEntity.ok(result);
	    } catch (Exception e) {
	        throw new ControllerException(e);
	    }
	}
	
	@GetMapping("/idcheck")
	@ResponseBody
	public Integer idcheck (@RequestParam("id") String id) throws ControllerException {
		log.info("\n 컨트롤러 id중복체크 : {} ",id);
		try {
			return this.user.idCheck(id);
		} catch (Exception e) {
			throw new ControllerException(e);
		}
	}// post_idcheck
}
