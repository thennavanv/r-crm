package com.ridsys.rib.payload.request;

import javax.validation.constraints.NotNull;

public class CreateSwitchRequest {

	@NotNull
	private String switchname;

	@NotNull
	private String switchip;

	@NotNull
	private String switchport;

	@NotNull
	private int provider;

	@NotNull
	private int branch;

	@NotNull
	private int nas;

	private int portcount;

	public String getSwitchname() {
		return switchname;
	}

	public void setSwitchname(String switchname) {
		this.switchname = switchname;
	}

	public String getSwitchip() {
		return switchip;
	}

	public void setSwitchip(String switchip) {
		this.switchip = switchip;
	}

	public String getSwitchport() {
		return switchport;
	}

	public void setSwitchport(String switchport) {
		this.switchport = switchport;
	}

	public int getProvider() {
		return provider;
	}

	public void setProvider(int provider) {
		this.provider = provider;
	}

	public int getBranch() {
		return branch;
	}

	public void setBranch(int branch) {
		this.branch = branch;
	}

	public int getNas() {
		return nas;
	}

	public void setNas(int nas) {
		this.nas = nas;
	}

	public int getPortcount() {
		return portcount;
	}

	public void setPortcount(int portcount) {
		this.portcount = portcount;
	}

}
