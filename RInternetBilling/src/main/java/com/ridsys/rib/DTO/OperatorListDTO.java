package com.ridsys.rib.DTO;

public class OperatorListDTO {
	private int id;
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private String title;
	private String department;
	private String company;
	private String phone1;
	private String phone2;
	private String email1;
	private String email2;
	private String messenger1;
	private String messenger2;
	private String notes;
	private String lastlogin;
	private String creationdate;
	private String creationby;
	private String updatedate;
	private String updateby;	

	public OperatorListDTO(int id, String username, String password, String firstname, String lastname, String title,
			String department, String company, String phone1, String phone2, String email1, String email2,
			String messenger1, String messenger2, String notes, String lastlogin, String creationdate,
			String creationby, String updatedate, String updateby) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.title = title;
		this.department = department;
		this.company = company;
		this.phone1 = phone1;
		this.phone2 = phone2;
		this.email1 = email1;
		this.email2 = email2;
		this.messenger1 = messenger1;
		this.messenger2 = messenger2;
		this.notes = notes;
		this.lastlogin = lastlogin;
		this.creationdate = creationdate;
		this.creationby = creationby;
		this.updatedate = updatedate;
		this.updateby = updateby;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getEmail1() {
		return email1;
	}

	public void setEmail1(String email1) {
		this.email1 = email1;
	}

	public String getEmail2() {
		return email2;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}

	public String getMessenger1() {
		return messenger1;
	}

	public void setMessenger1(String messenger1) {
		this.messenger1 = messenger1;
	}

	public String getMessenger2() {
		return messenger2;
	}

	public void setMessenger2(String messenger2) {
		this.messenger2 = messenger2;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getLastlogin() {
		return lastlogin;
	}

	public void setLastlogin(String lastlogin) {
		this.lastlogin = lastlogin;
	}

	public String getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(String creationdate) {
		this.creationdate = creationdate;
	}

	public String getCreationby() {
		return creationby;
	}

	public void setCreationby(String creationby) {
		this.creationby = creationby;
	}

	public String getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}

	public String getUpdateby() {
		return updateby;
	}

	public void setUpdateby(String updateby) {
		this.updateby = updateby;
	}

}
