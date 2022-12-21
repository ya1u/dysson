package com.cos.dysson.controller;

import java.util.List;

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
import com.cos.dysson.model.Boards;
import com.cos.dysson.service.BoardService;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	
//	@GetMapping("/auth/support")
//	public String board() {
//		return "board/board";
//	}
	
	//공지 수정하기
	@GetMapping("/board/{id}/updateForm")
	public String updateForm(@PathVariable int id, Model model) {
		model.addAttribute("board",boardService.noticeDetail(id));
		return "board/updateForm";
	}
	
	@GetMapping({"","/"})
	public String index(@AuthenticationPrincipal PrincipalDetail principal) {
		return "index";
	}
	
	@GetMapping({"/board/saveForm"})
	public String saveForm() { //작성페이지
		return "board/saveForm";
	}
	//글 상세보기
	@GetMapping("/board/{id}")
	public String findById(@PathVariable int id, Model model) {
		model.addAttribute("board",boardService.noticeDetail(id));
		return "board/detail";
	}
	//게시글 페이징
	@GetMapping("/auth/support")
	public String index(Model model,@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) { 
		model.addAttribute("boards",boardService.toNotice(pageable));
		return "board/board";
	}
	@GetMapping("/auth/support/askBoard")
	public String indexAsk() {
		return "board/askBoard";
	}
			
	//검색기능


	
	
}
