package com.ridsys.rib.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ridsys.rib.models.Gateway;
import com.ridsys.rib.models.PaymentGatewayMaster;

public interface PaymentGatewayService {

	ResponseEntity<?> CreateGatway(Gateway gateway);

	ResponseEntity<?> updateGateway(Gateway gateway);

	ResponseEntity<?> createPaymentgateway(PaymentGatewayMaster paymentgatewaymaster);

	ResponseEntity<?> updatePaymentGateway(PaymentGatewayMaster paymentgatewaymaster);

}
