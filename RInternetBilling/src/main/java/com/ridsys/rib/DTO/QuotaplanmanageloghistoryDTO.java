package com.ridsys.rib.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuotaplanmanageloghistoryDTO {

	private String rechargeid;
	private String customername;
	private String username;
	private String creationdate;
	private String quotaexpirydate;
	private String plancost;
	private String plantax;
	private String planmrp;
	private String plandiscount;
	private String plancuscost;
	private String planmsocost;
	private String planlcocomm;
	private String walletoldbalance;
	private String walletnewbalance;
	private String description;
	private String planname;
	private int planid;
	private String planDays;
	private String lconame;
	private String creationBy;
	private String creationByRole;
	private String oproldbalance;
	private String oprnewbalance;
	private String rechargetype;

	public QuotaplanmanageloghistoryDTO(String rechargeid, String customername, String username, String creationdate,
			String quotaexpirydate, String plancost, String plantax, String planmrp, String plandiscount,
			String plancuscost, String planmsocost, String planlcocomm, String walletoldbalance,
			String walletnewbalance, String description, String planname, int planid, String planDays, String lconame,
			String creationBy, String creationByRole, String rechargetype) {
		super();
		this.rechargeid = rechargeid;
		this.customername = customername;
		this.username = username;
		this.creationdate = creationdate;
		this.quotaexpirydate = quotaexpirydate;
		this.plancost = plancost;
		this.plantax = plantax;
		this.planmsocost = planmsocost;
		this.planlcocomm = planlcocomm;
		this.plancuscost = plancuscost;
		this.walletoldbalance = walletoldbalance;
		this.walletnewbalance = walletnewbalance;
		this.description = description;
		this.planname = planname;
		this.planid = planid;
		this.planDays = planDays;
		this.planmrp = planmrp;
		this.plandiscount = plandiscount;
		this.lconame = lconame;
		this.creationBy = creationBy;
		this.creationByRole = creationByRole;
		this.rechargetype = rechargetype;
	}

	public QuotaplanmanageloghistoryDTO(String rechargeid, String customername, String username, String creationdate,
			String quotaexpirydate, String plancost, String plantax, String planmrp, String plandiscount,
			String plancuscost, String planmsocost, String planlcocomm, String walletoldbalance,
			String walletnewbalance, String description, String planname, int planid, String planDays, String lconame,
			String creationBy, String creationByRole, String oproldbalance, String oprnewbalance) {
		super();
		this.rechargeid = rechargeid;
		this.customername = customername;
		this.username = username;
		this.creationdate = creationdate;
		this.quotaexpirydate = quotaexpirydate;
		this.plancost = plancost;
		this.plantax = plantax;
		this.planmsocost = planmsocost;
		this.planlcocomm = planlcocomm;
		this.plancuscost = plancuscost;
		this.walletoldbalance = walletoldbalance;
		this.walletnewbalance = walletnewbalance;
		this.description = description;
		this.planname = planname;
		this.planid = planid;
		this.planDays = planDays;
		this.planmrp = planmrp;
		this.plandiscount = plandiscount;
		this.lconame = lconame;
		this.creationBy = creationBy;
		this.creationByRole = creationByRole;
		this.oproldbalance = oproldbalance;
		this.oprnewbalance = oprnewbalance;
	}

	public String getOproldbalance() {
		return oproldbalance;
	}

	public void setOproldbalance(String oproldbalance) {
		this.oproldbalance = oproldbalance;
	}

	public String getOprnewbalance() {
		return oprnewbalance;
	}

	public void setOprnewbalance(String oprnewbalance) {
		this.oprnewbalance = oprnewbalance;
	}

	public String getLconame() {
		return lconame;
	}

	public void setLconame(String lconame) {
		this.lconame = lconame;
	}

	public String getRechargeid() {
		return rechargeid;
	}

	public void setRechargeid(String rechargeid) {
		this.rechargeid = rechargeid;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(String creationdate) {
		this.creationdate = creationdate;
	}

	public String getQuotaexpirydate() {
		return quotaexpirydate;
	}

	public void setQuotaexpirydate(String quotaexpirydate) {
		this.quotaexpirydate = quotaexpirydate;
	}

	public String getPlancost() {
		return plancost;
	}

	public void setPlancost(String plancost) {
		this.plancost = plancost;
	}

	public String getPlantax() {
		return plantax;
	}

	public void setPlantax(String plantax) {
		this.plantax = plantax;
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

	public String getPlanlcocomm() {
		return planlcocomm;
	}

	public void setPlanlcocomm(String planlcocomm) {
		this.planlcocomm = planlcocomm;
	}

	public String getWalletoldbalance() {
		return walletoldbalance;
	}

	public void setWalletoldbalance(String walletoldbalance) {
		this.walletoldbalance = walletoldbalance;
	}

	public String getWalletnewbalance() {
		return walletnewbalance;
	}

	public void setWalletnewbalance(String walletnewbalance) {
		this.walletnewbalance = walletnewbalance;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPlanname() {
		return planname;
	}

	public void setPlanname(String planname) {
		this.planname = planname;
	}

	public int getPlanid() {
		return planid;
	}

	public void setPlanid(int planid) {
		this.planid = planid;
	}

	public String getPlanDays() {
		return planDays;
	}

	public void setPlanDays(String planDays) {
		this.planDays = planDays;
	}

	public String getCreationBy() {
		return creationBy;
	}

	public void setCreationBy(String creationBy) {
		this.creationBy = creationBy;
	}

	public String getCreationByRole() {
		return creationByRole;
	}

	public void setCreationByRole(String creationByRole) {
		this.creationByRole = creationByRole;
	}

}
