package com.ridsys.rib.comm;

public class General {
	private String customer;
	private String value;

	public General(String customer, String value) {
		super();
		this.customer = customer;
		this.value = Bandwidth.calBWDecimal(Long.valueOf(value));
	}

	public General() {
		super();
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
