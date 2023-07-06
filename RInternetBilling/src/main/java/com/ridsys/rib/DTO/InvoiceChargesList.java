package com.ridsys.rib.DTO;

public class InvoiceChargesList {

	private String txnNo;
	private String txnDate;
	private String periodDate;
	private String description;
	private String hsnCode;
	private String planDescription;
	private String rate;
	private String unitPeriod;
	private String taxableAmount;
	private String cgstRateInPct;
	private String cgstAmount;
	private String sgstRateInPct;
	private String sgstAmount;
	private String amountInclTax;
	private String discountCost;

	public String getDiscountCost() {
		return discountCost;
	}

	public void setDiscountCost(String discountCost) {
		this.discountCost = discountCost;
	}

	public String getTxnNo() {
		return txnNo;
	}

	public void setTxnNo(String txnNo) {
		this.txnNo = txnNo;
	}

	public String getTxnDate() {
		return txnDate;
	}

	public void setTxnDate(String txnDate) {
		this.txnDate = txnDate;
	}

	public String getPeriodDate() {
		return periodDate;
	}

	public void setPeriodDate(String periodDate) {
		this.periodDate = periodDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHsnCode() {
		return hsnCode;
	}

	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
	}

	public String getPlanDescription() {
		return planDescription;
	}

	public void setPlanDescription(String planDescription) {
		this.planDescription = planDescription;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getUnitPeriod() {
		return unitPeriod;
	}

	public void setUnitPeriod(String unitPeriod) {
		this.unitPeriod = unitPeriod;
	}

	public String getTaxableAmount() {
		return taxableAmount;
	}

	public void setTaxableAmount(String taxableAmount) {
		this.taxableAmount = taxableAmount;
	}

	public String getCgstRateInPct() {
		return cgstRateInPct;
	}

	public void setCgstRateInPct(String cgstRateInPct) {
		this.cgstRateInPct = cgstRateInPct;
	}

	public String getCgstAmount() {
		return cgstAmount;
	}

	public void setCgstAmount(String cgstAmount) {
		this.cgstAmount = cgstAmount;
	}

	public String getSgstRateInPct() {
		return sgstRateInPct;
	}

	public void setSgstRateInPct(String sgstRateInPct) {
		this.sgstRateInPct = sgstRateInPct;
	}

	public String getSgstAmount() {
		return sgstAmount;
	}

	public void setSgstAmount(String sgstAmount) {
		this.sgstAmount = sgstAmount;
	}

	public String getAmountInclTax() {
		return amountInclTax;
	}

	public void setAmountInclTax(String amountInclTax) {
		this.amountInclTax = amountInclTax;
	}

}
