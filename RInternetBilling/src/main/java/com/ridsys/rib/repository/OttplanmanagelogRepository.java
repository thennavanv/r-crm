package com.ridsys.rib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ridsys.rib.DTO.SpinListDTO;
import com.ridsys.rib.models.Ottplanmanagelog;

@Repository
public interface OttplanmanagelogRepository extends JpaRepository<Ottplanmanagelog, Long> {
	
	@Query(nativeQuery=true)
	SpinListDTO getPlanDetailByUser(String username);
	
	@Query(value="SELECT * FROM ottplanmanagelog WHERE username=:username ORDER BY id DESC LIMIT 1",nativeQuery=true)
	Ottplanmanagelog getActivePlanDetailByUsername(String username);

}
