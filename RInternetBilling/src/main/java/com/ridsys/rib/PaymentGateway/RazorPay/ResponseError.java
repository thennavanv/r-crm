package com.ridsys.rib.PaymentGateway.RazorPay;

import java.util.Map;

import lombok.Data;

@Data
public class ResponseError {

	private String code;
	private String description;
	private Map<String, Object> metadata;
	private String order_id;
	private String reason;
	private String source;
	private String step;

}
