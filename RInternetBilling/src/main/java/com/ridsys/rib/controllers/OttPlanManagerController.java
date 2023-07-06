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

import com.ridsys.rib.DTO.TicketSubjectTypeDTO;
import com.ridsys.rib.models.Ottpackages;
import com.ridsys.rib.models.Ottuserinfo;
import com.ridsys.rib.payload.request.OttplanactivationRequest;
import com.ridsys.rib.service.OttplanmanagelogService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/ottplan")
public class OttPlanManagerController {

	@Autowired
	OttplanmanagelogService service;

	@PostMapping("/active")
	public ResponseEntity<?> activeOttPlan(@Valid @RequestBody OttplanactivationRequest reqobj) {

		return service.activeOttPlan1(reqobj);
	}

	@PostMapping("/deactive")
	public ResponseEntity<?> deactiveOttPlan(@Valid @RequestBody Map<String, Object> requestbody) {

		return service.deactiveOttPlan(requestbody.get("username").toString(),
				requestbody.get("creationbyrole").toString(), requestbody.get("creationbyusername").toString());
	}

	@GetMapping("/getPlanDetail")
	public Map<String, Object> getPlanDetailsByUser(@Valid @RequestParam("username") String username) {
		return service.getPlanDetailsByUser(username);
	}

	@GetMapping("/ottPackageList")
	public List<Ottpackages> getOttpackageList() {
		return service.getOttpackageList();
	}

	@GetMapping("/ottPlanList")
	public List<TicketSubjectTypeDTO> getOttPlanList() {
		return service.getOttPlanList();
	}

	@GetMapping("/GetSubscriptionDetails")
	public Ottuserinfo GetSubscriptionDetails(@Valid @RequestParam String mobile_no) {
		return service.getSubsDetails(mobile_no);
	}

}
