package com.ridsys.rib.DTO;

public class UserDetailsDTO {

	private int id;
	private String name;
	private String lconame;
	private String startDate;
	private String expiryDate;
	private String planName;
	private String username;
	private String password;
	private String mobileNo;
	private int lcoId;
	private int vlanid;
	private String status;

	public UserDetailsDTO(int id, String name, String lconame, String startDate, String expiryDate, String planName,
			String username, String password, String mobileNo, int lcoId, int vlanid, String status) {
		super();
		this.id = id;
		this.name = name;
		this.lconame = lconame;
		this.startDate = startDate;
		this.expiryDate = expiryDate;
		this.planName = planName;
		this.username = username;
		this.password = password;
		this.mobileNo = mobileNo;
		this.lcoId = lcoId;
		this.vlanid = vlanid;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLconame() {
		return lconame;
	}

	public void setLconame(String lconame) {
		this.lconame = lconame;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
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

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public int getLcoId() {
		return lcoId;
	}

	public void setLcoId(int lcoId) {
		this.lcoId = lcoId;
	}

	public int getVlanid() {
		return vlanid;
	}

	public void setVlanid(int vlanid) {
		this.vlanid = vlanid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
