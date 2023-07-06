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

import org.jetbrains.annotations.NotNull;

import com.ridsys.rib.DTO.OnlineRechargelistDTO;
import com.ridsys.rib.DTO.OnlinepaymentDTO;
import com.ridsys.rib.DTO.OttuserDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "online_payment_log")

@NamedNativeQuery(name = "Online_payment_log.getOnlinePaymentHistoryAllFtdate", query = "SELECT opl.role,opl.amount,opl.orderid,opl.gatewayid, IF(pt.value is null,'None',pt.value) AS paymenttype,opl.status,opl.tstarttime,concat(op.firstname,' ',op.lastname)AS lconame,if(opl.resmsg is null,'None',opl.resmsg)AS resmsg,if(opl.errormsg is null,'None',opl.errormsg)AS errormsg FROM online_payment_log opl LEFT JOIN operators op ON op.username=opl.username LEFT JOIN payment_type pt on pt.id=opl.paymenttypeid WHERE opl.role='OPERATOR'  AND DATE(opl.tstarttime)>=:fdate AND DATE(opl.tstarttime)<=:tdate union SELECT opl.role,opl.amount,opl.orderid,opl.gatewayid, IF(pt.value is null,' ',pt.value) AS paymenttype,opl.status,opl.tstarttime,(SELECT concat(firstname,' ',lastname)AS lconame FROM operators WHERE id=ui.opid)AS lconame,if(opl.resmsg is null,'None',opl.resmsg)AS resmsg,if(opl.errormsg is null,'None',opl.errormsg)AS errormsg FROM online_payment_log opl LEFT JOIN userinfo ui ON opl.username=ui.username LEFT JOIN payment_type pt on pt.id=opl.paymenttypeid WHERE opl.role='USER' AND DATE(opl.tstarttime)>=:fdate AND DATE(opl.tstarttime)<=:tdate", resultSetMapping = "Mapping.getOnlinePaymentHistoryRS")
@NamedNativeQuery(name = "Online_payment_log.getOnlinePaymentHistoryAll", query = "SELECT opl.role,opl.amount,opl.orderid,opl.gatewayid, IF(pt.value is null,'None',pt.value) AS paymenttype,opl.status,opl.tstarttime,concat(op.firstname,' ',op.lastname)AS lconame,if(opl.resmsg is null,'None',opl.resmsg)AS resmsg,if(opl.errormsg is null,'None',opl.errormsg)AS errormsg FROM online_payment_log opl LEFT JOIN operators op ON op.username=opl.username LEFT JOIN payment_type pt on pt.id=opl.paymenttypeid WHERE opl.role='OPERATOR' union SELECT opl.role,opl.amount,opl.orderid,opl.gatewayid, IF(pt.value is null,' ',pt.value) AS paymenttype,opl.status,opl.tstarttime,(SELECT concat(firstname,' ',lastname)AS lconame FROM operators WHERE id=ui.opid)AS lconame,if(opl.resmsg is null,'None',opl.resmsg)AS resmsg,if(opl.errormsg is null,'None',opl.errormsg)AS errormsg  FROM online_payment_log opl LEFT JOIN userinfo ui ON opl.username=ui.username LEFT JOIN payment_type pt on pt.id=opl.paymenttypeid WHERE opl.role='USER'", resultSetMapping = "Mapping.getOnlinePaymentHistoryRS")
@NamedNativeQuery(name = "Online_payment_log.getOnlinePaymentHistoryFtdate", query = "SELECT opl.role,opl.amount,opl.orderid,opl.gatewayid, IF(pt.value is null,'None',pt.value) AS paymenttype,opl.status,opl.tstarttime,concat(op.firstname,' ',op.lastname)AS lconame,if(opl.resmsg is null,'None',opl.resmsg)AS resmsg,if(opl.errormsg is null,'None',opl.errormsg)AS errormsg FROM online_payment_log opl LEFT JOIN operators op ON op.username=opl.username LEFT JOIN payment_type pt on pt.id=opl.paymenttypeid WHERE opl.role=:role AND opl.username=:username AND DATE(opl.tstarttime)>=:fdate AND DATE(opl.tstarttime)<=:tdate", resultSetMapping = "Mapping.getOnlinePaymentHistoryRS")
@NamedNativeQuery(name = "Online_payment_log.getOnlinePaymentHistory", query = "SELECT opl.role,opl.amount,opl.orderid,opl.gatewayid, IF(pt.value is null,'None',pt.value) AS paymenttype,opl.status,opl.tstarttime,concat(op.firstname,' ',op.lastname)AS lconame,if(opl.resmsg is null,'None',opl.resmsg)AS resmsg,if(opl.errormsg is null,'None',opl.errormsg)AS errormsg FROM online_payment_log opl LEFT JOIN operators op ON op.username=opl.username LEFT JOIN payment_type pt on pt.id=opl.paymenttypeid WHERE opl.role=:role AND opl.username=:username", resultSetMapping = "Mapping.getOnlinePaymentHistoryRS")
@SqlResultSetMapping(name = "Mapping.getOnlinePaymentHistoryRS", classes = {
		@ConstructorResult(targetClass = OnlinepaymentDTO.class, columns = { @ColumnResult(name = "lconame"),
				@ColumnResult(name = "role"), @ColumnResult(name = "amount", type = String.class),
				@ColumnResult(name = "orderid"), @ColumnResult(name = "gatewayid", type = Integer.class),
				@ColumnResult(name = "paymenttype"), @ColumnResult(name = "status"),
				@ColumnResult(name = "tstarttime", type = String.class), @ColumnResult(name = "resmsg"),
				@ColumnResult(name = "errormsg") }) })

