package com.ridsys.rib.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.decimal4j.util.DoubleRounder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ridsys.rib.DTO.PlanCostDTO;
import com.ridsys.rib.DTO.QuotaplanmanageloghistoryDTO;
import com.ridsys.rib.DTO.SubscriptionInfoDTO;
import com.ridsys.rib.comm.Bandwidth;
import com.ridsys.rib.comm.Dateformat;
import com.ridsys.rib.comm.IdGen;
import com.ridsys.rib.comm.InvoicePeriodGenerator;
import com.ridsys.rib.models.Billing_plans_profiles;
import com.ridsys.rib.models.Duplicatesubplan;
import com.ridsys.rib.models.Graceperiodextendhistory;
import com.ridsys.rib.models.Invoice;
import com.ridsys.rib.models.InvoiceFetching;
import com.ridsys.rib.models.Invoice_items;
import com.ridsys.rib.models.Online_payment_log;
import com.ridsys.rib.models.Online_plan_payment;
import com.ridsys.rib.models.Operators;
import com.ridsys.rib.models.Quotaplan;
import com.ridsys.rib.models.Quotaplancancelhistory;
import com.ridsys.rib.models.Quotaplanlcoprices;
import com.ridsys.rib.models.Quotaplanmanagelog;
import com.ridsys.rib.models.Quotasubplan;
import com.ridsys.rib.models.Radcheck;
import com.ridsys.rib.models.Radusergroup;
import com.ridsys.rib.models.Renewalhistory;
import com.ridsys.rib.models.Userbillinfo;
import com.ridsys.rib.models.Userinfo;
import com.ridsys.rib.models.Walletupdatelog;
import com.ridsys.rib.payload.request.CancelInvoice;
import com.ridsys.rib.payload.request.QuatoplanactivationRequest;
import com.ridsys.rib.payload.response.MessageResponse;
import com.ridsys.rib.repository.Billing_plansRepository;
import com.ridsys.rib.repository.Billing_plans_profilesRepository;
import com.ridsys.rib.repository.DuplicatesubplanRepository;
import com.ridsys.rib.repository.GraceperiodextendhistoryRepository;
import com.ridsys.rib.repository.InvoiceItemsRepository;
import com.ridsys.rib.repository.InvoiceRepository;
import com.ridsys.rib.repository.Online_payment_logRepository;
import com.ridsys.rib.repository.Online_plan_paymentRepository;
import com.ridsys.rib.repository.OperatorsRepository;
import com.ridsys.rib.repository.QuotaplanRepository;
import com.ridsys.rib.repository.QuotaplancanclehistoryRepository;
import com.ridsys.rib.repository.QuotaplanlcopricesRepository;
import com.ridsys.rib.repository.QuotaplanmanagelogRepository;
import com.ridsys.rib.repository.QuotasubplanRepository;
import com.ridsys.rib.repository.RadcheckRepository;
import com.ridsys.rib.repository.RadusergroupRepository;
import com.ridsys.rib.repository.RenewalhistoryRepository;
import com.ridsys.rib.repository.UserbillinfoRepository;
import com.ridsys.rib.repository.UserinfoRepository;
import com.ridsys.rib.repository.WalletupdatelogRepository;
import com.ridsys.rib.service.QuotaplanmanagingService;

@Service
public class QuotaplanmanagingServiceImpl implements QuotaplanmanagingService {

	@Autowired
	Billing_plansRepository billing_plansRepository;

	@Autowired
	Billing_plans_profilesRepository billing_plans_profilesRepository;

	@Autowired
	RadcheckRepository radcheckRepository;

	@Autowired
	WalletupdatelogRepository walletupdatelogRepository;

	@Autowired
	QuotaplanmanagelogRepository quotaplanmanagelogRepository;

	@Autowired
	UserinfoRepository userinfoRepository;

	@Autowired
	UserbillinfoRepository userbillinfoRepository;

	@Autowired
	OperatorsRepository operatorsRepository;

	@Autowired
	RadusergroupRepository radusergroupRepository;

	@Autowired
	InvoiceRepository invoiceRep;

	@Autowired
	InvoiceItemsRepository invoiceItemsRep;

	@Autowired
	QuotaplanRepository quotaplanRepository;

	@Autowired
	QuotasubplanRepository quotasubplanRepository;

	@Autowired
	QuotaplanlcopricesRepository quotaplanlcopricesRepository;

	@Autowired
	GraceperiodextendhistoryRepository graceperiodextendhistoryRepository;

	@Autowired
	Online_plan_paymentRepository online_plan_paymentRepository;

	@Autowired
	DuplicatesubplanRepository duplicatesubplanRepository;

	@Autowired
	QuotaplancanclehistoryRepository quotaplancanclehistoryRepository;

	@Autowired
	RenewalhistoryRepository renewalhistoryRepository;

	@Override
	public ResponseEntity<?> quotaPlanActive(@Valid QuatoplanactivationRequest robj) {

//		int quotadays = Integer.parseInt(robj.getQuotadays());

		if (!userbillinfoRepository.existsByUsername(robj.getCus_username())) {
			return ResponseEntity.badRequest().body(new MessageResponse("This customer is not verified!."));
		}

		if (!userinfoRepository.existsByUsername(robj.getCus_username())) {
			return ResponseEntity.badRequest().body(new MessageResponse("This customer is not authorized!.."));
		}

//		if (quotaplanmanagelogRepository.checkExpiry(robj.getCus_username())) {
//			return ResponseEntity.badRequest().body(new MessageResponse("Previous plan is not expired!."));
//		}

//		if (quotadays < 30) {
//			return ResponseEntity.badRequest().body(new MessageResponse("Quota days Should be at least 30 days!."));
//		}
		if (Integer.parseInt(robj.getPlanid()) < 1) {
			return ResponseEntity.badRequest().body(new MessageResponse("Please select a plan"));
		}
		if (!billing_plansRepository.existsById(Integer.parseInt(robj.getPlanid()))) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Your selected plan is currently not available!."));
		}
//		if (!operatorsRepository.existsByUsername(robj.getCreationbyusername())) {
//			return ResponseEntity.badRequest()
//					.body(new MessageResponse("Internal Error: Activator username is incorrect!."));
//		}
//		if (!robj.getCreationby().equalsIgnoreCase("OPERATOR") || !robj.getCreationby().equalsIgnoreCase("ADMIN")
//				|| !robj.getCreationby().equalsIgnoreCase("USER")
//				|| !robj.getCreationby().equalsIgnoreCase("MODERATOR")) {
//			return ResponseEntity.badRequest()
//					.body(new MessageResponse("Internal Error!."));
//		}	

