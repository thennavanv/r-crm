package com.ridsys.rib.DTO;

public class SubscriptionInfoDTO {

	private String activationdate;
	private String expirydate;
	private String expirydate1;
	private int quotadays;
	private int quotaremaindays;
	private String uploadbw;
	private String downloadbw;

	public SubscriptionInfoDTO(String activationdate, String expirydate, int quotadays, int quotaremaindays,
			String uploadbw, String downloadbw) {
		super();
		this.activationdate = activationdate;
		this.expirydate = expirydate;
		this.quotadays = quotadays;
		this.quotaremaindays = quotaremaindays;
		this.uploadbw = uploadbw;
		this.downloadbw = downloadbw;
	}

	public SubscriptionInfoDTO(String activationdate, String expirydate, String expirydate1, int quotadays,
			int quotaremaindays) {
		super();
		this.activationdate = activationdate;
		this.expirydate = expirydate;
		this.expirydate1 = expirydate1;
		this.quotadays = quotadays;
		this.quotaremaindays = quotaremaindays;
	}

	public SubscriptionInfoDTO(String uploadbw, String downloadbw) {
		super();
		this.uploadbw = uploadbw;
		this.downloadbw = downloadbw;
	}

	public SubscriptionInfoDTO() {
		super();
	}

	public String getActivationdate() {
		return activationdate;
	}

	public void setActivationdate(String activationdate) {
		this.activationdate = activationdate;
	}

	public String getExpirydate() {
		return expirydate;
	}

	public void setExpirydate(String expirydate) {
		this.expirydate = expirydate;
	}

	public int getQuotadays() {
		return quotadays;
	}

	public String getExpirydate1() {
		return expirydate1;
	}

	public void setExpirydate1(String expirydate1) {
		this.expirydate1 = expirydate1;
	}

	public void setQuotadays(int quotadays) {
		this.quotadays = quotadays;
	}

	public int getQuotaremaindays() {
		return quotaremaindays;
	}

	public void setQuotaremaindays(int quotaremaindays) {
		this.quotaremaindays = quotaremaindays;
	}

	public String getUploadbw() {
		return uploadbw;
	}

	public void setUploadbw(String uploadbw) {
		this.uploadbw = uploadbw;
	}

	public String getDownloadbw() {
		return downloadbw;
	}

	public void setDownloadbw(String downloadbw) {
		this.downloadbw = downloadbw;
	}

}