@NamedNativeQuery(name = "Online_payment_log.getOnlineUserRechargeHistory", query = "SELECT CONCAT(op.firstname,' ',op.lastname) AS lconame,CONCAT(ui.firstname,' ',ui.lastname) AS fullname,opp.id,opl.orderid,opl.transactionid,opl.status,opl.amount,opl.resmsg,opl.errormsg,opl.username,opl.tstarttime,opl.tendtime,opl.extra1 AS description,opp.transactionid,opp.referenceid,qsp.subplan AS planname,opp.quotaexpirydate,opp.status AS rechargestatus,opp.creationbyusername,opp.creationdate,opp.updateddate  FROM online_payment_log opl ,online_plan_payment opp,quotasubplan qsp,userinfo ui,operators op WHERE opl.orderid=opp.transactionid AND opp.planid=qsp.id AND opl.username=ui.username AND op.id=ui.opid AND opl.username=:username ORDER BY id DESC", resultSetMapping = "Mapping.getOnlineUserRechargeHistoryRS")
@NamedNativeQuery(name = "Online_payment_log.getOnlineUserRechargeHistoryAll", query = "SELECT CONCAT(op.firstname,' ',op.lastname) AS lconame,CONCAT(ui.firstname,' ',ui.lastname) AS fullname,opp.id,opl.orderid,opl.transactionid,opl.status,opl.amount,opl.resmsg,opl.errormsg,opl.username,opl.tstarttime,opl.tendtime,opl.extra1 AS description,opp.transactionid,opp.referenceid,qsp.subplan AS planname,opp.quotaexpirydate,opp.status AS rechargestatus,opp.creationbyusername,opp.creationdate,opp.updateddate  FROM online_payment_log opl ,online_plan_payment opp,quotasubplan qsp,userinfo ui,operators op WHERE opl.orderid=opp.transactionid AND opp.planid=qsp.id AND opl.username=ui.username  AND op.id=ui.opid  ORDER BY id DESC", resultSetMapping = "Mapping.getOnlineUserRechargeHistoryRS")
@NamedNativeQuery(name = "Online_payment_log.getOnlineUserRechargeHistorByOpid", query = "SELECT CONCAT(op.firstname,' ',op.lastname) AS lconame,CONCAT(ui.firstname,' ',ui.lastname) AS fullname,opp.id,opl.orderid,opl.transactionid,opl.status,opl.amount,opl.resmsg,opl.errormsg,opl.username,opl.tstarttime,opl.tendtime,opl.extra1 AS description,opp.transactionid,opp.referenceid,qsp.subplan AS planname,opp.quotaexpirydate,opp.status AS rechargestatus,opp.creationbyusername,opp.creationdate,opp.updateddate  FROM online_payment_log opl ,online_plan_payment opp,quotasubplan qsp,userinfo ui,operators op WHERE opl.orderid=opp.transactionid AND opp.planid=qsp.id AND opl.username=ui.username AND op.id=ui.opid AND op.username=:username  ORDER BY id DESC", resultSetMapping = "Mapping.getOnlineUserRechargeHistoryRS")
@NamedNativeQuery(name = "Online_payment_log.getOnlineUserRechargeHistoryFtdate", query = "SELECT CONCAT(op.firstname,' ',op.lastname) AS lconame,CONCAT(ui.firstname,' ',ui.lastname) AS fullname,opp.id,opl.orderid,opl.transactionid,opl.status,opl.amount,opl.resmsg,opl.errormsg,opl.username,opl.tstarttime,opl.tendtime,opl.extra1 AS description,opp.transactionid,opp.referenceid,qsp.subplan AS planname,opp.quotaexpirydate,opp.status AS rechargestatus,opp.creationbyusername,opp.creationdate,opp.updateddate  FROM online_payment_log opl ,online_plan_payment opp,quotasubplan qsp,userinfo ui,operators op WHERE opl.orderid=opp.transactionid AND opp.planid=qsp.id AND opl.username=ui.username AND op.id=ui.opid  AND opl.username=:username AND DATE(tstarttime)>=:fdate AND DATE(tstarttime)<=:tdate  ORDER BY id DESC", resultSetMapping = "Mapping.getOnlineUserRechargeHistoryRS")
@NamedNativeQuery(name = "Online_payment_log.getOnlineUserRechargeHistoryAllFtdate", query = "SELECT CONCAT(op.firstname,' ',op.lastname) AS lconame,CONCAT(ui.firstname,' ',ui.lastname) AS fullname,opp.id,opl.orderid,opl.transactionid,opl.status,opl.amount,opl.resmsg,opl.errormsg,opl.username,opl.tstarttime,opl.tendtime,opl.extra1 AS description,opp.transactionid,opp.referenceid,qsp.subplan AS planname,opp.quotaexpirydate,opp.status AS rechargestatus,opp.creationbyusername,opp.creationdate,opp.updateddate  FROM online_payment_log opl ,online_plan_payment opp,quotasubplan qsp,userinfo ui,operators op WHERE opl.orderid=opp.transactionid AND opp.planid=qsp.id AND op.id=ui.opid  AND opl.username=ui.username AND  DATE(tstarttime)>=:fdate AND DATE(tstarttime)<=:tdate  ORDER BY id DESC", resultSetMapping = "Mapping.getOnlineUserRechargeHistoryRS")
@NamedNativeQuery(name = "Online_payment_log.getOnlineUserRechargeHistoryByOpidFtdate", query = "SELECT CONCAT(op.firstname,' ',op.lastname) AS lconame,CONCAT(ui.firstname,' ',ui.lastname) AS fullname,opp.id,opl.orderid,opl.transactionid,opl.status,opl.amount,opl.resmsg,opl.errormsg,opl.username,opl.tstarttime,opl.tendtime,opl.extra1 AS description,opp.transactionid,opp.referenceid,qsp.subplan AS planname,opp.quotaexpirydate,opp.status AS rechargestatus,opp.creationbyusername,opp.creationdate,opp.updateddate  FROM online_payment_log opl ,online_plan_payment opp,quotasubplan qsp,userinfo ui,operators op WHERE opl.orderid=opp.transactionid AND opp.planid=qsp.id AND opl.username=ui.username AND op.id=ui.opid AND op.username=:username AND  DATE(tstarttime)>=:fdate AND DATE(tstarttime)<=:tdate  ORDER BY id DESC", resultSetMapping = "Mapping.getOnlineUserRechargeHistoryRS")
@SqlResultSetMapping(name = "Mapping.getOnlineUserRechargeHistoryRS", classes = {
		@ConstructorResult(targetClass = OnlineRechargelistDTO.class, columns = {
				@ColumnResult(name = "id", type = Integer.class), @ColumnResult(name = "orderid"),
				@ColumnResult(name = "transactionid", type = String.class), @ColumnResult(name = "status"),
				@ColumnResult(name = "amount", type = String.class), @ColumnResult(name = "resmsg"),
				@ColumnResult(name = "errormsg"), @ColumnResult(name = "username", type = String.class),
				@ColumnResult(name = "tstarttime", type = String.class),
				@ColumnResult(name = "tendtime", type = String.class), @ColumnResult(name = "description"),
				@ColumnResult(name = "planname"), @ColumnResult(name = "referenceid"),
				@ColumnResult(name = "quotaexpirydate", type = String.class), @ColumnResult(name = "rechargestatus"),
				@ColumnResult(name = "creationbyusername"), @ColumnResult(name = "creationdate", type = String.class),
				@ColumnResult(name = "fullname"), @ColumnResult(name = "updateddate", type = String.class),
				@ColumnResult(name = "lconame") }) })

