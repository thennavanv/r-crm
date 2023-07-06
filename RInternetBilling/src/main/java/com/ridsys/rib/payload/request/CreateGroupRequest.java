package com.ridsys.rib.payload.request;

import javax.validation.constraints.NotBlank;

public class CreateGroupRequest {
	@NotBlank
	private String table;

	private String groupname;

	@NotBlank
	private String attribute;

	@NotBlank
	private String op;

	@NotBlank
	private String value;

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
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

}
