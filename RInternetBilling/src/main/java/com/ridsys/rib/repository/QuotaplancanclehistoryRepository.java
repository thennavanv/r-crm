package com.ridsys.rib.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ridsys.rib.models.Quotaplancancelhistory;

public interface QuotaplancanclehistoryRepository extends JpaRepository<Quotaplancancelhistory, Long> {

	@Query(nativeQuery = true)
	List<Quotaplancancelhistory> getReportByUsernameAndftDate(String username, String fdate, String tdate);

	@Query(nativeQuery = true)
	List<Quotaplancancelhistory> getReportByOpernameAndftDate(String username, String fdate, String tdate);

	@Query(nativeQuery = true)
	List<Quotaplancancelhistory> getReportByAdminAndftDate(String fdate, String tdate);

	@Query(nativeQuery = true)
	List<Quotaplancancelhistory> getReportByUsername(String username);

	@Query(nativeQuery = true)
	List<Quotaplancancelhistory> getReportByOpername(String username);

	@Query(nativeQuery = true)
	List<Quotaplancancelhistory> getReportByAdmin();
	
}
