package com.ridsys.rib.DTO;

public class OfflinePaymentDTO {

	private String transactionid;
	private String amount;
	private String role;
	private String username;
	private String paymenttype;
	private String createddate;
	private String creationbyrole;
	private String creationbyusername;
	private String description;

	public OfflinePaymentDTO(String transactionid, String amount, String role, String username, String paymenttype,
			String createddate, String creationbyrole, String creationbyusername, String description) {
		super();
		this.transactionid = transactionid;
		this.amount = amount;
		this.role = role;
		this.username = username;
		this.paymenttype = paymenttype;
		this.createddate = createddate;
		this.creationbyrole = creationbyrole;
		this.creationbyusername = creationbyusername;
		this.description = description;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getCreationbyrole() {
		return creationbyrole;
	}

	public void setCreationbyrole(String creationbyrole) {
		this.creationbyrole = creationbyrole;
	}

	public String getTransactionid() {
		return transactionid;
	}

	public void setTransactionid(String transactionid) {
		this.transactionid = transactionid;
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

	public String getPaymenttype() {
		return paymenttype;
	}

	public void setPaymenttype(String paymenttype) {
		this.paymenttype = paymenttype;
	}

	public String getCreateddate() {
		return createddate;
	}

	public void setCreateddate(String createddate) {
		this.createddate = createddate;
	}

	public String getCreationbyusername() {
		return creationbyusername;
	}

	public void setCreationbyusername(String creationbyusername) {
		this.creationbyusername = creationbyusername;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
