package com.ridsys.rib.controllers;

import java.util.List;

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

import com.ridsys.rib.DTO.OfflinePaymentDTO;
import com.ridsys.rib.DTO.SpinListDTO;
import com.ridsys.rib.models.Offline_payment_log;
import com.ridsys.rib.payload.request.OfflinePayment;
import com.ridsys.rib.repository.Payment_typeRepository;
import com.ridsys.rib.service.OfflinePaymentService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/offlinepayment")
public class OfflinePaymentController {

	@Autowired
	private Payment_typeRepository service;

	@Autowired
	private OfflinePaymentService offlinePaymentService;

	@GetMapping("/paymenttype/spinlist")
	public List<SpinListDTO> getSpinList() {
		return service.getSpinList();
	}

	@PostMapping("/walletrecharge")
	public ResponseEntity<?> paymentRequest(@Valid @RequestBody OfflinePayment requestbody) {
		return offlinePaymentService.operatorPaymentRequest(requestbody);
	}

	@GetMapping("/Offlinerechargelist")
	public List<Offline_payment_log> getOfflineRechargeList() {
		return offlinePaymentService.getOfflineRechargeList();
	}

	@GetMapping("/offlinerecharge/list")
	public List<OfflinePaymentDTO> offlineRechargeList(@Valid @RequestParam("username") String username,
			@RequestParam("fdate") String fdate, @RequestParam("tdate") String tdate,
			@RequestParam("role") String role) {

		return offlinePaymentService.offlineRechargeList(username, role, fdate, tdate);
	}

}
