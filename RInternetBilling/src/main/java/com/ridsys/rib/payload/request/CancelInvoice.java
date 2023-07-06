package com.ridsys.rib.payload.request;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CancelInvoice {

	@NotNull
	private String username;
	
	@NotNull
	private String role;
	
	
	@NotNull
	private String customername;
	
	@NotNull
	private int planid;
	
	@NotNull
	private String startdate;
	
}
