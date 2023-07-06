package com.ridsys.rib.payload.request;

import javax.validation.constraints.NotBlank;

public class QuatoplanactivationRequest {

	@NotBlank
	private String cus_username;

	@NotBlank
	private String planid;

	@NotBlank
	private String quotadays;

	@NotBlank
	private String creationby;

	@NotBlank
	private String creationbyusername;

	@NotBlank
	private String paymentType;

	private String transactionid;

	private String expirydate;

	private String startdate;

	public String getTransactionid() {
		return transactionid;
	}

	public void setTransactionid(String transactionid) {
		this.transactionid = transactionid;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getCus_username() {
		return cus_username;
	}

	public void setCus_username(String cus_username) {
		this.cus_username = cus_username;
	}

	public String getPlanid() {
		return planid;
	}

	public void setPlanid(String planid) {
		this.planid = planid;
	}

	public String getQuotadays() {
		return quotadays;
	}

	public void setQuotadays(String quotadays) {
		this.quotadays = quotadays;
	}

	public String getCreationby() {
		return creationby;
	}

	public void setCreationby(String creationby) {
		this.creationby = creationby;
	}

	public String getCreationbyusername() {
		return creationbyusername;
	}

	public void setCreationbyusername(String creationbyusername) {
		this.creationbyusername = creationbyusername;
	}

	public String getExpirydate() {
		return expirydate;
	}

	public void setExpirydate(String expirydate) {
		this.expirydate = expirydate;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

}
