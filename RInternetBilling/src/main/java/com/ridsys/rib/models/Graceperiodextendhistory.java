package com.ridsys.rib.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.ConstructorResult;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.ColumnResult;

import org.hibernate.annotations.NamedNativeQuery;

import com.fasterxml.jackson.annotation.JsonFormat;

@NamedNativeQuery(name = "Graceperiodextendhistory.getReportByOpernameAndftDate", query = "select gp.id,gp.days,gp.creationby,gp.updateddate,gp.oldexpirydate,gp.newexpirydate,gp.creationdate,gp.creationbyusername,CONCAT(ui.firstname,' ',ui.lastname) as name,ui.username,o.username as opusername,CONCAT(o.firstname,' ',o.lastname) as operatorname,ubi.planName from graceperiodextendhistory as gp,operators as o,userbillinfo as ubi,userinfo as ui where o.id=ui.opid and ui.username=gp.username and gp.planid=ubi.id and o.username=:username and DATE(gp.creationdate)>=:fdate and DATE(gp.creationdate)<=:tdate", resultSetMapping = "Mapping.getgraceplanextendhistoryRS")
@NamedNativeQuery(name = "Graceperiodextendhistory.getReportByAdminAndftDate", query = "select gp.id,gp.days,gp.creationby,gp.updateddate,gp.oldexpirydate,gp.newexpirydate,gp.creationdate,gp.creationbyusername,CONCAT(ui.firstname,' ',ui.lastname) as name,ui.username,o.username as opusername,CONCAT(o.firstname,' ',o.lastname) as operatorname,ubi.planName from graceperiodextendhistory as gp,operators as o,userbillinfo as ubi,userinfo as ui where o.id=ui.opid and ui.username=gp.username and gp.planid=ubi.id and DATE(gp.creationdate)>=:fdate and DATE(gp.creationdate)<=:tdate", resultSetMapping = "Mapping.getgraceplanextendhistoryRS")
@NamedNativeQuery(name = "Graceperiodextendhistory.getReportByUsernameAndftDate", query = "select gp.id,gp.days,gp.creationby,gp.updateddate,gp.oldexpirydate,gp.newexpirydate,gp.creationdate,gp.creationbyusername,CONCAT(ui.firstname,' ',ui.lastname) as name,ui.username,o.username as opusername,CONCAT(o.firstname,' ',o.lastname) as operatorname,ubi.planName from graceperiodextendhistory as gp,operators as o,userbillinfo as ubi,userinfo as ui where o.id=ui.opid and ui.username=gp.username and gp.planid=ubi.id and gp.username=:username and DATE(gp.creationdate)>=:fdate and DATE(gp.creationdate)<=:tdate", resultSetMapping = "Mapping.getgraceplanextendhistoryRS")
@NamedNativeQuery(name = "Graceperiodextendhistory.getReportByOpername", query = "select gp.id,gp.days,gp.creationby,gp.updateddate,gp.oldexpirydate,gp.newexpirydate,gp.creationdate,gp.creationbyusername,CONCAT(ui.firstname,' ',ui.lastname) as name,ui.username,o.username as opusername,CONCAT(o.firstname,' ',o.lastname) as operatorname,ubi.planName from graceperiodextendhistory as gp,operators as o,userbillinfo as ubi,userinfo as ui where o.id=ui.opid and ui.username=gp.username and gp.planid=ubi.id and o.username=:username", resultSetMapping = "Mapping.getgraceplanextendhistoryRS")
@NamedNativeQuery(name = "Graceperiodextendhistory.getReportByAdmin", query = "select gp.id,gp.days,gp.creationby,gp.updateddate,gp.oldexpirydate,gp.newexpirydate,gp.creationdate,gp.creationbyusername,CONCAT(ui.firstname,' ',ui.lastname) as name,ui.username,o.username as opusername,CONCAT(o.firstname,' ',o.lastname) as operatorname,ubi.planName from graceperiodextendhistory as gp,operators as o,userbillinfo as ubi,userinfo as ui where o.id=ui.opid and ui.username=gp.username and gp.planid=ubi.id", resultSetMapping = "Mapping.getgraceplanextendhistoryRS")
@NamedNativeQuery(name = "Graceperiodextendhistory.getReportByUsername", query = "select gp.id,gp.days,gp.creationby,gp.updateddate,gp.oldexpirydate,gp.newexpirydate,gp.creationdate,gp.creationbyusername,CONCAT(ui.firstname,' ',ui.lastname) as name,ui.username,o.username as opusername,CONCAT(o.firstname,' ',o.lastname) as operatorname,ubi.planName from graceperiodextendhistory as gp,operators as o,userbillinfo as ubi,userinfo as ui where o.id=ui.opid and ui.username=gp.username and gp.planid=ubi.id and gp.username=:username", resultSetMapping = "Mapping.getgraceplanextendhistoryRS")
@SqlResultSetMapping(name = "Mapping.getgraceplanextendhistoryRS", classes = {
		@ConstructorResult(targetClass = Graceperiodextendhistory.class, columns = {
				@ColumnResult(name = "id", type = int.class), @ColumnResult(name = "username", type = String.class),
				@ColumnResult(name = "days", type = String.class),
				@ColumnResult(name = "creationby", type = String.class),
				@ColumnResult(name = "creationbyusername", type = String.class),
				@ColumnResult(name = "oldexpirydate", type = String.class),
				@ColumnResult(name = "newexpirydate", type = String.class),
				@ColumnResult(name = "creationdate", type = String.class),
				@ColumnResult(name = "updateddate", type = String.class),
				@ColumnResult(name = "name", type = String.class),
				@ColumnResult(name = "opusername", type = String.class),
				@ColumnResult(name = "planName", type = String.class),
				@ColumnResult(name = "operatorname", type = String.class) }) })
