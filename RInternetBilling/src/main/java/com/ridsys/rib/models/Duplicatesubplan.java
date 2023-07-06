package com.ridsys.rib.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "duplicatesubplan")
public class Duplicatesubplan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int planid;
	private int subplanid;
	private int opid;
	private String subplan;
	private String price;
	private String tax;
	private String taxcost;
	private String mrp;
	private boolean taxinclude;
	private String plantimebank;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String creationdate;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String updatedate;

	public Duplicatesubplan() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Duplicatesubplan(int id, int planid, int subplanid, int opid, String subplan, String price, String tax,
			String taxcost, String mrp, boolean taxinclude, String plantimebank, String creationdate,
			String updatedate) {
		super();
		this.id = id;
		this.planid = planid;
		this.subplanid = subplanid;
		this.subplan = subplan;
		this.price = price;
		this.tax = tax;
		this.taxcost = taxcost;
		this.mrp = mrp;
		this.taxinclude = taxinclude;
		this.plantimebank = plantimebank;
		this.creationdate = creationdate;
		this.updatedate = updatedate;
	}

	public int getOpid() {
		return opid;
	}

	public void setOpid(int opid) {
		this.opid = opid;
	}

	public int getSubplanid() {
		return subplanid;
	}

	public void setSubplanid(int subplanid) {
		this.subplanid = subplanid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPlanid() {
		return planid;
	}

	public void setPlanid(int planid) {
		this.planid = planid;
	}

	public String getSubplan() {
		return subplan;
	}

	public void setSubplan(String subplan) {
		this.subplan = subplan;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	public String getTaxcost() {
		return taxcost;
	}

	public void setTaxcost(String taxcost) {
		this.taxcost = taxcost;
	}

	public String getMrp() {
		return mrp;
	}

	public void setMrp(String mrp) {
		this.mrp = mrp;
	}

	public boolean isTaxinclude() {
		return taxinclude;
	}

	public void setTaxinclude(boolean taxinclude) {
		this.taxinclude = taxinclude;
	}

	public String getPlantimebank() {
		return plantimebank;
	}

	public void setPlantimebank(String plantimebank) {
		this.plantimebank = plantimebank;
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

}
