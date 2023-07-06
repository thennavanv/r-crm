package com.ridsys.rib.models;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ridsys.rib.DTO.AllPlanListDTO;
import com.ridsys.rib.DTO.QuotaplanDTO;
import com.ridsys.rib.DTO.TicketSubjectTypeDTO;

@Entity
@Table(name = "quotaplan")

@NamedNativeQuery(name = "Quotaplan.getMainplans", query = "SELECT id,plan FROM quotaplan WHERE isactive=1 AND isdelete=0", resultSetMapping = "Mapping.quotaplan")
@NamedNativeQuery(name = "Quotaplan.getActiveSubplanByOpid", query = "(SELECT qsp.id,qsp.subplan AS plan FROM quotasubplan qsp LEFT JOIN quotaplanlcoprices lcp ON lcp.subplanId=qsp.id LEFT JOIN (SELECT id FROM operators WHERE username=:username)as op on op.id=lcp.opid LEFT JOIN quotaplan qp ON qp.id=qsp.planId WHERE  qsp.isdelete=0 AND lcp.isactive=1 and lcp.subplanId not in(select q.subplanId from quotaplanlcoprices q left join operators o on o.id=q.opid where o.username=:username) AND lcp.opid=0 AND qsp.isactive=1 AND qp.isactive=1 AND if(:isonemonth=true,(qsp.subplan not like '%+%' OR qsp.id in(13,10)),qsp.planTimeBank>0))UNION(SELECT qsp.id,qsp.subplan AS plan FROM quotasubplan qsp LEFT JOIN quotaplanlcoprices lcp ON lcp.subplanId=qsp.id LEFT JOIN (SELECT id FROM operators WHERE username=:username)AS op on op.id=lcp.opid LEFT JOIN quotaplan qp ON qp.id=qsp.planId WHERE   qsp.isdelete=0 AND  op.id=lcp.opid AND lcp.isactive=1 AND qsp.isactive=1 AND qp.isactive=1 AND if(:isonemonth=true,(qsp.subplan not like '%+%' OR qsp.id in(13,10)),qsp.planTimeBank>0)) ORDER BY plan", resultSetMapping = "Mapping.quotaplan")
@NamedNativeQuery(name = "Quotaplan.getSubplanByUserPlanprice", query = "(SELECT qsp.id,qsp.subplan AS plan FROM quotasubplan qsp LEFT JOIN quotaplanlcoprices lcp ON lcp.subplanId=qsp.id LEFT JOIN quotaplan qp ON qp.id=qsp.planId WHERE  qsp.isdelete=0 AND lcp.isactive=1 and lcp.subplanId not in(select q.subplanId from quotaplanlcoprices q where opid=:opid) AND lcp.opid=0 AND qsp.isactive=1 AND qp.isactive=1 AND qp.bandwidthUp>=if((select bandwidthUp from quotaplan where id in(select planId from quotasubplan where id in(select planid from userbillinfo where username=:username))) is null,0,(select bandwidthUp from quotaplan where id in(select planId from quotasubplan where id in(select planid from userbillinfo where username=:username)))) AND If(:isonemonth=true,(qsp.subplan not like '%+%' OR qsp.id in(13,10)) ,qsp.planTimeBank>0))UNION(SELECT qsp.id,qsp.subplan AS plan FROM quotasubplan qsp LEFT JOIN quotaplanlcoprices lcp ON lcp.subplanId=qsp.id LEFT JOIN quotaplan qp ON qp.id=qsp.planId WHERE   qsp.isdelete=0 AND  lcp.opid=:opid AND lcp.isactive=1 AND qsp.isactive=1 AND qp.isactive=1  AND qp.bandwidthUp>=if((select bandwidthUp from quotaplan where id in(select planId from quotasubplan where id in(select planid from userbillinfo where username=:username))) is null,0,(select bandwidthUp from quotaplan where id in(select planId from quotasubplan where id in(select planid from userbillinfo where username=:username)))) AND If(:isonemonth=true,(qsp.subplan not like '%+%' OR qsp.id in(13,10)) ,qsp.planTimeBank>0)) ORDER BY plan", resultSetMapping = "Mapping.quotaplan")
@SqlResultSetMapping(name = "Mapping.quotaplan", classes = {
		@ConstructorResult(targetClass = TicketSubjectTypeDTO.class, columns = {
				@ColumnResult(name = "id", type = Integer.class), @ColumnResult(name = "plan") }) })

