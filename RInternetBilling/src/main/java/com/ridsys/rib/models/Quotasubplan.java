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
import com.ridsys.rib.DTO.PlanDTO;
import com.ridsys.rib.DTO.QuotasubplanDTO;

@Entity
@Table(name = "quotasubplan")

//@NamedNativeQuery(name = "Quotasubplan.getSubplanByPlanId", query = "SELECT sp.subPlan,sp.planTimeBank,lp.planCusCost FROM quotasubplan sp LEFT JOIN quotaplanlcoprices lp ON lp.subplanId=sp.id WHERE sp.planId=:planid AND lp.opid=0 AND sp.isdelete=0", resultSetMapping = "Mapping.quotasubplanList")
//@SqlResultSetMapping(name = "Mapping.quotasubplanList", classes = {
//		@ConstructorResult(targetClass = PlanDTO.class, columns = { @ColumnResult(name = "subPlan"),
//				@ColumnResult(name = "planCusCost"), @ColumnResult(name = "planTimeBank") }) })

@NamedNativeQuery(name = "Quotasubplan.getSubPlanForRidsys", query = "(SELECT qsp.id,qsp.subplan,lcp.isactive FROM quotasubplan qsp LEFT JOIN quotaplanlcoprices lcp ON lcp.subplanId=qsp.id LEFT JOIN (SELECT id FROM operators WHERE username=:username)as op on op.id=lcp.opid LEFT JOIN quotaplan qp ON qp.id=qsp.planId WHERE  qsp.planId=:planid AND qsp.isdelete=0 AND lcp.isactive=1 and lcp.subplanId not in(select q.subplanId from quotaplanlcoprices q left join operators o on o.id=q.opid where o.username=:username) AND lcp.opid=0 AND qsp.isactive=1  AND if(:role='operator',(qsp.subplan not like '%+%' or qsp.id=13),planTimeBank>0))UNION(SELECT qsp.id,qsp.subplan,lcp.isactive AS mainplan FROM quotasubplan qsp LEFT JOIN quotaplanlcoprices lcp ON lcp.subplanId=qsp.id LEFT JOIN (SELECT id FROM operators WHERE username=:username)AS op on op.id=lcp.opid LEFT JOIN quotaplan qp ON qp.id=qsp.planId WHERE  qsp.planId=:planid AND qsp.isdelete=0 AND  op.id=lcp.opid AND qsp.isactive=1  AND if(:role='operator',(qsp.subplan not like '%+%' or qsp.id=13),planTimeBank>0))", resultSetMapping = "Mapping.getSubPlanRS")
@NamedNativeQuery(name = "Quotasubplan.getSubPlan", query = "(SELECT qsp.id,qsp.subplan,lcp.isactive FROM quotasubplan qsp LEFT JOIN quotaplanlcoprices lcp ON lcp.subplanId=qsp.id LEFT JOIN (SELECT id FROM operators WHERE username=:username)as op on op.id=lcp.opid LEFT JOIN quotaplan qp ON qp.id=qsp.planId WHERE  qsp.planId=:planid AND qsp.isdelete=0 AND lcp.isactive=1 and lcp.subplanId not in(select q.subplanId from quotaplanlcoprices q left join operators o on o.id=q.opid where o.username=:username) AND lcp.opid=0 AND qsp.isactive=1)UNION(SELECT qsp.id,qsp.subplan,lcp.isactive AS mainplan FROM quotasubplan qsp LEFT JOIN quotaplanlcoprices lcp ON lcp.subplanId=qsp.id LEFT JOIN (SELECT id FROM operators WHERE username=:username)AS op on op.id=lcp.opid LEFT JOIN quotaplan qp ON qp.id=qsp.planId WHERE  qsp.planId=:planid AND qsp.isdelete=0 AND  op.id=lcp.opid AND qsp.isactive=1)", resultSetMapping = "Mapping.getSubPlanRS")
@SqlResultSetMapping(name = "Mapping.getSubPlanRS", classes = {
		@ConstructorResult(targetClass = QuotasubplanDTO.class, columns = {
				@ColumnResult(name = "id", type = Integer.class), @ColumnResult(name = "subplan"),
				@ColumnResult(name = "isactive", type = Boolean.class) }) })

