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
import com.ridsys.rib.DTO.SpinListDTO;

@Entity
@Table(name = "ottplanmanagelog")

@NamedNativeQuery(name = "Ottplanmanagelog.getPlanDetailByUser", query = "SELECT planCost,plandays,packageid FROM ottplanmanagelog WHERE username=:username ORDER BY id DESC LIMIT 1", resultSetMapping = "Mapping.UserplanDetails")
@SqlResultSetMapping(name = "Mapping.UserplanDetails", classes = {
		@ConstructorResult(targetClass = SpinListDTO.class, columns = {
				@ColumnResult(name = "packageid", type = Integer.class), @ColumnResult(name = "planCost"),
				@ColumnResult(name = "plandays") }) })

public class Ottplanmanagelog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int packageid;
	private String plandays;
	private String rechargeid;
	private String plancost;
	private String username;
	private String creationby;
	private String creationbyusername;
	private String description;
	private double lcocom;
	private double msocom;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String creationdate;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String expirydate;

	public Ottplanmanagelog() {
		super();

	}

	public Ottplanmanagelog(int id, int packageid, String plandays, String rechargeid, String plancost, String username,
			String creationby, String creationbyusername, String description, double lcocom, double msocom,
			String creationdate, String expirydate) {
		super();
		this.id = id;
		this.packageid = packageid;
		this.plandays = plandays;
		this.rechargeid = rechargeid;
		this.plancost = plancost;
		this.username = username;
		this.creationby = creationby;
		this.creationbyusername = creationbyusername;
		this.description = description;
		this.lcocom = lcocom;
		this.msocom = msocom;
		this.creationdate = creationdate;
		this.expirydate = expirydate;
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

	public int getPackageid() {
		return packageid;
	}

	public void setPackageid(int packageid) {
		this.packageid = packageid;
	}

	public String getPlandays() {
		return plandays;
	}

	public void setPlandays(String plandays) {
		this.plandays = plandays;
	}

	public String getPlancost() {
		return plancost;
	}

	public void setPlancost(String plancost) {
		this.plancost = plancost;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(String creationdate) {
		this.creationdate = creationdate;
	}

	public String getExpirydate() {
		return expirydate;
	}

	public void setExpirydate(String expirydate) {
		this.expirydate = expirydate;
	}

	public double getLcocom() {
		return lcocom;
	}

	public void setLcocom(double lcocom) {
		this.lcocom = lcocom;
	}

	public double getMsocom() {
		return msocom;
	}

	public void setMsocom(double msocom) {
		this.msocom = msocom;
	}

}
