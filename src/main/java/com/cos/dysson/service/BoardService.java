package com.cos.dysson.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.dysson.dto.ReplySaveRequestDto;
import com.cos.dysson.model.Boards;
import com.cos.dysson.model.Reply;
import com.cos.dysson.model.Users;
import com.cos.dysson.repository.BoardRepository;
import com.cos.dysson.repository.ReplyRepository;
import com.cos.dysson.repository.UserRepository;

@Service
public class BoardService {
	@Autowired
	private BoardRepository boardRepository;
	@Autowired
	private ReplyRepository replyRepository;
	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public void 글작성(Boards board, Users user) { //title,content
		board.setCount(0);
		board.setUsers(user);
		boardRepository.save(board);
	}
	@Transactional(readOnly = true)
	public Page<Boards> toNotice(Pageable pageable) { //글 목록
		return boardRepository.findAll(pageable);
	}
	@Transactional(readOnly = true)
	public Boards detail(int id) { // 글 상세보기
		return boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("글 상세보기 실패: 아이디를 찾을 수 없습니다.");
		});
	}
	
	@Transactional
	public void 글삭제하기(int id) {
		boardRepository.deleteById(id);
	}
	@Transactional
	public void 글수정하기(int id, Boards requestBoard) {
		Boards board = boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("글 찾기 실패: 아이디를 찾을 수 없습니다.");
		}); // 영속화 완료
		board.setTitle(requestBoard.getTitle());
		board.setContent(requestBoard.getContent());
	}
	
	
	
	
	
	@Transactional
	public void 댓글쓰기(ReplySaveRequestDto replySaveRequestDto) {
		Users user = userRepository.findById(replySaveRequestDto.getUsersId()).orElseThrow(()->{
			return new IllegalArgumentException("댓글 쓰기 실패: 유저 id를 찾을수 없습니다");
			
		});//영속화 완료
		
		Boards board = boardRepository.findById(replySaveRequestDto.getBoardsId()).orElseThrow(()->{
			return new IllegalArgumentException("댓글 쓰기 실패: 게시글 id를 찾을수 없습니다");
			
		});//영속화 완료
		Reply reply = Reply.builder()
				.users(user)
				.boards(board)
				.content(replySaveRequestDto.getContent())
				.build();
		replyRepository.save(reply);
				
		
	}
	
	
	@Transactional
	public void 댓글삭제(int replyId) {
		replyRepository.deleteById(replyId);
	}

	
}
