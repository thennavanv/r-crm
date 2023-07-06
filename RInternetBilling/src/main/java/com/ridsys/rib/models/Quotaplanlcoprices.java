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
import com.ridsys.rib.DTO.UserplanDetailDTO;

@Entity
@Table(name = "quotaplanlcoprices")

@NamedNativeQuery(name = "Quotaplanlcoprices.getUserPlanList", query = "SELECT substring(ubi.quotastartdate,1,10) as sdate,substring(ubi.quotaexpirydate,1,10) as edate,qsp.id,qsp.subplan,qp.planType,qp.billType,qp.bandwidthup AS bandwidthSpeed,qsp.price,qsp.mrp,lcp.planDiscount,lcp.planCusCost,qsp.taxCost,date_format(substring(ubi.quotaexpirydate,1,10),'%d-%M-%Y') as quotaexpirydate,DATEDIFF(ubi.quotaexpirydate, curdate()) AS quotadays,date_format(substring(ubi.quotastartdate,1,10),'%d-%M-%Y') as quotastartdate,qsp.tax,lcp.planDiscountDays,lcp.isactive,'admin' as creationby,substring(lcp.creationdate,1,10) as creationdate,if(ubi.quotaexpirydate>now(),true,false) as planActive,qp.packageType,qsp.planTimeBank FROM quotasubplan qsp left join quotaplanlcoprices lcp ON lcp.subplanId=qsp.id left join quotaplan qp ON qp.id=qsp.planId left join userbillinfo ubi ON ubi.planid=qsp.id  WHERE  ubi.username=:username and lcp.opid=:opid", resultSetMapping = "Mapping.getUserPlanListRS")
@NamedNativeQuery(name = "Quotaplanlcoprices.getUserDuplicatePlanList", query = "SELECT substring(ubi.quotastartdate,1,10) as sdate,substring(ubi.quotaexpirydate,1,10) as edate,qsp.id,qsp.subplan,qp.planType,qp.billType,qp.bandwidthup AS bandwidthSpeed,qsp.price,qsp.mrp,lcp.planDiscount,lcp.planCusCost,qsp.taxCost,date_format(substring(ubi.quotaexpirydate,1,10),'%d-%M-%Y') as quotaexpirydate,DATEDIFF(ubi.quotaexpirydate, curdate()) AS quotadays,date_format(substring(ubi.quotastartdate,1,10),'%d-%M-%Y') as quotastartdate,qsp.tax,lcp.planDiscountDays,lcp.isactive,'admin' as creationby,substring(lcp.creationdate,1,10) as creationdate,if(ubi.quotaexpirydate>now(),true,false) as planActive,qp.packageType,qsp.planTimeBank FROM duplicatesubplan qsp left join quotaplanlcoprices lcp ON lcp.subplanId=qsp.subplanId AND lcp.opid=qsp.opid left join quotaplan qp ON qp.id=qsp.planId left join userbillinfo ubi ON ubi.planid=qsp.subplanId  WHERE  ubi.username=:username and lcp.opid=:opid", resultSetMapping = "Mapping.getUserPlanListRS")
@SqlResultSetMapping(name = "Mapping.getUserPlanListRS", classes = {
		@ConstructorResult(targetClass = UserplanDetailDTO.class, columns = {
				@ColumnResult(name = "id", type = Integer.class), @ColumnResult(name = "subplan"),
				@ColumnResult(name = "planType"), @ColumnResult(name = "billType"),
				@ColumnResult(name = "bandwidthSpeed", type = String.class),
				@ColumnResult(name = "price", type = String.class), @ColumnResult(name = "mrp", type = String.class),
				@ColumnResult(name = "planDiscount", type = String.class),
				@ColumnResult(name = "planCusCost", type = String.class),
				@ColumnResult(name = "quotaexpirydate", type = String.class),
				@ColumnResult(name = "taxCost", type = String.class),
				@ColumnResult(name = "quotadays", type = Integer.class),
				@ColumnResult(name = "quotastartdate", type = String.class),
				@ColumnResult(name = "tax", type = String.class),
				@ColumnResult(name = "planDiscountDays", type = String.class),
				@ColumnResult(name = "isactive", type = Boolean.class), @ColumnResult(name = "creationby"),
				@ColumnResult(name = "creationdate", type = String.class),
				@ColumnResult(name = "planActive", type = Boolean.class), @ColumnResult(name = "packageType"),
				@ColumnResult(name = "planTimeBank"), @ColumnResult(name = "sdate"), @ColumnResult(name = "edate") }) })

