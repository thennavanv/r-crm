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
@Table(name = "userproof")
@Data
@NoArgsConstructor
public class Userproof {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String username;
	private String imageurl;
	private boolean isactive;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String createddate;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String updateddate;
	
	
	
	
}
