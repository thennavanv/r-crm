package com.ridsys.rib.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clientinfo")
@Data
@NoArgsConstructor
public class Clientinfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String clientname;
	private String company;
	private String address;
	private String email;
	private String phone;
	private String mobile1;
	private String mobile2;
	private String location;
	private String adurl1;
	private String adurl2;
	private String domainurl;
	private String gstin;
	private String linkurl;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String creationdate;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String updateddate;
	private boolean isactive;
	private boolean isdelete;

	public Clientinfo(int id, String clientname, String company, String address, String email, String phone,
			String mobile1, String mobile2, String location, String adurl1, String adurl2, String creationdate,
			String updateddate, boolean isactive, boolean isdelete, String domainurl, String gstin, String linkurl) {
		super();
		this.id = id;
		this.clientname = clientname;
		this.company = company;
		this.address = address;
		this.email = email;
		this.phone = phone;
		this.mobile1 = mobile1;
		this.mobile2 = mobile2;
		this.location = location;
		this.adurl1 = adurl1;
		this.adurl2 = adurl2;
		this.creationdate = creationdate;
		this.updateddate = updateddate;
		this.isactive = isactive;
		this.isdelete = isdelete;
		this.domainurl = domainurl;
		this.gstin = gstin;
		this.linkurl = linkurl;

	}

}
