package com.cos.dysson.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.dysson.dto.ReplySaveRequestDto;
import com.cos.dysson.dto.ResponseDto;
import com.cos.dysson.model.Boards;
import com.cos.dysson.service.BoardService;

@RestController
public class BoardApiController {
	@Autowired
	private BoardService boardService;
	
	//글 작성
	@PostMapping("/api/board")
	public ResponseDto<Integer> save(@RequestBody Boards board, @AuthenticationPrincipal com.cos.dysson.config.auth.PrincipalDetail principal) {
		boardService.관리자글작성(board, principal.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
		
	}
	
	//댓글 작성
	@PostMapping("/api/board/{boardsId}/reply")
	public ResponseDto<Integer> replySave(@RequestBody ReplySaveRequestDto replySaveRequestDto){
		boardService.댓글쓰기(replySaveRequestDto);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
}
