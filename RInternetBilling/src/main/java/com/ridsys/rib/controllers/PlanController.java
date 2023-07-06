package com.ridsys.rib.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ridsys.rib.DTO.AllPlanListDTO;
import com.ridsys.rib.DTO.PlanCostDTO;
import com.ridsys.rib.DTO.PlanDTO;
import com.ridsys.rib.DTO.TicketSubjectTypeDTO;
import com.ridsys.rib.DTO.UserplanDetailDTO;
import com.ridsys.rib.models.Billing_plans;
import com.ridsys.rib.models.Quotaplan;
import com.ridsys.rib.payload.request.CreatePlanRequest;
import com.ridsys.rib.payload.request.createPlanStatus;
import com.ridsys.rib.repository.Billing_plansRepository;
import com.ridsys.rib.repository.QuotaplanRepository;
import com.ridsys.rib.repository.QuotasubplanRepository;
import com.ridsys.rib.service.PlanService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
//@RequestMapping("/crm/api/v1/plan")
@RequestMapping("/api/v1/plan")
public class PlanController {

	private PlanService service;

	@Autowired
	Billing_plansRepository billing_plansRepository;

	@Autowired
	QuotaplanRepository quotaplanRepository;

	public PlanController(PlanService service) {
		super();
		this.service = service;
	}

	@PostMapping("/create")
	public ResponseEntity<?> createUser(@Valid @RequestBody CreatePlanRequest requestobject) {
		return service.createPlan(requestobject);
	}

	@GetMapping("/list")
	public List<AllPlanListDTO> getAllPlanList(@RequestParam("username") String username,
			@RequestParam("role") String role,@RequestParam("client") String client) {
		return service.getAllPlanList(username, role,client);
	}

//	@GetMapping("/list")
//	public List<Billing_plans> getPlanList() {
//		return service.getPlanList();
//	}

	@GetMapping("/spinlist")
	public List<PlanCostDTO> getPlanListForSpinlist() {
		return billing_plansRepository.findAllActivePlansWithCostDetails();

	}

	@PostMapping("/ManagePlan")
	public ResponseEntity<?> managePackagePlans(@RequestBody createPlanStatus obj) {

		return service.managePackagePlan(obj.getPlanid(), obj.isStatus());
	}

	@GetMapping("/getPlan")
	public Quotaplan getPlanById(@Valid @RequestParam("planid") String planid) {
		return service.getPlanById(Integer.parseInt(planid));
	}

//	@GetMapping("/getPlan")
//	public Billing_plans getPlanById(@Valid @RequestParam("planid") String planid) {
//		return service.getPlanById(Integer.parseInt(planid));
//	}

	@PostMapping("/editPlan")
	public ResponseEntity<?> editPlan(@Valid @RequestBody Map<String, String> obj) {
		return service.editMainPlan(obj);
	}

	@GetMapping("/userPlanlist") // Billing_plans
	public UserplanDetailDTO getUserPlanList(@Valid @RequestParam("role") String role,
			@RequestParam("username") String username) {
		return service.getUserPlanList(username);

	}

	@GetMapping("/ottplan")
	public Map<String, Object> getOttplanList() {
		return service.getOttplanList();
	}

	@PostMapping("/createPlan")
	public ResponseEntity<?> createQuotaPlan(@Valid @RequestBody Map<String, String> obj) {
		return service.createQuotaplan(obj);
	}

	@PostMapping("/createSubPlan")
	public ResponseEntity<?> createQuotaSubPlan(@Valid @RequestBody Map<String, String> obj) {
		return service.createQuotaSubPlan(obj);
	}

	// dropdown list
	@GetMapping("/mainplan/list")
	public List<TicketSubjectTypeDTO> getMainplans() {
		return service.getMainplans();
	}

	@GetMapping("/subplan/list")
	public List<PlanDTO> getSubplanByPlanId(@Valid @RequestParam("username") String username,
			@RequestParam("planId") int planId) {
		return service.getSubplanByPlanId(username, planId);
	}

	@GetMapping("/mainplan")
	public Quotaplan getQuotaplanById(@Valid @RequestParam("planId") int planId) {
		return service.getQuotaplanById(planId);
	}

	@GetMapping("/planDetails")
	public Map<?, ?> getPlanDetails(@Valid @RequestParam("username") String username,@RequestParam("client") String client,@RequestParam("role") String role) {
		return service.getPlanDetails(username,role,client);
	}

	// plan and subplan active,deactive operatorwise
	@PostMapping("/manageplan")
	public ResponseEntity<?> managePlanForOperators(@Valid @RequestBody Map<String, String> obj) {
		return service.managePlanForOperators(obj);
	}

	@PostMapping("/edit/subplan")
	public ResponseEntity<?> editSubplanForOperator(@Valid @RequestBody Map<String, String> obj) {
		return service.editSubplanForOperator(obj);
	}

	@GetMapping("/mainplan/details")
	public List<AllPlanListDTO> getMainplanList() {
		return quotaplanRepository.getMainplanList();
	}

	@GetMapping("/active/subplan")
	public List<TicketSubjectTypeDTO> getActiveSubplanByOpid(@Valid @RequestParam("username") String username,
			@RequestParam("isonemonth") boolean isonemonth, @RequestParam("client") String client) {

		System.out.println(client);

		if (client.contains("RIDSYS")) {
			System.out.println("fistloop");
			return quotaplanRepository.getActiveSubplanByOpid(username, isonemonth);

		} else {
			System.out.println("secondloop");
			return quotaplanRepository.getActiveSubplanByOpid(username, false);

		}

	}

	@PostMapping("/planstatus/change")
	public ResponseEntity<?> setPlanStatus(@Valid @RequestBody Map<String, String> obj) {
		return service.setPlanStatus(obj);
	}

	@GetMapping("/planDetailsByPlanid") // Billing_plans
	public UserplanDetailDTO getplanDetailsByPlanid(@Valid @RequestParam("planid") int planid,
			@RequestParam("username") String username) {
		return service.getPlanDetailsByPlanid(planid, username);

	}

	@PostMapping("/createDuplicateplan")
	public ResponseEntity<?> createDuplicateplan(@Valid @RequestBody Map<String, String> obj) {
		return service.createDuplicatePlan(obj);
	}

	@GetMapping("/subplan/details")
	public List<PlanDTO> getSubplanById(@Valid @RequestParam("username") String username,
			@RequestParam("planId") int planId, @RequestParam("subplanId") int subplanId) {
		return service.getSubplanById(username, planId, subplanId);
	}

	@GetMapping("/subplanByPlanPrice")
	public List<TicketSubjectTypeDTO> getsubplanByPlanPrice(@Valid @RequestParam("username") String username,
			@RequestParam("isonemonth") boolean isonemonth, @RequestParam("client") String client) {
		
		

		System.out.println(client+"  ");
		if (client.contains("RIDSYS")) {
			System.out.println("fistloop");
			return service.getSubplanByUserPlanprice(username, isonemonth);

		} else {
			System.out.println("secondloop");
			return service.getSubplanByUserPlanprice(username, false);

		}
	}

}