@NamedNativeQuery(name = "Quotaplan.getMainPlan", query = "SELECT qp.id,qp.plan,if(opp.isactive is null,0,opp.isactive) AS isactive FROM quotaplan qp LEFT JOIN (SELECT opp.isactive,opp.planid FROM operatorplanpermission opp LEFT JOIN operators op ON op.id=opp.opid WHERE op.username=:username) AS opp on opp.planid=qp.id WHERE qp.isactive=1 AND qp.isdelete=0", resultSetMapping = "Mapping.getMainPlanRS")
@SqlResultSetMapping(name = "Mapping.getMainPlanRS", classes = {
		@ConstructorResult(targetClass = QuotaplanDTO.class, columns = {
				@ColumnResult(name = "id", type = Integer.class), @ColumnResult(name = "plan"),
				@ColumnResult(name = "isactive", type = Boolean.class) }) })

//@NamedNativeQuery(name = "Quotaplan.getAllPlanList", query = "SELECT qsp.subplan AS planName,qsp.planTimeBank AS validitydays,qsp.price AS price,qsp.tax AS tax,qsp.mrp AS mrp,lcp.planDiscount AS discountCost,lcp.planCusCost AS cusCost,lcp.planLcoCom AS lcoCom,lcp.planMsoCom AS msoCom,qp.bandwidthUp AS uploadSpeed,qp.bandwidthDown AS downloadSpeed,qp.planType AS plantype,qp.billType AS billtype,qp.bandwidthTotal AS totalLimit,qsp.isactive AS activeStatus,qp.packageType,lcp.planLcoComPercent,qp.id AS planid,qsp.id AS subplanid FROM quotasubplan AS qsp left join quotaplan AS qp ON qp.id=qsp.planId left join quotaplanlcoprices AS lcp ON lcp.subplanId=qsp.id UNION SELECT qp.plan AS planName,30 AS validitydays,onemonthcost AS price,18 AS tax,0 AS mrp,0 AS discountCost,0 AS cusCost,0 AS lcoCom,0 AS msoCom,bandwidthUp AS uploadSpeed,bandwidthDown AS  downloadSpeed,qp.planType AS plantype,qp.billType AS billtype,bandwidthTotal AS totalLimit,qp.isactive AS activeStatus,qp.packageType,0 AS planLcoComPercent,qp.id AS planid,0 AS subplanid FROM quotaplan qp ORDER BY planName DESC", resultSetMapping = "Mapping.getAllPlanListRS")
@NamedNativeQuery(name = "Quotaplan.getAllPlanList", query = "SELECT distinct qsp.id AS subplanid, qsp.subplan AS planName,qsp.planTimeBank AS validitydays,qsp.price AS price,qsp.tax AS tax,qsp.mrp AS mrp,lcp.planDiscount AS discountCost,lcp.planCusCost AS cusCost,lcp.planLcoCom AS lcoCom,lcp.planMsoCom AS msoCom,qp.bandwidthUp AS uploadSpeed,qp.bandwidthDown AS downloadSpeed,qp.planType AS plantype,qp.billType AS billtype,qp.bandwidthTotal AS totalLimit,qsp.isactive AS activeStatus,qp.packageType,lcp.planLcoComPercent,qp.id AS planid FROM quotasubplan AS qsp left join quotaplan AS qp ON qp.id=qsp.planId left join quotaplanlcoprices AS lcp ON lcp.subplanId=qsp.id WHERE lcp.opid=0 ORDER BY planName", resultSetMapping = "Mapping.getAllPlanListRS")
@NamedNativeQuery(name = "Quotaplan.getAllPlanListByOpid", query = "(SELECT distinct qsp.id AS subplanid, qsp.subplan AS planName,qsp.planTimeBank AS validitydays,qsp.price AS price,qsp.tax AS tax,qsp.mrp AS mrp,lcp.planDiscount AS discountCost,lcp.planCusCost AS cusCost,lcp.planLcoCom AS lcoCom,lcp.planMsoCom AS msoCom,qp.bandwidthUp AS uploadSpeed,qp.bandwidthDown AS downloadSpeed,qp.planType AS plantype,qp.billType AS billtype,qp.bandwidthTotal AS totalLimit,qsp.isactive AS activeStatus,qp.packageType,lcp.planLcoComPercent,qp.id AS planid FROM quotasubplan AS qsp LEFT JOIN quotaplan AS qp ON qp.id=qsp.planId LEFT JOIN quotaplanlcoprices AS lcp ON lcp.subplanId=qsp.id  LEFT JOIN (SELECT id FROM operators WHERE username=:username)as op on op.id=lcp.opid WHERE  qsp.isdelete=0 AND lcp.isactive=1 AND lcp.subplanId not in(select q.subplanId FROM quotaplanlcoprices q LEFT JOIN operators o on o.id=q.opid WHERE o.username=:username) AND lcp.opid=0 AND qsp.isactive=1 AND qp.isactive=1 )union(SELECT distinct qsp.id AS subplanid, qsp.subplan AS planName,qsp.planTimeBank AS validitydays,qsp.price AS price,qsp.tax AS tax,qsp.mrp AS mrp,lcp.planDiscount AS discountCost,lcp.planCusCost AS cusCost,lcp.planLcoCom AS lcoCom,lcp.planMsoCom AS msoCom,qp.bandwidthUp AS uploadSpeed,qp.bandwidthDown AS downloadSpeed,qp.planType AS plantype,qp.billType AS billtype,qp.bandwidthTotal AS totalLimit,qsp.isactive AS activeStatus,qp.packageType,lcp.planLcoComPercent,qp.id AS planid FROM quotasubplan AS qsp LEFT JOIN quotaplan AS qp ON qp.id=qsp.planId LEFT JOIN quotaplanlcoprices AS lcp ON lcp.subplanId=qsp.id  LEFT JOIN (SELECT id FROM operators WHERE username=:username)as op on op.id=lcp.opid WHERE  qsp.isdelete=0 AND  op.id=lcp.opid AND lcp.isactive=1 AND qsp.isactive=1 AND qp.isactive=1) ORDER BY planName", resultSetMapping = "Mapping.getAllPlanListRS")
@NamedNativeQuery(name = "Quotaplan.getAllPlanListByOpidForRnet", query = "(SELECT distinct qsp.id AS subplanid, qsp.subplan AS planName,qsp.planTimeBank AS validitydays,qsp.price AS price,qsp.tax AS tax,qsp.mrp AS mrp,lcp.planDiscount AS discountCost,lcp.planCusCost AS cusCost,lcp.planLcoCom AS lcoCom,lcp.planMsoCom AS msoCom,qp.bandwidthUp AS uploadSpeed,qp.bandwidthDown AS downloadSpeed,qp.planType AS plantype,qp.billType AS billtype,qp.bandwidthTotal AS totalLimit,qsp.isactive AS activeStatus,qp.packageType,lcp.planLcoComPercent,qp.id AS planid FROM quotasubplan AS qsp LEFT JOIN quotaplan AS qp ON qp.id=qsp.planId LEFT JOIN quotaplanlcoprices AS lcp ON lcp.subplanId=qsp.id  LEFT JOIN (SELECT id FROM operators WHERE username=:username)as op on op.id=lcp.opid WHERE  qsp.isdelete=0 AND lcp.isactive=1 AND lcp.subplanId not in(select q.subplanId FROM quotaplanlcoprices q LEFT JOIN operators o on o.id=q.opid WHERE o.username=:username) AND lcp.opid=0 AND qsp.isactive=1 AND qp.isactive=1  AND  (qsp.subplan not like '%+%' OR qsp.id in(13,10)))union(SELECT distinct qsp.id AS subplanid, qsp.subplan AS planName,qsp.planTimeBank AS validitydays,qsp.price AS price,qsp.tax AS tax,qsp.mrp AS mrp,lcp.planDiscount AS discountCost,lcp.planCusCost AS cusCost,lcp.planLcoCom AS lcoCom,lcp.planMsoCom AS msoCom,qp.bandwidthUp AS uploadSpeed,qp.bandwidthDown AS downloadSpeed,qp.planType AS plantype,qp.billType AS billtype,qp.bandwidthTotal AS totalLimit,qsp.isactive AS activeStatus,qp.packageType,lcp.planLcoComPercent,qp.id AS planid FROM quotasubplan AS qsp LEFT JOIN quotaplan AS qp ON qp.id=qsp.planId LEFT JOIN quotaplanlcoprices AS lcp ON lcp.subplanId=qsp.id  LEFT JOIN (SELECT id FROM operators WHERE username=:username)as op on op.id=lcp.opid WHERE  qsp.isdelete=0 AND  op.id=lcp.opid AND lcp.isactive=1 AND qsp.isactive=1 AND qp.isactive=1  AND  (qsp.subplan not like '%+%' OR qsp.id in(13,10)) ) ORDER BY planName", resultSetMapping = "Mapping.getAllPlanListRS")
@NamedNativeQuery(name = "Quotaplan.getMainplanList", query = "SELECT qp.plan AS planName,0 AS validitydays,onemonthcost AS price,18 AS tax,0 AS mrp,0 AS discountCost,0 AS cusCost,0 AS lcoCom,0 AS msoCom,bandwidthUp AS uploadSpeed,bandwidthDown AS  downloadSpeed,qp.planType AS plantype,qp.billType AS billtype,bandwidthTotal AS totalLimit,qp.isactive AS activeStatus,qp.packageType,0 AS planLcoComPercent,qp.id AS planid,0 AS subplanid FROM quotaplan qp WHERE qp.isactive=1", resultSetMapping = "Mapping.getAllPlanListRS")
@SqlResultSetMapping(name = "Mapping.getAllPlanListRS", classes = {
		@ConstructorResult(targetClass = AllPlanListDTO.class, columns = { @ColumnResult(name = "planName"),
				@ColumnResult(name = "validitydays", type = String.class),
				@ColumnResult(name = "price", type = String.class), @ColumnResult(name = "tax", type = String.class),
				@ColumnResult(name = "mrp", type = String.class),
				@ColumnResult(name = "discountCost", type = String.class),
				@ColumnResult(name = "cusCost", type = String.class),
				@ColumnResult(name = "lcoCom", type = String.class),
				@ColumnResult(name = "msoCom", type = String.class),
				@ColumnResult(name = "uploadSpeed", type = String.class),
				@ColumnResult(name = "downloadSpeed", type = String.class), @ColumnResult(name = "plantype"),
				@ColumnResult(name = "billtype"), @ColumnResult(name = "totalLimit", type = String.class),
				@ColumnResult(name = "activeStatus", type = Boolean.class), @ColumnResult(name = "packageType"),
				@ColumnResult(name = "planLcoComPercent", type = String.class),
				@ColumnResult(name = "planid", type = String.class),
				@ColumnResult(name = "subplanid", type = String.class) }) })

