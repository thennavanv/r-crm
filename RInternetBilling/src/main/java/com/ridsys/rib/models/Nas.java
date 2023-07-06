package com.ridsys.rib.models;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.persistence.Transient;

@Entity
@Table(name = "nas")

//@NamedNativeQuery(name = "Nas.getAllNaslist", query = "SELECT *,'ridsys.com' AS subsecret,(SELECT COUNT(ra.radacctid) online FROM userinfo ui,userbillinfo ubi,radacct ra,(SELECT username,MAX(acctstarttime) acctstarttime FROM radacct WHERE acctstoptime is null GROUP BY username) rat WHERE ui.is_delete=0 AND ui.username=ubi.username AND ubi.quotaexpirydate>now() AND ra.username=rat.username AND ra.acctstarttime=rat.acctstarttime AND ra.username=ui.username AND ui.opid>0 AND ra.acctstoptime IS null AND ui.verificationstatus>0)AS online,'None' as nasstatus FROM nas", resultSetMapping = "Mapping.getAllnaslistRS")
@NamedNativeQuery(name = "Nas.getAllNaslist", query = "SELECT n.*,'ridsys.com' AS subsecret,(SELECT COUNT(ra.radacctid) online FROM userinfo ui,userbillinfo ubi,radacct ra,(SELECT username,MAX(acctstarttime) acctstarttime FROM radacct WHERE acctstoptime is null GROUP BY username) rat WHERE ui.is_delete=0 AND ui.username=ubi.username AND ubi.quotaexpirydate>now() AND ra.username=rat.username AND ra.acctstarttime=rat.acctstarttime AND ra.username=ui.username AND ui.opid>0 AND ra.acctstoptime IS null AND ui.verificationstatus>0)AS online,if(r.ustatus >0,'true','false') as nasstatus FROM nas n left join (SELECT COUNT(ra.radacctid) ustatus,ra.nasipaddress FROM userinfo ui,userbillinfo ubi,radacct ra,(SELECT username,MAX(acctstarttime) acctstarttime FROM radacct WHERE acctstoptime is null GROUP BY username) rat WHERE ui.is_delete=0 AND ui.username=ubi.username AND ubi.quotaexpirydate>now() AND ra.username=rat.username AND ra.acctstarttime=rat.acctstarttime AND ra.username=ui.username AND ui.opid>0 AND ra.acctstoptime IS null AND ui.verificationstatus>0)  r on r.nasipaddress=n.nasname", resultSetMapping = "Mapping.getAllnaslistRS")
@SqlResultSetMapping(name = "Mapping.getAllnaslistRS", classes = {
		@ConstructorResult(targetClass = Nas.class, columns = { @ColumnResult(name = "id", type = Long.class),
				@ColumnResult(name = "nasname"), @ColumnResult(name = "shortname"), @ColumnResult(name = "type"),
				@ColumnResult(name = "ports", type = String.class), @ColumnResult(name = "secret"),
				@ColumnResult(name = "server"), @ColumnResult(name = "community"), @ColumnResult(name = "description"),
				@ColumnResult(name = "COA"), @ColumnResult(name = "creationdate", type = String.class),
				@ColumnResult(name = "online", type = String.class), @ColumnResult(name = "subsecret"),
				@ColumnResult(name = "nasstatus") }) })

public class Nas {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	private String nasname;

	@NotNull
	private String shortname;
	private String type;

	@NotNull
	private String ports;

	@NotNull
	private String secret;
	private String server;
	private String community;
	private String description;

	@Column(name = "COA")
	private String coaport;

	private String updateddate;
	private String creationdate;
	private boolean isactive;
	private boolean isdelete;

	@Transient
	private String online;

	@Transient
	private String subsecret;

	@Transient
	private String nasstatus;

	public Nas() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Nas(long id, @NotNull String nasname, @NotNull String shortname, String type, @NotNull String ports,
			@NotNull String secret, String server, String community, String description, String coaport,
			String updateddate, String creationdate, boolean isactive, boolean isdelete) {
		super();
		this.id = id;
		this.nasname = nasname;
		this.shortname = shortname;
		this.type = type;
		this.ports = ports;
		this.secret = secret;
		this.server = server;
		this.community = community;
		this.description = description;
		this.coaport = coaport;
		this.updateddate = updateddate;
		this.creationdate = creationdate;
		this.isactive = isactive;
		this.isdelete = isdelete;
	}

	public Nas(long id, @NotNull String nasname, @NotNull String shortname, String type, @NotNull String ports,
			@NotNull String secret, String server, String community, String description, String coaport,
			String creationdate, String online, String subsecret, String nasstatus) {
		super();
		this.id = id;
		this.nasname = nasname;
		this.shortname = shortname;
		this.type = type;
		this.ports = ports;
		this.secret = secret;
		this.server = server;
		this.community = community;
		this.description = description;
		this.coaport = coaport;
		this.creationdate = creationdate;
		this.online = online;
		this.subsecret = subsecret;
		this.nasstatus = nasstatus;
	}

	public String getSubsecret() {
		return subsecret;
	}

	public void setSubsecret(String subsecret) {
		this.subsecret = subsecret;
	}

	public String getNasstatus() {
		return nasstatus;
	}

	public void setNasstatus(String nasstatus) {
		this.nasstatus = nasstatus;
	}

	public String getCoaport() {
		return coaport;
	}

	public void setCoaport(String coaport) {
		this.coaport = coaport;
	}

	public String getUpdateddate() {
		return updateddate;
	}

	public void setUpdateddate(String updateddate) {
		this.updateddate = updateddate;
	}

	public String getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(String creationdate) {
		this.creationdate = creationdate;
	}

	public boolean isIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	public boolean isIsdelete() {
		return isdelete;
	}

	public void setIsdelete(boolean isdelete) {
		this.isdelete = isdelete;
	}

	public String getOnline() {
		return online;
	}

	public void setOnline(String online) {
		this.online = online;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNasname() {
		return nasname;
	}

	public void setNasname(String nasname) {
		this.nasname = nasname;
	}

	public String getShortname() {
		return shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPorts() {
		return ports;
	}

	public void setPorts(String ports) {
		this.ports = ports;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public String getCommunity() {
		return community;
	}

	public void setCommunity(String community) {
		this.community = community;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