		PlanCostDTO planCostDet = billing_plansRepository.findCostDetailsById(robj.getPlanid());

		final String creationdate = Dateformat.getCurrentDatetime();// date & time
		final String RadcheckQuotaExpiryDate = Dateformat.expiration(planCostDet.getPlantimebank());// date for radcheck
																									// table
		final String quotaExpiryDate = Dateformat.expiryDate(planCostDet.getPlantimebank());// date & time
		final String planrechargeid = IdGen.genID("PRC");// recharge unique ID Generation

		double plancost = planCostDet.getPlancost();
		double plantax = planCostDet.getPlantax();
		double planlcocomm = planCostDet.getPlanlcocomm();
		double msoamount = planCostDet.getPlanmsocost();
		double plancuscost = planCostDet.getPlancuscost();

		String walletbalancestr = "0";
		double walletbalance = 0;// Double.parseDouble(walletbalancestr);
		double newbalance = 0;// DoubleRounder.round((walletbalance - msoamount), 2);

		// Operator Walletupdatelog
		Walletupdatelog opwul = new Walletupdatelog();

		// User Walletupdatelog
		Walletupdatelog uswul = new Walletupdatelog();

		Operators opobj = new Operators();
		Userinfo usrobj = userinfoRepository.findByUsername(robj.getCus_username());

		if (robj.getCreationby().equalsIgnoreCase("OPERATOR")) {

			opobj = operatorsRepository.findByUsername(robj.getCreationbyusername());
			if (Double.parseDouble(opobj.getWalletbalance()) < msoamount) {
				return ResponseEntity.badRequest().body(new MessageResponse("Insufficient wallet balance!."));
			}
			walletbalancestr = opobj.getWalletbalance();
			walletbalance = Double.parseDouble(walletbalancestr);
			newbalance = DoubleRounder.round((walletbalance - msoamount), 2);

			// Deduct from operator log
			opwul = new Walletupdatelog("OPERATOR", opobj.getUsername(), walletbalancestr, String.valueOf(msoamount),
					String.valueOf(newbalance), planrechargeid, 2, 2, robj.getCreationby(),
					robj.getCreationbyusername(), creationdate);

			walletupdatelogRepository.save(opwul);
			operatorsRepository.walletBalanceDetection(opobj.getId(), msoamount);

		} else if (robj.getCreationby().equalsIgnoreCase("USER")) {

			if (Double.parseDouble(usrobj.getWalletbalance()) < plancuscost) {
				return ResponseEntity.badRequest().body(new MessageResponse("Insufficient wallet balance!."));
			}
			walletbalancestr = usrobj.getWalletbalance();
			walletbalance = Double.parseDouble(walletbalancestr);
			newbalance = DoubleRounder.round((walletbalance - plancuscost), 2);

			// Deduct from user log
			uswul = new Walletupdatelog("USER", robj.getCus_username(), walletbalancestr, String.valueOf(plancuscost),
					String.valueOf(newbalance), planrechargeid, 2, 4, robj.getCreationby(),
					robj.getCreationbyusername(), creationdate);

			opobj = operatorsRepository.findById(usrobj.getOpid());
			walletbalancestr = opobj.getWalletbalance();
			walletbalance = Double.parseDouble(walletbalancestr);
			newbalance = DoubleRounder.round((walletbalance + planlcocomm), 2);

			// Credit to operator log
			opwul = new Walletupdatelog("OPERATOR", opobj.getUsername(), walletbalancestr, String.valueOf(planlcocomm),
					String.valueOf(newbalance), planrechargeid, 2, 1, robj.getCreationby(),
					robj.getCreationbyusername(), creationdate);

			walletupdatelogRepository.save(uswul);
			userinfoRepository.walletBalanceDetection(usrobj.getId(), plancuscost);

			walletupdatelogRepository.save(opwul);
			operatorsRepository.walletBalanceCredit(opobj.getId(), planlcocomm);

		}

		// Quotaplanmanagelog
		Quotaplanmanagelog logobj = new Quotaplanmanagelog(planrechargeid, robj.getCus_username(), robj.getPlanid(),
				creationdate, quotaExpiryDate, String.valueOf(planCostDet.getPlantimebank()), String.valueOf(plancost),
				"", String.valueOf(plantax), String.valueOf(planCostDet.getPlanmrp()),
				String.valueOf(planCostDet.getPlandiscount()), String.valueOf(planCostDet.getPlancuscost()),
				String.valueOf(msoamount), String.valueOf(planlcocomm), robj.getCreationby(),
				robj.getCreationbyusername(), "Plan activation");

		// Radcheck
		Radcheck rcobj = new Radcheck(robj.getCus_username(), "Expiration", ":=", RadcheckQuotaExpiryDate);

		// Firstime Activation
		if ((radcheckRepository.existsByUsernameAttribute(robj.getCus_username(), "Cleartext-Password")) == 0) {
			Userinfo obj = userinfoRepository.findByUsername(robj.getCus_username());
			Radcheck radcheck = new Radcheck();
			radcheck.setUsername(obj.getUsername());
			radcheck.setAttribute("Cleartext-Password");
			radcheck.setOp(":=");
			radcheck.setValue(obj.getPassword());
			// insert username/password
			radcheckRepository.save(radcheck);
		}

