package com.ridsys.rib.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ridsys.rib.DTO.InvoiceChargesList;
import com.ridsys.rib.DTO.InvoiceDTO;
import com.ridsys.rib.comm.Dateformat;
import com.ridsys.rib.models.Clientinfo;
import com.ridsys.rib.models.Invoice;
import com.ridsys.rib.models.InvoiceFetching;
import com.ridsys.rib.models.Invoice_items;
import com.ridsys.rib.models.Userinfo;
import com.ridsys.rib.repository.ClientinfoRepository;
import com.ridsys.rib.repository.InvoiceFetchingRepository;
import com.ridsys.rib.repository.InvoiceRepository;
import com.ridsys.rib.repository.QuotaplanmanagelogRepository;
import com.ridsys.rib.repository.UserinfoRepository;
import com.ridsys.rib.service.InvoiceService;

@Service
public class InvoiceServiceImpl implements InvoiceService {

	private static final DecimalFormat df = new DecimalFormat("0.00");

	@Autowired
	InvoiceFetchingRepository invoiceFetchingRepository;

	@Autowired
	UserinfoRepository userinfoRepository;

	@Autowired
	InvoiceRepository invoiceRepository;

	@Autowired
	QuotaplanmanagelogRepository quotaplanmanagelogRepository;
	
	@Autowired
	ClientinfoRepository clientinfoRepository;

	@Override
	public List<InvoiceFetching> getInvoiceHistory(String role, String username, String fdate, String tdate,
			String subrole, String gstin) {

		List<InvoiceFetching> invoicelist = new ArrayList();

		if (!fdate.equals("0000-00-00") && !tdate.equals("0000-00-00")) {
			if (role.equalsIgnoreCase("ADMIN") || role.equalsIgnoreCase("EMPLOYEE")) {
				if (subrole.equalsIgnoreCase("OPERATOR")) {
					invoicelist = invoiceFetchingRepository.findAllOrderByOpidFtdate(fdate, tdate);

				} else if (subrole.equalsIgnoreCase("USER")) {

					if (gstin.equalsIgnoreCase("WITH")) {
						invoicelist = invoiceFetchingRepository.findAllByWithGstFtdate(fdate, tdate);

					} else if (gstin.equalsIgnoreCase("WITHOUT")) {
						invoicelist = invoiceFetchingRepository.findAllByWithOutGstFtdate(fdate, tdate);

					} else {
						invoicelist = invoiceFetchingRepository.findAllByFtdate(fdate, tdate);
					}

				}

			} else if (role.equalsIgnoreCase("OPERATOR")) {

				if (gstin.equalsIgnoreCase("WITH")) {
					invoicelist = invoiceFetchingRepository.findByOpidWithGstFtdate(username, fdate, tdate);

				} else if (gstin.equalsIgnoreCase("WITH")) {
					invoicelist = invoiceFetchingRepository.findByOpidWithOutGstFtdate(username, fdate, tdate);

				} else {
					invoicelist = invoiceFetchingRepository.findByOpidFtdate(username, fdate, tdate);

				}

			} else if (role.equalsIgnoreCase("USER")) {
				invoicelist = invoiceFetchingRepository.findByUseridFtdate(username, fdate, tdate);

			}

		} else {

			if (role.equalsIgnoreCase("ADMIN") || role.equalsIgnoreCase("EMPLOYEE")) {

				if (subrole.equalsIgnoreCase("OPERATOR")) {
					invoicelist = invoiceFetchingRepository.findAllOrderByOpid();

				} else if (subrole.equalsIgnoreCase("USER")) {

					if (gstin.equalsIgnoreCase("WITH")) {
						invoicelist = invoiceFetchingRepository.findAllByWithGst();

					} else if (gstin.equalsIgnoreCase("WITHOUT")) {
						invoicelist = invoiceFetchingRepository.findAllByWithOutGst();
					} else {
						invoicelist = invoiceFetchingRepository.findAllByOrderByInvoiceDate();
					}

				}

			} else if (role.equalsIgnoreCase("OPERATOR")) {

				if (gstin.equalsIgnoreCase("WITH")) {
					invoicelist = invoiceFetchingRepository.findByOpidWithGst(username);

				} else if (gstin.equalsIgnoreCase("WITHOUT")) {
					invoicelist = invoiceFetchingRepository.findByOpidWithOutGst(username);

				} else {
					invoicelist = invoiceFetchingRepository.findByOpid(username);
				}

			} else if (role.equalsIgnoreCase("USER")) {
				Userinfo usrobj = userinfoRepository.findByUsername(username);
				if (usrobj.getId() > 0) {
					invoicelist = invoiceFetchingRepository.findByUseridOrderByInvoiceDate(usrobj.getId());
				}
			}
		}

		if (invoicelist.size() > 0) {

			for (InvoiceFetching obj : invoicelist) {

				InvoiceFetching obj1 = invoiceFetchingRepository.getUserDetails(obj.getUserid(), obj.getReferenceid());

				if (obj1 != null) {
					obj.setFullname(obj1.getFullname());
					obj.setUsername(obj1.getUsername());
					obj.setOpname(obj1.getOpname());
					obj.setOpid(obj1.getOpid());
					obj.setPlanName(obj1.getPlanName());
				}
			}
			return invoicelist;
		}

		return null;
	}

