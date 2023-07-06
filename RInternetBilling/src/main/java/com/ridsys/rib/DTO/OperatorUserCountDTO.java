package com.ridsys.rib.DTO;

public class OperatorUserCountDTO {

	public String firstname;
	public String lastname;
	public String username;
	public String password;
	public int lcoid;
	public int vlanid;
	public int total;
	public int active;
	public int deactive;
	public int online;
	public int offline;
	public int newcus;
	public int deleted;
	private String arealan;
	private String switchname;
	private String portno;
	private boolean iswithoutRnet;
	private String netStatus;

	public OperatorUserCountDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OperatorUserCountDTO(String firstname, String lastname, String username, String password, int lcoid,
			int vlanid, int total, int active, int deactive, int online, int offline, int newcus, int deleted,
			String arealan, String switchname, String portno, boolean iswithoutRnet, String netStatus) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.lcoid = lcoid;
		this.vlanid = vlanid;
		this.total = total;
		this.active = active;
		this.deactive = deactive;
		this.online = online;
		this.offline = offline;
		this.newcus = newcus;
		this.deleted = deleted;
		this.arealan = arealan;
		this.switchname = switchname;
		this.portno = portno;
		this.iswithoutRnet = iswithoutRnet;
		this.netStatus = netStatus;
	}

	public String getNetStatus() {
		return netStatus;
	}

	public void setNetStatus(String netStatus) {
		this.netStatus = netStatus;
	}

	public String getSwitchname() {
		return switchname;
	}

	public void setSwitchname(String switchname) {
		this.switchname = switchname;
	}

	public String getPortno() {
		return portno;
	}

	public void setPortno(String portno) {
		this.portno = portno;
	}

	public boolean isIswithoutRnet() {
		return iswithoutRnet;
	}

	public void setIswithoutRnet(boolean iswithoutRnet) {
		this.iswithoutRnet = iswithoutRnet;
	}

	public String getArealan() {
		return arealan;
	}

	public void setArealan(String arealan) {
		this.arealan = arealan;
	}

	public int getLcoid() {
		return lcoid;
	}

	public void setLcoid(int lcoid) {
		this.lcoid = lcoid;
	}

	public int getVlanid() {
		return vlanid;
	}

	public void setVlanid(int vlanid) {
		this.vlanid = vlanid;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public int getDeactive() {
		return deactive;
	}

	public void setDeactive(int deactive) {
		this.deactive = deactive;
	}

	public int getOnline() {
		return online;
	}

	public void setOnline(int online) {
		this.online = online;
	}

	public int getOffline() {
		return offline;
	}

	public void setOffline(int offline) {
		this.offline = offline;
	}

	public int getNewcus() {
		return newcus;
	}

	public void setNewcus(int newcus) {
		this.newcus = newcus;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
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

}
