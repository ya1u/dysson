package com.cos.dysson.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cos.dysson.model.Reply;

public interface ReplyRepository extends JpaRepository<Reply,Integer>{
	
	//네이티브쿼리 사용
	@Modifying
	@Query(value="INSERT INTO reply(usersId,boardsId,content,createDate) VALUES(?1,?2,?3,to_date(sysdate,'yyyy-mm-dd'))", nativeQuery=true)
	int mSave(int usersId, int boardsId, String content); //업데이트된 행의 개수를 리턴해줌
}
