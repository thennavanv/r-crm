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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ridsys.rib.DTO.SpinListDTO;
import com.ridsys.rib.DTO.OnlinepaymentDTO;
import com.ridsys.rib.DTO.OperatorDTO;
import com.ridsys.rib.DTO.OperatorUserCountDTO;
import com.ridsys.rib.DTO.OperatorWalletReportDTO;
import com.ridsys.rib.DTO.Operator_permissionDTO;

@Entity
@Table(name = "operators")
@NamedNativeQuery(name = "Operators.getForSpinList", query = "SELECT id,username,CONCAT(firstname,' ',lastname) AS name FROM operators WHERE isactive=1", resultSetMapping = "Mapping.SpinListDTO")
@SqlResultSetMapping(name = "Mapping.SpinListDTO", classes = {
		@ConstructorResult(targetClass = SpinListDTO.class, columns = { @ColumnResult(name = "id"),
				@ColumnResult(name = "username"), @ColumnResult(name = "name") }) })

@NamedNativeQuery(name = "Operators.getAllOperatorwithCounts", query = "SELECT ov.area AS arealan,opr.id,opr.operatorid,opr.firstname,opr.lastname,opr.area,opr.address,opr.phone1,opr.email1,opr.state,if(opr.vlanid is null,0,opr.vlanid)as vlanid,opr.username,opr.password,if(ex.deactive is null,0,ex.deactive)as deactive,if(ac.active is null,0,ac.active)As active,if(nw.new is null,0,nw.new)as new FROM operators opr LEFT JOIN (SELECT count(ubi.id)AS deactive,ui.opid FROM userinfo ui, userbillinfo ubi WHERE ui.is_delete=0 AND ui.username=ubi.username AND ubi.quotaexpirydate<now()  GROUP BY opid)AS ex ON ex.opid=opr.id LEFT JOIN (SELECT count(ubi.id)AS active,ui.opid FROM userinfo ui, userbillinfo ubi WHERE ui.is_delete=0 AND ui.username=ubi.username and ubi.quotaexpirydate>now()  GROUP BY opid)AS ac ON ac.opid=opr.id LEFT JOIN (SELECT count(ubi.id)AS new,ui.opid FROM userinfo ui, userbillinfo ubi WHERE ui.is_delete=0 AND ui.username=ubi.username and ubi.quotaexpirydate is null  GROUP BY opid)AS nw ON nw.opid=opr.id LEFT JOIN (SELECT * FROM operator_vlan GROUP BY opid) ov ON ov.opid=opr.id AND ov.vlanid=opr.vlanid WHERE opr.isactive=1 AND opr.isdelete=0", resultSetMapping = "Mapping.operatorDTO")
@NamedNativeQuery(name = "Operators.getAllOperatorwithCountsByOpid", query = "SELECT ov.area AS arealan,opr.id,opr.operatorid,opr.firstname,opr.lastname,opr.area,opr.address,opr.phone1,opr.email1,opr.state,if(opr.vlanid is null,0,opr.vlanid)as vlanid,opr.username,opr.password,if(ex.deactive is null,0,ex.deactive)as deactive,if(ac.active is null,0,ac.active)As active,if(nw.new is null,0,nw.new)as new FROM operators opr LEFT JOIN (SELECT count(ubi.id)AS deactive,ui.opid FROM userinfo ui, userbillinfo ubi WHERE ui.is_delete=0 AND ui.username=ubi.username AND ubi.quotaexpirydate<now()  GROUP BY opid)AS ex ON ex.opid=opr.id LEFT JOIN (SELECT count(ubi.id)AS active,ui.opid FROM userinfo ui, userbillinfo ubi WHERE ui.is_delete=0 AND ui.username=ubi.username and ubi.quotaexpirydate>now()  GROUP BY opid)AS ac ON ac.opid=opr.id LEFT JOIN (SELECT count(ubi.id)AS new,ui.opid FROM userinfo ui, userbillinfo ubi WHERE ui.is_delete=0 AND ui.username=ubi.username and ubi.quotaexpirydate is null GROUP BY opid)AS nw ON nw.opid=opr.id  LEFT JOIN (SELECT * FROM operator_vlan group by opid) ov ON ov.opid=opr.id  AND ov.vlanid=opr.vlanid WHERE opr.username=:username", resultSetMapping = "Mapping.operatorDTO")
@SqlResultSetMapping(name = "Mapping.operatorDTO", classes = {
		@ConstructorResult(targetClass = OperatorDTO.class, columns = {
				@ColumnResult(name = "id", type = Integer.class), @ColumnResult(name = "firstname"),
				@ColumnResult(name = "lastname"), @ColumnResult(name = "area"), @ColumnResult(name = "address"),
				@ColumnResult(name = "phone1"), @ColumnResult(name = "email1"), @ColumnResult(name = "state"),
				@ColumnResult(name = "vlanid", type = Integer.class), @ColumnResult(name = "username"),
				@ColumnResult(name = "password"), @ColumnResult(name = "deactive", type = Integer.class),
				@ColumnResult(name = "active", type = Integer.class), @ColumnResult(name = "new", type = Integer.class),
				@ColumnResult(name = "arealan"), @ColumnResult(name = "operatorid", type = Integer.class) }) })

