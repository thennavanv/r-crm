package com.ridsys.rib.DTO;

public class OttuserDTO {

	private int id;
	private String profilepic;
	private String username;
	private String mobilephone;
	private String email;

	public OttuserDTO(int id, String profilepic, String username, String mobilephone, String email) {
		super();
		this.id = id;
		this.profilepic = profilepic;
		this.username = username;
		this.mobilephone = mobilephone;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProfilepic() {
		return profilepic;
	}

	public void setProfilepic(String profilepic) {
		this.profilepic = profilepic;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMobilephone() {
		return mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
