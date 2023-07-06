package com.ridsys.rib.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dictionary")
public class Dictionary {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String Type;
	private String Attribute;
	private String Value;
	private String Format;
	private String Vendor;
	private String RecommendedOP;
	private String RecommendedTable;
	private String RecommendedHelper;
	private String RecommendedTooltip;

	public Dictionary() {
	}	
	
	public Dictionary(String type, String attribute, String value, String format, String vendor,
			String recommendedOP, String recommendedTable, String recommendedHelper, String recommendedTooltip) {
		super();
		Type = type;
		Attribute = attribute;
		Value = value;
		Format = format;
		Vendor = vendor;
		RecommendedOP = recommendedOP;
		RecommendedTable = recommendedTable;
		RecommendedHelper = recommendedHelper;
		RecommendedTooltip = recommendedTooltip;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public String getAttribute() {
		return Attribute;
	}

	public void setAttribute(String attribute) {
		Attribute = attribute;
	}

	public String getValue() {
		return Value;
	}

	public void setValue(String value) {
		Value = value;
	}

	public String getFormat() {
		return Format;
	}

	public void setFormat(String format) {
		Format = format;
	}

	public String getVendor() {
		return Vendor;
	}

	public void setVendor(String vendor) {
		Vendor = vendor;
	}

	public String getRecommendedOP() {
		return RecommendedOP;
	}

	public void setRecommendedOP(String recommendedOP) {
		RecommendedOP = recommendedOP;
	}

	public String getRecommendedTable() {
		return RecommendedTable;
	}

	public void setRecommendedTable(String recommendedTable) {
		RecommendedTable = recommendedTable;
	}

	public String getRecommendedHelper() {
		return RecommendedHelper;
	}

	public void setRecommendedHelper(String recommendedHelper) {
		RecommendedHelper = recommendedHelper;
	}

	public String getRecommendedTooltip() {
		return RecommendedTooltip;
	}

	public void setRecommendedTooltip(String recommendedTooltip) {
		RecommendedTooltip = recommendedTooltip;
	}

}
