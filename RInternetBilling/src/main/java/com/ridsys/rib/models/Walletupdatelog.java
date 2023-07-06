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
import com.ridsys.rib.DTO.OnlineOfflineDTO;
import com.ridsys.rib.DTO.WalletupdatelogDTO;

@Entity
@Table(name = "walletupdatelog")
@NamedNativeQuery(name = "Walletupdatelog.getOperatorRecentOnlineWalletUpdateHistory", query = "SELECT wul.id,wul.role,wul.username,CONCAT(op.firstname,' ',op.lastname) AS operatorname,wul.oldbalance,wul.amount,wul.newbalance,wul.referenceid,rt.type rechargetpename,wut.type walletupdatetypename,wul.paymentbyrole,wul.paymentbyusername,wul.creationdate FROM walletupdatelog wul LEFT JOIN operators op ON op.username=wul.username  INNER JOIN rechargetype rt ON rt.id=wul.rechargetype INNER JOIN walletupdatetype wut ON wul.walletupdatetypeid=wut.id AND (wul.paymentbyusername=:username OR wul.username=:username) AND (wul.paymentbyrole=:role OR wul.role=:role) AND wul.rechargetype IN (1) order by wul.id desc limit 3", resultSetMapping = "Mapping.WalletupdatelogDTO")
@NamedNativeQuery(name = "Walletupdatelog.getUserRecentOnlineWalletUpdateHistory", query = "SELECT wul.id,wul.role,wul.username,CONCAT(op.firstname,' ',op.lastname) AS operatorname,wul.oldbalance,wul.amount,wul.newbalance,wul.referenceid,rt.type rechargetpename,wut.type walletupdatetypename,wul.paymentbyrole,wul.paymentbyusername,wul.creationdate FROM walletupdatelog wul LEFT JOIN operators op ON op.id=(select opid from userinfo where username=:username) INNER JOIN rechargetype rt ON rt.id=wul.rechargetype INNER JOIN walletupdatetype wut ON wul.walletupdatetypeid=wut.id AND (wul.paymentbyusername=:username OR wul.username=:username) AND (wul.paymentbyrole=:role OR wul.role=:role) AND wul.rechargetype IN (1) order by wul.id desc limit 3", resultSetMapping = "Mapping.WalletupdatelogDTO")

//@NamedNativeQuery(name = "Walletupdatelog.getOperatorWalletUpdateHistory", query = "SELECT wul.id,wul.role,wul.username,CONCAT(op.firstname,' ',op.lastname) AS operatorname,wul.oldbalance,wul.amount,wul.newbalance,wul.referenceid,rt.type rechargetpename,wut.type walletupdatetypename,wul.paymentbyrole,wul.paymentbyusername,wul.creationdate FROM walletupdatelog wul LEFT JOIN operators op ON op.username=wul.username  INNER JOIN rechargetype rt ON rt.id=wul.rechargetype INNER JOIN walletupdatetype wut ON wul.walletupdatetypeid=wut.id AND (wul.paymentbyusername=:username OR wul.username=:username) AND (wul.paymentbyrole=:role OR wul.role=:role) AND wul.walletupdatetypeid IN (1,2,3,4) order by wul.id desc", resultSetMapping = "Mapping.WalletupdatelogDTO")
//@NamedNativeQuery(name = "Walletupdatelog.getOperatorWalletUpdateHistoryFtdate", query = "SELECT wul.id,wul.role,wul.username,CONCAT(op.firstname,' ',op.lastname) AS operatorname,wul.oldbalance,wul.amount,wul.newbalance,wul.referenceid,rt.type rechargetpename,wut.type walletupdatetypename,wul.paymentbyrole,wul.paymentbyusername,wul.creationdate FROM walletupdatelog wul LEFT JOIN operators op ON op.username=wul.username  INNER JOIN rechargetype rt ON rt.id=wul.rechargetype INNER JOIN walletupdatetype wut ON wul.walletupdatetypeid=wut.id AND (wul.paymentbyusername=:username OR wul.username=:username) AND (wul.paymentbyrole=:role OR wul.role=:role) AND wul.walletupdatetypeid IN (1,2,3,4) AND DATE(wul.creationdate)>=:fdate AND DATE(wul.creationdate)<=:tdate order by wul.id desc", resultSetMapping = "Mapping.WalletupdatelogDTO")
//@NamedNativeQuery(name = "Walletupdatelog.getUserWalletUpdateHistory", query = "SELECT wul.id,wul.role,wul.username,CONCAT(op.firstname,' ',op.lastname) AS operatorname,wul.oldbalance,wul.amount,wul.newbalance,wul.referenceid,rt.type rechargetpename,wut.type walletupdatetypename,wul.paymentbyrole,wul.paymentbyusername,wul.creationdate FROM walletupdatelog wul LEFT JOIN operators op ON op.id=(select opid from userinfo where username=:username) INNER JOIN rechargetype rt ON rt.id=wul.rechargetype INNER JOIN walletupdatetype wut ON wul.walletupdatetypeid=wut.id AND (wul.paymentbyusername=:username OR wul.username=:username) AND (wul.paymentbyrole=:role OR wul.role=:role) AND wul.walletupdatetypeid IN (3,4) order by wul.id desc", resultSetMapping = "Mapping.WalletupdatelogDTO")
//@NamedNativeQuery(name = "Walletupdatelog.getUserWalletUpdateHistoryFtdate", query = "SELECT wul.id,wul.role,wul.username,CONCAT(op.firstname,' ',op.lastname) AS operatorname,wul.oldbalance,wul.amount,wul.newbalance,wul.referenceid,rt.type rechargetpename,wut.type walletupdatetypename,wul.paymentbyrole,wul.paymentbyusername,wul.creationdate FROM walletupdatelog wul LEFT JOIN operators op ON op.id=(select opid from userinfo where username=:username) INNER JOIN rechargetype rt ON rt.id=wul.rechargetype INNER JOIN walletupdatetype wut ON wul.walletupdatetypeid=wut.id AND (wul.paymentbyusername=:username OR wul.username=:username) AND (wul.paymentbyrole=:role OR wul.role=:role) AND wul.walletupdatetypeid IN (3,4) AND DATE(wul.creationdate)>=:fdate AND DATE(wul.creationdate)<=:tdate order by wul.id desc", resultSetMapping = "Mapping.WalletupdatelogDTO")

