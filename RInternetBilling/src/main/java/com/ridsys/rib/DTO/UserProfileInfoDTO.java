package com.ridsys.rib.DTO;

import org.springframework.beans.factory.annotation.Value;

public class UserProfileInfoDTO {

	@Value("${spring.application.url}")
	private String url;

	private int id;
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private String email;
	private String department;
	private String company;
	private String workphone;
	private String homephone;
	private String mobilephone;
	private String address;
	private String city;
	private String state;
	private String country;
	private String zip;
	private String notes;
	private String walletbalance;
	private String changeuserinfo;
	private String portalloginpassword;
	private int enableportallogin;
	private int verificationstatus;
	private String userproofimage1;
	private String olttype;
	private String ponout;
	private String userdevice;
	private String macaddress;
	private String creationdate;
	private String updatedate;
	private String creationby;
	private String updateby;
	private int opid;
	private String planname;
	private int planid;
	private String quotaexpirydate;
	private String opname;
	private String opphone;
	private String opemail;
	private String optitle;
	private String opdepartment;
	private String opcompany;
	private boolean expstatus;
	private String billingaddress;
	private String billzipcode;
	private String billingstate;
	private String billingcity;
	private String selectIsOtt;
	private String opusername;
	private String deviceno;

	public UserProfileInfoDTO(int id, String username, String password, String firstname, String lastname, String email,
			String department, String company, String workphone, String homephone, String mobilephone, String address,
			String city, String state, String country, String zip, String notes, String changeuserinfo,
			String portalloginpassword, int enableportallogin, int verificationstatus, String userproofimage1,
			String olttype, String ponout, String userdevice, String macaddress, String creationdate, String updatedate,
			String creationby, String updateby, int opid, String planname, int planid, String quotaexpirydate,
			String opname, String opphone, String opemail, String optitle, String opdepartment, String opcompany,
			String walletbalance, boolean expstatus, String billingaddress, String billzipcode, String billingstate,
			String billingcity, String selectIsOtt) {
		super();

//			
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.department = department;
		this.company = company;
		this.workphone = workphone;
		this.homephone = homephone;
		this.mobilephone = mobilephone;
		this.address = address;
		this.city = city;
		this.state = state;
		this.country = country;
		this.zip = zip;
		this.notes = notes;
		this.changeuserinfo = changeuserinfo;
		this.portalloginpassword = portalloginpassword;
		this.enableportallogin = enableportallogin;
		this.verificationstatus = verificationstatus;
		this.userproofimage1 = userproofimage1;
		System.out.println(userproofimage1);
//		this.userproofimage1 = "http://dnet.ridsys.in/RCRMPACKAGE/user-proofimage/" + username + "/" + userproofimage1;

		this.olttype = olttype;
		this.ponout = ponout;
		this.userdevice = userdevice;
		this.macaddress = macaddress;
		this.creationdate = creationdate;
		this.updatedate = updatedate;
		this.creationby = creationby;
		this.updateby = updateby;
		this.opid = opid;
		this.planname = planname;
		this.planid = planid;
		this.quotaexpirydate = quotaexpirydate;
		this.opname = opname;
		this.opphone = opphone;
		this.opemail = opemail;
		this.optitle = optitle;
		this.opdepartment = opdepartment;
		this.opcompany = opcompany;
		this.walletbalance = walletbalance;
		this.expstatus = expstatus;
		this.billingaddress = billingaddress;
		this.billzipcode = billzipcode;
		this.billingstate = billingstate;
		this.billingcity = billingcity;
		this.selectIsOtt = selectIsOtt;
	}

	public UserProfileInfoDTO(int id, String username, String password, String firstname, String lastname, String email,
			String department, String company, String workphone, String homephone, String mobilephone, String address,
			String city, String state, String country, String zip, String notes, String changeuserinfo,
			String portalloginpassword, int enableportallogin, int verificationstatus, String userproofimage1,
			String olttype, String ponout, String userdevice, String macaddress, String creationdate, String updatedate,
			String creationby, String updateby, int opid, String planname, int planid, String quotaexpirydate,
			String opname, String opphone, String opemail, String optitle, String opdepartment, String opcompany,
			String walletbalance, boolean expstatus, String billingaddress, String billzipcode, String billingstate,
			String billingcity, String selectIsOtt, String opusername, String deviceno) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.department = department;
		this.company = company;
		this.workphone = workphone;
		this.homephone = homephone;
		this.mobilephone = mobilephone;
		this.address = address;
		this.city = city;
		this.state = state;
		this.country = country;
		this.zip = zip;
		this.notes = notes;
		this.changeuserinfo = changeuserinfo;
		this.portalloginpassword = portalloginpassword;
		this.enableportallogin = enableportallogin;
		this.verificationstatus = verificationstatus;
		this.userproofimage1 = userproofimage1;