@NamedNativeQuery(name = "Operators.getOperatorPermission", query = "SELECT opr.operatorid,opr.firstname,opr.lastname,opr.area,opr.address,opr.phone1,opr.email1,opr.state,if(opr.vlanid is null,0,opr.vlanid)AS vlanid,opr.username,opr.password,if(op.ott=1,'Active','Deactive') as ott,if(op.iptv=1,'Active','Deactive') as vod,if(op.walletRecharge is null,false,true) as walletRecharge,if(op.sms=1,'Active','Deactive') as sms,if(op.email=1,'Active','Deactive') as email FROM operators opr LEFT JOIN operator_permission op ON op.opid=opr.id WHERE opr.isactive=1 AND opr.isdelete=0 ORDER BY opr.id", resultSetMapping = "Mapping.OpeatorPermissionDTO")
@SqlResultSetMapping(name = "Mapping.OpeatorPermissionDTO", classes = {
		@ConstructorResult(targetClass = Operator_permissionDTO.class, columns = {
				@ColumnResult(name = "operatorid", type = Integer.class), @ColumnResult(name = "firstname"),
				@ColumnResult(name = "lastname"), @ColumnResult(name = "area"), @ColumnResult(name = "address"),
				@ColumnResult(name = "phone1"), @ColumnResult(name = "email1"), @ColumnResult(name = "state"),
				@ColumnResult(name = "vlanid", type = Integer.class), @ColumnResult(name = "username"),
				@ColumnResult(name = "password"), @ColumnResult(name = "walletRecharge", type = Boolean.class),
				@ColumnResult(name = "ott", type = String.class), @ColumnResult(name = "vod", type = String.class),
				@ColumnResult(name = "sms", type = String.class),
				@ColumnResult(name = "email", type = String.class) }) })

