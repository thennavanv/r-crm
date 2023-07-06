package com.ridsys.rib.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ridsys.rib.DTO.AllPlanListDTO;
import com.ridsys.rib.DTO.PlanDTO;
import com.ridsys.rib.DTO.QuotaplanDTO;
import com.ridsys.rib.DTO.QuotasubplanDTO;
import com.ridsys.rib.DTO.TicketSubjectTypeDTO;
import com.ridsys.rib.DTO.UserplanDetailDTO;
import com.ridsys.rib.comm.Dateformat;
import com.ridsys.rib.models.Billing_plans;
import com.ridsys.rib.models.Billing_plans_profiles;
import com.ridsys.rib.models.Duplicatesubplan;
import com.ridsys.rib.models.Fup;
import com.ridsys.rib.models.Nas;
import com.ridsys.rib.models.Operatorplanpermission;
import com.ridsys.rib.models.Operators;
import com.ridsys.rib.models.Ottpackages;
import com.ridsys.rib.models.Quotaplan;
import com.ridsys.rib.models.Quotaplanlcoprices;
import com.ridsys.rib.models.Quotasubplan;
import com.ridsys.rib.models.Radgroupcheck;
import com.ridsys.rib.models.Radgroupreply;
import com.ridsys.rib.models.Userbillinfo;
import com.ridsys.rib.models.Userinfo;
import com.ridsys.rib.payload.request.CreatePlanRequest;
import com.ridsys.rib.payload.response.MessageResponse;
import com.ridsys.rib.repository.Billing_plansRepository;
import com.ridsys.rib.repository.Billing_plans_profilesRepository;
import com.ridsys.rib.repository.DuplicatesubplanRepository;
import com.ridsys.rib.repository.FupRepository;
import com.ridsys.rib.repository.NasRepository;
import com.ridsys.rib.repository.OperatorplanpermissionRepository;
import com.ridsys.rib.repository.OperatorsRepository;
import com.ridsys.rib.repository.OttpackagesRepository;
import com.ridsys.rib.repository.QuotaplanRepository;
import com.ridsys.rib.repository.QuotaplanlcopricesRepository;
import com.ridsys.rib.repository.QuotasubplanRepository;
import com.ridsys.rib.repository.RadgroupcheckRepository;
import com.ridsys.rib.repository.RadgroupreplyRepository;
import com.ridsys.rib.repository.UserbillinfoRepository;
import com.ridsys.rib.repository.UserinfoRepository;
import com.ridsys.rib.service.PlanService;

@Service
public class PlanServiceImpl implements PlanService {

	@Autowired
	RadgroupcheckRepository radgroupcheckRepository;

	@Autowired
	RadgroupreplyRepository radgroupreplyRepository;

	@Autowired
	Billing_plansRepository billing_plansRepository;

	@Autowired
	Billing_plans_profilesRepository billing_plans_profilesRepository;

	@Autowired
	FupRepository fupRepository;

	@Autowired
	OttpackagesRepository ottpackagesRepository;

	@Autowired
	QuotaplanRepository quotaplanRepository;

	@Autowired
	QuotasubplanRepository quotasubplanRepository;

	@Autowired
	OperatorsRepository operatorsRepository;

	@Autowired
	QuotaplanlcopricesRepository quotaplanlcopricesRepository;

	@Autowired
	OperatorplanpermissionRepository operatorplanpermissionRepository;

	@Autowired
	UserinfoRepository userinfoRepository;

	@Autowired
	UserbillinfoRepository userbillinfoRepository;

	@Autowired
	DuplicatesubplanRepository duplicatesubplanRepository;

	@Autowired
	NasRepository nasRepository;

