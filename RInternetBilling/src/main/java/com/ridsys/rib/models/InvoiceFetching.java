package com.ridsys.rib.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.ridsys.rib.DTO.UserProfileInfoDTO;

@Entity
@Table(name = "invoice")
@NamedNativeQuery(name = "InvoiceFetching.getUserDetails", query = "SELECT CONCAT(ui.firstname,' ',ui.lastname) AS fullname,ui.username,op.username AS opname,op.id AS opid,(SELECT subPlan FROM quotasubplan WHERE id=(SELECT planid FROM quotaplanmanagelog WHERE rechargeid=:referenceid)) AS planName  FROM userinfo ui,operators op WHERE op.id=ui.opid AND ui.id=:userid", resultSetMapping = "Mapping.getUserDetailsRS")
@SqlResultSetMapping(name = "Mapping.getUserDetailsRS", classes = {
		@ConstructorResult(targetClass = InvoiceFetching.class, columns = { @ColumnResult(name = "fullname"),
				@ColumnResult(name = "username"), @ColumnResult(name = "opname"),
				@ColumnResult(name = "opid", type = Integer.class), @ColumnResult(name = "planName") }) })
public class InvoiceFetching {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "user_id")
	private int userid;

	private int invoice_no;
	private String referenceid;
	private String financial_year;
	private int type_id;
	private int status_id;

	@Column(name = "invoice_date")
	private String invoiceDate;

	private String invoice_period;
	private String due_date;
	private String previous_due;
	private String payment_recevied;
	private String adjustments;
	private String invoice_amount;
	private String balance_amount;
	private String packet_amount_balance;
	private String creationdate;
	private String updatedate;
	private boolean is_delete;
	private String gstin;

	@Transient
	private String fullname;

	@Transient
	private String username;

	@Transient
	private String opname;

	@Transient
	private int opid;

	@Transient
	private String planName;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "invoice_id")
	private Set<Invoice_items> invoice_items = new HashSet<Invoice_items>();

	public InvoiceFetching() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InvoiceFetching(int id, int userid, int invoice_no, String referenceid, String financial_year, int type_id,
			int status_id, String invoice_date, String invoice_period, String due_date, String previous_due,
			String payment_recevied, String adjustments, String invoice_amount, String balance_amount,
			String packet_amount_balance, String creationdate, String updatedate, boolean is_delete,
			Set<Invoice_items> invoice_items, String fullname, String username, String opname, int opid,
			String planName, String gstin) {
		super();

		System.out.println(fullname + username + opname + invoice_no);
		this.id = id;
		this.userid = userid;
		this.invoice_no = invoice_no;
		this.referenceid = referenceid;
		this.financial_year = financial_year;
		this.type_id = type_id;
		this.status_id = status_id;
		this.invoiceDate = invoice_date;
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
		this.invoice_items = invoice_items;
		this.fullname = fullname;
		this.username = username;
		this.opname = opname;
		this.opid = opid;
		this.planName = planName;
		this.gstin = gstin;
	}

	public InvoiceFetching(String fullname, String username, String opname, int opid, String planName) {
		super();
		this.fullname = fullname;
		this.username = username;
		this.opname = opname;
		this.opid = opid;
		this.planName = planName;
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

	public String getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
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

	public String getPrevious_due() {
		return previous_due;
	}

	public void setPrevious_due(String previous_due) {
		this.previous_due = previous_due;
	}

	public String getPayment_recevied() {
		return payment_recevied;
	}

	public void setPayment_recevied(String payment_recevied) {
		this.payment_recevied = payment_recevied;
	}

	public String getAdjustments() {
		return adjustments;
	}

	public void setAdjustments(String adjustments) {
		this.adjustments = adjustments;
	}

	public String getInvoice_amount() {
		return invoice_amount;
	}

	public void setInvoice_amount(String invoice_amount) {
		this.invoice_amount = invoice_amount;
	}

	public String getBalance_amount() {
		return balance_amount;
	}

	public void setBalance_amount(String balance_amount) {
		this.balance_amount = balance_amount;
	}

	public String getPacket_amount_balance() {
		return packet_amount_balance;
	}

	public void setPacket_amount_balance(String packet_amount_balance) {
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

	public Set<Invoice_items> getInvoice_items() {
		return invoice_items;
	}

	public void setInvoice_items(Set<Invoice_items> invoice_items) {
		this.invoice_items = invoice_items;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getOpname() {
		return opname;
	}

	public void setOpname(String opname) {
		this.opname = opname;
	}

	public int getOpid() {
		return opid;
	}

	public void setOpid(int opid) {
		this.opid = opid;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

}
