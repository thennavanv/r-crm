package com.ridsys.rib.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.net.http.HttpRequest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.HashMap;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.decimal4j.util.DoubleRounder;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.ridsys.rib.comm.Dateformat;
import com.ridsys.rib.comm.OnlinePaymentAssets;
import com.ridsys.rib.comm.PaymentStatus;
import com.ridsys.rib.models.Duplicatesubplan;
import com.ridsys.rib.models.Online_payment_log;
import com.ridsys.rib.models.Online_plan_payment;
import com.ridsys.rib.models.Operators;
import com.ridsys.rib.models.Userinfo;
import com.ridsys.rib.models.Walletupdatelog;
import com.ridsys.rib.payload.response.MessageResponse;
import com.ridsys.rib.repository.Billing_plansRepository;
import com.ridsys.rib.repository.DuplicatesubplanRepository;
import com.ridsys.rib.repository.Online_payment_logRepository;
import com.ridsys.rib.repository.Online_plan_paymentRepository;
import com.ridsys.rib.repository.OperatorsRepository;
import com.ridsys.rib.repository.UserinfoRepository;
import com.ridsys.rib.repository.WalletupdatelogRepository;
import com.ridsys.rib.service.OnlinePaymentService;

import aj.org.objectweb.asm.Type;

import com.ridsys.rib.repository.PaymentGatewayMasterRepository;
import com.ridsys.rib.repository.QuotaplanlcopricesRepository;
import com.ridsys.rib.repository.QuotasubplanRepository;
import com.ridsys.rib.repository.UserbillinfoRepository;
import com.ridsys.rib.DTO.OnlineRechargelistDTO;
import com.ridsys.rib.DTO.OnlinepaymentDTO;
import com.ridsys.rib.DTO.PlanCostDTO;
import com.ridsys.rib.PaymentGateway.RazorPay.Assets;
import com.ridsys.rib.PaymentGateway.RazorPay.OrderCreate;
import com.ridsys.rib.PaymentGateway.RazorPay.OrderEntity;
import com.ridsys.rib.PaymentGateway.RazorPay.Signature;
import com.ridsys.rib.payload.request.PaymentRequest;
import com.ridsys.rib.payload.request.QuatoplanactivationRequest;
import com.ridsys.rib.models.PaymentGatewayMaster;
import com.ridsys.rib.models.Quotaplanlcoprices;
import com.ridsys.rib.models.Quotasubplan;
import com.ridsys.rib.models.Userbillinfo;

@Service
public class OnlinePaymentServiceImpl implements OnlinePaymentService {

	@Autowired
	private OperatorsRepository operatorsRepository;

	@Autowired
	private UserinfoRepository userinfoRepository;

	@Autowired
	private Online_payment_logRepository onlinePaymentLogRepository;

	@Autowired
	private PaymentGatewayMasterRepository paymentGatewayMasterRepository;

	@Autowired
	private WalletupdatelogRepository walletupdatelogRepository;

	@Autowired
	QuotasubplanRepository quotasubplanRepository;

	@Autowired
	Billing_plansRepository billing_plansRepository;

	@Autowired
	UserbillinfoRepository userbillinfoRepository;

	@Autowired
	QuotaplanlcopricesRepository quotaplanlcopricesRepository;

	@Autowired
	Online_plan_paymentRepository online_plan_paymentRepository;

	@Autowired
	QuotaplanmanagingServiceImpl quotaplanserviceimpl;

	@Autowired
	DuplicatesubplanRepository duplicatesubplanRepository;

	@Autowired
	QuotaplanmanagingServiceImpl quotaplanservicimp;

//	@Override
//	public ResponseEntity<?> operatorPaymentRequest(PaymentRequest requestbody) {
//
//		final String RETURN_URL = requestbody.getReturnurl();// "http://localhost:8080/CloudPayment/agreepaypaymentresponse.do";
//		final String AMOUNT = requestbody.getAmount();
//		final String USERNAME = requestbody.getUsername();
//		final String ORDER_ID = "WOP" + OnlinePaymentAssets.generateOrderID();
//		final String SALT = requestbody.getSalt();
//		final String API_KEY = requestbody.getApikey();
//		final String LOCATER_URL = requestbody.getLocaterurl();
//
//		final String CITY = "Pondicherry";
//		final String STATE = "Pondicherry";
//		final String COUNTRY = "india";
//		final String CURRENCY = "INR";
//		final String DESCRIPTION = "Payment by username:" + USERNAME;
//		final String MODE = "LIVE";
//		final String ZIP_CODE = "605001";
//
//		String EMAIL = "";
//		String NAME = "";
//		String PHONE = "";
//		String ADDRESS_1 = "";
//		String ADDRESS_2 = "";
//
//		if (requestbody.getRole().equalsIgnoreCase("OPERATOR")) {
//			Operators object = operatorsRepository.findByUsername(USERNAME);
//			EMAIL = object.getEmail1();
//			NAME = object.getFirstname() + " " + object.getLastname();
//			PHONE = object.getPhone1();
//			ADDRESS_1 = object.getCompany() + "," + object.getDepartment();
//			ADDRESS_2 = object.getCompany() + "," + object.getDepartment();
//
//		} else if (requestbody.getRole().equalsIgnoreCase("USER")) {
//			Userinfo object = userinfoRepository.findByUsername(USERNAME);
//			EMAIL = object.getEmail();
//			NAME = object.getFirstname() + " " + object.getLastname();
//			PHONE = object.getMobilephone();
//			ADDRESS_1 = object.getOpid() + "_" + object.getCity();
//			ADDRESS_2 = object.getCity();
//		}
//
////		Syntax of hash data formation
////		address_line_1|address_line_2|amount|api_key|city|country|currency|description|email|mode|name|order_id|phone|return_url|state|zip_code		
//		String HASH = SALT + "|" + ADDRESS_1 + "|" + ADDRESS_2 + "|" + AMOUNT + "|" + API_KEY + "|" + CITY + "|"
//				+ COUNTRY + "|" + CURRENCY + "|" + DESCRIPTION + "|" + EMAIL + "|" + MODE + "|" + NAME + "|" + ORDER_ID
//				+ "|" + PHONE + "|" + RETURN_URL + "|" + STATE + "|" + ZIP_CODE;
//		System.out.println(HASH);
//		try {
//			HASH = OnlinePaymentAssets.getHashCodeFromString(HASH);
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//			return ResponseEntity.badRequest().body(new MessageResponse("Error creating your payment hash generation"));
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//			return ResponseEntity.badRequest().body(new MessageResponse("Error creating your payment hash generation"));
//		}
//
//		Online_payment_log opl = new Online_payment_log();
//		opl.setOrderid(ORDER_ID);
//		opl.setStatus("Initiated");
//		opl.setAmount(AMOUNT);
//		opl.setGatewayid(1);
//		opl.setRole(requestbody.getRole());
//		opl.setUsername(USERNAME);
//		opl.setPaymenttypeid(4);
//		opl.setTstarttime(Dateformat.getCurrentDatetime());
//
//		// Insert online payment initiate log
//		onlinePaymentLogRepository.save(opl);
//
//		String response = "" + "<html>" + "<body>" + "<form id=\"tnpform\" action=\"" + LOCATER_URL
//				+ "\"  name=\"tnpform\" method=POST >" + "      <input type=\"hidden\" name=\"hash\" value=\"" + HASH
//				+ "\">" + "      <input type=\"hidden\" name=\"api_key\" value=\"" + API_KEY + "\">"
//				+ "      <input type=\"hidden\" name=\"return_url\" value=\"" + RETURN_URL + "\">"
//				+ "      <input type=\"hidden\" name=\"mode\" value=\"" + MODE + "\">"
//				+ "      <input type=\"hidden\" name=\"order_id\" value=\"" + ORDER_ID + "\">"
//				+ "      <input type=\"hidden\" name=\"amount\" value=\"" + AMOUNT + "\">"
//				+ "      <input type=\"hidden\" name=\"currency\" value=\"" + CURRENCY + "\">"
//				+ "      <input type=\"hidden\" name=\"description\" value=\"" + DESCRIPTION + "\">"
//				+ "      <input type=\"hidden\" name=\"name\" value=\"" + NAME + "\">"
//				+ "      <input type=\"hidden\" name=\"email\" value=\"" + EMAIL + "\">"
//				+ "      <input type=\"hidden\" name=\"phone\" value=\"" + PHONE + "\">"
//				+ "      <input type=\"hidden\" name=\"address_line_1\" value=\"" + ADDRESS_1 + "\">"
//				+ "      <input type=\"hidden\" name=\"address_line_2\" value=\"" + ADDRESS_2 + "\">"
//				+ "      <input type=\"hidden\" name=\"city\" value=\"" + CITY + "\">"
//				+ "      <input type=\"hidden\" name=\"country\" value=\"" + COUNTRY + "\">"
//				+ "      <input type=\"hidden\" name=\"state\" value=\"" + STATE + "\">"
//				+ "      <input type=\"hidden\" name=\"zip_code\" value=\"" + ZIP_CODE + "\">" + "</form>" + "<script>"
//				+ " document.getElementById(\"tnpform\").submit();" + "</script>" + "</body>" + "</html>";
//
//		System.out.print(response);
//
////		PaymentResponse PaymentResponse = new PaymentResponse(TEST_SALT, TEST_API_KEY, RETURN_URL, mode, ORDER_ID,
////				AMOUNT, currency, description, NAME, email, phone, address, address, address, country, address,
////				zip_code);
////		return ResponseEntity.ok(PaymentResponse);
//		return ResponseEntity.ok(response);
//	}

