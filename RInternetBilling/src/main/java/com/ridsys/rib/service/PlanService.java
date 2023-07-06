package com.ridsys.rib.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.ridsys.rib.DTO.AllPlanListDTO;
import com.ridsys.rib.DTO.PlanDTO;
import com.ridsys.rib.DTO.TicketSubjectTypeDTO;
import com.ridsys.rib.DTO.UserplanDetailDTO;
import com.ridsys.rib.models.Billing_plans;
import com.ridsys.rib.models.Quotaplan;
import com.ridsys.rib.payload.request.CreatePlanRequest;

public interface PlanService {

	ResponseEntity<?> createPlan(CreatePlanRequest obj);

	List<Billing_plans> getPlanList();

	ResponseEntity<?> managePackagePlan(int planId, boolean sts);

	Quotaplan getPlanById(int planid);

	ResponseEntity<?> editMainPlan(Map<String, String> obj);

	UserplanDetailDTO getUserPlanList(String username);

	Map<String, Object> getOttplanList();

	ResponseEntity<?> createQuotaplan(Map<String, String> obj);

	ResponseEntity<?> createQuotaSubPlan(Map<String, String> obj);

	List<TicketSubjectTypeDTO> getMainplans();

	List<PlanDTO> getSubplanByPlanId(String username, int planId);

	Quotaplan getQuotaplanById(int planid);

	Map<?, ?> getPlanDetails(String username,String role,String client);

	ResponseEntity<?> managePlanForOperators(Map<String, String> obj);

	ResponseEntity<?> editSubplanForOperator(Map<String, String> obj);

	ResponseEntity<?> setPlanStatus(Map<String, String> obj);

	List<AllPlanListDTO> getAllPlanList(String username, String role,String client);

	UserplanDetailDTO getPlanDetailsByPlanid(int planid, String username);

	ResponseEntity<?> createDuplicatePlan(Map<String, String> obj);

	List<PlanDTO> getSubplanById(String username, int planId, int subplanId);

	List<TicketSubjectTypeDTO> getSubplanByUserPlanprice(String usermame, boolean isonemonth);
}
