package com.ridsys.rib.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.ridsys.rib.DTO.UserListDTO;
import com.ridsys.rib.comm.Dateformat;
import com.ridsys.rib.models.Clientinfo;
import com.ridsys.rib.models.Ottplanmanagelog;
import com.ridsys.rib.models.Renewalhistory;
import com.ridsys.rib.models.Userbillinfo;
import com.ridsys.rib.repository.ClientinfoRepository;
import com.ridsys.rib.repository.RadacctRepository;
import com.ridsys.rib.repository.RenewalhistoryRepository;
import com.ridsys.rib.repository.UserbillinfoRepository;
import com.ridsys.rib.repository.UserinfoRepository;

@Service
public class ScheduleService {

	@Autowired
	private JavaMailSender mailsender;

	@Value("${spring.mail.username}")
	private String sender;

	@Autowired
	private UserinfoRepository userinfoRepository;

	@Autowired
	ClientinfoRepository clientinfoRepository;

	@Autowired
	RenewalhistoryRepository renewalhistoryrepository;

	@Autowired
	UserbillinfoRepository userbillinfoRepository;
	
	@Autowired
	RadacctRepository radacctRepository;

	public void expiryAlertService() throws URISyntaxException {

		Clientinfo clientobj = clientinfoRepository.findOneClient();
//		String domainname = "";
//
//		try {
//			URL url = new URL(clientobj.getDomainurl());
//			domainname = url.getHost();
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		}

		int befexdays[] = { 2, 1, 0 };

		int afterexdays[] = { 2, 4 };

		List<String> message = new ArrayList();

		String smsmessage = "";

		String htmlMsg = "";

		for (int i = 0; i < befexdays.length; i++) {

			List<UserListDTO> exuserlist = userinfoRepository.getExpiryUserDetailsBefore(befexdays[i]);

			if (befexdays[i] == 1 || befexdays[i] == 2) {
				for (UserListDTO obj : exuserlist) {
					message = new ArrayList();
					System.out.println(obj.getUsername());
					smsmessage = "Dear Customer, Your Broadband Plan expired on " + obj.getQuotaexpirydate()
							+ " 23:59 Hrs. Click here to Recharge " + clientobj.getDomainurl() + " - MRDSYS";

					message.add("Dear Customer, Your plan is going to expiry " + obj.getPlan());
					message.add("Recharge Link: " + clientobj.getDomainurl() + "/#/pay/" + obj.getUsername() + "/"
							+ obj.getPlanid());

					htmlMsg = msgGenrate(obj, "is going to expiry on ", clientobj.getDomainurl(),
							clientobj.getLinkurl());
					// mail
					mailService(obj.getEmail(), htmlMsg);
//					// SMS

					bhashSms(obj.getMobile(), smsmessage);

					System.out.println(smsmessage);
				}

			} else if (befexdays[i] == 0) {
				message = new ArrayList();
				for (UserListDTO obj : exuserlist) {
					System.out.println("second");
					message = new ArrayList();
					System.out.println(obj.getUsername());
					smsmessage = "Dear Customer, Your Broadband Plan expired on " + obj.getQuotaexpirydate()
							+ " 23:59 Hrs. Click here to Recharge " + clientobj.getDomainurl() + " - MRDSYS";

					message.add("Dear Customer, Your plan is expiry soon " + obj.getPlan());
					message.add("Recharge Link: " + clientobj.getDomainurl() + "/#/pay/" + obj.getUsername() + "/"
							+ obj.getPlanid());

					htmlMsg = "<html>  \n <body>\n Hi " + obj.getFullname() + ",<br><br>\n";
					htmlMsg = htmlMsg
							+ "<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Your RNET Internet <span style='font-weight: bold'>"
							+ obj.getPlan()
							+ "</span> is Expiring TODAY . Please active your plan using this link <a href="
							+ clientobj.getDomainurl() + ">" + clientobj.getLinkurl()
							+ "</a> as early as possible for uninterrupted services.\n\n" + "           </p>\n\n";

					htmlMsg = htmlMsg + "Username : <span style='font-weight: bold'>" + obj.getUsername()
							+ "</span><br/>" + "Password   : <span  style='font-weight: bold'>" + obj.getMobile()
							+ "</span>\n\n";

					htmlMsg = htmlMsg
							+ "<br/><br/> For Contact:\n <br/><br/> LCO Name : <span style='font-weight: bold'>"
							+ obj.getLconame() + "</span><br/>" + "Mobile   : <span style='color:#00acff'>"
							+ obj.getMac() + "</span>\n\n";

					htmlMsg = htmlMsg + "<br/><br/><center> Thank You! </center> </body>\n </html>";

					mailService(obj.getEmail(), htmlMsg);

					bhashSms(obj.getMobile(), smsmessage);

					System.out.println(smsmessage);

				}

			}
		}