@NamedNativeQuery(name = "Quotasubplan.getSubplanByOpid", query = "(SELECT qsp.*,lcp.*,if(op.id is null,false,true) AS activeSts,qp.plan AS mainplan FROM quotasubplan qsp LEFT JOIN quotaplanlcoprices lcp ON lcp.subplanId=qsp.id LEFT JOIN (SELECT id FROM operators WHERE username=:username)as op on op.id=lcp.opid LEFT JOIN quotaplan qp ON qp.id=qsp.planId WHERE  qsp.planId=:planid AND qsp.isdelete=0 AND lcp.isactive=1 and lcp.subplanId not in(select q.subplanId from quotaplanlcoprices q left join operators o on o.id=q.opid where o.username=:username) AND lcp.opid=0 AND qsp.isactive=1)UNION(SELECT qsp.*,lcp.*,if(op.id is null,false,true) AS activeSts,qp.plan AS mainplan FROM quotasubplan qsp LEFT JOIN quotaplanlcoprices lcp ON lcp.subplanId=qsp.id LEFT JOIN (SELECT id FROM operators WHERE username=:username)AS op on op.id=lcp.opid LEFT JOIN quotaplan qp ON qp.id=qsp.planId WHERE  qsp.planId=:planid AND qsp.isdelete=0 AND  op.id=lcp.opid AND qsp.isactive=1)", resultSetMapping = "Mapping.getSubplanByOpidRS")
@NamedNativeQuery(name = "Quotasubplan.getSubplanByPlanId", query = "SELECT qsp.*,lcp.*,0 as activeSts,qp.plan AS mainplan FROM quotasubplan qsp LEFT JOIN quotaplanlcoprices lcp ON lcp.subplanId=qsp.id LEFT JOIN quotaplan qp ON qp.id=qsp.planId  WHERE lcp.opid=0 AND qsp.planId=:planid AND qsp.isdelete=0", resultSetMapping = "Mapping.getSubplanByOpidRS")
@NamedNativeQuery(name = "Quotasubplan.getSubplanByIdAndOpid", query = "SELECT qsp.*,lcp.*,0 as activeSts,qp.plan AS mainplan FROM quotasubplan qsp LEFT JOIN quotaplanlcoprices lcp ON lcp.subplanId=qsp.id LEFT JOIN quotaplan qp ON qp.id=qsp.planId  WHERE lcp.opid=:opid AND qsp.id=:subplanid AND qsp.isdelete=0 AND qp.id=:planid", resultSetMapping = "Mapping.getSubplanByOpidRS")
@NamedNativeQuery(name = "Quotasubplan.getDuplicateSubplanByIdAndOpid", query = "SELECT qsp.*,lcp.*,0 as activeSts,qp.plan AS mainplan FROM duplicatesubplan qsp LEFT JOIN quotaplanlcoprices lcp ON lcp.subplanId=qsp.subplanid AND qsp.opid=lcp.opid LEFT JOIN quotaplan qp ON qp.id=qsp.planId  WHERE lcp.opid=:opid AND qsp.subplanid=:subplanid AND qsp.isdelete=0 AND qp.id=:planid", resultSetMapping = "Mapping.getSubplanByOpidRS")
@SqlResultSetMapping(name = "Mapping.getSubplanByOpidRS", classes = {
		@ConstructorResult(targetClass = PlanDTO.class, columns = { @ColumnResult(name = "tax", type = String.class),
				@ColumnResult(name = "taxCost", type = String.class),
				@ColumnResult(name = "price", type = String.class), @ColumnResult(name = "mrp", type = String.class),
				@ColumnResult(name = "planCusCost", type = String.class),
				@ColumnResult(name = "planLcoCom", type = String.class),
				@ColumnResult(name = "planMsoCom", type = String.class),
				@ColumnResult(name = "planId", type = String.class), @ColumnResult(name = "id", type = String.class),
				@ColumnResult(name = "planDiscount", type = String.class),
				@ColumnResult(name = "planDiscountDays", type = String.class),
				@ColumnResult(name = "subPlan", type = String.class),
				@ColumnResult(name = "planTimeBank", type = String.class),
				@ColumnResult(name = "planLcoComPercent", type = String.class),
				@ColumnResult(name = "activeSts", type = Boolean.class), @ColumnResult(name = "mainplan"),
				@ColumnResult(name = "discountIncl", type = Boolean.class) }) })

public class Quotasubplan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int planid;
	private String subplan;
	private String price;
	private String tax;
	private String taxcost;
	private String mrp;
	private boolean taxinclude;
	private String plantimebank;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String creationdate;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String updatedate;

	public Quotasubplan() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Quotasubplan(int id, int planid, String subplan, String price, String tax, String taxcost, String mrp,
			boolean taxinclude, String creationdate, String plantimebank) {
		super();
		this.id = id;
		this.planid = planid;
		this.subplan = subplan;
		this.price = price;
		this.tax = tax;
		this.taxcost = taxcost;
		this.mrp = mrp;
		this.taxinclude = taxinclude;
		this.creationdate = creationdate;
		this.plantimebank = plantimebank;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPlanid() {
		return planid;
	}

	public void setPlanid(int planid) {
		this.planid = planid;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	public String getTaxcost() {
		return taxcost;
	}

	public void setTaxcost(String taxcost) {
		this.taxcost = taxcost;
	}

	public String getMrp() {
		return mrp;
	}

	public void setMrp(String mrp) {
		this.mrp = mrp;
	}

	public boolean isTaxinclude() {
		return taxinclude;
	}

	public void setTaxinclude(boolean taxinclude) {
		this.taxinclude = taxinclude;
	}

	public String getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(String creationdate) {
		this.creationdate = creationdate;
	}

	public String getSubplan() {
		return subplan;
	}

	public void setSubplan(String subplan) {
		this.subplan = subplan;
	}

	public String getPlantimebank() {
		return plantimebank;
	}

	public void setPlantimebank(String plantimebank) {
		this.plantimebank = plantimebank;
	}

	public String getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}

}
