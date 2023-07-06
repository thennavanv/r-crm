package com.ridsys.rib.DTO;

public class PlanDTO {

	private String tax;
	private String taxCost;
	private String price;
	private String mrp;
	private String cusCost;
	private String lcoCom;
	private String msoCom;
	private String planId;
	private String subplanId;
	private String discount;
	private String discountMonth;
	private String subplan;
	private String planTimeBank;
	private String lcoComPer;
	private Boolean activeStatus;
	private String Mainplan;
	private boolean discountIncl;

	public PlanDTO(String subplan, String cusCost, String planTimeBank) {
		super();
		this.cusCost = cusCost;
		this.subplan = subplan;
		this.planTimeBank = planTimeBank;
	}

	public PlanDTO(String tax, String taxCost, String price, String mrp, String cusCost, String lcoCom, String msoCom,
			String planId, String subplanId, String discount, String discountMonth, String subplan, String planTimeBank,
			String lcoComPer, Boolean activeStatus, String mainplan, boolean discountIncl) {
		super();
		this.tax = tax;
		this.taxCost = taxCost;
		this.price = price;
		this.mrp = mrp;
		this.cusCost = cusCost;
		this.lcoCom = lcoCom;
		this.msoCom = msoCom;
		this.planId = planId;
		this.subplanId = subplanId;
		this.discount = discount;
		this.discountMonth = String.valueOf(Integer.parseInt(discountMonth) / 30);
		this.subplan = subplan;
		this.planTimeBank = String.valueOf(Integer.parseInt(planTimeBank) / 30);
		this.lcoComPer = lcoComPer;
		this.activeStatus = activeStatus;
		Mainplan = mainplan;
		this.discountIncl = discountIncl;
	}

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	public String getTaxCost() {
		return taxCost;
	}

	public void setTaxCost(String taxCost) {
		this.taxCost = taxCost;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getMrp() {
		return mrp;
	}

	public void setMrp(String mrp) {
		this.mrp = mrp;
	}

	public String getCusCost() {
		return cusCost;
	}

	public void setCusCost(String cusCost) {
		this.cusCost = cusCost;
	}

	public String getLcoCom() {
		return lcoCom;
	}

	public void setLcoCom(String lcoCom) {
		this.lcoCom = lcoCom;
	}

	public String getMsoCom() {
		return msoCom;
	}

	public void setMsoCom(String msoCom) {
		this.msoCom = msoCom;
	}

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getDiscountMonth() {
		return discountMonth;
	}

	public void setDiscountMonth(String discountMonth) {
		this.discountMonth = discountMonth;
	}

	public String getSubplan() {
		return subplan;
	}

	public void setSubplan(String subplan) {
		this.subplan = subplan;
	}

	public String getPlanTimeBank() {
		return planTimeBank;
	}

	public void setPlanTimeBank(String planTimeBank) {
		this.planTimeBank = planTimeBank;
	}

	public String getLcoComPer() {
		return lcoComPer;
	}

	public void setLcoComPer(String lcoComPer) {
		this.lcoComPer = lcoComPer;
	}

	public Boolean getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(Boolean activeStatus) {
		this.activeStatus = activeStatus;
	}

	public String getMainplan() {
		return Mainplan;
	}

	public void setMainplan(String mainplan) {
		Mainplan = mainplan;
	}

	public boolean isDiscountIncl() {
		return discountIncl;
	}

	public void setDiscountIncl(boolean discountIncl) {
		this.discountIncl = discountIncl;
	}

	public String getSubplanId() {
		return subplanId;
	}

	public void setSubplanId(String subplanId) {
		this.subplanId = subplanId;
	}

}
