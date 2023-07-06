package com.ridsys.rib.DTO;

public class WalletupdatelogDTO {

	private int id;
	private String role;
	private String username;
	private String oldbalance;
	private String amount;
	private String newbalance;
	private String referenceid;
	private String rechargetypename;
	private String walletupdatetypename;
	private String paymentbyrole;
	private String paymentbyusername;
	private String creationdate;
	private String operatorname;
	private String status;

	public WalletupdatelogDTO() {
		super();
	}

	public WalletupdatelogDTO(int id, String role, String username, String oldbalance, String amount, String newbalance,
			String referenceid, String rechargetypename, String walletupdatetypename, String paymentbyrole,
			String paymentbyusername, String creationdate, String operatorname) {
		super();
		this.id = id;
		this.role = role;
		this.username = username;
		this.oldbalance = oldbalance;
		this.amount = amount;
		this.newbalance = newbalance;
		this.referenceid = referenceid;
		this.rechargetypename = rechargetypename;
		this.walletupdatetypename = walletupdatetypename;
		this.paymentbyrole = paymentbyrole;
		this.paymentbyusername = paymentbyusername;
		this.creationdate = creationdate;
		this.operatorname = operatorname;
	}
	
	

	public WalletupdatelogDTO(int id, String role, String username, String oldbalance, String amount, String newbalance,
			String referenceid, String rechargetypename, String walletupdatetypename, String paymentbyrole,
			String paymentbyusername, String creationdate, String operatorname, String status) {
		super();
		this.id = id;
		this.role = role;
		this.username = username;
		this.oldbalance = oldbalance;
		this.amount = amount;
		this.newbalance = newbalance;
		this.referenceid = referenceid;
		this.rechargetypename = rechargetypename;
		this.walletupdatetypename = walletupdatetypename;
		this.paymentbyrole = paymentbyrole;
		this.paymentbyusername = paymentbyusername;
		this.creationdate = creationdate;
		this.operatorname = operatorname;
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReferenceid() {
		return referenceid;
	}

	public void setReferenceid(String referenceid) {
		this.referenceid = referenceid;
	}

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getOldbalance() {
		return oldbalance;
	}

	public void setOldbalance(String oldbalance) {
		this.oldbalance = oldbalance;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getNewbalance() {
		return newbalance;
	}

	public void setNewbalance(String newbalance) {
		this.newbalance = newbalance;
	}

	public String getRechargetypename() {
		return rechargetypename;
	}

	public void setRechargetypename(String rechargetypename) {
		this.rechargetypename = rechargetypename;
	}

	public String getWalletupdatetypename() {
		return walletupdatetypename;
	}

	public void setWalletupdatetypename(String walletupdatetypename) {
		this.walletupdatetypename = walletupdatetypename;
	}

	public String getPaymentbyrole() {
		return paymentbyrole;
	}

	public void setPaymentbyrole(String paymentbyrole) {
		this.paymentbyrole = paymentbyrole;
	}

	public String getPaymentbyusername() {
		return paymentbyusername;
	}

	public void setPaymentbyusername(String paymentbyusername) {
		this.paymentbyusername = paymentbyusername;
	}

	public String getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(String creationdate) {
		this.creationdate = creationdate;
	}

	public String getOperatorname() {
		return operatorname;
	}

	public void setOperatorname(String operatorname) {
		this.operatorname = operatorname;
	}

}
