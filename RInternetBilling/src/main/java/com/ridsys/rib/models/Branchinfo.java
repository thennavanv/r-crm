package com.ridsys.rib.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "branchinfo")
public class Branchinfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String branchname;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String creationdate;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String updatedate;
	private boolean isactive;
	private boolean isdelete;

	public Branchinfo() {
		super();

	}

	public Branchinfo(int id, String branchname, String creationdate, String updatedate, boolean isactive,
			boolean isdelete) {
		super();
		this.id = id;
		this.branchname = branchname;
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

	public String getBranchname() {
		return branchname;
	}

	public void setBranchname(String branchname) {
		this.branchname = branchname;
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
