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

import com.ridsys.rib.DTO.PlanDetailDTO;
import com.ridsys.rib.DTO.UserDetailsDTO;
import com.ridsys.rib.DTO.CustomerStatDTO;
import com.ridsys.rib.comm.General;

@Entity
@Table(name = "userbillinfo")
//@NamedNativeQuery(name = "Userbillinfo.getExpiryStatusAdmin", query = "SELECT(SELECT COUNT(id) FROM userbillinfo WHERE quotaexpirydate>=:t1 AND quotaexpirydate<=:t2)AS today,(SELECT COUNT(id) FROM userbillinfo WHERE quotaexpirydate>=:y1 AND quotaexpirydate<=:y2)AS yesterday,(SELECT COUNT(id) FROM userbillinfo WHERE quotaexpirydate>=:trw1 AND quotaexpirydate<=:trw2)AS tomorrow,(SELECT COUNT(id) FROM userbillinfo WHERE quotaexpirydate>=:week1 AND quotaexpirydate<=:week2)AS lastweek,(SELECT COUNT(id) FROM userbillinfo WHERE quotaexpirydate>=:month1 AND quotaexpirydate<=:month2) AS lastmonth", resultSetMapping = "Mapping.customerStat")
//@NamedNativeQuery(name = "Userbillinfo.getExpiryStatusByOpid", query = "SELECT(SELECT count(ubi.id)from userbillinfo AS ubi,(SELECT  ui.username FROM userinfo ui RIGHT JOIN operators op ON op.id=ui.opid AND op.username=:username)AS usrinfo WHERE usrinfo.username=ubi.username AND  quotaexpirydate>=:t1 AND quotaexpirydate<=:t2)AS today,(SELECT count(ubi.id)FROM userbillinfo AS ubi,(SELECT  ui.username FROM userinfo ui RIGHT JOIN operators op ON op.id=ui.opid AND op.username=:username)AS usrinfo WHERE usrinfo.username=ubi.username AND  quotaexpirydate>=:y1 AND quotaexpirydate<=:y2)AS yesterday,(SELECT count(ubi.id)from userbillinfo AS ubi,(SELECT  ui.username FROM userinfo ui RIGHT JOIN operators op ON op.id=ui.opid AND op.username=:username)AS usrinfo WHERE usrinfo.username=ubi.username AND  quotaexpirydate>=:trw1 AND quotaexpirydate<=:trw2)AS tomorrow,(SELECT count(ubi.id)FROM userbillinfo AS ubi,(SELECT  ui.username FROM userinfo ui RIGHT JOIN operators op ON op.id=ui.opid AND op.username=:username)AS usrinfo WHERE usrinfo.username=ubi.username AND  quotaexpirydate>=:week1 AND quotaexpirydate<=:week2)AS lastweek,(SELECT count(ubi.id)FROM userbillinfo AS ubi,(SELECT  ui.username FROM userinfo ui RIGHT JOIN operators op ON op.id=ui.opid AND op.username=:username)AS usrinfo WHERE usrinfo.username=ubi.username AND  quotaexpirydate>=:month1 AND quotaexpirydate<=:month2) AS lastmonth", resultSetMapping = "Mapping.customerStat")
@NamedNativeQuery(name = "Userbillinfo.getExpiryStatusAdmin", query = "SELECT(SELECT COUNT(id) FROM userbillinfo WHERE DATE(quotaexpirydate)=CURDATE())AS today,(SELECT COUNT(id) FROM userbillinfo WHERE DATE(quotaexpirydate)=(CURDATE()-INTERVAL 1 DAY))AS yesterday,(SELECT COUNT(id) FROM userbillinfo WHERE DATE(quotaexpirydate)=(CURDATE()+INTERVAL 1 DAY))AS tomorrow,(SELECT COUNT(id) FROM userbillinfo WHERE DATE(quotaexpirydate)>=(curdate()-INTERVAL 7 DAY) AND DATE(quotaexpirydate)<=(CURDATE()-INTERVAL 1 DAY))AS lastweek,(SELECT COUNT(id) FROM userbillinfo WHERE DATE(quotaexpirydate)>=CURDATE() AND DATE(quotaexpirydate)<=(CURDATE()+INTERVAL 7 DAY)) AS nextweek", resultSetMapping = "Mapping.customerStat")
@NamedNativeQuery(name = "Userbillinfo.getExpiryStatusByOpid", query = "SELECT(SELECT count(ubi.id)from userbillinfo AS ubi,(SELECT  ui.username FROM userinfo ui RIGHT JOIN operators op ON op.id=ui.opid AND op.username=:username)AS usrinfo WHERE usrinfo.username=ubi.username AND  DATE(quotaexpirydate)=CURDATE())AS today,(SELECT count(ubi.id)FROM userbillinfo AS ubi,(SELECT  ui.username FROM userinfo ui RIGHT JOIN operators op ON op.id=ui.opid AND op.username=:username)AS usrinfo WHERE usrinfo.username=ubi.username AND  DATE(quotaexpirydate)=(CURDATE()-INTERVAL 1 DAY))AS yesterday,(SELECT count(ubi.id)from userbillinfo AS ubi,(SELECT  ui.username FROM userinfo ui RIGHT JOIN operators op ON op.id=ui.opid AND op.username=:username)AS usrinfo WHERE usrinfo.username=ubi.username AND  DATE(quotaexpirydate)=(CURDATE()+INTERVAL 1 DAY))AS tomorrow,(SELECT count(ubi.id)FROM userbillinfo AS ubi,(SELECT  ui.username FROM userinfo ui RIGHT JOIN operators op ON op.id=ui.opid AND op.username=:username)AS usrinfo WHERE usrinfo.username=ubi.username AND  DATE(quotaexpirydate)>=(curdate()-INTERVAL 7 DAY) AND DATE(quotaexpirydate)<=(CURDATE()-INTERVAL 1 DAY))AS lastweek,(SELECT count(ubi.id)FROM userbillinfo AS ubi,(SELECT  ui.username FROM userinfo ui RIGHT JOIN operators op ON op.id=ui.opid AND op.username=:username)AS usrinfo WHERE usrinfo.username=ubi.username AND  DATE(quotaexpirydate)>=CURDATE() AND DATE(quotaexpirydate)<=(CURDATE()+INTERVAL 7 DAY)) AS nextweek", resultSetMapping = "Mapping.customerStat")
@SqlResultSetMapping(name = "Mapping.customerStat", classes = {
		@ConstructorResult(targetClass = CustomerStatDTO.class, columns = {
				@ColumnResult(name = "today", type = long.class), @ColumnResult(name = "yesterday", type = long.class),
				@ColumnResult(name = "tomorrow", type = long.class),
				@ColumnResult(name = "lastweek", type = long.class),
				@ColumnResult(name = "nextweek", type = long.class) }) })

