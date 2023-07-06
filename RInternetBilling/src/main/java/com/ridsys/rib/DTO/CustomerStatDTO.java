package com.ridsys.rib.DTO;

import java.util.HashMap;
import java.util.Map;

public class CustomerStatDTO {

	private long today;
	private long yesterday;
	private long tomorrow;
	private long lastweek;
	private long nextweek;
	private Map<String, Long> shortableCustExpired;
	private Map<String, Long> sortTableCustomerCout;
	private Map<String, Long> sortTableTicketCout;

	public CustomerStatDTO() {
		super();
	}
	
	



	public CustomerStatDTO(long today, long yesterday, long tomorrow, long lastweek) {
		super();
		this.today = today;
		this.yesterday = yesterday;
		this.tomorrow = tomorrow;
		this.lastweek = lastweek;
	}

	public CustomerStatDTO(long today, long yesterday, long tomorrow) {
		super();
		this.today = today;
		this.yesterday = yesterday;
		this.tomorrow = tomorrow;

	}

	public CustomerStatDTO(long today, long yesterday, long tomorrow, long lastweek, long nextweek) {
		super();
		this.today = today;
		this.yesterday = yesterday;
		this.tomorrow = tomorrow;
		this.lastweek = lastweek;
		this.nextweek = nextweek;
	}

	public void setShortableCustExpired(long today, long yesterday, long trw, long lastweek, long nextweek) {

		Map<String, Long> shortableCustExpired = new HashMap<>();
		shortableCustExpired.put("today", today);
		shortableCustExpired.put("yesterday", yesterday);
		shortableCustExpired.put("tomorrow", trw);
		shortableCustExpired.put("lastweek", lastweek);
		shortableCustExpired.put("nextweek", nextweek);

		this.shortableCustExpired = shortableCustExpired;

	}

	public void setSortTableCustomerCout(long today, long yesterday, long trw, long lastweek, long nextweek) {
		Map<String, Long> sortTableCustomerCout = new HashMap<>();
		sortTableCustomerCout.put("New", today);
		sortTableCustomerCout.put("Active", yesterday);
		sortTableCustomerCout.put("Expiry", trw);
		sortTableCustomerCout.put("Reject", lastweek);
		sortTableCustomerCout.put("FUPExceed", nextweek);
		this.sortTableCustomerCout = sortTableCustomerCout;
	}

	
	
	public void setSortTableTicketCout(long today, long yesterday, long trw, long lastweek, long nextweek) {
		Map<String, Long> sortTableTicketCout = new HashMap<>();
		sortTableCustomerCout.put("New", today);
		sortTableCustomerCout.put("Opened", yesterday);
		sortTableCustomerCout.put("Finished", trw);
		sortTableCustomerCout.put("Solved", lastweek);
		sortTableCustomerCout.put("Reassigned", nextweek);
		this.sortTableTicketCout = sortTableTicketCout;
	}

	public Map<String, Long> getSortTableTicketCout() {
		return sortTableTicketCout;
	}

	public Map<String, Long> getSortTableCustomerCout() {
		return sortTableCustomerCout;
	}

	public long getToday() {
		return today;
	}

	public void setToday(long today) {
		this.today = today;
	}

	public long getYesterday() {
		return yesterday;
	}

	public void setYesterday(long yesterday) {
		this.yesterday = yesterday;
	}

	public long getTomorrow() {
		return tomorrow;
	}

	public void setTomorrow(long tomorrow) {
		this.tomorrow = tomorrow;
	}

	public long getLastweek() {
		return lastweek;
	}

	public void setLastweek(long lastweek) {
		this.lastweek = lastweek;
	}

	public long getNextweek() {
		return nextweek;
	}

	public void setNextweek(long nextweek) {
		this.nextweek = nextweek;
	}

	public void setShortableCustExpired(Map<String, Long> shortableCustExpired) {
		this.shortableCustExpired = shortableCustExpired;
	}

	public Map<String, Long> getShortableCustExpired() {
		return shortableCustExpired;
	}

}
