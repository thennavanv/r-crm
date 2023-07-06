package com.ridsys.rib.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.decimal4j.util.DoubleRounder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ridsys.rib.DTO.SpinListDTO;
import com.ridsys.rib.DTO.TicketSubjectTypeDTO;
import com.ridsys.rib.comm.Dateformat;
import com.ridsys.rib.comm.IdGen;
import com.ridsys.rib.comm.OttApi;
import com.ridsys.rib.models.Operators;
import com.ridsys.rib.models.Ottpackages;
import com.ridsys.rib.models.Ottplanmanagelog;
import com.ridsys.rib.models.Ottuserinfo;
import com.ridsys.rib.models.Userinfo;
import com.ridsys.rib.models.Walletupdatelog;
import com.ridsys.rib.payload.request.OttplanactivationRequest;
import com.ridsys.rib.payload.response.MessageResponse;
import com.ridsys.rib.repository.OperatorsRepository;
import com.ridsys.rib.repository.OttpackagesRepository;
import com.ridsys.rib.repository.OttplanmanagelogRepository;
import com.ridsys.rib.repository.OttuserinfoRepository;
import com.ridsys.rib.repository.UserinfoRepository;
import com.ridsys.rib.repository.WalletupdatelogRepository;
import com.ridsys.rib.service.OttplanmanagelogService;

@Service
public class OttplanmanagelogServiceImpl implements OttplanmanagelogService {

	@Autowired
	OttplanmanagelogRepository ottplanmanagelogRepository;

	@Autowired
	UserinfoRepository userinfoRepository;

	@Autowired
	OttpackagesRepository ottpackagesRepository;

	@Autowired
	OperatorsRepository operatorsRepository;

	@Autowired
	OttuserinfoRepository ottuserinfoRepository;

	@Autowired
	WalletupdatelogRepository walletupdatelogRepository;

//	@Override
	public ResponseEntity<?> activeOttPlan(OttplanactivationRequest robj) {

		try {
			String walletbalancestr = "0";
			double walletbalance = 0;
			double newbalance = 0;
			double packagerate = 0;

			;
			if (!userinfoRepository.existsById(Integer.parseInt(robj.getUserid()))) {
				return ResponseEntity.badRequest().body(new MessageResponse("This customer is not authorized!.."));
			}

//		if (Integer.parseInt(robj.getPackagerate()) < 0) {
//			return ResponseEntity.badRequest().body(new MessageResponse("Please select a Package"));
//		}

			if (Integer.parseInt(robj.getPlanDays()) < 30) {
				return ResponseEntity.badRequest().body(new MessageResponse("Please select a plan"));
			}
//		if (!ottpackagesRepository.existsByPackagerate(Double.parseDouble(robj.getPackagerate()))) {
//			return ResponseEntity.badRequest()
//					.body(new MessageResponse("Your selected plan is currently not available!."));
//		}

			if (!ottpackagesRepository.existsById(robj.getId())) {
				return ResponseEntity.badRequest()
						.body(new MessageResponse("Your selected plan is currently not available!."));
			}

			Operators opobj = operatorsRepository.findById(Integer.parseInt(robj.getLcoid()));

			Userinfo userobj = userinfoRepository.findById(Integer.parseInt(robj.getUserid()));

			Ottpackages packobj = ottpackagesRepository.findById(robj.getId());

			if (!ottuserinfoRepository.existsByUsername(userobj.getUsername())) {
				return ResponseEntity.badRequest()
						.body(new MessageResponse("This customer is not authorized for using OTT plan!.."));
			}

			if (robj.getRole().equalsIgnoreCase("ADMIN")) {
				robj.setCreationby("admin");

			} else if (robj.getRole().equalsIgnoreCase("OPERATOR")) {
				robj.setCreationby(opobj.getUsername());
			}

			final String expirydate = Dateformat.expiryDate(Integer.parseInt(robj.getPlanDays()));
			final int packageid = packobj.getId();
			final int planpackageid = packobj.getPackageid();
			final String startdate = Dateformat.getCurrentDatetime();
			final String rechargeid = IdGen.genID("OTT");
			packagerate = (packobj.getPackagerate() / 30) * (Integer.parseInt(robj.getPlanDays()));
//		packagerate = (((Integer.parseInt(robj.getPlanDays())) / 30) * packobj.getPackagerate());

			if (opobj != null) {

				if (Double.parseDouble(opobj.getWalletbalance()) < packagerate) {
					return ResponseEntity.badRequest().body(new MessageResponse("Insufficient wallet balance!."));
				}

				walletbalancestr = opobj.getWalletbalance();
				walletbalance = Double.parseDouble(walletbalancestr);
				newbalance = DoubleRounder.round((walletbalance - packagerate), 2);

				Walletupdatelog opwul = new Walletupdatelog();

				// Deduct from operator log
				opwul = new Walletupdatelog("OPERATOR", opobj.getUsername(), walletbalancestr,
						String.valueOf(packagerate), String.valueOf(newbalance), rechargeid, 3, 2, robj.getRole(),
						robj.getCreationby(), startdate);

				ottuserinfoRepository.updateOttuserInfo(packageid, startdate, expirydate, userobj.getUsername());
				walletupdatelogRepository.save(opwul);
				operatorsRepository.walletBalanceDetection(opobj.getId(), packagerate);

				Ottplanmanagelog ottlog = new Ottplanmanagelog();

				ottlog.setRechargeid(rechargeid);
				ottlog.setUsername(userobj.getUsername());
				ottlog.setPackageid(packageid);
				ottlog.setPlandays(robj.getPlanDays());
				ottlog.setCreationdate(startdate);
				ottlog.setExpirydate(expirydate);
				ottlog.setPlancost(String.valueOf(packagerate));
				ottlog.setCreationby(robj.getRole());
				ottlog.setCreationbyusername(robj.getCreationby());
				ottlog.setDescription("Ott plan activation");
				ottplanmanagelogRepository.save(ottlog);

				try {
					/////// ott api calling for log
					String result = OttApi.planActivation(ottlog, planpackageid);

				} catch (Exception e) {
					System.out.println("Exception at calling plan activation log api: " + e);
				}

				return ResponseEntity.ok(new MessageResponse("Plan activated"));
			}
		} catch (Exception e) {

		}

		return null;
	}