		for (int i = 0; i < afterexdays.length; i++) {
			message = new ArrayList();

			List<UserListDTO> exuserlist = userinfoRepository.getExpiryUserDetailsAfter(afterexdays[i]);

			for (UserListDTO obj : exuserlist) {
				System.out.println("third");
				System.out.println(obj.getUsername());
				message = new ArrayList();
				smsmessage = "Dear Customer, Your Broadband Plan expired on " + obj.getQuotaexpirydate()
						+ " 23:59 Hrs. Click here to Recharge " + clientobj.getDomainurl() + " - MRDSYS";

				message.add("Dear Customer, Your plan is was expired " + obj.getPlan());
				message.add("Recharge Link: " + clientobj.getDomainurl() + "/#/pay/" + obj.getUsername() + "/"
						+ obj.getPlanid());

				htmlMsg = msgGenrate(obj, " expired on ", clientobj.getDomainurl(), clientobj.getLinkurl());

				mailService(obj.getEmail(), htmlMsg);

				bhashSms(obj.getMobile(), smsmessage);

//				System.out.println(smsmessage);

			}
		}

//		List<UserListDTO> exuserlist = userinfoRepository.getTodayRechargedUserDetails();
//		for (UserListDTO obj : exuserlist) {
//			System.out.println(obj.getUsername());
//			smsmessage = "Dear Customer, Your Plan " + obj.getPlan() + " Successfully recharged";
//
//			htmlMsg = "<html>  \n <body>\n Hi " + obj.getFullname() + ",<br><br>\n";
//			htmlMsg = htmlMsg
//					+ "<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Your RNET Internet <span style='font-weight: bold'>"
//					+ obj.getPlan() + "</span>  Successfully recharged .";
//
//			htmlMsg = htmlMsg + "<br/><br/><center> Thank You! </center> </body>\n </html>";
//
////			System.out.println(htmlMsg);
//			// mail
//			mailService(obj.getEmail(), htmlMsg);
//			// SMS
////			smsService(obj.getMobile(), smsmessage);
//
//			System.out.println(smsmessage);
//
//		}

	}

	public static String msgGenrate(UserListDTO obj, String details, String domainname, String linkurl) {
		String htmlMsg = "<html>  \n <body>\n Hi " + obj.getFullname() + ",<br><br>\n";
		htmlMsg = htmlMsg
				+ "<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Your RNET Internet <span style='font-weight: bold'>"
				+ obj.getPlan() + "</span> " + details + "<span style='font-weight: bold'>" + obj.getQuotaexpirydate()
				+ " 23:59 Hrs</span>,Please active your plan using this link <a href=" + domainname + ">" + linkurl
				+ "</a> as early as possible for uninterrupted services.\n\n" + "           </p>\n\n";

		htmlMsg = htmlMsg + "Username : <span style='font-weight: bold'>" + obj.getUsername() + "</span><br/>"
				+ "Password   : <span  style='font-weight: bold'>" + obj.getMobile() + "</span>\n\n";

		htmlMsg = htmlMsg + "<br/><br/> For Contact:\n <br/><br/> LCO Name : <span style='font-weight: bold'>"
				+ obj.getLconame() + "</span><br/>" + "Mobile   : <span style='color:#00acff'>" + obj.getMac()
				+ "</span>\n\n";

		htmlMsg = htmlMsg + "<br/><br/><center> Thank You! </center> </body>\n </html>";

		return htmlMsg;

	}

	public void mailService(String email, String htmlMsg) {
		try {
			Properties props = new Properties();
			props.put("mail.smtp.host", "sg3plcpnl0081.prod.sin3.secureserver.net");// change accordingly
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "25");

			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("noreply@ridsys.in", "[t~qM*[@uZ4V");
				}
			});

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress("noreply@ridsys.in"));
			message.setSubject("INTERNET Plan Expiry Alert!");