@NamedNativeQuery(name = "Userbillinfo.getDatewiseCustomerCountAdmin", query = "SELECT (SELECT COUNT(id) FROM userinfo WHERE verificationstatus=0 AND is_delete=0 AND (date(creationdate)=curdate()) )AS new,(SELECT count(ubi.id) FROM userinfo ui, userbillinfo ubi WHERE ui.is_delete=0 AND ui.username=ubi.username AND ui.opid>0 AND ubi.quotaexpirydate>=now() AND ui.verificationstatus=2)AS active,(SELECT count(ubi.id) FROM userinfo ui, userbillinfo ubi WHERE ui.is_delete=0 AND ui.username=ubi.username AND ui.opid>0 AND (ubi.quotaexpirydate>=:today AND ubi.quotaexpirydate<=now()) AND ui.verificationstatus=2)AS expiry,(SELECT COUNT(id) FROM userinfo WHERE verificationstatus=1 AND is_delete=0 AND (date(creationdate)=curdate()))AS reject,(SELECT count(ubi.id) FROM userbillinfo ubi LEFT JOIN userinfo ui ON ui.username=ubi.username LEFT JOIN quotasubplan qsp ON qsp.id=ubi.planid LEFT JOIN quotaplan qp ON qp.id=qsp.planid  WHERE ui.verificationstatus=2 AND  ui.is_delete=0 AND qp.packageType='fup' AND DATE(ubi.quotastartdate)=CURDATE())AS fup", resultSetMapping = "Mapping.customerDatewiseCount")
@NamedNativeQuery(name = "Userbillinfo.getDatewiseCustomerCountByOpid", query = "SELECT if(n.new is null,0,n.new)AS new,if(a.active is null,0,a.active)AS active,if(e.expiry is null,0,e.expiry)AS expiry,if(p.reject is null,0,p.reject)AS reject,if(f.fup is null,0,f.fup)AS fup FROM operators AS opr left join (SELECT COUNT(id)AS new,opid FROM userinfo WHERE verificationstatus=0 AND is_delete=0 AND (date(creationdate)=curdate()) GROUP BY opid)AS n ON n.opid=opr.id left join (SELECT count(ubi.id)AS active,ui.opid FROM userinfo ui, userbillinfo ubi WHERE ui.is_delete=0 AND ui.username=ubi.username AND ui.opid>0 AND ubi.quotaexpirydate>=now()  AND ui.verificationstatus=2 GROUP BY ui.opid)AS a ON a.opid=opr.id left join (SELECT count(ubi.id)AS expiry,ui.opid FROM userinfo ui, userbillinfo ubi WHERE ui.is_delete=0 AND ui.username=ubi.username AND ui.opid>0 AND (ubi.quotaexpirydate>=:today AND ubi.quotaexpirydate<=now()) AND ui.verificationstatus=2 GROUP BY ui.opid)AS e ON e.opid=opr.id left join (SELECT COUNT(id)AS reject,opid FROM userinfo WHERE verificationstatus=1 AND is_delete=0 AND (date(creationdate)=curdate())GROUP BY opid)AS p ON p.opid=opr.id left join(SELECT count(ubi.id) as fup,ui.opid FROM userbillinfo ubi LEFT JOIN userinfo ui ON ui.username=ubi.username LEFT JOIN quotasubplan qsp ON qsp.id=ubi.planid LEFT JOIN quotaplan qp ON qp.id=qsp.planid  WHERE ui.verificationstatus=2 AND  ui.is_delete=0 AND qp.packageType='fup' AND DATE(ubi.quotastartdate)=CURDATE()) as f on f.opid=opr.id where opr.username=:username", resultSetMapping = "Mapping.customerDatewiseCount")
@SqlResultSetMapping(name = "Mapping.customerDatewiseCount", classes = {
		@ConstructorResult(targetClass = CustomerStatDTO.class, columns = {
				@ColumnResult(name = "new", type = long.class), @ColumnResult(name = "active", type = long.class),
				@ColumnResult(name = "expiry", type = long.class), @ColumnResult(name = "reject", type = long.class),
				@ColumnResult(name = "fup", type = long.class) }) })

@NamedNativeQuery(name = "Userbillinfo.getPlanwiseCustomercountTodayAdmin", query = "SELECT bp.id,bp.subplan AS planName,if(ac.active is null,0,ac.active)AS active,if(dc.deactive is null,0,dc.deactive)AS deactive FROM quotasubplan bp LEFT JOIN(SELECT COUNT(id) AS active,planName FROM userbillinfo WHERE quotaexpirydate>=now() AND planid!=0 GROUP BY planName)AS ac ON ac.planName=bp.subplan LEFT JOIN(SELECT COUNT(id) AS deactive,planName FROM userbillinfo WHERE quotaexpirydate<now() AND planid!=0 GROUP BY planName)AS dc ON dc.planName=bp.subplan  ORDER BY active DESC LIMIT 6", resultSetMapping = "Mapping.PlanDetail")
@NamedNativeQuery(name = "Userbillinfo.getPlanwiseCustomercountTodayByOpid", query = "SELECT bp.id,bp.subplan AS planName,if(ac.active is null,0,ac.active)AS active,if(dc.deactive is null,0,dc.deactive)AS deactive FROM quotasubplan bp LEFT JOIN(SELECT COUNT(ubi.id)as active,ubi.planName FROM userbillinfo ubi WHERE ubi.quotaexpirydate>=now() AND ubi.planid!=0 AND ubi.username in(SELECT  ui.username FROM userinfo ui INNER JOIN operators op ON op.id=ui.opid AND op.username=:username) GROUP BY planName)AS ac ON ac.planName=bp.subplan LEFT JOIN(SELECT COUNT(ubi.id)AS deactive,ubi.planName FROM userbillinfo ubi WHERE ubi.quotaexpirydate<now() AND ubi.planid!=0 AND ubi.username in(SELECT  ui.username FROM userinfo ui INNER JOIN operators op ON op.id=ui.opid AND op.username=:username) GROUP BY planName)AS dc ON dc.planName=bp.subplan  ORDER BY active DESC LIMIT 6", resultSetMapping = "Mapping.PlanDetail")
@NamedNativeQuery(name = "Userbillinfo.getPlanwiseCustomerAllcountAdmin", query = "SELECT count(ubi.id) AS active,ubi.planName,count(ubi.planid) as deactive FROM userbillinfo ubi LEFT JOIN quotasubplan bp ON bp.id=ubi.planid where ubi.planid!=0 GROUP BY ubi.planName ORDER BY active DESC LIMIT 6", resultSetMapping = "Mapping.PlanDetail")
@NamedNativeQuery(name = "Userbillinfo.getPlanwiseCustomerAllcountByOpid", query = "SELECT count(ubi.id) AS active,ubi.planName,count(ubi.planid) as deactive FROM userbillinfo ubi LEFT JOIN quotasubplan bp ON bp.id=ubi.planid WHERE ubi.username in(SELECT  ui.username FROM userinfo ui RIGHT JOIN operators op ON op.id=ui.opid AND op.username=:username) AND ubi.planid!=0 GROUP BY ubi.planName ORDER BY active DESC LIMIT 6", resultSetMapping = "Mapping.PlanDetail")
@SqlResultSetMapping(name = "Mapping.PlanDetail", classes = {
		@ConstructorResult(targetClass = PlanDetailDTO.class, columns = {
				@ColumnResult(name = "planName", type = String.class),
				@ColumnResult(name = "active", type = Integer.class),
				@ColumnResult(name = "deactive", type = Integer.class) }) })

