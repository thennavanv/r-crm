package com.ridsys.rib.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ridsys.rib.DTO.OttuserDTO;
import com.ridsys.rib.DTO.UserListDTO;
import com.ridsys.rib.DTO.UserProfileInfoDTO;
import com.ridsys.rib.DTO.UserStatusDTO;
import com.ridsys.rib.comm.Dateformat;
import com.ridsys.rib.comm.IptvApi;
import com.ridsys.rib.comm.OttApi;
import com.ridsys.rib.models.Clientinfo;
import com.ridsys.rib.models.ERole;
import com.ridsys.rib.models.Operator_change_history;
import com.ridsys.rib.models.Operators;
import com.ridsys.rib.models.Ottuserinfo;
import com.ridsys.rib.models.Radcheck;
import com.ridsys.rib.models.Radgroupcheck;
import com.ridsys.rib.models.Radgroupreply;
import com.ridsys.rib.models.Radippool;
import com.ridsys.rib.models.Radreply;
import com.ridsys.rib.models.Radusergroup;
import com.ridsys.rib.models.Role;
import com.ridsys.rib.models.User;
import com.ridsys.rib.models.Userbillinfo;
import com.ridsys.rib.models.Userinfo;
import com.ridsys.rib.models.Userproof;
import com.ridsys.rib.payload.request.CreateAttributesRequest;
import com.ridsys.rib.payload.request.CreateGroupRequest;
import com.ridsys.rib.payload.request.CreateProfileRequest;
import com.ridsys.rib.payload.request.CreatebulkuserRequest;
import com.ridsys.rib.payload.request.CreateuserRequest;
import com.ridsys.rib.payload.request.UserDeviceInfoUpdateRequest;
import com.ridsys.rib.payload.response.MessageResponse;
import com.ridsys.rib.payload.response.MessageResponseForBulk;
import com.ridsys.rib.repository.ClientinfoRepository;
import com.ridsys.rib.repository.Operator_change_historyRepository;
import com.ridsys.rib.repository.Operator_permissionRepository;
import com.ridsys.rib.repository.OperatorsRepository;
import com.ridsys.rib.repository.OttuserinfoRepository;
import com.ridsys.rib.repository.RadcheckRepository;
import com.ridsys.rib.repository.RadgroupcheckRepository;
import com.ridsys.rib.repository.RadgroupreplyRepository;
import com.ridsys.rib.repository.RadippoolRepository;
import com.ridsys.rib.repository.RadreplyRepository;
import com.ridsys.rib.repository.RadusergroupRepository;
import com.ridsys.rib.repository.RoleRepository;
import com.ridsys.rib.repository.SwitchRepository;
import com.ridsys.rib.repository.UserRepository;
import com.ridsys.rib.repository.UserbillinfoRepository;
import com.ridsys.rib.repository.UserinfoRepository;
import com.ridsys.rib.repository.UserproofRepository;
import com.ridsys.rib.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	private UserinfoRepository userinfoRepository;

	@Autowired
	private UserbillinfoRepository userbillinfoRepository;

	@Autowired
	private RadcheckRepository radcheckRepository;

	@Autowired
	private RadreplyRepository radreplyRepository;

	@Autowired
	private RadusergroupRepository radusergroupRepository;

	@Autowired
	private RadgroupcheckRepository radgroupcheckRepository;

	@Autowired
	private RadgroupreplyRepository radgroupreplyRepository;

	@Autowired
	private OperatorsRepository operatorsRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	OttuserinfoRepository ottuserinfoRepository;

	@Autowired
	Operator_permissionRepository operator_permissionRepository;

	@Autowired
	RadippoolRepository radippoolRepository;

	@Autowired
	Operator_change_historyRepository operator_change_historyRepository;

	@Autowired
	UserproofRepository userproofRepository;

	@Autowired
	ClientinfoRepository clientinfoRepository;

	@Override
	public ResponseEntity<?> createUser(CreateuserRequest obj) {

		if (obj.getUsername() == "" || obj.getPassword() == "") {
			return ResponseEntity.badRequest().body(new MessageResponse("username or password are empty"));

		}

		boolean usernameverified = false;
		if (obj.getUsername().length() >= 6) {// Username not should be with special character for JWT AuthToken
												// Generation
			Pattern special = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
			Matcher hasSpecial = special.matcher(obj.getUsername());
			usernameverified = hasSpecial.find();// If present any special character assign 'true'
			if (usernameverified) {// If present any special character with username
				return ResponseEntity.badRequest()
						.body(new MessageResponse("Username not should be with special character!."));
			}
		} else {
			return ResponseEntity.badRequest().body(new MessageResponse("The username length should be 6 or more!."));
		}
		if (obj.getPassword().length() < 6) {
			return ResponseEntity.badRequest().body(new MessageResponse("The password length should be 6 or more!."));
		}
		if (obj.getOpusername() == "") {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("for creating user is not authorized operator"));

		} else if (obj.getAuthtype() == "") {
			return ResponseEntity.badRequest().body(new MessageResponse("authtype is empty"));
		}
		if (radcheckRepository.existsByUsername(obj.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("The username is already in use!"));

		} else if (obj.getUserinfo().getMobilephone() != ""
				&& userinfoRepository.existsByMobilephone(obj.getUserinfo().getMobilephone())) {
			return ResponseEntity.badRequest().body(new MessageResponse("The mobile number is already in use!"));

		} else if (obj.getUserinfo().getWorkphone() != ""
				&& userinfoRepository.existsByWorkphone(obj.getUserinfo().getWorkphone())) {
			return ResponseEntity.badRequest().body(new MessageResponse("The mobile number is already in use!"));

		} else if (obj.getUserinfo().getEmail() != ""
				&& userinfoRepository.existsByEmail(obj.getUserinfo().getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("The email Id is already in use!"));
		}
		if (userRepository.existsByUsername(obj.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(obj.getUserinfo().getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}

		if (!obj.isAutoip()) {
			if (radippoolRepository.getUsernameByFromAndFramedIp(obj.getFromip(), obj.getFramedipaddress()) != null) {
				return ResponseEntity.badRequest().body(new MessageResponse("Error: IP already allocated for User!"));
			}
		}

		if (obj.getAuthtype().equalsIgnoreCase("userAuth")) {

			String result = userCreation(obj);

			if (result.contains("Success")) {
				return ResponseEntity.ok(new MessageResponse("New user created successfully!"));
			} else if (result.contains("Username")) {
				return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
			}

		} else {
			return ResponseEntity.badRequest().body(new MessageResponse("invalid authtype"));
		}
		return null;
	}

	public String userCreation(CreateuserRequest obj) {
		Radcheck radcheck = new Radcheck();
//		radcheck.setUsername(obj.getUsername());
//		radcheck.setAttribute("Cleartext-Password");
//		radcheck.setOp(":=");
//		radcheck.setValue(obj.getPassword());
//		// insert username/password
//		radcheckRepository.save(radcheck);

		Operators operator = operatorsRepository.findByUsername(obj.getOpusername());

		Userinfo userinfo = new Userinfo();
		if (obj.getUserinfo() != null) {
			userinfo = obj.getUserinfo();
		}

		userinfo.setUsername(obj.getUsername());
		userinfo.setPassword(obj.getPassword());
		userinfo.setCreationby(obj.getRole());
		userinfo.setOpid(operator.getId());
		String currentdatetime = Dateformat.getCurrentDatetime();
		userinfo.setCreationdate(currentdatetime);
		userinfo.setMobilephone(obj.getUserinfo().getMobilephone());
		userinfo.setWalletbalance("0");
		userinfo.setIptype(obj.getIptype());
		userinfo.setIsiptv(obj.isSelectIsIptv());

		if (userinfoRepository.existsByUsername(userinfo.getUsername())) {
			return "The username is already in use!";
		}

		if (obj.getUserproofimage1() != null) {
			userinfo.setUserproofimage1(obj.getUserproofimage1());
		}

		// insert user information
		userinfoRepository.save(userinfo);

		Userbillinfo userbillinfo = new Userbillinfo();
		if (obj.getUserbillinfo() != null) {
			userbillinfo = obj.getUserbillinfo();
		}
		userbillinfo.setUsername(obj.getUsername());
		userbillinfo.setCreationby(obj.getRole());
		userbillinfo.setCreationdate(currentdatetime);

		if (userbillinfoRepository.existsByUsername(userbillinfo.getUsername())) {
			return "The username is already in use!";
		}
		// insert user bill information
		userbillinfoRepository.save(userbillinfo);

		if (obj.getRadusergroups().size() > 0) {
			for (Radusergroup rug : obj.getRadusergroups()) {
				if (rug.getGroupname() != "") {
					rug.setUsername(obj.getUsername());
					rug.setPriority(0);
					// insert usergroup mapping
					radusergroupRepository.save(rug);
				}
			}
		}

		List<CreateAttributesRequest> attributes = new ArrayList<>();
		if (obj.getAttributes().size() > 0) {
			attributes = obj.getAttributes();

//			CreateAttributesRequest car = new CreateAttributesRequest();
//			car.setTable("check");
//			car.setAttribute("Fall-Through");
//			car.setOp(":=");
//			car.setValue("Yes");
//			attributes.add(car);
//
//			car = new CreateAttributesRequest();
//			car.setTable("reply");
//			car.setAttribute("Acct-Interim-Interval");
//			car.setOp(":=");
//			car.setValue("60");
//			attributes.add(car);

			for (CreateAttributesRequest ab : attributes) {

				if (ab.getAttribute() != "" && ab.getOp() != "" && ab.getValue() != "") {
					if (ab.getTable().equalsIgnoreCase("check")) {
						radcheck = new Radcheck();
						radcheck.setUsername(obj.getUsername());
						radcheck.setAttribute(ab.getAttribute());
						radcheck.setOp(ab.getOp());
						radcheck.setValue(ab.getValue());

						// insert radcheck
						radcheckRepository.save(radcheck);
					} else if (ab.getTable().equalsIgnoreCase("reply")) {
						Radreply radreply = new Radreply();
						radreply.setUsername(obj.getUsername());
						radreply.setAttribute(ab.getAttribute());
						radreply.setOp(ab.getOp());
						radreply.setValue(ab.getValue());

						// insert radreply
						radreplyRepository.save(radreply);
					}
				}
			}
		}
		Set<Role> roles = new HashSet<>();
		Role operatorRole = roleRepository.findByName(ERole.ROLE_USER)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		roles.add(operatorRole);

		// Create new user's account
		User user = new User(userinfo.getUsername(), userinfo.getEmail(),
				encoder.encode(obj.getUserinfo().getMobilephone()));
		user.setRoles(roles);
		userRepository.save(user);

		// set framedip

		if (!obj.isAutoip()) {
			Radippool radipobj = radippoolRepository.findByFromipAndFramedipaddress(obj.getFromip(),
					obj.getFramedipaddress());

			if (radipobj != null) {
				radipobj.setUsername(userinfo.getUsername());
				radipobj.setUpdateddate(Dateformat.getCurrentDatetime());
				radippoolRepository.save(radipobj);

				Radreply radreply = new Radreply();
				radreply.setUsername(obj.getUsername());
				radreply.setAttribute("Framed-IP-address");
				radreply.setOp(":=");
				radreply.setValue(radipobj.getFramedipaddress());
				// insert radreply
				radreplyRepository.save(radreply);

			}
		}

		//////////////////////////////////
		try {
			if (obj.isSelectIsOtt()) {
				Ottuserinfo ottObj = new Ottuserinfo();
				ottObj.setUsername(obj.getUsername());
				ottObj.setEmail(obj.getUserinfo().getEmail());
				ottObj.setMobilephone(obj.getUserinfo().getMobilephone());
				ottObj.setCreationdate(currentdatetime);
				ottObj.setIsactive(true);
				ottuserinfoRepository.save(ottObj);

				OttApi.userCreationOtt(userinfo);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {

		}
		////////////////////////////////////////////

		try {
			if (obj.isSelectIsIptv()) {

//				IptvApi.SubscriberCreation(userinfo, true);
//				IptvApi.SubscriberCreationForSMS(userinfo);

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {

		}

		return "Success!";
	}

	@Override
	public List<UserListDTO> getUserListWithCurrentStatus(String username, String status, String fdate, String tdate) {

		if (!fdate.equals("0000-00-00") && !tdate.equals("0000-00-00")) {
			if (username != "") {
				Operators object = operatorsRepository.findByUsername(username);
				if (object == null) {
					if (status.equals("all")) {
						return userinfoRepository.gatUserListWithCurrentStatusAllFtdate(fdate, tdate);
					}
				} else {
					if (status.equals("all")) {
						return userinfoRepository.gatUserListWithCurrentStatusAllByOpidFtdate(object.getId(), fdate,
								tdate);
					}
				}
			}
		} else {

			if (username != "") {
				Operators object = operatorsRepository.findByUsername(username);
				if (object == null) {
					if (status.equals("all")) {
						return userinfoRepository.gatUserListWithCurrentStatusAll();

					} else if (status.equals("notexp")) {
						return userinfoRepository.gatUserListWithCurrentStatusNotExp();

					} else if (status.equals("exp")) {
						return userinfoRepository.gatUserListWithCurrentStatusExp();

					} else if (status.equals("online")) {
						return userinfoRepository.gatUserListWithCurrentStatusOnline();

					} else if (status.equals("offline")) {
						return userinfoRepository.gatUserListWithCurrentStatusOffline();

					} else if (status.equals("underverif")) {
						return userinfoRepository.gatUserListWithCurrentStatusUnderverif();

					} else if (status.equalsIgnoreCase("new")) {
						return userinfoRepository.gatUserListWithCurrentStatusNew();

					} else if (status.equalsIgnoreCase("delete")) {
						return userinfoRepository.gatUserListWithCurrentStatusDelete();

					} else if (status.equalsIgnoreCase("plancust")) {
						return userinfoRepository.gatUserListWithCurrentStatusPlanWise();

					} else if (status.equalsIgnoreCase("Max")) {
						return userinfoRepository.gatUserListWithCurrentStatusMaxDataUserAll();

					}
				} else {
					if (status.equals("all")) {
						return userinfoRepository.gatUserListWithCurrentStatusAllByOpid(object.getId());

					} else if (status.equals("notexp")) {
						return userinfoRepository.gatUserListWithCurrentStatusNotExpByOpid(object.getId());

					} else if (status.equals("exp")) {
						return userinfoRepository.gatUserListWithCurrentStatusExpByOpid(object.getId());

					} else if (status.equals("online")) {
						return userinfoRepository.gatUserListWithCurrentStatusOnlineByOpid(object.getId());

					} else if (status.equals("offline")) {
						return userinfoRepository.gatUserListWithCurrentStatusOfflineByOpid(object.getId());

					} else if (status.equals("underverif")) {
						return userinfoRepository.gatUserListWithCurrentStatusUnVerfByOpid(object.getId());
					} else if (status.equalsIgnoreCase("new")) {
						return userinfoRepository.gatUserListWithCurrentStatusNewByOpid(object.getId());

					} else if (status.equalsIgnoreCase("delete")) {
						return userinfoRepository.gatUserListWithCurrentStatusDeleteByOpid(object.getId());

					} else if (status.equalsIgnoreCase("plancust")) {
						return userinfoRepository.gatUserListWithCurrentStatusPlanWiseByOpid(object.getId());

					} else if (status.equalsIgnoreCase("Max")) {
						return userinfoRepository.gatUserListWithCurrentStatusMaxDataUserByOpid(object.getId());

					}

				}
			}
		}
		return null;
	}

	@Override
	public ResponseEntity<?> createUserProfile(CreateProfileRequest obj) {

		List<CreateGroupRequest> groups = new ArrayList<>();
		if (obj.getGroups().size() > 0) {
			groups = obj.getGroups();

			if (radgroupcheckRepository.existsByGroupname(obj.getProfilename())) {
				return ResponseEntity.badRequest().body(new MessageResponse("The profilname is already in use!"));

			} else if (radgroupreplyRepository.existsByGroupname(obj.getProfilename())) {
				return ResponseEntity.badRequest().body(new MessageResponse("The profilname is already in use!"));

			}

			for (CreateGroupRequest gp : groups) {

				if (gp.getAttribute() != "" && gp.getOp() != "" && gp.getValue() != "") {
					if (gp.getTable().equalsIgnoreCase("check")) {
						Radgroupcheck radgroupcheck = new Radgroupcheck();
						radgroupcheck.setGroupname(obj.getProfilename());
						radgroupcheck.setAttribute(gp.getAttribute());
						radgroupcheck.setOp(gp.getOp());
						radgroupcheck.setValue(gp.getValue());

						// insert radgroupcheck
						radgroupcheckRepository.save(radgroupcheck);
					} else if (gp.getTable().equalsIgnoreCase("reply")) {
						Radgroupreply radgroupreply = new Radgroupreply();
						radgroupreply.setGroupname(obj.getProfilename());
						radgroupreply.setAttribute(gp.getAttribute());
						radgroupreply.setOp(gp.getOp());
						radgroupreply.setValue(gp.getValue());

						// insert radgroupreply
						radgroupreplyRepository.save(radgroupreply);
					}
				}
			}
			return ResponseEntity.ok(new MessageResponse("New profile created successfully!"));
		} else {
			return ResponseEntity.badRequest().body(new MessageResponse("Groups should not be empty!"));
		}

	}

	@Override
	public UserProfileInfoDTO getUserInfoWithCurrentStatusByUsername(String username) {
		return userinfoRepository.getUserInfoWithCurrentStatusByUsername(username);
	}

	@Override
	public ResponseEntity<?> uploaduserimageproof(String username, String filename) {

		userinfoRepository.updateUserProofImage(username, filename);

		Userproof userproof_obj = new Userproof();

		int count = userproofRepository.getcount(username);
		userproof_obj.setUsername(username);
		userproof_obj.setImageurl(filename);
		if (count == 0) {
			userproof_obj.setCreateddate(Dateformat.getCurrentDatetime());
		} else if (count > 0) {
			userproof_obj.setUpdateddate(Dateformat.getCurrentDatetime());
		}
		userproofRepository.save(userproof_obj);

		return ResponseEntity.ok(new MessageResponse("User proof image upload successfully!"));

	}

	@Override
	public ResponseEntity<?> userApproval(String username) {

		// Update user verification status
		userinfoRepository.userApproval(username);

		return ResponseEntity.ok(new MessageResponse("User approved successfully!"));
	}

	@Override
	public ResponseEntity<?> updateUserDeviceInfo(UserDeviceInfoUpdateRequest uDevInfo) {

		// Update user device info
		userinfoRepository.updateUserDeviceInfo(uDevInfo.getOlttype(), uDevInfo.getPonout(), uDevInfo.getUserdevice(),
				uDevInfo.getMacaddress(), uDevInfo.getUsername(), uDevInfo.getVlanid());
		return ResponseEntity.ok(new MessageResponse("User device info updated successfully!"));
	}

	@Override
	public ResponseEntity<?> editUserDetails(CreateuserRequest reqobj) {

		Userinfo uobj = reqobj.getUserinfo();
		Userbillinfo ubobj = reqobj.getUserbillinfo();
		Userinfo userobj = new Userinfo();
		userobj = userinfoRepository.findByUsername(reqobj.getUsername());

		Userbillinfo userbillobj = new Userbillinfo();
		userbillobj = userbillinfoRepository.findByUsername(reqobj.getUsername());

		if (reqobj.getUserinfo().getMobilephone() != ""
				&& (userinfoRepository.checkMobilenumber(reqobj.getUsername(), uobj.getMobilephone()) > 0)) {
			return ResponseEntity.badRequest().body(new MessageResponse("The mobile number is already in use!"));

		} else if (reqobj.getUserinfo().getEmail() != ""
				&& (userinfoRepository.checkEmailaddress(reqobj.getUsername(), uobj.getEmail()) > 0)) {
			return ResponseEntity.badRequest().body(new MessageResponse("The email Id is already in use!"));

		} else if (reqobj.getUserinfo().getHomephone() != ""
				&& (userinfoRepository.checkHomenumber(reqobj.getUsername(), uobj.getHomephone()) > 0)) {
			return ResponseEntity.badRequest().body(new MessageResponse("The Home mobile number is already in use!"));
		}

		if (reqobj.getPassword().length() < 6) {
			return ResponseEntity.badRequest().body(new MessageResponse("The password length should be 6 or more!."));
		}

		if (reqobj.getUserinfo().getDeviceno() != ""
				&& (userinfoRepository.checkDeviceNo(reqobj.getUsername(), uobj.getDeviceno()) > 0)) {
			return ResponseEntity.badRequest().body(new MessageResponse("The Device number is already in use!"));

		}

		if (userobj.getMobilephone() != uobj.getMobilephone() || userobj.getEmail() != uobj.getEmail()) {
			Optional<User> loginobj = userRepository.findByUsername(reqobj.getUsername());

			loginobj.get().setEmail(uobj.getEmail());
			loginobj.get().setPassword(encoder.encode(uobj.getMobilephone()));
			userRepository.save(loginobj.get());

		}

		userobj.setFirstname(uobj.getFirstname());
		userobj.setLastname(uobj.getLastname());
		userobj.setMobilephone(uobj.getMobilephone());
		userobj.setEmail(uobj.getEmail());
		userobj.setAddress(uobj.getAddress());
		userobj.setCity(uobj.getCity());
		userobj.setState(uobj.getState());
		userobj.setZip(uobj.getZip());
		userobj.setHomephone(uobj.getHomephone());
		userobj.setUpdatedate(Dateformat.getCurrentDatetime());
		userobj.setPassword(reqobj.getPassword());
		userobj.setUserdevice(uobj.getUserdevice());
		userobj.setDeviceno(uobj.getDeviceno());
		userinfoRepository.save(userobj);

		userbillobj.setAddress(ubobj.getAddress());
		userbillobj.setCity(ubobj.getCity());
		userbillobj.setState(ubobj.getState());
		userbillobj.setZip(ubobj.getZip());
		userbillobj.setCountry(ubobj.getCountry());
		userbillobj.setUpdatedate(Dateformat.getCurrentDatetime());
		userbillinfoRepository.save(userbillobj);

		Radcheck radcheckobj = new Radcheck();

		radcheckobj = radcheckRepository.findByUsernameAndAttribute(reqobj.getUsername(), "Cleartext-Password");
//		System.out.println(radcheckobj.getUsername() + "  " + reqobj.getPassword());
		if (radcheckobj != null) {
			radcheckobj.setValue(reqobj.getPassword());
			radcheckRepository.save(radcheckobj);
		}
		return ResponseEntity.ok(new MessageResponse("User Details Edited Successfully!"));

	}

	@Override
	public List<UserStatusDTO> getUserStatus(String username, String status) {
		String t1 = Dateformat.todayDate();
		if (status.equalsIgnoreCase("ALL")) {
			return userinfoRepository.getUserStatusAll(username);

		} else if (status.equalsIgnoreCase("ACTIVE")) {
			return userinfoRepository.getUserStatusActive(username, t1);

		} else if (status.equalsIgnoreCase("DEACTIVE")) {
			return userinfoRepository.getUserStatusDeactive(username, t1);

		} else if (status.equalsIgnoreCase("NEW")) {
			return userinfoRepository.getUserStatusNew(username);

		}
		return null;
	}

	@Override
	public ResponseEntity<?> userReject(String username) {
		userinfoRepository.userReject(username);
		return ResponseEntity.ok(new MessageResponse("User successfully Rejected!"));
	}

	@Override
	public String getOttPlanStatusByUser(int userid) {

		Userinfo obj = userinfoRepository.findById(userid);

		int status = ottuserinfoRepository.getOttPlanStatusByUser(obj.getUsername());

		switch (status) {
		case 1:
			return ("Success");

		case 0:
			return ("Please Active your plan");

		default:
			return ("Your Plan is Expired");

		}

	}

	@Override
	public List<Map<String, Object>> checkUserAndroidID(String androidid) {

		Map<String, Object> map = new HashMap<>();
		List<Map<String, Object>> lst = new ArrayList<>();
		int userid = 0;

		try {
			userid = ottuserinfoRepository.checkUserAndroidID(androidid);

		} catch (Exception e) {
			System.out.println("Exception at:" + e);

		} finally {

		}

		if (userid == 0) {
			map.put("Status", "Failed");
			map.put("user_id", userid);

		} else {

			map.put("Status", "Success");
			map.put("user_id", userid);
		}

		lst.add(map);
		return lst;
	}

	@Override
	public List<OttuserDTO> getUserDetails(int userid) {
		List<OttuserDTO> lst = new ArrayList<>();
		OttuserDTO obj = ottuserinfoRepository.getUserDetails(userid);
		lst.add(obj);

		return lst;

	}

	@Override
	public ResponseEntity<?> setOttPermission(String role, String username, String sts, String val) {

		Boolean value = Boolean.parseBoolean(val);
		String result = "Disabled";

		if (value) {
			result = "Enabled";
		}

		if (role.equalsIgnoreCase("ADMIN")) {
			Operators opobj = operatorsRepository.findByUsername(username);
			if (opobj != null) {
				if (sts.equalsIgnoreCase("OTT")) {
					operator_permissionRepository.setOttPermission(opobj.getId(), value);

				} else if (sts.equalsIgnoreCase("IPTV")) {
					operator_permissionRepository.setVodPermission(opobj.getId(), value);

				} else if (sts.equalsIgnoreCase("SMS")) {
					operator_permissionRepository.setSmsPermission(opobj.getId(), value);

				} else if (sts.equalsIgnoreCase("EMAIL")) {
					operator_permissionRepository.setEmailPermission(opobj.getId(), value);

				}
				return ResponseEntity.ok(new MessageResponse(sts.toUpperCase() + " Successfully " + result + " for "
						+ opobj.getFirstname() + " " + opobj.getLastname()));

			}
		}

		try {
			Userinfo uobj = userinfoRepository.findByUsername(username);
			if (uobj != null) {
				if (sts.equalsIgnoreCase("OTT")) {

					boolean sts1 = ottuserinfoRepository.existsByUsername(username);

					if (sts1) {
						ottuserinfoRepository.setOttUserPermission(username, value);

						OttApi.userActiveDeactive(username, value, uobj);

					} else {

						if (value) {
							Ottuserinfo ouiobj = new Ottuserinfo();
							ouiobj.setUsername(uobj.getUsername());
							ouiobj.setEmail(uobj.getEmail());
							ouiobj.setMobilephone(uobj.getMobilephone());
							ouiobj.setCreationdate(Dateformat.getCurrentDatetime());
							ouiobj.setIsactive(true);

							ottuserinfoRepository.save(ouiobj);
							try {
								uobj.setCreationdate(Dateformat.getCurrentDatetime());
								OttApi.userCreationOtt(uobj);

							} catch (Exception e) {
								System.out.println("Exception at calling ott usercreation api :" + e);
							}
						} else {
							return ResponseEntity.badRequest()
									.body(new MessageResponse("Not Enabled OTT permission for this user!!"));
						}
					}

					return ResponseEntity.ok(new MessageResponse(sts.toUpperCase() + " Successfully " + result + " for "
							+ uobj.getFirstname() + " " + uobj.getLastname()));

				} else if (sts.equalsIgnoreCase("IPTV")) {

					uobj.setIsiptv(value);
					uobj.setUpdatedate(Dateformat.getCurrentDatetime());
					userinfoRepository.save(uobj);

//					IptvApi.SubscriberCreation(uobj, value);

					return ResponseEntity.ok(new MessageResponse(sts.toUpperCase() + " Successfully " + result + " for "
							+ uobj.getFirstname() + " " + uobj.getLastname()));

				}
			}
		} catch (Exception e) {
			System.out.println("Exception at:" + e);
		}

		return null;
	}

	@Override
	public Map<String, List> getOperatorBasedUserlist(String username) {

		Operators opobj = operatorsRepository.findByUsername(username);

		Map map1 = new HashMap<>();
		List lst = new ArrayList();

		if (opobj != null) {

			List<Userinfo> userlist = userinfoRepository.findByOpid(opobj.getId());

			for (Userinfo u : userlist) {
				Map map = new HashMap<>();
				map.put("name", u.getFirstname() + " " + u.getLastname());
				map.put("username", u.getUsername());
				map.put("id", u.getId());
				lst.add(map);

			}

			map1.put("userlist", lst);

			return map1;
		}

		return null;
	}

	@Override
	public ResponseEntity<?> changeOperatoryForUser(Map<String, ?> obj) {

		try {

			if (!obj.get("username").equals(" ") && obj.get("username") != null) {

				Operators opobj = operatorsRepository.findByUsername(obj.get("username").toString());

				if (opobj != null) {

					String user = obj.get("userid").toString().replace("[", "").replace("]", "").replace(" ", "");
//
					String[] userlist = user.split(",");

					Map<String, Long> map = Arrays.stream(userlist)
							.collect(Collectors.groupingBy(s -> s, Collectors.counting()));

					System.out.println(map);

					for (Map.Entry<String, Long> entry : map.entrySet()) {

						if (entry.getValue() % 2 == 0) {

						} else {
							Userinfo userobj = userinfoRepository.findById(Integer.parseInt(entry.getKey().trim()));

							if (userobj != null) {

								Operator_change_history opchangeobj = new Operator_change_history();
								opchangeobj.setUsername(userobj.getUsername());
								opchangeobj.setOldopid(userobj.getOpid());
								opchangeobj.setNewopid(opobj.getId());
								opchangeobj.setRemarks(obj.get("remark").toString());
								opchangeobj.setCreationdate(Dateformat.getCurrentDatetime());
								opchangeobj.setCreationby("admin");
								operator_change_historyRepository.save(opchangeobj);

								userobj.setOpid(opobj.getId());
								userobj.setUpdatedate(Dateformat.getCurrentDatetime());
								userinfoRepository.save(userobj);

							} else {
								System.out.println("else part working");
							}

						}

					}

					return ResponseEntity.ok(new MessageResponse("Success!"));
				}

			} else {
				return ResponseEntity.badRequest().body(new MessageResponse("Choose atleast one operator!"));
			}

		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new MessageResponse("Something went wrong!"));
		}
		return null;

	}

	@Override
	public Map<String, List> getUsernamelist(String username, String role, String subject) {
		List<Userinfo> userlist = new ArrayList<Userinfo>();
		Map map1 = new HashMap<>();
		List lst = new ArrayList();

		if (role.equalsIgnoreCase("ADMIN")) {

		} else if (role.equalsIgnoreCase("OPERATOR")) {

			if (username != null && !username.equals("")) {

				if (subject.contains("new")) {
					userlist = userinfoRepository.getNewActiveUserForSMS(username);

				} else if (subject.contains("expired")) {
					userlist = userinfoRepository.getExpiredUserForSMS(username);

				} else if (subject.contains("expirysoon")) {
					userlist = userinfoRepository.getExpiringUserForSMS(username);

				} else if (subject.contains("renewal")) {
					userlist = userinfoRepository.getRenewalUserForSMS(username);

				} else if (subject.contains("self")) {
					userlist = userinfoRepository.getAllUserForSMS(username);
				}

			}

		}

		for (Userinfo u : userlist) {
			Map map = new HashMap<>();
			map.put("name", u.getFirstname());
			map.put("username", u.getUsername());
			map.put("id", u.getId());
			lst.add(map);

		}

		map1.put("userlist", lst);

		return map1;

	}

	@Override
	public String changePasswordforOtt(String oldmobile, String newmobile) {

		if (!oldmobile.equals("") && oldmobile != null && !newmobile.equals("") && newmobile != null) {

			System.out.println(oldmobile);
			Userinfo userobj = userinfoRepository.findByMobilephone(oldmobile);
			Ottuserinfo ottuser = ottuserinfoRepository.findByUsername(userobj.getUsername());

			System.out.println(userobj.getMobilephone() + "  " + userobj.getUsername());
			if (userobj != null) {
				System.out.println(userobj.getUsername());
				if ((userinfoRepository.checkMobilenumber(userobj.getUsername(), newmobile) > 0)) {
					return ("The mobile number is already in use!");

				}

				try {
					// update users table
					Optional<User> loginobj = userRepository.findByUsername(userobj.getUsername());

					loginobj.get().setEmail(userobj.getEmail());
					loginobj.get().setPassword(encoder.encode(newmobile));
					userRepository.save(loginobj.get());

					// update userinfo
					userobj.setMobilephone(newmobile);
					userinfoRepository.save(userobj);

					// update ottuserinfo
					ottuser.setMobilephone(newmobile);
					ottuser.setUpdatedate(Dateformat.getCurrentDatetime());
					ottuserinfoRepository.save(ottuser);

					String response = OttApi.forgotPassword(newmobile, oldmobile);

					if (response.contains("success")) {
						return ("Success!");
					} else {
						return ("Failed!");
					}

				} catch (Exception e) {

					return ("something went wrong!");
				}

			} else {
				return ("Check your input!");
			}
		} else {
			return ("Check your input!");
		}

	}

	@Override
	public List<CreateuserRequest> editUserdetails(@Valid List<CreatebulkuserRequest> requestobject) {

		List<CreateuserRequest> user_list = new ArrayList<>();

		for (CreatebulkuserRequest obj : requestobject) {

			CreateuserRequest obj1 = new CreateuserRequest();

			List<Radusergroup> radlist = new ArrayList();
			List<CreateAttributesRequest> attributelist = new ArrayList();
			Userinfo userobj = new Userinfo();
			Userbillinfo userbillobj = new Userbillinfo();
			CreateAttributesRequest attributeobj = new CreateAttributesRequest();

			userobj.setUsername(obj.getUsername());
			userobj.setAddress(obj.getBill_Address());
			userobj.setFirstname(obj.getFirst_Name());
			userobj.setLastname(obj.getLast_Name());
			userobj.setEmail(obj.getEmail());
			userobj.setMobilephone(obj.getMobile());
			userobj.setDepartment("");
			userobj.setCompany("");
			userobj.setWorkphone(obj.getWork_Phone());
			userobj.setCountry(obj.getCountry());
			userobj.setCity(obj.getCity());
			userobj.setState(obj.getState());
			userobj.setZip(obj.getZipCode());
			userobj.setVlanid(obj.getVlan_Id());

			attributeobj.setAttribute("Simultaneous-Use");
			attributeobj.setOp(":=");
			attributeobj.setTable("check");
			attributeobj.setUsername(obj.getUsername());
			attributeobj.setValue("1");
			attributelist.add(attributeobj);

			userbillobj.setAddress(obj.getBill_Address());
			userbillobj.setCity(obj.getCity());
			userbillobj.setState(obj.getState());
			userbillobj.setZip(obj.getZipCode());

			obj1.setRole("ADMIN");
			obj1.setUsername(obj.getUsername());
			obj1.setPassword(obj.getPassword());
			obj1.setAuthtype("userAuth");
			obj1.setFromip("");
			obj1.setFramedipaddress("");
			obj1.setIptype("");
			obj1.setOpusername(obj.getOp_username());
			obj1.setRadusergroups(radlist);
			obj1.setUserinfo(userobj);
			obj1.setUserbillinfo(userbillobj);
			obj1.setAutoip(true);
			obj1.setAttributes(attributelist);

			if (obj.getIS_OTT_IPTV_BOTH() != null && !obj.getIS_OTT_IPTV_BOTH().equals("")) {
				if (obj.getIS_OTT_IPTV_BOTH().equalsIgnoreCase("OTT")) {

					obj1.setSelectIsOtt(true);

				} else if (obj.getIS_OTT_IPTV_BOTH().equalsIgnoreCase("IPTV")) {

					obj1.setSelectIsIptv(true);

				} else if (obj.getIS_OTT_IPTV_BOTH().equalsIgnoreCase("BOTH")) {

					obj1.setSelectIsOtt(true);
					obj1.setSelectIsIptv(true);
				}
			} else {
				obj1.setSelectIsOtt(false);
				obj1.setSelectIsIptv(false);
			}
			user_list.add(obj1);
		}
		return user_list;
	}

	@Override
	public ResponseEntity<?> createBulkUser(@Valid List<CreateuserRequest> userdetailobject) {

		Set<String> special_character_error = new HashSet<>();
		Set<String> username_length_error = new HashSet<>();
		Set<String> password_length_error = new HashSet<>();
		Set<String> not_authorized_operator_error = new HashSet<>();
		Set<String> empty_authtype_error = new HashSet<>();
		Set<String> duplicate_username_error = new HashSet<>();
		Set<String> duplicate_Mobilephone_error = new HashSet<>();
		Set<String> duplicate_Workphone_error = new HashSet<>();
		Set<String> duplicate_email_error = new HashSet<>();
		Set<String> duplicate_ip_error = new HashSet<>();
		Set<String> invalid_authtype_error = new HashSet<>();
		List<String> user_name_list = new ArrayList();

		boolean details_ok = true;

		for (CreateuserRequest obj : userdetailobject) {

			String users_name = (obj.getUserinfo().getFirstname()) + " " + (obj.getUserinfo().getLastname()) + '('
					+ obj.getUsername() + ')';
			user_name_list.add(users_name);

			if (obj.getUsername() == "" || obj.getPassword() == "") {
				return ResponseEntity.badRequest().body(new MessageResponse("username or password are empty"));
			}

			boolean usernameverified = false;

			if (obj.getUsername().length() >= 6) {// Username not should be with special character for JWT AuthToken
													// Generation
				Pattern special = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
				Matcher hasSpecial = special.matcher(obj.getUsername());
				usernameverified = hasSpecial.find();// If present any special character assign 'true'
				if (usernameverified) {// If present any special character with username
					special_character_error.add(users_name);
					details_ok = false;
				}
			} else {
				username_length_error.add(users_name);
				details_ok = false;
			}
			if (obj.getPassword().length() < 6) {
				password_length_error.add(users_name);
				details_ok = false;
			} else if (obj.getOpusername() == "") {
				not_authorized_operator_error.add(users_name);
				details_ok = false;
			} else if (!operatorsRepository.existsByUsername(obj.getOpusername())) {
				not_authorized_operator_error.add(users_name);
				details_ok = false;
			} else if (obj.getAuthtype() == "") {
				empty_authtype_error.add(users_name);
				details_ok = false;
			} else if (radcheckRepository.existsByUsername(obj.getUsername())) {
				duplicate_username_error.add(users_name);
				details_ok = false;
			} else if (obj.getUserinfo().getMobilephone() != ""
					&& userinfoRepository.existsByMobilephone(obj.getUserinfo().getMobilephone())) {
				duplicate_Mobilephone_error.add(users_name);
				details_ok = false;
			} else if (obj.getUserinfo().getWorkphone() != ""
					&& userinfoRepository.existsByWorkphone(obj.getUserinfo().getWorkphone())) {
				duplicate_Workphone_error.add(users_name);
				details_ok = false;
			} else if (obj.getUserinfo().getEmail() != ""
					&& userinfoRepository.existsByEmail(obj.getUserinfo().getEmail())) {
				duplicate_email_error.add(users_name);
				details_ok = false;
			} else if (userRepository.existsByUsername(obj.getUsername())) {
				duplicate_username_error.add(users_name);
				details_ok = false;
			} else if (userRepository.existsByEmail(obj.getUserinfo().getEmail())) {
				duplicate_email_error.add(users_name);
				details_ok = false;
			} else if (!obj.isAutoip()) {
				if (radippoolRepository.getUsernameByFromAndFramedIp(obj.getFromip(),
						obj.getFramedipaddress()) != null) {
					duplicate_ip_error.add(users_name);
					details_ok = false;
				}
			} else if (userinfoRepository.existsByUsername(obj.getUserinfo().getUsername())) {
				duplicate_username_error.add(users_name);
				details_ok = false;
			} else if (userbillinfoRepository.existsByUsername(obj.getUserbillinfo().getUsername())) {
				duplicate_username_error.add(users_name);
				details_ok = false;
			} else if (!obj.getAuthtype().equalsIgnoreCase("userAuth")) {
				invalid_authtype_error.add(obj.getUsername());
				details_ok = false;
			}
		}

		if (!details_ok) {
			List<Map> user_create_error_list = new ArrayList<>();
			Map map = new HashMap();

//		   for(String userobj:user_name_list) {
//			   
//			   List<String> Error_list = new ArrayList<>();
//			   
//	             if(special_character_error.contains(userobj)) {
//			    	 Error_list.add("Special character does not allowed in username column");
//			       }
//		         if(username_length_error.contains(userobj)) {
//		        	 Error_list.add("Username length must be  in 6 to 10 characters");
//				   }
//				 if(password_length_error.contains(userobj)) {
//					 Error_list.add("Password length must be  in 6 to 10 characters");
//				   }
//				 if(not_authorized_operator_error.contains(userobj)) {
//					 Error_list.add("Invalid operator username");
//				   }
//				 if(empty_authtype_error.contains(userobj)) {
//					 Error_list.add("Authtype is empty");
//				   }
//				 if(duplicate_username_error.contains(userobj)) {
//					 Error_list.add("Username is already exist in our database");
//				   }
//				 if(duplicate_Mobilephone_error.contains(userobj)) {
//					 Error_list.add("Mobilenumber is already exist in our database");
//				   }
//				 if(duplicate_Workphone_error.contains(userobj)) {
//					 Error_list.add("Workphone is already exist in our database");
//				   }
//				 if(duplicate_email_error.contains(userobj)) {
//					 Error_list.add("Email is already exist in our database");
//				   }
//				 if(duplicate_ip_error.contains(userobj)) {
//					 Error_list.add("Ip is already exist in our database");
//				   }
//				 if(invalid_authtype_error.contains(userobj)) {
//					 Error_list.add("invalid authtype");
//				   }
//				 
//				 map.put("Name", userobj);
//				 map.put("Error", Error_list);
//				 user_create_error_list.add(map);
//			   
//		   }

			if (special_character_error.size() >= 1) {
				map.put("special_character_Error", special_character_error);
			}
			if (username_length_error.size() >= 1) {
				map.put("username_length_Error", username_length_error);
			}
			if (password_length_error.size() >= 1) {
				map.put("password_length_Error", password_length_error);
			}
			if (not_authorized_operator_error.size() >= 1) {
				map.put("not_authorized_operator_Error", not_authorized_operator_error);
			}
			if (empty_authtype_error.size() >= 1) {
				map.put("empty_authtype_Error", empty_authtype_error);
			}
			if (duplicate_username_error.size() >= 1) {
				map.put("duplicate_username_Error", duplicate_username_error);
			}
			if (duplicate_Mobilephone_error.size() >= 1) {
				map.put("duplicate_Mobilephone_Error", duplicate_Mobilephone_error);
			}
			if (duplicate_Workphone_error.size() >= 1) {
				map.put("duplicate_Workphone_Error", duplicate_Workphone_error);
			}
			if (duplicate_email_error.size() >= 1) {
				map.put("duplicate_email_Error", duplicate_email_error);
			}
			if (duplicate_ip_error.size() >= 1) {
				map.put("duplicate_ip_Error", duplicate_ip_error);
			}
			if (invalid_authtype_error.size() >= 1) {
				map.put("invalid_authtype_Error", invalid_authtype_error);
			}

			if (!map.isEmpty()) {
				user_create_error_list.add(map);
			}
			System.out.println("error_list=" + user_create_error_list);
			System.out.println("error_list_size=" + user_create_error_list.size());

			return ResponseEntity.ok(new MessageResponseForBulk(user_create_error_list));

		}

		if (details_ok) {

			for (CreateuserRequest obj : userdetailobject) {

				if (obj.getAuthtype().equalsIgnoreCase("userAuth")) {
					String result = userCreation(obj);

				}

			}
		}

		List<Map> success = new ArrayList<>();
		Map map1 = new HashMap();
		map1.put("responce", "New users created successfully!");
		success.add(map1);

		return ResponseEntity.ok(new MessageResponseForBulk(success));

	}

	@Override
	public Map getproofimage(String username) {

		List<Map> Image_List = new ArrayList();

		Clientinfo clientobj = clientinfoRepository.findOneClient();
		Userinfo userinfo = userinfoRepository.findByUsername(username);

		List<Userproof> image_list = userproofRepository.getUserProofImage(username);

		String proofimage = "";

		File file = new File(
				"/var/www/html/RCRMPACKAGE/user-proofimage/" + username + "/" + userinfo.getUserproofimage1());
		if (file.exists()) {
			proofimage = "http://" + clientobj.getLinkurl() + "/RCRMPACKAGE/user-proofimage/" + username + "/"
					+ userinfo.getUserproofimage1();

		} else {
			proofimage = "http://" + clientobj.getLinkurl() + "/RCRMPACKAGE/user-proofimage/defaultuserproof.png";

		}

		Map map = new HashMap();

		map.put("id", 0);
		map.put("image_url", proofimage);
		Image_List.add(map);

		for (Userproof obj : image_list) {

			map = new HashMap();
			String image = "";

			file = new File("/var/www/html/RCRMPACKAGE/user-proofimage/" + username + "/" + obj.getImageurl());
			if (file.exists()) {
				System.out.println("file avaialble");
				image = "http://" + clientobj.getLinkurl() + "/RCRMPACKAGE/user-proofimage/" + username + "/"
						+ obj.getImageurl();
			} else {
				System.out.println("file not avaialble");
				image = "http://" + clientobj.getLinkurl() + "/RCRMPACKAGE/user-proofimage/defaultuserproof.png";
			}
			long id = obj.getId();

			map.put("id", id);
			map.put("image_url", image);

			Image_List.add(map);
		}

		Map map2 = new HashMap();
		map2.put("recent_image", proofimage);
		map2.put("image", Image_List);

		return map2;
	}

	@Override
	public ResponseEntity<?> delete_image(int id) {

		Userproof upobj = userproofRepository.findById(id).get();

		try {
			File f = new File(
					"/var/www/html/RCRMPACKAGE/user-proofimage/" + upobj.getUsername() + "/" + upobj.getImageurl());

			if (f.exists()) {
				f.delete();
			} else {
				System.out.println("file not available" + f.getAbsolutePath());
			}

		} catch (Exception e) {
			System.out.println("Exception at file deleting!");
		}
		userproofRepository.deleteById(id);
		return ResponseEntity.ok(new MessageResponse("Image deleted successfully"));
	}

}
