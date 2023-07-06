package com.ridsys.rib.payload.request;

import javax.validation.constraints.NotBlank;

public class SendSmsRequest {

	@NotBlank
	private String senderId;
	
	@NotBlank
	private String is_Unicode;
	
	@NotBlank
	private String is_Flash;
	
	private String schedTime;
	private String groupId;
	
	@NotBlank
	private String message;
	
	@NotBlank
	private String mobileNumbers;
	
	private String serviceId;
	private String coRelator;
	private String linkId;
	
	@NotBlank
	private String apiKey;
	
	@NotBlank
	private String clientId;
	
	public String getSenderId() {
		return senderId;
	}
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	public String getIs_Unicode() {
		return is_Unicode;
	}
	public void setIs_Unicode(String is_Unicode) {
		this.is_Unicode = is_Unicode;
	}
	public String getIs_Flash() {
		return is_Flash;
	}
	public void setIs_Flash(String is_Flash) {
		this.is_Flash = is_Flash;
	}
	public String getSchedTime() {
		return schedTime;
	}
	public void setSchedTime(String schedTime) {
		this.schedTime = schedTime;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMobileNumbers() {
		return mobileNumbers;
	}
	public void setMobileNumbers(String mobileNumbers) {
		this.mobileNumbers = mobileNumbers;
	}
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public String getCoRelator() {
		return coRelator;
	}
	public void setCoRelator(String coRelator) {
		this.coRelator = coRelator;
	}
	public String getLinkId() {
		return linkId;
	}
	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}
	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	

}
