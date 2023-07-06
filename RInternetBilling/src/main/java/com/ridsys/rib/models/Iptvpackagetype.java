package com.ridsys.rib.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="iptvpackagetype")
@Data
@NoArgsConstructor
public class Iptvpackagetype {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String type;

	public Iptvpackagetype(int id, String type) {
		super();
		this.id = id;
		this.type = type;
	}
	
	
}
