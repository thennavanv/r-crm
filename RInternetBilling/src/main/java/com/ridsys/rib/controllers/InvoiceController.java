package com.ridsys.rib.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ridsys.rib.DTO.InvoiceDTO;
import com.ridsys.rib.models.InvoiceFetching;
import com.ridsys.rib.service.InvoiceService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/invoice")
public class InvoiceController {

	@Autowired
	InvoiceService invoiceService;

	@GetMapping("/history")
	public List<InvoiceFetching> getInvoiceHistory(@RequestParam("role") String role,
			@RequestParam("username") String username, @RequestParam("fdate") String fdate,
			@RequestParam("tdate") String tdate, @RequestParam("subRole") String subrole,
			@RequestParam("gstin") String gstin) {

		return invoiceService.getInvoiceHistory(role, username, fdate, tdate, subrole, gstin);

	}

	@GetMapping("/download")
	public InvoiceDTO downloadInvoice(@RequestParam String role, @RequestParam String username,
			@RequestParam int invoiceid, @RequestParam String gstin) {
		return invoiceService.downloadInvoice(role, username, invoiceid, gstin);
	}

	@GetMapping("/gstinStatus")
	public Map<String, String> getGstStatus(@RequestParam("username") String username,
			@RequestParam("invoiceno") int invoiceno) {
		return invoiceService.getGstStatus(username, invoiceno);
	}
}
