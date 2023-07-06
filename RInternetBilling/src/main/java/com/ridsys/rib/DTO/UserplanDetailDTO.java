package com.ridsys.rib.DTO;

public class UserplanDetailDTO {

	private int planid;
	private String planName;
	private String planType;
	private String billType;
	private String bandwidthSpeed;
	private String planCost;
	private String mrp;
	private String discount;
	private String cusCost;
	private String expiryDate;
	private String tax;
	private int quotadays;
	private String startDate;
	private String taxPercentage;
	private String discountDays;
	private boolean isactive;
	private String creationby;
	private String creationdate;
	private boolean planActive;
	private String packageType;
	private String planDays;
	private String sdate;
	private String edate;

	public UserplanDetailDTO(int planid, String planName, String planType, String billType, String bandwidthSpeed,
			String planCost, String mrp, String discount, String cusCost, String expiryDate, String tax, int quotadays,
			String startDate, String taxPercentage, String discountDays, boolean isactive, String creationby,
			String creationdate, boolean planActive, String packageType, String planDays, String sdate, String edate) {
		super();
		this.planid = planid;
		this.planName = planName;
		this.planType = planType;
		this.billType = billType;
		this.bandwidthSpeed = bandwidthSpeed;
		this.planCost = planCost;
		this.mrp = mrp;
		this.discount = discount;
		this.cusCost = cusCost;
		this.expiryDate = expiryDate;
		this.tax = tax;
		this.quotadays = quotadays;
		this.startDate = startDate;
		this.taxPercentage = taxPercentage;
		this.discountDays = discountDays;
		this.isactive = isactive;
		this.creationby = creationby;
		this.creationdate = creationdate;
		this.planActive = planActive;
		this.packageType = packageType;
		this.planDays = planDays;
		this.sdate = sdate;
		this.edate = edate;
	}

	public UserplanDetailDTO(int planid, String planName, String planType, String billType, String bandwidthSpeed,
			String planCost, String mrp, String discount, String cusCost, String tax, String taxPercentage,
			String discountDays, boolean isactive, String creationby, String creationdate, String packageType,
			String planDays) {
		super();
		this.planid = planid;
		this.planName = planName;
		this.planType = planType;
		this.billType = billType;
		this.bandwidthSpeed = bandwidthSpeed;
		this.planCost = planCost;
		this.mrp = mrp;
		this.discount = discount;
		this.cusCost = cusCost;
		this.tax = tax;
		this.taxPercentage = taxPercentage;
		this.discountDays = discountDays;
		this.isactive = isactive;
		this.creationby = creationby;
		this.creationdate = creationdate;
		this.packageType = packageType;
		this.planDays = planDays;
	}

	public int getPlanid() {
		return planid;
	}

	public void setPlanid(int planid) {
		this.planid = planid;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public String getPlanType() {
		return planType;
	}

	public void setPlanType(String planType) {
		this.planType = planType;
	}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public String getBandwidthSpeed() {
		return bandwidthSpeed;
	}

	public void setBandwidthSpeed(String bandwidthSpeed) {
		this.bandwidthSpeed = bandwidthSpeed;
	}

	public String getPlanCost() {
		return planCost;
	}

	public void setPlanCost(String planCost) {
		this.planCost = planCost;
	}

	public String getMrp() {
		return mrp;
	}

	public void setMrp(String mrp) {
		this.mrp = mrp;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getCusCost() {
		return cusCost;
	}

	public void setCusCost(String cusCost) {
		this.cusCost = cusCost;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	public int getQuotadays() {
		return quotadays;
	}

	public void setQuotadays(int quotadays) {
		this.quotadays = quotadays;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getTaxPercentage() {
		return taxPercentage;
	}

	public void setTaxPercentage(String taxPercentage) {
		this.taxPercentage = taxPercentage;
	}

	public String getDiscountDays() {
		return discountDays;
	}

	public void setDiscountDays(String discountDays) {
		this.discountDays = discountDays;
	}

	public boolean isIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	public String getCreationby() {
		return creationby;
	}

	public void setCreationby(String creationby) {
		this.creationby = creationby;
	}

	public String getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(String creationdate) {
		this.creationdate = creationdate;
	}

	public boolean isPlanActive() {
		return planActive;
	}

	public void setPlanActive(boolean planActive) {
		this.planActive = planActive;
	}

	public String getPackageType() {
		return packageType;
	}

	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}

	public String getPlanDays() {
		return planDays;
	}

	public void setPlanDays(String planDays) {
		this.planDays = planDays;
	}

	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	public String getEdate() {
		return edate;
	}

	public void setEdate(String edate) {
		this.edate = edate;
	}

}
