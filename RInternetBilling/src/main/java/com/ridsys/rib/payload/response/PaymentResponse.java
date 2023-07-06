package com.ridsys.rib.payload.response;

public class PaymentResponse {

	private String hash;
	private String api_key;
	private String return_url;
	private String mode;
	private String order_id;
	private String amount;
	private String currency;
	private String description;
	private String name;
	private String email;
	private String phone;
	private String address_line_1;
	private String address_line_2;
	private String city;
	private String country;
	private String state;
	private String zip_code;

	public PaymentResponse(String hash, String api_key, String return_url, String mode, String order_id, String amount,
			String currency, String description, String name, String email, String phone, String address_line_1,
			String address_line_2, String city, String country, String state, String zip_code) {
		super();
		this.hash = hash;
		this.api_key = api_key;
		this.return_url = return_url;
		this.mode = mode;
		this.order_id = order_id;
		this.amount = amount;
		this.currency = currency;
		this.description = description;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address_line_1 = address_line_1;
		this.address_line_2 = address_line_2;
		this.city = city;
		this.country = country;
		this.state = state;
		this.zip_code = zip_code;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getApi_key() {
		return api_key;
	}

	public void setApi_key(String api_key) {
		this.api_key = api_key;
	}

	public String getReturn_url() {
		return return_url;
	}

	public void setReturn_url(String return_url) {
		this.return_url = return_url;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress_line_1() {
		return address_line_1;
	}

	public void setAddress_line_1(String address_line_1) {
		this.address_line_1 = address_line_1;
	}

	public String getAddress_line_2() {
		return address_line_2;
	}

	public void setAddress_line_2(String address_line_2) {
		this.address_line_2 = address_line_2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip_code() {
		return zip_code;
	}

	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}

}