	public ResponseEntity<?> deactiveOttPlan(String username, String creationbyrole, String creationbyusername) {
		try {

			Ottplanmanagelog opml = ottplanmanagelogRepository.getActivePlanDetailByUsername(username);

			Userinfo userobj = userinfoRepository.findByUsername(username);

			Ottuserinfo otobj = ottuserinfoRepository.findByUsername(username);

			Operators opobj = operatorsRepository.findById(userobj.getOpid());

			Ottpackages packobj = ottpackagesRepository.findById(opml.getPackageid());

//			if (robj.getRole().equalsIgnoreCase("ADMIN")) {
//				robj.setCreationby("admin");
//
//			} else if (robj.getRole().equalsIgnoreCase("OPERATOR")) {
//				robj.setCreationby(opobj.getUsername());
//			}

			if (otobj != null) {
				String oldexpiry = otobj.getPlanexpirydate();
				String newexpiry = Dateformat.getCurrentDatetime();
				final String rechargeid = IdGen.genID("OTT");
				Walletupdatelog opwul = new Walletupdatelog();

				SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date date1 = myFormat.parse(newexpiry);
				Date date2 = myFormat.parse(oldexpiry);
				long diff = date2.getTime() - date1.getTime();

				// days calculation
				float days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

				// if the plan is not expiry
				if (date1.compareTo(date2) < 0) {
					double refundrate = DoubleRounder.round(
							((Double.parseDouble(opml.getPlancost()) / Integer.parseInt(opml.getPlandays())) * days),
							2);

					System.out.println(refundrate);
					double price = refundrate - ((refundrate * 18) / 100);

					System.out.println(price);

					double lcorefund = (price * packobj.getLcoper()) / 100;

					System.out.println(lcorefund);

					String walletbalanceold = opobj.getWalletbalance();
					double walletbalance = Double.parseDouble(walletbalanceold);
					double newbalance = DoubleRounder.round((walletbalance + lcorefund), 2);

					opwul = new Walletupdatelog("OPERATOR", opobj.getUsername(), walletbalanceold,
							String.valueOf(lcorefund), String.valueOf(newbalance), rechargeid, 2, 1, creationbyrole,
							creationbyusername, newexpiry);

					walletupdatelogRepository.save(opwul);
					operatorsRepository.walletBalanceCredit(opobj.getId(), lcorefund);

					Ottplanmanagelog ottlog = new Ottplanmanagelog();

					ottlog.setRechargeid(rechargeid);
					ottlog.setUsername(userobj.getUsername());
					ottlog.setPackageid(opml.getPackageid());
					ottlog.setPlandays(String.valueOf(days));
					ottlog.setCreationdate(newexpiry);
					ottlog.setExpirydate(newexpiry);
					ottlog.setPlancost(String.valueOf(refundrate));
					ottlog.setCreationby(creationbyrole);
					ottlog.setCreationbyusername(creationbyusername);
					ottlog.setDescription("Ott plan Deactivation");
					ottlog.setLcocom(lcorefund);
					ottlog.setMsocom(refundrate - lcorefund);
					ottplanmanagelogRepository.save(ottlog);

					ottuserinfoRepository.updateOttuserAfterDeactive(newexpiry, userobj.getUsername());

					try {
						/////// ott api calling for log
						String result = OttApi.planDectivation(ottlog);
					} catch (Exception e) {
						System.out.println("Exception at calling plan Dectivation log api: " + e);
					}

					return ResponseEntity.ok(new MessageResponse("Plan Deactivated"));
				}

				return ResponseEntity.badRequest().body(new MessageResponse("The User plan is already Expired!!"));
			}
		} catch (Exception e) {
			System.out.println("Exception at:" + e);
		}
		return null;
	}