//

public class Online_payment_log {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int gatewayid;

	@NotNull
	private String orderid;

	private String transactionid;
	private String status;

	@NotNull
	private String amount;

	private String resmsg;
	private String errormsg;

	@NotNull
	private String role;

	@NotNull
	private String username;

	private int paymenttypeid;
	private String tstarttime;
	private String tendtime;
	private String tupdatetime;
	private String response;
	private String extra1;
	private String extra2;
	private String paymentid;
	private int rechargetypeid;
	private String mode;

	public Online_payment_log() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Online_payment_log(int gatewayid, String orderid, String transactionid, String status, String amount,
			String resmsg, String errormsg, String role, String username, int paymenttypeid, String tstarttime,
			String tendtime, String tupdatetime, String response, String extra1, String extra2, String paymentid,
			int rechargetypeid, String mode) {
		super();
		this.gatewayid = gatewayid;
		this.orderid = orderid;
		this.transactionid = transactionid;
		this.status = status;
		this.amount = amount;
		this.resmsg = resmsg;
		this.errormsg = errormsg;
		this.role = role;
		this.username = username;
		this.paymenttypeid = paymenttypeid;
		this.tstarttime = tstarttime;
		this.tendtime = tendtime;
		this.tupdatetime = tupdatetime;
		this.response = response;
		this.extra1 = extra1;
		this.extra2 = extra2;
		this.paymentid = paymentid;
		this.rechargetypeid = rechargetypeid;
		this.mode = mode;
	}

