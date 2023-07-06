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
import com.ridsys.rib.DTO.UserListDTO;

@Entity
@Table(name = "smshistory")

@NamedNativeQuery(name = "Smshistory.getSmsHistoryByAll", query = "SELECT sh.id,sh.username,sh.mobileno,sh.role,sh.content,sh.status,if(sh.messagedesc is null,' ',sh.messagedesc)AS messagedesc,if(sh.errordesc is null,' ',sh.errordesc)AS errordesc,if(messageid is null,' ',sh.messageid)AS messageid,sh.creationby,sh.creationbyusername,if(sh.role='USER',CONCAT(ui.firstname,' ',ui.lastname),CONCAT(op.firstname,' ',op.lastname)) AS fullname,if(DATE(sh.creationdate)=CURDATE(),TIME_FORMAT(SUBSTRING(sh.creationdate,12,5), '%h:%i %p'),CONCAT(DATE_FORMAT(sh.creationdate,'%d %b %Y'),' ' ,TIME_FORMAT(SUBSTRING(sh.creationdate,12,5), '%h:%i %p')))AS date,if(sh.role='OPERATOR',' ',CONCAT(op.firstname,' ',op.lastname))AS lconame FROM smshistory sh LEFT JOIN userinfo ui ON ui.username=sh.username LEFT JOIN operators op ON op.username=sh.username OR ui.opid=op.id ORDER BY sh.creationdate DESC", resultSetMapping = "Mapping.getSmsHistoryRS")
@NamedNativeQuery(name = "Smshistory.getSmsHistoryByStatusAll", query = "SELECT sh.id,sh.username,sh.mobileno,sh.role,sh.content,sh.status,if(sh.messagedesc is null,' ',sh.messagedesc)AS messagedesc,if(sh.errordesc is null,' ',sh.errordesc)AS errordesc,if(messageid is null,' ',sh.messageid)AS messageid,sh.creationby,sh.creationbyusername,if(sh.role='USER',CONCAT(ui.firstname,' ',ui.lastname),CONCAT(op.firstname,' ',op.lastname)) AS fullname,if(DATE(sh.creationdate)=CURDATE(),TIME_FORMAT(SUBSTRING(sh.creationdate,12,5), '%h:%i %p'),CONCAT(DATE_FORMAT(sh.creationdate,'%d %b %Y'),' ' ,TIME_FORMAT(SUBSTRING(sh.creationdate,12,5), '%h:%i %p')))AS date,if(sh.role='OPERATOR',' ',CONCAT(op.firstname,' ',op.lastname))AS lconame FROM smshistory sh LEFT JOIN userinfo ui ON ui.username=sh.username LEFT JOIN operators op ON op.username=sh.username OR ui.opid=op.id  WHERE sh.status=:status ORDER BY sh.creationdate DESC", resultSetMapping = "Mapping.getSmsHistoryRS")
@NamedNativeQuery(name = "Smshistory.getSmsHistoryByOpid", query = "SELECT sh.id,sh.username,sh.mobileno,sh.role,sh.content,sh.status,if(sh.messagedesc is null,' ',sh.messagedesc)AS messagedesc,if(sh.errordesc is null,' ',sh.errordesc)AS errordesc,if(messageid is null,' ',sh.messageid)AS messageid,sh.creationby,sh.creationbyusername,if(sh.role='USER',CONCAT(ui.firstname,' ',ui.lastname),CONCAT(op.firstname,' ',op.lastname)) AS fullname,if(DATE(sh.creationdate)=CURDATE(),TIME_FORMAT(SUBSTRING(sh.creationdate,12,5), '%h:%i %p'),CONCAT(DATE_FORMAT(sh.creationdate,'%d %b %Y'),' ' ,TIME_FORMAT(SUBSTRING(sh.creationdate,12,5), '%h:%i %p')))AS date,if(sh.role='OPERATOR',' ',CONCAT(op.firstname,' ',op.lastname))AS lconame FROM smshistory sh LEFT JOIN userinfo ui ON ui.username=sh.username LEFT JOIN operators op ON op.username=sh.username  OR ui.opid=op.id  WHERE sh.creationbyusername=:username or sh.username=:username  ORDER BY sh.creationdate DESC", resultSetMapping = "Mapping.getSmsHistoryRS")
@NamedNativeQuery(name = "Smshistory.getSmsHistoryByOpidAndStatus", query = "SELECT sh.id,sh.username,sh.mobileno,sh.role,sh.content,sh.status,if(sh.messagedesc is null,' ',sh.messagedesc)AS messagedesc,if(sh.errordesc is null,' ',sh.errordesc)AS errordesc,if(messageid is null,' ',sh.messageid)AS messageid,sh.creationby,sh.creationbyusername,if(sh.role='USER',CONCAT(ui.firstname,' ',ui.lastname),CONCAT(op.firstname,' ',op.lastname)) AS fullname,if(DATE(sh.creationdate)=CURDATE(),TIME_FORMAT(SUBSTRING(sh.creationdate,12,5), '%h:%i %p'),CONCAT(DATE_FORMAT(sh.creationdate,'%d %b %Y'),' ' ,TIME_FORMAT(SUBSTRING(sh.creationdate,12,5), '%h:%i %p')))AS date,if(sh.role='OPERATOR',' ',CONCAT(op.firstname,' ',op.lastname))AS lconame FROM smshistory sh LEFT JOIN userinfo ui ON ui.username=sh.username LEFT JOIN operators op ON op.username=sh.username  OR ui.opid=op.id  WHERE sh.creationbyusername=:username or sh.username=:username AND sh.status=:status ORDER BY sh.creationdate DESC", resultSetMapping = "Mapping.getSmsHistoryRS")
@NamedNativeQuery(name = "Smshistory.getSMSbyMobile", query = "SELECT sh.id,sh.username,sh.mobileno,sh.role,sh.content,sh.status,if(sh.messagedesc is null,' ',sh.messagedesc)AS messagedesc,if(sh.errordesc is null,' ',sh.errordesc)AS errordesc,if(messageid is null,' ',sh.messageid)AS messageid,sh.creationby,sh.creationbyusername,if(sh.role='USER',CONCAT(ui.firstname,' ',ui.lastname),CONCAT(op.firstname,' ',op.lastname)) AS fullname,if(DATE(sh.creationdate)=CURDATE(),TIME_FORMAT(SUBSTRING(sh.creationdate,12,5), '%h:%i %p'),CONCAT(DATE_FORMAT(sh.creationdate,'%d %b %Y'),' ' ,TIME_FORMAT(SUBSTRING(sh.creationdate,12,5), '%h:%i %p')))AS date,if(sh.role='OPERATOR',' ',CONCAT(op.firstname,' ',op.lastname))AS lconame FROM smshistory sh LEFT JOIN userinfo ui ON ui.username=sh.username LEFT JOIN operators op ON op.username=sh.username  OR ui.opid=op.id  WHERE sh.mobileno=:mobileno ORDER BY sh.creationdate DESC", resultSetMapping = "Mapping.getSmsHistoryRS")
@SqlResultSetMapping(name = "Mapping.getSmsHistoryRS", classes = {
		@ConstructorResult(targetClass = Smshistory.class, columns = { @ColumnResult(name = "id", type = Integer.class),
				@ColumnResult(name = "username", type = String.class), @ColumnResult(name = "mobileno"),
				@ColumnResult(name = "role"), @ColumnResult(name = "content", type = String.class),
				@ColumnResult(name = "status"), @ColumnResult(name = "messagedesc", type = String.class),
				@ColumnResult(name = "errordesc"), @ColumnResult(name = "messageid"),
				@ColumnResult(name = "creationby", type = String.class),
				@ColumnResult(name = "creationbyusername", type = String.class),
				@ColumnResult(name = "fullname", type = String.class),
				@ColumnResult(name = "date", type = String.class),
				@ColumnResult(name = "lconame", type = String.class) }) })

