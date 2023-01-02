package com.cos.dysson.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
		boardService.글작성(board, principal.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
		
	}
	
	//게시글 삭제
	@DeleteMapping("/api/board/{id}")
	public ResponseDto<Integer> deleteById(@PathVariable int id){
		System.out.println(id);
		boardService.글삭제하기(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
//	//댓글 삭제
	@DeleteMapping("/api/board/{boardId}/reply/{replyId}")
	public ResponseDto<Integer> replyDelete(@PathVariable int replyId){
		boardService.댓글삭제(replyId);

		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	
	//글수정
	@PutMapping("/api/board/{id}")
	public ResponseDto<Integer> update(@PathVariable int id,@RequestBody Boards board){
		System.out.println("BoardApiController:update id"+id);
		System.out.println("BoardApiController:update title"+board.getTitle());
		boardService.글수정하기(id,board);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	//댓글 작성
	@PostMapping("/api/board/{boardsId}/reply")
	public ResponseDto<Integer> replySave(@RequestBody ReplySaveRequestDto replySaveRequestDto){
		
		boardService.댓글쓰기(replySaveRequestDto);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	
}
