package com.cos.dysson.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.cos.dysson.config.auth.PrincipalDetail;
import com.cos.dysson.repository.BoardRepository;
import com.cos.dysson.service.BoardService;
import com.cos.dysson.service.UserService;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private BoardRepository boardRepository;

//	@GetMapping("/auth/support")
//	public String board() {
//		return "board/board";
//	}
	
	//공지 수정하기
	@GetMapping("/board/{id}/updateForm")
	public String updateForm(@PathVariable int id, Model model) {
		model.addAttribute("board",boardService.detail(id));
		return "board/updateForm";
	}
	//문의 수정하기
	@GetMapping("/board/{id}/askUpdateForm")
	public String askUpdateForm(@PathVariable int id, Model model) {
		model.addAttribute("board",boardService.detail(id));
		return "board/askUpdateForm";
	}
	
	@GetMapping({"","/"})
	public String index(@AuthenticationPrincipal PrincipalDetail principal, HttpSession httpSession) {
		if(principal!=null) {
			httpSession.setAttribute("user", userService.findUser(principal.getUser().getId()));
		}
		return "index";
	}

	//공지작성페이지
	@GetMapping({"/board/saveForm"})
	public String saveForm() { 
		return "board/saveForm";
	}
	//문의작성페이지
	@GetMapping({"/board/askSaveForm"})
	public String askSaveForm() { 
		return "board/askSaveForm";
	}
//	글 상세보기
	@GetMapping("/board/{id}")
	public String findById(@PathVariable int id, Model model) {
		model.addAttribute("board",boardService.detail(id));
		return "board/detail";
	}
	
	//문의글 상세보기
	@GetMapping("/askBoard/{id}")
	public String findById2(@PathVariable int id, Model model) {
		model.addAttribute("board",boardService.detail(id));
		return "board/askDetail";
	}
	
	//공지 페이징
	@GetMapping("/auth/support")
	public String notice(Model model,@PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
			@RequestParam(value = "searchKeyword", required = false) String searchKeyword) { 
//		model.addAttribute("boards",boardService.toNotice(pageable));
		
	    if(searchKeyword == null)
	        model.addAttribute("boards", boardService.toNotice(pageable));
	    else {
	        model.addAttribute("boards", boardRepository.findByTitleContaining(searchKeyword, pageable));
	    }
	    
	    return "board/board";
		
	}
	
	
	//1대1문의 페이징
	@GetMapping("/auth/support/askBoard")
	public String ask(Model model,@PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
			@RequestParam(value = "searchKeyword", required = false) String searchKeyword) { 
		
	    if(searchKeyword == null)
	        model.addAttribute("boards", boardService.toNotice(pageable));
	    else {
	        model.addAttribute("boards", boardRepository.findByTitleContaining(searchKeyword, pageable));
	    }
	    
		return "board/askBoard";
	}

			
	


	
	
}