@NamedNativeQuery(name = "Quotaplanlcoprices.getPlanDetailsByPlanid", query = "SELECT qsp.id,qsp.subplan,qp.planType,qp.billType,qp.bandwidthup AS bandwidthSpeed,qsp.price,qsp.mrp,lcp.planDiscount,lcp.planCusCost,qsp.taxCost,qsp.tax,lcp.planDiscountDays,lcp.isactive,'admin' as creationby,substring(lcp.creationdate,1,10) as creationdate,qp.packageType,qsp.planTimeBank FROM quotasubplan qsp left join quotaplanlcoprices lcp ON lcp.subplanId=qsp.id left join quotaplan qp ON qp.id=qsp.planId  WHERE lcp.opid=:opid and qsp.id=:planid", resultSetMapping = "Mapping.getPlanDetailsByPlanidRs")
@NamedNativeQuery(name = "Quotaplanlcoprices.getDuplicatePlanDetailsByPlanid", query = "SELECT qsp.id,qsp.subplan,qp.planType,qp.billType,qp.bandwidthup AS bandwidthSpeed,qsp.price,qsp.mrp,lcp.planDiscount,lcp.planCusCost,qsp.taxCost,qsp.tax,lcp.planDiscountDays,lcp.isactive,'admin' as creationby,substring(lcp.creationdate,1,10) as creationdate,qp.packageType,qsp.planTimeBank FROM duplicatesubplan qsp left join quotaplanlcoprices lcp ON lcp.subplanId=qsp.subplanId and lcp.opid=qsp.opid left join quotaplan qp ON qp.id=qsp.planId  WHERE lcp.opid=:opid and qsp.subplanId=:planid", resultSetMapping = "Mapping.getPlanDetailsByPlanidRs")
@SqlResultSetMapping(name = "Mapping.getPlanDetailsByPlanidRs", classes = {
		@ConstructorResult(targetClass = UserplanDetailDTO.class, columns = {
				@ColumnResult(name = "id", type = Integer.class), @ColumnResult(name = "subplan"),
				@ColumnResult(name = "planType"), @ColumnResult(name = "billType"),
				@ColumnResult(name = "bandwidthSpeed", type = String.class),
				@ColumnResult(name = "price", type = String.class), @ColumnResult(name = "mrp", type = String.class),
				@ColumnResult(name = "planDiscount", type = String.class),
				@ColumnResult(name = "planCusCost", type = String.class),
				@ColumnResult(name = "taxCost", type = String.class), @ColumnResult(name = "tax", type = String.class),
				@ColumnResult(name = "planDiscountDays", type = String.class),
				@ColumnResult(name = "isactive", type = Boolean.class), @ColumnResult(name = "creationby"),
				@ColumnResult(name = "creationdate", type = String.class), @ColumnResult(name = "packageType"),
				@ColumnResult(name = "planTimeBank") }) })

public class Quotaplanlcoprices {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int subplanid;
	private int opid;
	private String plandiscountdays;
	private String plandiscount;
	private String plancuscost;
	private String planlcocom;
	private String planmsocom;
	private String planlcocompercent;
	private boolean isactive;
	private boolean discountincl;
	private boolean isduplicate;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String creationdate;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String updatedate;

	public Quotaplanlcoprices() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Quotaplanlcoprices(int id, int subplanid, int opid, String plandiscountdays, String plandiscount,
			String plancuscost, String planlcocom, String planmsocom, String creationdate, String updatedate,
			String planlcocompercent, boolean discountincl, boolean isduplicate) {
		super();
		this.id = id;
		this.subplanid = subplanid;
		this.opid = opid;
		this.plandiscountdays = plandiscountdays;
		this.plandiscount = plandiscount;
		this.plancuscost = plancuscost;
		this.planlcocom = planlcocom;
		this.planmsocom = planmsocom;
		this.creationdate = creationdate;
		this.updatedate = updatedate;
		this.planlcocompercent = planlcocompercent;
		this.discountincl = discountincl;
		this.isduplicate = isduplicate;
	}

	public boolean isIsduplicate() {
		return isduplicate;
	}

	public void setIsduplicate(boolean isduplicate) {
		this.isduplicate = isduplicate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSubplanid() {
		return subplanid;
	}

	public void setSubplanid(int subplanid) {
		this.subplanid = subplanid;
	}

	public int getOpid() {
		return opid;
	}

	public void setOpid(int opid) {
		this.opid = opid;
	}

	public String getPlandiscountdays() {
		return plandiscountdays;
	}

	public void setPlandiscountdays(String plandiscountdays) {
		this.plandiscountdays = plandiscountdays;
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

	public String getPlanlcocom() {
		return planlcocom;
	}

	public void setPlanlcocom(String planlcocom) {
		this.planlcocom = planlcocom;
	}

	public String getPlanmsocom() {
		return planmsocom;
	}

	public void setPlanmsocom(String planmsocom) {
		this.planmsocom = planmsocom;
	}

	public String getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(String creationdate) {
		this.creationdate = creationdate;
	}

	public String getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}

	public String getPlanlcocompercent() {
		return planlcocompercent;
	}

	public void setPlanlcocompercent(String planlcocompercent) {
		this.planlcocompercent = planlcocompercent;
	}

	public boolean isIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	public boolean isDiscountincl() {
		return discountincl;
	}

	public void setDiscountincl(boolean discountincl) {
		this.discountincl = discountincl;
	}

}