@NamedNativeQuery(name = "Walletupdatelog.getOfflineWalletUpdateHistoryAllFtdate", query = "SELECT wul.id,wul.role,wul.username,CONCAT(op.firstname,' ',op.lastname) AS operatorname,wul.oldbalance,wul.amount,wul.newbalance,wul.referenceid,rt.type rechargetpename,wut.type walletupdatetypename,wul.paymentbyrole,wul.paymentbyusername,wul.creationdate FROM walletupdatelog wul  LEFT JOIN operators op ON op.username=wul.username INNER JOIN rechargetype rt ON rt.id=wul.rechargetype INNER JOIN walletupdatetype wut ON wul.walletupdatetypeid=wut.id AND wul.walletupdatetypeid IN (1,2,3,4) AND wul.rechargetype in (1) AND DATE(wul.creationdate)>=:fdate AND DATE(wul.creationdate)<=:tdate ORDER BY wul.id DESC", resultSetMapping = "Mapping.WalletupdatelogDTO")
@NamedNativeQuery(name = "Walletupdatelog.getOfflineWalletUpdateHistoryFtdate", query = "SELECT wul.id,wul.role,wul.username,CONCAT(op.firstname,' ',op.lastname) AS operatorname,wul.oldbalance,wul.amount,wul.newbalance,wul.referenceid,rt.type rechargetpename,wut.type walletupdatetypename,wul.paymentbyrole,wul.paymentbyusername,wul.creationdate FROM walletupdatelog wul  LEFT JOIN operators op ON op.username=wul.username INNER JOIN rechargetype rt ON rt.id=wul.rechargetype INNER JOIN walletupdatetype wut ON wul.walletupdatetypeid=wut.id AND (wul.paymentbyusername=:username OR wul.username=:username) AND wul.walletupdatetypeid IN (1,2,3,4) AND wul.rechargetype in (1) AND DATE(wul.creationdate)>=:fdate AND DATE(wul.creationdate)<=:tdate ORDER BY wul.id DESC", resultSetMapping = "Mapping.WalletupdatelogDTO")
@NamedNativeQuery(name = "Walletupdatelog.getOfflineWalletUpdateHistoryAll", query = "SELECT wul.id,wul.role,wul.username,CONCAT(op.firstname,' ',op.lastname) AS operatorname,wul.oldbalance,wul.amount,wul.newbalance,wul.referenceid,rt.type rechargetpename,wut.type walletupdatetypename,wul.paymentbyrole,wul.paymentbyusername,wul.creationdate FROM walletupdatelog wul  LEFT JOIN operators op ON op.username=wul.username INNER JOIN rechargetype rt ON rt.id=wul.rechargetype INNER JOIN walletupdatetype wut ON wul.walletupdatetypeid=wut.id AND wul.walletupdatetypeid IN (1,2,3,4) AND wul.rechargetype in (1) ORDER BY wul.id DESC", resultSetMapping = "Mapping.WalletupdatelogDTO")
@NamedNativeQuery(name = "Walletupdatelog.getOfflineWalletUpdateHistory", query = "SELECT wul.id,wul.role,wul.username,CONCAT(op.firstname,' ',op.lastname) AS operatorname,wul.oldbalance,wul.amount,wul.newbalance,wul.referenceid,rt.type rechargetpename,wut.type walletupdatetypename,wul.paymentbyrole,wul.paymentbyusername,wul.creationdate FROM walletupdatelog wul  LEFT JOIN operators op ON op.username=wul.username INNER JOIN rechargetype rt ON rt.id=wul.rechargetype INNER JOIN walletupdatetype wut ON wul.walletupdatetypeid=wut.id AND (wul.paymentbyusername=:username OR wul.username=:username) AND wul.walletupdatetypeid IN (1,2,3,4) AND wul.rechargetype in (1) ORDER BY wul.id DESC", resultSetMapping = "Mapping.WalletupdatelogDTO")
@SqlResultSetMapping(name = "Mapping.WalletupdatelogDTO", classes = {
		@ConstructorResult(targetClass = WalletupdatelogDTO.class, columns = {
				@ColumnResult(name = "id", type = Integer.class), @ColumnResult(name = "role"),
				@ColumnResult(name = "username"), @ColumnResult(name = "oldbalance"), @ColumnResult(name = "amount"),
				@ColumnResult(name = "newbalance"), @ColumnResult(name = "referenceid"),
				@ColumnResult(name = "rechargetpename"), @ColumnResult(name = "walletupdatetypename"),
				@ColumnResult(name = "paymentbyrole"), @ColumnResult(name = "paymentbyusername"),
				@ColumnResult(name = "creationdate", type = String.class), @ColumnResult(name = "operatorname") }) })

