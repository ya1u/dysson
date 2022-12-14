package com.cos.dysson.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.dysson.dto.ReplySaveRequestDto;
import com.cos.dysson.model.Boards;
import com.cos.dysson.model.Users;
import com.cos.dysson.repository.BoardRepository;
import com.cos.dysson.repository.ReplyRepository;

@Service
public class BoardService {
	@Autowired
	private BoardRepository boardRepository;
	@Autowired
	private ReplyRepository replyRepository;
	
	
	@Transactional
	public void 관리자글작성(Boards board, Users user) { //title,content
		board.setCount(0);
		board.setUsers(user);
		boardRepository.save(board);
	}
	@Transactional(readOnly = true)
	public Page<Boards> toNotice(Pageable pageable) { //공지글 목록
		return boardRepository.findAll(pageable);
	}
	@Transactional(readOnly = true)
	public Boards noticeDetail(int id) { // 공지글 상세보기
		return boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("글 상세보기 실패: 아이디를 찾을 수 없습니다.");
		});
	}
	
	@Transactional
	public void 댓글쓰기(ReplySaveRequestDto replySaveRequestDto) {
		replyRepository.mSave(replySaveRequestDto.getUsersId(),replySaveRequestDto.getBoardsId(),replySaveRequestDto.getContent());
				
		
	}
}
