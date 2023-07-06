package com.ridsys.rib.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ridsys.rib.DTO.PlanDTO;
import com.ridsys.rib.DTO.QuotasubplanDTO;
import com.ridsys.rib.models.Quotasubplan;

public interface QuotasubplanRepository extends JpaRepository<Quotasubplan, Long> {

	boolean existsBySubplan(String subplan);

	boolean existsById(int id);

	Quotasubplan findById(int id);

	List<Quotasubplan> findByPlanid(int planid);

	@Query(nativeQuery = true)
	List<PlanDTO> getSubplanByPlanId(int planid);

	@Query(nativeQuery = true)
	List<PlanDTO> getSubplanByOpid(String username, int planid);

	@Query(nativeQuery = true)
	List<QuotasubplanDTO> getSubPlanForRidsys(int planid, String username,String role);
	
	@Query(nativeQuery = true)
	List<QuotasubplanDTO> getSubPlan(int planid, String username);

	@Query(nativeQuery = true, value = "SELECT id FROM quotasubplan ORDER BY id DESC LIMIT 1")
	int getLastSubPlanId();

	@Query(nativeQuery = true, value = "SELECT id FROM quotasubplan WHERE planId=:planid ORDER BY id DESC LIMIT 1")
	int getLastSubPlanIdByPlanId(int planid);

	@Query(nativeQuery = true, value = "UPDATE quotasubplan SET isactive=:status WHERE id=:planid")
	void setSubplanStatus(int planid, boolean status);

	@Query(nativeQuery = true)
	List<PlanDTO> getSubplanByIdAndOpid(int subplanid, int opid, int planid);
	
	@Query(nativeQuery = true)
	List<PlanDTO> getDuplicateSubplanByIdAndOpid(int subplanid, int opid, int planid);
	
	

}
