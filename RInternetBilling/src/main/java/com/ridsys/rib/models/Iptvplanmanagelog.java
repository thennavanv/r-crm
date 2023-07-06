package com.ridsys.rib.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="iptvplanmanagelog")
@Data
@NoArgsConstructor
public class Iptvplanmanagelog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotBlank(message = "rechargeid cannot be null")
	private String rechargeid;

	@NotBlank(message = "username cannot be null")
	private String username;

	private int plantimebank;
	private String creationdate;
	private String quotaexpirydate;
	private int planid;

	
	private double plancost;

	private double plantax;
	private double planmrp;
	private double plandiscount;
	private double plancuscost;
	private double planmsocost;
	private String planlcocomm;
	private String planname;
	private String remarks;
	
	@Column(name="creationby")
	private String creationby;
	
	@Column(name="creationbyusername")
	private String creationbyusername;
 
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "packagetypeid", referencedColumnName = "id")
	private Iptvpackagetype iptvpackagetype;

	public Iptvplanmanagelog(int id, @NotBlank(message = "rechargeid cannot be null") String rechargeid,
			@NotBlank(message = "username cannot be null") String username, int plantimebank, String creationdate,
			String quotaexpirydate, int planid, double plancost, double plantax, double planmrp, double plandiscount,
			double plancuscost, double planmsocost, String planlcocomm, String planname, String creationby,
			String creationbyusername, Iptvpackagetype iptvpackagetype,String remarks) {
		super();
		this.id = id;
		this.rechargeid = rechargeid;
		this.username = username;
		this.plantimebank = plantimebank;
		this.creationdate = creationdate;
		this.quotaexpirydate = quotaexpirydate;
		this.planid = planid;
		this.plancost = plancost;
		this.plantax = plantax;
		this.planmrp = planmrp;
		this.plandiscount = plandiscount;
		this.plancuscost = plancuscost;
		this.planmsocost = planmsocost;
		this.planlcocomm = planlcocomm;
		this.planname = planname;
		this.creationby = creationby;
		this.creationbyusername = creationbyusername;
		this.iptvpackagetype = iptvpackagetype;
		this.remarks=remarks;
	}

	
	
	
}
