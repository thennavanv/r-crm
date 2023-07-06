package com.ridsys.rib.PaymentGateway.RazorPay;

import java.util.Map;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
//@NoArgsConstructor
public class OrderEntity {

	private String key;
	private String id;
	private String entity;
	private int amount;
	private int amount_paid;
	private int amount_due;
	private String currency;
	private String receipt;
	private String status;
	private int attempts;
	private Map<String, Object> notes;
	private Long created_at;

	public OrderEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderEntity(String key, String id, String entity, int amount, int amount_paid, int amount_due,
			String currency, String receipt, String status, int attempts, Map<String, Object> notes, Long created_at) {
		super();
		this.key = key;
		this.id = id;
		this.entity = entity;
		this.amount = amount;
		this.amount_paid = amount_paid;
		this.amount_due = amount_due;
		this.currency = currency;
		this.receipt = receipt;
		this.status = status;
		this.attempts = attempts;
		this.notes = notes;
		this.created_at = created_at;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getAmount_paid() {
		return amount_paid;
	}

	public void setAmount_paid(int amount_paid) {
		this.amount_paid = amount_paid;
	}

	public int getAmount_due() {
		return amount_due;
	}

	public void setAmount_due(int amount_due) {
		this.amount_due = amount_due;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getReceipt() {
		return receipt;
	}

	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getAttempts() {
		return attempts;
	}

	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}

	public Map<String, Object> getNotes() {
		return notes;
	}

	public void setNotes(Map<String, Object> notes) {
		this.notes = notes;
	}

	public Long getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Long created_at) {
		this.created_at = created_at;
	}

}
