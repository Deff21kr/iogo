package org.zerock.myapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.myapp.domain.BoardDTO;
import org.zerock.myapp.domain.UserDTO;
import org.zerock.myapp.exception.ControllerException;
import org.zerock.myapp.service.AdminService;
import org.zerock.myapp.service.BoardService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/board/admin")
public class AdminController {
	
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
	
	@GetMapping("/get")
	public void adminboardGet (Model model, String bno) throws ControllerException {
		log.info("\nadminboardGet ::::: Controller\n");
		
		try {
			this.board.getBoard(bno);
			model.addAttribute("__GET__", this.board.getBoard(bno));
		} catch(Exception e) {
			throw new ControllerException(e);
		}
			
	}
	
	@ResponseBody
	@PostMapping(value= "/select",produces="application/json")
	public List<UserDTO> adminList (String dept) throws ControllerException {
		log.info("\nadminList ::::: Controller\n");
		System.out.println("dept : "+dept+" : 끝");
		try {
			String real="";
			if( dept.equals("운영부서") ) {
				real="A001";
			} else if ( dept.equals("지원부서")) {
				real = "B001";
			} else {
				real = "C001";
			}
			System.out.println("real = " + real);
			List<UserDTO> list = this.admin.getAdminList(real);
			System.out.println(list);
			return list;
		} catch(Exception e) {
			throw new ControllerException(e);
		}
		
	}
	
	@ResponseBody
	@PostMapping(value="/modify" , produces="application/json")
	public Integer adminModify (BoardDTO board) throws ControllerException {
		log.info("\nadminModify ::::: Controller\n");
		System.out.println("board : "+board);
		
		try {
			if(1==this.admin.modifySup(board)) {
				return 1;
			} else {
				return 0;
			}
		} catch(Exception e) {
			throw new ControllerException(e);
		}
		
	}
	
	
}
