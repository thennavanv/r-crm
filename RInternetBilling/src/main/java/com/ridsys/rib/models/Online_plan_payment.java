package com.ridsys.rib.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "online_plan_payment")
public class Online_plan_payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int planid;
	private String transactionid;
	private String username;
	private String quotaexpirydate;
	private String status;
	private String response;
	private String creationbyusername;
	private String creationby;
	private String amount;
	private String referenceid;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String creationdate;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String updateddate;

	public Online_plan_payment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Online_plan_payment(int planid, String transactionid, String username, String quotaexpirydate, String status,
			String response, String creationbyusername, String creationby, String amount, String creationdate,
			String updateddate, String referenceid) {
		super();
		this.planid = planid;
		this.transactionid = transactionid;
		this.username = username;
		this.quotaexpirydate = quotaexpirydate;
		this.status = status;
		this.response = response;
		this.creationdate = creationdate;
		this.updateddate = updateddate;
		this.creationbyusername = creationbyusername;
		this.creationby = creationby;
		this.amount = amount;
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

	public int getPlanid() {
		return planid;
	}

	public void setPlanid(int planid) {
		this.planid = planid;
	}

	public String getTransactionid() {
		return transactionid;
	}

	public void setTransactionid(String transactionid) {
		this.transactionid = transactionid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getQuotaexpirydate() {
		return quotaexpirydate;
	}

	public void setQuotaexpirydate(String quotaexpirydate) {
		this.quotaexpirydate = quotaexpirydate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(String creationdate) {
		this.creationdate = creationdate;
	}

	public String getUpdateddate() {
		return updateddate;
	}

	public void setUpdateddate(String updateddate) {
		this.updateddate = updateddate;
	}

	public String getCreationbyusername() {
		return creationbyusername;
	}

	public void setCreationbyusername(String creationbyusername) {
		this.creationbyusername = creationbyusername;
	}

	public String getCreationby() {
		return creationby;
	}

	public void setCreationby(String creationby) {
		this.creationby = creationby;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

}