@NamedNativeQuery(name = "Operators.getOperatorUserCounts", query = "SELECT ov.area,op.firstname,op.lastname,op.username,op.password,op.id AS lcoid,op.vlanid ,(SELECT count(id) FROM userinfo WHERE is_delete=0 and opid=op.id) AS total,(SELECT count(ubi.id) FROM userinfo ui, userbillinfo ubi WHERE ui.is_delete=0 and ui.username=ubi.username AND ui.opid=op.id AND ubi.quotaexpirydate>now()) AS active,(SELECT count(ubi.id) FROM userinfo ui, userbillinfo ubi WHERE ui.is_delete=0 and ui.username=ubi.username AND ui.opid=op.id AND ubi.quotaexpirydate<now()) AS deactive,(SELECT COUNT(ra.radacctid) online FROM userinfo ui,userbillinfo ubi,radacct ra,(SELECT username,MAX(acctstarttime) acctstarttime FROM radacct GROUP BY username) rat WHERE ui.is_delete=0 AND ui.username=ubi.username AND ubi.quotaexpirydate>now() AND ra.username=rat.username AND ra.acctstarttime=rat.acctstarttime AND ra.username=ui.username AND ui.opid=op.id AND ra.acctstoptime IS null) AS online,(SELECT COUNT(ra.radacctid) offline FROM userinfo ui,userbillinfo ubi,radacct ra,(SELECT username,MAX(acctstarttime) acctstarttime FROM radacct GROUP BY username) rat WHERE ui.is_delete=0 AND ui.username=ubi.username AND ubi.quotaexpirydate>now() AND ra.username=rat.username AND ra.acctstarttime=rat.acctstarttime AND ra.username=ui.username AND ui.opid=op.id AND ra.acctstoptime IS NOT null) AS offline,(SELECT COUNT(id) FROM userinfo WHERE verificationstatus=0 AND is_delete=0 AND  opid=op.id) AS new,(SELECT COUNT(id) FROM userinfo WHERE is_delete=1 AND  opid=op.id) AS deleted,IF(os.switchname is null and os.switchip is null,' ',CONCAT(os.switchname,'(',os.switchip,')')) AS switchname,IF(os.portno is null,' ',os.portno) AS portno,IF(os.opid=op.id,true,false) AS rnetopstatus,IF(os.opid=op.id,'Internet','No Internet') AS netStatus from operators op LEFT JOIN operator_vlan ov ON op.id=ov.opid AND ov.vlanid=op.vlanid  LEFT JOIN (SELECT s.switchname,os.portno,os.opid,s.switchip FROM operator_switchport os,switch s WHERE s.id=os.switchid GROUP BY opid) os ON os.opid=op.id  where op.isactive=1", resultSetMapping = "Mapping.OperatorUserCountDTO")
@SqlResultSetMapping(name = "Mapping.OperatorUserCountDTO", classes = {
		@ConstructorResult(targetClass = OperatorUserCountDTO.class, columns = {
				@ColumnResult(name = "firstname", type = String.class),
				@ColumnResult(name = "lastname", type = String.class),
				@ColumnResult(name = "username", type = String.class),
				@ColumnResult(name = "password", type = String.class),
				@ColumnResult(name = "lcoid", type = Integer.class),
				@ColumnResult(name = "vlanid", type = Integer.class),
				@ColumnResult(name = "total", type = Integer.class),
				@ColumnResult(name = "active", type = Integer.class),
				@ColumnResult(name = "deactive", type = Integer.class),
				@ColumnResult(name = "online", type = Integer.class),
				@ColumnResult(name = "offline", type = Integer.class),
				@ColumnResult(name = "new", type = Integer.class),
				@ColumnResult(name = "deleted", type = Integer.class),
				@ColumnResult(name = "area", type = String.class),
				@ColumnResult(name = "switchname", type = String.class),
				@ColumnResult(name = "portno", type = String.class),
				@ColumnResult(name = "rnetopstatus", type = Boolean.class), @ColumnResult(name = "netStatus") }) })

