package com.ridsys.rib.controllers;

import java.util.List;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ridsys.rib.models.Smshistory;
import com.ridsys.rib.payload.request.SmsRequest;
import com.ridsys.rib.payload.request.UserRegisterVerificationRequest;
import com.ridsys.rib.service.SMSService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class SMSController {

	private SMSService service;

	public SMSController(SMSService service) {
		super();
		this.service = service;
	}

	@PostMapping("/SendSMS")
	public ResponseEntity<?> createUser(@Valid @RequestBody UserRegisterVerificationRequest requestobject) {
		return service.sendSMS(requestobject);
	}

	@PostMapping("/send/SMS")
	public ResponseEntity<?> sendBulkSms(@Valid @RequestBody SmsRequest obj) {
		return service.sendBulkSms(obj);
	}

	@GetMapping("/SMS/history")
	public List<Smshistory> GetSMShistory(@Valid @RequestParam("username") String username,
			@RequestParam("role") String role, @RequestParam("status") String status) {
		return service.getSMShistory(username, role, status);
	}

	@GetMapping("/search")
	public List<Smshistory> getSMSbyMobile(@RequestParam("mobileno") String mobileno) {
		return service.getSMSbyMobile(mobileno);
	}

}
