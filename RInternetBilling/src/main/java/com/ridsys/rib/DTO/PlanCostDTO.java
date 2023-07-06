package com.ridsys.rib.DTO;

public class PlanCostDTO {

	private int id;
	private String planname;
	private double plancost;
	private double plantax;
	private double planmrp;
	private double plandiscount;
	private double planmsocost;
	private double planlcocomm;
	private double plancuscost;
	private int plantimebank;

	public PlanCostDTO(int id, String planname, double plancost, double plantax, double planmrp, double plandiscount,
			double plancuscost, double planlcocomm, double planmsocost, int plantimebank) {
		super();
		this.id = id;
		this.planname = planname;
		this.plancost = plancost;
		this.plantax = plantax;
		this.planmrp = planmrp;
		this.plandiscount = plandiscount;
		this.plancuscost = plancuscost;
		this.planlcocomm = planlcocomm;
		this.planmsocost = planmsocost;
		this.plantimebank = plantimebank;
	}

	public double getPlanmrp() {
		return planmrp;
	}

	public void setPlanmrp(double planmrp) {
		this.planmrp = planmrp;
	}

	public double getPlandiscount() {
		return plandiscount;
	}

	public void setPlandiscount(double plandiscount) {
		this.plandiscount = plandiscount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPlanname() {
		return planname;
	}

	public void setPlanname(String planname) {
		this.planname = planname;
	}

	public double getPlancost() {
		return plancost;
	}

	public void setPlancost(double plancost) {
		this.plancost = plancost;
	}

	public double getPlantax() {
		return plantax;
	}

	public void setPlantax(double plantax) {
		this.plantax = plantax;
	}

	public double getPlanmsocost() {
		return planmsocost;
	}

	public void setPlanmsocost(double planmsocost) {
		this.planmsocost = planmsocost;
	}

	public double getPlanlcocomm() {
		return planlcocomm;
	}

	public void setPlanlcocomm(double planlcocomm) {
		this.planlcocomm = planlcocomm;
	}

	public double getPlancuscost() {
		return plancuscost;
	}

	public void setPlancuscost(double plancuscost) {
		this.plancuscost = plancuscost;
	}

	public int getPlantimebank() {
		return plantimebank;
	}

	public void setPlantimebank(int plantimebank) {
		this.plantimebank = plantimebank;
	}

}
