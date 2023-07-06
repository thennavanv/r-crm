package com.ridsys.rib.service.impl;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ridsys.rib.DTO.UserListDTO;
import com.ridsys.rib.comm.Dateformat;
import com.ridsys.rib.models.Operator_change_history;
import com.ridsys.rib.models.Operators;
import com.ridsys.rib.models.Smshistory;
import com.ridsys.rib.models.Userbillinfo;
import com.ridsys.rib.models.Userinfo;
import com.ridsys.rib.payload.request.CreateuserRequest;
import com.ridsys.rib.payload.request.SmsRequest;
import com.ridsys.rib.payload.request.Smsuser;
import com.ridsys.rib.payload.request.UserRegisterVerificationRequest;
import com.ridsys.rib.payload.response.MessageResponse;
import com.ridsys.rib.repository.OperatorsRepository;
import com.ridsys.rib.repository.RadcheckRepository;
import com.ridsys.rib.repository.SmshistoryRepository;
import com.ridsys.rib.repository.UserbillinfoRepository;
import com.ridsys.rib.repository.UserinfoRepository;
import com.ridsys.rib.service.SMSService;
import com.ridsys.rib.service.ScheduleService;

@Service
public class SMSServiceImpl implements SMSService {

	private UserinfoRepository userinfoRepository;
	private RadcheckRepository radcheckRepository;

	@Autowired
	private OperatorsRepository operatorsRepository;

	@Autowired
	ScheduleService scheduleService;

	@Autowired
	SmshistoryRepository smshistoryRepository;

	@Autowired
	UserbillinfoRepository userbillinfoRepository;

	public SMSServiceImpl(RadcheckRepository radcheckRepository, UserinfoRepository userinfoRepository) {
		super();
		this.radcheckRepository = radcheckRepository;
		this.userinfoRepository = userinfoRepository;
	}

	@Override
	public ResponseEntity<?> sendSMS(UserRegisterVerificationRequest object) {

		CreateuserRequest obj = object.getUserinfo();

		if (obj.getUsername() == "" || obj.getPassword() == "") {
			return ResponseEntity.badRequest().body(new MessageResponse("username or password are empty"));

		} else if (obj.getOpusername() == "") {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("for creating user is not authorized operator"));

		} else if (obj.getAuthtype() == "") {
			return ResponseEntity.badRequest().body(new MessageResponse("authtype is empty"));
		}

		if (radcheckRepository.existsByUsername(obj.getUsername())
				|| userinfoRepository.existsByUsername(obj.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("The username is already in use!"));

		} else if (obj.getUserinfo().getMobilephone() != ""
				&& userinfoRepository.existsByMobilephone(obj.getUserinfo().getMobilephone())) {
			return ResponseEntity.badRequest().body(new MessageResponse("The mobile number is already in use!"));

		} else if (obj.getUserinfo().getWorkphone() != ""
				&& userinfoRepository.existsByWorkphone(obj.getUserinfo().getWorkphone())) {
			return ResponseEntity.badRequest().body(new MessageResponse("The mobile number is already in use!"));

		}

//		String url = "https://api.mylogin.co.in/api/v2/SendSMS";
//		RestTemplate restTemplate = new RestTemplate();
//
//		Object[] response = restTemplate.postForObject(url, object.getSmsinfo(), Object[].class);
//
//		System.out.print(response.toString());