@NamedNativeQuery(name = "Operators.getAllRechargeHistory", query = "SELECT transactionid,amount,role,concat(op.firstname,' ',op.lastname)AS username,(SELECT value FROM payment_type WHERE id=paymenttypeid)AS paymenttype,opl.creationdate,'Offline' AS status,if(description is null,' ',description)AS description,creationbyrole AS creationby FROM offline_payment_log opl LEFT JOIN operators op on op.username=opl.username union SELECT orderid,amount,role,concat(op.firstname,' ',op.lastname)AS username,(SELECT value FROM payment_type WHERE id=paymenttypeid)AS paymenttype,tstarttime AS creationdate,'Online' AS status,if(resmsg is null,' ',resmsg) AS description,role AS creationby FROM online_payment_log opl LEFT JOIN operators op on  op.username=opl.username WHERE op.username=opl.username AND op.isdelete=0 AND op.isactive=1 ORDER BY creationdate DESC", resultSetMapping = "Mapping.getAllhistoryRS")
@NamedNativeQuery(name = "Operators.getAllRechargeHistoryFtdate", query = "SELECT transactionid,amount,role,concat(op.firstname,' ',op.lastname)AS username,(SELECT value FROM payment_type WHERE id=paymenttypeid)AS paymenttype,opl.creationdate,'Offline' AS status,if(description is null,' ',description)AS description,creationbyrole AS creationby FROM offline_payment_log opl LEFT JOIN operators op on op.username=opl.username WHERE DATE(opl.creationdate)>=:fdate and DATE(opl.creationdate)<=:tdate union SELECT orderid,amount,role,concat(op.firstname,' ',op.lastname)AS username,(SELECT value FROM payment_type WHERE id=paymenttypeid)AS paymenttype,tstarttime AS creationdate,'Online' AS status,if(resmsg is null,' ',resmsg) AS description,role AS creationby FROM online_payment_log opl LEFT JOIN operators op on  op.username=opl.username WHERE op.username=opl.username and DATE(tstarttime)>=:fdate and DATE(tstarttime)<=:tdate  AND op.isdelete=0 AND op.isactive=1   ORDER BY creationdate DESC", resultSetMapping = "Mapping.getAllhistoryRS")
@NamedNativeQuery(name = "Operators.getAllRechargeHistoryByOpid", query = "SELECT transactionid,amount,role,concat(op.firstname,' ',op.lastname)AS username,(SELECT value FROM payment_type WHERE id=paymenttypeid)AS paymenttype,opl.creationdate,'Offline' AS status,if(description is null,' ',description)AS description,creationbyrole AS creationby FROM offline_payment_log opl LEFT JOIN operators op on op.username=opl.username WHERE opl.username=:username union SELECT orderid,amount,role,concat(op.firstname,' ',op.lastname)AS username,(SELECT value FROM payment_type WHERE id=paymenttypeid)AS paymenttype,tstarttime AS creationdate,'Online' AS status,if(resmsg is null,' ',resmsg) AS description,role AS creationby FROM online_payment_log opl LEFT JOIN operators op on  op.username=opl.username WHERE op.username=opl.username and opl.username=:username  AND op.isdelete=0 AND op.isactive=1  ORDER BY creationdate DESC", resultSetMapping = "Mapping.getAllhistoryRS")
@NamedNativeQuery(name = "Operators.getAllRechargeHistoryByOpidFtdate", query = "SELECT transactionid,amount,role,concat(op.firstname,' ',op.lastname)AS username,(SELECT value FROM payment_type WHERE id=paymenttypeid)AS paymenttype,opl.creationdate,'Offline' AS status,if(description is null,' ',description)AS description,creationbyrole AS creationby FROM offline_payment_log opl LEFT JOIN operators op on op.username=opl.username WHERE opl.username=:username and  DATE(opl.creationdate)>=:fdate and DATE(opl.creationdate)<=:tdate  union SELECT orderid,amount,role,concat(op.firstname,' ',op.lastname)AS username,(SELECT value FROM payment_type WHERE id=paymenttypeid)AS paymenttype,tstarttime AS creationdate,'Online' AS status,if(resmsg is null,' ',resmsg) AS description,role AS creationby FROM online_payment_log opl LEFT JOIN operators op on  op.username=opl.username WHERE op.username=opl.username and opl.username=:username and DATE(tstarttime)>=:fdate and DATE(tstarttime)<=:tdate AND op.isdelete=0 AND op.isactive=1  ORDER BY creationdate DESC", resultSetMapping = "Mapping.getAllhistoryRS")
@SqlResultSetMapping(name = "Mapping.getAllhistoryRS", classes = {
		@ConstructorResult(targetClass = OnlinepaymentDTO.class, columns = {
				@ColumnResult(name = "username", type = String.class),
				@ColumnResult(name = "role", type = String.class), @ColumnResult(name = "amount", type = String.class),
				@ColumnResult(name = "transactionid", type = String.class),
				@ColumnResult(name = "paymenttype", type = String.class),
				@ColumnResult(name = "status", type = String.class),
				@ColumnResult(name = "creationdate", type = String.class),
				@ColumnResult(name = "description", type = String.class), @ColumnResult(name = "creationby") }) })