public class Smshistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String username;
	private String role;
	private String content;
	private String status;
	private String creationby;
	private String creationbyusername;
	private String messagedesc;
	private String errordesc;
	private String messageid;
	private String mobileno;
	private boolean isactive;

	private String creationdate;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String updateddate;

	@Transient
	private String fullname;

	@Transient
	private String lconame;

	public Smshistory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Smshistory(int id, String username, String role, String content, String status, String creationby,
			String creationbyusername, String messagedesc, String errordesc, String messageid, String mobileno,
			boolean isactive, String creationdate, String updateddate) {
		super();
		this.id = id;
		this.username = username;
		this.role = role;
		this.content = content;
		this.status = status;
		this.creationby = creationby;
		this.creationbyusername = creationbyusername;
		this.messagedesc = messagedesc;
		this.errordesc = errordesc;
		this.messageid = messageid;
		this.mobileno = mobileno;
		this.isactive = isactive;
		this.creationdate = creationdate;
		this.updateddate = updateddate;
	}

	public Smshistory(int id, String username, String mobileno, String role, String content, String status,
			String messagedesc, String errordesc, String messageid, String creationby, String creationbyusername,
			String fullname, String date, String lconame) {
		super();
		this.id = id;
		this.username = username;
		this.role = role;
		this.content = content;
		this.status = status;
		this.creationby = creationby;
		this.creationbyusername = creationbyusername;
		this.messagedesc = messagedesc;
		this.errordesc = errordesc;
		this.messageid = messageid;
		this.mobileno = mobileno;
		this.creationdate = date;
		this.fullname = fullname;
		this.lconame = lconame;
	}

	public String getLconame() {
		return lconame;
	}

	public void setLconame(String lconame) {
		this.lconame = lconame;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public String getMessagedesc() {
		return messagedesc;
	}

	public void setMessagedesc(String messagedesc) {
		this.messagedesc = messagedesc;
	}

	public String getErrordesc() {
		return errordesc;
	}

	public void setErrordesc(String errordesc) {
		this.errordesc = errordesc;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public boolean isIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	public String getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(String creationdate) {
		this.creationdate = creationdate;
	}

	public String getMessageid() {
		return messageid;
	}

	public void setMessageid(String messageid) {
		this.messageid = messageid;
	}

	public String getUpdateddate() {
		return updateddate;
	}

	public void setUpdateddate(String updateddate) {
		this.updateddate = updateddate;
	}

}
