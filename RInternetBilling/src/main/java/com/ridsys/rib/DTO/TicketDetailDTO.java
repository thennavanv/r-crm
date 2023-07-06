package com.ridsys.rib.DTO;

public class TicketDetailDTO {

	private long ticketcount;
	private String subjectname;
	private String typename;

	public TicketDetailDTO(long ticketcount, String subjectname, String typename) {
		super();
		this.ticketcount = ticketcount;
		this.subjectname = subjectname;
		this.typename = typename;
	}

	public long getTicketcount() {
		return ticketcount;
	}

	public void setTicketcount(long ticketcount) {
		this.ticketcount = ticketcount;
	}

	public String getSubjectname() {
		return subjectname;
	}

	public void setSubjectname(String subjectname) {
		this.subjectname = subjectname;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

}
