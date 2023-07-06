package com.ridsys.rib.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "billing_plans_profiles")
public class Billing_plans_profiles {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "plan_name")
	private String planname;

	@Column(name = "profile_name")
	private String profilename;

	public Billing_plans_profiles() {
	}

	public Billing_plans_profiles(String planname, String profilename) {
		super();
		this.planname = planname;
		this.profilename = profilename;
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

	public String getProfilename() {
		return profilename;
	}

	public void setProfilename(String profilename) {
		this.profilename = profilename;
	}

}
