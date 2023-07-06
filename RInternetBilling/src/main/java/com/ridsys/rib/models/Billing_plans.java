package com.ridsys.rib.models;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ridsys.rib.DTO.PlanCostDTO;

@NamedNativeQuery(name = "Billing_plans.findCostDetailsById", query = "SELECT id,planName,planCost,planTax,planMrp,planDiscount,planCusCost,planLcoComm,planMsoCost,planTimeBank FROM billing_plans WHERE id=:id", resultSetMapping = "Mapping.PlanCostDTO")
@NamedNativeQuery(name = "Billing_plans.findAllActivePlansWithCostDetails", query = "SELECT id,planName,planCost,planTax,planMrp,planDiscount,planCusCost,planLcoComm,planMsoCost,planTimeBank FROM billing_plans WHERE isdeleted=0", resultSetMapping = "Mapping.PlanCostDTO")
@SqlResultSetMapping(name = "Mapping.PlanCostDTO", classes = @ConstructorResult(targetClass = PlanCostDTO.class, columns = {
		@ColumnResult(name = "id"), @ColumnResult(name = "planName"),
		@ColumnResult(name = "planCost", type = double.class), @ColumnResult(name = "planTax", type = double.class),
		@ColumnResult(name = "planMrp", type = double.class), @ColumnResult(name = "planDiscount", type = double.class),
		@ColumnResult(name = "planCusCost", type = double.class),
		@ColumnResult(name = "planLcoComm", type = double.class),
		@ColumnResult(name = "planMsoCost", type = double.class),
		@ColumnResult(name = "planTimeBank", type = Integer.class) }))
@Entity
@Table(name = "billing_plans")
public class Billing_plans {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	private String lco_commission_per;
	private String planmrp;
	private String plandiscount;
	private String plancuscost;
	private String planmsocost;
	private int plandiscountdays;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String creationdate;

	private String creationby;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String updatedate;

	private String updateby;
	private boolean isdeleted;
	private boolean isactive;

	public Billing_plans() {
	}

	public Billing_plans(String planname, String planid, String plantype, String plantimebank, String plantimetype,
			String plantimerefillcost, String planbandwidthup, String planbandwidthdown, String plantraffictotal,
			String plantrafficup, String plantrafficdown, String plantrafficrefillcost, String planrecurring,
			String planrecurringperiod, String planrecurringbillingschedule, String plancost, String plansetupcost,
			String plantax, String planlcocomm, String plancurrency, String plangroup, String planactive,
			String creationdate, String creationby, String updatedate, String updateby, boolean isdeleted,
			boolean isactive, String lco_commission_per, String planMrp, String planDiscount, String planCusCost,
			String planMsoCost, int plandiscountdays) {
		super();
		this.planname = planname;
		this.planid = planid;
		this.plantype = plantype;
		this.plantimebank = plantimebank;
		this.plantimetype = plantimetype;
		this.plantimerefillcost = plantimerefillcost;
		this.planbandwidthup = planbandwidthup;
		this.planbandwidthdown = planbandwidthdown;
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
		this.isdeleted = isdeleted;
		this.isactive = isactive;
		this.lco_commission_per = lco_commission_per;
		this.planmrp = planMrp;
		this.plandiscount = planDiscount;
		this.plancuscost = planCusCost;
		this.planmsocost = planMsoCost;
		this.plandiscountdays = plandiscountdays;
	}

	public int getPlandiscountdays() {
		return plandiscountdays;
	}

	public void setPlandiscountdays(int plandiscountdays) {
		this.plandiscountdays = plandiscountdays;
	}

	public String getLco_commission_per() {
		return lco_commission_per;
	}

	public void setLco_commission_per(String lco_commission_per) {
		this.lco_commission_per = lco_commission_per;
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
		return planbandwidthup + " Mbps";
//		return planbandwidthup + " Kbps";
	}

	public void setPlanbandwidthup(String planbandwidthup) {
		this.planbandwidthup = planbandwidthup;
	}

	public String getPlanbandwidthdown() {
		return planbandwidthdown + " Mbps";
//		return planbandwidthdown + " Kbps";
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

	public boolean isIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(boolean isdeleted) {
		this.isdeleted = isdeleted;
	}

	public boolean isIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	public String getPlanmrp() {
		return planmrp;
	}

	public void setPlanmrp(String planmrp) {
		this.planmrp = planmrp;
	}

	public String getPlandiscount() {
		return plandiscount;
	}

	public void setPlandiscount(String plandiscount) {
		this.plandiscount = plandiscount;
	}

	public String getPlancuscost() {
		return plancuscost;
	}

	public void setPlancuscost(String plancuscost) {
		this.plancuscost = plancuscost;
	}

	public String getPlanmsocost() {
		return planmsocost;
	}

	public void setPlanmsocost(String planmsocost) {
		this.planmsocost = planmsocost;
	}

}
