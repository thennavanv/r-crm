package com.ridsys.rib.DTO;

public class AllPlanListDTO {

	private String planName;
	private String validityDays;
	private String price;
	private String tax;
	private String mrp;
	private String discountPrice;
	private String cusCost;
	private String lcoCom;
	private String msoCom;
	private String uploadSpeed;
	private String downloadSpeed;
	private String plantype;
	private String billtype;
	private String totalLimit;
	private boolean activateStatus;
	private String packageType;
	private String lcoComPer;
	private String planId;
	private String subplanId;

	public AllPlanListDTO(String planName, String validityDays, String price, String tax, String mrp,
			String discountPrice, String cusCost, String lcoCom, String msoCom, String uploadSpeed,
			String downloadSpeed, String plantype, String billtype, String totalLimit, boolean activateStatus,
			String packageType, String lcoComPer, String planId, String subplanId) {
		super();
		this.planName = planName;
		this.validityDays = validityDays;
		this.price = price;
		this.tax = tax;
		this.mrp = mrp;
		this.discountPrice = discountPrice;
		this.cusCost = cusCost;
		this.lcoCom = lcoCom;
		this.msoCom = msoCom;
		this.uploadSpeed = uploadSpeed;
		this.downloadSpeed = downloadSpeed;
		this.plantype = plantype;
		this.billtype = billtype;
		this.totalLimit = totalLimit;
		this.activateStatus = activateStatus;
		this.packageType = packageType;
		this.lcoComPer = lcoComPer;
		this.planId = planId;
		this.subplanId = subplanId;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public String getValidityDays() {
		return validityDays;
	}

	public void setValidityDays(String validityDays) {
		this.validityDays = validityDays;
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

	public String getMrp() {
		return mrp;
	}

	public void setMrp(String mrp) {
		this.mrp = mrp;
	}

	public String getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(String discountPrice) {
		this.discountPrice = discountPrice;
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

	public String getUploadSpeed() {
		return uploadSpeed;
	}

	public void setUploadSpeed(String uploadSpeed) {
		this.uploadSpeed = uploadSpeed;
	}

	public String getDownloadSpeed() {
		return downloadSpeed;
	}

	public void setDownloadSpeed(String downloadSpeed) {
		this.downloadSpeed = downloadSpeed;
	}

	public String getPlantype() {
		return plantype;
	}

	public void setPlantype(String plantype) {
		this.plantype = plantype;
	}

	public String getBilltype() {
		return billtype;
	}

	public void setBilltype(String billtype) {
		this.billtype = billtype;
	}

	public String getTotalLimit() {
		return totalLimit;
	}

	public void setTotalLimit(String totalLimit) {
		this.totalLimit = totalLimit;
	}

	public boolean isActivateStatus() {
		return activateStatus;
	}

	public void setActivateStatus(boolean activateStatus) {
		this.activateStatus = activateStatus;
	}

	public String getPackageType() {
		return packageType;
	}

	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}

	public String getLcoComPer() {
		return lcoComPer;
	}

	public void setLcoComPer(String lcoComPer) {
		this.lcoComPer = lcoComPer;
	}

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public String getSubplanId() {
		return subplanId;
	}

	public void setSubplanId(String subplanId) {
		this.subplanId = subplanId;
	}

}
