package com.ridsys.rib.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ridsys.rib.models.Smshistory;

@Repository
public interface SmshistoryRepository extends JpaRepository<Smshistory, Long> {

	@Query(nativeQuery = true)
	List<Smshistory> getSmsHistoryByAll();
	
	@Query(nativeQuery = true)
	List<Smshistory> getSmsHistoryByStatusAll(String status);

	@Query(nativeQuery = true)
	List<Smshistory> getSmsHistoryByOpid(String username);
	
	@Query(nativeQuery = true)
	List<Smshistory> getSmsHistoryByOpidAndStatus(String username,String status);
	
	@Query(nativeQuery = true)
	List<Smshistory> getSMSbyMobile(String mobileno);
}
