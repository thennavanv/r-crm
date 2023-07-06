package com.ridsys.rib.DTO;

import java.security.KeyStore.PrivateKeyEntry;

public class OnlinepaymentDTO {

	private String lconame;
	private String role;
	private String amount;
	private String orderid;
	private int gatewayid;
	private String paymenttype;
	private String status;
	private String tstarttime;
	private String resmsg;
	private String errormsg;
	private String rechargetype;
	private String creationby;

	public OnlinepaymentDTO(String lconame, String role, String amount, String orderid, int gatewayid,
			String paymenttype, String status, String tstarttime, String resmsg, String errormsg) {
		super();
		this.lconame = lconame;
		this.role = role;
		this.amount = amount;
		this.orderid = orderid;
		this.gatewayid = gatewayid;
		this.paymenttype = paymenttype;
		this.status = status;
		this.tstarttime = tstarttime;
		this.resmsg = resmsg;
		this.errormsg = errormsg;
	}

	public OnlinepaymentDTO(String lconame, String role, String amount, String orderid, String paymenttype,
			String status, String tstarttime, String resmsg, String creationby) {
		super();
		this.lconame = lconame;
		this.role = role;
		this.amount = amount;
		this.orderid = orderid;
		this.paymenttype = paymenttype;
		this.status = status;
		this.tstarttime = tstarttime;
		this.resmsg = resmsg;
		this.creationby = creationby;
	}

	public String getCreationby() {
		return creationby;
	}

	public void setCreationby(String creationby) {
		this.creationby = creationby;
	}

	public String getRechargetype() {
		return rechargetype;
	}

	public void setRechargetype(String rechargetype) {
		this.rechargetype = rechargetype;
	}

	public String getErrormsg() {
		return errormsg;
	}

	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}

	public String getResmsg() {
		return resmsg;
	}

	public void setResmsg(String resmsg) {
		this.resmsg = resmsg;
	}

	public String getLconame() {
		return lconame;
	}

	public void setLconame(String lconame) {
		this.lconame = lconame;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public int getGatewayid() {
		return gatewayid;
	}

	public void setGatewayid(int gatewayid) {
		this.gatewayid = gatewayid;
	}

	public String getPaymenttype() {
		return paymenttype;
	}

	public void setPaymenttype(String paymenttype) {
		this.paymenttype = paymenttype;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTstarttime() {
		return tstarttime;
	}

	public void setTstarttime(String tstarttime) {
		this.tstarttime = tstarttime;
	}

}
