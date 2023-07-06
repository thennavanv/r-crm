package com.ridsys.rib.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ridsys.rib.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByUsername(String username);
	
//	User findByUsername1(String username);
	
	Boolean existsByUsername(String username);
	
	Boolean existsByEmail(String email);
}