@NamedNativeQuery(name = "Operators.getAllOperator", query = "SELECT op.id,op.firstname,op.lastname,op.operatorid,op.vlanid,op.username,op.password,op.walletbalance,op.title,op.department,op.company,op.phone1,op.email1,op.messenger1,op.notes,ov.area AS arealan,IF(os.switchname  is null and os.switchip is null,' ',CONCAT(os.switchname,'(',os.switchip,')')) AS switchname,IF(os.portno is null,' ',os.portno) AS portno,IF(os.opid=op.id,true,false) AS rnetopstatus,IF(os.opid=op.id,'Internet','No Internet') AS netStatus FROM operators op LEFT JOIN (select *from operator_vlan group by opid) ov ON ov.opid=op.id AND ov.vlanid=op.vlanid LEFT JOIN (SELECT s.switchname,os.portno,os.opid,s.switchip FROM operator_switchport os,switch s WHERE s.id=os.switchid GROUP BY opid) os ON os.opid=op.id WHERE op.isactive=1 AND  op.isdelete=0", resultSetMapping = "Mapping.getAllOperatorRS")
@SqlResultSetMapping(name = "Mapping.getAllOperatorRS", classes = {
		@ConstructorResult(targetClass = Operators.class, columns = { @ColumnResult(name = "id", type = Integer.class),
				@ColumnResult(name = "username", type = String.class),
				@ColumnResult(name = "password", type = String.class),
				@ColumnResult(name = "firstname", type = String.class),
				@ColumnResult(name = "lastname", type = String.class),
				@ColumnResult(name = "title", type = String.class),
				@ColumnResult(name = "department", type = String.class),
				@ColumnResult(name = "company", type = String.class),
				@ColumnResult(name = "phone1", type = String.class),
				@ColumnResult(name = "email1", type = String.class),
				@ColumnResult(name = "messenger1", type = String.class),
				@ColumnResult(name = "notes", type = String.class),
				@ColumnResult(name = "walletbalance", type = String.class),
				@ColumnResult(name = "operatorid", type = Integer.class),
				@ColumnResult(name = "vlanid", type = Integer.class),
				@ColumnResult(name = "arealan", type = String.class),
				@ColumnResult(name = "switchname", type = String.class),
				@ColumnResult(name = "portno", type = String.class),
				@ColumnResult(name = "rnetopstatus", type = Boolean.class),
				@ColumnResult(name = "netStatus", type = String.class) }) })

