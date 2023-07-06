package com.ridsys.rib.DTO;

import lombok.Data;

@Data
public class OperatorWalletReportDTO {

	private int id;
	private String Operatorname;
	private String username;
	private String creationdate;
	private String walletupdatetype;
	private String oldbalance;
	private String amount;
	private String newbalance;
	private String paymentby;
	private String rechargetype;
	private String paymenttype;
	private String paymentbyusername;
	private String planName;
	private String planMonth;
	private String customername;
	private String customerusername;

	public OperatorWalletReportDTO(int id, String operatorname, String username, String creationdate,
			String walletupdatetype, String oldbalance, String amount, String newbalance, String paymentby,
			String rechargetype, String paymenttype, String paymentbyusername, String planName, String planMonth) {
		super();
		this.id = id;
		this.Operatorname = operatorname;
		this.username = username;
		this.creationdate = creationdate;
		this.walletupdatetype = walletupdatetype;
		this.oldbalance = oldbalance;
		this.amount = amount;
		this.newbalance = newbalance;
		this.paymentby = paymentby;
		this.rechargetype = rechargetype;
		this.paymenttype = paymenttype;
		this.paymentbyusername = paymentbyusername;
		this.planName = planName;
		this.planMonth = planMonth;
	}

	public OperatorWalletReportDTO(int id, String operatorname, String username, String creationdate,
			String walletupdatetype, String oldbalance, String amount, String newbalance, String paymentby,
			String rechargetype, String paymenttype, String paymentbyusername, String planName, String planMonth,
			String customername, String customerusername) {
		super();
		this.id = id;
		Operatorname = operatorname;
		this.username = username;
		this.creationdate = creationdate;
		this.walletupdatetype = walletupdatetype;
		this.oldbalance = oldbalance;
		this.amount = amount;
		this.newbalance = newbalance;
		this.paymentby = paymentby;
		this.rechargetype = rechargetype;
		this.paymenttype = paymenttype;
		this.paymentbyusername = paymentbyusername;
		this.planName = planName;
		this.planMonth = planMonth;
		this.customername = customername;
		this.customerusername = customerusername;
	}

	
	


}