@NamedNativeQuery(name = "Walletupdatelog.getOperatorWalletUpdateHistory", query = "SELECT wul.id,wul.role,if(concat(ui.firstname,' ',ui.lastname) is null,' ',concat(ui.firstname,' ',ui.lastname))AS username,CONCAT(op.firstname,' ',op.lastname) AS operatorname,wul.oldbalance,wul.amount,wul.newbalance,wul.referenceid,rt.type rechargetpename,wut.type walletupdatetypename,wul.paymentbyrole,wul.paymentbyusername,wul.creationdate,if(wul.rechargetype=1,if(opl.transactionid is null,'Offline','Online'),' ') AS status FROM walletupdatelog wul LEFT JOIN operators op ON op.username=wul.username LEFT JOIN quotaplanmanagelog qpl ON qpl.rechargeid=wul.referenceid LEFT JOIN userinfo ui ON ui.username=qpl.username LEFT JOIN online_payment_log opl ON opl.transactionid=wul.referenceid INNER JOIN rechargetype rt ON rt.id=wul.rechargetype INNER JOIN walletupdatetype wut ON wul.walletupdatetypeid=wut.id AND (wul.paymentbyusername=:username OR wul.username=:username) AND (wul.paymentbyrole=:role OR wul.role=:role) AND wul.walletupdatetypeid IN (1,2,3,4) order by wul.id desc", resultSetMapping = "Mapping.WalletupdatelogDTORS")
@NamedNativeQuery(name = "Walletupdatelog.getOperatorWalletUpdateHistoryFtdate", query = "SELECT wul.id,wul.role,if(concat(ui.firstname,' ',ui.lastname) is null,' ',concat(ui.firstname,' ',ui.lastname))AS username,CONCAT(op.firstname,' ',op.lastname) AS operatorname,wul.oldbalance,wul.amount,wul.newbalance,wul.referenceid,rt.type rechargetpename,wut.type walletupdatetypename,wul.paymentbyrole,wul.paymentbyusername,wul.creationdate,if(wul.rechargetype=1,if(opl.transactionid is null,'Offline','Online'),' ') AS status FROM walletupdatelog wul LEFT JOIN operators op ON op.username=wul.username LEFT JOIN quotaplanmanagelog qpl ON qpl.rechargeid=wul.referenceid LEFT JOIN userinfo ui ON ui.username=qpl.username LEFT JOIN online_payment_log opl ON opl.transactionid=wul.referenceid INNER JOIN rechargetype rt ON rt.id=wul.rechargetype INNER JOIN walletupdatetype wut ON wul.walletupdatetypeid=wut.id AND (wul.paymentbyusername=:username OR wul.username=:username) AND (wul.paymentbyrole=:role OR wul.role=:role) AND wul.walletupdatetypeid IN (1,2,3,4) AND DATE(wul.creationdate)>=:fdate AND DATE(wul.creationdate)<=:tdate order by wul.id desc", resultSetMapping = "Mapping.WalletupdatelogDTORS")
@NamedNativeQuery(name = "Walletupdatelog.getUserWalletUpdateHistory", query = "SELECT wul.id,wul.role,if(concat(ui.firstname,' ',ui.lastname) is null,' ',concat(ui.firstname,' ',ui.lastname))AS username,CONCAT(op.firstname,' ',op.lastname) AS operatorname,wul.oldbalance,wul.amount,wul.newbalance,wul.referenceid,rt.type rechargetpename,wut.type walletupdatetypename,wul.paymentbyrole,wul.paymentbyusername,wul.creationdate,if(wul.rechargetype=1,if(opl.transactionid is null,'Offline','Online'),' ') AS status FROM walletupdatelog wul LEFT JOIN operators op ON op.id=(select opid from userinfo where username=:username) INNER JOIN rechargetype rt ON rt.id=wul.rechargetype LEFT JOIN quotaplanmanagelog qpl ON qpl.rechargeid=wul.referenceid LEFT JOIN userinfo ui ON ui.username=qpl.username LEFT JOIN online_payment_log opl ON opl.transactionid=wul.referenceid INNER JOIN walletupdatetype wut ON wul.walletupdatetypeid=wut.id AND (wul.paymentbyusername=:username OR wul.username=:username) AND (wul.paymentbyrole=:role OR wul.role=:role) AND wul.walletupdatetypeid IN (3,4) order by wul.id desc", resultSetMapping = "Mapping.WalletupdatelogDTORS")
@NamedNativeQuery(name = "Walletupdatelog.getUserWalletUpdateHistoryFtdate", query = "SELECT wul.id,wul.role,if(concat(ui.firstname,' ',ui.lastname) is null,' ',concat(ui.firstname,' ',ui.lastname))AS username,CONCAT(op.firstname,' ',op.lastname) AS operatorname,wul.oldbalance,wul.amount,wul.newbalance,wul.referenceid,rt.type rechargetpename,wut.type walletupdatetypename,wul.paymentbyrole,wul.paymentbyusername,wul.creationdate,if(wul.rechargetype=1,if(opl.transactionid is null,'Offline','Online'),' ') AS status FROM walletupdatelog wul LEFT JOIN operators op ON op.id=(select opid from userinfo where username=:username) INNER JOIN rechargetype rt ON rt.id=wul.rechargetype LEFT JOIN quotaplanmanagelog qpl ON qpl.rechargeid=wul.referenceid LEFT JOIN userinfo ui ON ui.username=qpl.username LEFT JOIN online_payment_log opl ON opl.transactionid=wul.referenceid INNER JOIN walletupdatetype wut ON wul.walletupdatetypeid=wut.id AND (wul.paymentbyusername=:username OR wul.username=:username) AND (wul.paymentbyrole=:role OR wul.role=:role) AND wul.walletupdatetypeid IN (3,4) AND DATE(wul.creationdate)>=:fdate AND DATE(wul.creationdate)<=:tdate order by wul.id desc", resultSetMapping = "Mapping.WalletupdatelogDTORS")
@SqlResultSetMapping(name = "Mapping.WalletupdatelogDTORS", classes = {
		@ConstructorResult(targetClass = WalletupdatelogDTO.class, columns = {
				@ColumnResult(name = "id", type = Integer.class), @ColumnResult(name = "role"),
				@ColumnResult(name = "username"), @ColumnResult(name = "oldbalance"), @ColumnResult(name = "amount"),
				@ColumnResult(name = "newbalance"), @ColumnResult(name = "referenceid"),
				@ColumnResult(name = "rechargetpename"), @ColumnResult(name = "walletupdatetypename"),
				@ColumnResult(name = "paymentbyrole"), @ColumnResult(name = "paymentbyusername"),
				@ColumnResult(name = "creationdate", type = String.class), @ColumnResult(name = "operatorname"),
				@ColumnResult(name = "status") }) })

