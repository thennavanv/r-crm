package com.ridsys.rib.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "operatorplanpermission")
public class Operatorplanpermission {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private boolean isactive;
	private int planid;
	private int opid;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String creationdate;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String updateddate;

	public Operatorplanpermission() {
		super();
	}

	public Operatorplanpermission(int id, boolean isactive, int planid, int opid, String creationdate,
			String updateddate) {
		super();
		this.id = id;
		this.isactive = isactive;
		this.planid = planid;
		this.opid = opid;
		this.creationdate = creationdate;
		this.updateddate = updateddate;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	public int getPlanid() {
		return planid;
	}

	public void setPlanid(int planid) {
		this.planid = planid;
	}

	public int getOpid() {
		return opid;
	}

	public void setOpid(int opid) {
		this.opid = opid;
	}

}
