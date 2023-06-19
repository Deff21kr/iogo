package org.zerock.myapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.myapp.domain.UserDTO;
import org.zerock.myapp.domain.UserVO;
import org.zerock.myapp.exception.ControllerException;
import org.zerock.myapp.service.UserService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService user; 
	
	@GetMapping("/modify")
	public void modify () {
		log.info("modify::::::");
		
	}
	
	@PostMapping("/modify")
	public ResponseEntity<Integer> modify (UserDTO dto,HttpServletRequest req,Model model) throws ControllerException {
		log.info("\nmodify:::::: CONTROLLER\n{}",dto);
		
		try {
			if(this.user.modifyUser(dto) ==1 ) {
				log.info("\n회원정보수정 성공 ::: 컨트롤러");
				System.out.println(dto);
				
				HttpSession session = req.getSession(false);
				if(session != null) {
					session.invalidate();
				}
				session = req.getSession();
				UserVO vo = this.user.getUser(dto.getId());
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
				return ResponseEntity.ok(1);
			} else {
				log.info("\n회원정보수정 실패 ::: 컨트롤러");
				System.out.println(dto);
				return ResponseEntity.ok(0);
			}
			
		} catch (Exception e) {
			throw new ControllerException(e);
		}
		
	}
	

}
