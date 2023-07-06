package com.ridsys.rib.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class OttplanactivationRequest {

//	@NotBlank
	private String userid;

	@NotBlank
	private String lcoid;

	@NotBlank
	private String planDays;

	@NotBlank
	private String role;

	private int id;

	private int packageid;

	private String creationby;

	private String rechargeid;

	@NotNull
	private String username;

	private String paymenttype = "";

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getPackageid() {
		return packageid;
	}

	public void setPackageid(int packageid) {
		this.packageid = packageid;
	}

	public String getCreationby() {
		return creationby;
	}

	public void setCreationby(String creationby) {
		this.creationby = creationby;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getLcoid() {
		return lcoid;
	}

	public void setLcoid(String lcoid) {
		this.lcoid = lcoid;
	}

	public String getPlanDays() {
		return planDays;
	}

	public void setPlanDays(String planDays) {
		this.planDays = planDays;
	}

	public String getRechargeid() {
		return rechargeid;
	}

	public void setRechargeid(String rechargeid) {
		this.rechargeid = rechargeid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPaymenttype() {
		return paymenttype;
	}

	public void setPaymenttype(String paymenttype) {
		this.paymenttype = paymenttype;
	}

}
