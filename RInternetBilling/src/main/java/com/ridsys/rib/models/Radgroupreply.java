package com.ridsys.rib.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "radgroupreply")
public class Radgroupreply {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String groupname;
	private String attribute;
	private String op;
	private String value;

	public Radgroupreply() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Radgroupreply(String groupname, String attribute, String op, String value) {
		super();
		this.groupname = groupname;
		this.attribute = attribute;
		this.op = op;
		this.value = value;
	}

}
