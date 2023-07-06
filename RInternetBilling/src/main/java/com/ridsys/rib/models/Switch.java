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

@Entity
@Table(name = "switch")

																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																															@NamedNativeQuery(name = "Switch.getSwitchlist", query = "SELECT s.*,p.providername,b.branchname,n.nasname,ops.opcount,ops.oplist,ops.portcount,(SELECT COUNT(u.id) FROM userinfo u,userbillinfo ubi WHERE u.username=ubi.username AND ubi.quotaexpirydate>now() AND FIND_IN_SET(u.opid,ops.oplist)) AS activecus,(SELECT COUNT(ra.radacctid) online FROM userinfo ui,userbillinfo ubi,radacct ra,(SELECT username,MAX(acctstarttime) acctstarttime FROM radacct WHERE acctstoptime is null GROUP BY username) rat WHERE ui.is_delete=0 AND ui.username=ubi.username AND ubi.quotaexpirydate>now() AND ra.username=rat.username AND ra.acctstarttime=rat.acctstarttime AND ra.username=ui.username AND ui.opid>0 AND ra.acctstoptime IS null AND ui.verificationstatus>0 AND FIND_IN_SET(ui.opid,ops.oplist)) AS onlinecus FROM switch s,providerinfo p,nas n,branchinfo b LEFT JOIN (SELECT COUNT(id) as opcount,switchid,group_concat(opid) as oplist,16-COUNT(DISTINCT portno) AS portcount FROM operator_switchport GROUP BY switchid) ops ON ops.switchid=id WHERE b.id=s.branchid AND n.id=s.nasid AND p.id=s.providerid", resultSetMapping = "Mapping.getSwitchlistRS")
@SqlResultSetMapping(name = "Mapping.getSwitchlistRS", classes = {
		@ConstructorResult(targetClass = Switch.class, columns = { @ColumnResult(name = "id", type = Integer.class),
				@ColumnResult(name = "switchname"), @ColumnResult(name = "switchip", type = String.class),
				@ColumnResult(name = "switchport", type = String.class),
				@ColumnResult(name = "creationdate", type = String.class),
				@ColumnResult(name = "isactive", type = Boolean.class), @ColumnResult(name = "providername"),
				@ColumnResult(name = "branchname"), @ColumnResult(name = "nasname"),
				@ColumnResult(name = "opcount", type = Integer.class),
				@ColumnResult(name = "portcount", type = Integer.class),
				@ColumnResult(name = "activecus", type = Integer.class),
				@ColumnResult(name = "onlinecus", type = Integer.class) }) })

public class Switch {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String switchname;
	private String switchip;
	private String switchport;
	private int nasid;
	private int branchid;
	private int providerid;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String creationdate;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String updatedate;
	private boolean isactive;
	private boolean isdelete;

	@Transient
	private String providername;

	@Transient
	private String branchname;

	@Transient
	private String nasname;

	@Transient
	private int opcount;

	@Transient
	private int portcount;

	@Transient
	private int activecus;

	@Transient
	private int onlinecus;

	public Switch() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Switch(int id, String switchname, String switchip, String switchport, int nasid, int branchid,
			int providerid, String creationdate, String updatedate, boolean isactive, boolean isdelete) {
		super();
		this.id = id;
		this.switchname = switchname;
		this.switchip = switchip;
		this.switchport = switchport;
		this.nasid = nasid;
		this.branchid = branchid;
		this.providerid = providerid;
		this.creationdate = creationdate;
		this.updatedate = updatedate;
		this.isactive = isactive;
		this.isdelete = isdelete;
	}

	public Switch(int id, String switchname, String switchip, String switchport, String creationdate, boolean isactive,
			String providername, String branchname, String nasname, int opcount, int portcount, int activecus,
			int onlinecus) {
		super();
		this.id = id;
		this.switchname = switchname;
		this.switchip = switchip;
		this.switchport = switchport;
		this.creationdate = creationdate;
		this.isactive = isactive;
		this.providername = providername;
		this.branchname = branchname;
		this.nasname = nasname;
		this.opcount = opcount;
		this.portcount = portcount;
		this.activecus = activecus;
		this.onlinecus = onlinecus;
	}

	public String getProvidername() {
		return providername;
	}

	public void setProvidername(String providername) {
		this.providername = providername;
	}

	public String getBranchname() {
		return branchname;
	}

	public void setBranchname(String branchname) {
		this.branchname = branchname;
	}

	public String getNasname() {
		return nasname;
	}

	public void setNasname(String nasname) {
		this.nasname = nasname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSwitchname() {
		return switchname;
	}

	public void setSwitchname(String switchname) {
		this.switchname = switchname;
	}

	public String getSwitchip() {
		return switchip;
	}

	public void setSwitchip(String switchip) {
		this.switchip = switchip;
	}

	public String getSwitchport() {
		return switchport;
	}

	public void setSwitchport(String switchport) {
		this.switchport = switchport;
	}

	public int getNasid() {
		return nasid;
	}

	public void setNasid(int nasid) {
		this.nasid = nasid;
	}

	public int getBranchid() {
		return branchid;
	}

	public void setBranchid(int branchid) {
		this.branchid = branchid;
	}

	public int getProviderid() {
		return providerid;
	}

	public void setProviderid(int providerid) {
		this.providerid = providerid;
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

	public int getOpcount() {
		return opcount;
	}

	public void setOpcount(int opcount) {
		this.opcount = opcount;
	}

	public int getPortcount() {
		return portcount;
	}

	public void setPortcount(int portcount) {
		this.portcount = portcount;
	}

	public int getActivecus() {
		return activecus;
	}

	public void setActivecus(int activecus) {
		this.activecus = activecus;
	}

	public int getOnlinecus() {
		return onlinecus;
	}

	public void setOnlinecus(int onlinecus) {
		this.onlinecus = onlinecus;
	}

}