public class Quotaplan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String planid;
	private String plan;
	private String billtype;
	private String plantype;
	private String packagetype;
	private String bandwidthup;
	private String bandwidthdown;
	private String bandwidthtotal;
	private String onemonthcost;
	private String description;
	private boolean isfup;
	private boolean isactive;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "operatorplanpermission", joinColumns = {
			@JoinColumn(name = "planid", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "opid", referencedColumnName = "id") })
	private Set<Operators> opset = new LinkedHashSet<>();

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String creationdate;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String updatedate;

	public Quotaplan() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Quotaplan(int id, String planid, String plan, String billtype, String plantype, String packagetype,
			String bandwidthup, String bandwidthdown, String bandwidthtotal, String onemonthcost, String description,
			boolean isfup, boolean isactive, String creationdate) {
		super();
		this.id = id;
		this.planid = planid;
		this.plan = plan;
		this.billtype = billtype;
		this.plantype = plantype;
		this.packagetype = packagetype;
		this.bandwidthup = bandwidthup;
		this.bandwidthdown = bandwidthdown;
		this.bandwidthtotal = bandwidthtotal;
		this.onemonthcost = onemonthcost;
		this.description = description;
		this.isfup = isfup;
		this.isactive = isactive;
		this.creationdate = creationdate;
	}

	public void setOpset(Set<Operators> opset) {
		this.opset = opset;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPlanid() {
		return planid;
	}

	public void setPlanid(String planid) {
		this.planid = planid;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	public String getBilltype() {
		return billtype;
	}

	public void setBilltype(String billtype) {
		this.billtype = billtype;
	}

	public String getPlantype() {
		return plantype;
	}

	public void setPlantype(String plantype) {
		this.plantype = plantype;
	}

	public String getPackagetype() {
		return packagetype;
	}

	public void setPackagetype(String packagetype) {
		this.packagetype = packagetype;
	}

	public String getBandwidthup() {
		return bandwidthup;
	}

	public void setBandwidthup(String bandwidthup) {
		this.bandwidthup = bandwidthup;
	}

	public String getBandwidthdown() {
		return bandwidthdown;
	}

	public void setBandwidthdown(String bandwidthdown) {
		this.bandwidthdown = bandwidthdown;
	}

	public String getBandwidthtotal() {
		return bandwidthtotal;
	}

	public void setBandwidthtotal(String bandwidthtotal) {
		this.bandwidthtotal = bandwidthtotal;
	}

	public String getOnemonthcost() {
		return onemonthcost;
	}

	public void setOnemonthcost(String onemonthcost) {
		this.onemonthcost = onemonthcost;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isIsfup() {
		return isfup;
	}

	public void setIsfup(boolean isfup) {
		this.isfup = isfup;
	}

	public boolean isIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
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

}