//@NamedNativeQuery(name = "Userbillinfo.getMaxDataUsersAdmin", query = "SELECT gb.username,if(gb.GB_total is null,0,gb.GB_total)as GB_total FROM ( SELECT username,ROUND((SUM(acctinputoctets)/1000/1000/1000)+(SUM(acctoutputoctets)/1000/1000/1000),1) AS GB_total FROM  data_usage_by_period WHERE  period_end IS NOT NULL AND period_start>=:t1 AND period_start<=:t2 GROUP BY DATE(period_start),username) AS gb ORDER BY gb.GB_total DESC LIMIT 5", resultSetMapping = "Mapping.MaxDataUser")
//@NamedNativeQuery(name = "Userbillinfo.getMaxDataUsersByOpid", query = "SELECT gb.username,if(gb.GB_total is null,0,gb.GB_total)as GB_total FROM ( SELECT username,ROUND((SUM(acctinputoctets)/1000/1000/1000)+(SUM(acctoutputoctets)/1000/1000/1000),1) AS GB_total FROM  data_usage_by_period WHERE  period_end IS NOT NULL AND period_start>=:t1 AND period_start<=:t2 AND username in(SELECT  ui.username FROM userinfo ui RIGHT JOIN operators op ON op.id=ui.opid AND op.username=:username)GROUP BY DATE(period_start),username) AS gb ORDER BY gb.GB_total DESC LIMIT 5", resultSetMapping = "Mapping.MaxDataUser")
@NamedNativeQuery(name = "Userbillinfo.getMaxDataUsersAdmin", query = "SELECT gb.username,if(gb.GB_total is null,0,gb.GB_total)as GB_total FROM ( SELECT username,ROUND(SUM(acctinputoctets+acctoutputoctets)) AS GB_total FROM  data_usage_by_period WHERE  period_end IS NOT NULL  GROUP BY DATE(period_start),username) AS gb ORDER BY gb.GB_total DESC LIMIT 3", resultSetMapping = "Mapping.MaxDataUser")
@NamedNativeQuery(name = "Userbillinfo.getMaxDataUsersByOpid", query = "SELECT gb.username,if(gb.GB_total is null,0,gb.GB_total)as GB_total FROM ( SELECT username,ROUND(SUM(acctinputoctets+acctoutputoctets)) AS GB_total FROM  data_usage_by_period WHERE  period_end IS NOT NULL  AND username in(SELECT  ui.username FROM userinfo ui RIGHT JOIN operators op ON op.id=ui.opid AND op.username=:username)GROUP BY DATE(period_start),username) AS gb ORDER BY gb.GB_total DESC LIMIT 3", resultSetMapping = "Mapping.MaxDataUser")
@SqlResultSetMapping(name = "Mapping.MaxDataUser", classes = {
		@ConstructorResult(targetClass = General.class, columns = {
				@ColumnResult(name = "username", type = String.class),
				@ColumnResult(name = "GB_total", type = String.class) }) })

