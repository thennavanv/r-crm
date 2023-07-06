package com.ridsys.rib.DTO;

public class OperatorDTO {

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
	private int deactivecount;
	private int activeCount;
	private int newCount;
	private String arealan;
	private int operatorid;

	public OperatorDTO(int lcoid, String firstname, String lastname, String area, String address, String mobile,
			String email, String state, int vlan, String username, String password, int deactivecount, int activeCount,
			int newCount, String arealan, int operatorid) {
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
		this.deactivecount = deactivecount;
		this.activeCount = activeCount;
		this.newCount = newCount;
		this.arealan = arealan;
		this.operatorid = operatorid;
	}
	
	public OperatorDTO(int lcoid, String firstname, String lastname, String area, String address, String mobile,
			String email, String state, int vlan, String username, String password, int deactivecount, int activeCount,
			int newCount, String arealan) {
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
		this.deactivecount = deactivecount;
		this.activeCount = activeCount;
		this.newCount = newCount;
		this.arealan = arealan;
	}


	public OperatorDTO() {
		super();
	}

	public int getOperatorid() {
		return operatorid;
	}

	public void setOperatorid(int operatorid) {
		this.operatorid = operatorid;
	}

	public String getArealan() {
		return arealan;
	}

	public void setArealan(String arealan) {
		this.arealan = arealan;
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

	public void setEmail(String emai) {
		this.email = emai;
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

	public void setUsername(String useraname) {
		this.username = useraname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getActiveCount() {
		return activeCount;
	}

	public void setActiveCount(int activeCount) {
		this.activeCount = activeCount;
	}

	public int getDeactivecount() {
		return deactivecount;
	}

	public void setDeactivecount(int expiryCount) {
		this.deactivecount = expiryCount;
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

	public int getNewCount() {
		return newCount;
	}

	public void setNewCount(int newCount) {
		this.newCount = newCount;
	}

}