	public ResponseEntity<?> operatorPaymentRequest(PaymentRequest requestbody) {

//		PaymentGatewayMaster PaymentGatewayMaster = paymentGatewayMasterRepository.findByGatewayname("AGGREPAY");

		PaymentGatewayMaster PaymentGatewayMaster = paymentGatewayMasterRepository.findByIsactive(true);

		if (PaymentGatewayMaster.getSalt() != null && PaymentGatewayMaster.getApikey() != null) {

			final String RETURN_URL = requestbody.getReturnurl();// "http://localhost:8080/CloudPayment/agreepaypaymentresponse.do";
			final String AMOUNT = requestbody.getAmount();
			final String USERNAME = requestbody.getUsername();
			final String ORDER_ID = "WOP" + OnlinePaymentAssets.generateOrderID();
			final String SALT = PaymentGatewayMaster.getSalt();
			final String API_KEY = PaymentGatewayMaster.getApikey();
			final String LOCATER_URL = requestbody.getLocaterurl();

			System.out.println(requestbody.getTitle());

			final String CITY = "Pondicherry";
			final String STATE = "Pondicherry";
			final String COUNTRY = "india";
			final String CURRENCY = "INR";
			final String DESCRIPTION = "Payment by username:" + USERNAME;
			final String MODE = PaymentGatewayMaster.getMode();
			final String ZIP_CODE = "605001";

			String EMAIL = "";
			String NAME = "";
			String PHONE = "";
			String ADDRESS_1 = "";
			String ADDRESS_2 = "";

			if (requestbody.getRole().equalsIgnoreCase("OPERATOR")) {
				Operators object = operatorsRepository.findByUsername(USERNAME);
				EMAIL = object.getEmail1();
				NAME = object.getFirstname() + " " + object.getLastname();
				PHONE = object.getPhone1();
				ADDRESS_1 = object.getCompany() + "," + object.getDepartment();
				ADDRESS_2 = object.getCompany() + "," + object.getDepartment();

			} else if (requestbody.getRole().equalsIgnoreCase("USER")) {
				Userinfo object = userinfoRepository.findByUsername(USERNAME);
				EMAIL = object.getEmail();
				NAME = object.getFirstname() + " " + object.getLastname();
				PHONE = object.getMobilephone();
				ADDRESS_1 = object.getOpid() + "_" + object.getCity();
				ADDRESS_2 = object.getCity();
			}

//		Syntax of hash data formation
//		address_line_1|address_line_2|amount|api_key|city|country|currency|description|email|mode|name|order_id|phone|return_url|state|zip_code		
			String HASH = SALT + "|" + ADDRESS_1.trim() + "|" + ADDRESS_2.trim() + "|" + AMOUNT + "|" + API_KEY + "|"
					+ CITY.trim() + "|" + COUNTRY.trim() + "|" + CURRENCY + "|" + DESCRIPTION + "|" + EMAIL.trim() + "|"
					+ MODE + "|" + NAME.trim() + "|" + ORDER_ID + "|" + PHONE.trim() + "|" + RETURN_URL + "|"
					+ STATE.trim() + "|" + ZIP_CODE;
			System.out.println(HASH);
			try {
				HASH = OnlinePaymentAssets.getHashCodeFromString(HASH);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
				return ResponseEntity.badRequest()
						.body(new MessageResponse("Error creating your payment hash generation"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return ResponseEntity.badRequest()
						.body(new MessageResponse("Error creating your payment hash generation"));
			}

			Online_payment_log opl = new Online_payment_log();
			opl.setOrderid(ORDER_ID);
			opl.setStatus("Initiated");
			opl.setAmount(AMOUNT);
			opl.setGatewayid(1);
			opl.setRole(requestbody.getRole());
			opl.setUsername(USERNAME);
			opl.setPaymenttypeid(4);
//			if (requestbody.getTitle().contains("wallet")) {
//
			opl.setRechargetypeid(1);
//			} else if (requestbody.getTitle().contains("plan")) {
//
//				opl.setRechargetypeid(2);
//			}
			opl.setTstarttime(Dateformat.getCurrentDatetime());

			// Insert online payment initiate log
			onlinePaymentLogRepository.save(opl);

			String response = "" + "<html>" + "<body>" + "<form id=\"tnpform\" action=\"" + LOCATER_URL
					+ "\"  name=\"tnpform\" method=POST >" + "      <input type=\"hidden\" name=\"hash\" value=\""
					+ HASH + "\">" + "      <input type=\"hidden\" name=\"api_key\" value=\"" + API_KEY + "\">"
					+ "      <input type=\"hidden\" name=\"return_url\" value=\"" + RETURN_URL + "\">"
					+ "      <input type=\"hidden\" name=\"mode\" value=\"" + MODE + "\">"
					+ "      <input type=\"hidden\" name=\"order_id\" value=\"" + ORDER_ID + "\">"
					+ "      <input type=\"hidden\" name=\"amount\" value=\"" + AMOUNT + "\">"
					+ "      <input type=\"hidden\" name=\"currency\" value=\"" + CURRENCY + "\">"
					+ "      <input type=\"hidden\" name=\"description\" value=\"" + DESCRIPTION + "\">"
					+ "      <input type=\"hidden\" name=\"name\" value=\"" + NAME + "\">"
					+ "      <input type=\"hidden\" name=\"email\" value=\"" + EMAIL + "\">"
					+ "      <input type=\"hidden\" name=\"phone\" value=\"" + PHONE + "\">"
					+ "      <input type=\"hidden\" name=\"address_line_1\" value=\"" + ADDRESS_1 + "\">"
					+ "      <input type=\"hidden\" name=\"address_line_2\" value=\"" + ADDRESS_2 + "\">"
					+ "      <input type=\"hidden\" name=\"city\" value=\"" + CITY + "\">"
					+ "      <input type=\"hidden\" name=\"country\" value=\"" + COUNTRY + "\">"
					+ "      <input type=\"hidden\" name=\"state\" value=\"" + STATE + "\">"
					+ "      <input type=\"hidden\" name=\"zip_code\" value=\"" + ZIP_CODE + "\">" + "</form>"
					+ "<script>" + " document.getElementById(\"tnpform\").submit();" + "</script>" + "</body>"
					+ "</html>";

			System.out.print(response);
//		PaymentResponse PaymentResponse = new PaymentResponse(TEST_SALT, TEST_API_KEY, RETURN_URL, mode, ORDER_ID,
//				AMOUNT, currency, description, NAME, email, phone, address, address, address, country, address,
//				zip_code);
//		return ResponseEntity.ok(PaymentResponse);
			Map<String, String> map = new HashMap<>();
			map.put("order_id", ORDER_ID);
			map.put("response", response);

			return ResponseEntity.ok(map);
		} else {
			return ResponseEntity.badRequest().body(new MessageResponse("Error with payment"));
		}
	}

	@Override
	public List<Online_payment_log> getOnlineRechargeHistory(String username, String role, String subrole, String fdate,
			String tdate) {

		if (!fdate.equals("0000-00-00") && !tdate.equals("0000-00-00")) {
			if (role.equalsIgnoreCase("ADMIN")) {
				return onlinePaymentLogRepository.getOnlineRechargeHistoryByAdminFtdate(subrole, fdate, tdate);

			} else if (role.equalsIgnoreCase("OPERATOR")) {
				return onlinePaymentLogRepository.getOnlineRechargeHistoryFtdate(username, role, fdate, tdate);

			} else {
				return onlinePaymentLogRepository.getOnlineRechargeHistoryUserFtdate(username, role, fdate, tdate);

			}
		} else {
			if (role.equalsIgnoreCase("ADMIN")) {
				return onlinePaymentLogRepository.getOnlineRechargeHistoryByAdmin(subrole);

			} else if (role.equalsIgnoreCase("OPERATOR")) {
				return onlinePaymentLogRepository.getOnlineRechargeHistory(username, role);

			} else {
				return onlinePaymentLogRepository.getOnlineRechargeHistoryUser(username, role);
			}
		}

	}

	@Override
	public List<OnlinepaymentDTO> getAllOnlinePaymentLog(String fdate, String tdate, String username, String role) {

		if (!fdate.equals("0000-00-00") && !tdate.equals("0000-00-00")) {
			if (role.equalsIgnoreCase("ADMIN")) {
				return onlinePaymentLogRepository.getOnlinePaymentHistoryAllFtdate(fdate, tdate);
			} else {
				return onlinePaymentLogRepository.getOnlinePaymentHistoryFtdate(fdate, tdate, username, role);
			}

		} else {
			if (role.equalsIgnoreCase("ADMIN")) {
				return onlinePaymentLogRepository.getOnlinePaymentHistoryAll();
			} else {
				return onlinePaymentLogRepository.getOnlinePaymentHistory(username, role);
			}
		}

	}

	@Override
	public ResponseEntity<?> paymentOrderCreate(OrderCreate obj) {

		// <Validation start
		if (obj.getUsername() == "" || obj.getRole() == "" || obj.getRemark() == "") {
			return ResponseEntity.badRequest().body(new MessageResponse("Username, role or remark is invalid"));
		}

		// Checking if the payment amount is less than 1
		if ((obj.getAmount() / 100) < 1) {
			return ResponseEntity.badRequest().body(new MessageResponse("Payment amount is invalid"));
		}

		// Checking if any payment gateway available with active
		if (paymentGatewayMasterRepository.checkPaymentGatewayStatus() != 1) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("The payment service is temporarily unavailable"));
		}
		// Validation end>

		String name = "";
		String contact = "";
		String email = "";

		if (obj.getRole().equalsIgnoreCase("OPERATOR")) {
			Operators operator = operatorsRepository.findByUsername(obj.getUsername());

			name = operator.getFirstname() + " " + operator.getLastname();
			contact = operator.getPhone1();
			email = operator.getEmail1();

		} else if (obj.getRole().equalsIgnoreCase("USER")) {
			Userinfo userinfo = userinfoRepository.findByUsername(obj.getUsername());

			name = userinfo.getFirstname() + " " + userinfo.getLastname();
			contact = userinfo.getMobilephone();
			email = userinfo.getEmail();
		}

		if (!contact.equals("")) {

			// Find active payment gateway info
//			final PaymentGatewayMaster paymentGatewayMaster = paymentGatewayMasterRepository
//					.findActivePaymentGatewayInfo();

			PaymentGatewayMaster paymentGatewayMaster = paymentGatewayMasterRepository.findByIsactive(true);
			// Receipt generation
			String receipt = Assets.generate_tid(7);

			// If the Razorpay Gateway is exist with active
			if (paymentGatewayMaster.getGateway().getGatewayname().equals("RAZORPAY")) {

				// Prefix adding for order receipt Id
				receipt = "rcpt_" + Assets.generate_tid(15);

				OrderEntity orderEntity = new OrderEntity();
				try {
					// Initialize client
					RazorpayClient razorpay = new RazorpayClient(paymentGatewayMaster.getApikey(),
							paymentGatewayMaster.getSalt());// ApiKey & Secret

					JSONObject orderRequest = new JSONObject();
					orderRequest.put("amount", obj.getAmount()); // Amount in the smallest currency unit (Note: Amount

//					Map headers=new HashMap();
//					headers.put("X-Razorpay-Account","Gka9LdxBp4Jp4e");
//					razorpay.addHeaders(headers);

					// should already multiple with 100)
					orderRequest.put("currency", "INR");
					orderRequest.put("receipt", receipt);

					JSONObject notes = new JSONObject();
					notes.put("name", name);
					notes.put("contact", contact);
					notes.put("email", email);
					notes.put("remark", obj.getRemark());
					orderRequest.put("notes", notes);

					JSONObject payment = new JSONObject();
					payment.put("capture", "automatic");
					JSONObject capture_options = new JSONObject();
					capture_options.put("automatic_expiry_period", 12);
					capture_options.put("manual_expiry_period", 7200);
					capture_options.put("refund_speed", "optimum");
					payment.put("capture_options", capture_options);
					orderRequest.put("payment", payment);

					// Order Creating
					Order order = razorpay.orders.create(orderRequest);

					// Order response converting into JsonObject
					JSONObject json = order.toJson();

					System.out.println(json);

					orderEntity = new OrderEntity(paymentGatewayMaster.getApikey(), json.getString("id"),
							json.getString("entity"), json.getInt("amount"), json.getInt("amount_paid"),
							json.getInt("amount_due"), json.getString("currency"), json.getString("receipt"),
							json.getString("status"), json.getInt("attempts"), json.getJSONObject("notes").toMap(),
							json.getLong("created_at"));

				} catch (RazorpayException e) {
					System.out.println(e.getMessage());
					return ResponseEntity.badRequest().body(new MessageResponse("Error with payment"));
				}

				int paymentTypeId = 4;// 4 - for online payment type

				// Converting unix_timestamp TO date_time
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = new Date(orderEntity.getCreated_at() * 1000);
				String tstarttime = dateFormat.format(date);

				// Putting initial log for start the payment
				Online_payment_log onlinePaymentLog = new Online_payment_log(paymentGatewayMaster.getId(),
						orderEntity.getId(), orderEntity.getReceipt(), orderEntity.getStatus(),
						String.valueOf(orderEntity.getAmount() / 100), null, null, obj.getRole(), obj.getUsername(),
						paymentTypeId, tstarttime, null, null, null, obj.getRemark(), null, null, 2,
						paymentGatewayMaster.getMode());
				onlinePaymentLogRepository.save(onlinePaymentLog);

				// Return response
				return ResponseEntity.ok(orderEntity);

			} else {
				return ResponseEntity.badRequest().body(new MessageResponse("The payment service is not unavailable"));
			}
		} else {
			return ResponseEntity.badRequest().body(new MessageResponse("Error with your info"));
		}
	}

