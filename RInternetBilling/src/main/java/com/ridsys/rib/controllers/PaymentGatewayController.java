package com.ridsys.rib.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ridsys.rib.models.Gateway;
import com.ridsys.rib.models.PaymentGatewayMaster;
import com.ridsys.rib.payload.response.MessageResponse;
import com.ridsys.rib.repository.GatewayRepository;
import com.ridsys.rib.repository.PaymentGatewayMasterRepository;
import com.ridsys.rib.service.PaymentGatewayService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/paymentgateway")
public class PaymentGatewayController {

	@Autowired
	PaymentGatewayMasterRepository paymentgatewaymasterRepository;

	@Autowired
	GatewayRepository gatewayRepository;

	@Autowired
	PaymentGatewayService service;

	@GetMapping("/getAllGateway")
	public List<PaymentGatewayMaster> getall() {
		return paymentgatewaymasterRepository.findAll();
	}

	@GetMapping("/getActiveGateway")
	public PaymentGatewayMaster getActiveGateway() {
		return paymentgatewaymasterRepository.findByIsactive(true);
	}

	@GetMapping("/gateway/getAll")
	public List<Gateway> getgatewaydetails(@RequestParam("status") boolean status) {
		if (status) {
			return gatewayRepository.findAll();
		} else {
			return gatewayRepository.findAllByNotPresent();
		}

	}

	@PostMapping("/create")
	public ResponseEntity<?> createPaymentgateway(@Valid @RequestBody PaymentGatewayMaster paymentgatewaymaster) {
		return service.createPaymentgateway(paymentgatewaymaster);
	}

	// not used
	@PostMapping("/gateway/create")
	public ResponseEntity<?> createGateway(@Valid @RequestBody Gateway gateway) {
		return service.CreateGatway(gateway);
	}

	@PutMapping("/gateway/update")
	public ResponseEntity<?> updateGateway(@Valid @RequestBody Gateway gateway) {
		return service.updateGateway(gateway);
	}

	@PutMapping("/update")
	public ResponseEntity<?> updatePaymentGateway(@Valid @RequestBody PaymentGatewayMaster reqobj) {
		return service.updatePaymentGateway(reqobj);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteById(@Valid @RequestParam int id) {
		if (id > 0) {
			if (paymentgatewaymasterRepository.existsById(id)) {

				paymentgatewaymasterRepository.deleteById(id);
				return ResponseEntity.ok(new MessageResponse("Successfully Deleted!"));
			} else {
				return ResponseEntity.badRequest().body(new MessageResponse("The selected gateway is not available!"));
			}
		} else {
			return ResponseEntity.badRequest().body(new MessageResponse("Check your Input!"));
		}
	}

}
