package com.ridsys.rib.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "operator_switchport")
public class Operator_switchport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int switchid;
	private int portno;
	private int opid;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String creationdate;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String updatedate;
	private boolean isactive;
	private boolean isdelete;

	public Operator_switchport() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Operator_switchport(int id, int switchid, int portno, int opid, String creationdate, String updatedate,
			boolean isactive, boolean isdelete) {
		super();
		this.id = id;
		this.switchid = switchid;
		this.portno = portno;
		this.opid = opid;
		this.creationdate = creationdate;
		this.updatedate = updatedate;
		this.isactive = isactive;
		this.isdelete = isdelete;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSwitchid() {
		return switchid;
	}

	public void setSwitchid(int switchid) {
		this.switchid = switchid;
	}

	public int getPortno() {
		return portno;
	}

	public void setPortno(int portno) {
		this.portno = portno;
	}

	public int getOpid() {
		return opid;
	}

	public void setOpid(int opid) {
		this.opid = opid;
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

}
