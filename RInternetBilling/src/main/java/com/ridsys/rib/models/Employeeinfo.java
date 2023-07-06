package com.ridsys.rib.models;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ridsys.rib.DTO.TicketSubjectTypeDTO;

@Entity
@Table(name = "employeeinfo")
@NamedNativeQuery(name = "Employeeinfo.getEmployeeList", query = "SELECT id,empname as name FROM employeeinfo WHERE isdelete=0", resultSetMapping = "Mapping.EmployeeList")
@SqlResultSetMapping(name = "Mapping.EmployeeList", classes = {
		@ConstructorResult(targetClass = TicketSubjectTypeDTO.class, columns = {
				@ColumnResult(name = "id", type = Integer.class), @ColumnResult(name = "name") }) })
public class Employeeinfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private int empid;
	private String username;
	private String password;
	private String empname;
	private String mobilephone;
	private String email;
	private String creationby;
	private boolean isactive;
	private boolean isdelete;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String createddate;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String updateddate;

	public Employeeinfo() {
		super();
	}

	public Employeeinfo(long id, int empid, String username, String password, String empname, String mobilephone,
			String email, String creationby, boolean isactive, boolean isdelete, String createddate,
			String updateddate) {
		super();
		this.id = id;
		this.empid = empid;
		this.username = username;
		this.password = password;
		this.empname = empname;
		this.mobilephone = mobilephone;
		this.email = email;
		this.creationby = creationby;
		this.isactive = isactive;
		this.isdelete = isdelete;
		this.createddate = createddate;
		this.updateddate = updateddate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public int getEmpid() {
		return empid;
	}

	public void setEmpid(int empid) {
		this.empid = empid;
	}

	public String getEmpname() {
		return empname;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}

	public String getMobilephone() {
		return mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCreationby() {
		return creationby;
	}

	public void setCreationby(String creationby) {
		this.creationby = creationby;
	}

	public boolean isIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	public boolean isIsdelete() {
		return isdelete;
	}

	public void setIsdelete(boolean isdelete) {
		this.isdelete = isdelete;
	}

	public String getCreateddate() {
		return createddate;
	}

	public void setCreateddate(String createddate) {
		this.createddate = createddate;
	}

	public String getUpdateddate() {
		return updateddate;
	}

	public void setUpdateddate(String updateddate) {
		this.updateddate = updateddate;
	}

}
