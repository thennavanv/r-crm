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

import com.ridsys.rib.DTO.QuotaplanmanageloghistoryDTO;
import com.ridsys.rib.DTO.SubscriptionInfoDTO;
import com.ridsys.rib.models.Graceperiodextendhistory;
import com.ridsys.rib.models.Quotaplancancelhistory;
import com.ridsys.rib.models.Quotaplanmanagelog;
import com.ridsys.rib.payload.request.CancelInvoice;
import com.ridsys.rib.payload.request.QuatoplanactivationRequest;
import com.ridsys.rib.service.QuotaplanmanagingService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/quotaplanmanage")
public class QuotaPlanManageController {

	@Autowired
	QuotaplanmanagingService service;

	@PostMapping("/active")
	public ResponseEntity<?> quotaPlanActive(@Valid @RequestBody QuatoplanactivationRequest requestobject) {
		return service.quotaPlanActive2(requestobject);
	}

	@GetMapping("/history")
	public List<QuotaplanmanageloghistoryDTO> getQuotaPlanManageLogHistoryByRoleCreationbyusername(
			@RequestParam String role, @RequestParam String username, @RequestParam String fdate,
			@RequestParam String tdate) {
		return service.getQuotaPlanManageLogHistoryByRoleCreationbyusername(role, username, fdate, tdate);

	}

	@GetMapping("/history/list")
	public List<QuotaplanmanageloghistoryDTO> getQuotaPlanManageLogHistoryForOpAndUser(
			@RequestParam("role") String role, @RequestParam("username") String username,
			@RequestParam("fdate") String fdate, @RequestParam("tdate") String tdate) {
		System.out.println(role + " " + username + " " + fdate + " " + tdate);
		return service.getQuotaPlanManageLogHistoryForOpAndUser(role, username, fdate, tdate);

	}

	@GetMapping("/subscriptioninfo")
	public SubscriptionInfoDTO getSubscriptionInfoByRoleCreationbyusername(@RequestParam String role,
			@RequestParam String username) {
		return service.getSubscriptionInfoByRoleCreationbyusername(username);

	}

	@GetMapping("/susbcriberPackage")
	public List<Quotaplanmanagelog> getSubscriberPackageHistory(@Valid @RequestParam("username") String username,
			@RequestParam("role") String role) {
		return service.getSubscriberPackageHistory(username, role);
	}

	@PostMapping("/expirydate/extend")
	public ResponseEntity<?> expirydateExtend(@RequestBody Map<String, ?> obj) {
		return service.expirydateExtend(obj);
	}
	
	@PostMapping("/cancelinvoice")
	public ResponseEntity<?> cancelInvoice(@RequestBody CancelInvoice obj) {
		return service.cancelinvoice(obj);
	}
	
	@GetMapping("/GracePeriodExtendHistory")
	public List<Graceperiodextendhistory> getgraceplanextendhistory(@RequestParam String role, @RequestParam String username,
			@RequestParam String fdate,@RequestParam String tdate){
				return service.getgraceplanextendhistory(role,username,fdate,tdate);
	}

	@GetMapping("/quotaplancancelhistory")
	public List<Quotaplancancelhistory> getquotaplancancelhistory(@RequestParam String role, @RequestParam String username,
			@RequestParam String fdate,@RequestParam String tdate){
				return service.getquotaplancancelhistory(role,username,fdate,tdate);
	}
}
