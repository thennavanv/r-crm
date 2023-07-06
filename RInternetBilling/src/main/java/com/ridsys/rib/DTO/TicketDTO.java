package com.ridsys.rib.DTO;

import org.jsoup.Jsoup;

public class TicketDTO {

	private int id;
//	private int emp_id;
	private int opid;
	private int customerid;
	private String vlanid;
	private int rating;
//	private int status;
	private String code;
	private String username;
	private String empname;
	private String ticketDescription;
	private String subject;
	private String type;
	private String opname;
	private String clientcomments;
	private String empcomments;
	private String createddate;
	private String assigneddate;
	private String accepteddate;
	private String finisheddate;
	private String closeddate;
	private String status;
	private boolean isreassign;
	private boolean isactive;

	public TicketDTO(int id, int opid, int customerid, String vlanid, String code, String username, String empname,
			String ticketDescription, String subject, String type, String opname, String clientcomments,
			String empcomments, String createddate, String assigneddate, String accepteddate, String finisheddate,
			String closeddate, String status, boolean isreassign, boolean isactive, int rating) {
		super();
		this.id = id;
		this.opid = opid;
		this.customerid = customerid;
		this.vlanid = vlanid;
		this.code = code;
		this.username = username;
		this.empname = empname;
		this.ticketDescription = ticketDescription;
		this.subject = subject;
		this.type = type;
		this.opname = opname;
		this.clientcomments = clientcomments;
		this.empcomments = empcomments;
		this.createddate = createddate;
		this.assigneddate = assigneddate;
		this.accepteddate = accepteddate;
		this.finisheddate = finisheddate;
		this.closeddate = closeddate;
		this.status = status;
		this.isreassign = isreassign;
		this.isactive = isactive;
		this.rating = rating;
	}

	public TicketDTO(String vlanid, String username, String opname, String subject, String type) {
		super();
		this.vlanid = vlanid;
		this.username = username;
		this.opname = opname;
		this.subject = subject;
		this.type = type;

	}

	public TicketDTO(String ticketDescription, String subject, String type, String createddate1, String status,
			String createddate) {
		super();
		this.closeddate = createddate;
		this.ticketDescription = Jsoup.parse(ticketDescription).text();
		this.subject = subject;
		this.type = type;
		this.createddate = createddate1;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOpid() {
		return opid;
	}

	public void setOpid(int opid) {
		this.opid = opid;
	}

	public int getCustomerid() {
		return customerid;
	}

	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}

	public String getVlanid() {
		return vlanid;
	}

	public void setVlanid(String vlanid) {
		this.vlanid = vlanid;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmpname() {
		return empname;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}

	public String getTicketDescription() {
		return ticketDescription;
	}

	public void setTicketDescription(String ticketDescription) {
		this.ticketDescription = ticketDescription;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOpname() {
		return opname;
	}

	public void setOpname(String opname) {
		this.opname = opname;
	}

	public String getClientcomments() {
		return clientcomments;
	}

	public void setClientcomments(String clientcomments) {
		this.clientcomments = clientcomments;
	}

	public String getEmpcomments() {
		return empcomments;
	}

	public void setEmpcomments(String empcomments) {
		this.empcomments = empcomments;
	}

	public String getCreateddate() {
		return createddate;
	}

	public void setCreateddate(String createddate) {
		this.createddate = createddate;
	}

	public String getAssigneddate() {
		return assigneddate;
	}

	public void setAssigneddate(String assigneddate) {
		this.assigneddate = assigneddate;
	}

	public String getAccepteddate() {
		return accepteddate;
	}

	public void setAccepteddate(String accepteddate) {
		this.accepteddate = accepteddate;
	}

	public String getFinisheddate() {
		return finisheddate;
	}

	public void setFinisheddate(String finisheddate) {
		this.finisheddate = finisheddate;
	}

	public String getCloseddate() {
		return closeddate;
	}

	public void setCloseddate(String closeddate) {
		this.closeddate = closeddate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isIsreassign() {
		return isreassign;
	}

	public void setIsreassign(boolean isreassign) {
		this.isreassign = isreassign;
	}

	public boolean isIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

}
