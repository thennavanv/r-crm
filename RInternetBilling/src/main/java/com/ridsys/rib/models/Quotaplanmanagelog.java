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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ridsys.rib.DTO.QuotaplanmanageloghistoryDTO;
import com.ridsys.rib.DTO.SubscriptionInfoDTO;

@Entity
@Table(name = "quotaplanmanagelog")
@NamedNativeQuery(name = "Quotaplanmanagelog.getQuotaPlanManageLogHistoryByAllOpidFtdate", query = "SELECT if(qpl.creationby='USER','Online','Offline') AS rechargetype,qpl.rechargeid,qpl.creationby,concat(ui.firstname,' ',ui.lastname) customername,qpl.username,qpl.creationdate,qpl.quotaexpirydate,qpl.planCost,qpl.planTax,qpl.planMrp,qpl.planDiscount,qpl.planCusCost,qpl.planmsocost,qpl.planLcoComm,wul.oldbalance,wul.newbalance,qpl.description,qsp.subplan as planName,qsp.id AS planid,CONCAT((qsp.planTimeBank/30),' ','Month') AS planDays,(SELECT CONCAT(firstname,' ',lastname) AS opname FROM operators WHERE id=ui.opid) AS opname,CASE WHEN qpl.creationbyusername IS NOT NULL THEN (IF(qpl.creationby='OPERATOR',(SELECT CONCAT(firstname,' ',lastname) FROM operators WHERE username=qpl.creationbyusername),(SELECT CONCAT(firstname,' ',lastname) FROM userinfo WHERE username=qpl.creationbyusername))) ELSE '' END AS creationByUsername FROM quotaplanmanagelog qpl,quotasubplan qsp,walletupdatelog wul,userinfo ui WHERE ui.username=qpl.username and wul.referenceid=qpl.rechargeid and qpl.creationby=:role AND qsp.id=qpl.planid AND DATE(qpl.creationdate)>=:fdate AND DATE(qpl.creationdate)<=:tdate GROUP BY qpl.rechargeid ORDER BY qpl.creationdate DESC", resultSetMapping = "Mapping.QuotaplanmanageloghistoryDTO")
@NamedNativeQuery(name = "Quotaplanmanagelog.getQuotaPlanManageLogHistoryByAllOpid", query = "SELECT if(qpl.creationby='USER','Online','Offline') AS rechargetype,qpl.rechargeid,qpl.creationby,concat(ui.firstname,' ',ui.lastname) customername,qpl.username,qpl.creationdate,qpl.quotaexpirydate,qpl.planCost,qpl.planTax,qpl.planMrp,qpl.planDiscount,qpl.planCusCost,qpl.planmsocost,qpl.planLcoComm,wul.oldbalance,wul.newbalance,qpl.description,qsp.subplan as planName,qsp.id AS planid,CONCAT((qsp.planTimeBank/30),' ','Month') AS planDays,(SELECT CONCAT(firstname,' ',lastname) AS opname FROM operators WHERE id=ui.opid) AS opname,CASE WHEN qpl.creationbyusername IS NOT NULL THEN (IF(qpl.creationby='OPERATOR',(SELECT CONCAT(firstname,' ',lastname) FROM operators WHERE username=qpl.creationbyusername),(SELECT CONCAT(firstname,' ',lastname) FROM userinfo WHERE username=qpl.creationbyusername))) ELSE '' END AS creationByUsername FROM quotaplanmanagelog qpl,quotasubplan qsp,walletupdatelog wul,userinfo ui WHERE ui.username=qpl.username and wul.referenceid=qpl.rechargeid and qpl.creationby=:role AND qsp.id=qpl.planid GROUP BY qpl.rechargeid ORDER BY qpl.creationdate DESC", resultSetMapping = "Mapping.QuotaplanmanageloghistoryDTO")
@NamedNativeQuery(name = "Quotaplanmanagelog.getQuotaPlanManageLogHistoryByRoleCreationbyusernameFtdate", query = "SELECT if(qpl.creationby='USER','Online','Offline') AS rechargetype,qpl.rechargeid,qpl.creationby,concat(ui.firstname,' ',ui.lastname) customername,qpl.username,qpl.creationdate,qpl.quotaexpirydate,qpl.planCost,qpl.planTax,qpl.planMrp,qpl.planDiscount,qpl.planCusCost,qpl.planmsocost,qpl.planLcoComm,wul.oldbalance,wul.newbalance,qpl.description,qsp.subplan as planName,qsp.id AS planid,CONCAT((qsp.planTimeBank/30),' ','Month') AS planDays,(SELECT CONCAT(firstname,' ',lastname) AS opname FROM operators WHERE id=ui.opid) AS opname,CASE WHEN qpl.creationbyusername IS NOT NULL THEN (IF(qpl.creationby='OPERATOR',(SELECT CONCAT(firstname,' ',lastname) FROM operators WHERE username=qpl.creationbyusername),(SELECT CONCAT(firstname,' ',lastname) FROM userinfo WHERE username=qpl.creationbyusername))) ELSE '' END AS creationByUsername FROM quotaplanmanagelog qpl,quotasubplan qsp,walletupdatelog wul,userinfo ui left join operators op on op.id=ui.opid WHERE op.username=:username and ui.opid=op.id and ui.username=qpl.username and wul.referenceid=qpl.rechargeid and (qpl.creationbyusername=:username or qpl.creationbyusername=ui.username)  AND qsp.id=qpl.planid  AND DATE(qpl.creationdate)>=:fdate AND DATE(qpl.creationdate)<=:tdate GROUP BY qpl.rechargeid ORDER BY qpl.creationdate DESC", resultSetMapping = "Mapping.QuotaplanmanageloghistoryDTO")
@NamedNativeQuery(name = "Quotaplanmanagelog.getQuotaPlanManageLogHistoryByRoleCreationbyusername", query = "SELECT if(qpl.creationby='USER','Online','Offline') AS rechargetype,qpl.rechargeid,qpl.creationby,concat(ui.firstname,' ',ui.lastname) customername,qpl.username,qpl.creationdate,qpl.quotaexpirydate,qpl.planCost,qpl.planTax,qpl.planMrp,qpl.planDiscount,qpl.planCusCost,qpl.planmsocost,qpl.planLcoComm,wul.oldbalance,wul.newbalance,qpl.description,qsp.subplan as planName,qsp.id AS planid,CONCAT((qsp.planTimeBank/30),' ','Month') AS planDays,(SELECT CONCAT(firstname,' ',lastname) AS opname FROM operators WHERE id=ui.opid) AS opname,CASE WHEN qpl.creationbyusername IS NOT NULL THEN (IF(qpl.creationby='OPERATOR',(SELECT CONCAT(firstname,' ',lastname) FROM operators WHERE username=qpl.creationbyusername),(SELECT CONCAT(firstname,' ',lastname) FROM userinfo WHERE username=qpl.creationbyusername))) ELSE '' END AS creationByUsername FROM quotaplanmanagelog qpl,quotasubplan qsp,walletupdatelog wul,userinfo ui left join operators op on op.id=ui.opid WHERE op.username=:username and ui.opid=op.id and ui.username=qpl.username and wul.referenceid=qpl.rechargeid and (qpl.creationbyusername=:username or qpl.creationbyusername=ui.username)  AND qsp.id=qpl.planid GROUP BY qpl.rechargeid ORDER BY qpl.creationdate DESC", resultSetMapping = "Mapping.QuotaplanmanageloghistoryDTO")
@NamedNativeQuery(name = "Quotaplanmanagelog.getQuotaPlanManageLogHistoryFtdate", query = "SELECT if(qpl.creationby='USER','Online','Offline') AS rechargetype,qpl.rechargeid,qpl.creationby,concat(ui.firstname,' ',ui.lastname)customername,qpl.username,qpl.creationdate,qpl.quotaexpirydate,qpl.planCost,qpl.planTax,qpl.planMrp,qpl.planDiscount,qpl.planCusCost,qpl.planmsocost,qpl.planLcoComm,wul.oldbalance,wul.newbalance,qpl.description,qsp.subplan AS planName,qsp.id AS planid,CONCAT((qsp.planTimeBank/30),' ','Month') AS planDays,(SELECT CONCAT(firstname,' ',lastname) AS opname FROM operators WHERE id=ui.opid) AS opname,CASE WHEN qpl.creationbyusername IS NOT NULL THEN (IF(qpl.creationby='OPERATOR',(SELECT CONCAT(firstname,' ',lastname) FROM operators WHERE username=qpl.creationbyusername),(SELECT CONCAT(firstname,' ',lastname) FROM userinfo WHERE username=qpl.creationbyusername))) ELSE '' END AS creationByUsername FROM quotaplanmanagelog qpl,quotasubplan qsp,walletupdatelog wul,userinfo ui WHERE ui.username=qpl.username AND wul.referenceid=qpl.rechargeid AND qsp.id=qpl.planid AND  DATE(qpl.creationdate)>=:fdate AND DATE(qpl.creationdate)<=:tdate GROUP BY qpl.rechargeid ORDER BY qpl.creationdate DESC", resultSetMapping = "Mapping.QuotaplanmanageloghistoryDTO")
@NamedNativeQuery(name = "Quotaplanmanagelog.getQuotaPlanManageLogHistory", query = "SELECT if(qpl.creationby='USER','Online','Offline') AS rechargetype,qpl.rechargeid,qpl.creationby,concat(ui.firstname,' ',ui.lastname)customername,qpl.username,qpl.creationdate,qpl.quotaexpirydate,qpl.planCost,qpl.planTax,qpl.planMrp,qpl.planDiscount,qpl.planCusCost,qpl.planmsocost,qpl.planLcoComm,wul.oldbalance,wul.newbalance,qpl.description,qsp.subplan AS planName,qsp.id AS planid,CONCAT((qsp.planTimeBank/30),' ','Month') AS planDays,(SELECT CONCAT(firstname,' ',lastname) AS opname FROM operators WHERE id=ui.opid) AS opname ,CASE WHEN qpl.creationbyusername IS NOT NULL THEN (IF(qpl.creationby='OPERATOR',(SELECT CONCAT(firstname,' ',lastname) FROM operators WHERE username=qpl.creationbyusername),(SELECT CONCAT(firstname,' ',lastname) FROM userinfo WHERE username=qpl.creationbyusername))) ELSE '' END AS creationByUsername FROM quotaplanmanagelog qpl,quotasubplan qsp,walletupdatelog wul,userinfo ui WHERE ui.username=qpl.username AND wul.referenceid=qpl.rechargeid AND qsp.id=qpl.planid  AND IF(qpl.creationby='USER', wul.walletupdatetypeid IN(3,4), wul.walletupdatetypeid IN(1,2,3,4))  GROUP BY qpl.rechargeid ORDER BY qpl.creationdate DESC", resultSetMapping = "Mapping.QuotaplanmanageloghistoryDTO")
@NamedNativeQuery(name = "Quotaplanmanagelog.getQuotaPlanManageLogHistoryByUserFtdate", query = "SELECT if(qpl.creationby='USER','Online','Offline') AS rechargetype,qpl.rechargeid,qpl.creationby,concat(ui.firstname,' ',ui.lastname) customername,qpl.username,qpl.creationdate,qpl.quotaexpirydate,qpl.planCost,qpl.planTax,qpl.planMrp,qpl.planDiscount,qpl.planCusCost,qpl.planmsocost,qpl.planLcoComm,wul.oldbalance,wul.newbalance,qpl.description,qsp.subplan as planName,qsp.id AS planid,CONCAT((qsp.planTimeBank/30),' ','Month') AS planDays,(SELECT CONCAT(firstname,' ',lastname) AS opname FROM operators WHERE id=ui.opid) AS opname,CASE WHEN qpl.creationbyusername IS NOT NULL THEN (IF(qpl.creationby='OPERATOR',(SELECT CONCAT(firstname,' ',lastname) FROM operators WHERE username=qpl.creationbyusername),(SELECT CONCAT(firstname,' ',lastname) FROM userinfo WHERE username=qpl.creationbyusername))) ELSE '' END AS creationByUsername FROM quotaplanmanagelog qpl,quotasubplan qsp,walletupdatelog wul,userinfo ui WHERE ui.username=qpl.username and wul.referenceid=qpl.rechargeid and qpl.username=:username AND qsp.id=qpl.planid  AND IF(qpl.creationby='USER', wul.walletupdatetypeid IN(3,4), wul.walletupdatetypeid IN(1,2,3,4)) AND DATE(qpl.creationdate)>=:fdate AND DATE(qpl.creationdate)<=:tdate GROUP BY qpl.rechargeid ORDER BY qpl.creationdate DESC", resultSetMapping = "Mapping.QuotaplanmanageloghistoryDTO")
@NamedNativeQuery(name = "Quotaplanmanagelog.getQuotaPlanManageLogHistoryByUser", query = "SELECT if(qpl.creationby='USER','Online','Offline') AS rechargetype,qpl.rechargeid,qpl.creationby,concat(ui.firstname,' ',ui.lastname) customername,qpl.username,qpl.creationdate,qpl.quotaexpirydate,qpl.planCost,qpl.planTax,qpl.planMrp,qpl.planDiscount,qpl.planCusCost,qpl.planmsocost,qpl.planLcoComm,wul.oldbalance,wul.newbalance,qpl.description,qsp.subplan as planName,qsp.id AS planid,CONCAT((qsp.planTimeBank/30),' ','Month') AS planDays,(SELECT CONCAT(firstname,' ',lastname) AS opname FROM operators WHERE id=ui.opid) AS opname,CASE WHEN qpl.creationbyusername IS NOT NULL THEN (IF(qpl.creationby='OPERATOR',(SELECT CONCAT(firstname,' ',lastname) FROM operators WHERE username=qpl.creationbyusername),(SELECT CONCAT(firstname,' ',lastname) FROM userinfo WHERE username=qpl.creationbyusername))) ELSE '' END AS creationByUsername FROM quotaplanmanagelog qpl,quotasubplan qsp,walletupdatelog wul,userinfo ui WHERE ui.username=qpl.username and wul.referenceid=qpl.rechargeid and qpl.username=:username AND qsp.id=qpl.planid GROUP BY qpl.rechargeid ORDER BY qpl.creationdate DESC", resultSetMapping = "Mapping.QuotaplanmanageloghistoryDTO")
@NamedNativeQuery(name = "Quotaplanmanagelog.getSubscriptionInfoByCreationbyusername", query = "select date_format(creationdate,'%Y-%M-%d') as creationdate,date_format(quotaexpirydate,'%Y-%M-%d') as quotaexpirydate,DATE(quotaexpirydate) quotaexpirydate1,DATEDIFF(DATE(quotaexpirydate), DATE(creationdate))+1 quotadays,if(DATEDIFF(DATE(quotaexpirydate), DATE(NOW()))<0,0,DATEDIFF(DATE(quotaexpirydate), DATE(NOW()))) remainquotadays from quotaplanmanagelog where username=:username limit 1", resultSetMapping = "Mapping.SubscriptionInfo")
@NamedNativeQuery(name = "Quotaplanmanagelog.getDatausageByCreationbyusername", query = "select if(sum(acctinputoctets) is null,0,sum(acctinputoctets)) as uploadbw,if(sum(acctoutputoctets) is null,0,sum(acctoutputoctets)) as downloadbw from radacct ra,(select creationdate from quotaplanmanagelog where username=:username order by id desc limit 1) qp where ra.username=:username and ra.acctstarttime>=qp.creationdate", resultSetMapping = "Mapping.DatausageInfo")

