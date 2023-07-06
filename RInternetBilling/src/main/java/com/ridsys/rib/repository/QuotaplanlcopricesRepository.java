package com.ridsys.rib.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ridsys.rib.DTO.UserplanDetailDTO;
import com.ridsys.rib.models.Quotaplanlcoprices;

public interface QuotaplanlcopricesRepository extends JpaRepository<Quotaplanlcoprices, Long> {

	Quotaplanlcoprices findBySubplanidAndOpid(int subplanid, int opid);

	boolean existsBySubplanidAndOpid(int subplanid, int opid);

	@Query(nativeQuery = true)
	UserplanDetailDTO getUserPlanList(int opid, String username);

	List<Quotaplanlcoprices> findBySubplanid(int subplanid);

	@Query(nativeQuery = true)
	UserplanDetailDTO getPlanDetailsByPlanid(int opid, int planid);

	@Query(nativeQuery = true)
	UserplanDetailDTO getUserDuplicatePlanList(int opid, String username);

	@Query(nativeQuery = true)
	UserplanDetailDTO getDuplicatePlanDetailsByPlanid(int opid, int planid);
}
