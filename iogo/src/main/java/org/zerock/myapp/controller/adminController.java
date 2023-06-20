package org.zerock.myapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.myapp.exception.ControllerException;
import org.zerock.myapp.service.AdminService;
import org.zerock.myapp.service.BoardService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/board/admin")
public class adminController {
	
	@Autowired
	private AdminService admin;
	@Autowired
	private BoardService board;

	
	@GetMapping(value= "/list")
	public void adminboardList (Model model,String dept,RedirectAttributes rttrs) throws ControllerException {
		log.info("\nadminboardList ::::: Controller\n");
		System.out.println("dept : " + dept);
		try {
			if(dept !=null ) {
				log.info("\nadmin 모델");
				model.addAttribute("__ADMIN__",this.admin.getList(dept));
			} else {
				log.info("\nboard 모델");
				model.addAttribute("__ADMIN__", this.board.getList());
			}
			
		} catch (Exception e) {
			throw new ControllerException(e);
		}
	}
	
	
}