	@Override
	public ResponseEntity<?> createPlan(CreatePlanRequest obj) {

		Nas nas = nasRepository.findAllByIsactivelimit();

		if (billing_plansRepository.existsByPlanname(obj.getPlanname())) {
			return ResponseEntity.badRequest().body(new MessageResponse("The plan name is already exist!"));

		} else if (obj.getUploadspeed() == 0 || obj.getDownloadspeed() == 0) {
			return ResponseEntity.badRequest().body(new MessageResponse("Give the proper bandwidth speed"));

		} else if (obj.getPlantype().equals("fup")) {

//			if (obj.getTotallimit() == 0 || (obj.getUploadlimit() + obj.getDownloadlimit()) == 0) {
//			if (obj.getTotallimit() == 0 && (obj.getUploadlimit() + obj.getDownloadlimit()) == 0) {
			if (obj.getTotallimit() == 0) {
				return ResponseEntity.badRequest().body(new MessageResponse("Give the proper data limits"));

			}
		} else if (obj.getValiditydays() == 0) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Should give the validity days more than 1 day"));

		}
		String currentdatetime = Dateformat.getCurrentDatetime();
		if (obj.getPlantype().equals("unlimited") || obj.getPlantype().equals("fup")) {

			Billing_plans object = new Billing_plans();
			object.setPlanname(obj.getPlanname());
			object.setPlantype(obj.getBilltype());
			object.setPlantimebank(String.valueOf(obj.getValiditydays()));
			object.setPlantimetype(obj.getPlantype());
			object.setPlanbandwidthup(String.valueOf(obj.getUploadspeed()));
			object.setPlanbandwidthdown(String.valueOf(obj.getDownloadspeed()));
			object.setPlantraffictotal(String.valueOf(obj.getTotallimit()));
			object.setPlantrafficup(String.valueOf(obj.getUploadlimit()));
			object.setPlantrafficdown(String.valueOf(obj.getDownloadlimit()));
			object.setPlancost(String.valueOf(obj.getPlancost()));
			object.setPlantax(String.valueOf(obj.getTax()));
			object.setPlanlcocomm(String.valueOf(obj.getOperatorcommission()));
			object.setPlanmrp(String.valueOf(obj.getPlanMrp()));
			object.setPlandiscount(String.valueOf(obj.getPlanDiscount()));
			object.setPlancuscost(String.valueOf(obj.getPlanCusCost()));
			object.setPlanmsocost(String.valueOf(obj.getPlanMsoCost()));
			object.setPlancurrency("INR");
			object.setPlanrecurringbillingschedule("Fixed");
			object.setCreationdate(currentdatetime);
			object.setCreationby("admin");
			object.setPlanactive("Yes");
			object.setLco_commission_per(obj.getLco_commission_per());
			object.setPlandiscountdays(obj.getDiscountdays());
			object.setIsactive(true);

			// insert bill paln
			billing_plansRepository.save(object);

			List<Radgroupcheck> radgroupchecklist = new ArrayList<>();
			List<Radgroupreply> radgroupreplylist = new ArrayList<>();

			String profilename = obj.getPlanname() + " group";

			Radgroupcheck radgroupcheck = new Radgroupcheck();

			Radgroupreply radgroupreply = new Radgroupreply();

			if (obj.getUploadspeed() > 0 || obj.getUploadspeed() > 0) {

				radgroupcheck.setGroupname(profilename);

				radgroupreply.setGroupname(profilename);

				if (nas.getShortname().contains("Mikrotic")) {

					// radgroupcheck
					radgroupcheck.setAttribute("Mikrotik-Rate-Limit");
					radgroupcheck.setOp(":=");
					radgroupcheck.setValue(obj.getUploadspeed() + "M/" + obj.getDownloadspeed() + "M");
					radgroupchecklist.add(radgroupcheck);

					// radgroupreply
					radgroupreply.setAttribute("Mikrotik-Rate-Limit");
					radgroupreply.setOp(":=");
					radgroupreply.setValue(obj.getUploadspeed() + "M/" + obj.getDownloadspeed() + "M");
					radgroupreplylist.add(radgroupreply);

				} else if (nas.getShortname().contains("Cisco")) {
					// radgroupcheck
					// upload
					radgroupcheck.setAttribute("Cisco-AVPair");
					radgroupcheck.setOp("+=");
					radgroupcheck.setValue("ip:sub-qos-policy-in=" + obj.getUploadspeed() + "M");
					radgroupchecklist.add(radgroupcheck);

					// download
					radgroupcheck.setAttribute("Cisco-AVPair");
					radgroupcheck.setOp("+=");
					radgroupcheck.setValue("ip:sub-qos-policy-out=" + obj.getDownloadspeed() + "M");
					radgroupchecklist.add(radgroupcheck);

					// radrerply
					// upload
					radgroupreply.setAttribute("Cisco-AVPair");
					radgroupreply.setOp("+=");
					radgroupreply.setValue("ip:sub-qos-policy-in=" + obj.getUploadspeed() + "M");
					radgroupreplylist.add(radgroupreply);

					// download
					radgroupreply.setAttribute("Cisco-AVPair");
					radgroupreply.setOp("+=");
					radgroupreply.setValue("ip:sub-qos-policy-out=" + obj.getDownloadspeed() + "M");
					radgroupreplylist.add(radgroupreply);

				} else {
					// radgroupcheck
					radgroupcheck.setAttribute("Mikrotik-Rate-Limit");
					radgroupcheck.setOp(":=");
					radgroupcheck.setValue(obj.getUploadspeed() + "M/" + obj.getDownloadspeed() + "M");
					radgroupchecklist.add(radgroupcheck);

					// readgroupreply
					radgroupreply.setAttribute("Mikrotik-Rate-Limit");
					radgroupreply.setOp(":=");
					radgroupreply.setValue(obj.getUploadspeed() + "M/" + obj.getDownloadspeed() + "M");
					radgroupreplylist.add(radgroupreply);

				}

//				radgroupreply.setGroupname(profilename);
//				radgroupreply.setAttribute("Cisco-AVPair");
//				radgroupreply.setOp("=");
//				radgroupreply.setValue("ip:sub-qos-policy-in="+obj.getDownloadspeed()+"K");
//				radgroupreplylist.add(radgroupreply);
//				
//				radgroupreply = new Radgroupreply();
//				radgroupreply.setGroupname(profilename);
//				radgroupreply.setAttribute("Cisco-AVPair");
//				radgroupreply.setOp("=");
//				radgroupreply.setValue("ip:sub-qos-policy-out="+obj.getDownloadspeed()+"K");
//				radgroupreplylist.add(radgroupreply);

				if (obj.getPlantype().equals("fup")) {
					radgroupcheck = new Radgroupcheck();

					if (nas.getShortname().contains("Mikrotic")) {

						radgroupcheck.setGroupname(profilename);
						radgroupcheck.setAttribute("Mikrotik-Total-Limit");
						radgroupcheck.setOp(":=");
						radgroupcheck.setValue(String.valueOf(obj.getTotallimit() * 1024));// GB to MB
						radgroupchecklist.add(radgroupcheck);

					} else if (nas.getShortname().contains("Cisco")) {

						radgroupcheck.setAttribute("Cisco-AVPair");
						radgroupcheck.setOp(":=");
						radgroupcheck.setValue("Monthly-Bandwidth=" + String.valueOf(obj.getTotallimit() * 1024));// GB
																													// to
																													// MB
						radgroupchecklist.add(radgroupcheck);

					} else {
						radgroupcheck.setGroupname(profilename);
						radgroupcheck.setAttribute("Mikrotik-Total-Limit");
						radgroupcheck.setOp(":=");
						radgroupcheck.setValue(String.valueOf(obj.getTotallimit() * 1024));// GB to MB
						radgroupchecklist.add(radgroupcheck);
					}

					Fup fup = new Fup();
					if (nas.getShortname().contains("Mikrotic")) {
						fup.setGroupname(profilename);
						fup.setValue1("ip:sub-qos-policy-in=3M");
						fup.setValue2("ip:sub-qos-policy-out=3M");

					} else if (nas.getShortname().contains("Cisco")) {
						fup.setGroupname(profilename);
						fup.setValue1("2M/2M");
						fup.setValue2("3M/3M");

					} else {
						fup.setGroupname(profilename);
						fup.setValue1("2M/2M");
						fup.setValue2("3M/3M");
					}

					fupRepository.save(fup);

				}

			} else {
				radgroupcheck.setGroupname(profilename);
				radgroupcheck.setAttribute("Mikrotik-Rate-Limit");
				radgroupcheck.setOp(":=");
				radgroupcheck.setValue("2M/2M");
				radgroupchecklist.add(radgroupcheck);

				radgroupreply.setGroupname(profilename);
				radgroupreply.setAttribute("Mikrotik-Rate-Limit");
				radgroupreply.setOp(":=");
				radgroupreply.setValue("2M/2M");
				radgroupreplylist.add(radgroupreply);

//				radgroupreply.setGroupname(profilename);
//				radgroupreply.setAttribute("Cisco-AVPair");
//				radgroupreply.setOp("=");
//				radgroupreply.setValue("ip:sub-qos-policy-in=3M");
//				radgroupreplylist.add(radgroupreply);
//				
//				radgroupreply = new Radgroupreply();
//				radgroupreply.setGroupname(profilename);
//				radgroupreply.setAttribute("Cisco-AVPair");
//				radgroupreply.setOp("=");
//				radgroupreply.setValue("ip:sub-qos-policy-out=3M");
//				radgroupreplylist.add(radgroupreply);
			}

			if (radgroupchecklist.size() > 0) {
				// insert radgroupcheck
				radgroupcheckRepository.saveAll(radgroupchecklist);
			}
			if (radgroupreplylist.size() > 0) {
				// insert radgroupreply
				radgroupreplyRepository.saveAll(radgroupreplylist);
			}

			Billing_plans_profiles bpp = new Billing_plans_profiles();
			bpp.setPlanname(obj.getPlanname());
			bpp.setProfilename(profilename);

			// Insert plan profile
			billing_plans_profilesRepository.save(bpp);

			return ResponseEntity.ok(new MessageResponse("New plan created successfully!"));

		}
		return ResponseEntity.badRequest().body(new MessageResponse("New plan not created"));
	}

	@Override
	public List<Billing_plans> getPlanList() {
		return billing_plansRepository.findAllActivePlans();
	}

	@Override
	public ResponseEntity<?> managePackagePlan(int planId, boolean sts) {
//		Optional<Billing_plans> obj=billing_plansRepository.findById((planId);
//		obj.get().setIsactive(sts);

		Billing_plans obj = billing_plansRepository.findById(planId);
		obj.setIsactive(sts);
		billing_plansRepository.save(obj);

		if (sts) {
			return ResponseEntity.ok(new MessageResponse("Plan sucessfully Activated!"));
		} else {
			return ResponseEntity.ok(new MessageResponse("Plan sucessfully Deactivated!"));
		}
	}

	@Override
	public Quotaplan getPlanById(int planid) {
		return quotaplanRepository.findById(planid);
//		return billing_plansRepository.findById(planid);
	}

	@Override
	public ResponseEntity<?> editMainPlan(Map<String, String> reqobj) {

		double cost = Double.parseDouble(reqobj.get("planCost"));
		double plancost;
		double mrp;
		double discountcost = 0.0;
		double lcoCom;
		double cusCost;
		double msoCom;
		double tax;

		List<Quotasubplan> sublist = quotasubplanRepository.findByPlanid(Integer.parseInt(reqobj.get("planid")));

		for (Quotasubplan qsp : sublist) {

			Quotasubplan subobj = quotasubplanRepository.findById(qsp.getId());

			int plandays = (Integer.parseInt(subobj.getPlantimebank()) / 30);

			plancost = cost * plandays;

			tax = (plancost * Integer.parseInt(subobj.getTax()) / 100);

			mrp = tax + plancost;

			List<Quotaplanlcoprices> priceList = quotaplanlcopricesRepository.findBySubplanid(qsp.getId());
			for (Quotaplanlcoprices lcp : priceList) {

				if (Integer.parseInt(lcp.getPlandiscountdays()) > 0) {

					discountcost = (mrp / plandays) * (Integer.parseInt(lcp.getPlandiscountdays()) / 30);

				}

				cusCost = mrp - discountcost;

				if (!lcp.isDiscountincl()) {// incldiscount false deduct money from plancost

					Double incldiscost = (plancost / plandays) * (Integer.parseInt(lcp.getPlandiscountdays()) / 30);

					incldiscost = plancost - incldiscost;

					lcoCom = incldiscost * Integer.parseInt(lcp.getPlanlcocompercent()) / 100;

					msoCom = cusCost - lcoCom;

				} else {
					lcoCom = plancost * Integer.parseInt(lcp.getPlanlcocompercent()) / 100;

					msoCom = cusCost - lcoCom;
				}

				lcp.setPlancuscost(String.valueOf(cusCost));
				lcp.setPlandiscount(String.valueOf(discountcost));
				lcp.setPlanlcocom(String.valueOf(lcoCom));
				lcp.setPlanmsocom(String.valueOf(msoCom));
				lcp.setUpdatedate(Dateformat.getCurrentDatetime());

				quotaplanlcopricesRepository.save(lcp);

			}

			qsp.setMrp(String.valueOf(mrp));
			qsp.setPrice(String.valueOf(plancost));
			qsp.setTaxcost(String.valueOf(tax));
			qsp.setUpdatedate(Dateformat.getCurrentDatetime());
			quotasubplanRepository.save(qsp);
		}

		Quotaplan planobj = quotaplanRepository.findById(Integer.parseInt(reqobj.get("planid")));
		if (planobj != null) {
			planobj.setOnemonthcost(reqobj.get("planCost"));
			planobj.setUpdatedate(Dateformat.getCurrentDatetime());
			quotaplanRepository.save(planobj);
		}

		return ResponseEntity.ok(new MessageResponse("Plan Details Edited Successfully!"));
	}

	@Override
	public UserplanDetailDTO getUserPlanList(String username) {

		Userinfo userobj = userinfoRepository.findByUsername(username);

		Userbillinfo userbillobj = userbillinfoRepository.findByUsername(username);

		if (duplicatesubplanRepository.existsBySubplanidAndOpid(userbillobj.getPlanid(), userobj.getOpid())) {
//
			return quotaplanlcopricesRepository.getUserDuplicatePlanList(userobj.getOpid(), username);
		} else {
			if (quotaplanlcopricesRepository.existsBySubplanidAndOpid(userbillobj.getPlanid(), userobj.getOpid())) {
				return quotaplanlcopricesRepository.getUserPlanList(userobj.getOpid(), username);

			} else {
				return quotaplanlcopricesRepository.getUserPlanList(0, username);
			}
		}

//		return billing_plansRepository.getUserPlanList(username);
	}

