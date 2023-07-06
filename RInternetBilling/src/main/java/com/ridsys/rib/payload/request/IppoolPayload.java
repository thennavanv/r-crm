package com.ridsys.rib.payload.request;

import javax.validation.constraints.NotNull;

public class IppoolPayload {

	@NotNull
	private String poolname;

	@NotNull
	private String pooltype;

	@NotNull
	private String fromip;

	@NotNull
	private String toip;

	@NotNull
	private String franchies;

	@NotNull
	private String nasipaddress;

	@NotNull
	private String address;

	@NotNull
	private String netmask;

	private boolean status;

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNetmask() {
		return netmask;
	}

	public void setNetmask(String netmask) {
		this.netmask = netmask;
	}

	public String getNasipaddress() {
		return nasipaddress;
	}

	public void setNasipaddress(String nasipaddress) {
		this.nasipaddress = nasipaddress;
	}

	public String getPoolname() {
		return poolname;
	}

	public void setPoolname(String poolname) {
		this.poolname = poolname;
	}

	public String getPooltype() {
		return pooltype;
	}

	public void setPooltype(String pooltype) {
		this.pooltype = pooltype;
	}

	public String getFromip() {
		return fromip;
	}

	public void setFromip(String fromip) {
		this.fromip = fromip;
	}

	public String getToip() {
		return toip;
	}

	public void setToip(String toip) {
		this.toip = toip;
	}

	public String getFranchies() {
		return franchies;
	}

	public void setFranchies(String franchies) {
		this.franchies = franchies;
	}

}