@SqlResultSetMapping(name = "Mapping.QuotaplanmanageloghistoryDTO", classes = {
		@ConstructorResult(targetClass = QuotaplanmanageloghistoryDTO.class, columns = {
				@ColumnResult(name = "rechargeid"), @ColumnResult(name = "customername"),
				@ColumnResult(name = "username"), @ColumnResult(name = "creationdate", type = String.class),
				@ColumnResult(name = "quotaexpirydate", type = String.class), @ColumnResult(name = "planCost"),
				@ColumnResult(name = "planTax"), @ColumnResult(name = "planMrp"), @ColumnResult(name = "planDiscount"),
				@ColumnResult(name = "planCusCost"), @ColumnResult(name = "planmsocost"),
				@ColumnResult(name = "planLcoComm"), @ColumnResult(name = "oldbalance"),
				@ColumnResult(name = "newbalance"), @ColumnResult(name = "description"),
				@ColumnResult(name = "planName"), @ColumnResult(name = "planid"),
				@ColumnResult(name = "planDays", type = String.class), @ColumnResult(name = "opname"),
				@ColumnResult(name = "creationByUsername"), @ColumnResult(name = "creationBy"),@ColumnResult(name="rechargetype") }) })

@SqlResultSetMapping(name = "Mapping.SubscriptionInfo", classes = {
		@ConstructorResult(targetClass = SubscriptionInfoDTO.class, columns = {
				@ColumnResult(name = "creationdate", type = String.class),
				@ColumnResult(name = "quotaexpirydate", type = String.class),
				@ColumnResult(name = "quotaexpirydate1", type = String.class),
				@ColumnResult(name = "quotadays", type = Integer.class),
				@ColumnResult(name = "remainquotadays", type = Integer.class) }) })

