package com.ridsys.rib.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.jetbrains.annotations.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;

//@Getter
@Entity
//@NoArgsConstructor
@Table(name = "payment_gateway_master")
public class PaymentGatewayMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

//	@NotNull
//	private String gatewayname;

	@NotNull
	private String apikey;

	@NotNull
	private String salt;

	@NotNull
	private String mode;

	@NotNull
	private String merchant;

	private String extra1;
	private String extra2;
	private boolean isactive;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String created_date;

	@NotNull
	private String baseurl;

	private String successurl;
	private String faliureurl;

	@OneToOne
	@JoinColumn(name = "gatewayid")
	private Gateway gateway;

	private String updateddate;

	public PaymentGatewayMaster() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PaymentGatewayMaster(int id, String apikey, String salt, String mode, String merchant, String extra1,
			String extra2, boolean isactive, String created_date, String baseurl, String successurl, String faliureurl,
			Gateway gateway, String updateddate) {
		super();
		this.id = id;
		this.apikey = apikey;
		this.salt = salt;
		this.mode = mode;
		this.merchant = merchant;
		this.extra1 = extra1;
		this.extra2 = extra2;
		this.isactive = isactive;
		this.created_date = created_date;
		this.baseurl = baseurl;
		this.successurl = successurl;
		this.faliureurl = faliureurl;
		this.gateway = gateway;
		this.updateddate = updateddate;
	}

	public String getUpdateddate() {
		return updateddate;
	}

	public void setUpdateddate(String updateddate) {
		this.updateddate = updateddate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

//	public String getGatewayname() {
//		return gatewayname;
//	}
//
//	public void setGatewayname(String gatewayname) {
//		this.gatewayname = gatewayname;
//	}

	public String getApikey() {
		return apikey;
	}

	public void setApikey(String apikey) {
		this.apikey = apikey;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getMerchant() {
		return merchant;
	}

	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}

	public String getExtra1() {
		return extra1;
	}

	public void setExtra1(String extra1) {
		this.extra1 = extra1;
	}

	public String getExtra2() {
		return extra2;
	}

	public void setExtra2(String extra2) {
		this.extra2 = extra2;
	}

	public boolean isIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	public String getCreated_date() {
		return created_date;
	}

	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}

	public String getBaseurl() {
		return baseurl;
	}

	public void setBaseurl(String baseurl) {
		this.baseurl = baseurl;
	}

	public String getSuccessurl() {
		return successurl;
	}

	public void setSuccessurl(String successurl) {
		this.successurl = successurl;
	}

	public String getFaliureurl() {
		return faliureurl;
	}

	public void setFaliureurl(String faliureurl) {
		this.faliureurl = faliureurl;
	}

	public Gateway getGateway() {
		return gateway;
	}

	public void setGateway(Gateway gateway) {
		this.gateway = gateway;
	}

}