		List<Billing_plans_profiles> profilelist = billing_plans_profilesRepository
				.findAllByPlanname(planCostDet.getPlanname());
		List<Radusergroup> ruglist = new ArrayList<>();

		for (Billing_plans_profiles bpp : profilelist) {

			Radusergroup rug = new Radusergroup(robj.getCus_username(), bpp.getProfilename(), 0);
			ruglist.add(rug);
		}

		quotaplanmanagelogRepository.save(logobj);

		userbillinfoRepository.updateCurrentPlanDetailsForUser(robj.getCus_username(), planCostDet.getPlanname(),
				robj.getPlanid(), creationdate, quotaExpiryDate);

		radcheckRepository.deleteByUsernameAttribute(robj.getCus_username(), "Expiration");

		radcheckRepository.save(rcobj);

		radusergroupRepository.deleteByUsername(robj.getCus_username());

		radusergroupRepository.saveAll(ruglist);

		try {
			Byte cgst_perc = 9;
			Byte sgst_perc = 9;
			String invoice_period = InvoicePeriodGenerator.generate();
			Invoice inv = new Invoice();
			inv.setReferenceid(planrechargeid);
			inv.setUserid(usrobj.getId());
			inv.setFinancial_year(invoice_period);
			inv.setType_id(1);
			inv.setStatus_id(5);
			inv.setInvoice_date(creationdate);
			inv.setInvoice_period(Dateformat.ddmmyyyy(creationdate.substring(0, 10)) + " to "
					+ Dateformat.ddmmyyyy(quotaExpiryDate.substring(0, 10)));
			inv.setDue_date(creationdate);
			inv.setPrevious_due(0);
			inv.setPayment_recevied(plancuscost);
			inv.setAdjustments(0);
			inv.setInvoice_amount(plancuscost);
			inv.setBalance_amount(0);
			inv.setPacket_amount_balance(0);
			inv.setCreationdate(creationdate);
			inv = invoiceRep.save(inv);

			int invoice_id = invoiceRep.getRecentIdByInvoiceNo(inv.getInvoice_no());

			List<Invoice_items> invoice_items = new ArrayList<Invoice_items>();
			Invoice_items invitm = new Invoice_items();
			invitm.setInvoice_id(invoice_id);
			invitm.setTxn_date(inv.getInvoice_date());
			invitm.setPeriod_date(inv.getInvoice_period());
			invitm.setHsn_code("9984");
			invitm.setPlanid(Integer.parseInt(robj.getPlanid()));
			invitm.setPlan_desc("Subscription Charges");
			invitm.setPeriod_days(planCostDet.getPlantimebank());
			invitm.setRate(plancost);
			invitm.setCgst_perc(cgst_perc);
			invitm.setCgst_rate(DoubleRounder.round((plancost / 100) * cgst_perc, 2));
			invitm.setSgst_perc(sgst_perc);
			invitm.setSgst_rate(DoubleRounder.round((plancost / 100) * sgst_perc, 2));
			invitm.setAmount_incl_tax(plancuscost);
			invitm.setDiscription(planCostDet.getPlanname());
			invitm.setCreationbyusername(robj.getCreationbyusername());
			invitm.setCreationbyrole(robj.getCreationby());
			invitm.setCreationdate(creationdate);

			invoice_items.add(invitm);
			invoiceItemsRep.saveAll(invoice_items);
		} catch (Exception e) {
			System.out.println("Exp: " + e.getMessage());
		} finally {

		}

