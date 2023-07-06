package com.ridsys.rib.payload.request;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.ridsys.rib.models.Radusergroup;
import com.ridsys.rib.models.Userbillinfo;
import com.ridsys.rib.models.Userinfo;

public class CreateuserRequest {
	
	@NotBlank
	private String opusername;

	@NotBlank
	private String role;

	@NotBlank
	private String username;

	@NotBlank
	private String password;

	@NotBlank
	private String authtype;

	private String passwordType;
	private String macaddress;
	private String pincode;
	private String group_macaddress;
	private String group_pincode;

	private List<Radusergroup> radusergroups;
	private Userinfo userinfo;
	private Userbillinfo userbillinfo;
	private List<CreateAttributesRequest> attributes;
	private String userproofimage1;
	private boolean selectIsOtt;
	private boolean selectIsIptv;
	private boolean autoip;

//	@NotBlank
	private String fromip;

//	@NotBlank
	private String framedipaddress;
	private String iptype;
	

//	private int vlanid;
//
//	public int getVlanid() {
//		return vlanid;
//	}
//
//	public void setVlanid(int vlanid) {
//		this.vlanid = vlanid;
//	}

	public String getOpusername() {
		return opusername;
	}

	public boolean isAutoip() {
		return autoip;
	}

	public void setAutoip(boolean autoip) {
		this.autoip = autoip;
	}

	public String getFromip() {
		return fromip;
	}

	public void setFromip(String fromip) {
		this.fromip = fromip;
	}

	public String getFramedipaddress() {
		return framedipaddress;
	}

	public void setFramedipaddress(String framedipaddress) {
		this.framedipaddress = framedipaddress;
	}

	public boolean isSelectIsIptv() {
		return selectIsIptv;
	}

	public void setSelectIsIptv(boolean selectIsIptv) {
		this.selectIsIptv = selectIsIptv;
	}

	public void setOpusername(String opusername) {
		this.opusername = opusername;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAuthtype() {
		return authtype;
	}

	public void setAuthtype(String authtype) {
		this.authtype = authtype;
	}

	public String getPasswordType() {
		return passwordType;
	}

	public void setPasswordType(String passwordType) {
		this.passwordType = passwordType;
	}

	public String getMacaddress() {
		return macaddress;
	}

	public void setMacaddress(String macaddress) {
		this.macaddress = macaddress;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getGroup_macaddress() {
		return group_macaddress;
	}

	public void setGroup_macaddress(String group_macaddress) {
		this.group_macaddress = group_macaddress;
	}

	public String getGroup_pincode() {
		return group_pincode;
	}

	public void setGroup_pincode(String group_pincode) {
		this.group_pincode = group_pincode;
	}

	public List<Radusergroup> getRadusergroups() {
		return radusergroups;
	}

	public void setRadusergroups(List<Radusergroup> radusergroups) {
		this.radusergroups = radusergroups;
	}

	public Userinfo getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(Userinfo userinfo) {
		this.userinfo = userinfo;
	}

	public Userbillinfo getUserbillinfo() {
		return userbillinfo;
	}

	public void setUserbillinfo(Userbillinfo userbillinfo) {
		this.userbillinfo = userbillinfo;
	}

	public List<CreateAttributesRequest> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<CreateAttributesRequest> attributes) {
		this.attributes = attributes;
	}

	public String getUserproofimage1() {
		return userproofimage1;
	}

	public void setUserproofimage1(String userproofimage1) {
		this.userproofimage1 = userproofimage1;
	}

	public boolean isSelectIsOtt() {
		return selectIsOtt;
	}

	public void setSelectIsOtt(boolean selectIsOtt) {
		this.selectIsOtt = selectIsOtt;
	}

	public String getIptype() {
		return iptype;
	}

	public void setIptype(String iptype) {
		this.iptype = iptype;
	}

}
