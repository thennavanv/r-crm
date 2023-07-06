package com.ridsys.rib.service;

import java.util.List;
import java.util.Map;

import com.ridsys.rib.DTO.InvoiceDTO;
import com.ridsys.rib.models.InvoiceFetching;

public interface InvoiceService {

	List<InvoiceFetching> getInvoiceHistory(String role, String username, String fdate, String tdate, String subrole,
			String gstin);

	InvoiceDTO downloadInvoice(String role, String username, int invoiceid, String gstin);

	Map<String, String> getGstStatus(String username, int invoiceno);

}
