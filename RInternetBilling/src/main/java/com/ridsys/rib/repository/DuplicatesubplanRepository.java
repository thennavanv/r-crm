package com.ridsys.rib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ridsys.rib.models.Duplicatesubplan;

@Repository
public interface DuplicatesubplanRepository extends JpaRepository<Duplicatesubplan, Long>  {

	
	Duplicatesubplan findBySubplanidAndOpid(int subplanid, int opid);
	
	boolean existsBySubplanidAndOpid(int subplanid,int opid);
	
	
}
