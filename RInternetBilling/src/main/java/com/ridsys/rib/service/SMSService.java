package com.ridsys.rib.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ridsys.rib.models.Smshistory;
import com.ridsys.rib.payload.request.SmsRequest;
import com.ridsys.rib.payload.request.UserRegisterVerificationRequest;

public interface SMSService {
	ResponseEntity<?> sendSMS(UserRegisterVerificationRequest obj);
	
	ResponseEntity<?> sendBulkSms(SmsRequest obj);
	
	List<Smshistory> getSMShistory(String username,String role,String status);
	
	List<Smshistory> getSMSbyMobile(String mobile);
}
