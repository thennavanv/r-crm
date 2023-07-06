package com.ridsys.rib.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "invoice_items")
public class Invoice_items {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int invoice_id;
	private String txn_date;
	private String period_date;
	private String hsn_code;
	private int planid;
	private String plan_desc;
	private int period_days;
	private double rate;
	private double cgst_perc;
	private double cgst_rate;
	private double sgst_perc;
	private double sgst_rate;
	private double amount_incl_tax;
	private String discription;
	private String creationbyusername;
	private String creationbyrole;
	private String creationdate;
	private String updatedate;
	private boolean is_delete;
	
	public Invoice_items(int invoice_id,String txn_date, String period_date, String hsn_code,int planid, String plan_desc,
			int period_days, double rate, double cgst_perc, double cgst_rate, double sgst_perc, double sgst_rate,
			double amount_incl_tax, String discription, String creationbyusername, String creationbyrole,
			String creationdate, String updatedate, boolean is_delete) {
		super();
		this.invoice_id=invoice_id;
		this.txn_date = txn_date;
		this.period_date = period_date;
		this.hsn_code = hsn_code;
		this.planid=planid;
		this.plan_desc = plan_desc;
		this.period_days = period_days;
		this.rate = rate;
		this.cgst_perc = cgst_perc;
		this.cgst_rate = cgst_rate;
		this.sgst_perc = sgst_perc;
		this.sgst_rate = sgst_rate;
		this.amount_incl_tax = amount_incl_tax;
		this.discription = discription;
		this.creationbyusername = creationbyusername;
		this.creationbyrole = creationbyrole;
		this.creationdate = creationdate;
		this.updatedate = updatedate;
		this.is_delete = is_delete;
	}

	public Invoice_items() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getInvoice_id() {
		return invoice_id;
	}

	public void setInvoice_id(int invoice_id) {
		this.invoice_id = invoice_id;
	}

	public String getTxn_date() {
		return txn_date;
	}

	public void setTxn_date(String txn_date) {
		this.txn_date = txn_date;
	}

	public String getPeriod_date() {
		return period_date;
	}

	public void setPeriod_date(String period_date) {
		this.period_date = period_date;
	}

	public String getHsn_code() {
		return hsn_code;
	}

	public void setHsn_code(String hsn_code) {
		this.hsn_code = hsn_code;
	}

	public int getPlanid() {
		return planid;
	}

	public void setPlanid(int planid) {
		this.planid = planid;
	}

	public String getPlan_desc() {
		return plan_desc;
	}

	public void setPlan_desc(String plan_desc) {
		this.plan_desc = plan_desc;
	}

	public int getPeriod_days() {
		return period_days;
	}

	public void setPeriod_days(int period_days) {
		this.period_days = period_days;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public double getCgst_perc() {
		return cgst_perc;
	}

	public void setCgst_perc(double cgst_perc) {
		this.cgst_perc = cgst_perc;
	}

	public double getCgst_rate() {
		return cgst_rate;
	}

	public void setCgst_rate(double cgst_rate) {
		this.cgst_rate = cgst_rate;
	}

	public double getSgst_perc() {
		return sgst_perc;
	}

	public void setSgst_perc(double sgst_perc) {
		this.sgst_perc = sgst_perc;
	}

	public double getSgst_rate() {
		return sgst_rate;
	}

	public void setSgst_rate(double sgst_rate) {
		this.sgst_rate = sgst_rate;
	}

	public double getAmount_incl_tax() {
		return amount_incl_tax;
	}

	public void setAmount_incl_tax(double amount_incl_tax) {
		this.amount_incl_tax = amount_incl_tax;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public String getCreationbyusername() {
		return creationbyusername;
	}

	public void setCreationbyusername(String creationbyusername) {
		this.creationbyusername = creationbyusername;
	}

	public String getCreationbyrole() {
		return creationbyrole;
	}

	public void setCreationbyrole(String creationbyrole) {
		this.creationbyrole = creationbyrole;
	}

	public String getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(String creationdate) {
		this.creationdate = creationdate;
	}

	public String getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}

	public boolean isIs_delete() {
		return is_delete;
	}

	public void setIs_delete(boolean is_delete) {
		this.is_delete = is_delete;
	}

}