	@Override
	public ResponseEntity<?> paymentResponseSuccess(Map<String, Object> obj) {

		final String razorpay_payment_id = obj.get("razorpay_payment_id").toString();
		final String razorpay_order_id = obj.get("razorpay_order_id").toString();
		final String razorpay_signature = obj.get("razorpay_signature").toString();

//		final PaymentGatewayMaster paymentGatewayMaster = paymentGatewayMasterRepository.findActivePaymentGatewayInfo();
		PaymentGatewayMaster paymentGatewayMaster = paymentGatewayMasterRepository.findByIsactive(true);

		final Online_payment_log onlinePaymentLog = onlinePaymentLogRepository.findByOrderid(razorpay_order_id);

		final String username = onlinePaymentLog.getUsername();
		final String role = onlinePaymentLog.getRole();

		final String creationdate = Dateformat.getCurrentDatetime();

		try {
			final String generated_signature = Signature.calculateRFC2104HMAC(
					onlinePaymentLog.getOrderid() + "|" + razorpay_payment_id, paymentGatewayMaster.getSalt());

			if (generated_signature.equals(razorpay_signature)) {

				onlinePaymentLogRepository.updatePaymentResponse("Success", "Payment is successful", null,
						razorpay_signature, creationdate, razorpay_payment_id, razorpay_order_id);

//				Operators operator = new Operators();
				if (role.equalsIgnoreCase("OPERATOR")) {

					Operators operator = operatorsRepository.findByUsername(username);

					final double newbalance = DoubleRounder.round((Double.parseDouble(operator.getWalletbalance())
							+ Double.valueOf(onlinePaymentLog.getAmount())), 2);

					Walletupdatelog wul = new Walletupdatelog(role, username, operator.getWalletbalance(),
							onlinePaymentLog.getAmount(), String.valueOf(newbalance),
							onlinePaymentLog.getTransactionid(), 1, 1, role, username, creationdate);

					walletupdatelogRepository.save(wul);

					operatorsRepository.walletBalanceCredit(operator.getId(),
							Double.valueOf(onlinePaymentLog.getAmount()));

				} else if (role.equalsIgnoreCase("USER")) {

					Userinfo userinfo = userinfoRepository.findByUsername(username);

					final double newbalance = DoubleRounder.round((Double.parseDouble(userinfo.getWalletbalance())
							+ Double.valueOf(onlinePaymentLog.getAmount())), 2);

					Walletupdatelog wul = new Walletupdatelog(role, username, userinfo.getWalletbalance(),
							onlinePaymentLog.getAmount(), String.valueOf(newbalance),
							onlinePaymentLog.getTransactionid(), 1, 3, role, username, creationdate);

					walletupdatelogRepository.save(wul);

					userinfoRepository.walletBalanceCredit(userinfo.getId(),
							Double.valueOf(onlinePaymentLog.getAmount()));
				}

			} else {
				onlinePaymentLogRepository.updatePaymentResponse("Error", "The signature is not matching",
						"Error with payment completion", razorpay_signature, creationdate, razorpay_payment_id,
						razorpay_order_id);
			}

			// Return response
			return ResponseEntity.ok(new MessageResponse("Your wallet recharge is successfully done!"));
		} catch (Exception e) {
			onlinePaymentLogRepository.updatePaymentResponse("Error", "Signature generate error",
					"Error with payment completion", razorpay_signature, creationdate, razorpay_payment_id,
					razorpay_order_id);
			return ResponseEntity.badRequest().body(new MessageResponse("Error with payment completion"));
		}
	}

	@Override
	public ResponseEntity<?> paymentResponseError(Map<String, Object> obj) {

		System.out.println(obj.toString());

		@SuppressWarnings("unchecked")
		final Map<String, Object> metadata = (Map<String, Object>) obj.get("metadata");

		final String description = obj.get("description").toString();
		final String reason = obj.get("reason").toString();
		final String payment_id = metadata.get("payment_id").toString();

		if (metadata.get("order_id") != null) {

			final String order_id = metadata.get("order_id").toString();

			onlinePaymentLogRepository.updatePaymentResponse("Error", reason, description, null,
					Dateformat.getCurrentDatetime(), payment_id, order_id);

			return ResponseEntity.ok(new MessageResponse("Your wallet recharge is successfully done!"));
		}
		return ResponseEntity.badRequest().body(new MessageResponse(description));
	}

	@Override
	public ResponseEntity<?> planPaymentOrderCreation(PaymentRequest obj) {
//		System.out.println(obj.get("username") + obj);
		int planid = (obj.getPlanid());
		// <Validation start
		if ((obj.getUsername() == "" || obj.getRole() == "" || obj.getRemark() == "")
				|| (obj.getUsername() == null || obj.getRole() == null || obj.getRemark() == null)) {
			return ResponseEntity.badRequest().body(new MessageResponse("Username, role or remark is invalid"));
		}

		if (planid < 1) {
			return ResponseEntity.badRequest().body(new MessageResponse("Please select a plan"));
		}
		if (!userbillinfoRepository.existsByUsername(obj.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("This customer is not verified!."));
		}

		if (!userinfoRepository.existsByUsername(obj.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("This customer is not authorized!.."));
		}

		if (!quotasubplanRepository.existsById(planid)) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Your selected plan is currently not available!."));
		}

