package com.ridsys.rib.DTO;

public class UserStatusDTO {
	private String username;
	private String status;
	
	
	

	public UserStatusDTO(String username, String status) {
		super();
		this.username = username;
		this.status = status;
	}

	public UserStatusDTO() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

	

}
