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

import com.ridsys.rib.DTO.SpinListDTO;

@Entity
@Table(name = "payment_type")
@NamedNativeQuery(name = "Payment_type.getSpinList", query = "SELECT id,value AS name FROM payment_type WHERE isactive=1", resultSetMapping = "Mapping.PaymentSpinListDTO")
@SqlResultSetMapping(name = "Mapping.PaymentSpinListDTO", classes = {
		@ConstructorResult(targetClass = SpinListDTO.class, columns = { @ColumnResult(name = "id"),
				@ColumnResult(name = "name") }) })
public class Payment_type {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String value;
	private String notes;
	private String creationdate;
	private String creationby;
	private String updatedate;
	private String updateby;
	private boolean isactive;

	public Payment_type(String value, String notes, String creationdate, String creationby, String updatedate,
			String updateby, boolean isactive) {
		super();
		this.value = value;
		this.notes = notes;
		this.creationdate = creationdate;
		this.creationby = creationby;
		this.updatedate = updatedate;
		this.updateby = updateby;
		this.isactive = isactive;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
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

	public boolean isIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

}
