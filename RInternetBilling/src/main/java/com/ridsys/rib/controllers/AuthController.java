package com.ridsys.rib.controllers;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ridsys.rib.comm.OnlinePaymentAssets;
import com.ridsys.rib.comm.SyslogApi;
import com.ridsys.rib.models.ERole;
import com.ridsys.rib.models.Role;
import com.ridsys.rib.models.User;
import com.ridsys.rib.models.Userinfo;
import com.ridsys.rib.payload.request.LoginRequest;
import com.ridsys.rib.payload.request.SignupRequest;
import com.ridsys.rib.payload.response.JwtResponse;
import com.ridsys.rib.payload.response.MessageResponse;
import com.ridsys.rib.repository.RoleRepository;
import com.ridsys.rib.repository.UserRepository;
import com.ridsys.rib.repository.UserinfoRepository;
import com.ridsys.rib.security.jwt.JwtUtils;
import com.ridsys.rib.security.services.UserDetailsImpl;
import com.ridsys.rib.service.UserService;
import com.ridsys.rib.service.ScheduleService;
import com.ridsys.rib.service.OttplanmanagelogService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	UserinfoRepository userinfoRepository;

	@Autowired
	UserService userService;

	@Autowired
	OttplanmanagelogService ottplanmanagelogService;

	@Autowired
	ScheduleService scheduleService;

	@Scheduled(cron = "0 0 01 * * ?")
//	@GetMapping("/sms")
	public void cronJobServiceSch() throws URISyntaxException {
		scheduleService.expiryAlertService();
//		scheduleService.expiryAlertServiceMV();

//		scheduleService.fast2SMSservice("hello!", "6380301519");

	}

	@Scheduled(cron = "0 0 12 * * *")
	public void dataUsageInsert() {
		scheduleService.dataUsageInsert();
	}

	@Scheduled(cron = "0 15 3 * * *")
	public void dataUsageDelete() {
		scheduleService.dataUsageDelete();
	}

	@Scheduled(cron = "0 30 23 * * *")
//	@GetMapping("/fup")
	public void fubUpdate() {
		scheduleService.fupUpdate();
	}

	@Scheduled(cron = "0 30 23 * * *")
	public void deleteRadacctHistory() {
		scheduleService.deleteRadacctHistory();
	}

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		if (roles.size() > 0) {
			return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(),
					userDetails.getEmail(), roles));

		}
		return ResponseEntity.badRequest().body(new MessageResponse("Login failed!"));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {

		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			System.out.println("str role is---" + strRoles);
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);
					break;
				case "operator":
					Role operatorRole = roleRepository.findByName(ERole.ROLE_OPERATOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(operatorRole);

					break;
				case "mod":
					Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);

					break;
				case "employee":
					Role empRole = roleRepository.findByName(ERole.ROLE_EMPLOYEE)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(empRole);

					break;

				case "iptvadmin":
					Role iptvadminRole = roleRepository.findByName(ERole.IPTV_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(iptvadminRole);

					break;

				case "iptvoperator":
					Role iptvoprRole = roleRepository.findByName(ERole.IPTV_OPERATOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(iptvoprRole);

					break;

				case "iptvuser":
					Role iptvuserRole = roleRepository.findByName(ERole.IPTV_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(iptvuserRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));
		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

	@PostMapping("/ottuserlogin")
	public ResponseEntity<?> authenticateOttUser(@RequestParam("mobileno") String mobile) {

		Userinfo obj = userinfoRepository.findByMobilephone(mobile);

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(obj.getUsername(), obj.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		if (roles.size() > 0) {
			return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(),
					userDetails.getEmail(), roles));
		}
		return ResponseEntity.badRequest().body(new MessageResponse("Login failed!"));
	}

	// Ott API's

	@GetMapping("/planstatus")
	public String getOttPlanStatusByUser(@Valid @RequestParam("user_id") int userid) {
		return userService.getOttPlanStatusByUser(userid);
	}

	@GetMapping("/checkAndroidID")
	public List<?> checkUserAndroidID(@Valid @RequestParam("android_id") String androidid) {
		return userService.checkUserAndroidID(androidid);
	}

	@GetMapping("/userdetail")
	public List<?> getUserDetails(@Valid @RequestParam("user_id") int userid) {
		return userService.getUserDetails(userid);
	}

	@PostMapping("/ottpackage")
	public ResponseEntity<?> createOttPackage(@Valid @RequestBody Map<String, String> obj) {

		return ottplanmanagelogService.createOttPackage(obj);
	}

//	@GetMapping("/get")
	public void welcomeAsHTML() {

		String key = "2PBP7IABZ2";
		String txnid = "c4fd1f23cd32fea90851";
		String amount = "1.0";
		String email = "gopinath6024@gmail.com";
		String phone = "9025169439";
		String salt = "DAH88E3UWQ";

		String hastext = key.trim() + "|" + txnid.trim() + "|" + amount.trim() + "|" + email.trim() + "|" + phone.trim()
				+ "|" + salt.trim();
		String hash = OnlinePaymentAssets.encryptThisString(hastext);

		System.out.println(hash);

		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://testdashboard.easebuzz.in/transaction/v1/retrieve"))
				.header("Content-Type", "application/x-www-form-urlencoded").header("Accept", "application/json")
				.method("POST",
						HttpRequest.BodyPublishers
								.ofString("key=" + key.trim() + "&txnid=" + txnid.trim() + "&amount=" + amount.trim()
										+ "&phone=" + phone.trim() + "&email=" + email.trim() + "&hash=" + hash.trim()))
				.build();
		java.net.http.HttpResponse<String> response = null;
		try {
			response = java.net.http.HttpClient.newHttpClient().send(request,
					java.net.http.HttpResponse.BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JSONObject json_data = new JSONObject(response.body().toString());
		System.out.println(json_data);
	}

	@GetMapping("/getPaymentStatus")
	public void checkstatus() {
		

		  String path = "file:=../../a/b/save.htm";
//	        path = "./a/../b/updat.htm";
//	        path = "./a/b/c.html";
	        path = "./a/b\\d\\c.html";
	        path = StringUtils.cleanPath(path);
	        System.out.println(path);
	        
//		String hastext = "QXESSGOAY3".trim() + "|" + "19cce8bc4ddd25f22c64".trim() + "|" + "708.00".trim() + "|"
//				+ "sathikum599@gmai.com".trim() + "|" + "8489002246".trim() + "|" + "3S3NHN82BW".trim();
//		String hash = OnlinePaymentAssets.encryptThisString(hastext);
//
//		System.out.println(hash);
//
//		//11c73fe674987034cdbb
//		String response = OnlinePaymentAssets.easebuzzRefreshCalling("19cce8bc4ddd25f22c64", "QXESSGOAY3", "708.0",
//				"sathikum599@gmai.com", hash, "8489002246", "LIVE");
//
//		System.out.println(response);
	}
	
	

}