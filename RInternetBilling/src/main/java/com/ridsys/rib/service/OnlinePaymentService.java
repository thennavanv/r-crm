package com.ridsys.rib.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.ridsys.rib.DTO.OnlineRechargelistDTO;
import com.ridsys.rib.DTO.OnlinepaymentDTO;
import com.ridsys.rib.PaymentGateway.RazorPay.OrderCreate;
import com.ridsys.rib.models.Online_payment_log;
import com.ridsys.rib.payload.request.PaymentRequest;
import com.ridsys.rib.payload.request.QuatoplanactivationRequest;

public interface OnlinePaymentService {

	ResponseEntity<?> operatorPaymentRequest1(PaymentRequest requestbody);

	List<Online_payment_log> getOnlineRechargeHistory(String username, String role, String subrole, String fdate,
			String tdate);

	List<OnlinepaymentDTO> getAllOnlinePaymentLog(String fdate, String tdate, String username, String role);

	ResponseEntity<?> paymentOrderCreate(OrderCreate obj);

	ResponseEntity<?> paymentResponseSuccess(Map<String, Object> obj);

	ResponseEntity<?> paymentResponseError(Map<String, Object> obj);

	ResponseEntity<?> planPaymentOrderCreation(PaymentRequest reqobj);

	ResponseEntity<?> planPaymenSuccess(Map<String, String> reqobj);

	ResponseEntity<?> planPaymenFailed(Map<String, Object> obj);

	ResponseEntity<?> getAggrePaymentStatus(String tid, String type);

	List<OnlineRechargelistDTO> getOnlineUserRechargeHistory(String username, String fdate, String tdate, String role);

	ResponseEntity<?> eazeBuzzOrderCreate(PaymentRequest requestbody);

//	String eazeBuzzSuccess(String txnid, String status, String easepayid, String hash);

	ResponseEntity<?> eazeBuzzSuccess(Map<?, ?> reqobj);

//	String eazeBuzzFailed(String txnid, String status, String easepayid, String hash);

	ResponseEntity<?> eazeBuzzFailed(Map<?, ?> reqobj);

	ResponseEntity<?> paymentRequest(PaymentRequest requestbody);
}
