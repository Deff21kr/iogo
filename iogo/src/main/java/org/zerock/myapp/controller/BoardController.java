package org.zerock.myapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.myapp.domain.SearchDTO;
import org.zerock.myapp.exception.ControllerException;
import org.zerock.myapp.service.BoardService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService board;

	
	@GetMapping("/list")
	public void boardList (Model model,SearchDTO search ,RedirectAttributes rttrs) throws ControllerException {
		log.info("\nboardList ::::: Controller\nsearch : {} ",search);
		Boolean status = (search.getStatus() != null )&&(search.getStatus()!="");
		Boolean title = (search.getTitle() != null )&&(search.getTitle()!="");
		log.info("\nstatus : {}\ntitle : {}",status,title );
		
		try {
			if(status || title ) {
				model.addAttribute("__LIST__",this.board.getSearch(search));
				rttrs.addAttribute("title", search.getTitle());
				rttrs.addAttribute("status", search.getStatus());
			} else {
				model.addAttribute("__LIST__",this.board.getList());
			}
				
			
		} catch (Exception e) {
			throw new ControllerException(e);
		}
	}
	
}
