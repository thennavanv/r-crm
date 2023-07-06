package com.ridsys.rib.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CreatePlanRequest {

	@NotBlank(message = "planname may not be Blank")
	@Size(min = 3, max = 50, message = "Planname must be between 3 and 50 characters long")
	private String planname;

	@NotBlank(message = "billtype may not be Blank")
	private String billtype;

	@NotBlank(message = "plantype may not be Blank")
	private String plantype;

	private boolean status;
	private int uploadspeed;
	private int downloadspeed;
	private int uploadlimit;
	private int downloadlimit;
	private int totallimit;
	private int validitydays;
	private int discountdays;
	private double plancost;
	private double tax;
	private double operatorcommission;
	private double planMrp;
	private double planDiscount;
	private double planCusCost;
	private double planMsoCost;
	private String currency;
	private String lco_commission_per;

	public int getDiscountdays() {
		return discountdays;
	}

	public void setDiscountdays(int discountdays) {
		this.discountdays = discountdays;
	}

	public double getPlanMrp() {
		return planMrp;
	}

	public void setPlanMrp(double planMrp) {
		this.planMrp = planMrp;
	}

	public double getPlanDiscount() {
		return planDiscount;
	}

	public void setPlanDiscount(double planDiscount) {
		this.planDiscount = planDiscount;
	}

	public double getPlanCusCost() {
		return planCusCost;
	}

	public void setPlanCusCost(double planCusCost) {
		this.planCusCost = planCusCost;
	}

	public double getPlanMsoCost() {
		return planMsoCost;
	}

	public void setPlanMsoCost(double planMsoCost) {
		this.planMsoCost = planMsoCost;
	}

	public String getLco_commission_per() {
		return lco_commission_per;
	}

	public void setLco_commission_per(String lco_commission_per) {
		this.lco_commission_per = lco_commission_per;
	}

	public String getPlanname() {
		return planname;
	}

	public void setPlanname(String planname) {
		this.planname = planname;
	}

	public String getBilltype() {
		return billtype;
	}

	public void setBilltype(String billtype) {
		this.billtype = billtype;
	}

	public String getPlantype() {
		return plantype;
	}

	public void setPlantype(String plantype) {
		this.plantype = plantype;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getUploadspeed() {
		return uploadspeed;
	}

	public void setUploadspeed(int uploadspeed) {
		this.uploadspeed = uploadspeed;
	}

	public int getDownloadspeed() {
		return downloadspeed;
	}

	public void setDownloadspeed(int downloadspeed) {
		this.downloadspeed = downloadspeed;
	}

	public int getUploadlimit() {
		return uploadlimit;
	}

	public void setUploadlimit(int uploadlimit) {
		this.uploadlimit = uploadlimit;
	}

	public int getDownloadlimit() {
		return downloadlimit;
	}

	public void setDownloadlimit(int downloadlimit) {
		this.downloadlimit = downloadlimit;
	}

	public int getTotallimit() {
		return totallimit;
	}

	public void setTotallimit(int totallimit) {
		this.totallimit = totallimit;
	}

	public int getValiditydays() {
		return validitydays;
	}

	public void setValiditydays(int validitydays) {
		this.validitydays = validitydays;
	}

	public double getPlancost() {
		return plancost;
	}

	public void setPlancost(double plancost) {
		this.plancost = plancost;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	public double getOperatorcommission() {
		return operatorcommission;
	}

	public void setOperatorcommission(double operatorcommission) {
		this.operatorcommission = operatorcommission;
	}

}