@NamedNativeQuery(name = "Operators.getAllOpWalletHistory", query = "SELECT (select concat(firstname,' ',lastname) from userinfo where username=if(qpl.username is not null,qpl.username,qpc.username) LIMIT 1) as customername,if(qpl.username is not null,qpl.username,qpc.username) as customerusername,if(qpl.rechargeid is not null,qpl.rechargeid,qpc.rechargeid)as rechargeid,w.id,CONCAT(op.firstname,' ',op.lastname) AS fullname,w.username,w.creationdate,wt.type AS walletupdatetype,w.oldbalance,w.amount,w.newbalance,w.paymentbyrole,if(r.type='plan',if(qpl.rechargeid is null,'Plan Deativation','Plan Activation'),r.type) AS rechargetype,if(pt.value is null,'Offline',pt.value)AS paymenttype,if(w.paymentbyrole='USER',concat(ui.firstname,' ',ui.lastname),concat(op.firstname,' ',op.lastname)) AS paymentbyusername,qsp.subplan,if(qsp.planTimeBank is not null,(qsp.planTimeBank/30),0)AS month FROM walletupdatelog w LEFT JOIN rechargetype r ON r.id=w.rechargetype LEFT JOIN walletupdatetype wt ON wt.id=w.walletupdatetypeid LEFT JOIN quotaplanmanagelog qpl ON qpl.rechargeid=w.referenceid LEFT JOIN quotasubplan qsp ON qsp.id=qpl.planid   LEFT JOIN quotaplancancelhistory qpc ON qpc.rechargeid=w.referenceid  LEFT JOIN userinfo ui ON ui.username=w.paymentbyusername LEFT JOIN operators op ON op.username=w.paymentbyusername OR op.username=w.username LEFT JOIN online_payment_log opl ON opl.transactionid=w.referenceid LEFT JOIN offline_payment_log ofpl ON ofpl.transactionid=w.referenceid LEFT JOIN payment_type pt ON pt.id=opl.paymenttypeid AND pt.id=ofpl.paymenttypeid  WHERE  w.role='OPERATOR' ORDER BY w.id", resultSetMapping = "Mapping.getAllOpWalletHistoryRS")
@NamedNativeQuery(name = "Operators.getAllOpWalletHistoryFtdate", query = "SELECT (select concat(firstname,' ',lastname) from userinfo where username=if(qpl.username is not null,qpl.username,qpc.username) LIMIT 1) as customername,if(qpl.username is not null,qpl.username,qpc.username) as customerusername,if(qpl.rechargeid is not null,qpl.rechargeid,qpc.rechargeid)as rechargeid,w.id,CONCAT(op.firstname,' ',op.lastname) AS fullname,w.username,w.creationdate,wt.type AS walletupdatetype,w.oldbalance,w.amount,w.newbalance,w.paymentbyrole,if(r.type='plan',if(qpl.rechargeid is null,'Plan Deativation','Plan Activation'),r.type) AS rechargetype,if(pt.value is null,'Offline',pt.value)AS paymenttype,if(w.paymentbyrole='USER',concat(ui.firstname,' ',ui.lastname),concat(op.firstname,' ',op.lastname)) AS paymentbyusername,qsp.subplan,if(qsp.planTimeBank is not null,(qsp.planTimeBank/30),0)AS month FROM walletupdatelog w LEFT JOIN rechargetype r ON r.id=w.rechargetype LEFT JOIN walletupdatetype wt ON wt.id=w.walletupdatetypeid LEFT JOIN quotaplanmanagelog qpl ON qpl.rechargeid=w.referenceid LEFT JOIN quotasubplan qsp ON qsp.id=qpl.planid   LEFT JOIN quotaplancancelhistory qpc ON qpc.rechargeid=w.referenceid  LEFT JOIN userinfo ui ON ui.username=w.paymentbyusername LEFT JOIN operators op ON op.username=w.paymentbyusername  OR op.username=w.username LEFT JOIN online_payment_log opl ON opl.transactionid=w.referenceid LEFT JOIN offline_payment_log ofpl ON ofpl.transactionid=w.referenceid LEFT JOIN payment_type pt ON pt.id=opl.paymenttypeid AND pt.id=ofpl.paymenttypeid  WHERE  w.role='OPERATOR'  AND date(w.creationdate)>=:fdate AND date(w.creationdate)<=:tdate ORDER BY w.id", resultSetMapping = "Mapping.getAllOpWalletHistoryRS")
@NamedNativeQuery(name = "Operators.getOpWalletHistoryByUsername", query = "SELECT (select concat(firstname,' ',lastname) from userinfo where username=if(qpl.username  is not null,qpl.username,qpc.username) LIMIT 1) as customername,if(qpl.username is not null,qpl.username,qpc.username) as customerusername,if(qpl.rechargeid is not null,qpl.rechargeid,qpc.rechargeid)as rechargeid,w.id,CONCAT(op.firstname,' ',op.lastname) AS fullname,w.username,w.creationdate,wt.type AS walletupdatetype,w.oldbalance,w.amount,w.newbalance,w.paymentbyrole,if(r.type='plan',if(qpl.rechargeid is null,'Plan Deativation','Plan Activation'),r.type) AS rechargetype,if(pt.value is null,'Offline',pt.value)AS paymenttype,if(w.paymentbyrole='USER',concat(ui.firstname,' ',ui.lastname),concat(op.firstname,' ',op.lastname)) AS paymentbyusername,qsp.subplan,if(qsp.planTimeBank is not null,(qsp.planTimeBank/30),0)AS month FROM walletupdatelog w LEFT JOIN rechargetype r ON r.id=w.rechargetype LEFT JOIN walletupdatetype wt ON wt.id=w.walletupdatetypeid LEFT JOIN quotaplanmanagelog qpl ON qpl.rechargeid=w.referenceid LEFT JOIN quotasubplan qsp ON qsp.id=qpl.planid   LEFT JOIN quotaplancancelhistory qpc ON qpc.rechargeid=w.referenceid  LEFT JOIN userinfo ui ON ui.username=w.paymentbyusername LEFT JOIN operators op ON op.username=w.paymentbyusername  OR op.username=w.username LEFT JOIN online_payment_log opl ON opl.transactionid=w.referenceid LEFT JOIN offline_payment_log ofpl ON ofpl.transactionid=w.referenceid LEFT JOIN payment_type pt ON pt.id=opl.paymenttypeid AND pt.id=ofpl.paymenttypeid  WHERE  w.role='OPERATOR'  AND w.username=:username ORDER BY w.id", resultSetMapping = "Mapping.getAllOpWalletHistoryRS")
@NamedNativeQuery(name = "Operators.getOpWalletHistoryByUsernameFtdate", query = "SELECT (select concat(firstname,' ',lastname) from userinfo where username=if(qpl.username is not null,qpl.username,qpc.username) LIMIT 1) as customername,if(qpl.username is not null,qpl.username,qpc.username) as customerusername,if(qpl.rechargeid is not null,qpl.rechargeid,qpc.rechargeid)as rechargeid,w.id,CONCAT(op.firstname,' ',op.lastname) AS fullname,w.username,w.creationdate,wt.type AS walletupdatetype,w.oldbalance,w.amount,w.newbalance,w.paymentbyrole,if(r.type='plan',if(qpl.rechargeid is null,'Plan Deativation','Plan Activation'),r.type) AS rechargetype,if(pt.value is null,'Offline',pt.value)AS paymenttype,if(w.paymentbyrole='USER',concat(ui.firstname,' ',ui.lastname),concat(op.firstname,' ',op.lastname)) AS paymentbyusername,qsp.subplan,if(qsp.planTimeBank is not null,(qsp.planTimeBank/30),0)AS month FROM walletupdatelog w LEFT JOIN rechargetype r ON r.id=w.rechargetype LEFT JOIN walletupdatetype wt ON wt.id=w.walletupdatetypeid LEFT JOIN quotaplanmanagelog qpl ON qpl.rechargeid=w.referenceid LEFT JOIN quotasubplan qsp ON qsp.id=qpl.planid   LEFT JOIN quotaplancancelhistory qpc ON qpc.rechargeid=w.referenceid  LEFT JOIN userinfo ui ON ui.username=w.paymentbyusername LEFT JOIN operators op ON op.username=w.paymentbyusername  OR op.username=w.username LEFT JOIN online_payment_log opl ON opl.transactionid=w.referenceid LEFT JOIN offline_payment_log ofpl ON ofpl.transactionid=w.referenceid LEFT JOIN payment_type pt ON pt.id=opl.paymenttypeid AND pt.id=ofpl.paymenttypeid  WHERE  w.role='OPERATOR'  AND w.username=:username  AND date(w.creationdate)>=:fdate AND date(w.creationdate)<=:tdate  ORDER BY w.id"
		+ "", resultSetMapping = "Mapping.getAllOpWalletHistoryRS")
