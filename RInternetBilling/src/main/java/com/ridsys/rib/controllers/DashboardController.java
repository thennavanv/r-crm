package com.ridsys.rib.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.ObjectIdGenerators.StringIdGenerator;
import com.ridsys.rib.DTO.DashboardDTO;
import com.ridsys.rib.DTO.PlanDetailDTO;
import com.ridsys.rib.DTO.UserDetailsDTO;
import com.ridsys.rib.comm.General;
import com.ridsys.rib.comm.IptvApi;
import com.ridsys.rib.models.Clientinfo;
import com.ridsys.rib.service.DashboardService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/dashboard")
public class DashboardController {

	@Autowired
	DashboardService service;

	@GetMapping("/getdata")
	public DashboardDTO getOfflineWalletupdatelog(@Valid @RequestParam("role") String role,
			@RequestParam("username") String username) {
		if (username != "" && role != "") {
			return service.getDashboardDatas(role, username);
		}
		return null;

	}

	@GetMapping("/expired")
	public Map<String, Long> getExpiredStatus(@Valid @RequestParam("username") String username,
			@RequestParam("role") String role) {
		return service.getExpiredStatus(username, role);
	}

	@GetMapping("/sorttable/list")
	public List<UserDetailsDTO> getShortableUserlist(@Valid @RequestParam("username") String username,
			@RequestParam("role") String role, @RequestParam("title") String statusname,
			@RequestParam("count") int count, @RequestParam("subject") String subject) {
		return service.getShortableUserlist(username, role, statusname, count, subject);
	}

	@GetMapping("/planWiseCount")
	public List<PlanDetailDTO> getPlanwiseCustomercount(@Valid @RequestParam("username") String username,
			@RequestParam("role") String role, @RequestParam("sts") String sts) {
		return service.getPlanwiseCustomercount(username, role, sts);
	}

	@GetMapping("/MaxDataUsage")
	public List<General> getMaxDataUsers(@Valid @RequestParam("username") String username,
			@RequestParam("role") String role) {
		return service.getMaxDataUsers(username, role);

	}

	@GetMapping("/getDatewiseCount")
	public Map<String, Long> getDatewiseCustomerCount(@Valid @RequestParam("username") String username,
			@RequestParam("role") String role, @RequestParam("day") String day) {
		return service.getDatewiseCustomerCount(username, role, day);
	}

	@GetMapping("/userCreated/count")
	public Map<String, Long> getUserCreatedCount(@Valid @RequestParam("username") String username,
			@RequestParam("role") String role) {
		return service.getUserCreatedCount(username, role);
	}

	@GetMapping("/searchUsername")
	public List<String> getSearchContents(@RequestParam("username") String username, @RequestParam("role") String role) {
		return service.getSearchContents(username, role);
	}
	
	@GetMapping("/getClientinfo")
	public Clientinfo getClientinfo() {
		return service.getClientinfo();
	}

}
