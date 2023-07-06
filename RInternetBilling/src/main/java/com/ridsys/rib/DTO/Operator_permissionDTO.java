package com.ridsys.rib.DTO;


public class Operator_permissionDTO {

	private int lcoid;
	private String firstname;
	private String lastname;
	private String area;
	private String address;
	private String mobile;
	private String email;
	private String state;
	private int vlan;
	private String username;
	private String password;
	private boolean walletRecharge;
	private String ott;
	private String vod;
	private String sms;
	private String emailsts;

	public Operator_permissionDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Operator_permissionDTO(int lcoid, String firstname, String lastname, String area, String address,
			String mobile, String email, String state, int vlan, String username, String password,
			boolean walletRecharge, String ott, String vod, String sms, String emailsts) {
		super();
		this.lcoid = lcoid;
		this.firstname = firstname;
		this.lastname = lastname;
		this.area = area;
		this.address = address;
		this.mobile = mobile;
		this.email = email;
		this.state = state;
		this.vlan = vlan;
		this.username = username;
		this.password = password;
		this.walletRecharge = walletRecharge;
		this.ott = ott;
		this.vod = vod;
		this.sms = sms;
		this.emailsts = emailsts;
	}

	public int getLcoid() {
		return lcoid;
	}

	public void setLcoid(int lcoid) {
		this.lcoid = lcoid;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getVlan() {
		return vlan;
	}

	public void setVlan(int vlan) {
		this.vlan = vlan;
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

	public boolean isWalletRecharge() {
		return walletRecharge;
	}

	public void setWalletRecharge(boolean walletRecharge) {
		this.walletRecharge = walletRecharge;
	}

	public String getOtt() {
		return ott;
	}

	public void setOtt(String ott) {
		this.ott = ott;
	}

	public String getVod() {
		return vod;
	}

	public void setVod(String vod) {
		this.vod = vod;
	}

	public String getSms() {
		return sms;
	}

	public void setSms(String sms) {
		this.sms = sms;
	}

	public String getEmailsts() {
		return emailsts;
	}

	public void setEmailsts(String emailsts) {
		this.emailsts = emailsts;
	}

}
