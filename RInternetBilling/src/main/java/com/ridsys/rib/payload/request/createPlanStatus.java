package com.ridsys.rib.payload.request;

public class createPlanStatus {

	private int planid;
	private boolean status;

	public createPlanStatus() {
		super();
		// TODO Auto-generated constructor stub
	}

	public createPlanStatus(int planid, boolean status) {
		super();
		this.planid = planid;
		this.status = status;
	}

	public int getPlanid() {
		return planid;
	}

	public void setPlanid(int planid) {
		this.planid = planid;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