@SqlResultSetMapping(name = "Mapping.DatausageInfo", classes = {
		@ConstructorResult(targetClass = SubscriptionInfoDTO.class, columns = {
				@ColumnResult(name = "uploadbw", type = String.class),
				@ColumnResult(name = "downloadbw", type = String.class) }) })

@NamedNativeQuery(name = "Quotaplanmanagelog.getQuotaPlanManageLogHistoryByAllUser", query = "SELECT qpl.rechargeid,qpl.creationby,concat(ui.firstname,' ',ui.lastname) customername,qpl.username,qpl.creationdate,qpl.quotaexpirydate,qpl.planCost,qpl.planTax,qpl.planMrp,qpl.planDiscount,qpl.planCusCost,qpl.planmsocost,qpl.planLcoComm,wul.oldbalance,wul.newbalance,qpl.description,qsp.subplan as planName,qsp.id AS planid,CONCAT((qsp.planTimeBank/30),' ','Month') AS planDays,(SELECT CONCAT(firstname,' ',lastname) AS opname FROM operators WHERE id=ui.opid) AS opname,CASE WHEN qpl.creationbyusername IS NOT NULL THEN (IF(qpl.creationby='OPERATOR',(SELECT CONCAT(firstname,' ',lastname) FROM operators WHERE username=qpl.creationbyusername),(SELECT CONCAT(firstname,' ',lastname) FROM userinfo WHERE username=qpl.creationbyusername))) ELSE '' END AS creationByUsername,opw.oldbalance AS oproldbalance,opw.newbalance AS oprnewbalance FROM quotaplanmanagelog qpl   LEFT JOIN(SELECT referenceid,paymentbyusername,oldbalance,amount,newbalance from walletupdatelog WHERE role='OPERATOR') as opw ON opw.referenceid=qpl.rechargeid AND qpl.username=opw.paymentbyusername ,quotasubplan qsp,walletupdatelog wul,userinfo ui WHERE ui.username=qpl.username and wul.referenceid=qpl.rechargeid AND qpl.creationby=:role AND wul.walletupdatetypeid in(3,4) AND qsp.id=qpl.planid GROUP BY qpl.rechargeid ORDER BY qpl.creationdate DESC", resultSetMapping = "Mapping.getUserPlanHistoryRS")
@NamedNativeQuery(name = "Quotaplanmanagelog.getQuotaPlanManageLogHistoryByAllUserFtdate", query = "SELECT qpl.rechargeid,qpl.creationby,concat(ui.firstname,' ',ui.lastname) customername,qpl.username,qpl.creationdate,qpl.quotaexpirydate,qpl.planCost,qpl.planTax,qpl.planMrp,qpl.planDiscount,qpl.planCusCost,qpl.planmsocost,qpl.planLcoComm,wul.oldbalance,wul.newbalance,qpl.description,qsp.subplan as planName,qsp.id AS planid,CONCAT((qsp.planTimeBank/30),' ','Month') AS planDays,(SELECT CONCAT(firstname,' ',lastname) AS opname FROM operators WHERE id=ui.opid) AS opname,CASE WHEN qpl.creationbyusername IS NOT NULL THEN (IF(qpl.creationby='OPERATOR',(SELECT CONCAT(firstname,' ',lastname) FROM operators WHERE username=qpl.creationbyusername),(SELECT CONCAT(firstname,' ',lastname) FROM userinfo WHERE username=qpl.creationbyusername))) ELSE '' END AS creationByUsername,opw.oldbalance AS oproldbalance,opw.newbalance AS oprnewbalance FROM quotaplanmanagelog qpl LEFT JOIN(SELECT referenceid,paymentbyusername,oldbalance,amount,newbalance from walletupdatelog WHERE role='OPERATOR') as opw ON opw.referenceid=qpl.rechargeid AND qpl.username=opw.paymentbyusername,quotasubplan qsp,walletupdatelog wul,userinfo ui WHERE ui.username=qpl.username and wul.referenceid=qpl.rechargeid  AND qpl.creationby=:role AND wul.walletupdatetypeid in(3,4) AND qsp.id=qpl.planid AND DATE(qpl.creationdate)>=:fdate AND DATE(qpl.creationdate)<=:tdate GROUP BY qpl.rechargeid ORDER BY qpl.creationdate DESC", resultSetMapping = "Mapping.getUserPlanHistoryRS")
@NamedNativeQuery(name = "Quotaplanmanagelog.getQuotaPlanManageLogHistoryByCreationUser", query = "SELECT qpl.rechargeid,qpl.creationby,concat(ui.firstname,' ',ui.lastname) customername,qpl.username,qpl.creationdate,qpl.quotaexpirydate,qpl.planCost,qpl.planTax,qpl.planMrp,qpl.planDiscount,qpl.planCusCost,qpl.planmsocost,qpl.planLcoComm,wul.oldbalance,wul.newbalance,qpl.description,qsp.subplan as planName,qsp.id AS planid,CONCAT((qsp.planTimeBank/30),' ','Month') AS planDays,(SELECT CONCAT(firstname,' ',lastname) AS opname FROM operators WHERE id=ui.opid) AS opname,CASE WHEN qpl.creationbyusername IS NOT NULL THEN (IF(qpl.creationby='OPERATOR',(SELECT CONCAT(firstname,' ',lastname) FROM operators WHERE username=qpl.creationbyusername),(SELECT CONCAT(firstname,' ',lastname) FROM userinfo WHERE username=qpl.creationbyusername))) ELSE '' END AS creationByUsername,opw.oldbalance AS oproldbalance,opw.newbalance AS oprnewbalance FROM quotaplanmanagelog qpl LEFT JOIN(SELECT referenceid,paymentbyusername,oldbalance,amount,newbalance from walletupdatelog WHERE role='OPERATOR') as opw ON opw.referenceid=qpl.rechargeid AND qpl.username=opw.paymentbyusername ,quotasubplan qsp,walletupdatelog wul,userinfo ui WHERE ui.username=qpl.username and wul.referenceid=qpl.rechargeid and qpl.username=:username AND qpl.creationby=:role AND wul.walletupdatetypeid in(3,4) AND qsp.id=qpl.planid GROUP BY qpl.rechargeid ORDER BY qpl.creationdate DESC", resultSetMapping = "Mapping.getUserPlanHistoryRS")
@NamedNativeQuery(name = "Quotaplanmanagelog.getQuotaPlanManageLogHistoryByCreationUserFtdate", query = "SELECT qpl.rechargeid,qpl.creationby,concat(ui.firstname,' ',ui.lastname) customername,qpl.username,qpl.creationdate,qpl.quotaexpirydate,qpl.planCost,qpl.planTax,qpl.planMrp,qpl.planDiscount,qpl.planCusCost,qpl.planmsocost,qpl.planLcoComm,wul.oldbalance,wul.newbalance,qpl.description,qsp.subplan as planName,qsp.id AS planid,CONCAT((qsp.planTimeBank/30),' ','Month') AS planDays,(SELECT CONCAT(firstname,' ',lastname) AS opname FROM operators WHERE id=ui.opid) AS opname,CASE WHEN qpl.creationbyusername IS NOT NULL THEN (IF(qpl.creationby='OPERATOR',(SELECT CONCAT(firstname,' ',lastname) FROM operators WHERE username=qpl.creationbyusername),(SELECT CONCAT(firstname,' ',lastname) FROM userinfo WHERE username=qpl.creationbyusername))) ELSE '' END AS creationByUsername,opw.oldbalance AS oproldbalance,opw.newbalance AS oprnewbalance FROM quotaplanmanagelog qpl LEFT JOIN(SELECT referenceid,paymentbyusername,oldbalance,amount,newbalance from walletupdatelog WHERE role='OPERATOR') as opw ON opw.referenceid=qpl.rechargeid AND qpl.username=opw.paymentbyusername,quotasubplan qsp,walletupdatelog wul,userinfo ui WHERE ui.username=qpl.username and wul.referenceid=qpl.rechargeid and qpl.username=:username AND qpl.creationby=:role  AND wul.walletupdatetypeid in(3,4) AND qsp.id=qpl.planid AND DATE(qpl.creationdate)>=:fdate AND DATE(qpl.creationdate)<=:tdate GROUP BY qpl.rechargeid ORDER BY qpl.creationdate DESC", resultSetMapping = "Mapping.getUserPlanHistoryRS")

