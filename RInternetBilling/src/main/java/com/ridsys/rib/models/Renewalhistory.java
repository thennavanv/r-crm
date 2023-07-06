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
@Table(name = "renewalhistory")
@Data
@NoArgsConstructor
public class Renewalhistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String username;
	private String days;
	private String creationby;
	private String creationbyusername;
	private int planid;
	private boolean isupdate;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String oldexpirydate;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String newexpirydate;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String newstartdate;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String creationdate;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String updateddate;
}
