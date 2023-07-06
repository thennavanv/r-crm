package com.ridsys.rib.PaymentGateway.RazorPay;

import lombok.Getter;

@Getter
public class ResponseSuccess {

	private String razorpay_payment_id;
	private String razorpay_order_id;
	private String razorpay_signature;

	public ResponseSuccess(String razorpay_payment_id, String razorpay_order_id, String razorpay_signature) {
		super();
		this.razorpay_payment_id = razorpay_payment_id;
		this.razorpay_order_id = razorpay_order_id;
		this.razorpay_signature = razorpay_signature;
	}

}