@SqlResultSetMapping(name = "Mapping.getUserPlanHistoryRS", classes = {
		@ConstructorResult(targetClass = QuotaplanmanageloghistoryDTO.class, columns = {
				@ColumnResult(name = "rechargeid"), @ColumnResult(name = "customername"),
				@ColumnResult(name = "username"), @ColumnResult(name = "creationdate", type = String.class),
				@ColumnResult(name = "quotaexpirydate", type = String.class), @ColumnResult(name = "planCost"),
				@ColumnResult(name = "planTax"), @ColumnResult(name = "planMrp"), @ColumnResult(name = "planDiscount"),
				@ColumnResult(name = "planCusCost"), @ColumnResult(name = "planmsocost"),
				@ColumnResult(name = "planLcoComm"), @ColumnResult(name = "oldbalance"),
				@ColumnResult(name = "newbalance"), @ColumnResult(name = "description"),
				@ColumnResult(name = "planName"), @ColumnResult(name = "planid"),
				@ColumnResult(name = "planDays", type = String.class), @ColumnResult(name = "opname"),
				@ColumnResult(name = "creationByUsername"), @ColumnResult(name = "creationBy"),
				@ColumnResult(name = "oproldbalance", type = String.class),
				@ColumnResult(name = "oprnewbalance", type = String.class) }) })