@Entity
@Table(name = "graceperiodextendhistory")
public class Graceperiodextendhistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String username;
	private String days;
	private String creationby;
	private String creationbyusername;
	private String planid;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String oldexpirydate;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String newexpirydate;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String creationdate;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String updateddate;

	@Transient
	private String name;

	@Transient
	private String opusername;

	@Transient
	private String planName;

	@Transient
	private String operatorname;

	public Graceperiodextendhistory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Graceperiodextendhistory(int id, String username, String days, String creationby, String creationbyusername,
			String oldexpirydate, String newexpirydate, String creationdate, String updateddate) {
		super();
		this.id = id;
		this.username = username;
		this.days = days;
		this.creationby = creationby;
		this.creationbyusername = creationbyusername;
		this.oldexpirydate = oldexpirydate;
		this.newexpirydate = newexpirydate;
		this.creationdate = creationdate;
		this.updateddate = updateddate;
	}

	public Graceperiodextendhistory(int id, String username, String days, String creationby, String creationbyusername,
			String oldexpirydate, String newexpirydate, String creationdate, String updateddate, String name,
			String opusername, String planName, String operatorname) {
		super();
		this.id = id;
		this.username = username;
		this.days = days;
		this.creationby = creationby;
		this.creationbyusername = creationbyusername;
		this.oldexpirydate = oldexpirydate;
		this.newexpirydate = newexpirydate;
		this.creationdate = creationdate;
		this.updateddate = updateddate;
		this.name = name;
		this.opusername = opusername;
		this.planName = planName;
		this.operatorname = operatorname;
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

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
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

	public String getOldexpirydate() {
		return oldexpirydate;
	}

	public void setOldexpirydate(String oldexpirydate) {
		this.oldexpirydate = oldexpirydate;
	}

	public String getNewexpirydate() {
		return newexpirydate;
	}

	public void setNewexpirydate(String newexpirydate) {
		this.newexpirydate = newexpirydate;
	}

	public String getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(String creationdate) {
		this.creationdate = creationdate;
	}

	public String getUpdateddate() {
		return updateddate;
	}

	public void setUpdateddate(String updateddate) {
		this.updateddate = updateddate;
	}

	public String getPlanid() {
		return planid;
	}

	public void setPlanid(String planid) {
		this.planid = planid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOpusername() {
		return opusername;
	}

	public void setOpusername(String opusername) {
		this.opusername = opusername;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public String getOperatorname() {
		return operatorname;
	}

	public void setOperatorname(String operatorname) {
		this.operatorname = operatorname;
	}

}
