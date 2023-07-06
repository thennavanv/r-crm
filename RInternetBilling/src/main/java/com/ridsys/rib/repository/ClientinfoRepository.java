package com.ridsys.rib.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ridsys.rib.models.Clientinfo;

@Repository
public interface ClientinfoRepository extends JpaRepository<Clientinfo, Long> {

	@Query(value="SELECT * FROM clientinfo LIMIT 1",nativeQuery=true)
	Clientinfo findOneClient();
	
}
