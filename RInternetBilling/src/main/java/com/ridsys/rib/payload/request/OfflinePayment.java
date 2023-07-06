package com.ridsys.rib.payload.request;

import javax.validation.constraints.NotBlank;

public class OfflinePayment {

	@NotBlank
	private String amount;

	@NotBlank
	private String fromusername;

	@NotBlank
	private String fromrole;

	@NotBlank
	private String tousername;

	@NotBlank
	private String torole;

	@NotBlank
	private String payment_type;

	private String description;

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getFromusername() {
		return fromusername;
	}

	public void setFromusername(String fromusername) {
		this.fromusername = fromusername;
	}

	public String getFromrole() {
		return fromrole;
	}

	public void setFromrole(String fromrole) {
		this.fromrole = fromrole;
	}

	public String getTousername() {
		return tousername;
	}

	public void setTousername(String tousername) {
		this.tousername = tousername;
	}

	public String getTorole() {
		return torole;
	}

	public void setTorole(String torole) {
		this.torole = torole;
	}

	public String getPayment_type() {
		return payment_type;
	}

	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