	@Override
	public InvoiceDTO downloadInvoice(String role, String username, int invoiceid, String gstin) {

		System.out.println(gstin);
		if (gstin != null && !gstin.equals("")) {

			String usergstin = userinfoRepository.getGstNumberByUser(username);

			if (usergstin == null) {
				Userinfo userobj = userinfoRepository.findByUsername(username);
				userobj.setGstin(gstin);
				userobj.setUpdatedate(Dateformat.getCurrentDatetime());
				userinfoRepository.save(userobj);
			} else {

				if (!usergstin.equals(gstin)) {
					Userinfo userobj = userinfoRepository.findByUsername(username);
					userobj.setGstin(gstin);
					userobj.setUpdatedate(Dateformat.getCurrentDatetime());
					userinfoRepository.save(userobj);
				}
				System.out.println("Nothing works");
			}

			Invoice invoiceobj = invoiceRepository.findById(invoiceid);

			invoiceobj.setGstin(gstin);
			invoiceobj.setUpdatedate(Dateformat.getCurrentDatetime());
			invoiceRepository.save(invoiceobj);

		}

		Clientinfo clientobj=clientinfoRepository.findOneClient();
		
		if(clientobj.getClientname().equalsIgnoreCase("RIDSYS")) {
			clientobj.setClientname("R");
			clientobj.setCompany("MRRidsys Tech Pvt Ltd");
			clientobj.setMobile1(clientobj.getPhone()+"/"+clientobj.getMobile1());
		}
		
		if (role != null && username != null && invoiceid > 0) {
			Userinfo usrobj = userinfoRepository.findByUsername(username);

			if (usrobj.getId() > 0) {
				InvoiceFetching invobj = invoiceFetchingRepository.findById(invoiceid);
				String plandiscount = "0";
				try {
					plandiscount = quotaplanmanagelogRepository.findByRechargeid(invobj.getReferenceid())
							.getPlandiscount();
				} catch (Exception e) {
					plandiscount = "0";
				}


				InvoiceDTO inv = new InvoiceDTO();
				inv.setTitle("Tax Invoice");
				inv.setSubTitle("(Original for the Receipt)");
				inv.setClientname(clientobj.getClientname());
//				inv.setProviderInfo("MRRidsys Technologies Pvt Ltd",
//						"No.21, First Floor, Jansi street, Indiragandhi nagar, Pondicherry-605 001.", "041-32243900",
//						"support@ridsys.com", "34AAKCM2741H1ZJ");
//				
				inv.setProviderInfo(clientobj.getCompany(),clientobj.getClientname(),clientobj.getAddress(),clientobj.getMobile1(),clientobj.getEmail(),clientobj.getGstin());

				inv.setUserInfo(usrobj.getFirstname().toUpperCase() + " " + usrobj.getLastname().toUpperCase(),
						usrobj.getAddress(), usrobj.getZip(), usrobj.getMobilephone(), "NA");

//				inv.setUserBillInfo(usrobj.getUsername(), String.valueOf(usrobj.getId()),
//						"R-NET/" + invobj.getFinancial_year() + "/" + invobj.getInvoice_no(), invobj.getInvoiceDate(),
//						invobj.getInvoice_period(), invobj.getDue_date().toString());

				inv.setUserBillInfo(usrobj.getUsername(), String.valueOf(usrobj.getId()),
						invobj.getFinancial_year() + "/" + invobj.getInvoice_no(), invobj.getInvoiceDate(),
						invobj.getInvoice_period(), invobj.getDue_date().toString());
				inv.setPreviousDue(invobj.getPrevious_due());
				inv.setPaymentsReceived(invobj.getPayment_recevied());
				inv.setAdjustments(invobj.getAdjustments());
				inv.setInvoiceAmount(invobj.getInvoice_amount());
				inv.setBalanceAmount(invobj.getBalance_amount());
				inv.setPocketAmountBalance(invobj.getPacket_amount_balance());
				inv.setAccountNo(String.valueOf(invobj.getUserid()));
				inv.setUserId(usrobj.getUsername());
				inv.setGstin(invobj.getGstin());

				double totalTaxableAmount = 0;
				double totalCGSTAmount = 0;
				double totalSGSTAmount = 0;
				double amountInclTax = 0;

				List<InvoiceChargesList> invoiceChargesList = new ArrayList<>();

				for (Invoice_items ii : invobj.getInvoice_items()) {
					totalTaxableAmount += ii.getRate();
					totalCGSTAmount += ii.getCgst_rate();
					totalSGSTAmount += ii.getSgst_rate();
					amountInclTax += ii.getAmount_incl_tax();

					InvoiceChargesList invch = new InvoiceChargesList();
					invch.setTxnNo(String.valueOf(invobj.getInvoice_no()));
					invch.setTxnDate(invobj.getDue_date().toString());
					invch.setPeriodDate(invobj.getInvoice_period());
					invch.setDescription(ii.getDiscription());
					invch.setHsnCode(ii.getHsn_code());
					invch.setPlanDescription(ii.getPlan_desc());
					invch.setRate(String.valueOf(df.format(ii.getRate())));
					invch.setUnitPeriod(ii.getPeriod_days() + " Days");
					invch.setTaxableAmount(String.valueOf(df.format(ii.getRate())));
					invch.setCgstRateInPct(String.valueOf(df.format(ii.getCgst_perc())));
					invch.setCgstAmount(String.valueOf(df.format(ii.getCgst_rate())));
					invch.setSgstRateInPct(String.valueOf(df.format(ii.getSgst_perc())));
					invch.setSgstAmount(String.valueOf(df.format(ii.getSgst_rate())));
					invch.setAmountInclTax(String.valueOf(df.format(ii.getAmount_incl_tax())));
					invch.setDiscountCost(plandiscount);
					invoiceChargesList.add(invch);
				}

				inv.setInvoiceCharges(String.valueOf(df.format(totalTaxableAmount)),
						String.valueOf(df.format(totalCGSTAmount)), String.valueOf(df.format(totalSGSTAmount)),
						String.valueOf(df.format(amountInclTax)),plandiscount, invoiceChargesList);

				Map<String, String> paymentReceived = new HashMap<>();
				paymentReceived.put("refNo", String.valueOf(invobj.getInvoice_no()));
				paymentReceived.put("txnDate", invobj.getDue_date().toString());
				paymentReceived.put("details", "Cash Payment");
				paymentReceived.put("amount", String.valueOf(df.format(amountInclTax)));
				paymentReceived.put("total", String.valueOf(df.format(amountInclTax)));
				paymentReceived.put("remarks", "Quota Plan Activation");

				inv.setPaymentReceived(String.valueOf(df.format(amountInclTax)),
						String.valueOf(df.format(amountInclTax)), paymentReceived);
				inv.setRegisteredOfficeAddress(
						"No.21, First Floor, Jansi street, Indiragandhi nagar, Pondicherry-605 001.");

				return inv;
			}
		}
		return null;
	}

	@Override
	public Map<String, String> getGstStatus(String username, int invoiceno) {

		String gstin = userinfoRepository.getGstNumberByUser(username);

		String invoiceGstin = invoiceRepository.getInvoiceGstinByUser(invoiceno);

		Map<String, String> map = new HashMap<>();

//		if (gstin == null && gstin.equals(" ")) {
//			System.out.println("came into first loop");
//			gstin = "";
//		}
//
//		if (invoiceGstin == null && invoiceGstin.equals(" ")) {
//			System.out.println("came into second loop");
//			invoiceGstin = "";
//		}

		map.put("gstin", gstin);
		map.put("invoiceGstin", invoiceGstin);

		return map;

	}
}
