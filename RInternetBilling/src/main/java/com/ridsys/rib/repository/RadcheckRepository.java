package com.ridsys.rib.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ridsys.rib.models.Radcheck;

@Repository
public interface RadcheckRepository extends JpaRepository<Radcheck, Long> {
	
	Boolean existsByUsername(String username);
	
	Radcheck findByUsernameAndAttribute(String username,String attribute);
	
	@Query(value = "SELECT COUNT(id) FROM radcheck WHERE attribute=:attribute AND username=:username", nativeQuery = true)
	long existsByUsernameAttribute(String username,String attribute);
	
	@Query(value = "DELETE FROM radcheck WHERE attribute=:attribute AND username=:username", nativeQuery = true)
	void deleteByUsernameAttribute(String username, String attribute);
}
