package com.ridsys.rib.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "operator_vlan")
public class Operatorvlan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int vlanid;
	private int opid;
	private boolean isactive;
	private String area;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String creationdate;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String updatedate;

	public Operatorvlan() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Operatorvlan(int id, int vlanid, int opid, boolean isactive, String area, String creationdate,
			String updatedate) {
		super();
		this.id = id;
		this.vlanid = vlanid;
		this.opid = opid;
		this.isactive = isactive;
		this.area = area;
		this.creationdate = creationdate;
		this.updatedate = updatedate;
	}

	public String getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getVlanid() {
		return vlanid;
	}

	public void setVlanid(int vlanid) {
		this.vlanid = vlanid;
	}

	public int getOpid() {
		return opid;
	}

	public void setOpid(int opid) {
		this.opid = opid;
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

}
