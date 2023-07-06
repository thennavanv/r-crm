package com.ridsys.rib.payload.request;

import java.util.List;

public class SmsRequest {

	private String username;
	private List<Smsuser> userid;
	private List<Smsuser> operatorid;
	private String message;
	private String role;
	private String receiverrole;
	private String subject;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Smsuser> getUserid() {
		return userid;
	}

	public void setUserid(List<Smsuser> userid) {
		this.userid = userid;
	}

	public List<Smsuser> getOperatorid() {
		return operatorid;
	}

	public void setOperatorid(List<Smsuser> operatorid) {
		this.operatorid = operatorid;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getReceiverrole() {
		return receiverrole;
	}

	public void setReceiverrole(String receiverrole) {
		this.receiverrole = receiverrole;
	}

}
