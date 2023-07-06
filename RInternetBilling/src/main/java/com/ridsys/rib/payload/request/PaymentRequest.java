package com.ridsys.rib.payload.request;

import javax.validation.constraints.NotBlank;

public class PaymentRequest {

	@NotBlank
	private String amount;

	@NotBlank
	private String username;

	@NotBlank
	private String role;

//	@NotBlank
	private String returnurl;

//	@NotBlank
//	private String salt;

//	@NotBlank
//	private String apikey;

//	@NotBlank
	private String locaterurl;

	@NotBlank
	private String title;

	private int planid;
	private String remark;

	public int getPlanid() {
		return planid;
	}

	public void setPlanid(int planid) {
		this.planid = planid;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getReturnurl() {
		return returnurl;
	}

	public void setReturnurl(String returnurl) {
		this.returnurl = returnurl;
	}

//	public String getSalt() {
//		return salt;
//	}
//
//	public void setSalt(String salt) {
//		this.salt = salt;
//	}

//	public String getApikey() {
//		return apikey;
//	}
//
//	public void setApikey(String apikey) {
//		this.apikey = apikey;
//	}

	public String getLocaterurl() {
		return locaterurl;
	}

	public void setLocaterurl(String locaterurl) {
		this.locaterurl = locaterurl;
	}

}
