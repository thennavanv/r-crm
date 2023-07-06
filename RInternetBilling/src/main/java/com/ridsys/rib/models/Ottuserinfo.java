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
import com.ridsys.rib.DTO.OttuserDTO;

@Entity
@Table(name = "ottuserinfo")

@NamedNativeQuery(name = "Ottuserinfo.getUserDetails", query = "SELECT ui.id,oui.profilepic,concat(ui.firstname,' ' ,ui.lastname)AS username,ui.mobilephone,ui.email FROM userinfo ui ,ottuserinfo oui WHERE oui.username=ui.username AND ui.id=:userid", resultSetMapping = "Mapping.ottuserDTO")
@SqlResultSetMapping(name = "Mapping.ottuserDTO", classes = {
		@ConstructorResult(targetClass = OttuserDTO.class, columns = { @ColumnResult(name = "id"),
				@ColumnResult(name = "profilepic"), @ColumnResult(name = "username"),
				@ColumnResult(name = "mobilephone"), @ColumnResult(name = "email") }) })

public class Ottuserinfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int packageid;
	private String username;
	private String email;
	private String mobilephone;
	private String androidid;
	private boolean isactive;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String planstartdate;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String planexpirydate;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String creationdate;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String updatedate;

	public Ottuserinfo() {
		super();
	}

	public Ottuserinfo(int id, int packageid, String username, String email, String mobilephone, String androidid,
			boolean isactive, String planstartdate, String planexpirydate, String creationdate, String updatedate) {
		super();
		this.id = id;
		this.packageid = packageid;
		this.username = username;
		this.email = email;
		this.mobilephone = mobilephone;
		this.androidid = androidid;
		this.isactive = isactive;
		this.planstartdate = planstartdate;
		this.planexpirydate = planexpirydate;
		this.creationdate = creationdate;
		this.updatedate = updatedate;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobilephone() {
		return mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	public String getAndroidid() {
		return androidid;
	}

	public void setAndroidid(String androidid) {
		this.androidid = androidid;
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

	public String getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}

	public int getPackageid() {
		return packageid;
	}

	public void setPackageid(int packageid) {
		this.packageid = packageid;
	}

	public String getPlanstartdate() {
		return planstartdate;
	}

	public void setPlanstartdate(String planstartdate) {
		this.planstartdate = planstartdate;
	}

	public String getPlanexpirydate() {
		return planexpirydate;
	}

	public void setPlanexpirydate(String planexpirydate) {
		this.planexpirydate = planexpirydate;
	}

}