@NamedNativeQuery(name = "Userbillinfo.getExpiredUserList", query = "SELECT concat(ui.firstname,' ',ui.lastname) AS name,ui.id,ui.opid AS lcoid,ui.username,ui.password,ui.mobilephone,ubi.planName,ubi.quotastartdate,ubi.quotaexpirydate,op.vlanid,CONCAT(op.firstname,'  ',op.lastname) AS lconame,if(ra2.ustatus is null,if(ui.verificationstatus=0,'New',if(ubi.quotaexpirydate<now(),'Expiry','Offline')),ra2.ustatus) AS status from userinfo ui LEFT JOIN userbillinfo ubi ON ubi.username=ui.username  LEFT JOIN operators op ON op.id=ui.opid LEFT JOIN (SELECT username,if(COUNT(username)>=1,'Online','Offline') AS ustatus FROM radacct WHERE AcctStopTime IS NULL GROUP BY username) AS ra2 ON ui.username=ra2.username WHERE   ubi.quotaexpirydate>=:start AND ubi.quotaexpirydate<=:end", resultSetMapping = "Mapping.getUsersDetailsByStatusRS")
@NamedNativeQuery(name = "Userbillinfo.getExpiredUserListByOpid", query = "SELECT concat(ui.firstname,' ',ui.lastname) AS name,ui.id,ui.opid AS lcoid,ui.username,ui.password,ui.mobilephone,ubi.planName,ubi.quotastartdate,ubi.quotaexpirydate,op.vlanid,CONCAT(op.firstname,'  ',op.lastname) AS lconame,if(ra2.ustatus is null,if(ui.verificationstatus=0,'New',if(ubi.quotaexpirydate<now(),'Expiry','Offline')),ra2.ustatus) AS status from userinfo ui LEFT JOIN userbillinfo ubi ON ubi.username=ui.username  LEFT JOIN operators op ON op.id=ui.opid LEFT JOIN (SELECT username,if(COUNT(username)>=1,'Online','Offline') AS ustatus FROM radacct WHERE AcctStopTime IS NULL GROUP BY username) AS ra2 ON ui.username=ra2.username WHERE  op.username=:username AND ubi.quotaexpirydate>=:start AND ubi.quotaexpirydate<=:end", resultSetMapping = "Mapping.getUsersDetailsByStatusRS")
@NamedNativeQuery(name = "Userbillinfo.getStatictisUserlist", query = "SELECT concat(ui.firstname,' ',ui.lastname) AS name,ui.id,ui.opid AS lcoid,ui.username,ui.password,ui.mobilephone,ubi.planName,ubi.quotastartdate,ubi.quotaexpirydate,op.vlanid,CONCAT(op.firstname,'  ',op.lastname) AS lconame,if(ra2.ustatus is null,if(ui.verificationstatus=0,'New',if(ubi.quotaexpirydate<now(),'Expiry','Offline')),ra2.ustatus) AS status from userinfo ui LEFT JOIN userbillinfo ubi ON ubi.username=ui.username  LEFT JOIN operators op ON op.id=ui.opid LEFT JOIN (SELECT username,if(COUNT(username)>=1,'Online','Offline') AS ustatus FROM radacct WHERE AcctStopTime IS NULL GROUP BY username) AS ra2 ON ui.username=ra2.username WHERE ui.verificationstatus=:status AND ui.is_delete=0 AND (date(ui.creationdate)=curdate())", resultSetMapping = "Mapping.getUsersDetailsByStatusRS")
@NamedNativeQuery(name = "Userbillinfo.getStatictisExpiryUserlist", query = "SELECT concat(ui.firstname,' ',ui.lastname) AS name,ui.id,ui.opid AS lcoid,ui.username,ui.password,ui.mobilephone,ubi.planName,ubi.quotastartdate,ubi.quotaexpirydate,op.vlanid,CONCAT(op.firstname,'  ',op.lastname) AS lconame,if(ra2.ustatus is null,if(ui.verificationstatus=0,'New',if(ubi.quotaexpirydate<now(),'Expiry','Offline')),ra2.ustatus) AS status from userinfo ui LEFT JOIN userbillinfo ubi ON ubi.username=ui.username  LEFT JOIN operators op ON op.id=ui.opid LEFT JOIN (SELECT username,if(COUNT(username)>=1,'Online','Offline') AS ustatus FROM radacct WHERE AcctStopTime IS NULL GROUP BY username) AS ra2 ON ui.username=ra2.username WHERE ui.is_delete=0 AND ui.username=ubi.username AND ui.opid>0 AND (ubi.quotaexpirydate>=:date AND ubi.quotaexpirydate<=now()) AND ui.verificationstatus=2", resultSetMapping = "Mapping.getUsersDetailsByStatusRS")
@NamedNativeQuery(name = "Userbillinfo.getStatictisActiveUserlist", query = "SELECT concat(ui.firstname,' ',ui.lastname) AS name,ui.id,ui.opid AS lcoid,ui.username,ui.password,ui.mobilephone,ubi.planName,ubi.quotastartdate,ubi.quotaexpirydate,op.vlanid,CONCAT(op.firstname,'  ',op.lastname) AS lconame,if(ra2.ustatus is null,if(ui.verificationstatus=0,'New',if(ubi.quotaexpirydate<now(),'Expiry','Offline')),ra2.ustatus) AS status from userinfo ui LEFT JOIN userbillinfo ubi ON ubi.username=ui.username  LEFT JOIN operators op ON op.id=ui.opid LEFT JOIN (SELECT username,if(COUNT(username)>=1,'Online','Offline') AS ustatus FROM radacct WHERE AcctStopTime IS NULL GROUP BY username) AS ra2 ON ui.username=ra2.username WHERE ui.is_delete=0 AND ui.username=ubi.username AND ui.opid>0 AND ubi.quotaexpirydate>=now() AND ui.verificationstatus=2", resultSetMapping = "Mapping.getUsersDetailsByStatusRS")
@NamedNativeQuery(name = "Userbillinfo.getStatictisUserlistByOpid", query = "SELECT concat(ui.firstname,' ',ui.lastname) AS name,ui.id,ui.opid AS lcoid,ui.username,ui.password,ui.mobilephone,ubi.planName,ubi.quotastartdate,ubi.quotaexpirydate,op.vlanid,CONCAT(op.firstname,'  ',op.lastname) AS lconame,if(ra2.ustatus is null,if(ui.verificationstatus=0,'New',if(ubi.quotaexpirydate<now(),'Expiry','Offline')),ra2.ustatus) AS status from userinfo ui LEFT JOIN userbillinfo ubi ON ubi.username=ui.username  LEFT JOIN operators op ON op.id=ui.opid LEFT JOIN (SELECT username,if(COUNT(username)>=1,'Online','Offline') AS ustatus FROM radacct WHERE AcctStopTime IS NULL GROUP BY username) AS ra2 ON ui.username=ra2.username WHERE ui.verificationstatus=:status AND ui.is_delete=0 AND (date(ui.creationdate)=curdate()) AND op.username=:username", resultSetMapping = "Mapping.getUsersDetailsByStatusRS")
@NamedNativeQuery(name = "Userbillinfo.getStatictisExpiryUserlistByOpid", query = "SELECT concat(ui.firstname,' ',ui.lastname) AS name,ui.id,ui.opid AS lcoid,ui.username,ui.password,ui.mobilephone,ubi.planName,ubi.quotastartdate,ubi.quotaexpirydate,op.vlanid,CONCAT(op.firstname,'  ',op.lastname) AS lconame,if(ra2.ustatus is null,if(ui.verificationstatus=0,'New',if(ubi.quotaexpirydate<now(),'Expiry','Offline')),ra2.ustatus) AS status from userinfo ui LEFT JOIN userbillinfo ubi ON ubi.username=ui.username  LEFT JOIN operators op ON op.id=ui.opid LEFT JOIN (SELECT username,if(COUNT(username)>=1,'Online','Offline') AS ustatus FROM radacct WHERE AcctStopTime IS NULL GROUP BY username) AS ra2 ON ui.username=ra2.username WHERE ui.is_delete=0 AND ui.username=ubi.username AND ui.opid>0 AND (ubi.quotaexpirydate>=:date AND ubi.quotaexpirydate<=now()) AND ui.verificationstatus=2 AND op.username=:username", resultSetMapping = "Mapping.getUsersDetailsByStatusRS")
@NamedNativeQuery(name = "Userbillinfo.getStatictisActiveUserlistByOpid", query = "SELECT concat(ui.firstname,' ',ui.lastname) AS name,ui.id,ui.opid AS lcoid,ui.username,ui.password,ui.mobilephone,ubi.planName,ubi.quotastartdate,ubi.quotaexpirydate,op.vlanid,CONCAT(op.firstname,'  ',op.lastname) AS lconame,if(ra2.ustatus is null,if(ui.verificationstatus=0,'New',if(ubi.quotaexpirydate<now(),'Expiry','Offline')),ra2.ustatus) AS status from userinfo ui LEFT JOIN userbillinfo ubi ON ubi.username=ui.username  LEFT JOIN operators op ON op.id=ui.opid LEFT JOIN (SELECT username,if(COUNT(username)>=1,'Online','Offline') AS ustatus FROM radacct WHERE AcctStopTime IS NULL GROUP BY username) AS ra2 ON ui.username=ra2.username WHERE ui.is_delete=0 AND ui.username=ubi.username AND ui.opid>0 AND ubi.quotaexpirydate>=now() AND ui.verificationstatus=2 AND op.username=:username", resultSetMapping = "Mapping.getUsersDetailsByStatusRS")
@NamedNativeQuery(name = "Userbillinfo.getStatictisFupUserList", query = "SELECT concat(ui.firstname,' ',ui.lastname) AS name,ui.id,ui.opid AS lcoid,ui.username,ui.password,ui.mobilephone,ubi.planName,ubi.quotastartdate,ubi.quotaexpirydate,op.vlanid,CONCAT(op.firstname,'  ',op.lastname) AS lconame,if(ra2.ustatus is null,if(ui.verificationstatus=0,'New',if(ubi.quotaexpirydate<now(),'Expiry','Offline')),ra2.ustatus) AS status FROM userbillinfo ubi LEFT JOIN userinfo ui ON ui.username=ubi.username LEFT JOIN quotasubplan qsp ON qsp.id=ubi.planid LEFT JOIN quotaplan qp ON qp.id=qsp.planid LEFT JOIN operators op ON op.id=ui.opid LEFT JOIN (SELECT username,if(COUNT(username)>=1,'Online','Offline') AS ustatus FROM radacct WHERE AcctStopTime IS NULL GROUP BY username) AS ra2 ON ui.username=ra2.username  WHERE ui.verificationstatus=2 AND  ui.is_delete=0 AND qp.packageType='fup' AND DATE(ubi.quotastartdate)=CURDATE()", resultSetMapping = "Mapping.getUsersDetailsByStatusRS")
@NamedNativeQuery(name = "Userbillinfo.getStatictisFupUserListByOpid", query = "SELECT concat(ui.firstname,' ',ui.lastname) AS name,ui.id,ui.opid AS lcoid,ui.username,ui.password,ui.mobilephone,ubi.planName,ubi.quotastartdate,ubi.quotaexpirydate,op.vlanid,CONCAT(op.firstname,'  ',op.lastname) AS lconame,if(ra2.ustatus is null,if(ui.verificationstatus=0,'New',if(ubi.quotaexpirydate<now(),'Expiry','Offline')),ra2.ustatus) AS status FROM userbillinfo ubi LEFT JOIN userinfo ui ON ui.username=ubi.username LEFT JOIN quotasubplan qsp ON qsp.id=ubi.planid LEFT JOIN quotaplan qp ON qp.id=qsp.planid LEFT JOIN operators op ON op.id=ui.opid LEFT JOIN (SELECT username,if(COUNT(username)>=1,'Online','Offline') AS ustatus FROM radacct WHERE AcctStopTime IS NULL GROUP BY username) AS ra2 ON ui.username=ra2.username  WHERE ui.verificationstatus=2 AND  ui.is_delete=0 AND qp.packageType='fup' AND DATE(ubi.quotastartdate)=CURDATE() AND op.username=:username", resultSetMapping = "Mapping.getUsersDetailsByStatusRS")
@NamedNativeQuery(name = "Userbillinfo.getUserCreatedListToday", query = "SELECT concat(ui.firstname,' ',ui.lastname) AS name,ui.id,ui.opid AS lcoid,ui.username,ui.password,ui.mobilephone,ubi.planName,ubi.quotastartdate,ubi.quotaexpirydate,op.vlanid,CONCAT(op.firstname,'  ',op.lastname) AS lconame,if(ra2.ustatus is null,if(ui.verificationstatus=0,'New',if(ubi.quotaexpirydate<now(),'Expiry','Offline')),ra2.ustatus) AS status from userinfo ui LEFT JOIN userbillinfo ubi ON ubi.username=ui.username  LEFT JOIN operators op ON op.id=ui.opid LEFT JOIN (SELECT username,if(COUNT(username)>=1,'Online','Offline') AS ustatus FROM radacct WHERE AcctStopTime IS NULL GROUP BY username) AS ra2 ON ui.username=ra2.username WHERE ui.verificationstatus=2 AND DATE(ui.creationdate)=CURDATE()", resultSetMapping = "Mapping.getUsersDetailsByStatusRS")
@NamedNativeQuery(name = "Userbillinfo.getUserCreatedListYester", query = "SELECT concat(ui.firstname,' ',ui.lastname) AS name,ui.id,ui.opid AS lcoid,ui.username,ui.password,ui.mobilephone,ubi.planName,ubi.quotastartdate,ubi.quotaexpirydate,op.vlanid,CONCAT(op.firstname,'  ',op.lastname) AS lconame,if(ra2.ustatus is null,if(ui.verificationstatus=0,'New',if(ubi.quotaexpirydate<now(),'Expiry','Offline')),ra2.ustatus) AS status from userinfo ui LEFT JOIN userbillinfo ubi ON ubi.username=ui.username  LEFT JOIN operators op ON op.id=ui.opid LEFT JOIN (SELECT username,if(COUNT(username)>=1,'Online','Offline') AS ustatus FROM radacct WHERE AcctStopTime IS NULL GROUP BY username) AS ra2 ON ui.username=ra2.username WHERE ui.verificationstatus=2 AND DATE(ui.creationdate)=(CURDATE()-INTERVAL 1 DAY)", resultSetMapping = "Mapping.getUsersDetailsByStatusRS")
@NamedNativeQuery(name = "Userbillinfo.getUserCreatedListWweek", query = "SELECT concat(ui.firstname,' ',ui.lastname) AS name,ui.id,ui.opid AS lcoid,ui.username,ui.password,ui.mobilephone,ubi.planName,ubi.quotastartdate,ubi.quotaexpirydate,op.vlanid,CONCAT(op.firstname,'  ',op.lastname) AS lconame,if(ra2.ustatus is null,if(ui.verificationstatus=0,'New',if(ubi.quotaexpirydate<now(),'Expiry','Offline')),ra2.ustatus) AS status from userinfo ui LEFT JOIN userbillinfo ubi ON ubi.username=ui.username  LEFT JOIN operators op ON op.id=ui.opid LEFT JOIN (SELECT username,if(COUNT(username)>=1,'Online','Offline') AS ustatus FROM radacct WHERE AcctStopTime IS NULL GROUP BY username) AS ra2 ON ui.username=ra2.username WHERE ui.verificationstatus=2 AND (DATE(ui.creationdate)>=(CURDATE()-INTERVAL 7 DAY) AND (DATE(ui.creationdate)<=CURDATE()))", resultSetMapping = "Mapping.getUsersDetailsByStatusRS")
@NamedNativeQuery(name = "Userbillinfo.getUserCreatedListMonth", query = "SELECT concat(ui.firstname,' ',ui.lastname) AS name,ui.id,ui.opid AS lcoid,ui.username,ui.password,ui.mobilephone,ubi.planName,ubi.quotastartdate,ubi.quotaexpirydate,op.vlanid,CONCAT(op.firstname,'  ',op.lastname) AS lconame,if(ra2.ustatus is null,if(ui.verificationstatus=0,'New',if(ubi.quotaexpirydate<now(),'Expiry','Offline')),ra2.ustatus) AS status from userinfo ui LEFT JOIN userbillinfo ubi ON ubi.username=ui.username  LEFT JOIN operators op ON op.id=ui.opid LEFT JOIN (SELECT username,if(COUNT(username)>=1,'Online','Offline') AS ustatus FROM radacct WHERE AcctStopTime IS NULL GROUP BY username) AS ra2 ON ui.username=ra2.username WHERE ui.verificationstatus=2 AND (DATE(ui.creationdate)>=(CURDATE()-INTERVAL 30 DAY) AND (DATE(ui.creationdate)<=CURDATE()))", resultSetMapping = "Mapping.getUsersDetailsByStatusRS")
@NamedNativeQuery(name = "Userbillinfo.getUserCreatedListTwoMonth", query = "SELECT concat(ui.firstname,' ',ui.lastname) AS name,ui.id,ui.opid AS lcoid,ui.username,ui.password,ui.mobilephone,ubi.planName,ubi.quotastartdate,ubi.quotaexpirydate,op.vlanid,CONCAT(op.firstname,'  ',op.lastname) AS lconame,if(ra2.ustatus is null,if(ui.verificationstatus=0,'New',if(ubi.quotaexpirydate<now(),'Expiry','Offline')),ra2.ustatus) AS status from userinfo ui LEFT JOIN userbillinfo ubi ON ubi.username=ui.username  LEFT JOIN operators op ON op.id=ui.opid LEFT JOIN (SELECT username,if(COUNT(username)>=1,'Online','Offline') AS ustatus FROM radacct WHERE AcctStopTime IS NULL GROUP BY username) AS ra2 ON ui.username=ra2.username WHERE ui.verificationstatus=2 AND (DATE(ui.creationdate)>=(CURDATE()-INTERVAL 60 DAY) AND (DATE(ui.creationdate)<=CURDATE()))", resultSetMapping = "Mapping.getUsersDetailsByStatusRS")
@NamedNativeQuery(name = "Userbillinfo.getUserCreatedListTodayByOpid", query = "SELECT concat(ui.firstname,' ',ui.lastname) AS name,ui.id,ui.opid AS lcoid,ui.username,ui.password,ui.mobilephone,ubi.planName,ubi.quotastartdate,ubi.quotaexpirydate,op.vlanid,CONCAT(op.firstname,'  ',op.lastname) AS lconame,if(ra2.ustatus is null,if(ui.verificationstatus=0,'New',if(ubi.quotaexpirydate<now(),'Expiry','Offline')),ra2.ustatus) AS status from userinfo ui LEFT JOIN userbillinfo ubi ON ubi.username=ui.username  LEFT JOIN operators op ON op.id=ui.opid LEFT JOIN (SELECT username,if(COUNT(username)>=1,'Online','Offline') AS ustatus FROM radacct WHERE AcctStopTime IS NULL GROUP BY username) AS ra2 ON ui.username=ra2.username WHERE ui.verificationstatus=2 AND DATE(ui.creationdate)=CURDATE() AND op.username=:username", resultSetMapping = "Mapping.getUsersDetailsByStatusRS")
@NamedNativeQuery(name = "Userbillinfo.getUserCreatedListYesterByOpid", query = "SELECT concat(ui.firstname,' ',ui.lastname) AS name,ui.id,ui.opid AS lcoid,ui.username,ui.password,ui.mobilephone,ubi.planName,ubi.quotastartdate,ubi.quotaexpirydate,op.vlanid,CONCAT(op.firstname,'  ',op.lastname) AS lconame,if(ra2.ustatus is null,if(ui.verificationstatus=0,'New',if(ubi.quotaexpirydate<now(),'Expiry','Offline')),ra2.ustatus) AS status from userinfo ui LEFT JOIN userbillinfo ubi ON ubi.username=ui.username  LEFT JOIN operators op ON op.id=ui.opid LEFT JOIN (SELECT username,if(COUNT(username)>=1,'Online','Offline') AS ustatus FROM radacct WHERE AcctStopTime IS NULL GROUP BY username) AS ra2 ON ui.username=ra2.username WHERE ui.verificationstatus=2 AND DATE(ui.creationdate)=(CURDATE()-INTERVAL 1 DAY) AND op.username=:username", resultSetMapping = "Mapping.getUsersDetailsByStatusRS")
@NamedNativeQuery(name = "Userbillinfo.getUserCreatedListWweekByOpid", query = "SELECT concat(ui.firstname,' ',ui.lastname) AS name,ui.id,ui.opid AS lcoid,ui.username,ui.password,ui.mobilephone,ubi.planName,ubi.quotastartdate,ubi.quotaexpirydate,op.vlanid,CONCAT(op.firstname,'  ',op.lastname) AS lconame,if(ra2.ustatus is null,if(ui.verificationstatus=0,'New',if(ubi.quotaexpirydate<now(),'Expiry','Offline')),ra2.ustatus) AS status from userinfo ui LEFT JOIN userbillinfo ubi ON ubi.username=ui.username  LEFT JOIN operators op ON op.id=ui.opid LEFT JOIN (SELECT username,if(COUNT(username)>=1,'Online','Offline') AS ustatus FROM radacct WHERE AcctStopTime IS NULL GROUP BY username) AS ra2 ON ui.username=ra2.username WHERE ui.verificationstatus=2 AND (DATE(ui.creationdate)>=(CURDATE()-INTERVAL 7 DAY) AND (DATE(ui.creationdate)<=CURDATE())) AND op.username=:username", resultSetMapping = "Mapping.getUsersDetailsByStatusRS")
@NamedNativeQuery(name = "Userbillinfo.getUserCreatedListMonthByOpid", query = "SELECT concat(ui.firstname,' ',ui.lastname) AS name,ui.id,ui.opid AS lcoid,ui.username,ui.password,ui.mobilephone,ubi.planName,ubi.quotastartdate,ubi.quotaexpirydate,op.vlanid,CONCAT(op.firstname,'  ',op.lastname) AS lconame,if(ra2.ustatus is null,if(ui.verificationstatus=0,'New',if(ubi.quotaexpirydate<now(),'Expiry','Offline')),ra2.ustatus) AS status from userinfo ui LEFT JOIN userbillinfo ubi ON ubi.username=ui.username  LEFT JOIN operators op ON op.id=ui.opid LEFT JOIN (SELECT username,if(COUNT(username)>=1,'Online','Offline') AS ustatus FROM radacct WHERE AcctStopTime IS NULL GROUP BY username) AS ra2 ON ui.username=ra2.username WHERE ui.verificationstatus=2 AND (DATE(ui.creationdate)>=(CURDATE()-INTERVAL 30 DAY) AND (DATE(ui.creationdate)<=CURDATE())) AND op.username=:username", resultSetMapping = "Mapping.getUsersDetailsByStatusRS")
@NamedNativeQuery(name = "Userbillinfo.getUserCreatedListTwoMonthByOpid", query = "SELECT concat(ui.firstname,' ',ui.lastname) AS name,ui.id,ui.opid AS lcoid,ui.username,ui.password,ui.mobilephone,ubi.planName,ubi.quotastartdate,ubi.quotaexpirydate,op.vlanid,CONCAT(op.firstname,'  ',op.lastname) AS lconame,if(ra2.ustatus is null,if(ui.verificationstatus=0,'New',if(ubi.quotaexpirydate<now(),'Expiry','Offline')),ra2.ustatus) AS status from userinfo ui LEFT JOIN userbillinfo ubi ON ubi.username=ui.username  LEFT JOIN operators op ON op.id=ui.opid LEFT JOIN (SELECT username,if(COUNT(username)>=1,'Online','Offline') AS ustatus FROM radacct WHERE AcctStopTime IS NULL GROUP BY username) AS ra2 ON ui.username=ra2.username WHERE ui.verificationstatus=2 AND (DATE(ui.creationdate)>=(CURDATE()-INTERVAL 60 DAY) AND (DATE(ui.creationdate)<=CURDATE())) AND op.username=:username", resultSetMapping = "Mapping.getUsersDetailsByStatusRS")
@SqlResultSetMapping(name = "Mapping.getUsersDetailsByStatusRS", classes = {
		@ConstructorResult(targetClass = UserDetailsDTO.class, columns = {
				@ColumnResult(name = "id", type = Integer.class), @ColumnResult(name = "name", type = String.class),
				@ColumnResult(name = "lconame", type = String.class),
				@ColumnResult(name = "quotastartdate", type = String.class),
				@ColumnResult(name = "quotaexpirydate", type = String.class),
				@ColumnResult(name = "planName", type = String.class),
				@ColumnResult(name = "username", type = String.class),
				@ColumnResult(name = "password", type = String.class),
				@ColumnResult(name = "mobilephone", type = String.class),
				@ColumnResult(name = "lcoid", type = Integer.class),
				@ColumnResult(name = "vlanid", type = Integer.class), @ColumnResult(name = "status") }) })

