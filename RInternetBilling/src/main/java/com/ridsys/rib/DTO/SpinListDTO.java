package com.ridsys.rib.DTO;

public class SpinListDTO {
	private int id;
	private String username;
	private String name;

	public SpinListDTO(int id, String username, String name) {
		super();
		this.id = id;
		this.username = username;
		this.name = name.toUpperCase();
	}

	public SpinListDTO(int id, String name) {
		super();
		this.id = id;
		this.name = name.toUpperCase();
	}

	public SpinListDTO(String username, String name) {
		super();
		this.username = username;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
