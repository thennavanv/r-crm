package com.ridsys.rib.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.ConstructorResult;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.ColumnResult;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@NamedNativeQuery(name = "Quotaplancancelhistory.getReportByOpernameAndftDate", query = "select pc.id,pc.planid,pc.planactivationdate,pc.creationdate,pc.refundcost,pc.refunduser,pc.creationby,pc.creationbyusername,pc.description,CONCAT(ui.firstname,' ',ui.lastname) as name,ui.username,o.username as opusername,CONCAT(o.firstname,' ',o.lastname) as operatorname,ubi.planName from quotaplancancelhistory as pc,operators as o,userbillinfo as ubi,userinfo as ui where o.id=ui.opid and ui.username=pc.username and pc.planid=ubi.id and o.username=:username and DATE(pc.creationdate)>=:fdate and DATE(pc.creationdate)<=:tdate", resultSetMapping = "Mapping.QuotaplancancelhistoryRS")
@NamedNativeQuery(name = "Quotaplancancelhistory.getReportByAdminAndftDate", query = "select pc.id,pc.planid,pc.planactivationdate,pc.creationdate,pc.refundcost,pc.refunduser,pc.creationby,pc.creationbyusername,pc.description,CONCAT(ui.firstname,' ',ui.lastname) as name,ui.username,o.username as opusername,CONCAT(o.firstname,' ',o.lastname) as operatorname,ubi.planName from quotaplancancelhistory as pc,operators as o,userbillinfo as ubi,userinfo as ui where o.id=ui.opid and ui.username=pc.username and pc.planid=ubi.id and DATE(pc.creationdate)>=:fdate and DATE(pc.creationdate)<=:tdate", resultSetMapping = "Mapping.QuotaplancancelhistoryRS")
@NamedNativeQuery(name = "Quotaplancancelhistory.getReportByUsernameAndftDate", query = "select pc.id,pc.planid,pc.planactivationdate,pc.creationdate,pc.refundcost,pc.refunduser,pc.creationby,pc.creationbyusername,pc.description,CONCAT(ui.firstname,' ',ui.lastname) as name,ui.username,o.username as opusername,CONCAT(o.firstname,' ',o.lastname) as operatorname,ubi.planName from quotaplancancelhistory as pc,operators as o,userbillinfo as ubi,userinfo as ui where o.id=ui.opid and ui.username=pc.username and pc.planid=ubi.id and pc.username=:username and DATE(pc.creationdate)>=:fdate and DATE(pc.creationdate)<=:tdate", resultSetMapping = "Mapping.QuotaplancancelhistoryRS")
@NamedNativeQuery(name = "Quotaplancancelhistory.getReportByOpername", query = "select pc.id,pc.planid,pc.planactivationdate,pc.creationdate,pc.refundcost,pc.refunduser,pc.creationby,pc.creationbyusername,pc.description,CONCAT(ui.firstname,' ',ui.lastname) as name,ui.username,o.username as opusername,CONCAT(o.firstname,' ',o.lastname) as operatorname,ubi.planName from quotaplancancelhistory as pc,operators as o,userbillinfo as ubi,userinfo as ui where o.id=ui.opid and ui.username=pc.username and pc.planid=ubi.id and o.username=:username", resultSetMapping = "Mapping.QuotaplancancelhistoryRS")
@NamedNativeQuery(name = "Quotaplancancelhistory.getReportByAdmin", query = "select pc.id,pc.planid,pc.planactivationdate,pc.creationdate,pc.refundcost,pc.refunduser,pc.creationby,pc.creationbyusername,pc.description,CONCAT(ui.firstname,' ',ui.lastname) as name,ui.username,o.username as opusername,CONCAT(o.firstname,' ',o.lastname) as operatorname,ubi.planName from quotaplancancelhistory as pc,operators as o,userbillinfo as ubi,userinfo as ui where o.id=ui.opid and ui.username=pc.username and pc.planid=ubi.id", resultSetMapping = "Mapping.QuotaplancancelhistoryRS")
@NamedNativeQuery(name = "Quotaplancancelhistory.getReportByUsername", query = "select pc.id,pc.planid,pc.planactivationdate,pc.creationdate,pc.refundcost,pc.refunduser,pc.creationby,pc.creationbyusername,pc.description,CONCAT(ui.firstname,' ',ui.lastname) as name,ui.username,o.username as opusername,CONCAT(o.firstname,' ',o.lastname) as operatorname,ubi.planName from quotaplancancelhistory as pc,operators as o,userbillinfo as ubi,userinfo as ui where o.id=ui.opid and ui.username=pc.username and pc.planid=ubi.id and pc.username=:username", resultSetMapping = "Mapping.QuotaplancancelhistoryRS")
@SqlResultSetMapping(name = "Mapping.QuotaplancancelhistoryRS", classes = {
		@ConstructorResult(targetClass = Quotaplancancelhistory.class, columns = {
				@ColumnResult(name = "id", type = int.class), @ColumnResult(name = "username", type = String.class),
				@ColumnResult(name = "planid", type = String.class),
				@ColumnResult(name = "refundcost", type = String.class),
				@ColumnResult(name = "refunduser", type = String.class),
				@ColumnResult(name = "creationby", type = String.class),
				@ColumnResult(name = "creationbyusername", type = String.class),
				@ColumnResult(name = "description", type = String.class),
				@ColumnResult(name = "creationdate", type = String.class),
				@ColumnResult(name = "planactivationdate", type = String.class),
				@ColumnResult(name = "name", type = String.class),
				@ColumnResult(name = "opusername", type = String.class),
				@ColumnResult(name = "planName", type = String.class),
				@ColumnResult(name = "operatorname", type = String.class) }) })

@Entity
@Table(name = "quotaplancancelhistory")
@Data
@NoArgsConstructor
public class Quotaplancancelhistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String rechargeid;
	private String username;
	private String planid;
	private String refundcost;
	private String refunduser;
	private String creationby;
	private String creationbyusername;
	private String description;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String creationdate;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String planactivationdate;

	@Transient
	private String name;

	@Transient
	private String opusername;

	@Transient
	private String planname;

	@Transient
	private String operatorname;

	public Quotaplancancelhistory(String rechargeid, String username, String planid, String refundcost,
			String refunduser, String creationby, String creationbyusername, String description, String creationdate,
			String planactivationdate) {
		super();
		this.rechargeid = rechargeid;
		this.username = username;
		this.planid = planid;
		this.refundcost = refundcost;
		this.refunduser = refunduser;
		this.creationby = creationby;
		this.creationbyusername = creationbyusername;
		this.description = description;
		this.creationdate = creationdate;
		this.planactivationdate = planactivationdate;
	}

	public Quotaplancancelhistory(int id, String username, String planid, String refundcost, String refunduser,
			String creationby, String creationbyusername, String description, String creationdate,
			String planactivationdate, String name, String opusername, String planname, String operatorname) {
		super();
		this.id = id;
		this.username = username;
		this.planid = planid;
		this.refundcost = refundcost;
		this.refunduser = refunduser;
		this.creationby = creationby;
		this.creationbyusername = creationbyusername;
		this.description = description;
		this.creationdate = creationdate;
		this.planactivationdate = planactivationdate;
		this.name = name;
		this.opusername = opusername;
		this.planname = planname;
		this.operatorname = operatorname;
	}

}
