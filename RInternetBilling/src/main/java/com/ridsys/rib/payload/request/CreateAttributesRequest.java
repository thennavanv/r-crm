package com.ridsys.rib.payload.request;

import javax.validation.constraints.NotBlank;

public class CreateAttributesRequest {
	@NotBlank
	private String table;

	private String username;

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