public class Quotaplanmanagelog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String rechargeid;
	private String username;
	private String planid;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String creationdate;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String quotaexpirydate;

	private String plantimebank;
	private String plancost;
	private String plansetupcost;
	private String plantax;
	private String planmrp;
	private String plandiscount;
	private String plancuscost;
	private String planmsocost;
	private String planlcocomm;
	private String creationby;
	private String creationbyusername;
	private String description;

	public Quotaplanmanagelog() {
		super();
	}

	public Quotaplanmanagelog(String rechargeid, String username, String planid, String creationdate,
			String quotaexpirydate, String plantimebank, String plancost, String plansetupcost, String plantax,
			String planmrp, String plandiscount, String plancuscost, String planmsocost, String planlcocomm,
			String creationby, String creationbyusername, String description) {
		super();
		this.rechargeid = rechargeid;
		this.username = username;
		this.planid = planid;
		this.creationdate = creationdate;
		this.quotaexpirydate = quotaexpirydate;
		this.plantimebank = plantimebank;
		this.plancost = plancost;
		this.plansetupcost = plansetupcost;
		this.plantax = plantax;
		this.planmrp = planmrp;
		this.plandiscount = plandiscount;
		this.plancuscost = plancuscost;
		this.planmsocost = planmsocost;
		this.planlcocomm = planlcocomm;
		this.creationby = creationby;
		this.creationbyusername = creationbyusername;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRechargeid() {
		return rechargeid;
	}

	public void setRechargeid(String rechargeid) {
		this.rechargeid = rechargeid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPlanid() {
		return planid;
	}

	public void setPlanid(String planid) {
		this.planid = planid;
	}

	public String getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(String creationdate) {
		this.creationdate = creationdate;
	}

	public String getQuotaexpirydate() {
		return quotaexpirydate;
	}

	public void setQuotaexpirydate(String quotaexpirydate) {
		this.quotaexpirydate = quotaexpirydate;
	}

	public String getPlantimebank() {
		return plantimebank;
	}

	public void setPlantimebank(String plantimebank) {
		this.plantimebank = plantimebank;
	}

	public String getPlancost() {
		return plancost;
	}

	public void setPlancost(String plancost) {
		this.plancost = plancost;
	}

	public String getPlansetupcost() {
		return plansetupcost;
	}

	public void setPlansetupcost(String plansetupcost) {
		this.plansetupcost = plansetupcost;
	}

	public String getPlantax() {
		return plantax;
	}

	public void setPlantax(String plantax) {
		this.plantax = plantax;
	}

	public String getPlanmrp() {
		return planmrp;
	}

	public void setPlanmrp(String planmrp) {
		this.planmrp = planmrp;
	}

	public String getPlandiscount() {
		return plandiscount;
	}

	public void setPlandiscount(String plandiscount) {
		this.plandiscount = plandiscount;
	}

	public String getPlancuscost() {
		return plancuscost;
	}

	public void setPlancuscost(String plancuscost) {
		this.plancuscost = plancuscost;
	}

	public String getPlanlcocomm() {
		return planlcocomm;
	}

	public String getPlanmsocost() {
		return planmsocost;
	}

	public void setPlanmsocost(String planmsocost) {
		this.planmsocost = planmsocost;
	}

	public void setPlanlcocomm(String planlcocomm) {
		this.planlcocomm = planlcocomm;
	}

	public String getCreationby() {
		return creationby;
	}

	public void setCreationby(String creationby) {
		this.creationby = creationby;
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

}