	@Override
	public Map<String, Object> getPlanDetailsByUser(String username) {
		Map<String, Object> map = new HashMap<>();

		SpinListDTO sp = ottplanmanagelogRepository.getPlanDetailByUser(username);

		map.put("planCost", sp.getUsername());
		map.put("plandays", sp.getName());
		map.put("packageid", sp.getId());

		return map;

	}

	@Override
	public ResponseEntity<?> createOttPackage(Map<String, String> reqobj) {

		System.out.println(reqobj);

		if (reqobj.get("status").equalsIgnoreCase("create")) {
			if (ottpackagesRepository.existsByPackageid(Integer.parseInt(reqobj.get("packageid")))) {
				return ResponseEntity.ok(new MessageResponse("Package Already Exists!!"));
			}

			Ottpackages obj = new Ottpackages();
			obj.setPackagename(reqobj.get("packagename"));
			obj.setPackagerate(Double.parseDouble(reqobj.get("packagerate")));
			obj.setPackageid(Integer.parseInt(reqobj.get("packageid")));
			obj.setPackagedesc(reqobj.get("packagedesc"));
			obj.setLcocom(Double.parseDouble(reqobj.get("lco_com")));
			obj.setLcoper(Double.parseDouble(reqobj.get("lco_per")));
			obj.setMsocom(Double.parseDouble(reqobj.get("mso_com")));
			obj.setMrp(Double.parseDouble(reqobj.get("mrp")));
			obj.setTaxamount(Double.parseDouble(reqobj.get("tax_amount")));
			obj.setTaxper(Double.parseDouble(reqobj.get("tax_per")));

			ottpackagesRepository.save(obj);

			return ResponseEntity.ok(new MessageResponse(reqobj.get("packagename") + " Successfully created!!"));
		}
		return null;
	}

	@Override
	public List<Ottpackages> getOttpackageList() {
		return ottpackagesRepository.findAll();
	}

	@Override
	public List<TicketSubjectTypeDTO> getOttPlanList() {
		List<TicketSubjectTypeDTO> planlist = ottpackagesRepository.getOttPlans();
		return planlist;
	}

	@Override
	public Ottuserinfo getSubsDetails(@Valid String mobile_no) {
		return ottuserinfoRepository.findByMobilephone(mobile_no);
	}

