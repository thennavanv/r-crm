package com.ridsys.rib.DTO;

public class QuotasubplanDTO {

	private int subid;
	private String subname;
	private boolean substatus;

	public QuotasubplanDTO(int subid,String subname, boolean substatus) {
		super();
		this.subid = subid;
		this.subname = subname;
		this.substatus = substatus;
	}

	public int getSubid() {
		return subid;
	}

	public void setSubid(int subid) {
		this.subid = subid;
	}

	public String getSubname() {
		return subname;
	}

	public void setSubname(String subname) {
		this.subname = subname;
	}

	public boolean isSubstatus() {
		return substatus;
	}

	public void setSubstatus(boolean substatus) {
		this.substatus = substatus;
	}

}