@NamedNativeQuery(name = "Userbillinfo.getUserCreatedCount", query = "SELECT(SELECT count(id) FROM userinfo WHERE verificationstatus=2 AND DATE(creationdate)=CURDATE())AS today,(SELECT count(id) FROM userinfo WHERE verificationstatus=2 AND DATE(creationdate)=(CURDATE()-INTERVAL 1 DAY))AS yesterday,(SELECT COUNT(id) FROM userinfo WHERE verificationstatus=2 AND (DATE(creationdate)>=(CURDATE()-INTERVAL 7 DAY) AND (DATE(creationdate)<=CURDATE())))AS lastweek,(SELECT COUNT(id) FROM userinfo WHERE verificationstatus=2 AND (DATE(creationdate)>=(CURDATE()-INTERVAL 30 DAY) AND (DATE(creationdate)<=CURDATE())))AS lastmonth,(SELECT COUNT(id) FROM userinfo WHERE verificationstatus=2 AND (DATE(creationdate)>=(CURDATE()-INTERVAL 60 DAY) AND (DATE(creationdate)<=CURDATE())))AS lasttwomonth", resultSetMapping = "Mapping.getUserCreatedCountRS")
@NamedNativeQuery(name = "Userbillinfo.getUserCreatedCountByOpid", query = "SELECT(SELECT count(id) FROM userinfo WHERE opid IN(SELECT id FROM operators WHERE username=:username) AND verificationstatus=2 AND DATE(creationdate)=CURDATE())AS today,(SELECT count(id) FROM userinfo WHERE  opid IN(SELECT id FROM operators WHERE username=:username) AND verificationstatus=2 AND DATE(creationdate)=(CURDATE()-INTERVAL 1 DAY))AS yesterday,(SELECT COUNT(id) FROM userinfo WHERE opid IN(SELECT id FROM operators WHERE username=:username) AND  verificationstatus=2 AND (DATE(creationdate)>=(CURDATE()-INTERVAL 7 DAY) AND (DATE(creationdate)<=CURDATE())))AS lastweek,(SELECT COUNT(id) FROM userinfo WHERE opid IN(SELECT id FROM operators WHERE username=:username) AND verificationstatus=2 AND (DATE(creationdate)>=(CURDATE()-INTERVAL 30 DAY) AND (DATE(creationdate)<=CURDATE())))AS lastmonth,(SELECT COUNT(id) FROM userinfo WHERE opid IN(SELECT id FROM operators WHERE username=:username) AND verificationstatus=2 AND (DATE(creationdate)>=(CURDATE()-INTERVAL 60 DAY) AND (DATE(creationdate)<=CURDATE())))AS lasttwomonth", resultSetMapping = "Mapping.getUserCreatedCountRS")
@SqlResultSetMapping(name = "Mapping.getUserCreatedCountRS", classes = {
		@ConstructorResult(targetClass = CustomerStatDTO.class, columns = {
				@ColumnResult(name = "today", type = long.class), @ColumnResult(name = "yesterday", type = long.class),
				@ColumnResult(name = "lastweek", type = long.class),
				@ColumnResult(name = "lastmonth", type = long.class),
				@ColumnResult(name = "lasttwomonth", type = long.class) }) })

