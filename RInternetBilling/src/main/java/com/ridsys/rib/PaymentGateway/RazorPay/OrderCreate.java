package com.ridsys.rib.PaymentGateway.RazorPay;

import org.jetbrains.annotations.NotNull;

import lombok.Getter;

@Getter
public class OrderCreate {
	@NotNull
	private String username;

	@NotNull
	private String role;

	private int amount;

	@NotNull
	private String remark;

	public OrderCreate(String username, String role, int amount, String remark) {
		super();
		this.username = username;
		this.role = role;
		this.amount = amount;
		this.remark = remark;
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

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
