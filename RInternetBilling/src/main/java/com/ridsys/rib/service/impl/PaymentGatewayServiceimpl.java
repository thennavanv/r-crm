package com.ridsys.rib.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ridsys.rib.comm.Dateformat;
import com.ridsys.rib.models.Gateway;
import com.ridsys.rib.models.PaymentGatewayMaster;
import com.ridsys.rib.payload.response.MessageResponse;
import com.ridsys.rib.repository.GatewayRepository;
import com.ridsys.rib.repository.PaymentGatewayMasterRepository;
import com.ridsys.rib.service.PaymentGatewayService;

@Service
public class PaymentGatewayServiceimpl implements PaymentGatewayService {

	@Autowired
	PaymentGatewayMasterRepository paymentgatewaymasterRepository;

	@Autowired
	GatewayRepository gatewayRepository;

	@Override
	public ResponseEntity<?> CreateGatway(Gateway gateway) {
		if (gateway.getGatewayname() == null) {

			return ResponseEntity.badRequest().body(new MessageResponse("Check Your Input!"));

		} else if (gatewayRepository.existsBygatewayname(gateway.getGatewayname())) {

			return ResponseEntity.badRequest().body(new MessageResponse("The gateway name is already exist!"));

		} else {
			gateway.setIsactive(true);
			gatewayRepository.save(gateway);
			return ResponseEntity.ok(new MessageResponse("Gateway created successfully!"));
		}

	}

	@Override
	public ResponseEntity<?> updateGateway(Gateway gateway) {

		Gateway gateway2 = gatewayRepository.findById(gateway.getId()).get();

		if (gateway2 == null) {

			return ResponseEntity.badRequest().body(new MessageResponse("The selected gateway is not available"));

		}
		gateway2.setGatewayname(gateway.getGatewayname());
		gateway2.setIsactive(gateway.isIsactive());

		gatewayRepository.save(gateway2);

		return ResponseEntity.ok(new MessageResponse(" gateway updated successfully!"));
	}

	@Override
	public ResponseEntity<?> createPaymentgateway(PaymentGatewayMaster pobj) {
		String currentdatetime = Dateformat.getCurrentDatetime();

		if (pobj.getApikey() == null || pobj.getApikey().equals("")) {
			return ResponseEntity.badRequest().body(new MessageResponse("API key is empty!"));

		} else if (pobj.getSalt() == null || pobj.getSalt().equals("")) {
			return ResponseEntity.badRequest().body(new MessageResponse("Salt is empty!"));

		}
		if (pobj.getMode() == null || pobj.getMode().equals("")) {
			return ResponseEntity.badRequest().body(new MessageResponse("Mode is empty!"));

		}
		if (pobj.getBaseurl() == null || pobj.getBaseurl().equals("")) {
			return ResponseEntity.badRequest().body(new MessageResponse("Base URL is empty!"));

		}

		pobj.setIsactive(false);
		pobj.setCreated_date(currentdatetime);

		paymentgatewaymasterRepository.save(pobj);

		return ResponseEntity.ok(new MessageResponse("Successfully created!"));

	}

	@Override
	public ResponseEntity<?> updatePaymentGateway(PaymentGatewayMaster pobj) {

		PaymentGatewayMaster pobj2 = paymentgatewaymasterRepository.findById(pobj.getId());

		if (pobj2 == null) {

			return ResponseEntity.badRequest().body(new MessageResponse("The gateway is not available"));

		}

		if (pobj.isIsactive()) {
			paymentgatewaymasterRepository.updateisactive();
		}

		pobj2.setIsactive(pobj.isIsactive());
		pobj2.setApikey(pobj.getApikey());
		pobj2.setSalt(pobj.getSalt());
		pobj2.setMode(pobj.getMode());
		pobj2.setMerchant(pobj.getMerchant());
		pobj2.setExtra1(pobj.getExtra1());
		pobj2.setExtra2(pobj.getExtra2());
		pobj2.setBaseurl(pobj.getBaseurl());
		pobj2.setSuccessurl(pobj.getSuccessurl());
		pobj2.setFaliureurl(pobj.getFaliureurl());
		pobj2.setGateway(pobj.getGateway());
		pobj2.setUpdateddate(Dateformat.getCurrentDatetime());

		paymentgatewaymasterRepository.save(pobj2);

		return ResponseEntity.ok(new MessageResponse("Successfully Updated!"));
	}
}
