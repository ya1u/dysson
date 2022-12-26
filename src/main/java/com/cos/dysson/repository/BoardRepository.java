package com.cos.dysson.repository;
 
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.dysson.model.Boards;


public interface BoardRepository extends JpaRepository<Boards,Integer>{
	 Page<Boards> findByTitleContaining(String searchKeyword, Pageable pageable);
}