	public int getRechargetypeid() {
		return rechargetypeid;
	}

	public void setRechargetypeid(int rechargetypeid) {
		this.rechargetypeid = rechargetypeid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGatewayid() {
		return gatewayid;
	}

	public void setGatewayid(int gatewayid) {
		this.gatewayid = gatewayid;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getTransactionid() {
		return transactionid;
	}

	public void setTransactionid(String transactionid) {
		this.transactionid = transactionid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getResmsg() {
		return resmsg;
	}

	public void setResmsg(String resmsg) {
		this.resmsg = resmsg;
	}

	public String getErrormsg() {
		return errormsg;
	}

	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
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

	public String getTstarttime() {
		return tstarttime;
	}

	public void setTstarttime(String tstarttime) {
		this.tstarttime = tstarttime;
	}

	public String getTendtime() {
		return tendtime;
	}

	public void setTendtime(String tendtime) {
		this.tendtime = tendtime;
	}

	public String getTupdatetime() {
		return tupdatetime;
	}

	public void setTupdatetime(String tupdatetime) {
		this.tupdatetime = tupdatetime;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getExtra1() {
		return extra1;
	}

	public void setExtra1(String extra1) {
		this.extra1 = extra1;
	}

	public String getExtra2() {
		return extra2;
	}

	public void setExtra2(String extra2) {
		this.extra2 = extra2;
	}

	public String getPaymentid() {
		return paymentid;
	}

	public void setPaymentid(String paymentid) {
		this.paymentid = paymentid;
	}
	
	

}
