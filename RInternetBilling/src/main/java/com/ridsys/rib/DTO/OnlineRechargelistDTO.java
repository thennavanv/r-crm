package com.ridsys.rib.DTO;

import lombok.Data;

@Data
public class OnlineRechargelistDTO {

	private int planpaymentid;
	private String fullname;
	private String orderid;
	private String transactionid;
	private String status;
	private String amount;
	private String resmsg;
	private String errormsg;
	private String username;
	private String tstarttime;
	private String tendtime;
	private String description;
	private String planname;
	private String referenceid;
	private String quotaexpirydate;
	private String rechargestatus;
	private String creationbyusername;
	private String creationdate;
	private String updateddate;
	private String lconame;

	public OnlineRechargelistDTO(int planpaymentid, String orderid, String transactionid, String status, String amount,
			String resmsg, String errormsg, String username, String tstarttime, String tendtime, String description,
			String planname, String referenceid, String quotaexpirydate, String rechargestatus,
			String creationbyusername, String creationdate, String fullname, String updateddate, String lconame) {
		super();
		this.planpaymentid = planpaymentid;
		this.orderid = orderid;
		this.transactionid = transactionid;
		this.status = status;
		this.amount = amount;
		this.resmsg = resmsg;
		this.errormsg = errormsg;
		this.username = username;
		this.tstarttime = tstarttime;
		this.tendtime = tendtime;
		this.description = description;
		this.planname = planname;
		this.referenceid = referenceid;
		this.quotaexpirydate = quotaexpirydate;
		this.rechargestatus = rechargestatus;
		this.creationbyusername = creationbyusername;
		this.creationdate = creationdate;
		this.fullname = fullname;
		this.updateddate = updateddate;
		this.lconame = lconame;
	}

}