		return ResponseEntity.ok(new MessageResponse("Plan activated"));
	}

	@Override
	public List<QuotaplanmanageloghistoryDTO> getQuotaPlanManageLogHistoryByRoleCreationbyusername(String role,
			String username, String fdate, String tdate) {

		List<QuotaplanmanageloghistoryDTO> lst = new ArrayList<>();

		if (!fdate.equals("0000-00-00") && !tdate.equals("0000-00-00")) {

			if (role.equals("ADMIN") || role.equals("EMPLOYEE")) {
				lst = quotaplanmanagelogRepository.getQuotaPlanManageLogHistoryFtdate(fdate, tdate);
			} else if (role.equalsIgnoreCase("OPERATOR")) {
				lst = quotaplanmanagelogRepository.getQuotaPlanManageLogHistoryByRoleCreationbyusernameFtdate(username,
						fdate, tdate);
			} else {
				lst = quotaplanmanagelogRepository.getQuotaPlanManageLogHistoryByUserFtdate(username, fdate, tdate);
			}
		} else {

			if (role.equals("ADMIN") || role.equals("EMPLOYEE")) {
				lst = quotaplanmanagelogRepository.getQuotaPlanManageLogHistory();
			} else if (role.equalsIgnoreCase("OPERATOR")) {
				lst = quotaplanmanagelogRepository.getQuotaPlanManageLogHistoryByRoleCreationbyusername(username);
			} else {
				lst = quotaplanmanagelogRepository.getQuotaPlanManageLogHistoryByUser(username);
			}
		}

		return lst;
	}

	@Override
	public List<QuotaplanmanageloghistoryDTO> getQuotaPlanManageLogHistoryForOpAndUser(String role, String username,
			String fdate, String tdate) {

		List<QuotaplanmanageloghistoryDTO> lst = new ArrayList<>();

		if (!fdate.equals("0000-00-00") && !tdate.equals("0000-00-00")) {

			if (role.equalsIgnoreCase("OPERATOR")) {
				if (username.equalsIgnoreCase("ALL")) {
					lst = quotaplanmanagelogRepository.getQuotaPlanManageLogHistoryByAllOpidFtdate(role, fdate, tdate);
				} else {
					lst = quotaplanmanagelogRepository
							.getQuotaPlanManageLogHistoryByRoleCreationbyusernameFtdate(username, fdate, tdate);
				}
			} else {
				if (username.equalsIgnoreCase("ALL")) {
					lst = quotaplanmanagelogRepository.getQuotaPlanManageLogHistoryByAllUserFtdate(role, fdate, tdate);
				} else {
					lst = quotaplanmanagelogRepository.getQuotaPlanManageLogHistoryByCreationUserFtdate(username, role,
							fdate, tdate);
				}
			}
		} else {

			if (role.equalsIgnoreCase("OPERATOR")) {
				if (username.equalsIgnoreCase("ALL")) {
					lst = quotaplanmanagelogRepository.getQuotaPlanManageLogHistoryByAllOpid(role);
				} else {
					lst = quotaplanmanagelogRepository.getQuotaPlanManageLogHistoryByRoleCreationbyusername(username);
				}
			} else {
				if (username.equalsIgnoreCase("ALL")) {
					lst = quotaplanmanagelogRepository.getQuotaPlanManageLogHistoryByAllUser(role);
				} else {
					lst = quotaplanmanagelogRepository.getQuotaPlanManageLogHistoryByCreationUser(username, role);
				}
			}
		}

		return lst;
	}

	@Override
	public SubscriptionInfoDTO getSubscriptionInfoByRoleCreationbyusername(String username) {

		SubscriptionInfoDTO si1 = quotaplanmanagelogRepository.getSubscriptionInfoByCreationbyusername(username);
		if (si1 != null) {
			SubscriptionInfoDTO si2 = quotaplanmanagelogRepository.getDatausageByCreationbyusername(username);
			si1.setUploadbw(Bandwidth.calBWDecimal(Long.valueOf(si2.getUploadbw())));
			si1.setDownloadbw(Bandwidth.calBWDecimal(Long.valueOf(si2.getDownloadbw())));
		} else {
			si1 = new SubscriptionInfoDTO();
			si1.setDownloadbw("0.0 B");
			si1.setUploadbw("0.0 B");
		}
		return si1;
	}

	@Override
	public List<Quotaplanmanagelog> getSubscriberPackageHistory(String username, String role) {

		if (role.equalsIgnoreCase("ADMIN")) {
			return quotaplanmanagelogRepository.getSusbcriberPackageHistoryByAdmin();
		}
		return null;
	}

	@Override
	public ResponseEntity<?> quotaPlanActive2(@Valid QuatoplanactivationRequest robj) {
		try {

			System.out.println(robj.getStartdate() + "   " + robj.getExpirydate());

			Operators opobj = new Operators();
			String quotaExpiryDate = "";
			String RadcheckQuotaExpiryDate = "";
			String quotaStartDate = "";
			String invoicedate = "";
			boolean isrenewal = false;

			final String creationdate = Dateformat.getCurrentDatetime();// date & time

			if (!userbillinfoRepository.existsByUsername(robj.getCus_username())) {
				return ResponseEntity.badRequest().body(new MessageResponse("This customer is not verified!."));
			}

			if (!userinfoRepository.existsByUsername(robj.getCus_username())) {
				return ResponseEntity.badRequest().body(new MessageResponse("This customer is not authorized!.."));
			}

			if (Integer.parseInt(robj.getPlanid()) < 1) {
				return ResponseEntity.badRequest().body(new MessageResponse("Please select a plan"));
			}
			if (!quotasubplanRepository.existsById(Integer.parseInt(robj.getPlanid()))) {
				return ResponseEntity.badRequest()
						.body(new MessageResponse("Your selected plan is currently not available!."));
			}

			Userinfo usrobj = userinfoRepository.findByUsername(robj.getCus_username());

			opobj = operatorsRepository.findById(usrobj.getOpid());

			Quotasubplan subobj = new Quotasubplan();

			if (duplicatesubplanRepository.existsBySubplanidAndOpid(Integer.parseInt(robj.getPlanid()),
					opobj.getId())) {

				Duplicatesubplan duplicateobj = duplicatesubplanRepository
						.findBySubplanidAndOpid(Integer.parseInt(robj.getPlanid()), opobj.getId());

				subobj.setMrp(duplicateobj.getMrp());
				subobj.setPlanid(duplicateobj.getPlanid());
				subobj.setPlantimebank(duplicateobj.getPlantimebank());
				subobj.setPrice(duplicateobj.getPrice());
				subobj.setSubplan(duplicateobj.getSubplan());
				subobj.setTax(duplicateobj.getTax());
				subobj.setTaxcost(duplicateobj.getTaxcost());
				subobj.setId(duplicateobj.getSubplanid());

			} else {
				subobj = quotasubplanRepository.findById(Integer.parseInt(robj.getPlanid()));
			}

			Quotaplanlcoprices priceobj = quotaplanlcopricesRepository
					.findBySubplanidAndOpid(Integer.parseInt(robj.getPlanid()), opobj.getId());

			Userbillinfo userbillobj = userbillinfoRepository.findByUsername(robj.getCus_username());

			// checking previous plan expirydate
			if (userbillobj.getQuotaexpirydate() != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date preexdate = sdf.parse(userbillobj.getQuotaexpirydate());
				Date curdate = sdf.parse(Dateformat.getCurrentDatetime());
				if (robj.getExpirydate() != null && robj.getStartdate() != null && !robj.getExpirydate().equals("")
						&& !robj.getStartdate().equals("")) {

					quotaExpiryDate = robj.getExpirydate();

					Date plancreationdate = sdf.parse(Dateformat.getCurrentDatetime());

					Date d = new SimpleDateFormat("yyyy-MM-dd").parse(quotaExpiryDate);
					RadcheckQuotaExpiryDate = new SimpleDateFormat("dd MMM yyyy").format(d) + " 23:59:59";

					Date exdate = sdf.parse(userbillobj.getQuotaexpirydate());

					if (preexdate.after(plancreationdate)) {

						invoicedate = Dateformat.getNextDay(exdate);
						quotaStartDate = userbillobj.getQuotastartdate();
						isrenewal = true;

					} else if (preexdate.before(plancreationdate)) {

						invoicedate = Dateformat.getCurrentDatetime();
						quotaStartDate = Dateformat.getCurrentDatetime();

					}
				} else {

					if (preexdate.after(curdate)) {

						if (userbillobj.getPlanid() != Integer.parseInt(robj.getPlanid())) {
							return ResponseEntity.badRequest().body(new MessageResponse(
									"Your current plan is not Expired! please select same plan for Extend!"));
						}

						quotaExpiryDate = LocalDate.parse(userbillobj.getQuotaexpirydate().substring(0, 10))
								.plusDays(Integer.parseInt(subobj.getPlantimebank())).toString() + " 23:59:59";

						Date d = new SimpleDateFormat("yyyy-MM-dd").parse(quotaExpiryDate);
						RadcheckQuotaExpiryDate = new SimpleDateFormat("dd MMM yyyy").format(d) + " 23:59:59";

						Date exdate = sdf.parse(userbillobj.getQuotaexpirydate());
						invoicedate = Dateformat.getNextDay(exdate);

						quotaStartDate = userbillobj.getQuotastartdate();
						isrenewal = true;
					}
					if (preexdate.before(curdate)) {
						RadcheckQuotaExpiryDate = Dateformat.expiration(Integer.parseInt(subobj.getPlantimebank()));
						quotaExpiryDate = Dateformat.expiryDate(Integer.parseInt(subobj.getPlantimebank()));
						quotaStartDate = creationdate;
						invoicedate = creationdate;
					}
				}
			} else {
				RadcheckQuotaExpiryDate = Dateformat.expiration(Integer.parseInt(subobj.getPlantimebank()));
				quotaExpiryDate = Dateformat.expiryDate(Integer.parseInt(subobj.getPlantimebank()));
				quotaStartDate = creationdate;
				invoicedate = creationdate;
			}

			if (priceobj == null) {
				priceobj = new Quotaplanlcoprices();
				priceobj = quotaplanlcopricesRepository.findBySubplanidAndOpid(Integer.parseInt(robj.getPlanid()), 0);

			}

			final String planrechargeid = IdGen.genID("PRC");// recharge unique ID Generation

			double plancost = Double.parseDouble(subobj.getPrice());
			double plantax = Double.parseDouble(subobj.getTaxcost());
			double planlcocomm = Double.parseDouble(priceobj.getPlanlcocom());
			double msoamount = Double.parseDouble(priceobj.getPlanmsocom());
			double plancuscost = Double.parseDouble(priceobj.getPlancuscost());

			String walletbalancestr = "0";
			double walletbalance = 0;// Double.parseDouble(walletbalancestr);
			double newbalance = 0;// DoubleRounder.round((walletbalance - msoamount), 2);

			// Operator Walletupdatelog
			Walletupdatelog opwul = new Walletupdatelog();

			// User Walletupdatelog
			Walletupdatelog uswul = new Walletupdatelog();

			if (robj.getPaymentType().equalsIgnoreCase("online")) {
				if (robj.getCreationby().equalsIgnoreCase("OPERATOR")) {

					opobj = operatorsRepository.findById(usrobj.getOpid());
					walletbalancestr = opobj.getWalletbalance();
					walletbalance = Double.parseDouble(walletbalancestr);
					newbalance = DoubleRounder.round((walletbalance + planlcocomm), 2);

					opwul = new Walletupdatelog("OPERATOR", opobj.getUsername(), walletbalancestr,
							String.valueOf(planlcocomm), String.valueOf(newbalance), planrechargeid, 2, 1,
							robj.getCreationby(), robj.getCreationbyusername(), creationdate);

					operatorsRepository.walletBalanceCredit(opobj.getId(), planlcocomm);
					walletupdatelogRepository.save(opwul);

				} else if (robj.getCreationby().equalsIgnoreCase("USER")) {

					opobj = operatorsRepository.findById(usrobj.getOpid());
					walletbalancestr = opobj.getWalletbalance();
					walletbalance = Double.parseDouble(walletbalancestr);
					newbalance = DoubleRounder.round((walletbalance + planlcocomm), 2);

					opwul = new Walletupdatelog("OPERATOR", opobj.getUsername(), walletbalancestr,
							String.valueOf(planlcocomm), String.valueOf(newbalance), planrechargeid, 2, 1,
							robj.getCreationby(), robj.getCreationbyusername(), creationdate);

					operatorsRepository.walletBalanceCredit(opobj.getId(), planlcocomm);
					walletupdatelogRepository.save(opwul);

				}

				try {
					Online_plan_payment planpaymentobj = online_plan_paymentRepository
							.findByTransactionid(robj.getTransactionid());

					planpaymentobj.setReferenceid(planrechargeid);
					planpaymentobj.setUpdateddate(Dateformat.getCurrentDatetime());
					online_plan_paymentRepository.save(planpaymentobj);
				} catch (Exception e) {
					System.out.println("Exception at planpayment obj: " + e);
				}

			} else if (robj.getPaymentType().equalsIgnoreCase("offline")) {

				if (robj.getCreationby().equalsIgnoreCase("OPERATOR")) {

					if (Double.parseDouble(opobj.getWalletbalance()) < msoamount) {
						return ResponseEntity.badRequest().body(new MessageResponse("Insufficient wallet balance!."));
					}
					walletbalancestr = opobj.getWalletbalance();
					walletbalance = Double.parseDouble(walletbalancestr);
					newbalance = DoubleRounder.round((walletbalance - msoamount), 2);

					// Deduct from operator log
					opwul = new Walletupdatelog("OPERATOR", opobj.getUsername(), walletbalancestr,
							String.valueOf(msoamount), String.valueOf(newbalance), planrechargeid, 2, 2,
							robj.getCreationby(), robj.getCreationbyusername(), creationdate);

					walletupdatelogRepository.save(opwul);
					operatorsRepository.walletBalanceDetection(opobj.getId(), msoamount);

				} else if (robj.getCreationby().equalsIgnoreCase("USER")) {

					if (Double.parseDouble(usrobj.getWalletbalance()) < plancuscost) {
						return ResponseEntity.badRequest().body(new MessageResponse("Insufficient wallet balance!."));
					}
					walletbalancestr = usrobj.getWalletbalance();
					walletbalance = Double.parseDouble(walletbalancestr);
					newbalance = DoubleRounder.round((walletbalance - plancuscost), 2);

					// Deduct from user log
					uswul = new Walletupdatelog("USER", robj.getCus_username(), walletbalancestr,
							String.valueOf(plancuscost), String.valueOf(newbalance), planrechargeid, 2, 4,
							robj.getCreationby(), robj.getCreationbyusername(), creationdate);
					opobj = operatorsRepository.findById(usrobj.getOpid());
					walletbalancestr = opobj.getWalletbalance();
					walletbalance = Double.parseDouble(walletbalancestr);
					newbalance = DoubleRounder.round((walletbalance + planlcocomm), 2);

					// Credit to operator log
					opwul = new Walletupdatelog("OPERATOR", opobj.getUsername(), walletbalancestr,
							String.valueOf(planlcocomm), String.valueOf(newbalance), planrechargeid, 2, 1,
							robj.getCreationby(), robj.getCreationbyusername(), creationdate);

					walletupdatelogRepository.save(uswul);
					userinfoRepository.walletBalanceDetection(usrobj.getId(), plancuscost);

					walletupdatelogRepository.save(opwul);
					operatorsRepository.walletBalanceCredit(opobj.getId(), planlcocomm);

				}
			}

			// Quotaplanmanagelog
			Quotaplanmanagelog logobj = new Quotaplanmanagelog(planrechargeid, robj.getCus_username(), robj.getPlanid(),
					creationdate, quotaExpiryDate, subobj.getPlantimebank(), String.valueOf(plancost), "",
					String.valueOf(plantax), subobj.getMrp(), priceobj.getPlandiscount(), priceobj.getPlancuscost(),
					String.valueOf(msoamount), String.valueOf(planlcocomm), robj.getCreationby(),
					robj.getCreationbyusername(), "Plan activation");

			// Radcheck
			Radcheck rcobj = new Radcheck(robj.getCus_username(), "Expiration", ":=", RadcheckQuotaExpiryDate);

			// Firstime Activation
			if ((radcheckRepository.existsByUsernameAttribute(robj.getCus_username(), "Cleartext-Password")) == 0) {
				Userinfo obj = userinfoRepository.findByUsername(robj.getCus_username());
				Radcheck radcheck = new Radcheck();
				radcheck.setUsername(obj.getUsername());
				radcheck.setAttribute("Cleartext-Password");
				radcheck.setOp(":=");
				radcheck.setValue(obj.getPassword());
				// insert username/password
				radcheckRepository.save(radcheck);
			}

			Quotaplan planobj = quotaplanRepository.findById(subobj.getPlanid());
			List<Radusergroup> ruglist = new ArrayList<>();

			Radusergroup rug = new Radusergroup(robj.getCus_username(), planobj.getPlan(), 0);
			ruglist.add(rug);

			quotaplanmanagelogRepository.save(logobj);

			userbillinfoRepository.updateCurrentPlanDetailsForUser(robj.getCus_username(), subobj.getSubplan(),
					robj.getPlanid(), quotaStartDate, quotaExpiryDate);

			radcheckRepository.deleteByUsernameAttribute(robj.getCus_username(), "Expiration");

			radcheckRepository.save(rcobj);

			radusergroupRepository.deleteByUsername(robj.getCus_username());

			radusergroupRepository.saveAll(ruglist);

			try {
				Byte cgst_perc = 9;
				Byte sgst_perc = 9;
				String invoice_period = InvoicePeriodGenerator.generate();
				Invoice inv = new Invoice();
				inv.setReferenceid(planrechargeid);
				inv.setUserid(usrobj.getId());
				inv.setFinancial_year(invoice_period);
				inv.setType_id(1);
				inv.setStatus_id(5);

				if (robj.getExpirydate() != null && robj.getStartdate() != null && !robj.getExpirydate().equals("")
						&& !robj.getStartdate().equals("")) {
					inv.setInvoice_date(robj.getStartdate());
				} else {
					inv.setInvoice_date(creationdate);
				}
				inv.setInvoice_period(Dateformat.ddmmyyyy(invoicedate.substring(0, 10)) + " to "
						+ Dateformat.ddmmyyyy(quotaExpiryDate.substring(0, 10)));
				inv.setDue_date(creationdate);
				inv.setPrevious_due(0);
				inv.setPayment_recevied(plancuscost);
				inv.setAdjustments(0);
				inv.setInvoice_amount(plancuscost);
				inv.setBalance_amount(0);
				inv.setPacket_amount_balance(0);
				inv.setCreationdate(creationdate);
				inv = invoiceRep.save(inv);

				int invoice_id = invoiceRep.getRecentIdByInvoiceNo(inv.getInvoice_no());

				List<Invoice_items> invoice_items = new ArrayList<Invoice_items>();
				Invoice_items invitm = new Invoice_items();
				invitm.setInvoice_id(invoice_id);
				invitm.setTxn_date(inv.getInvoice_date());
				invitm.setPeriod_date(inv.getInvoice_period());
				invitm.setHsn_code("9984");
				invitm.setPlanid(Integer.parseInt(robj.getPlanid()));
				invitm.setPlan_desc("Subscription Charges");
				invitm.setPeriod_days(Integer.parseInt(subobj.getPlantimebank()));
				invitm.setRate(plancost);
				invitm.setCgst_perc(cgst_perc);
				invitm.setCgst_rate(DoubleRounder.round((plancost / 100) * cgst_perc, 2));
				invitm.setSgst_perc(sgst_perc);
				invitm.setSgst_rate(DoubleRounder.round((plancost / 100) * sgst_perc, 2));
				invitm.setAmount_incl_tax(plancuscost);
				invitm.setDiscription(subobj.getSubplan());
				invitm.setCreationbyusername(robj.getCreationbyusername());
				invitm.setCreationbyrole(robj.getCreationby());
				invitm.setCreationdate(creationdate);

				invoice_items.add(invitm);
				invoiceItemsRep.saveAll(invoice_items);

				// if it's is Extend plan
				if (isrenewal) {

					Renewalhistory rwhis = new Renewalhistory();

					rwhis.setUsername(userbillobj.getUsername());
					rwhis.setPlanid(Integer.parseInt(robj.getPlanid()));
					rwhis.setOldexpirydate(userbillobj.getQuotaexpirydate());
					rwhis.setNewexpirydate(quotaExpiryDate);
					rwhis.setNewstartdate(invoicedate + " 00:00:00");
					rwhis.setDays(subobj.getPlantimebank());
					rwhis.setCreationbyusername(robj.getCreationbyusername());
					rwhis.setCreationby(robj.getCreationby());
					rwhis.setIsupdate(false);
					rwhis.setCreationdate(Dateformat.getCurrentDatetime());
					renewalhistoryRepository.save(rwhis);

				}

			} catch (Exception e) {
				System.out.println("Exp: " + e.getMessage());
			} finally {

			}
		} catch (Exception e) {
			System.out.println("Exception at plan activation: " + e);
		}

		return ResponseEntity.ok(new MessageResponse("Plan activated"));
	}

	@SuppressWarnings("unused")
	@Override
	public ResponseEntity<?> expirydateExtend(Map<String, ?> obj) {

		try {

			String nonaffectuser = null;
			String expirydate = null;

			if (!obj.get("username").equals(" ") && obj.get("username") != null) {
				String user = obj.get("userid").toString().replace("[", "").replace("]", "").replace(" ", "");

				String[] userlist = user.split(",");

				if (userlist.length > 0) {
					Map<String, Long> map = Arrays.stream(userlist)
							.collect(Collectors.groupingBy(s -> s, Collectors.counting()));

					System.out.println(map);

					for (Map.Entry<String, Long> entry : map.entrySet()) {

						if (entry.getValue() % 2 == 0) {

						} else {

							Userbillinfo userobj = userbillinfoRepository.findByUsername(entry.getKey());

							if (userobj != null && userobj.getQuotaexpirydate() != null) {
								Graceperiodextendhistory gpeobj = new Graceperiodextendhistory();

								if (!obj.get("days").toString().equals("0") && obj.get("days") != null) {

									Calendar cal = Calendar.getInstance();

									Date preexdate = new SimpleDateFormat("yyyy-MM-dd")
											.parse(userobj.getQuotaexpirydate());
									Date curdate = new SimpleDateFormat("yyyy-MM-dd")
											.parse(Dateformat.getCurrentDatetime());

									if (preexdate.after(curdate)) {
										Date beforedate = new SimpleDateFormat("yyyy-MM-dd")
												.parse(userobj.getQuotaexpirydate());

										cal.setTime(beforedate);

									} else if (preexdate.before(curdate)) {
										cal.setTime(curdate);
										cal.add(Calendar.DATE, -1);

									} else {
										cal.setTime(curdate);
										cal.add(Calendar.DATE, -1);

									}

									cal.add(Calendar.DATE, Integer.parseInt(obj.get("days").toString()));
									expirydate = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()) + " 23:59:59";
									gpeobj.setDays(obj.get("days").toString());

								} else if (!obj.get("expirydate").toString().equals(" ")
										&& obj.get("expirydate") != null) {

									expirydate = obj.get("expirydate").toString() + " 23:59:59";
									gpeobj.setDays(String.valueOf(Dateformat
											.differentBetweenTwoDates(userobj.getQuotaexpirydate(), expirydate)));
								}

								// save into grace period extend history table

								gpeobj.setOldexpirydate(userobj.getQuotaexpirydate());
								gpeobj.setNewexpirydate(expirydate);
								gpeobj.setUsername(userobj.getUsername());
								gpeobj.setCreationbyusername(obj.get("username").toString());
								gpeobj.setCreationby(obj.get("role").toString());
								gpeobj.setPlanid(String.valueOf(userobj.getPlanid()));
								gpeobj.setCreationdate(Dateformat.getCurrentDatetime());

								graceperiodextendhistoryRepository.save(gpeobj);

								// expirydate update to billinfo table
								userobj.setQuotaexpirydate(expirydate);
								userobj.setUpdatedate(Dateformat.getCurrentDatetime());
								userbillinfoRepository.save(userobj);

								Radcheck radcheckobj = radcheckRepository
										.findByUsernameAndAttribute(userobj.getUsername(), "Expiration");

								// radcheck expiration update
								Date radexpirydate = new SimpleDateFormat("yyyy-MM-dd").parse(expirydate);
								radcheckobj.setValue(
										new SimpleDateFormat("dd MMM yyyy").format(radexpirydate) + " 23:59:59");

								radcheckRepository.save(radcheckobj);

							} else {

								if (nonaffectuser == null) {
									nonaffectuser = userobj.getUsername() + ",";
								} else {
									nonaffectuser = nonaffectuser + userobj.getUsername() + ",";
								}

							}
						}
					}
					if (nonaffectuser != null && !nonaffectuser.equals(" ")) {
						return ResponseEntity.ok(new MessageResponse("Success!  Expiry Date not Updated for "
								+ nonaffectuser.substring(0, nonaffectuser.length() - 1)));
					} else {
						return ResponseEntity.ok(new MessageResponse("Success!"));
					}

				} else {
					return ResponseEntity.badRequest().body(new MessageResponse("Select atleast one user!"));
				}

			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;
	}

	@Override
	public ResponseEntity<?> cancelinvoice(CancelInvoice obj) {

		List<Quotaplanmanagelog> qpllist = quotaplanmanagelogRepository.getUserRecentLog(obj.getCustomername(),
				obj.getStartdate());

		System.out.print(qpllist.size());
		if (qpllist.size() > 0) {

			Userbillinfo userbillobj = userbillinfoRepository.findByUsername(obj.getCustomername());
			if (userbillobj != null) {

				Userinfo userobj = userinfoRepository.findByUsername(obj.getCustomername());

				userbillobj.setQuotaexpirydate(Dateformat.getCurrentDatetime());
				userbillobj.setUpdatedate(Dateformat.getCurrentDatetime());
				userbillobj.setUpdateby(obj.getUsername());
				userbillinfoRepository.save(userbillobj);

				Radcheck radobj = radcheckRepository.findByUsernameAndAttribute(obj.getCustomername(), "Expiration");
				if (radobj != null) {

					try {
						Date date = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss").parse(Dateformat.getCurrentDatetime()); // TODO
						String dateString = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").format(date).replace("-", " ");
						radobj.setValue(dateString);
						radcheckRepository.save(radobj);

					} catch (Exception e) {
						System.out.println(e);
					}

				}

				for (Quotaplanmanagelog qplobj : qpllist) {
					Operators opobj = operatorsRepository.findById(userobj.getOpid());

					final String planrefundid = IdGen.genID("PRF");// recharge unique ID Generation

					System.out.println(qplobj.getRechargeid());
					Invoice invobj = invoiceRep.findByReferenceid(qplobj.getRechargeid());

					if (invobj != null) {
						System.out.println("\n" + qplobj.getRechargeid() + "   " + invobj.getReferenceid() + "  "
								+ invobj.getId());
						if (invobj.getId() != 0) {
							invoiceItemsRep.deleteByInvoiceid(invobj.getId());
						}

						invoiceRep.deleteByReferenceid(qplobj.getRechargeid());

						double cuscost = 0.0;

						if (qplobj.getCreationby().equalsIgnoreCase("USER")) {
							cuscost = Double.parseDouble(qplobj.getPlancuscost());

						} else {
							cuscost = Double.parseDouble(qplobj.getPlanmsocost());
						}

						double wallet = Double.parseDouble(opobj.getWalletbalance());
						double newbal = DoubleRounder.round((wallet + cuscost), 2);

						opobj.setWalletbalance(String.valueOf(newbal));
						opobj.setUpdatedate(Dateformat.getCurrentDatetime());
						operatorsRepository.save(opobj);

						Walletupdatelog wulobj = new Walletupdatelog("OPERATOR", opobj.getUsername(),
								String.valueOf(wallet), String.valueOf(cuscost), String.valueOf(newbal), planrefundid,
								2, 1, obj.getRole(), obj.getUsername(), Dateformat.getCurrentDatetime());

						walletupdatelogRepository.save(wulobj);

						Quotaplancancelhistory cancelobj = new Quotaplancancelhistory(planrefundid,
								obj.getCustomername(), qplobj.getPlanid(), String.valueOf(cuscost), opobj.getUsername(),
								obj.getRole(), obj.getUsername(), "Plan Deactivation", Dateformat.getCurrentDatetime(),
								qplobj.getCreationdate());

						quotaplancanclehistoryRepository.save(cancelobj);
					}

				}
				System.out.println(obj.getUsername()+"  "+obj.getStartdate());

				renewalhistoryRepository.deleteByDateForCancelInvoice(obj.getCustomername(), obj.getStartdate());

				return ResponseEntity.ok(new MessageResponse("Success!"));

			} else {
				return ResponseEntity.badRequest().body(new MessageResponse("Error!"));
			}

		} else {
			return ResponseEntity.badRequest().body(new MessageResponse("No plan available for this user!"));
		}

	}

	@Override
	public List<Graceperiodextendhistory> getgraceplanextendhistory(String role, String username, String fdate,
			String tdate) {
		List<Graceperiodextendhistory> Report = new ArrayList<Graceperiodextendhistory>();
		if (!fdate.equals("0000-00-00") & !tdate.equals("0000-00-00")) {
			if (role.equalsIgnoreCase("user")) {
				Report = graceperiodextendhistoryRepository.getReportByUsernameAndftDate(username, fdate, tdate);
			} else if (role.equalsIgnoreCase("operator")) {
				Report = graceperiodextendhistoryRepository.getReportByOpernameAndftDate(username, fdate, tdate);
			} else if (role.equalsIgnoreCase("admin")) {
				Report = graceperiodextendhistoryRepository.getReportByAdminAndftDate(fdate, tdate);
			}
		} else {
			if (role.equalsIgnoreCase("user")) {
				Report = graceperiodextendhistoryRepository.getReportByUsername(username);
			} else if (role.equalsIgnoreCase("operator")) {
				Report = graceperiodextendhistoryRepository.getReportByOpername(username);
			} else if (role.equalsIgnoreCase("admin")) {
				Report = graceperiodextendhistoryRepository.getReportByAdmin();
			}
		}
		return Report;
	}

	@Override
	public List<Quotaplancancelhistory> getquotaplancancelhistory(String role, String username, String fdate,
			String tdate) {
		List<Quotaplancancelhistory> Report = new ArrayList<Quotaplancancelhistory>();
		if (!fdate.equals("0000-00-00") & !tdate.equals("0000-00-00")) {
			if (role.equalsIgnoreCase("user")) {
				Report = quotaplancanclehistoryRepository.getReportByUsernameAndftDate(username, fdate, tdate);
			} else if (role.equalsIgnoreCase("operator")) {
				Report = quotaplancanclehistoryRepository.getReportByOpernameAndftDate(username, fdate, tdate);
			} else if (role.equalsIgnoreCase("admin")) {
				Report = quotaplancanclehistoryRepository.getReportByAdminAndftDate(fdate, tdate);
			}
		} else {
			if (role.equalsIgnoreCase("user")) {
				Report = quotaplancanclehistoryRepository.getReportByUsername(username);
			} else if (role.equalsIgnoreCase("operator")) {
				Report = quotaplancanclehistoryRepository.getReportByOpername(username);
			} else if (role.equalsIgnoreCase("admin")) {
				Report = quotaplancanclehistoryRepository.getReportByAdmin();
			}
		}
		return Report;
	}

}