		if (paymentGatewayMasterRepository.checkPaymentGatewayStatus() != 1) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("The payment service is temporarily unavailable"));
		}
		// Validation end>

		String name = "";
		String contact = "";
		String email = "";
		Quotasubplan subobj = new Quotasubplan();
		Quotaplanlcoprices priceobj = new Quotaplanlcoprices();
		Userinfo userinfo = new Userinfo();
		Operators opobj = new Operators();
		Userbillinfo userbillobj = new Userbillinfo();
		Online_plan_payment planpaymentobj = new Online_plan_payment();

		if (obj.getRole().equalsIgnoreCase("OPERATOR")) {
			opobj = operatorsRepository.findByUsername(obj.getUsername());
//			userinfo = userinfoRepository.findByUsername(obj.getUsername());
			userbillobj = userbillinfoRepository.findByUsername(userinfo.getUsername());
			planpaymentobj.setCreationbyusername(opobj.getUsername());
			planpaymentobj.setUsername(userinfo.getUsername());

			name = opobj.getFirstname() + " " + opobj.getLastname();
			contact = opobj.getPhone1();
			email = opobj.getEmail1();

		} else if (obj.getRole().equalsIgnoreCase("USER")) {

			userinfo = userinfoRepository.findByUsername(obj.getUsername());
			opobj = operatorsRepository.findById(userinfo.getOpid());
			userbillobj = userbillinfoRepository.findByUsername(userinfo.getUsername());
			planpaymentobj.setCreationbyusername(userinfo.getUsername());
			planpaymentobj.setUsername(userinfo.getUsername());

			name = userinfo.getFirstname() + " " + userinfo.getLastname();
			contact = userinfo.getMobilephone();
			email = userinfo.getEmail();
		}

		subobj = quotasubplanRepository.findById(planid);

		if (userbillobj.getQuotaexpirydate() != null) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date preexdate = sdf.parse(userbillobj.getQuotaexpirydate());
				Date curdate = sdf.parse(Dateformat.getCurrentDatetime());

				if (preexdate.after(curdate)) {
//					if (userbillobj.getPlanid() != planid) {
//						System.out.println("Your current plan is not Expired! please select same plan for Extend!");
//						return ResponseEntity.badRequest().body(new MessageResponse(
//								"Your current plan is not Expired! please select same plan for Extend!"));
//					}
					planpaymentobj.setQuotaexpirydate(LocalDate.parse(userbillobj.getQuotaexpirydate().substring(0, 10))
							.plusDays(Integer.parseInt(subobj.getPlantimebank())).toString() + " 23:59:59");

				} else if (preexdate.before(curdate)) {
					planpaymentobj
							.setQuotaexpirydate(Dateformat.expiryDate(Integer.parseInt(subobj.getPlantimebank())));
				} else {
					planpaymentobj
							.setQuotaexpirydate(Dateformat.expiryDate(Integer.parseInt(subobj.getPlantimebank())));
				}
			} catch (Exception e) {
				System.out.println(e);
			} finally {

			}
		}
		System.out.println(planpaymentobj.getQuotaexpirydate() + " " + planpaymentobj.getUsername());

		System.out.println(subobj.getSubplan());

		priceobj = quotaplanlcopricesRepository.findBySubplanidAndOpid(planid, opobj.getId());

		if (priceobj == null) {
			priceobj = new Quotaplanlcoprices();
			priceobj = quotaplanlcopricesRepository.findBySubplanidAndOpid(planid, 0);

		}
		if (!contact.equals("")) {
			// Find active payment gateway info
//			final PaymentGatewayMaster paymentGatewayMaster = paymentGatewayMasterRepository
//					.findByGatewayname("RAZORPAY");

			PaymentGatewayMaster paymentGatewayMaster = paymentGatewayMasterRepository.findByIsactive(true);

			// Receipt generation
			String receipt = Assets.generate_tid(7);

			// If the Razorpay Gateway is exist with active
			if (paymentGatewayMaster.getGateway().getGatewayname().equals("RAZORPAY")) {

				// Prefix adding for order receipt Id
				receipt = "rzpdp_" + Assets.generate_tid(15);

				OrderEntity orderEntity = new OrderEntity();

				// online_plan_payment log
				planpaymentobj.setCreationby(obj.getRole());
				planpaymentobj.setTransactionid(receipt);
				planpaymentobj.setPlanid(planid);
				planpaymentobj.setAmount(priceobj.getPlancuscost());
				planpaymentobj.setStatus("created");
				planpaymentobj.setCreationdate(Dateformat.getCurrentDatetime());

				online_plan_paymentRepository.save(planpaymentobj);

				try {
					// Initialize client
					RazorpayClient razorpay = new RazorpayClient(paymentGatewayMaster.getApikey(),
							paymentGatewayMaster.getSalt());// ApiKey & Secret

//					BigDecimal b = new BigDecimal(priceobj.getPlancuscost());
					BigDecimal b = new BigDecimal("1");
					BigDecimal value = b.multiply(new BigDecimal("100"));
					String amountInPaise = value.setScale(0, RoundingMode.UP).toString();

					JSONObject orderRequest = new JSONObject();
					orderRequest.put("amount", amountInPaise); // Amount in the smallest currency unit (Note: Amount
																// should already multiple with 100)
					orderRequest.put("currency", "INR");
					orderRequest.put("receipt", receipt);

					JSONObject notes = new JSONObject();
					notes.put("name", name);
					notes.put("contact", contact);
					notes.put("email", email);
					notes.put("remark", obj.getRemark());
					orderRequest.put("notes", notes);

					JSONObject payment = new JSONObject();
					payment.put("capture", "automatic");
					JSONObject capture_options = new JSONObject();
					capture_options.put("automatic_expiry_period", 12);
					capture_options.put("manual_expiry_period", 7200);
					capture_options.put("refund_speed", "optimum");
					payment.put("capture_options", capture_options);
					orderRequest.put("payment", payment);

					// Order Creating
					Order order = razorpay.orders.create(orderRequest);

					// Order response converting into JsonObject
					JSONObject json = order.toJson();

					System.out.println(json);

					orderEntity = new OrderEntity(paymentGatewayMaster.getApikey(), json.getString("id"),
							json.getString("entity"), json.getInt("amount"), json.getInt("amount_paid"),
							json.getInt("amount_due"), json.getString("currency"), json.getString("receipt"),
							json.getString("status"), json.getInt("attempts"), json.getJSONObject("notes").toMap(),
							json.getLong("created_at"));

				} catch (RazorpayException e) {
					System.out.println(e.getMessage());
					return ResponseEntity.badRequest().body(new MessageResponse("Error with payment"));
				}

				int paymentTypeId = 4;// 4 - for online payment type

				// Converting unix_timestamp TO date_time
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = new Date(orderEntity.getCreated_at() * 1000);
				String tstarttime = dateFormat.format(date);

				// Putting initial log for start the payment
				Online_payment_log onlinePaymentLog = new Online_payment_log(paymentGatewayMaster.getId(),
						orderEntity.getId(), orderEntity.getReceipt(), orderEntity.getStatus(),
						String.valueOf(orderEntity.getAmount() / 100), null, null, obj.getRole(), obj.getUsername(),
						paymentTypeId, tstarttime, null, null, null, obj.getRemark(), null, null, 2,
						paymentGatewayMaster.getMode());
				onlinePaymentLogRepository.save(onlinePaymentLog);

				// Return response
				return ResponseEntity.ok(orderEntity);

			} else {
				return ResponseEntity.badRequest().body(new MessageResponse("The payment service is not unavailable"));
			}
		} else {
			return ResponseEntity.badRequest().body(new MessageResponse("Error with your info"));
		}

	}

	@Override
	public ResponseEntity<?> planPaymenSuccess(Map<String, String> obj) {

		System.out.println(obj.toString());
		final String razorpay_payment_id = obj.get("razorpay_payment_id").toString();
		final String razorpay_order_id = obj.get("razorpay_order_id").toString();
		final String razorpay_signature = obj.get("razorpay_signature").toString();

//		final PaymentGatewayMaster paymentGatewayMaster = paymentGatewayMasterRepository.findActivePaymentGatewayInfo();

		PaymentGatewayMaster paymentGatewayMaster = paymentGatewayMasterRepository.findByIsactive(true);

		final Online_payment_log onlinePaymentLog = onlinePaymentLogRepository.findByOrderid(razorpay_order_id);

		final Online_plan_payment planpaymentlog = online_plan_paymentRepository
				.findByTransactionid(onlinePaymentLog.getTransactionid());

		final String username = onlinePaymentLog.getUsername();
		final String role = onlinePaymentLog.getRole();

		final String creationdate = Dateformat.getCurrentDatetime();

		try {
			final String generated_signature = Signature.calculateRFC2104HMAC(
					onlinePaymentLog.getOrderid() + "|" + razorpay_payment_id, paymentGatewayMaster.getSalt());

			if (generated_signature.equals(razorpay_signature)) {
				System.out.println(generated_signature + " " + razorpay_signature);

				onlinePaymentLogRepository.updatePaymentResponse("Success", "Payment is successful", null,
						razorpay_signature, creationdate, razorpay_payment_id, razorpay_order_id);
				planpaymentlog.setStatus("completed");
				planpaymentlog.setResponse("Payment is successful");
				planpaymentlog.setUpdateddate(Dateformat.getCurrentDatetime());
				online_plan_paymentRepository.save(planpaymentlog);

				try {
					QuatoplanactivationRequest planactiveobj = new QuatoplanactivationRequest();
					planactiveobj.setCus_username(onlinePaymentLog.getUsername());
					planactiveobj.setCreationbyusername(planpaymentlog.getCreationbyusername());
					planactiveobj.setCreationby(planpaymentlog.getCreationby());
					planactiveobj.setPlanid(String.valueOf(planpaymentlog.getPlanid()));
					planactiveobj.setQuotadays("0");
					planactiveobj.setPaymentType("online");

					quotaplanserviceimpl.quotaPlanActive2(planactiveobj);
				} catch (Exception e) {
					return ResponseEntity.badRequest().body(new MessageResponse("Error with plan Activation!"));
				} finally {

				}
				return ResponseEntity.ok(new MessageResponse("Plan activated!"));
			} else {
				System.out.println("signature not equal");
				onlinePaymentLogRepository.updatePaymentResponse("Error", "The signature is not matching",
						"Error with payment completion", razorpay_signature, creationdate, razorpay_payment_id,
						razorpay_order_id);

				planpaymentlog.setStatus("Failed");
				planpaymentlog.setResponse("signature is not matching");
				planpaymentlog.setUpdateddate(Dateformat.getCurrentDatetime());
				online_plan_paymentRepository.save(planpaymentlog);
				return ResponseEntity.ok(new MessageResponse("Error with payment singnature is not matching!"));
			}

			// Return response

		} catch (Exception e) {
			System.out.println("exception occur");
			onlinePaymentLogRepository.updatePaymentResponse("Error", "Signature generate error",
					"Error with payment completion", razorpay_signature, creationdate, razorpay_payment_id,
					razorpay_order_id);
			return ResponseEntity.badRequest().body(new MessageResponse("Error with payment completion!"));
		}
	}

	@Override
	public ResponseEntity<?> planPaymenFailed(Map<String, Object> obj) {

		System.out.println(obj.toString());

		@SuppressWarnings("unchecked")
		final Map<String, Object> metadata = (Map<String, Object>) obj.get("metadata");

		final String description = obj.get("description").toString();
		final String reason = obj.get("reason").toString();
		final String payment_id = metadata.get("payment_id").toString();

		if (metadata.get("order_id") != null) {

			final String order_id = metadata.get("order_id").toString();

			Online_payment_log onlinePaymentLog = onlinePaymentLogRepository.findByOrderid(order_id);

			Online_plan_payment planpaymentlog = online_plan_paymentRepository
					.findByTransactionid(onlinePaymentLog.getTransactionid());

			onlinePaymentLogRepository.updatePaymentResponse("Error", reason, description, null,
					Dateformat.getCurrentDatetime(), payment_id, order_id);

			planpaymentlog.setStatus("Failed");
			planpaymentlog.setResponse(reason);
			planpaymentlog.setUpdateddate(Dateformat.getCurrentDatetime());
			online_plan_paymentRepository.save(planpaymentlog);

			return ResponseEntity.ok(new MessageResponse("Payment Failed!"));
		}
		return ResponseEntity.badRequest().body(new MessageResponse(description));
	}

	@Override
	public ResponseEntity<?> getAggrePaymentStatus(String orderid, String type) {

		Online_payment_log opl = onlinePaymentLogRepository.findByOrderid(orderid);
		PaymentGatewayMaster payobj = paymentGatewayMasterRepository.findById(opl.getGatewayid());

		if (payobj.getGateway().getGatewayname().equalsIgnoreCase("AGGREPAY")) {
			return aggreepayStatus(orderid, type, payobj);
		} else if (payobj.getGateway().getGatewayname().equalsIgnoreCase("EBUZZ")) {
			return easebuzzStatus(orderid, type, payobj, opl);
		}

		return null;

	}

	public ResponseEntity<?> easebuzzStatus(String orderid, String type, PaymentGatewayMaster payobj,
			Online_payment_log opl) {

		String key = payobj.getApikey();
		String txnid = orderid;
		String amount = String.valueOf(Double.parseDouble(opl.getAmount()));
		String email = "";
		String phone = "";
		String salt = payobj.getSalt();

		if (opl.getRole().equalsIgnoreCase("OPERATOR")) {
			Operators op = operatorsRepository.findByUsername(opl.getUsername());

			phone = op.getPhone1();
			email = op.getEmail1();
		} else if (opl.getRole().equalsIgnoreCase("USER")) {

			Userinfo user = userinfoRepository.findByUsername(opl.getUsername());

			phone = user.getMobilephone();
			email = user.getEmail();
		}

		String hastext = key.trim() + "|" + txnid.trim() + "|" + amount.trim() + "|" + email.trim() + "|" + phone.trim()
				+ "|" + salt.trim();
		String hash = OnlinePaymentAssets.encryptThisString(hastext);

		System.out.println(hash);

		String response = OnlinePaymentAssets.easebuzzRefreshCalling(txnid, key, amount, email, hash, phone,
				opl.getMode());

		if (response != null && !response.equals("")) {
			JSONObject json_data = new JSONObject(response);

			System.out.println(json_data);

			if (Boolean.parseBoolean(json_data.get("status").toString())) {
				JSONObject msg = (JSONObject) json_data.get("msg");
				System.out.println(json_data);

				if (msg.get("status").toString().equalsIgnoreCase("success")) {
					updateOnlinePaymentBothPlanAndWallet(opl.getOrderid(), msg.get("easepayid").toString(),
							"Transaction successful", "", "0", "", type, hash, "Completed");
					return ResponseEntity.ok(new MessageResponse("Updated!"));
				} else {
					String sts = "Completed";
					String resmsg = "Transaction Failed";
					if (msg.get("status").toString().equalsIgnoreCase("Pending")) {
						sts = "pending";
						resmsg = "Transaction Pending";
					}
					updateOnlinePaymentBothPlanAndWallet(opl.getOrderid(), msg.get("easepayid").toString(), resmsg, "",
							"1000", msg.get("status").toString(), type, hash, sts);
					return ResponseEntity.ok(new MessageResponse("Updated"));
				}
			} else {
				return ResponseEntity.badRequest().body(new MessageResponse(json_data.get("msg").toString()));
			}
		}

		return ResponseEntity.ok(new MessageResponse("Updated!"));
	}

	public ResponseEntity<?> aggreepayStatus(String orderid, String type, PaymentGatewayMaster payobj) {
		PaymentStatus paymentstatus = new PaymentStatus();

		try {

			HttpClient httpclient = paymentstatus.getNewHttpClient();
			System.out.println("Transaction :" + orderid + " LCO id:");

			HttpPost httppost = new HttpPost("https://biz.aggrepaypayments.com/v2/paymentstatus");
			String origresponseText = "";
			try {
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

				String salt = payobj.getSalt();// "e0f315cbbff49b1510099db8ac9e0329b1c07c51";

				String hash_data = salt;

				String[] hash_columns = { "api_key", "order_id" };
				System.out.println("14" + payobj.getApikey() + " " + orderid);
				String[] hash_columns_data = { payobj.getApikey().trim(), orderid };

				for (int i = 0; i < hash_columns.length; i++) {

					if (hash_columns_data[i].length() > 0) {
						hash_data += '|' + hash_columns_data[i];
					}

				}

				String hash = "";
				try {

					hash = paymentstatus.getHashCodeFromString(hash_data);
				} catch (NoSuchAlgorithmException e) {
					System.out.println("bye code change:" + e.getMessage());
				}

				nameValuePairs.add(new BasicNameValuePair("hash", hash));
				nameValuePairs.add(new BasicNameValuePair("api_key", payobj.getApikey()));
				nameValuePairs.add(new BasicNameValuePair("order_id", orderid));
				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

				HttpResponse response = httpclient.execute(httppost);

				// Exception "Peer Not Authenticated" solved
				origresponseText = paymentstatus.readContent(response);

//	                System.out.println("RESPONSE:" + origresponseText);
				origresponseText = origresponseText.replace(",", "&");

				String[] Str1 = origresponseText.split("&");
				String response_code = "";
				String response_message = "";
				String transactionid = "";

				for (int i = 0; i < Str1.length; i++) {
					System.out.println("\n" + Str1[i]);

					String st2 = Str1[i];
					String[] St3 = st2.split(":");
					for (int j = 0; j < St3.length; j++) {
						if ((St3[j].contains("response_code") || St3[j].contains("code"))
								&& !St3[j].contains("bcank_code")) {

							response_code = St3[++j];
						} else if (St3[j].contains("response_message") || St3[j].contains("message")) {

							response_message = St3[++j];
						} else if (St3[j].contains("transaction_id")) {

							transactionid = St3[++j];
						}

					}
				}
				System.out.println("response_message: " + response_message + "\n   " + response_code + "\n  " + orderid
						+ "\n   " + origresponseText + " \n  " + transactionid);

				if (response_code.equals("0")) {
					updateOnlinePaymentBothPlanAndWallet(orderid, transactionid, "Transaction successful",
							origresponseText, response_code, "", type, "", "Completed");
					return ResponseEntity.ok(new MessageResponse("Updated!"));
				} else if (response_code.equals("1000")) {
					updateOnlinePaymentBothPlanAndWallet(orderid, transactionid, "Transaction Failed", origresponseText,
							response_code, "Transaction Failed", type, "", "Completed");
					return ResponseEntity.ok(new MessageResponse("Updated"));
				} else {
					System.out.println(response_message.replace("\"", "").replace("}}", ""));
					return ResponseEntity.badRequest()
							.body(new MessageResponse(response_message.replace("\"", "").replace("}}", "")));
				}

			} catch (IOException e) {
				System.out.println("exception pay123:" + e.getMessage());
				return ResponseEntity.badRequest().body(new MessageResponse("Something went wrong!"));
			}
		} catch (Exception e) {

			System.out.println("exception pay678:" + e.getMessage());
			return ResponseEntity.badRequest().body(new MessageResponse("Something went wrong!"));
		}
	}

	public void updateOnlinePayment(String orderid, String transactionid, String resmsg, String responsedata,
			String rescode, String errormsg) {

		try {
			Online_payment_log object = new Online_payment_log();

			object = onlinePaymentLogRepository.findByOrderid(orderid);

			object.setOrderid(orderid);
			object.setTransactionid(transactionid.replace("\"", ""));
			object.setStatus("Completed");
			object.setResmsg(resmsg);
			object.setErrormsg(errormsg);
			object.setResponse(responsedata);
			object.setTendtime(Dateformat.getCurrentDatetime());

			onlinePaymentLogRepository.save(object);
			int response_code = Integer.parseInt(rescode);
			if (response_code == 0) {

				Walletupdatelog wulobj = new Walletupdatelog();
				wulobj.setRole(object.getRole());
				wulobj.setUsername(object.getUsername());
				wulobj.setAmount(object.getAmount());
				wulobj.setReferenceid(object.getTransactionid().replace("\"", ""));
				wulobj.setRechargetype(1);
				wulobj.setPaymentbyrole(object.getRole());// Who recharged
				wulobj.setPaymentbyusername(object.getUsername());
				wulobj.setCreationdate(Dateformat.getCurrentDatetime());

				String oldbalance = "0";
				String newbalance = "0";

				Operators opobj = new Operators();
				Userinfo userobj = new Userinfo();
				if (object.getRole().equalsIgnoreCase("OPERATOR")) {
					opobj = operatorsRepository.findByUsername(object.getUsername());

					System.out.println();
					oldbalance = opobj.getWalletbalance();
					newbalance = String
							.valueOf(Double.parseDouble(oldbalance) + Double.parseDouble(object.getAmount()));

					wulobj.setOldbalance(oldbalance);
					wulobj.setNewbalance(newbalance);
					wulobj.setWalletupdatetypeid(1);

					opobj.setWalletbalance(newbalance);
					operatorsRepository.save(opobj);

				} else if (object.getRole().equalsIgnoreCase("USER")) {
					userobj = userinfoRepository.findByUsername(object.getUsername());
					oldbalance = userobj.getWalletbalance();
					newbalance = String
							.valueOf(Double.parseDouble(oldbalance) + Double.parseDouble(object.getAmount()));
					wulobj.setOldbalance(oldbalance);
					wulobj.setNewbalance(newbalance);
					wulobj.setWalletupdatetypeid(3);

					userobj.setWalletbalance(oldbalance + newbalance);
					userinfoRepository.save(userobj);

				}

				walletupdatelogRepository.save(wulobj);
			}
		} catch (Exception e) {
			System.out.println("Exception at refresh method : " + e);

		}

	}

	public void updateOnlinePaymentBothPlanAndWallet(String orderid, String transactionid, String resmsg,
			String responsedata, String rescode, String errormsg, String type, String hash, String status) {

		try {
			Online_payment_log object = new Online_payment_log();

			object = onlinePaymentLogRepository.findByOrderid(orderid);

			if (!object.getStatus().equals("Completed")) {

				object.setOrderid(orderid);
				object.setTransactionid(transactionid.replace("\"", ""));
				object.setStatus(status);
				object.setResmsg(resmsg);
				object.setErrormsg(errormsg);
				object.setResponse(responsedata);
				object.setTendtime(Dateformat.getCurrentDatetime());

				if (hash != null && !hash.equals("")) {
					object.setExtra2(hash);
				}

				onlinePaymentLogRepository.save(object);
				int response_code = Integer.parseInt(rescode);
				if (response_code == 0) {

					if (type.equals("wallet")) {
						Walletupdatelog wulobj = new Walletupdatelog();
						wulobj.setRole(object.getRole());
						wulobj.setUsername(object.getUsername());
						wulobj.setAmount(object.getAmount());
						wulobj.setReferenceid(object.getTransactionid().replace("\"", ""));
						wulobj.setRechargetype(1);
						wulobj.setPaymentbyrole(object.getRole());// Who recharged
						wulobj.setPaymentbyusername(object.getUsername());
						wulobj.setCreationdate(Dateformat.getCurrentDatetime());

						String oldbalance = "0";
						String newbalance = "0";

						Operators opobj = new Operators();
						Userinfo userobj = new Userinfo();
						if (object.getRole().equalsIgnoreCase("OPERATOR")) {
							opobj = operatorsRepository.findByUsername(object.getUsername());

							System.out.println();
							oldbalance = opobj.getWalletbalance();
							newbalance = String
									.valueOf(Double.parseDouble(oldbalance) + Double.parseDouble(object.getAmount()));

							wulobj.setOldbalance(oldbalance);
							wulobj.setNewbalance(newbalance);
							wulobj.setWalletupdatetypeid(1);

							opobj.setWalletbalance(newbalance);
							operatorsRepository.save(opobj);

						} else if (object.getRole().equalsIgnoreCase("USER")) {
							userobj = userinfoRepository.findByUsername(object.getUsername());
							oldbalance = userobj.getWalletbalance();
							newbalance = String
									.valueOf(Double.parseDouble(oldbalance) + Double.parseDouble(object.getAmount()));
							wulobj.setOldbalance(oldbalance);
							wulobj.setNewbalance(newbalance);
							wulobj.setWalletupdatetypeid(3);

							userobj.setWalletbalance(oldbalance + newbalance);
							userinfoRepository.save(userobj);

						}

						walletupdatelogRepository.save(wulobj);
					} else if (type.equals("plan")) {

						Online_plan_payment Onlineplanobj = online_plan_paymentRepository
								.findByTransactionid(object.getOrderid());

						if (Onlineplanobj != null) {

							Onlineplanobj.setStatus("completed");
							Onlineplanobj.setResponse("Payment is successful");
							Onlineplanobj.setUpdateddate(Dateformat.getCurrentDatetime());

							online_plan_paymentRepository.save(Onlineplanobj);

							QuatoplanactivationRequest reqobj = new QuatoplanactivationRequest();
							reqobj.setCus_username(Onlineplanobj.getUsername());
							reqobj.setPlanid(String.valueOf(Onlineplanobj.getPlanid()));
							reqobj.setTransactionid(Onlineplanobj.getTransactionid());
							reqobj.setQuotadays("0");
							reqobj.setPaymentType("online");
							reqobj.setCreationby(Onlineplanobj.getCreationby());
							reqobj.setCreationbyusername(Onlineplanobj.getCreationbyusername());
							reqobj.setExpirydate(Onlineplanobj.getQuotaexpirydate());
							reqobj.setStartdate(Onlineplanobj.getCreationdate());

							quotaplanservicimp.quotaPlanActive2(reqobj);

						}

					}
				}
			}
		} catch (Exception e) {
			System.out.println("Exception at refresh method : " + e);

		}

	}

	@Override
	public ResponseEntity<?> operatorPaymentRequest1(PaymentRequest requestbody) {

//		PaymentGatewayMaster PaymentGatewayMaster = paymentGatewayMasterRepository.findByGatewayname("AGGREPAY");
		PaymentGatewayMaster PaymentGatewayMaster = paymentGatewayMasterRepository.findByIsactive(true);

		if (PaymentGatewayMaster.getSalt() != null && PaymentGatewayMaster.getApikey() != null) {

			final String RETURN_URL = requestbody.getReturnurl();// "http://localhost:8080/CloudPayment/agreepaypaymentresponse.do";
			String AMOUNT = requestbody.getAmount();
			final String USERNAME = requestbody.getUsername();
			final String ORDER_ID = "WOP" + OnlinePaymentAssets.generateOrderID();
			final String SALT = PaymentGatewayMaster.getSalt();
			final String API_KEY = PaymentGatewayMaster.getApikey();
			final String LOCATER_URL = requestbody.getLocaterurl();

			final String CITY = "Pondicherry";
			final String STATE = "Pondicherry";
			final String COUNTRY = "india";
			final String CURRENCY = "INR";
			final String DESCRIPTION = "Payment by username:" + USERNAME;
			final String MODE = PaymentGatewayMaster.getMode();
			final String ZIP_CODE = "605001";

			String EMAIL = "";
			String NAME = "";
			String PHONE = "";
			String ADDRESS_1 = "";
			String ADDRESS_2 = "";

			if (requestbody.getRole().equalsIgnoreCase("OPERATOR")) {
				Operators object = operatorsRepository.findByUsername(USERNAME);
				EMAIL = object.getEmail1();
				NAME = object.getFirstname() + " " + object.getLastname();
				PHONE = object.getPhone1();
				ADDRESS_1 = object.getCompany() + "," + object.getDepartment();
				ADDRESS_2 = object.getCompany() + "," + object.getDepartment();

			} else if (requestbody.getRole().equalsIgnoreCase("USER")) {
				Userinfo object = userinfoRepository.findByUsername(USERNAME);
				EMAIL = object.getEmail();
				NAME = object.getFirstname() + " " + object.getLastname();
				PHONE = object.getMobilephone();
				ADDRESS_1 = object.getOpid() + "_" + object.getCity();
				ADDRESS_2 = object.getCity();
			}

			Online_payment_log opl = new Online_payment_log();
			opl.setOrderid(ORDER_ID);
			opl.setStatus("Initiated");
			opl.setGatewayid(PaymentGatewayMaster.getId());
			opl.setRole(requestbody.getRole());
			opl.setUsername(USERNAME);
			opl.setPaymenttypeid(4);
			opl.setAmount(AMOUNT);
			opl.setTstarttime(Dateformat.getCurrentDatetime());
			opl.setMode(PaymentGatewayMaster.getMode());

			String status = PaymentSwitch(requestbody.getTitle(), requestbody.getPlanid(), requestbody.getRemark(),
					opl);

			if (status.equalsIgnoreCase("success")) {

				Online_plan_payment planobj = online_plan_paymentRepository.findByTransactionid(ORDER_ID);

				if (planobj == null) {

				} else {
					AMOUNT = planobj.getAmount();
				}

			} else {
				return ResponseEntity.badRequest().body(new MessageResponse(status));
			}
//			PHONE="6380301519";

//		Syntax of hash data formation
//		address_line_1|address_line_2|amount|api_key|city|country|currency|description|email|mode|name|order_id|phone|return_url|state|zip_code		
			String HASH = SALT + "|" + ADDRESS_1.trim() + "|" + ADDRESS_2.trim() + "|" + AMOUNT + "|" + API_KEY + "|"
					+ CITY.trim() + "|" + COUNTRY.trim() + "|" + CURRENCY + "|" + DESCRIPTION + "|" + EMAIL.trim() + "|"
					+ MODE + "|" + NAME.trim() + "|" + ORDER_ID + "|" + PHONE.trim() + "|" + RETURN_URL + "|"
					+ STATE.trim() + "|" + ZIP_CODE;
			System.out.println(HASH);
			try {
				HASH = OnlinePaymentAssets.getHashCodeFromString(HASH);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
				return ResponseEntity.badRequest()
						.body(new MessageResponse("Error creating your payment hash generation"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return ResponseEntity.badRequest()
						.body(new MessageResponse("Error creating your payment hash generation"));
			}

			// Insert online payment initiate log

			String response = "" + "<html>" + "<body>" + "<form id=\"tnpform\" action=\"" + LOCATER_URL
					+ "\"  name=\"tnpform\" method=POST >" + "      <input type=\"hidden\" name=\"hash\" value=\""
					+ HASH + "\">" + "      <input type=\"hidden\" name=\"api_key\" value=\"" + API_KEY + "\">"
					+ "      <input type=\"hidden\" name=\"return_url\" value=\"" + RETURN_URL + "\">"
					+ "      <input type=\"hidden\" name=\"mode\" value=\"" + MODE + "\">"
					+ "      <input type=\"hidden\" name=\"order_id\" value=\"" + ORDER_ID + "\">"
					+ "      <input type=\"hidden\" name=\"amount\" value=\"" + AMOUNT + "\">"
					+ "      <input type=\"hidden\" name=\"currency\" value=\"" + CURRENCY + "\">"
					+ "      <input type=\"hidden\" name=\"description\" value=\"" + DESCRIPTION + "\">"
					+ "      <input type=\"hidden\" name=\"name\" value=\"" + NAME + "\">"
					+ "      <input type=\"hidden\" name=\"email\" value=\"" + EMAIL + "\">"
					+ "      <input type=\"hidden\" name=\"phone\" value=\"" + PHONE + "\">"
					+ "      <input type=\"hidden\" name=\"address_line_1\" value=\"" + ADDRESS_1 + "\">"
					+ "      <input type=\"hidden\" name=\"address_line_2\" value=\"" + ADDRESS_2 + "\">"
					+ "      <input type=\"hidden\" name=\"city\" value=\"" + CITY + "\">"
					+ "      <input type=\"hidden\" name=\"country\" value=\"" + COUNTRY + "\">"
					+ "      <input type=\"hidden\" name=\"state\" value=\"" + STATE + "\">"
					+ "      <input type=\"hidden\" name=\"zip_code\" value=\"" + ZIP_CODE + "\">" + "</form>"
					+ "<script>" + " document.getElementById(\"tnpform\").submit();" + "</script>" + "</body>"
					+ "</html>";

			System.out.print(response);
//		PaymentResponse PaymentResponse = new PaymentResponse(TEST_SALT, TEST_API_KEY, RETURN_URL, mode, ORDER_ID,
//				AMOUNT, currency, description, NAME, email, phone, address, address, address, country, address,
//				zip_code);
//		return ResponseEntity.ok(PaymentResponse);
			Map<String, String> map = new HashMap<>();
			map.put("order_id", ORDER_ID);
			map.put("response", response);
			map.put("surl", PaymentGatewayMaster.getSuccessurl());
			map.put("furl", PaymentGatewayMaster.getFaliureurl());
			map.put("baseurl", PaymentGatewayMaster.getBaseurl());

//
			return ResponseEntity.ok(map);

		} else {
			return ResponseEntity.badRequest().body(new MessageResponse("Error with payment"));
		}
	}

	public String PaymentSwitch(String title, int planid, String remark, Online_payment_log opl) {
		if (title.contains("plan")) {
			System.out.print(planid);

			String name = "";
			String contact = "";
			String email = "";

			System.out.println(planid);
			if (planid < 1) {
				return "Please select a plan";
			}
			if (!userbillinfoRepository.existsByUsername(opl.getUsername())) {
				return "This customer is not verified!.";
			}

			if (!userinfoRepository.existsByUsername(opl.getUsername())) {
				return "This customer is not authorized!..";
			}

			if (!quotasubplanRepository.existsById(planid)) {
				return "Your selected plan is currently not available!.";
			}

			if (paymentGatewayMasterRepository.checkPaymentGatewayStatus() != 1) {
				return "The payment service is temporarily unavailable";
			}

			Quotasubplan subobj = new Quotasubplan();
			Quotaplanlcoprices priceobj = new Quotaplanlcoprices();
			Userinfo userinfo = new Userinfo();
			Operators opobj = new Operators();
			Userbillinfo userbillobj = new Userbillinfo();
			Online_plan_payment planpaymentobj = new Online_plan_payment();

			if (opl.getRole().equalsIgnoreCase("USER")) {

				userinfo = userinfoRepository.findByUsername(opl.getUsername());
				opobj = operatorsRepository.findById(userinfo.getOpid());
				userbillobj = userbillinfoRepository.findByUsername(userinfo.getUsername());
				planpaymentobj.setCreationbyusername(userinfo.getUsername());
				planpaymentobj.setUsername(userinfo.getUsername());

				name = userinfo.getFirstname() + " " + userinfo.getLastname();
				contact = userinfo.getMobilephone();
				email = userinfo.getEmail();
			}

			if (duplicatesubplanRepository.existsBySubplanidAndOpid(planid, opobj.getId())) {

				Duplicatesubplan dupsubplan = new Duplicatesubplan();
				dupsubplan = duplicatesubplanRepository.findBySubplanidAndOpid(planid, opobj.getId());
				subobj.setPlantimebank(dupsubplan.getPlantimebank());
				subobj.setId(dupsubplan.getSubplanid());

			} else {
				subobj = quotasubplanRepository.findById(planid);
			}

			if (userbillobj.getQuotaexpirydate() != null) {

				try {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date preexdate = sdf.parse(userbillobj.getQuotaexpirydate());
					Date curdate = sdf.parse(Dateformat.getCurrentDatetime());

					if (preexdate.after(curdate)) {

						if (userbillobj.getPlanid() != planid) {

							return "Your current plan is not Expired! please select same plan for Extend!";
						}
						planpaymentobj
								.setQuotaexpirydate(LocalDate.parse(userbillobj.getQuotaexpirydate().substring(0, 10))
										.plusDays(Integer.parseInt(subobj.getPlantimebank())).toString() + " 23:59:59");

					} else if (preexdate.before(curdate)) {
						planpaymentobj
								.setQuotaexpirydate(Dateformat.expiryDate(Integer.parseInt(subobj.getPlantimebank())));
					} else {
						planpaymentobj
								.setQuotaexpirydate(Dateformat.expiryDate(Integer.parseInt(subobj.getPlantimebank())));
					}
				} catch (Exception e) {
					System.out.println(e);
				} finally {

				}
			}

			priceobj = quotaplanlcopricesRepository.findBySubplanidAndOpid(planid, opobj.getId());

			if (priceobj == null) {

				priceobj = new Quotaplanlcoprices();
				priceobj = quotaplanlcopricesRepository.findBySubplanidAndOpid(planid, 0);

			}

			opl.setRechargetypeid(2);
			opl.setExtra1(remark);
			opl.setAmount(priceobj.getPlancuscost());
			// saving into online_payment_log
			onlinePaymentLogRepository.save(opl);

			// online_plan_payment log
			planpaymentobj.setCreationby(opl.getRole());
			planpaymentobj.setTransactionid(opl.getOrderid());
			planpaymentobj.setPlanid(planid);
			planpaymentobj.setAmount(priceobj.getPlancuscost());
			planpaymentobj.setStatus("created");
			planpaymentobj.setReferenceid("");
			planpaymentobj.setCreationdate(Dateformat.getCurrentDatetime());

			online_plan_paymentRepository.save(planpaymentobj);

			return "Success";

		} else if (title.contains("wallet")) {

			opl.setRechargetypeid(1);
			opl.setAmount(opl.getAmount());
			System.out.println(opl.getAmount());
			// saving into online_payment_log
			onlinePaymentLogRepository.save(opl);

			return "Success";

		}
		return null;
	}

	@Override
	public List<OnlineRechargelistDTO> getOnlineUserRechargeHistory(String username, String fdate, String tdate,
			String role) {

		if (!fdate.equals("0000-00-00") && !tdate.equals("0000-00-00")) {

			if (role.equalsIgnoreCase("ADMIN")) {
				return onlinePaymentLogRepository.getOnlineUserRechargeHistoryAllFtdate(fdate, tdate);
			} else if (role.equalsIgnoreCase("OPERATOR")) {
				return onlinePaymentLogRepository.getOnlineUserRechargeHistoryByOpidFtdate(username, fdate, tdate);
			} else {
				return onlinePaymentLogRepository.getOnlineUserRechargeHistoryFtdate(username, fdate, tdate);

			}

		} else {
			if (role.equalsIgnoreCase("ADMIN")) {
				return onlinePaymentLogRepository.getOnlineUserRechargeHistoryAll();
			} else if (role.equalsIgnoreCase("OPERATOR")) {
				return onlinePaymentLogRepository.getOnlineUserRechargeHistorByOpid(username);
			} else {
				return onlinePaymentLogRepository.getOnlineUserRechargeHistory(username);

			}
		}

	}

	@Override
	public ResponseEntity<?> eazeBuzzOrderCreate(PaymentRequest reqobj) {

//		PaymentGatewayMaster pgmobj = paymentGatewayMasterRepository.findByGatewayname("EBUZZ");
		PaymentGatewayMaster pgmobj = paymentGatewayMasterRepository.findByIsactive(true);

		String phone = "";
		String email = "";
		String url = "";
		String baseurl = "";
		String firstname = "";
		if (pgmobj.getApikey() != null && pgmobj.getSalt() != null) {
			try {

				if (pgmobj.getMode().equalsIgnoreCase("TEST")) {
					url = "https://testpay.easebuzz.in/payment/initiateLink";
					baseurl = "https://testpay.easebuzz.in/pay/";
				} else {
					url = "https://pay.easebuzz.in/payment/initiateLink";
					baseurl = "https://pay.easebuzz.in/pay/";
				}

				Random rand = new Random();
				String rndm = Integer.toString(rand.nextInt()) + (System.currentTimeMillis() / 1000L);
				String txnid = OnlinePaymentAssets.hashCalForEbuzz("SHA-256", rndm).substring(0, 20);

				String key = pgmobj.getApikey();
				String salt = pgmobj.getSalt();
				String amount = reqobj.getAmount();
				String surl = pgmobj.getSuccessurl();
				String furl = pgmobj.getFaliureurl();
				String productinfo = reqobj.getTitle();

				System.out.println(txnid);

				if (reqobj.getRole().equalsIgnoreCase("OPERATOR")) {
					Operators opobj = operatorsRepository.findByUsername(reqobj.getUsername());

					phone = opobj.getPhone1();
					email = opobj.getEmail1();
					firstname = opobj.getFirstname() + " " + opobj.getLastname();

				} else if (reqobj.getRole().equalsIgnoreCase("USER")) {

					Userinfo userobj = userinfoRepository.findByUsername(reqobj.getUsername());
					phone = userobj.getMobilephone();
					email = userobj.getEmail();
					firstname = userobj.getFirstname() + " " + userobj.getLastname();
				}

				Online_payment_log opl = new Online_payment_log();
				opl.setOrderid(txnid);
				opl.setStatus("Initiated");
				opl.setGatewayid(pgmobj.getId());
				opl.setRole(reqobj.getRole());
				opl.setUsername(reqobj.getUsername());
				opl.setPaymenttypeid(4);
				opl.setAmount(reqobj.getAmount());
				opl.setTstarttime(Dateformat.getCurrentDatetime());
				opl.setMode(pgmobj.getMode());

				String status = PaymentSwitch(reqobj.getTitle(), reqobj.getPlanid(), reqobj.getRemark(), opl);

				if (status.equalsIgnoreCase("success")) {

					Online_plan_payment planobj = online_plan_paymentRepository.findByTransactionid(txnid);

					if (planobj == null) {

					} else {
						amount = planobj.getAmount();
					}

				} else {
					return ResponseEntity.badRequest().body(new MessageResponse(status));
				}

				String hastext = key.trim() + "|" + txnid.trim() + "|" + amount.trim() + "|" + productinfo.trim() + "|"
						+ firstname.trim() + "|" + email.trim() + "|||||||||||" + salt.trim();

//				String hastext = key.trim() + "|" + txnid.trim() + "|" + amount + "|userrecharge|" + firstname.trim()
//						+ "|" + email.trim() + "|||||||||||" + salt.trim();

				String hash = OnlinePaymentAssets.encryptThisString(hastext);

				System.out.println(hash);

				String response = OnlinePaymentAssets.easebuzzGatewayInitialCalling(txnid, amount, key, productinfo,
						firstname, phone, email, surl, furl, hash, url, pgmobj.getMerchant());

				System.out.println(response);

				JSONObject json_data = new JSONObject(response);
				System.out.println(json_data);
				System.out.println(txnid);
				System.out.println(response);

				Map<String, String> map = new HashMap<>();
				map.put("status", json_data.get("status").toString());
				map.put("data", json_data.get("data").toString());
				map.put("surl", surl);
				map.put("furl", furl);
				map.put("baseurl", baseurl);
				map.put("amount", amount);
				map.put("username", reqobj.getUsername());
				return ResponseEntity.ok(map);

			} catch (Exception e) {

			}
		}

		return null;
	}

	@Override
//	public ResponseEntity<?> eazeBuzzSuccess(String txnid, String status, String easepayid, String hash) {
	public ResponseEntity<?> eazeBuzzSuccess(Map<?, ?> reqobj) {

		if (reqobj.get("status").toString().equalsIgnoreCase("success")) {

			Online_payment_log opl = onlinePaymentLogRepository.findByOrderid(reqobj.get("txnid").toString());
			String type = "";

			if (opl != null) {

				if (opl.getRole().equalsIgnoreCase("OPERATOR")) {
					type = "wallet";
				} else if (opl.getRole().equalsIgnoreCase("USER")) {
					type = "plan";
				}
				updateOnlinePaymentBothPlanAndWallet(opl.getOrderid(), reqobj.get("easepayid").toString(),
						"Transaction successful", "", "0", "", type, reqobj.get("hash").toString(), "Completed");

			}

		}

//		return "<html>\n" + "  <head>\n"
//				+ "    <link href=\"https://fonts.googleapis.com/css?family=Nunito+Sans:400,400i,700,900&display=swap\" rel=\"stylesheet\">\n"
//				+ "  </head>\n  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">"
//				+ "    <style>\n" + "      body {\n" + "        text-align: center;\n" + "        padding: 40px 0;\n"
//				+ "        background: #EBF0F5;\n" + "      }\n" + "        h1 {\n" + "          color: #88B04B;\n"
//				+ "          font-family: \"Nunito Sans\", \"Helvetica Neue\", sans-serif;\n"
//				+ "          font-weight: 900;\n" + "          font-size: 40px;\n" + "          margin-bottom: 10px;\n"
//				+ "        }\n" + "        p {\n" + "          color: #404F5E;\n"
//				+ "          font-family: \"Nunito Sans\", \"Helvetica Neue\", sans-serif;\n"
//				+ "          font-size:20px;\n" + "          margin: 0;\n" + "        }\n" + "      i {\n"
//				+ "        color: #9ABC66;\n" + "        font-size: 100px;\n" + "        line-height: 200px;\n"
//				+ "        margin-left:-15px;\n" + "      }\n" + "      .card {\n" + "        background: white;\n"
//				+ "        padding: 60px;\n" + "        border-radius: 4px;\n"
//				+ "        box-shadow: 0 2px 3px #C8D0D8;\n" + "        display: inline-block;\n"
//				+ "        margin: 0 auto;\n" + "      }\n" + "    </style>\n" + "    <body>\n"
//				+ "      <div class=\"card\">\n"
//				+ "      <div style=\"border-radius:200px; height:200px; width:200px; background: #F8FAF5; margin:0 auto;\">\n"
//				+ "        <i class=\"checkmark\">✓</i>\n" + "      </div>\n" + "        <h1>Thank You!!</h1> \n"
//				+ "        <p>Your Transaction Successfully Completed.</p>\n<br/><br/>"
//				+ "        <div align=\"left\"><text style=\"text-align:right;font-size:17px;font-weight:bold;color:darkolivegreen\">Note :</text></div>\n<br/>"
//				+ "        <p style=\"font-size:16px\">* Kindly close the page and Refreash our website</p>\n"
//				+ "      </div>\n" + "    </body>\n" + "</html>";

		return ResponseEntity.ok(new MessageResponse(reqobj.get("status").toString()));
	}

	@Override
//	public String eazeBuzzFailed(String txnid, String status, String easepayid, String hash) {
	public ResponseEntity<?> eazeBuzzFailed(Map<?, ?> reqobj) {
		if (!reqobj.get("status").toString().equalsIgnoreCase("success")) {

			Online_payment_log opl = onlinePaymentLogRepository.findByOrderid(reqobj.get("txnid").toString());
			String type = "";

			if (opl != null) {

				if (opl.getRole().equalsIgnoreCase("OPERATOR")) {
					type = "wallet";
				} else if (opl.getRole().equalsIgnoreCase("USER")) {
					type = "plan";
				}

				String sts = "Completed";
				String resmsg = "Transaction Failed";
				if (reqobj.get("status").toString().equalsIgnoreCase("Pending")) {
					sts = "pending";
					resmsg = "Transaction Pending";
				}

				updateOnlinePaymentBothPlanAndWallet(opl.getOrderid(), reqobj.get("easepayid").toString(), resmsg, "",
						"1000", reqobj.get("status").toString(), type, reqobj.get("status").toString(), sts);

			}

		}

//		return "<html>\n" + "  <head>\n"
//				+ "    <link href=\"https://fonts.googleapis.com/css?family=Nunito+Sans:400,400i,700,900&display=swap\" rel=\"stylesheet\">\n"
//				+ "  </head>\n  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"> "
//				+ "    <style>\n" + "      body {\n" + "        text-align: center;\n" + "        padding: 40px 0;\n"
//				+ "        background: #EBF0F5;\n" + "      }\n" + "        h1 {\n" + "          color: red;\n"
//				+ "          font-family: \"Nunito Sans\", \"Helvetica Neue\", sans-serif;\n"
//				+ "          font-weight: 900;\n" + "          font-size: 40px;\n" + "          margin-bottom: 10px;\n"
//				+ "        }\n" + "        p {\n" + "          color: #404F5E;\n"
//				+ "          font-family: \"Nunito Sans\", \"Helvetica Neue\", sans-serif;\n"
//				+ "          font-size:20px;\n" + "          margin: 0;\n" + "        }\n" + "      i {\n"
//				+ "        color: red;\n" + "        font-size: 100px;\n" + "        line-height: 200px;\n"
//				+ "        margin-left:-15px;\n" + "      }\n" + "      .card {\n" + "        background: white;\n"
//				+ "        padding: 60px;\n" + "        border-radius: 4px;\n"
//				+ "        box-shadow: 0 2px 3px #C8D0D8;\n" + "        display: inline-block;\n"
//				+ "        margin: 0 auto;\n" + "      }\n" + "    </style>\n" + "    <body>\n"
//				+ "      <div class=\"card\">\n"
//				+ "      <div style=\"border-radius:200px; height:200px; width:200px; background: #FAF5F5; margin:0 auto;\">\n"
//				+ "        <i class=\"checkmark\">×</i>\n" + "      </div>\n"
//				+ "        <h1>Transaction Failed!!</h1> \n" + "        <p>Please try again later!</p><br/><br/>\n"
//				+ "        <div align=\"left\"><text style=\"text-align:right;font-size:17px;font-weight:bold;color:darkolivegreen\">Note :</text></div><br/>\n"
//				+ "        <text style=\"font-size:14px;color:#c50303\">* Kindly close the page and Refreash our website</text>\n"
//				+ "      </div>\n" + "    </body>\n" + "</html>";

		return ResponseEntity.ok(new MessageResponse(reqobj.get("status").toString()));
	}

	@Override
	public ResponseEntity<?> paymentRequest(PaymentRequest requestbody) {

		PaymentGatewayMaster pgmobj = paymentGatewayMasterRepository.findByIsactive(true);

		System.out.println(pgmobj.getApikey() + "  " + pgmobj.getGateway().getGatewayname());

		if (pgmobj.getGateway().getGatewayname().equals("AGGREPAY")) {

			return operatorPaymentRequest1(requestbody);

		} else if (pgmobj.getGateway().getGatewayname().equals("RAZORPAY")) {
			return planPaymentOrderCreation(requestbody);

		} else if (pgmobj.getGateway().getGatewayname().equals("EBUZZ")) {
			return eazeBuzzOrderCreate(requestbody);

		} else {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Gateway not enabled! check your gateway settings!"));
		}

	}

}
