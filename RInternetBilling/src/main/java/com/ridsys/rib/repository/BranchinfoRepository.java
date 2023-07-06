package com.ridsys.rib.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ridsys.rib.models.Branchinfo;

public interface BranchinfoRepository extends JpaRepository<Branchinfo, Long>{
	
	List<Branchinfo> findByIsactive(boolean isactive);
	
	Boolean existsByBranchname(String branchname);

}
