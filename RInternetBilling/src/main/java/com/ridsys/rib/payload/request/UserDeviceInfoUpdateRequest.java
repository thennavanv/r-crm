package com.ridsys.rib.payload.request;

public class UserDeviceInfoUpdateRequest {

	private String olttype;
	private String ponout;
	private String userdevice;
	private String macaddress;
	private String username;
	private String vlanid;

	public String getVlanid() {
		return vlanid;
	}

	public void setVlanid(String vlanid) {
		this.vlanid = vlanid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getOlttype() {
		return olttype;
	}

	public void setOlttype(String olttype) {
		this.olttype = olttype;
	}

	public String getPonout() {
		return ponout;
	}

	public void setPonout(String ponout) {
		this.ponout = ponout;
	}

	public String getUserdevice() {
		return userdevice;
	}

	public void setUserdevice(String userdevice) {
		this.userdevice = userdevice;
	}

	public String getMacaddress() {
		return macaddress;
	}

	public void setMacaddress(String macaddress) {
		this.macaddress = macaddress;
	}

}
