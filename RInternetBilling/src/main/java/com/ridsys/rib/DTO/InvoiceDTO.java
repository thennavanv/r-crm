package com.ridsys.rib.DTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InvoiceDTO {

	private String title;
	private String subTitle;
	private String clientname;
	private Map<String, String> providerInfo;
	private Map<String, String> userInfo;
	private Map<String, String> userBillInfo;
	private String previousDue;
	private String paymentsReceived;
	private String adjustments;
	private String invoiceAmount;
	private String balanceAmount;
	private String pocketAmountBalance;
	private String accountNo;
	private String userId;
	private Map<Object, Object> invoiceCharges;
	private Map<Object, Object> paymentReceived;
	private List<String> termsAndConditions = new ArrayList<>();
	private String registeredOfficeAddress;
	private String gstin;

	public InvoiceDTO() {
		super();
		termsAndConditions.add("All disputes are subject to PUDUCHERRY jurisdiction.");
		termsAndConditions.add("Unless otherwise stated, tax on this invoice is not payable under reverse charge.");
		termsAndConditions.add("This Invoice is system generated hence signature and stamp is not required.");
	}
	
	

	public String getClientname() {
		return clientname;
	}



	public void setClientname(String clientname) {
		this.clientname = clientname;
	}



	public String getGstin() {
		return gstin;
	}

	public void setGstin(String gstin) {
		this.gstin = gstin;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public Map<String, String> getProviderInfo() {
		return providerInfo;
	}

	public void setProviderInfo(String company,String clientname, String address, String phone, String email, String gstIn) {
		Map<String, String> providerInfo = new HashMap<>();
		providerInfo.put("company", company);
		providerInfo.put("clientname", clientname);
		providerInfo.put("address", address);
		providerInfo.put("phone", phone);
		providerInfo.put("email", email);
		providerInfo.put("gstIn", gstIn);
		this.providerInfo = providerInfo;
	}
	
	

	public Map<String, String> getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(String name, String address, String zipCode, String mobile, String gstIn) {
		Map<String, String> userInfo = new HashMap<>();
		userInfo.put("name", name);
		userInfo.put("address", address);
		userInfo.put("zipCode", zipCode);
		userInfo.put("mobile", mobile);
		userInfo.put("gstIn", gstIn);
		this.userInfo = userInfo;
	}

	public Map<String, String> getUserBillInfo() {
		return userBillInfo;
	}

	public void setUserBillInfo(String userId, String accountNo, String invoiceNo, String invoiceDate,
			String invoicePeriod, String dueDate) {
		Map<String, String> userBillInfo = new HashMap<>();
		userBillInfo.put("userId", userId);
		userBillInfo.put("accountNo", accountNo);
		userBillInfo.put("invoiceNo", invoiceNo);
		userBillInfo.put("invoiceDate", invoiceDate);
		userBillInfo.put("invoicePeriod", invoicePeriod);
		userBillInfo.put("dueDate", dueDate);
		this.userBillInfo = userBillInfo;
	}

	public String getPreviousDue() {
		return previousDue;
	}

	public void setPreviousDue(String previousDue) {
		this.previousDue = previousDue;
	}

	public String getPaymentsReceived() {
		return paymentsReceived;
	}

	public void setPaymentsReceived(String paymentsReceived) {
		this.paymentsReceived = paymentsReceived;
	}

	public String getAdjustments() {
		return adjustments;
	}

	public void setAdjustments(String adjustments) {
		this.adjustments = adjustments;
	}

	public String getInvoiceAmount() {
		return invoiceAmount;
	}

	public void setInvoiceAmount(String invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	public String getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(String balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public String getPocketAmountBalance() {
		return pocketAmountBalance;
	}

	public void setPocketAmountBalance(String pocketAmountBalance) {
		this.pocketAmountBalance = pocketAmountBalance;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Map<Object, Object> getInvoiceCharges() {
		return invoiceCharges;
	}

	public void setInvoiceCharges(String totalTaxableAmount, String totalCGSTAmount, String totalSGSTAmount,
			String amountInclTax, List<InvoiceChargesList> invoiceChargesList) {
		Map<Object, Object> invoiceCharges = new HashMap<>();
		invoiceCharges.put("totalTaxableAmount", totalTaxableAmount);
		invoiceCharges.put("totalCGSTAmount", totalCGSTAmount);
		invoiceCharges.put("totalSGSTAmount", totalSGSTAmount);
		invoiceCharges.put("amountInclTax", amountInclTax);
		invoiceCharges.put("invoiceChargesList", invoiceChargesList);
		this.invoiceCharges = invoiceCharges;
	}
	
	public void setInvoiceCharges(String totalTaxableAmount, String totalCGSTAmount, String totalSGSTAmount,
			String amountInclTax,String discountCost, List<InvoiceChargesList> invoiceChargesList) {
		Map<Object, Object> invoiceCharges = new HashMap<>();
		invoiceCharges.put("totalTaxableAmount", totalTaxableAmount);
		invoiceCharges.put("totalCGSTAmount", totalCGSTAmount);
		invoiceCharges.put("totalSGSTAmount", totalSGSTAmount);
		invoiceCharges.put("amountInclTax", amountInclTax);
		invoiceCharges.put("discountCost", discountCost);
		invoiceCharges.put("invoiceChargesList", invoiceChargesList);
		this.invoiceCharges = invoiceCharges;
	}

	public Map<Object, Object> getPaymentReceived() {
		return paymentReceived;
	}

	public void setPaymentReceived(String payments, String totalPayments, Map<String, String> object) {
		Map<Object, Object> paymentReceived = new HashMap<>();
		paymentReceived.put("payments", payments);
		paymentReceived.put("totalPayments", totalPayments);
		paymentReceived.put("object", object);
		this.paymentReceived = paymentReceived;
	}

	public List<String> getTermsAndConditions() {
		return termsAndConditions;
	}

	public void setTermsAndConditions(List<String> termsAndConditions) {
		this.termsAndConditions = termsAndConditions;
	}

	public String getRegisteredOfficeAddress() {
		return registeredOfficeAddress;
	}

	public void setRegisteredOfficeAddress(String registeredOfficeAddress) {
		this.registeredOfficeAddress = registeredOfficeAddress;
	}

}