@SqlResultSetMapping(name = "Mapping.getAllOpWalletHistoryRS", classes = {
		@ConstructorResult(targetClass = OperatorWalletReportDTO.class, columns = {
				@ColumnResult(name = "id", type = Integer.class), @ColumnResult(name = "fullname", type = String.class),
				@ColumnResult(name = "username", type = String.class),
				@ColumnResult(name = "creationdate", type = String.class),
				@ColumnResult(name = "walletupdatetype", type = String.class),
				@ColumnResult(name = "oldbalance", type = String.class),
				@ColumnResult(name = "amount", type = String.class),
				@ColumnResult(name = "newbalance", type = String.class),
				@ColumnResult(name = "paymentbyrole", type = String.class),
				@ColumnResult(name = "rechargetype", type = String.class),
				@ColumnResult(name = "paymenttype", type = String.class),
				@ColumnResult(name = "paymentbyusername", type = String.class),
				@ColumnResult(name = "subplan", type = String.class),
				@ColumnResult(name = "month", type = String.class),
				@ColumnResult(name = "customername", type = String.class),
				@ColumnResult(name = "customerusername", type = String.class) }) })

public class Operators {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

//	@NotNull(message = "username has to be present")
//	@Size(min = 6, max = 10, message = "The username '${validatedValue}' must be between {min} and {max} characters long")
	private String username;

//	@NotNull(message = "password has to be present")
//	@Size(min = 6, max = 10, message = "The password '${validatedValue}' must be between {min} and {max} characters long")
	private String password;

	private String firstname;
	private String lastname;
	private String title;
	private String department;
	private String company;
	private String phone1;
	private String phone2;
	private String email1;
	private String email2;
	private String messenger1;
	private String messenger2;
	private String notes;
	private String walletbalance;
	private int paymentgatewayid;
	private int operatorid;
	private int vlanid;
	private String address;
	private String area;
	private String state;
	private String zipcode;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String lastlogin;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String creationdate;

	private String creationby;
	private String updatedate;
	private String updateby;
	private boolean isactive;

	@Transient
	private String arealan;

	@Transient
	private String switchname;

	@Transient
	private String portno;

	@Transient
	private boolean iswithoutRnet;

	@Transient
	private String netStatus;

