package com.ridsys.rib.DTO;

public class PlanListDTO {
	private int id;
	private String planname;
	private String planid;
	private String plantype;
	private String plantimebank;
	private String plantimetype;
	private String plantimerefillcost;	
	private String planbandwidthup;
	private String planbandwidthdown;
	private String plantraffictotal;
	private String plantrafficup;
	private String plantrafficdown;
	private String plantrafficrefillcost;
	private String planrecurring;
	private String planrecurringperiod;
	private String planrecurringbillingschedule;
	private String plancost;
	private String plansetupcost;
	private String plantax;
	private String planlcocomm;
	private String plancurrency;
	private String plangroup;
	private String planactive;
	private String creationdate;
	private String creationby;
	private String updatedate;
	private String updateby;
	
	public PlanListDTO(int id, String planname, String planid, String plantype, String plantimebank,
			String plantimetype, String plantimerefillcost, String planbandwidthup, String planbandwidthdown,
			String plantraffictotal, String plantrafficup, String plantrafficdown, String plantrafficrefillcost,
			String planrecurring, String planrecurringperiod, String planrecurringbillingschedule, String plancost,
			String plansetupcost, String plantax, String planlcocomm, String plancurrency, String plangroup,
			String planactive, String creationdate, String creationby, String updatedate, String updateby) {
		super();
		this.id = id;
		this.planname = planname;
		this.planid = planid;
		this.plantype = plantype;
		this.plantimebank = plantimebank;
		this.plantimetype = plantimetype;
		this.plantimerefillcost = plantimerefillcost;
		this.planbandwidthup = planbandwidthup+" Mbps";
		this.planbandwidthdown = planbandwidthdown+" Mbps";
		this.plantraffictotal = plantraffictotal;
		this.plantrafficup = plantrafficup;
		this.plantrafficdown = plantrafficdown;
		this.plantrafficrefillcost = plantrafficrefillcost;
		this.planrecurring = planrecurring;
		this.planrecurringperiod = planrecurringperiod;
		this.planrecurringbillingschedule = planrecurringbillingschedule;
		this.plancost = plancost;
		this.plansetupcost = plansetupcost;
		this.plantax = plantax;
		this.planlcocomm = planlcocomm;
		this.plancurrency = plancurrency;
		this.plangroup = plangroup;
		this.planactive = planactive;
		this.creationdate = creationdate;
		this.creationby = creationby;
		this.updatedate = updatedate;
		this.updateby = updateby;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPlanname() {
		return planname;
	}

	public void setPlanname(String planname) {
		this.planname = planname;
	}

	public String getPlanid() {
		return planid;
	}

	public void setPlanid(String planid) {
		this.planid = planid;
	}

	public String getPlantype() {
		return plantype;
	}

	public void setPlantype(String plantype) {
		this.plantype = plantype;
	}

	public String getPlantimebank() {
		return plantimebank;
	}

	public void setPlantimebank(String plantimebank) {
		this.plantimebank = plantimebank;
	}

	public String getPlantimetype() {
		return plantimetype;
	}

	public void setPlantimetype(String plantimetype) {
		this.plantimetype = plantimetype;
	}

	public String getPlantimerefillcost() {
		return plantimerefillcost;
	}

	public void setPlantimerefillcost(String plantimerefillcost) {
		this.plantimerefillcost = plantimerefillcost;
	}

	public String getPlanbandwidthup() {
		return planbandwidthup;
	}

	public void setPlanbandwidthup(String planbandwidthup) {
		this.planbandwidthup = planbandwidthup;
	}

	public String getPlanbandwidthdown() {
		return planbandwidthdown;
	}

	public void setPlanbandwidthdown(String planbandwidthdown) {
		this.planbandwidthdown = planbandwidthdown;
	}

	public String getPlantraffictotal() {
		return plantraffictotal;
	}

	public void setPlantraffictotal(String plantraffictotal) {
		this.plantraffictotal = plantraffictotal;
	}

	public String getPlantrafficup() {
		return plantrafficup;
	}

	public void setPlantrafficup(String plantrafficup) {
		this.plantrafficup = plantrafficup;
	}

	public String getPlantrafficdown() {
		return plantrafficdown;
	}

	public void setPlantrafficdown(String plantrafficdown) {
		this.plantrafficdown = plantrafficdown;
	}

	public String getPlantrafficrefillcost() {
		return plantrafficrefillcost;
	}

	public void setPlantrafficrefillcost(String plantrafficrefillcost) {
		this.plantrafficrefillcost = plantrafficrefillcost;
	}

	public String getPlanrecurring() {
		return planrecurring;
	}

	public void setPlanrecurring(String planrecurring) {
		this.planrecurring = planrecurring;
	}

	public String getPlanrecurringperiod() {
		return planrecurringperiod;
	}

	public void setPlanrecurringperiod(String planrecurringperiod) {
		this.planrecurringperiod = planrecurringperiod;
	}

	public String getPlanrecurringbillingschedule() {
		return planrecurringbillingschedule;
	}

	public void setPlanrecurringbillingschedule(String planrecurringbillingschedule) {
		this.planrecurringbillingschedule = planrecurringbillingschedule;
	}

	public String getPlancost() {
		return plancost;
	}

	public void setPlancost(String plancost) {
		this.plancost = plancost;
	}

	public String getPlansetupcost() {
		return plansetupcost;
	}

	public void setPlansetupcost(String plansetupcost) {
		this.plansetupcost = plansetupcost;
	}

	public String getPlantax() {
		return plantax;
	}

	public void setPlantax(String plantax) {
		this.plantax = plantax;
	}

	public String getPlanlcocomm() {
		return planlcocomm;
	}

	public void setPlanlcocomm(String planlcocomm) {
		this.planlcocomm = planlcocomm;
	}

	public String getPlancurrency() {
		return plancurrency;
	}

	public void setPlancurrency(String plancurrency) {
		this.plancurrency = plancurrency;
	}

	public String getPlangroup() {
		return plangroup;
	}

	public void setPlangroup(String plangroup) {
		this.plangroup = plangroup;
	}

	public String getPlanactive() {
		return planactive;
	}

	public void setPlanactive(String planactive) {
		this.planactive = planactive;
	}

	public String getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(String creationdate) {
		this.creationdate = creationdate;
	}

	public String getCreationby() {
		return creationby;
	}

	public void setCreationby(String creationby) {
		this.creationby = creationby;
	}

	public String getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}

	public String getUpdateby() {
		return updateby;
	}

	public void setUpdateby(String updateby) {
		this.updateby = updateby;
	}
}
