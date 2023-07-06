package com.ridsys.rib.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ridsys.rib.DTO.PlanCostDTO;
import com.ridsys.rib.models.Billing_plans;

@Repository
public interface Billing_plansRepository extends JpaRepository<Billing_plans, Long> {

	Billing_plans findById(int id);

	Boolean existsByPlanname(String planname);

	Boolean existsByPlanid(String planid);

	Boolean existsById(int id);

	@Query(nativeQuery = true)
	PlanCostDTO findCostDetailsById(String id);

	@Query(value = "SELECT id,planName,planId,planType,planTimeBank,planTimeType,planTimeRefillCost,planBandwidthUp,planBandwidthDown,planTrafficTotal,planTrafficUp,planTrafficDown,planTrafficRefillCost,planRecurring,planRecurringPeriod,planRecurringBillingSchedule,ROUND(planCost) AS planCost,planSetupCost,planTax,planLcoComm,planCurrency,planGroup,planActive,creationdate,creationby,updatedate,updateby,isdeleted,isactive,if(lco_commission_per is null,0,lco_commission_per)as lco_commission_per,planMrp,planDiscount,planCusCost,planMsoCost,planDiscountDays FROM billing_plans WHERE isdeleted=0", nativeQuery = true)
//	@Query(value="select*From billing_plans where isdeleted=0",nativeQuery=true)
	List<Billing_plans> findAllActivePlans();

	@Query(nativeQuery = true)
	List<PlanCostDTO> findAllActivePlansWithCostDetails();

	@Query(value = "SELECT COUNT(id) FROM billing_plans WHERE planName=:planName AND id!=:planid", nativeQuery = true)
	Integer checkPlanName(String planName, int planid);

	@Query(value = "SELECT * FROM billing_plans bp LEFT JOIN userbillinfo ubi ON ubi.planid=bp.id WHERE ubi.username=:username", nativeQuery = true)
	Billing_plans getUserPlanList(String username);
	
	
	
	
}
