
package com.ridsys.rib.controllers;

import java.util.HashMap;
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

import com.ridsys.rib.DTO.OnlineRechargelistDTO;
import com.ridsys.rib.DTO.OnlinepaymentDTO;
import com.ridsys.rib.PaymentGateway.RazorPay.OrderCreate;
import com.ridsys.rib.models.Online_payment_log;
import com.ridsys.rib.payload.request.PaymentRequest;
import com.ridsys.rib.payload.request.QuatoplanactivationRequest;
import com.ridsys.rib.repository.Online_payment_logRepository;
import com.ridsys.rib.service.OnlinePaymentService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/onlinepayment")
public class OnlinePaymentController {

	@Autowired
	OnlinePaymentService onlinePaymentService;

	@Autowired
	Online_payment_logRepository OnlinePaymentLogRepository;

	@PostMapping("/request")
	public ResponseEntity<?> paymentRequest(@Valid @RequestBody PaymentRequest requestbody) {

		/// AGREEPAY//////////////////////////////////////////////////
//		return onlinePaymentService.operatorPaymentRequest1(requestbody);

		return onlinePaymentService.paymentRequest(requestbody);
	}

	@GetMapping("/checkTransCompleted")
	public Map<String, String> checkTransCompleted(@RequestParam String order_id) {

		int status = 1;
		int time = 0;
		while (status > 0 && time < 180000) {
			status = OnlinePaymentLogRepository.paymentStatusCheckByOrderId(order_id);
			try {
				time += 5000;
				Thread.sleep(5000);
			} catch (InterruptedException e) {
			}
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("status", "completed");
		return map;
	}

	@PostMapping("/response")
	public void paymentResponse(@RequestParam Map<String, String> reqParam) {
		System.out.print(reqParam.toString());
	}

	@GetMapping("/Onlinerechargelist")
	public List<Online_payment_log> getOnlineRechargeHistory(@Valid @RequestParam("username") String username,
			@RequestParam("role") String role, @RequestParam("subrole") String subrole,
			@RequestParam("fdate") String fdate, @RequestParam("tdate") String tdate) {
		return onlinePaymentService.getOnlineRechargeHistory(username, role, subrole, fdate, tdate);
	}

	@GetMapping("/OnlinePaymentLog")
	public List<OnlinepaymentDTO> getAllOnlinePaymentLog(@RequestParam("username") String username,
			@RequestParam("role") String role, @RequestParam("fdate") String fdate,
			@RequestParam("tdate") String tdate) {
		return onlinePaymentService.getAllOnlinePaymentLog(fdate, tdate, username, role);
	}

	@PostMapping("/walletRecharge/paymentOrderCreate")
	public ResponseEntity<?> paymentOrderCreate(@RequestBody OrderCreate obj) {
		return onlinePaymentService.paymentOrderCreate(obj);
	}

	@PostMapping("/walletRecharge/paymentResponse/success")
	public ResponseEntity<?> paymentResponseSuccess(@RequestBody Map<String, Object> obj) {
		return onlinePaymentService.paymentResponseSuccess(obj);
	}

	@PostMapping("/walletRecharge/paymentResponse/error")
	public ResponseEntity<?> paymentResponseError(@RequestBody Map<String, Object> obj) {
		return onlinePaymentService.paymentResponseError(obj);
	}

	////// RAZOR PAU///////////////////////////////////////
	@PostMapping("/plan/onlinePayment/createdOrder")
	public ResponseEntity<?> planPaymentOrderCreation(@RequestBody PaymentRequest reqobj) {
		return onlinePaymentService.planPaymentOrderCreation(reqobj);
	}

	@PostMapping("/plan/onlinePayment/success")
	public ResponseEntity<?> planPaymenSuccess(@RequestBody Map<String, String> reqobj) {
		return onlinePaymentService.planPaymenSuccess(reqobj);
	}

	@PostMapping("/plan/onlinePayment/failed")
	public ResponseEntity<?> planPaymentFailed(@RequestBody Map<String, Object> reqobj) {
		return onlinePaymentService.planPaymenFailed(reqobj);
	}

	//////////////////////////////////////////////////////

	@GetMapping("/paymentstatus")
	public ResponseEntity<?> getAggrePaymentStatus(@RequestParam String orderid, @RequestParam String type) {
		return onlinePaymentService.getAggrePaymentStatus(orderid, type);
//		 onlinePaymentService.getAggrePaymentStatus("WOPWDwhTCMAIiCETxpysr8aOy82H");

	}

	@GetMapping("/Online/RechargeHistory")
	public List<OnlineRechargelistDTO> getOnlineUserRechargeHistory(@RequestParam("username") String username,
			@RequestParam("fdate") String fdate, @RequestParam("tdate") String tdate,
			@RequestParam("role") String role) {
		return onlinePaymentService.getOnlineUserRechargeHistory(username, fdate, tdate, role);
	}

	///////////////////////// easebuzz//////////////////////////////////////////////////////
	@PostMapping("/eazepayment/request")
	public ResponseEntity<?> eazeBuzzOrderCreate(@Valid @RequestBody PaymentRequest requestbody) {
		return onlinePaymentService.eazeBuzzOrderCreate(requestbody);
	}

//	@PostMapping("/eazepayment/success")
//	public String eazeBuzzSuccess(@Valid @RequestParam("txnid") String txnid, @RequestParam("status") String status,
//			@RequestParam("easepayid") String easepayid, @RequestParam("hash") String hash) {
//		return onlinePaymentService.eazeBuzzSuccess(txnid, status, easepayid, hash);
//
//	}

	@PostMapping("/eazepayment/success")
	public ResponseEntity<?> eazeBuzzSuccess(@Valid @RequestBody Map<?, ?> reqobj) {
		System.out.println("Success!!!!!");
		return onlinePaymentService.eazeBuzzSuccess(reqobj);

	}

//	@PostMapping("/eazepayment/failed")
//	public String eazeBuzzFailed(@Valid @RequestParam("txnid") String txnid, @RequestParam("status") String status,
//			@RequestParam("easepayid") String easepayid, @RequestParam("hash") String hash) {
//		return onlinePaymentService.eazeBuzzFailed(txnid, status, easepayid, hash);
//	}

	@PostMapping("/eazepayment/failed")
	public ResponseEntity<?> eazeBuzzFailed(@Valid @RequestBody Map<?, ?> reqobj) {
		System.out.println("Success!!!!!");
		return onlinePaymentService.eazeBuzzFailed(reqobj);

	}

	///////////////////////////////////////////////////////////////////////////////////////////////////

}
