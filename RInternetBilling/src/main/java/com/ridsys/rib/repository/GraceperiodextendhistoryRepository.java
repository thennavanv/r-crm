package com.ridsys.rib.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ridsys.rib.models.Graceperiodextendhistory;

@Repository
public interface GraceperiodextendhistoryRepository extends JpaRepository<Graceperiodextendhistory , Long>{

	@Query(nativeQuery = true)
	List<Graceperiodextendhistory> getReportByUsernameAndftDate(String username, String fdate, String tdate);

	@Query(nativeQuery = true)
	List<Graceperiodextendhistory> getReportByOpernameAndftDate(String username, String fdate, String tdate);

	@Query(nativeQuery = true)
	List<Graceperiodextendhistory> getReportByAdminAndftDate(String fdate, String tdate);

	@Query(nativeQuery = true)
	List<Graceperiodextendhistory> getReportByUsername(String username);

	@Query(nativeQuery = true)
	List<Graceperiodextendhistory> getReportByOpername(String username);

	@Query(nativeQuery = true)
	List<Graceperiodextendhistory> getReportByAdmin();
}
