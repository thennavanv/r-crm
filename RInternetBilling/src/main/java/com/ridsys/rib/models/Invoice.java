package com.ridsys.rib.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "invoice")
public class Invoice {

	private int id;

	@Column(name = "user_id")
	private int userid;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "invoiceno_gen")
	@GenericGenerator(name = "invoiceno_gen", strategy = "com.ridsys.rib.comm.InvoiceNumberGenerator")
	private int invoice_no;

	private String referenceid;
	private String financial_year;
	private int type_id;
	private int status_id;
	private String invoice_date;
	private String invoice_period;
	private String due_date;
	private double previous_due;
	private double payment_recevied;
	private double adjustments;
	private double invoice_amount;
	private double balance_amount;
	private double packet_amount_balance;
	private String creationdate;
	private String updatedate;
	private boolean is_delete;
	private String gstin;

//	public Invoice(int id, int invoice_no,String referenceid, int userid, String financial_year, int type_id, int status_id,
//			String invoice_date, String invoice_period, String due_date, double previous_due, double payment_recevied,
//			double adjustments, double invoice_amount, double balance_amount, double packet_amount_balance,
//			String creationdate, String updatedate, boolean is_delete) {
//		super();
//		this.id = id;
//		this.userid = userid;
//		this.financial_year = financial_year;
//		this.type_id = type_id;
//		this.status_id = status_id;
//		this.invoice_date = invoice_date;
//		this.invoice_period = invoice_period;
//		this.due_date = due_date;
//		this.previous_due = previous_due;
//		this.payment_recevied = payment_recevied;
//		this.adjustments = adjustments;
//		this.invoice_amount = invoice_amount;
//		this.balance_amount = balance_amount;
//		this.packet_amount_balance = packet_amount_balance;
//		this.creationdate = creationdate;
//		this.updatedate = updatedate;
//		this.is_delete = is_delete;
//	}

	public Invoice() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Invoice(int id, int userid, int invoice_no, String referenceid, String financial_year, int type_id,
			int status_id, String invoice_date, String invoice_period, String due_date, double previous_due,
			double payment_recevied, double adjustments, double invoice_amount, double balance_amount,
			double packet_amount_balance, String creationdate, String updatedate, boolean is_delete, String gstin) {
		super();
		this.id = id;
		this.userid = userid;
		this.invoice_no = invoice_no;
		this.referenceid = referenceid;
		this.financial_year = financial_year;
		this.type_id = type_id;
		this.status_id = status_id;
		this.invoice_date = invoice_date;
		this.invoice_period = invoice_period;
		this.due_date = due_date;
		this.previous_due = previous_due;
		this.payment_recevied = payment_recevied;
		this.adjustments = adjustments;
		this.invoice_amount = invoice_amount;
		this.balance_amount = balance_amount;
		this.packet_amount_balance = packet_amount_balance;
		this.creationdate = creationdate;
		this.updatedate = updatedate;
		this.is_delete = is_delete;
		this.gstin = gstin;
	}

	public String getGstin() {
		return gstin;
	}

	public void setGstin(String gstin) {
		this.gstin = gstin;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getInvoice_no() {
		return invoice_no;
	}

	public void setInvoice_no(int invoice_no) {
		this.invoice_no = invoice_no;
	}

	public String getReferenceid() {
		return referenceid;
	}

	public void setReferenceid(String referenceid) {
		this.referenceid = referenceid;
	}

	public String getFinancial_year() {
		return financial_year;
	}

	public void setFinancial_year(String financial_year) {
		this.financial_year = financial_year;
	}

	public int getType_id() {
		return type_id;
	}

	public void setType_id(int type_id) {
		this.type_id = type_id;
	}

	public int getStatus_id() {
		return status_id;
	}

	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}

	public String getInvoice_date() {
		return invoice_date;
	}

	public void setInvoice_date(String invoice_date) {
		this.invoice_date = invoice_date;
	}

	public String getInvoice_period() {
		return invoice_period;
	}

	public void setInvoice_period(String invoice_period) {
		this.invoice_period = invoice_period;
	}

	public String getDue_date() {
		return due_date;
	}

	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}

	public double getPrevious_due() {
		return previous_due;
	}

	public void setPrevious_due(double previous_due) {
		this.previous_due = previous_due;
	}

	public double getPayment_recevied() {
		return payment_recevied;
	}

	public void setPayment_recevied(double payment_recevied) {
		this.payment_recevied = payment_recevied;
	}

	public double getAdjustments() {
		return adjustments;
	}

	public void setAdjustments(double adjustments) {
		this.adjustments = adjustments;
	}

	public double getInvoice_amount() {
		return invoice_amount;
	}

	public void setInvoice_amount(double invoice_amount) {
		this.invoice_amount = invoice_amount;
	}

	public double getBalance_amount() {
		return balance_amount;
	}

	public void setBalance_amount(double balance_amount) {
		this.balance_amount = balance_amount;
	}

	public double getPacket_amount_balance() {
		return packet_amount_balance;
	}

	public void setPacket_amount_balance(double packet_amount_balance) {
		this.packet_amount_balance = packet_amount_balance;
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