public class Userbillinfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String username;
	private String planname;
	private int planid;
	private String quotastartdate;
	private String quotaexpirydate;
	private int hotspot_id;
	private String hotspotlocation;
	private String contactperson;
	private String company;
	private String email;
	private String phone;
	private String address;
	private String city;
	private String state;
	private String country;
	private String zip;
	private String paymentmethod;
	private String cash;
	private String creditcardname;
	private String creditcardnumber;
	private String creditcardverification;
	private String creditcardtype;
	private String creditcardexp;
	private String notes;
	private String changeuserbillinfo;
	private String leadvalue;
	private String coupon;
	private String ordertaker;
	private String billstatus;
	private String lastbill;
	private String nextbill;
	private int nextinvoicedue;
	private int billdue;
	private String postalinvoice;
	private String faxinvoice;
	private String emailinvoice;
	private int batch_id;
	private String creationdate;
	private String creationby;
	private String updatedate;
	private String updateby;

	public Userbillinfo() {
	}

	public Userbillinfo(String username, String planname, int planid, String quotastartdate, String quotaexpirydate,
			int hotspot_id, String hotspotlocation, String contactperson, String company, String email, String phone,
			String address, String city, String state, String country, String zip, String paymentmethod, String cash,
			String creditcardname, String creditcardnumber, String creditcardverification, String creditcardtype,
			String creditcardexp, String notes, String changeuserbillinfo, String leadvalue, String coupon,
			String ordertaker, String billstatus, String lastbill, String nextbill, int nextinvoicedue, int billdue,
			String postalinvoice, String faxinvoice, String emailinvoice, int batch_id, String creationdate,
			String creationby, String updatedate, String updateby) {
		super();
		this.username = username;
		this.planname = planname;
		this.planid = planid;
		this.quotastartdate = quotastartdate;
		this.quotaexpirydate = quotaexpirydate;
		this.hotspot_id = hotspot_id;
		this.hotspotlocation = hotspotlocation;
		this.contactperson = contactperson;
		this.company = company;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.city = city;
		this.state = state;
		this.country = country;
		this.zip = zip;
		this.paymentmethod = paymentmethod;
		this.cash = cash;
		this.creditcardname = creditcardname;
		this.creditcardnumber = creditcardnumber;
		this.creditcardverification = creditcardverification;
		this.creditcardtype = creditcardtype;
		this.creditcardexp = creditcardexp;
		this.notes = notes;
		this.changeuserbillinfo = changeuserbillinfo;
		this.leadvalue = leadvalue;
		this.coupon = coupon;
		this.ordertaker = ordertaker;
		this.billstatus = billstatus;
		this.lastbill = lastbill;
		this.nextbill = nextbill;
		this.nextinvoicedue = nextinvoicedue;
		this.billdue = billdue;
		this.postalinvoice = postalinvoice;
		this.faxinvoice = faxinvoice;
		this.emailinvoice = emailinvoice;
		this.batch_id = batch_id;
		this.creationdate = creationdate;
		this.creationby = creationby;
		this.updatedate = updatedate;
		this.updateby = updateby;
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

	public String getPlanname() {
		return planname;
	}

	public void setPlanname(String planname) {
		this.planname = planname;
	}

	public int getPlanid() {
		return planid;
	}

	public void setPlanid(int planid) {
		this.planid = planid;
	}

	public String getQuotastartdate() {
		return quotastartdate;
	}

	public void setQuotastartdate(String quotastartdate) {
		this.quotastartdate = quotastartdate;
	}

	public String getQuotaexpirydate() {
		return quotaexpirydate;
	}

	public void setQuotaexpirydate(String quotaexpirydate) {
		this.quotaexpirydate = quotaexpirydate;
	}

	public int getHotspot_id() {
		return hotspot_id;
	}

	public void setHotspot_id(int hotspot_id) {
		this.hotspot_id = hotspot_id;
	}

	public String getHotspotlocation() {
		return hotspotlocation;
	}

	public void setHotspotlocation(String hotspotlocation) {
		this.hotspotlocation = hotspotlocation;
	}

	public String getContactperson() {
		return contactperson;
	}

	public void setContactperson(String contactperson) {
		this.contactperson = contactperson;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getPaymentmethod() {
		return paymentmethod;
	}

	public void setPaymentmethod(String paymentmethod) {
		this.paymentmethod = paymentmethod;
	}

	public String getCash() {
		return cash;
	}

	public void setCash(String cash) {
		this.cash = cash;
	}

	public String getCreditcardname() {
		return creditcardname;
	}

	public void setCreditcardname(String creditcardname) {
		this.creditcardname = creditcardname;
	}

	public String getCreditcardnumber() {
		return creditcardnumber;
	}

	public void setCreditcardnumber(String creditcardnumber) {
		this.creditcardnumber = creditcardnumber;
	}

	public String getCreditcardverification() {
		return creditcardverification;
	}

	public void setCreditcardverification(String creditcardverification) {
		this.creditcardverification = creditcardverification;
	}

	public String getCreditcardtype() {
		return creditcardtype;
	}

	public void setCreditcardtype(String creditcardtype) {
		this.creditcardtype = creditcardtype;
	}

	public String getCreditcardexp() {
		return creditcardexp;
	}

	public void setCreditcardexp(String creditcardexp) {
		this.creditcardexp = creditcardexp;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getChangeuserbillinfo() {
		return changeuserbillinfo;
	}

	public void setChangeuserbillinfo(String changeuserbillinfo) {
		this.changeuserbillinfo = changeuserbillinfo;
	}

	public String getLeadvalue() {
		return leadvalue;
	}

	public void setLeadvalue(String leadvalue) {
		this.leadvalue = leadvalue;
	}

	public String getCoupon() {
		return coupon;
	}

	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}

	public String getOrdertaker() {
		return ordertaker;
	}

	public void setOrdertaker(String ordertaker) {
		this.ordertaker = ordertaker;
	}

	public String getBillstatus() {
		return billstatus;
	}

	public void setBillstatus(String billstatus) {
		this.billstatus = billstatus;
	}

	public String getLastbill() {
		return lastbill;
	}

	public void setLastbill(String lastbill) {
		this.lastbill = lastbill;
	}

	public String getNextbill() {
		return nextbill;
	}

	public void setNextbill(String nextbill) {
		this.nextbill = nextbill;
	}

	public int getNextinvoicedue() {
		return nextinvoicedue;
	}

	public void setNextinvoicedue(int nextinvoicedue) {
		this.nextinvoicedue = nextinvoicedue;
	}

	public int getBilldue() {
		return billdue;
	}

	public void setBilldue(int billdue) {
		this.billdue = billdue;
	}

	public String getPostalinvoice() {
		return postalinvoice;
	}

	public void setPostalinvoice(String postalinvoice) {
		this.postalinvoice = postalinvoice;
	}

	public String getFaxinvoice() {
		return faxinvoice;
	}

	public void setFaxinvoice(String faxinvoice) {
		this.faxinvoice = faxinvoice;
	}

	public String getEmailinvoice() {
		return emailinvoice;
	}

	public void setEmailinvoice(String emailinvoice) {
		this.emailinvoice = emailinvoice;
	}

	public int getBatch_id() {
		return batch_id;
	}

	public void setBatch_id(int batch_id) {
		this.batch_id = batch_id;
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

}
