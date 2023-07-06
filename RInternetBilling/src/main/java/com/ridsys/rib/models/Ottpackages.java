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

import com.ridsys.rib.DTO.TicketSubjectTypeDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ottpackages")

@NamedNativeQuery(name = "Ottpackages.getOttPlans", query = "SELECT planname,days FROM ottpackageplan WHERE isactive=1", resultSetMapping = "Mapping.ottPlan")
@SqlResultSetMapping(name = "Mapping.ottPlan", classes = {
		@ConstructorResult(targetClass = TicketSubjectTypeDTO.class, columns = {
				@ColumnResult(name = "days", type = Integer.class), @ColumnResult(name = "planname") }) })
@Getter
@Setter
//@NoArgsConstructor
public class Ottpackages {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String packagename;
	private double packagerate;
	private String packagedesc;
	private int packageid;
	private double lcoper;
	private double lcocom;
	private double msocom;
	private double taxper;
	private double taxamount;
	private double mrp;

	public Ottpackages() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Ottpackages(String packagename, double packagerate, String packagedesc, int packageid) {
		super();
		this.packagename = packagename;
		this.packagerate = packagerate;
		this.packagedesc = packagedesc;
		this.packageid = packageid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPackagename() {
		return packagename;
	}

	public void setPackagename(String packagename) {
		this.packagename = packagename;
	}

	public double getPackagerate() {
		return packagerate;
	}

	public void setPackagerate(double packagerate) {
		this.packagerate = packagerate;
	}

	public String getPackagedesc() {
		return packagedesc;
	}

	public void setPackagedesc(String packagedesc) {
		this.packagedesc = packagedesc;
	}

	public int getPackageid() {
		return packageid;
	}

	public void setPackageid(int packageid) {
		this.packageid = packageid;
	}

	public Ottpackages(int id, String packagename, double packagerate, String packagedesc, int packageid, double lcoper,
			double lcocom, double msocom, double taxper, double taxamount, double mrp) {
		super();
		this.id = id;
		this.packagename = packagename;
		this.packagerate = packagerate;
		this.packagedesc = packagedesc;
		this.packageid = packageid;
		this.lcoper = lcoper;
		this.lcocom = lcocom;
		this.msocom = msocom;
		this.taxper = taxper;
		this.taxamount = taxamount;
		this.mrp = mrp;
	}

}
