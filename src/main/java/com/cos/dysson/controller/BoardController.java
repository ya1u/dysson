package com.cos.dysson.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {
	@GetMapping("/auth/support")
	public String board() {
		return "board/board";
	}
	
	@GetMapping({"/board/saveForm"})
	public String saveForm() { 
		return "board/saveForm";
	}
	
	
}
