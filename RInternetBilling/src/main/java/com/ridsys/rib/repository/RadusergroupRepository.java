package com.ridsys.rib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ridsys.rib.models.Radusergroup;

@Repository
public interface RadusergroupRepository extends JpaRepository<Radusergroup, Long>{
	Boolean existsByUsername(String username);

	@Query(value = "DELETE FROM radusergroup WHERE username=:username", nativeQuery = true)
	void deleteByUsername(String username);

}
