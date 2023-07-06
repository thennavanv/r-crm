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
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "radippool")

@NamedNativeQuery(name = "Radippool.getIppoolList", query = "SELECT ip.id,ip.pool_name,ip.pool_type,ip.fromip,ip.toip ,if(tp.count is NULL,0,tp.count) AS total_ip ,if(up.count is NULL,0,up.count) AS used_ip,if(fp.count is NULL,0,fp.count) AS free_ip,ip.identity,ip.nasipaddress,ip.creationdate,ip.isactive FROM radippool ip LEFT JOIN (SELECT count(id) AS count,pool_name FROM radippool GROUP BY pool_name) tp ON tp.pool_name=ip.pool_name LEFT JOIN(SELECT count(id) AS count,pool_name FROM radippool WHERE username is NULL GROUP BY pool_name)fp ON fp.pool_name=ip.pool_name LEFT JOIN(SELECT count(id) AS count,pool_name FROM radippool WHERE username is NOT NULL GROUP BY pool_name)up ON up.pool_name=ip.pool_name GROUP BY pool_name", resultSetMapping = "Mapping.getIppoolListRS")
@SqlResultSetMapping(name = "Mapping.getIppoolListRS", classes = {
		@ConstructorResult(targetClass = Radippool.class, columns = { @ColumnResult(name = "id", type = Integer.class),
				@ColumnResult(name = "pool_name", type = String.class),
				@ColumnResult(name = "pool_type", type = String.class),
				@ColumnResult(name = "fromip", type = String.class), @ColumnResult(name = "toip", type = String.class),
				@ColumnResult(name = "nasipaddress", type = String.class),
				@ColumnResult(name = "identity", type = String.class),
				@ColumnResult(name = "creationdate", type = String.class),
				@ColumnResult(name = "total_ip", type = Integer.class),
				@ColumnResult(name = "free_ip", type = Integer.class),
				@ColumnResult(name = "used_ip", type = Integer.class),
				@ColumnResult(name = "isactive", type = Boolean.class) }) })

public class Radippool {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "pool_name")
	private String poolname;

	@Column(name = "pool_type")
	private String pooltype;

	private String fromip;
	private String toip;
	private String framedipaddress;
	private String nasipaddress;
	private String calledstationid;
	private String callingstationid;

	@Column(name = "pool_key")
	private String poolkey;

	@Column(name = "expiry_time")
	private String expirytime;
	private String username;
	private String identity;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String creationdate;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String updateddate;

	private Boolean isactive;

	@Transient
	private int totalcount;

	@Transient
	private int freecount;

	@Transient
	private int usedcount;

	public Radippool() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Radippool(int id, String poolname, String pooltype, String fromip, String toip, String framedipaddress,
			String nasipaddress, String calledstationid, String callingstationid,  String poolkey,
			String expirytime, String username, String identity, String creationdate, String updateddate,
			Boolean isactive) {
		super();
		this.id = id;
		this.poolname = poolname;
		this.pooltype = pooltype;
		this.fromip = fromip;
		this.toip = toip;
		this.framedipaddress = framedipaddress;
		this.nasipaddress = nasipaddress;
		this.calledstationid = calledstationid;
		this.callingstationid = callingstationid;
		this.poolkey = poolkey;
		this.expirytime = expirytime;
		this.username = username;
		this.identity = identity;
		this.creationdate = creationdate;
		this.updateddate = updateddate;
		this.isactive = isactive;
	}

	public Radippool(int id, String poolname, String pooltype, String fromip, String toip, String nasipaddress,
			String identity, String creationdate, int totalcount, int freecount, int usedcount, Boolean isactive) {
		super();
		this.id = id;
		this.poolname = poolname;
		this.pooltype = pooltype;
		this.fromip = fromip;
		this.toip = toip;
		this.nasipaddress = nasipaddress;
		this.identity = identity;
		this.creationdate = creationdate;
		this.totalcount = totalcount;
		this.freecount = freecount;
		this.usedcount = usedcount;
		this.isactive = isactive;
	}

	
	public int getTotalcount() {
		return totalcount;
	}

	public void setTotalcount(int totalcount) {
		this.totalcount = totalcount;
	}

	public int getFreecount() {
		return freecount;
	}

	public void setFreecount(int freecount) {
		this.freecount = freecount;
	}

	public int getUsedcount() {
		return usedcount;
	}

	public void setUsedcount(int usedcount) {
		this.usedcount = usedcount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPoolname() {
		return poolname;
	}

	public void setPoolname(String poolname) {
		this.poolname = poolname;
	}

	public String getPooltype() {
		return pooltype;
	}

	public void setPooltype(String pooltype) {
		this.pooltype = pooltype;
	}

	public String getFromip() {
		return fromip;
	}

	public void setFromip(String fromip) {
		this.fromip = fromip;
	}

	public String getToip() {
		return toip;
	}

	public void setToip(String toip) {
		this.toip = toip;
	}

	public String getFramedipaddress() {
		return framedipaddress;
	}

	public void setFramedipaddress(String framedipaddress) {
		this.framedipaddress = framedipaddress;
	}

	public String getNasipaddress() {
		return nasipaddress;
	}

	public void setNasipaddress(String nasipaddress) {
		this.nasipaddress = nasipaddress;
	}

	public String getExpirytime() {
		return expirytime;
	}

	public void setExpirytime(String expirytime) {
		this.expirytime = expirytime;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
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

	public Boolean getIsactive() {
		return isactive;
	}

	public void setIsactive(Boolean isactive) {
		this.isactive = isactive;
	}

	public String getCalledstationid() {
		return calledstationid;
	}

	public void setCalledstationid(String calledstationid) {
		this.calledstationid = calledstationid;
	}

	public String getCallingstationid() {
		return callingstationid;
	}

	public void setCallingstationid(String callingstationid) {
		this.callingstationid = callingstationid;
	}

	public String getPoolkey() {
		return poolkey;
	}

	public void setPoolkey(String poolkey) {
		this.poolkey = poolkey;
	}

}
