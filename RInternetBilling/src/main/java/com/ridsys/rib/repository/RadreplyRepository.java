package com.ridsys.rib.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ridsys.rib.models.Radreply;

@Repository
public interface RadreplyRepository extends JpaRepository<Radreply, Long> {

	@Transactional
	void deleteByAttributeAndUsername(String attribute, String username);
}
