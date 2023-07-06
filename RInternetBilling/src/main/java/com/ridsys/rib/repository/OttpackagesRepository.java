package com.ridsys.rib.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ridsys.rib.DTO.TicketSubjectTypeDTO;
import com.ridsys.rib.models.Ottpackages;

@Repository
public interface OttpackagesRepository extends JpaRepository<Ottpackages, Long> {

	Boolean existsById(int id);
	
	Ottpackages findById(int id);
	
	Ottpackages findByPackagerate(double  rate);
	
	Boolean existsByPackagerate(double rate);
	
	@Query(nativeQuery = true)
	List<TicketSubjectTypeDTO> getOttPlans();
		
	Boolean existsByPackageid(int packageid);
	
	
	
	
}