	@Override
	public ResponseEntity<?> activeOttPlan1(@Valid OttplanactivationRequest robj) {

		try {
			String walletbalancestr = "0";
			double walletbalance = 0;
			double newbalance = 0;
			double packagerate = 0;

			if (!userinfoRepository.existsByUsername(robj.getUsername())) {
				return ResponseEntity.badRequest().body(new MessageResponse("This customer is not authorized!.."));
			}

			if (Integer.parseInt(robj.getPlanDays()) < 30) {
				return ResponseEntity.badRequest().body(new MessageResponse("Please select a plan"));
			}

			if (!ottpackagesRepository.existsById(robj.getId())) {
				return ResponseEntity.badRequest()
						.body(new MessageResponse("Your selected plan is currently not available!."));
			}

			Userinfo userobj = userinfoRepository.findByUsername(robj.getUsername());

			Operators opobj = operatorsRepository.findById(userobj.getOpid());

			Ottpackages packobj = ottpackagesRepository.findById(robj.getId());

			if (!ottuserinfoRepository.existsByUsername(userobj.getUsername())) {
				return ResponseEntity.badRequest()
						.body(new MessageResponse("This customer is not authorized for using OTT plan!.."));
			}

			if (robj.getRole().equalsIgnoreCase("ADMIN")) {
				robj.setCreationby("admin");
			} else if (robj.getRole().equalsIgnoreCase("OPERATOR")) {
				robj.setCreationby(opobj.getUsername());
			} else if (robj.getRole().equalsIgnoreCase("USER")) {
				robj.setCreationby(opobj.getUsername());
			}

			final String expirydate = Dateformat.expiryDate(Integer.parseInt(robj.getPlanDays()));
			final int packageid = packobj.getId();
			final int planpackageid = packobj.getPackageid();
			final String startdate = Dateformat.getCurrentDatetime();

			String rechargeid = "";

			if (robj.getPaymenttype().equalsIgnoreCase("online")) {
				rechargeid = robj.getRechargeid();
				packagerate = (packobj.getLcocom() / 30) * (Integer.parseInt(robj.getPlanDays()));
			} else {
				rechargeid = IdGen.genID("OTT");
				packagerate = (packobj.getMsocom() / 30) * (Integer.parseInt(robj.getPlanDays()));
			}

			double plancost = (packobj.getMrp() / 30) * (Integer.parseInt(robj.getPlanDays()));
			System.out.println("plancost="+plancost);
			double lco_com = (packobj.getLcocom() / 30) * (Integer.parseInt(robj.getPlanDays()));
			System.out.println("plancost="+lco_com);
			double mso_com = (packobj.getMsocom() / 30) * (Integer.parseInt(robj.getPlanDays()));
			System.out.println("plancost="+mso_com);

			if (opobj != null) {

				walletbalancestr = opobj.getWalletbalance();
				walletbalance = Double.parseDouble(walletbalancestr);

				if (robj.getPaymenttype().equalsIgnoreCase("online")) {
					newbalance = DoubleRounder.round((walletbalance + packagerate), 2);
					operatorsRepository.walletBalanceCredit(opobj.getId(), packagerate);
				} else {
					newbalance = DoubleRounder.round((walletbalance - packagerate), 2);
					operatorsRepository.walletBalanceDetection(opobj.getId(), packagerate);
				}

				Walletupdatelog opwul = new Walletupdatelog();

				opwul = new Walletupdatelog("OPERATOR", opobj.getUsername(), walletbalancestr,
						String.valueOf(packagerate), String.valueOf(newbalance), rechargeid, 2, 2, robj.getRole(),
						robj.getCreationby(), startdate);

				ottuserinfoRepository.updateOttuserInfo(packageid, startdate, expirydate, userobj.getUsername());

				walletupdatelogRepository.save(opwul);

				Ottplanmanagelog ottlog = new Ottplanmanagelog();

				ottlog.setRechargeid(rechargeid);
				ottlog.setUsername(userobj.getUsername());
				ottlog.setPackageid(packageid);
				ottlog.setPlandays(robj.getPlanDays());
				ottlog.setCreationdate(startdate);
				ottlog.setExpirydate(expirydate);
				ottlog.setPlancost(String.valueOf(plancost));
				ottlog.setCreationby(robj.getRole());
				ottlog.setCreationbyusername(robj.getCreationby());
				ottlog.setDescription("Ott plan activation");
				ottlog.setLcocom(lco_com);
				ottlog.setMsocom(mso_com);

				ottplanmanagelogRepository.save(ottlog);

				try {
					/////// ott api calling for log
					String result = OttApi.planActivation(ottlog, planpackageid);
				} catch (Exception e) {
					System.out.println("Exception at calling plan activation log api: " + e);
				}

				return ResponseEntity.ok(new MessageResponse("Plan activated"));
			}
		} catch (Exception e) {

		}
		return null;
	}
}
