package com.ridsys.rib.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ridsys.rib.DTO.AllPlanListDTO;
import com.ridsys.rib.DTO.QuotaplanDTO;
import com.ridsys.rib.DTO.TicketSubjectTypeDTO;
import com.ridsys.rib.models.Quotaplan;

public interface QuotaplanRepository extends JpaRepository<Quotaplan, Long> {

	boolean existsByPlan(String plan);

	Quotaplan findById(int planid);

	List<Quotaplan> findAllByIsactiveTrue();

	@Query(nativeQuery = true)
	List<TicketSubjectTypeDTO> getMainplans();

	@Query(nativeQuery = true)
	List<QuotaplanDTO> getMainPlan(String username);

	@Query(nativeQuery = true, value = "SELECT id FROM quotaplan ORDER BY id DESC LIMIT 1")
	int getLasPlanId();

	@Query(nativeQuery = true)
	List<AllPlanListDTO> getAllPlanList();

	@Query(nativeQuery = true)
	List<AllPlanListDTO> getAllPlanListByOpid(String username);
	
	@Query(nativeQuery = true)
	List<AllPlanListDTO> getAllPlanListByOpidForRnet(String username);

	@Query(nativeQuery = true)
	List<AllPlanListDTO> getMainplanList();

	@Query(nativeQuery = true)
	List<TicketSubjectTypeDTO> getActiveSubplanByOpid(String username, boolean isonemonth);

	@Query(nativeQuery = true, value = "UPDATE quotaplan SET isactive=:status where id=:planid")
	void setMainplanStatus(int planid, boolean status);

	@Query(nativeQuery = true)
	List<TicketSubjectTypeDTO> getSubplanByUserPlanprice(String username, int opid, boolean isonemonth);

}
