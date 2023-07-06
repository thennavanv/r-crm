package com.ridsys.rib.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ridsys.rib.models.Renewalhistory;

@Repository
public interface RenewalhistoryRepository extends JpaRepository<Renewalhistory, Long> {

	@Query(value = "SELECT * FROM renewalhistory WHERE DATE(newstartdate)=CURDATE() +INTERVAL+ 1 DAY AND isupdate=false", nativeQuery = true)
	List<Renewalhistory> getTrwFubStartDate();

	@Transactional
	@Query(value = "DELETE FROM renewalhistory WHERE username=:username AND DATE(creationdate)>=:creationdate", nativeQuery = true)
	void deleteByDateForCancelInvoice(String username, String creationdate);
}