//	@Override
//	public Map<String, Object> getOttplanList() {
//		List<Ottpackages> packagelist = ottpackagesRepository.findAll();
//		List<TicketSubjectTypeDTO> planlist = ottpackagesRepository.getOttPlans();
//		Map<String, Object> map = new HashMap<>();
//
//		map.put("packagelist", packagelist);
//		map.put("planlist", planlist);
//		return map;
//	}

	@Override
	public Map<String, Object> getOttplanList() {
		List packagelist = new ArrayList<>();
		List<Ottpackages> ottpackagelist = ottpackagesRepository.findAll();
		List<TicketSubjectTypeDTO> planlist = ottpackagesRepository.getOttPlans();
		Map<String, Object> map = new HashMap<>();

		for(Ottpackages obj:ottpackagelist) {
			Map<String, Object> m = new HashMap<>();
		    m.put("id", obj.getId());
		    m.put("packageid", obj.getPackageid());
		    m.put("packagename", obj.getPackagename());
		    m.put("packagerate", obj.getMrp());
		    m.put("planlist", planlist);
		    packagelist.add(m);
		}
		map.put("packagelist", packagelist);
//		map.put("planlist", planlist);
		return map;
	}
	
	@Override
	public ResponseEntity<?> createQuotaplan(Map<String, String> reqobj) {

		Nas nas = nasRepository.findAllByIsactivelimit();

		String planname = reqobj.get("plan").trim();
		planname = planname.replace(" ", "_");

		if (planname != "" && quotaplanRepository.existsByPlan(planname)) {
			return ResponseEntity.badRequest().body(new MessageResponse("The plan name is already in use!"));
		} else if (Integer.parseInt(reqobj.get("bandwidthUp")) == 0
				|| Integer.parseInt(reqobj.get("bandwidthDown")) == 0) {
			return ResponseEntity.badRequest().body(new MessageResponse("Give the proper bandwidth speed"));

		} else if (reqobj.get("isfup").equals("true")) {

			if (Integer.parseInt(reqobj.get("bandwidthTotal")) == 0) {
				return ResponseEntity.badRequest().body(new MessageResponse("Give the proper data limits"));

			}
		} else if (Integer.parseInt(reqobj.get("onemonthCost")) == 0) {
			return ResponseEntity.badRequest().body(new MessageResponse("Give proper plan amount!"));
		}

		Quotaplan planobj = new Quotaplan();

		planobj.setBandwidthdown(reqobj.get("bandwidthDown"));
		planobj.setBandwidthtotal(reqobj.get("bandwidthTotal"));
		planobj.setBandwidthup(reqobj.get("bandwidthUp"));
		planobj.setCreationdate(Dateformat.getCurrentDatetime());
		planobj.setOnemonthcost(reqobj.get("onemonthCost"));
		planobj.setPackagetype(reqobj.get("packageType"));
		planobj.setPlantype(reqobj.get("planType"));
		planobj.setPlan(planname);
		planobj.setPlanid("0");
		planobj.setBilltype(reqobj.get("billtype"));
		planobj.setIsactive(true);
		planobj.setIsfup(Boolean.parseBoolean(reqobj.get("isfup")));

		List<Operators> oplist = operatorsRepository.findAllByIsactiveTrueOrderById();

		Set<Operators> opset = new LinkedHashSet<>();
		for (Operators op : oplist) {
			Operators ap = operatorsRepository.findById(op.getId());
			ap.setId(op.getId());
			opset.add(ap);
		}
		planobj.setOpset(opset);
		quotaplanRepository.save(planobj);

		List<Radgroupcheck> radgroupchecklist = new ArrayList<>();
		List<Radgroupreply> radgroupreplylist = new ArrayList<>();

//		String profilename = reqobj.get("plan") + " group";

		Radgroupcheck radgroupcheck = new Radgroupcheck();

		Radgroupreply radgroupreply = new Radgroupreply();

		if (Integer.parseInt(reqobj.get("bandwidthDown")) > 0 || Integer.parseInt(reqobj.get("bandwidthUp")) > 0) {

			if (nas.getShortname().contains("Mikrotic")) {

				// radgroupcheck
				radgroupcheck.setGroupname(planname);
				radgroupcheck.setAttribute("Mikrotik-Rate-Limit");
				radgroupcheck.setOp(":=");
				radgroupcheck.setValue(reqobj.get("bandwidthUp") + "M/" + reqobj.get("bandwidthDown") + "M");
				radgroupchecklist.add(radgroupcheck);

				// radgroupreply
				radgroupreply.setGroupname(planname);
				radgroupreply.setAttribute("Mikrotik-Rate-Limit");
				radgroupreply.setOp(":=");
				radgroupreply.setValue(reqobj.get("bandwidthUp") + "M/" + reqobj.get("bandwidthDown") + "M");
				radgroupreplylist.add(radgroupreply);

			} else if (nas.getShortname().contains("Cisco")) {
				// radgroupcheck
				// upload
				radgroupcheck.setGroupname(planname);
				radgroupcheck.setAttribute("Cisco-AVPair");
				radgroupcheck.setOp("+=");
				radgroupcheck.setValue("ip:sub-qos-policy-in=" + reqobj.get("bandwidthUp") + "M");
				radgroupchecklist.add(radgroupcheck);

				// download
				radgroupcheck = new Radgroupcheck();
				radgroupcheck.setGroupname(planname);
				radgroupcheck.setAttribute("Cisco-AVPair");
				radgroupcheck.setOp("+=");
				radgroupcheck.setValue("ip:sub-qos-policy-out=" + reqobj.get("bandwidthDown") + "M");
				radgroupchecklist.add(radgroupcheck);

				// radrerply
				// upload
				radgroupreply.setGroupname(planname);
				radgroupreply.setAttribute("Cisco-AVPair");
				radgroupreply.setOp("+=");
				radgroupreply.setValue("ip:sub-qos-policy-in=" + reqobj.get("bandwidthUp") + "M");
				radgroupreplylist.add(radgroupreply);

				// download
				radgroupreply = new Radgroupreply();
				radgroupreply.setGroupname(planname);
				radgroupreply.setAttribute("Cisco-AVPair");
				radgroupreply.setOp("+=");
				radgroupreply.setValue("ip:sub-qos-policy-out=" + reqobj.get("bandwidthDown") + "M");
				radgroupreplylist.add(radgroupreply);

			} else {
				// radgroupcheck
				radgroupcheck.setGroupname(planname);
				radgroupcheck.setAttribute("Mikrotik-Rate-Limit");
				radgroupcheck.setOp(":=");
				radgroupcheck.setValue(reqobj.get("bandwidthUp") + "M/" + reqobj.get("bandwidthDown") + "M");
				radgroupchecklist.add(radgroupcheck);

				// readgroupreply
				radgroupreply.setGroupname(planname);
				radgroupreply.setAttribute("Mikrotik-Rate-Limit");
				radgroupreply.setOp(":=");
				radgroupreply.setValue(reqobj.get("bandwidthUp") + "M/" + reqobj.get("bandwidthDown") + "M");
				radgroupreplylist.add(radgroupreply);

			}

//			radgroupreply.setGroupname(profilename);
//			radgroupreply.setAttribute("Cisco-AVPair");
//			radgroupreply.setOp("=");
//			radgroupreply.setValue("ip:sub-qos-policy-in="+reqobj.get("bandwidthDown")+"K");
//			radgroupreplylist.add(radgroupreply);
//			
//			radgroupreply = new Radgroupreply();
//			radgroupreply.setGroupname(profilename);
//			radgroupreply.setAttribute("Cisco-AVPair");
//			radgroupreply.setOp("=");
//			radgroupreply.setValue("ip:sub-qos-policy-out="+reqobj.get("bandwidthDown")+"K");
//			radgroupreplylist.add(radgroupreply);

			if (reqobj.get("packageType").equals("fup")) {
				radgroupcheck = new Radgroupcheck();

				if (nas.getShortname().contains("Mikrotic")) {

					radgroupcheck.setGroupname(planname);
					radgroupcheck.setAttribute("Mikrotik-Total-Limit");
					radgroupcheck.setOp(":=");
					radgroupcheck.setValue(String.valueOf(Integer.parseInt(reqobj.get("bandwidthTotal")) * 1024));// GB
																													// to
																													// MB
					radgroupchecklist.add(radgroupcheck);

				} else if (nas.getShortname().contains("Cisco")) {

					radgroupcheck.setGroupname(planname);
					radgroupcheck.setAttribute("Cisco-AVPair");
					radgroupcheck.setOp(":=");
					radgroupcheck.setValue("Monthly-Bandwidth="
							+ String.valueOf(Integer.parseInt(reqobj.get("bandwidthTotal")) * 1024));// GB
					// to
					// MB
					radgroupchecklist.add(radgroupcheck);

				} else {
					radgroupcheck.setGroupname(planname);
					radgroupcheck.setAttribute("Mikrotik-Total-Limit");
					radgroupcheck.setOp(":=");
					radgroupcheck.setValue(String.valueOf(Integer.parseInt(reqobj.get("bandwidthTotal")) * 1024));// GB
																													// to
																													// MB
					radgroupchecklist.add(radgroupcheck);
				}

				Fup fup = new Fup();
				if (nas.getShortname().contains("Mikrotic")) {
					fup.setGroupname(planname);
					fup.setValue1("2M/2M");
					fup.setValue2("3M/3M");

				} else if (nas.getShortname().contains("Cisco")) {
					fup.setGroupname(planname);
					fup.setValue1("ip:sub-qos-policy-in=3M");
					fup.setValue2("ip:sub-qos-policy-out=3M");

				} else {
					fup.setGroupname(planname);
					fup.setValue1("2M/2M");
					fup.setValue2("3M/3M");
				}

				fupRepository.save(fup);

			}

		} else {
			radgroupcheck.setGroupname(planname);
			radgroupcheck.setAttribute("Mikrotik-Rate-Limit");
			radgroupcheck.setOp(":=");
			radgroupcheck.setValue("2M/2M");
			radgroupchecklist.add(radgroupcheck);

			radgroupreply.setGroupname(planname);
			radgroupreply.setAttribute("Mikrotik-Rate-Limit");
			radgroupreply.setOp(":=");
			radgroupreply.setValue("2M/2M");
			radgroupreplylist.add(radgroupreply);

//			radgroupreply.setGroupname(profilename);
//			radgroupreply.setAttribute("Cisco-AVPair");
//			radgroupreply.setOp("+=");
//			radgroupreply.setValue("ip:sub-qos-policy-in=3M");
//			radgroupreplylist.add(radgroupreply);
//			
//			radgroupreply = new Radgroupreply();
//			radgroupreply.setGroupname(profilename);
//			radgroupreply.setAttribute("Cisco-AVPair");
//			radgroupreply.setOp("+=");
//			radgroupreply.setValue("ip:sub-qos-policy-out=3M");
//			radgroupreplylist.add(radgroupreply);
		}
		if (radgroupchecklist.size() > 0) {
			// insert radgroupcheck
			radgroupcheckRepository.saveAll(radgroupchecklist);
		}
		if (radgroupreplylist.size() > 0) {
			// insert radgroupreply
			radgroupreplyRepository.saveAll(radgroupreplylist);
		}

		return ResponseEntity.ok(new MessageResponse("Successfully Main plan created!"));
	}

	@Override
	public ResponseEntity<?> createQuotaSubPlan(Map<String, String> reqobj) {

		// reqobj
//		tax
//		taxCost
//		price
//		mrp
//		cusCost
//		lcoCom
//		msoCom
//		planTimeBank
//		lcoComPer
//		discountMonth
//		isfirstsub
//		planId
//		subplan
//		discountIncl

		if (reqobj.get("subplan") != "" && quotasubplanRepository.existsBySubplan(reqobj.get("subplan"))) {
			return ResponseEntity.badRequest().body(new MessageResponse("The plan name is already in use!"));
		} else if (Integer.parseInt(reqobj.get("tax")) == 0) {
			return ResponseEntity.badRequest().body(new MessageResponse("tax cannot be empty!"));
		} else if (Double.parseDouble(reqobj.get("taxCost")) == 0) {
			return ResponseEntity.badRequest().body(new MessageResponse("tax amount cannot be empty!"));
		} else if (Double.parseDouble(reqobj.get("price")) == 0) {
			return ResponseEntity.badRequest().body(new MessageResponse("Give proper plan amount!"));
		} else if (Double.parseDouble(reqobj.get("mrp")) == 0) {
			return ResponseEntity.badRequest().body(new MessageResponse("MRP is empty!"));
		} else if (Double.parseDouble(reqobj.get("cusCost")) == 0) {
			return ResponseEntity.badRequest().body(new MessageResponse("Customer Cost is empty!"));
		} else if (Double.parseDouble(reqobj.get("lcoCom")) == 0) {
			return ResponseEntity.badRequest().body(new MessageResponse("LCO Commission is empty!"));
		} else if (Double.parseDouble(reqobj.get("msoCom")) == 0) {
			return ResponseEntity.badRequest().body(new MessageResponse("MSO Commission is empty!"));
		} else if (Integer.parseInt(reqobj.get("planTimeBank")) == 0) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Should give the validity days more than 1 day"));
		} else if (Integer.parseInt(reqobj.get("lcoComPer")) == 0) {
			return ResponseEntity.badRequest().body(new MessageResponse("LCO Commission Percentage  is empty!"));
		}

		int discoutDays = Integer.parseInt(reqobj.get("discountMonth")) * 30;

		Quotasubplan subobj = new Quotasubplan();
		Quotaplanlcoprices priceobj = new Quotaplanlcoprices();

		if (Boolean.parseBoolean(reqobj.get("isfirstsub"))) {
			subobj.setPlanid(quotaplanRepository.getLasPlanId());

		} else {
			subobj.setPlanid(Integer.parseInt(reqobj.get("planId")));

		}

		int plantimebank = (Integer.parseInt(reqobj.get("planTimeBank")) * 30);
		subobj.setMrp(reqobj.get("mrp"));
		subobj.setSubplan(reqobj.get("subplan"));
		subobj.setPrice(reqobj.get("price"));
		subobj.setTax(reqobj.get("tax"));
		subobj.setTaxcost(reqobj.get("taxCost"));
		subobj.setTaxinclude(true);
		subobj.setPlantimebank(String.valueOf(plantimebank));
		subobj.setCreationdate(Dateformat.getCurrentDatetime());

		quotasubplanRepository.save(subobj);
		if (Boolean.parseBoolean(reqobj.get("isfirstsub"))) {
			priceobj.setSubplanid(quotasubplanRepository.getLastSubPlanId());
		} else {
			priceobj.setSubplanid(
					quotasubplanRepository.getLastSubPlanIdByPlanId(Integer.parseInt(reqobj.get("planId"))));
		}

		priceobj.setPlancuscost(reqobj.get("cusCost"));
		priceobj.setPlandiscount(reqobj.get("discount"));
		priceobj.setPlandiscountdays(String.valueOf(discoutDays));
		priceobj.setPlanlcocom(reqobj.get("lcoCom"));
		priceobj.setPlanmsocom(reqobj.get("msoCom"));
		priceobj.setPlanlcocompercent(reqobj.get("lcoComPer"));
		priceobj.setCreationdate(Dateformat.getCurrentDatetime());
		priceobj.setOpid(0);
		priceobj.setDiscountincl(Boolean.parseBoolean(reqobj.get("discountIncl")));
		priceobj.setIsactive(true);

		quotaplanlcopricesRepository.save(priceobj);

		return ResponseEntity.ok(new MessageResponse("Successfully subplan created!"));
	}

	@Override
	public List<TicketSubjectTypeDTO> getMainplans() {
		return quotaplanRepository.getMainplans();
	}

	@Override
	public List<PlanDTO> getSubplanByPlanId(String username, int planId) {

		if (username == null || username.equalsIgnoreCase("")) {
			return quotasubplanRepository.getSubplanByPlanId(planId);
		} else {
			return quotasubplanRepository.getSubplanByOpid(username, planId);
		}
	}

	@Override
	public Quotaplan getQuotaplanById(int planId) {
		return quotaplanRepository.findById(planId);
	}

	@Override
	public Map<?, ?> getPlanDetails(String username, String role, String client) {

		List<QuotaplanDTO> mainplanList = quotaplanRepository.getMainPlan(username);

		List lst = new ArrayList();
		int count = 0;
		int subplancount = 0;

		for (QuotaplanDTO qp : mainplanList) {
			QuotaplanDTO obj = new QuotaplanDTO();

			obj.setId(qp.getId());
			obj.setName(qp.getName());
			obj.setStatus(qp.isStatus());
			if (client.contains("RIDSYS")) {
				obj.setSubplanList(quotasubplanRepository.getSubPlanForRidsys(qp.getId(), username, role));

			} else {
				obj.setSubplanList(quotasubplanRepository.getSubPlan(qp.getId(), username));

			}
			if (obj.getSubplanList() != null) {
				for (QuotasubplanDTO qsp : obj.getSubplanList()) {
					if (!qsp.isSubstatus()) {
						subplancount++;
					}
				}
			}

			if (!qp.isStatus()) {
				count++;
			}
			lst.add(obj);
		}
		Map map = new HashMap();
		map.put("planCount", count);
		map.put("subplanCount", subplancount);
		map.put("planList", lst);

		return map;
	}

	@Override
	public ResponseEntity<?> managePlanForOperators(Map<String, String> reqobj) {

		String response;
		if (Boolean.parseBoolean(reqobj.get("status"))) {
			response = "Enabled";
		} else {
			response = "Disabled";
		}

		Operators opobj = operatorsRepository.findByUsername(reqobj.get("username"));

		if (reqobj.get("plantype").equalsIgnoreCase("BOTHPLAN")) {

			List<Quotaplan> mainplanlist = quotaplanRepository.findAllByIsactiveTrue();
			for (Quotaplan qp : mainplanlist) {
				mainplanModify(qp.getId(), Boolean.parseBoolean(reqobj.get("status")), opobj.getId(), 1);
			}

		} else if (reqobj.get("plantype").equalsIgnoreCase("MAINPLAN")) {
			mainplanModify(Integer.parseInt(reqobj.get("planId")), Boolean.parseBoolean(reqobj.get("status")),
					opobj.getId(), 2);

		} else if (reqobj.get("plantype").equalsIgnoreCase("SUBPLAN")) {
			subplanModify(Integer.parseInt(reqobj.get("subplanId")), Boolean.parseBoolean(reqobj.get("status")),
					opobj.getId());

		}

		return ResponseEntity.ok(new MessageResponse(
				"Plan Successfully " + response + " for " + opobj.getFirstname() + " " + opobj.getLastname()));
	}

	public void mainplanModify(int mainplanid, boolean status, int opid, int type) {

		Operatorplanpermission permisobj = operatorplanpermissionRepository.findByOpidAndPlanid(opid, mainplanid);

		if (permisobj == null) {

			permisobj = new Operatorplanpermission();
			permisobj.setOpid(opid);
			permisobj.setPlanid(mainplanid);
			permisobj.setIsactive(status);
			permisobj.setCreationdate(Dateformat.getCurrentDatetime());
			operatorplanpermissionRepository.save(permisobj);
		} else {
			permisobj.setIsactive(status);
			permisobj.setUpdateddate(Dateformat.getCurrentDatetime());
			operatorplanpermissionRepository.save(permisobj);
		}

		// subplan disable for mainplan
		if (type == 1) {
			List<Quotasubplan> subplanlist = quotasubplanRepository.findByPlanid(mainplanid);
			for (Quotasubplan qsp : subplanlist) {
				subplanModify(qsp.getId(), status, opid);
			}
		} else if (type == 2) {
			if (!status) {
				List<Quotasubplan> subplanlist = quotasubplanRepository.findByPlanid(mainplanid);
				for (Quotasubplan qsp : subplanlist) {
					subplanModify(qsp.getId(), status, opid);
				}
			}
		}
	}

	public void subplanModify(int subplanid, boolean status, int opid) {
		Quotaplanlcoprices planpriceobj = new Quotaplanlcoprices();
		Quotaplanlcoprices priceobj = quotaplanlcopricesRepository.findBySubplanidAndOpid(subplanid, opid);

		if (priceobj == null) {
			priceobj = quotaplanlcopricesRepository.findBySubplanidAndOpid(subplanid, 0);

			planpriceobj.setOpid(opid);
			planpriceobj.setIsactive(status);
			planpriceobj.setSubplanid(priceobj.getSubplanid());
			planpriceobj.setPlandiscountdays(priceobj.getPlandiscountdays());
			planpriceobj.setPlandiscount(priceobj.getPlandiscount());
			planpriceobj.setPlancuscost(priceobj.getPlancuscost());
			planpriceobj.setPlanlcocom(priceobj.getPlanlcocom());
			planpriceobj.setPlanlcocompercent(priceobj.getPlanlcocompercent());
			planpriceobj.setPlanmsocom(priceobj.getPlanmsocom());
			planpriceobj.setCreationdate(Dateformat.getCurrentDatetime());
			planpriceobj.setDiscountincl(priceobj.isDiscountincl());
			quotaplanlcopricesRepository.save(planpriceobj);
		} else {
			priceobj.setIsactive(status);
			priceobj.setUpdatedate(Dateformat.getCurrentDatetime());
			quotaplanlcopricesRepository.save(priceobj);

		}
	}

	@Override
	public ResponseEntity<?> editSubplanForOperator(Map<String, String> reqobj) {

		Operators opobj = operatorsRepository.findByUsername(reqobj.get("username"));
		Quotaplanlcoprices planpriceobj = new Quotaplanlcoprices();

		Quotaplanlcoprices priceobj = quotaplanlcopricesRepository
				.findBySubplanidAndOpid(Integer.parseInt(reqobj.get("subplanId")), opobj.getId());

		if (priceobj == null) {
			priceobj = quotaplanlcopricesRepository.findBySubplanidAndOpid(Integer.parseInt(reqobj.get("subplanId")),
					0);

			planpriceobj.setOpid(opobj.getId());
			planpriceobj.setIsactive(priceobj.isIsactive());
			planpriceobj.setSubplanid(priceobj.getSubplanid());
			planpriceobj.setPlandiscountdays(priceobj.getPlandiscountdays());
			planpriceobj.setPlandiscount(priceobj.getPlandiscount());
			planpriceobj.setPlancuscost(priceobj.getPlancuscost());
			planpriceobj.setPlanlcocom(reqobj.get("lcoCom"));
			planpriceobj.setPlanlcocompercent(reqobj.get("lcoComPer"));
			planpriceobj.setPlanmsocom(reqobj.get("msoCom"));
			priceobj.setDiscountincl(Boolean.parseBoolean(reqobj.get("discountIncl")));
			planpriceobj.setCreationdate(Dateformat.getCurrentDatetime());
			quotaplanlcopricesRepository.save(planpriceobj);
		} else {

			priceobj.setPlanlcocom(reqobj.get("lcoCom"));
			priceobj.setPlanmsocom(reqobj.get("msoCom"));
			priceobj.setPlanlcocompercent(reqobj.get("lcoComPer"));
			priceobj.setDiscountincl(Boolean.parseBoolean(reqobj.get("discountIncl")));
			priceobj.setUpdatedate(Dateformat.getCurrentDatetime());
			quotaplanlcopricesRepository.save(priceobj);

		}
		return ResponseEntity.ok(new MessageResponse("Successfully Edited!!"));
	}

	@Override
	public ResponseEntity<?> setPlanStatus(Map<String, String> reqobj) {

		try {
			if (reqobj.get("planType").equalsIgnoreCase("MAINPLAN")) {

				quotaplanRepository.setMainplanStatus(Integer.parseInt(reqobj.get("planid")),
						Boolean.parseBoolean(reqobj.get("status")));

				if (!Boolean.parseBoolean(reqobj.get("status"))) {
					List<Quotasubplan> subplanlist = quotasubplanRepository
							.findByPlanid(Integer.parseInt(reqobj.get("planid")));
					for (Quotasubplan qsp : subplanlist) {
						quotasubplanRepository.setSubplanStatus(qsp.getId(),
								Boolean.parseBoolean(reqobj.get("status")));
					}
				}

			} else if (reqobj.get("planType").equalsIgnoreCase("SUBPLAN")) {

				quotasubplanRepository.setSubplanStatus(Integer.parseInt(reqobj.get("planid")),
						Boolean.parseBoolean(reqobj.get("status")));
			}

			return ResponseEntity.ok(new MessageResponse("Success!!"));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new MessageResponse("Failed!!"));
		}
	}

	@Override
	public List<AllPlanListDTO> getAllPlanList(String username, String role, String client) {

		if (role.equalsIgnoreCase("OPERATOR")) {

			if (client.contains("RIDSYS")) {
				return quotaplanRepository.getAllPlanListByOpidForRnet(username);

			} else {
				return quotaplanRepository.getAllPlanListByOpid(username);

			}

		} else {
			return quotaplanRepository.getAllPlanList();

		}
	}

	@Override
	public UserplanDetailDTO getPlanDetailsByPlanid(int planid, String username) {
		try {

			Userinfo userobj = userinfoRepository.findByUsername(username);

			if (planid > 0) {

				if (duplicatesubplanRepository.existsBySubplanidAndOpid(planid, userobj.getOpid())) {
					return quotaplanlcopricesRepository.getDuplicatePlanDetailsByPlanid(userobj.getOpid(), planid);

				} else {

					if (quotaplanlcopricesRepository.existsBySubplanidAndOpid(planid, userobj.getOpid())) {
						return quotaplanlcopricesRepository.getPlanDetailsByPlanid(userobj.getOpid(), planid);

					} else {
						return quotaplanlcopricesRepository.getPlanDetailsByPlanid(0, planid);
					}
				}
			}
		} catch (Exception e) {

		}
		return null;
	}

	@Override
	public ResponseEntity<?> createDuplicatePlan(Map<String, String> robj) {

		try {

			Operators opobj = operatorsRepository.findByUsername(robj.get("username"));
			if (opobj != null) {
				int subplanid = Integer.parseInt(robj.get("subplanid"));
				int planid = Integer.parseInt(robj.get("planid"));
				int opid = opobj.getId();
				int discoutDays = Integer.parseInt(robj.get("discountMonth")) * 30;

				Quotaplanlcoprices priceobj = quotaplanlcopricesRepository.findBySubplanidAndOpid(subplanid, opid);
				Duplicatesubplan dupsubobj = duplicatesubplanRepository.findBySubplanidAndOpid(subplanid, opid);

				if (dupsubobj == null) {
					dupsubobj = new Duplicatesubplan();
					dupsubobj.setCreationdate(Dateformat.getCurrentDatetime());

				} else {
					dupsubobj.setUpdatedate(Dateformat.getCurrentDatetime());

				}
				int plantimebank = (Integer.parseInt(robj.get("planTimeBank")) * 30);
				dupsubobj.setSubplanid(subplanid);
				dupsubobj.setPlanid(planid);
				dupsubobj.setOpid(opid);
				dupsubobj.setMrp(robj.get("mrp"));
				dupsubobj.setSubplan(robj.get("subplan"));
				dupsubobj.setPrice(robj.get("price"));
				dupsubobj.setTax(robj.get("tax"));
				dupsubobj.setTaxcost(robj.get("taxCost"));
				dupsubobj.setTaxinclude(true);
				dupsubobj.setPlantimebank(String.valueOf(plantimebank));
				duplicatesubplanRepository.save(dupsubobj);

				if (priceobj == null) {
					priceobj = new Quotaplanlcoprices();
					priceobj.setCreationdate(Dateformat.getCurrentDatetime());
					priceobj.setOpid(opid);
					priceobj.setSubplanid(subplanid);

				} else {
					priceobj.setUpdatedate(Dateformat.getCurrentDatetime());

				}
				priceobj.setPlancuscost(robj.get("cusCost"));
				priceobj.setPlandiscount(robj.get("discount"));
				priceobj.setPlandiscountdays(String.valueOf(discoutDays));
				priceobj.setPlanlcocom(robj.get("lcoCom"));
				priceobj.setPlanmsocom(robj.get("msoCom"));
				priceobj.setPlanlcocompercent(robj.get("lcoComPer"));
				priceobj.setDiscountincl(Boolean.parseBoolean(robj.get("discountIncl")));
				priceobj.setIsactive(true);
				priceobj.setIsduplicate(true);

				quotaplanlcopricesRepository.save(priceobj);
				return ResponseEntity.ok(new MessageResponse("Successfully Duplicate plan created!"));

			} else {
				return ResponseEntity.badRequest().body(new MessageResponse("Check Input!"));
			}
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new MessageResponse("Something Went Wrong!"));
		}
	}

	@Override
	public List<PlanDTO> getSubplanById(String username, int planId, int subplanId) {
		if (username != null && !username.equals("")) {

			Operators opobj = operatorsRepository.findByUsername(username);

			if (duplicatesubplanRepository.existsBySubplanidAndOpid(subplanId, opobj.getId())) {
				return quotasubplanRepository.getDuplicateSubplanByIdAndOpid(subplanId, opobj.getId(), planId);
			} else {
				if (quotaplanlcopricesRepository.existsBySubplanidAndOpid(subplanId, opobj.getId())) {
					return quotasubplanRepository.getSubplanByIdAndOpid(subplanId, opobj.getId(), planId);

				} else {
					return quotasubplanRepository.getSubplanByIdAndOpid(subplanId, 0, planId);
				}
			}

		}
		return null;
	}

	@Override
	public List<TicketSubjectTypeDTO> getSubplanByUserPlanprice(String username, boolean isonemonth) {

		Userinfo obj = userinfoRepository.findByUsername(username);
		return quotaplanRepository.getSubplanByUserPlanprice(username, obj.getOpid(), isonemonth);

	}

}
