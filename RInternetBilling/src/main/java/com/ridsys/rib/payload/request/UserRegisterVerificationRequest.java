package com.ridsys.rib.payload.request;

public class UserRegisterVerificationRequest {

	private CreateuserRequest userinfo;
	private SendSmsRequest smsinfo;

	public CreateuserRequest getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(CreateuserRequest userinfo) {
		this.userinfo = userinfo;
	}

	public SendSmsRequest getSmsinfo() {
		return smsinfo;
	}

	public void setSmsinfo(SendSmsRequest smsinfo) {
		this.smsinfo = smsinfo;
	}

}
