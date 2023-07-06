package com.ridsys.rib.payload.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class IptvPlanActivation {

	private String creationby;
	private String creationbyusername;
	private String opusername;
	private String username;
	private String planrechargeid;
	private String expirydate;
	private String days;
	private String packageid;
	private String opid;
	private String packagetypeid;
	private String walletbalance;
	private String cuscost;
	private String msocost;
	private String lcocomm;
	private String price;
	private String mrp;
	private String tax;
	private String planname;
	private String remarks;

	public IptvPlanActivation(String creationby, String creationbyusername, String opusername, String username,
			String planrechargeid, String expirydate, String days, String packageid, String opid, String packagetypeid,
			String walletbalance, String cuscost, String msocost, String lcocomm, String price, String mrp, String tax,
			String planname,String remarks) {
		super();
		this.creationby = creationby;
		this.creationbyusername = creationbyusername;
		this.opusername = opusername;
		this.username = username;
		this.planrechargeid = planrechargeid;
		this.expirydate = expirydate;
		this.days = days;
		this.packageid = packageid;
		this.opid = opid;
		this.packagetypeid = packagetypeid;
		this.walletbalance = walletbalance;
		this.cuscost = cuscost;
		this.msocost = msocost;
		this.lcocomm = lcocomm;
		this.price = price;
		this.mrp = mrp;
		this.tax = tax;
		this.planname = planname;
		this.remarks=remarks;
	}

}