		this.olttype = olttype;
		this.ponout = ponout;
		this.userdevice = userdevice;
		this.macaddress = macaddress;
		this.creationdate = creationdate;
		this.updatedate = updatedate;
		this.creationby = creationby;
		this.updateby = updateby;
		this.opid = opid;
		this.planname = planname;
		this.planid = planid;
		this.quotaexpirydate = quotaexpirydate;
		this.opname = opname;
		this.opphone = opphone;
		this.opemail = opemail;
		this.optitle = optitle;
		this.opdepartment = opdepartment;
		this.opcompany = opcompany;
		this.walletbalance = walletbalance;
		this.expstatus = expstatus;
		this.billingaddress = billingaddress;
		this.billzipcode = billzipcode;
		this.billingstate = billingstate;
		this.billingcity = billingcity;
		this.selectIsOtt = selectIsOtt;
		this.opusername = opusername;
		this.deviceno = deviceno;
	}

	public UserProfileInfoDTO(int id, String username, String password, String firstname, String lastname, String email,
			String department, String company, String workphone, String homephone, String mobilephone, String address,
			String city, String state, String country, String zip, String notes, String changeuserinfo,
			String portalloginpassword, int enableportallogin, int verificationstatus, String userproofimage1,
			String creationdate, String walletbalance, boolean expstatus) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.department = department;
		this.company = company;
		this.workphone = workphone;
		this.homephone = homephone;
		this.mobilephone = mobilephone;
		this.address = address;
		this.city = city;
		this.state = state;
		this.country = country;
		this.zip = zip;
		this.notes = notes;
		this.changeuserinfo = changeuserinfo;
		this.portalloginpassword = portalloginpassword;
		this.enableportallogin = enableportallogin;
		this.verificationstatus = verificationstatus;
		this.userproofimage1 = userproofimage1;

		this.creationdate = creationdate;
		this.walletbalance = walletbalance;
		this.expstatus = expstatus;
	}

	public String getOpusername() {
		return opusername;
	}

	public void setOpusername(String opusername) {
		this.opusername = opusername;
	}

	public String getWalletbalance() {
		return walletbalance;
	}

	public void setWalletbalance(String walletbalance) {
		this.walletbalance = walletbalance;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getWorkphone() {
		return workphone;
	}

	public void setWorkphone(String workphone) {
		this.workphone = workphone;
	}

	public String getHomephone() {
		return homephone;
	}

	public void setHomephone(String homephone) {
		this.homephone = homephone;
	}

	public String getMobilephone() {
		return mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getChangeuserinfo() {
		return changeuserinfo;
	}

	public void setChangeuserinfo(String changeuserinfo) {
		this.changeuserinfo = changeuserinfo;
	}

	public String getPortalloginpassword() {
		return portalloginpassword;
	}

	public void setPortalloginpassword(String portalloginpassword) {
		this.portalloginpassword = portalloginpassword;
	}

	public int getEnableportallogin() {
		return enableportallogin;
	}

	public void setEnableportallogin(int enableportallogin) {
		this.enableportallogin = enableportallogin;
	}

	public int getVerificationstatus() {
		return verificationstatus;
	}

	public void setVerificationstatus(int verificationstatus) {
		this.verificationstatus = verificationstatus;
	}

	public String getUserproofimage1() {
		return userproofimage1;
	}

	public void setUserproofimage1(String userproofimage1) {
		this.userproofimage1 = userproofimage1;
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

	public String getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(String creationdate) {
		this.creationdate = creationdate;
	}

	public String getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}

	public String getCreationby() {
		return creationby;
	}

	public void setCreationby(String creationby) {
		this.creationby = creationby;
	}

	public String getUpdateby() {
		return updateby;
	}

	public void setUpdateby(String updateby) {
		this.updateby = updateby;
	}

	public int getOpid() {
		return opid;
	}

	public void setOpid(int opid) {
		this.opid = opid;
	}

	public String getPlanname() {
		return planname;
	}

	public void setPlanname(String planname) {
		this.planname = planname;
	}

	public int getPlanid() {
		return planid;
	}

	public void setPlanid(int planid) {
		this.planid = planid;
	}

	public String getQuotaexpirydate() {
		return quotaexpirydate;
	}

	public void setQuotaexpirydate(String quotaexpirydate) {
		this.quotaexpirydate = quotaexpirydate;
	}

	public String getOpname() {
		return opname;
	}

	public void setOpname(String opname) {
		this.opname = opname;
	}

	public String getOpphone() {
		return opphone;
	}

	public void setOpphone(String opphone) {
		this.opphone = opphone;
	}

	public String getOpemail() {
		return opemail;
	}

	public void setOpemail(String opemail) {
		this.opemail = opemail;
	}

	public String getOptitle() {
		return optitle;
	}

	public void setOptitle(String optitle) {
		this.optitle = optitle;
	}

	public String getOpdepartment() {
		return opdepartment;
	}

	public void setOpdepartment(String opdepartment) {
		this.opdepartment = opdepartment;
	}

	public String getOpcompany() {
		return opcompany;
	}

	public void setOpcompany(String opcompany) {
		this.opcompany = opcompany;
	}

	public boolean isExpstatus() {
		return expstatus;
	}

	public void setExpstatus(boolean expstatus) {
		this.expstatus = expstatus;
	}

	public String getBillingaddress() {
		return billingaddress;
	}

	public void setBillingaddress(String billingaddress) {
		this.billingaddress = billingaddress;
	}

	public String getBillzipcode() {
		return billzipcode;
	}

	public void setBillzipcode(String billzipcode) {
		this.billzipcode = billzipcode;
	}

	public String getBillingstate() {
		return billingstate;
	}

	public void setBillingstate(String billingstate) {
		this.billingstate = billingstate;
	}

	public String getBillingcity() {
		return billingcity;
	}

	public void setBillingcity(String billingcity) {
		this.billingcity = billingcity;
	}

	public String getSelectIsOtt() {
		return selectIsOtt;
	}

	public void setSelectIsOtt(String selectIsOtt) {
		this.selectIsOtt = selectIsOtt;
	}

	public String getDeviceno() {
		return deviceno;
	}

	public void setDeviceno(String deviceno) {
		this.deviceno = deviceno;
	}

}