		return ResponseEntity.ok(new MessageResponse("OTP Sent to your entered mobile number"));
	}

	@SuppressWarnings("null")
	@Override
	public ResponseEntity<?> sendBulkSms(SmsRequest reqobj) {

		String nonaffectuser = null;

		List<String> smsuserlist = new ArrayList();

		if (reqobj.getReceiverrole().equalsIgnoreCase("OPERATOR")) {
			System.out.println("operator loop");

			for (Smsuser s : reqobj.getOperatorid()) {
				smsuserlist.add(s.getUsername());
			}
		} else if (reqobj.getReceiverrole().equalsIgnoreCase("USER")) {
			System.out.println("user loop");

			if (reqobj.getUserid().get(0).getUsername().equalsIgnoreCase("all") && reqobj.getUserid().size() <= 1) {
				System.out.println("all");
				for (Smsuser s : reqobj.getOperatorid()) {

					Operators op = operatorsRepository.findByUsername(s.getUsername());

					System.out.println("operator name: " + op.getUsername());
					for (Userinfo u : userinfoRepository.findByOpid(op.getId()))
						smsuserlist.add(u.getUsername());
				}
			} else {
				System.out.println("non all");
				for (Smsuser s : reqobj.getUserid()) {
					smsuserlist.add(s.getUsername());
				}
			}

		}

//		System.out.println(smsuserlist + "   ");
//		if (smsuserlist.size() > 0) {
//			String[] userlist = smsuserlist.toArray(new String[0]);
//
//			System.out.println(smsuserlist + "   " + userlist);
//
//			if (userlist.length > 0) {
//				Map<String, Long> map = Arrays.stream(userlist)
//						.collect(Collectors.groupingBy(s -> s, Collectors.counting()));
//
//				System.out.println(map);
//
//				for (Map.Entry<String, Long> entry : map.entrySet()) {
//
//					if (entry.getValue() % 2 == 0) {
//
//					} else {

		for (String smsusersname : removeDuplicate(smsuserlist)) {

			System.out.println("username is: " + smsusersname);

			String mobileno = null;
			String username = "";

			if (reqobj.getReceiverrole().equalsIgnoreCase("USER")) {
				System.out.println("coming into user role");
				Userinfo userobj = new Userinfo();
				UserListDTO userbillobj = new UserListDTO();
				try {
					userobj = userinfoRepository.findByUsername(smsusersname);
					userbillobj = userinfoRepository.getUserDetailtByUsername(smsusersname);

					System.out.println(reqobj.getMessage());

					if (!(reqobj.getSubject().equalsIgnoreCase("self"))) {

						reqobj.setMessage(reqobj.getMessage().replace("{plan}", userbillobj.getPlan())
								.replace("{date}", userbillobj.getQuotaexpirydate() + " 23:59 hrs")
								.replace("{username}", userobj.getUsername())
								.replace("{password}", userobj.getMobilephone())
								.replace("{planid}", String.valueOf(userbillobj.getPlanid())));
					}

					System.out.println(reqobj.getMessage());

					username = userobj.getUsername();

					mobileno = userobj.getMobilephone();
				} catch (Exception e) {
					// TODO: handle exception
				}

			} else if (reqobj.getReceiverrole().equalsIgnoreCase("OPERATOR")) {
				System.out.println("coming into operator role");
				Operators opobj = new Operators();
				try {
					opobj = operatorsRepository.findByUsername(smsusersname);

					username = opobj.getUsername();

					mobileno = opobj.getPhone1();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}

			System.out.println(mobileno);

			if (mobileno != null) {

				Smshistory smsobj = new Smshistory();

				smsobj.setUsername(username);
				smsobj.setContent(reqobj.getMessage());
				smsobj.setCreationby(reqobj.getRole());
				smsobj.setCreationbyusername(reqobj.getUsername());
				smsobj.setRole(reqobj.getReceiverrole());
				smsobj.setMobileno(mobileno);
				smsobj.setIsactive(true);
				smsobj.setCreationdate(Dateformat.getCurrentDatetime());

				String result = null;
				String message[] = reqobj.getMessage().split("=");
				System.out.println(message.length);
				for (String s : message) {
					System.out.println(s);
					try {

						result = scheduleService.fast2SMSservice("6380301519", reqobj.getMessage());
					} catch (URISyntaxException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					JSONParser parser = new JSONParser();
					Object json = null;
					try {
						json = parser.parse(result);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					JSONObject json_data = (JSONObject) json;

					// fast2SMS

					if (result.contains("request_id")) {
						smsobj.setStatus("sent");
						smsobj.setMessagedesc("Success");
						smsobj.setMessageid(json_data.get("request_id").toString());

					} else {
						smsobj.setStatus("Not sent");
						smsobj.setMessagedesc("Failed");
						smsobj.setErrordesc(json_data.get("message").toString());
					}

////				//SMS manthra
////
////				if (Integer.parseInt(json_data.get("ErrorCode").toString()) == 0) {
////
////					JSONArray data = (JSONArray) json_data.get("Data");
////					JSONObject obj = (JSONObject) data.get(0);
////
////					smsobj.setStatus("sent");
////					smsobj.setMessagedesc(obj.get("MessageErrorDescription").toString());
////					smsobj.setMessageid(obj.get("MessageId").toString());
////
////				} else {
////
////					smsobj.setStatus("Not sent");
////					smsobj.setMessagedesc("Failed");
////					smsobj.setErrordesc(json_data.get("ErrorDescription").toString());
////
////				}
//
					smshistoryRepository.save(smsobj);
				}

			} else {

				if (nonaffectuser == null) {
					nonaffectuser = smsusersname + ",";
				} else {
					nonaffectuser = nonaffectuser + smsusersname + ",";
				}

				System.out.println(nonaffectuser);
			}

		}
//				}
//			}
//		}

		if (nonaffectuser != null && !nonaffectuser.equals(" "))

		{
			return ResponseEntity.ok(new MessageResponse(
					"Success!  :Not Valid Mobile No for " + nonaffectuser.substring(0, nonaffectuser.length() - 1)));
		} else {
			return ResponseEntity.ok(new MessageResponse("SMS sent!"));
		}
	}

	public List<String> removeDuplicate(List<String> list) {

		List<String> newList = new ArrayList<String>();
		for (String s : list) {

			if (!newList.contains(s)) {

				newList.add(s);
			}
		}
		System.out.println(newList + "  \n" + newList.size() + " \n" + list.size());
		return newList;

	}

	@Override
	public List<Smshistory> getSMShistory(String username, String role, String status) {

		if (role.equalsIgnoreCase("ADMIN")) {

			if (status.equalsIgnoreCase("ALL")) {

				return smshistoryRepository.getSmsHistoryByAll();

			} else {

				return smshistoryRepository.getSmsHistoryByStatusAll(status);
			}

		} else if (role.equalsIgnoreCase("OPERATOR")) {

			if (status.equalsIgnoreCase("ALL")) {
				return smshistoryRepository.getSmsHistoryByOpid(username);
			} else {
				return smshistoryRepository.getSmsHistoryByOpidAndStatus(username, status);
			}

		}

		return null;
	}

	@Override
	public List<Smshistory> getSMSbyMobile(String mobileno) {

		if (mobileno != null) {

			return smshistoryRepository.getSMSbyMobile(mobileno);

		}
		return null;
	}
}
