package com.ridsys.rib.payload.request;

public class CreateOperatorRequest {

	private String address;
	private String area;
	private String company="";
	private String department="";
	private String email1;
	private String firstname;
	private String lastname;
	private String notes="";
	private String password;
	private String phone1;
	private String state;
	private String title="";
	private String username;
	private String zipcode;
	private int vlanid;
	private int operatorid;
	private boolean selectIsIptvOpr;
	private int switchid;
	private int portno;
	private String lanarea;
	private boolean iswithoutRnet;

	public boolean isIswithoutRnet() {
		return iswithoutRnet;
	}

	public void setIswithoutRnet(boolean iswithoutRnet) {
		this.iswithoutRnet = iswithoutRnet;
	}

	public int getSwitchid() {
		return switchid;
	}

	public void setSwitchid(int switchid) {
		this.switchid = switchid;
	}

	public int getPortno() {
		return portno;
	}

//
	public void setPortno(int portno) {
		this.portno = portno;
	}

	public String getLanarea() {
		return lanarea;
	}

	public void setLanarea(String lanarea) {
		this.lanarea = lanarea;
	}

	public boolean isSelectIsIptvOpr() {
		return selectIsIptvOpr;
	}

	public void setSelectIsIptvOpr(boolean selectIsIptvOpr) {
		this.selectIsIptvOpr = selectIsIptvOpr;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getEmail1() {
		return email1;
	}

	public void setEmail1(String email1) {
		this.email1 = email1;
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

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getVlanid() {
		return vlanid;
	}

	public void setVlanid(int vlanid) {
		this.vlanid = vlanid;
	}

	public int getOperatorid() {
		return operatorid;
	}

	public void setOperatorid(int operatorid) {
		this.operatorid = operatorid;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

}
