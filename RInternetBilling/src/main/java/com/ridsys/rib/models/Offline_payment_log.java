package com.ridsys.rib.models;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ridsys.rib.DTO.OfflinePaymentDTO;

@Entity
@Table(name = "offline_payment_log")

@NamedNativeQuery(name = "Offline_payment_log.getOfflineHistoryAll", query = "SELECT opl.transactionid,opl.amount,opl.role,CONCAT(op.firstname,' ',op.lastname)AS username,(SELECT value FROM payment_type WHERE id=opl.paymenttypeid)AS paymenttype,opl.creationdate,opl.creationbyrole,opl.creationbyusername,opl.description FROM offline_payment_log opl LEFT JOIN operators op ON op.username=opl.username ORDER BY opl.id DESC", resultSetMapping = "Mapping.getOfflinePaymentHistoryRS")
@NamedNativeQuery(name = "Offline_payment_log.getOfflineHistoryAllFtdate", query = "SELECT opl.transactionid,opl.amount,opl.role,CONCAT(op.firstname,' ',op.lastname)AS username,(SELECT value FROM payment_type WHERE id=opl.paymenttypeid)AS paymenttype,opl.creationdate,opl.creationbyrole,opl.creationbyusername,opl.description FROM offline_payment_log opl LEFT JOIN operators op ON op.username=opl.username WHERE DATE(opl.creationdate)>=:fdate AND DATE(opl.creationdate)<=:tdate  ORDER BY opl.id DESC", resultSetMapping = "Mapping.getOfflinePaymentHistoryRS")
@NamedNativeQuery(name = "Offline_payment_log.getOfflineHistory", query = "SELECT transactionid,amount,role,username,(select value FROM payment_type WHERE id=paymenttypeid)AS paymenttype,creationdate,creationbyrole,creationbyusername,description FROM offline_payment_log WHERE username=:username  ORDER BY id DESC", resultSetMapping = "Mapping.getOfflinePaymentHistoryRS")
@NamedNativeQuery(name = "Offline_payment_log.getOfflineHistoryFtdate", query = "SELECT transactionid,amount,role,username,(select value FROM payment_type WHERE id=paymenttypeid)AS paymenttype,creationdate,creationbyrole,creationbyusername,description FROM offline_payment_log WHERE username=:username AND  DATE(creationdate)>=:fdate AND DATE(creationdate)<=:tdate  ORDER BY id DESC", resultSetMapping = "Mapping.getOfflinePaymentHistoryRS")
@SqlResultSetMapping(name = "Mapping.getOfflinePaymentHistoryRS", classes = {
		@ConstructorResult(targetClass = OfflinePaymentDTO.class, columns = {
				@ColumnResult(name = "transactionid", type = String.class),
				@ColumnResult(name = "amount", type = String.class), @ColumnResult(name = "role", type = String.class),
				@ColumnResult(name = "username", type = String.class),
				@ColumnResult(name = "paymenttype", type = String.class),
				@ColumnResult(name = "creationdate", type = String.class),
				@ColumnResult(name = "creationbyrole", type = String.class),
				@ColumnResult(name = "creationbyusername", type = String.class),
				@ColumnResult(name = "description", type = String.class) }) })
public class Offline_payment_log {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String transactionid;
	private String amount;
	private String role;
	private String username;
	private int paymenttypeid;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String creationdate;

	private String creationbyrole;
	private String creationbyusername;
	private String description;

	@Transient
	private String paymenttype;

	public Offline_payment_log() {
		super();
	}

	public Offline_payment_log(String transactionid, String amount, String role, String username, int paymenttypeid,
			String creationdate, String creationbyrole, String creationbyusername, String description) {
		super();
		this.transactionid = transactionid;
		this.amount = amount;
		this.role = role;
		this.username = username;
		this.paymenttypeid = paymenttypeid;
		this.creationdate = creationdate;
		this.creationbyrole = creationbyrole;
		this.creationbyusername = creationbyusername;
		this.description = description;
	}

	public Offline_payment_log(int id, String transactionid, String amount, String role, String username,
			String creationdate, String creationbyrole, String creationbyusername, String description,
			String paymenttype) {
		super();
		this.id = id;
		this.transactionid = transactionid;
		this.amount = amount;
		this.role = role;
		this.username = username;
		this.creationdate = creationdate;
		this.creationbyrole = creationbyrole;
		this.creationbyusername = creationbyusername;
		this.description = description;
		this.paymenttype = paymenttype;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getPaymenttypeid() {
		return paymenttypeid;
	}

	public void setPaymenttypeid(int paymenttypeid) {
		this.paymenttypeid = paymenttypeid;
	}

	public String getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(String creationdate) {
		this.creationdate = creationdate;
	}

	public String getCreationbyrole() {
		return creationbyrole;
	}

	public void setCreationbyrole(String creationbyrole) {
		this.creationbyrole = creationbyrole;
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

	public String getPaymenttype() {
		return paymenttype;
	}

	public void setPaymenttype(String paymenttype) {
		this.paymenttype = paymenttype;
	}

}
