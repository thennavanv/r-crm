package com.ridsys.rib.DTO;

public class OnlineOfflineDTO {

	private double offline;
	private double lcoOnline;
	private double subOnline;

	public OnlineOfflineDTO() {
		super();
	}

	public OnlineOfflineDTO(double offline, double lcoOnline, double subOnline) {
		super();
		this.offline = offline;
		this.lcoOnline = lcoOnline;
		this.subOnline = subOnline;
	}

	public double getOffline() {
		return offline;
	}

	public void setOffline(double offline) {
		this.offline = offline;
	}

	public double getLcoOnline() {
		return lcoOnline;
	}

	public void setLcoOnline(double lcoOnline) {
		this.lcoOnline = lcoOnline;
	}

	public double getSubOnline() {
		return subOnline;
	}

	public void setSubOnline(double subOnline) {
		this.subOnline = subOnline;
	}

}
