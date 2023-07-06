package com.ridsys.rib.DTO;

import java.util.List;

public class QuotaplanDTO {

	private int id;
	private String name;
	private boolean status;
	private List<QuotasubplanDTO> subplanList;
	
	

	public QuotaplanDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QuotaplanDTO(int id, String name, boolean status) {
		super();
		this.id = id;
		this.name = name;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public List<QuotasubplanDTO> getSubplanList() {
		return subplanList;
	}

	public void setSubplanList(List<QuotasubplanDTO> subplanList) {
		this.subplanList = subplanList;
	}

}
