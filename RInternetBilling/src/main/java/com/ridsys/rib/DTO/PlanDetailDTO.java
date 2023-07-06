package com.ridsys.rib.DTO;

public class PlanDetailDTO {

	private String Name;
	private int active;
	private int deactive;

	public PlanDetailDTO(String name, int active, int deactive) {
		super();
		Name = name;
		this.active = active;
		this.deactive = deactive;
	}

	public PlanDetailDTO() {
		super();
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getActive() {
		return active;
	}	

	public void setActive(int active) {
		this.active = active;
	}

	public int getDeactive() {
		return deactive;
	}

	public void setDeactive(int deactive) {
		this.deactive = deactive;
	}

}