//			message.setSubject("RNET Plan Expiry Alert!");
			message.setText(htmlMsg);
			message.addHeader("MIME-Version", "1.0\\r\\n");
			message.addHeader("Content-Type", "text/html");
			message.addHeader("charset=", "ISO-8859-1\\r\\n");
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
			Transport.send(message);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public String smsService(String mobileNo, String Message) {
		String line = "";
		try {
			String ApiKey = "RJHmeUN9bcMDpdqP/TcVjKZIEpiWTLi6RnckLF06eJs=";
			String sender = "MRDSYS";
			String Is_Unicode = "true";
			String Is_Flash = "true";
			String GroupId = "";
			String ClientId = "6ae1c5ad-abc9-4aa8-bdc8-f3a3a76216b7";

			JSONObject jsonobj = new JSONObject();
			jsonobj.put("SenderId", sender);
			jsonobj.put("Is_Unicode", Is_Unicode);
			jsonobj.put("Is_Flash", Is_Flash);
			jsonobj.put("GroupId", GroupId);
			jsonobj.put("Message", Message);
			jsonobj.put("MobileNumbers", "91" + mobileNo);
//			jsonobj.put("MobileNumbers", "919655638049");
			jsonobj.put("ApiKey", ApiKey);
			jsonobj.put("ClientId", ClientId);

//			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.mylogin.co.in/api/v2/SendSMS")
//					.openConnection();
//			conn.setDoOutput(true);
//			conn.setRequestMethod("POST");
//			conn.setRequestProperty("Content-Type", "application/json,charset=utf-8");
//			OutputStream os = conn.getOutputStream();
//			OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
//			osw.write(jsonobj.toJSONString());
//			osw.flush();
//			osw.close();
//			if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
//				try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
//					line = bufferedReader.readLine();
//					System.out.println(line);
//				} catch (Exception e) {
//					System.out.println("exception is " + e);
//				}
//			}
		} catch (Exception e) {
			System.out.println("exception is " + e);
		} finally {

		}
		return line;
	}

	public String bhashSms(String mobileNo, String Message) {
		String response = "";
		try {
			String user = "mrrtech";
			String pass = "123456";
			String sender = "MRDSYS";
			String priority = "ndnd";
			String stype = "normal";

			System.out.println("before replacing--" + Message);

			Message = Message.replace(" ", "%20");

			System.out.println("after replacing--" + Message);

			URL oracle = new URL("http://bhashsms.com/api/sendmsg.php?user=" + user + "&pass=" + pass + "&sender="
					+ sender + "&phone=" + mobileNo + "&text=" + Message + "&priority=" + priority + "&stype=" + stype);

			BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));
			String line = "";
			while ((line = in.readLine()) != null) {
				System.out.println(line);
				response += line;
			}
			in.close();

		} catch (Exception e) {
			System.out.println("exception is " + e);
		} finally {

		}
		return response;
	}

	public String fast2SMSservice(String mobileno, String message) throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		System.out.println(message + "   " + mobileno);
		String sender = "MRDSYS";
		String userCredentials = "piIoJy3A4KmxcRNS0r2lnQCGX7Yeq6Z9MaugvktHV1sTPObFwhQcnmEVbkeHyXwoiqsM1pTP9lL0Dr5I";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("authorization", userCredentials);

		JSONObject jsonobj = new JSONObject();
		jsonobj.put("sender_id", sender);
		jsonobj.put("language", "english");
		jsonobj.put("route", "v3");
		jsonobj.put("flash", 0);
		jsonobj.put("message", message);
		jsonobj.put("numbers", mobileno);

		URI url = new URI("https://www.fast2sms.com/dev/bulkV2");

		HttpEntity<String> entity = new HttpEntity<String>(jsonobj.toJSONString(), headers);

		ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

		System.out.println(result.getBody());
		return result.getBody();
	}

	public String smsService2(String mobileNo, String Message) {
		String line = "";
		try {
			Message = "Your Broadband plan going to expiry ,recharge via http://puduvainet.ajktvnetwork.com/R-CRM user:raja718 ,password:9790531885";
			String sender = "MRDSYS";
			String userCredentials = "piIoJy3A4KmxcRNS0r2lnQCGX7Yeq6Z9MaugvktHV1sTPObFwhQcnmEVbkeHyXwoiqsM1pTP9lL0Dr5I";

			JSONObject jsonobj = new JSONObject();
			jsonobj.put("sender_id", sender);
			jsonobj.put("language", "english");
			jsonobj.put("route", "v3");
			jsonobj.put("flash", 0);
			jsonobj.put("Message", Message);
			jsonobj.put("numbers", "9944369507");

			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.mylogin.co.in/api/v2/SendSMS")
					.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json,charset=utf-8");
			conn.setRequestProperty("authorization", userCredentials);
			OutputStream os = conn.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
			osw.write(jsonobj.toJSONString());
			osw.flush();
			osw.close();
//			if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
			try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
				line = bufferedReader.readLine();
				System.out.println(line);
			} catch (Exception e) {
				System.out.println("exception is " + e);
			}
//			}
		} catch (Exception e) {
			System.out.println("exception is " + e);
		} finally {

		}
		return line;
	}

	public void dataUsageInsert() {
		try {
			List<String> commands = new ArrayList<>();
			commands.add("/bin/bash");
			commands.add("-c");
			commands.add("echo \"CALL fr_new_data_usage_period()\" | mysql radius");
			System.out.println("command is  " + commands);
			ProcessBuilder pb = new ProcessBuilder(commands);
			try {
				Process p = pb.start();
				int j = p.waitFor();
				BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
				String line;
				while ((line = reader.readLine()) != null) {
					System.out.println(line);
				}
			} catch (Exception e) {
				System.out.println("exception: " + e);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void dataUsageDelete() {
		try {
			List<String> commands = new ArrayList<>();
			commands.add("/bin/bash");
			commands.add("-c");
			commands.add(
					"echo \"DELETE FROM data_usage_by_period WHERE period_end < DATE_SUB(CURDATE(), INTERVAL 700 day);\" | mysql radius");
			System.out.println("command is  " + commands);
			ProcessBuilder pb = new ProcessBuilder(commands);
			try {
				Process p = pb.start();
				int j = p.waitFor();
				BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
				String line;
				while ((line = reader.readLine()) != null) {
					System.out.println(line);
				}
			} catch (Exception e) {
				System.out.println("exception: " + e);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void expiryAlertServiceMV() {

		int befexdays[] = { 2, 1, 0 };

		int afterexdays[] = { 2, 4 };

		String message = "";

		String htmlMsg = "";

		for (int i = 0; i < befexdays.length; i++) {

			List<UserListDTO> exuserlist = userinfoRepository.getExpiryUserDetailsBefore(befexdays[i]);

			if (befexdays[i] == 1 || befexdays[i] == 2) {

				for (UserListDTO obj : exuserlist) {

					message = "Dear Customer, Your Broadband Plan " + obj.getPlan() + " going to expire on "
							+ obj.getQuotaexpirydate()
							+ " 23:59 Hrs. Click here to Recharge http://puduvainet.ajktvnetwork.com/R-CRM \n";

					message = message + "Username: " + obj.getUsername() + " \n" + "Password: " + obj.getMobile();

					htmlMsg = msgGenrateMV(obj, "is going to expiry on ");

					// mail
					mailService(obj.getEmail(), htmlMsg);
					// SMS
					smsService(obj.getMobile(), message);

				}

			} else if (befexdays[i] == 0) {
				for (UserListDTO obj : exuserlist) {
					System.out.println(obj.getUsername());
					message = "Dear Customer, Your Broadband Plan " + obj.getPlan()
							+ " is Expiring TODAY. Click here to Recharge  http://puduvainet.ajktvnetwork.com/R-CRM \n";
					message = message + "Username: " + obj.getUsername() + " \n" + "Password: " + obj.getMobile();

					htmlMsg = "<html>  \n <body>\n Hi " + obj.getFullname() + ",<br><br>\n";
					htmlMsg = htmlMsg
							+ "<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Your Internet <span style='font-weight: bold'>"
							+ obj.getPlan()
							+ "</span> is Expiring TODAY . Please active your plan using this link <a href='http://puduvainet.ajktvnetwork.com/R-CRM'>rnet.ridsys.in</a> as early as possible for uninterrupted services.\n\n"
							+ "           </p>\n\n";

					htmlMsg = htmlMsg + "Username : <span style='font-weight: bold'>" + obj.getUsername()
							+ "</span><br/>" + "Password   : <span  style='font-weight: bold'>" + obj.getMobile()
							+ "</span>\n\n";

					htmlMsg = htmlMsg
							+ "<br/><br/> For Contact:\n <br/><br/> LCO Name : <span style='font-weight: bold'>"
							+ obj.getLconame() + "</span><br/>" + "Mobile   : <span style='color:#00acff'>"
							+ obj.getMac() + "</span>\n\n";

					htmlMsg = htmlMsg + "<br/><br/><center> Thank You! </center> </body>\n </html>";

					// mail
					mailService(obj.getEmail(), htmlMsg);
					// SMS
					smsService(obj.getMobile(), message);
				}
			}

		}

		for (int i = 0; i < afterexdays.length; i++) {

			List<UserListDTO> exuserlist = userinfoRepository.getExpiryUserDetailsAfter(afterexdays[i]);

			for (UserListDTO obj : exuserlist) {
				message = "Dear Customer, Your Broadband Plan " + obj.getPlan() + "  expired on "
						+ obj.getQuotaexpirydate()
						+ " 23:59 Hrs. Click here to Recharge http://puduvainet.ajktvnetwork.com/R-CRM \n";
				message = message + "Username: " + obj.getUsername() + " \n" + "Password: " + obj.getMobile();

				htmlMsg = msgGenrateMV(obj, " expired on ");

				// mail
				mailService(obj.getEmail(), htmlMsg);
				// SMS
				smsService(obj.getMobile(), message);
			}
		}

		List<UserListDTO> exuserlist = userinfoRepository.getTodayRechargedUserDetails();
		for (UserListDTO obj : exuserlist) {
			message = "Dear Customer, Your Plan " + obj.getPlan() + " Successfully recharged";

			htmlMsg = "<html>  \n <body>\n Hi " + obj.getFullname() + ",<br><br>\n";
			htmlMsg = htmlMsg
					+ "<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Your Internet <span style='font-weight: bold'>"
					+ obj.getPlan() + "</span>  Successfully recharged .";

			htmlMsg = htmlMsg + "<br/><br/><center> Thank You! </center> </body>\n </html>";

			System.out.println(htmlMsg);
			// mail
			mailService(obj.getEmail(), htmlMsg);
			// SMS
			smsService(obj.getMobile(), message);
		}

	}

	public static String msgGenrateMV(UserListDTO obj, String details) {
		String htmlMsg = "<html>  \n <body>\n Hi " + obj.getFullname() + ",<br><br>\n";
		htmlMsg = htmlMsg
				+ "<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Your Internet <span style='font-weight: bold'>"
				+ obj.getPlan() + "</span> " + details + "<span style='font-weight: bold'>" + obj.getQuotaexpirydate()
				+ " 23:59 Hrs</span>,Please active your plan using this link <a href='http://puduvainet.ajktvnetwork.com/R-CRM '>puduvainet.ajktvnetwork.com</a> as early as possible for uninterrupted services.\n\n"
				+ "           </p>\n\n";

		htmlMsg = htmlMsg + "Username : <span style='font-weight: bold'>" + obj.getUsername() + "</span><br/>"
				+ "Password   : <span  style='font-weight: bold'>" + obj.getMobile() + "</span>\n\n";

		htmlMsg = htmlMsg + "\n<br/><br/> For Contact:\n <br/><br/> LCO Name : <span style='font-weight: bold'>"
				+ obj.getLconame() + "</span><br/>" + "Mobile   : <span style='color:#00acff'>" + obj.getMac()
				+ "</span>\n\n";

		htmlMsg = htmlMsg + "<br/><br/><center> Thank You! </center> </body>\n </html>";
		System.out.println(htmlMsg);

		return htmlMsg;

	}

	public void fupUpdate() {

		try {

			List<Renewalhistory> renewallist = renewalhistoryrepository.getTrwFubStartDate();

			for (Renewalhistory robj : renewallist) {

				Userbillinfo userobj = userbillinfoRepository.findByUsername(robj.getUsername());

				userobj.setQuotastartdate(robj.getNewstartdate());
				userobj.setUpdatedate(Dateformat.getCurrentDatetime());
				userbillinfoRepository.save(userobj);

				robj.setIsupdate(true);
				robj.setUpdateddate(Dateformat.getCurrentDatetime());
				renewalhistoryrepository.save(robj);

			}

		} catch (Exception e) {
			System.out.println(e);
		}

	}
	
	public void deleteRadacctHistory() {
		
		radacctRepository.deleteUserErrorRadacctHistory();

			
	}
}