	public Operators(String username, String password, String firstname, String lastname, String title,
			String department, String company, String phone1, String phone2, String email1, String email2,
			String messenger1, String messenger2, String notes, String walletbalance, int paymentgatewayid,
			String lastlogin, String creationdate, String creationby, String updatedate, String updateby,
			boolean isactive, int operatorid, int vlanid, String address, String area, String state, String zipcode) {
		super();
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.title = title;
		this.department = department;
		this.company = company;
		this.phone1 = phone1;
		this.phone2 = phone2;
		this.email1 = email1;
		this.email2 = email2;
		this.messenger1 = messenger1;
		this.messenger2 = messenger2;
		this.notes = notes;
		this.walletbalance = walletbalance;
		this.paymentgatewayid = paymentgatewayid;
		this.lastlogin = lastlogin;
		this.creationdate = creationdate;
		this.creationby = creationby;
		this.updatedate = updatedate;
		this.updateby = updateby;
		this.isactive = isactive;
		this.operatorid = operatorid;
		this.vlanid = vlanid;
		this.address = address;
		this.area = area;
		this.state = state;
		this.zipcode = zipcode;
	}

	public Operators(int id, String username, String password, String firstname, String lastname, String title,
			String department, String company, String phone1, String email1, String messenger1, String notes,
			String walletbalance, int operatorid, int vlanid, String arealan, String switchname, String portno,
			boolean iswithoutRnet, String netStatus) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.title = title;
		this.department = department;
		this.company = company;
		this.phone1 = phone1;
		this.email1 = email1;
		this.messenger1 = messenger1;
		this.notes = notes;
		this.walletbalance = walletbalance;
		this.operatorid = operatorid;
		this.vlanid = vlanid;
		this.arealan = arealan;
		this.switchname = switchname;
		this.portno = portno;
		this.iswithoutRnet = iswithoutRnet;
		this.netStatus = netStatus;
	}

	public String getNetStatus() {
		return netStatus;
	}

	public void setNetStatus(String netStatus) {
		this.netStatus = netStatus;
	}

	public String getSwitchname() {
		return switchname;
	}

	public void setSwitchname(String switchname) {
		this.switchname = switchname;
	}

	public String getPortno() {
		return portno;
	}

	public void setPortno(String portno) {
		this.portno = portno;
	}

	public boolean isIswithoutRnet() {
		return iswithoutRnet;
	}

	public void setIswithoutRnet(boolean iswithoutRnet) {
		this.iswithoutRnet = iswithoutRnet;
	}

	public Operators() {
	}

	public String getArealan() {
		return arealan;
	}

	public void setArealan(String arealan) {
		this.arealan = arealan;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDepartment() {
		return department == null ? "" : department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getCompany() {
		return company == null ? "" : company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPhone1() {
		return phone1 == null ? "" : phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getEmail1() {
		return email1 == null ? "" : email1;
	}

	public void setEmail1(String email1) {
		this.email1 = email1;
	}

	public String getEmail2() {
		return email2;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}

	public String getMessenger1() {
		return messenger1;
	}

	public void setMessenger1(String messenger1) {
		this.messenger1 = messenger1;
	}

	public String getMessenger2() {
		return messenger2;
	}

	public void setMessenger2(String messenger2) {
		this.messenger2 = messenger2;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getLastlogin() {
		return lastlogin;
	}

	public void setLastlogin(String lastlogin) {
		this.lastlogin = lastlogin;
	}

	public String getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(String creationdate) {
		this.creationdate = creationdate;
	}

	public String getCreationby() {
		return creationby;
	}

	public void setCreationby(String creationby) {
		this.creationby = creationby;
	}

	public String getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}

	public String getUpdateby() {
		return updateby;
	}

	public void setUpdateby(String updateby) {
		this.updateby = updateby;
	}

	public String getWalletbalance() {
		return walletbalance;
	}

	public void setWalletbalance(String walletbalance) {
		this.walletbalance = walletbalance;
	}

	public int getPaymentgatewayid() {
		return paymentgatewayid;
	}

	public void setPaymentgatewayid(int paymentgatewayid) {
		this.paymentgatewayid = paymentgatewayid;
	}

	public boolean isIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	public int getOperatorid() {
		return operatorid;
	}

	public void setOperatorid(int operatorid) {
		this.operatorid = operatorid;
	}

	public int getVlanid() {
		return vlanid;
	}

	public void setVlanid(int vlanid) {
		this.vlanid = vlanid;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

}
