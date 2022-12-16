package com.cos.dysson.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.dysson.model.Users;

public interface UserRepository extends JpaRepository<Users,Integer> {
	Optional<Users> findByUsername(String username);
 
	boolean existsByUsername(String username);
}
