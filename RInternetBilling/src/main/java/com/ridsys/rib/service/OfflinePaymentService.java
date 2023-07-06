package com.ridsys.rib.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ridsys.rib.payload.request.OfflinePayment;
import com.ridsys.rib.DTO.OfflinePaymentDTO;
import com.ridsys.rib.models.Offline_payment_log;

public interface OfflinePaymentService {

	ResponseEntity<?> operatorPaymentRequest(OfflinePayment requestbody);
	
	List<Offline_payment_log> getOfflineRechargeList();
	
	List<OfflinePaymentDTO> offlineRechargeList(String username,String role,String fdate,String tdate);

}
