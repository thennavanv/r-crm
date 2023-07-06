package com.ridsys.rib.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Table(name = "operator_change_history")
public class Operator_change_history {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int oldopid;
	private int newopid;
	private String username;
	private String remarks;
	private String creationby;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String creationdate;

	public Operator_change_history() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Operator_change_history(int id, int oldopid, int newopid, String username, String remarks, String creationby,
			String creationdate) {
		super();
		this.id = id;
		this.oldopid = oldopid;
		this.newopid = newopid;
		this.username = username;
		this.remarks = remarks;
		this.creationby = creationby;
		this.creationdate = creationdate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOldopid() {
		return oldopid;
	}

	public void setOldopid(int oldopid) {
		this.oldopid = oldopid;
	}

	public int getNewopid() {
		return newopid;
	}

	public void setNewopid(int newopid) {
		this.newopid = newopid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getCreationby() {
		return creationby;
	}

	public void setCreationby(String creationby) {
		this.creationby = creationby;
	}

	public String getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(String creationdate) {
		this.creationdate = creationdate;
	}

}