//@NamedNativeQuery(name = "Walletupdatelog.getWalletAmountDatewise", query = "SELECT(SELECT if(SUM(amount) is null,0,sum(amount)) FROM offline_payment_log WHERE creationdate>=:t1 AND creationdate<=:t2)AS offline,(SELECT if(SUM(amount) is null,0,sum(amount)) FROM online_payment_log WHERE role='OPERATOR' AND (tstarttime>=:t1 AND tstarttime<=:t2) AND status='Completed')AS lcoOnline,(SELECT if(SUM(amount) is null,0,sum(amount)) FROM online_payment_log WHERE role='USER' AND (tstarttime>=:t1 AND tstarttime<=:t2) AND status='Completed')AS subOnline", resultSetMapping = "Mapping.OnlineOfflineDTO")
@NamedNativeQuery(name = "Walletupdatelog.getWalletAmountDatewise", query = "SELECT(SELECT if(SUM(amount) is null,0,ROUND(sum(amount),2)) FROM offline_payment_log WHERE DATE(creationdate)=curdate())AS offline,(SELECT if(SUM(amount) is null,0,ROUND(sum(amount),2)) FROM online_payment_log WHERE role='OPERATOR' AND DATE(tstarttime)=curdate() AND resmsg LIKE '%success%')AS lcoOnline,(SELECT if(SUM(amount) is null,0,ROUND(sum(amount),2)) FROM online_payment_log WHERE role='USER' AND DATE(tstarttime)=curdate() AND resmsg LIKE '%success%')AS subOnline", resultSetMapping = "Mapping.OnlineOfflineDTO")
@SqlResultSetMapping(name = "Mapping.OnlineOfflineDTO", classes = {
		@ConstructorResult(targetClass = OnlineOfflineDTO.class, columns = {
				@ColumnResult(name = "offline", type = Double.class),
				@ColumnResult(name = "lcoOnline", type = Double.class),
				@ColumnResult(name = "subOnline", type = Double.class) }) })
