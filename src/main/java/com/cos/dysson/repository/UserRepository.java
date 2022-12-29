package com.cos.dysson.repository;

import com.cos.dysson.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,Integer> , JpaSpecificationExecutor<Users> {
	Optional<Users> findByUsername(String username);

	boolean existsByUsername(String username);
}