public class Walletupdatelog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String role;
	private String username;
	private String oldbalance;
	private String amount;
	private String newbalance;
	private String referenceid;// the referenceid refered by transactionid or planrechargeid
	private int rechargetype;// 1-Wallet Recharge, 2-Plan Recharge (Table refer: rechargetype)
	private int walletupdatetypeid;// 1-Credit to operator wallet, 2-Debit from operator wallet, 3-Credit to
									// Customer Wallet, 4-Debit from customer wallet (Table refer: walletupdatetype)
	private String paymentbyrole;
	private String paymentbyusername;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String creationdate;// date & time

	public Walletupdatelog() {
		super();
	}

	public Walletupdatelog(String role, String username, String oldbalance, String amount, String newbalance,
			String referenceid, int rechargetype, int walletupdatetypeid, String paymentbyrole,
			String paymentbyusername, String creationdate) {
		super();
		this.role = role;
		this.username = username;
		this.oldbalance = oldbalance;
		this.amount = amount;
		this.newbalance = newbalance;
		this.referenceid = referenceid;
		this.rechargetype = rechargetype;
		this.walletupdatetypeid = walletupdatetypeid;
		this.paymentbyrole = paymentbyrole;
		this.paymentbyusername = paymentbyusername;
		this.creationdate = creationdate;
	}

	public String getReferenceid() {
		return referenceid;
	}

	public void setReferenceid(String referenceid) {
		this.referenceid = referenceid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getOldbalance() {
		return oldbalance;
	}

	public void setOldbalance(String oldbalance) {
		this.oldbalance = oldbalance;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getNewbalance() {
		return newbalance;
	}

	public void setNewbalance(String newbalance) {
		this.newbalance = newbalance;
	}

	public int getRechargetype() {
		return rechargetype;
	}

	public void setRechargetype(int rechargetype) {
		this.rechargetype = rechargetype;
	}

	public int getWalletupdatetypeid() {
		return walletupdatetypeid;
	}

	public void setWalletupdatetypeid(int walletupdatetypeid) {
		this.walletupdatetypeid = walletupdatetypeid;
	}

	public String getPaymentbyrole() {
		return paymentbyrole;
	}

	public void setPaymentbyrole(String paymentbyrole) {
		this.paymentbyrole = paymentbyrole;
	}

	public String getPaymentbyusername() {
		return paymentbyusername;
	}

	public void setPaymentbyusername(String paymentbyusername) {
		this.paymentbyusername = paymentbyusername;
	}

	public String getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(String creationdate) {
		this.creationdate = creationdate;
	}
}
