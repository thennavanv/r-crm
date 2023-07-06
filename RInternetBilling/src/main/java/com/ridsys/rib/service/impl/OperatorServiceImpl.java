package com.ridsys.rib.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ridsys.rib.DTO.SpinListDTO;
import com.ridsys.rib.DTO.OnlinepaymentDTO;
import com.ridsys.rib.DTO.OperatorDTO;
import com.ridsys.rib.DTO.OperatorUserCountDTO;
import com.ridsys.rib.DTO.OperatorWalletReportDTO;
import com.ridsys.rib.DTO.Operator_permissionDTO;
import com.ridsys.rib.comm.Dateformat;
import com.ridsys.rib.comm.FileUploadUtil;
import com.ridsys.rib.comm.IdGen;
import com.ridsys.rib.comm.IptvApi;
import com.ridsys.rib.models.Adds;
import com.ridsys.rib.models.Clientinfo;
import com.ridsys.rib.models.ERole;
import com.ridsys.rib.models.Operators;
import com.ridsys.rib.models.Operatorvlan;
import com.ridsys.rib.models.Quotaplan;
import com.ridsys.rib.models.Role;
import com.ridsys.rib.models.User;
import com.ridsys.rib.models.Operator_permission;
import com.ridsys.rib.models.Operator_switchport;
import com.ridsys.rib.models.Operatorplanpermission;
import com.ridsys.rib.payload.request.CreateOperatorRequest;
import com.ridsys.rib.payload.response.MessageResponse;
import com.ridsys.rib.payload.response.MessageResponseForBulk;
import com.ridsys.rib.repository.OperatorsRepository;
import com.ridsys.rib.repository.OperatorvlanRepository;
import com.ridsys.rib.repository.QuotaplanRepository;
import com.ridsys.rib.repository.RoleRepository;
import com.ridsys.rib.repository.UserRepository;
import com.ridsys.rib.repository.AddsRepository;
import com.ridsys.rib.repository.ClientinfoRepository;
import com.ridsys.rib.repository.Operator_permissionRepository;
import com.ridsys.rib.repository.Operator_switchportRepository;
import com.ridsys.rib.repository.OperatorplanpermissionRepository;
import com.ridsys.rib.service.OperatorsService;

@Service
public class OperatorServiceImpl implements OperatorsService {

	@Autowired
	OperatorsRepository operatorsRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	Operator_permissionRepository operator_permissionRepository;

	@Autowired
	OperatorvlanRepository operatorvlanRepository;

	@Autowired
	QuotaplanRepository quotaplanRepository;

	@Autowired
	OperatorplanpermissionRepository operatorplanpermissionRepository;

	@Autowired
	Operator_switchportRepository operator_switchportRepository;

	@Autowired
	AddsRepository addsRepository;

	@Autowired
	ClientinfoRepository clientinfoRepository;

	@Override
	public ResponseEntity<?> createOperators(CreateOperatorRequest reqobj) {

		if (reqobj.getUsername() == "" || reqobj.getPassword() == "") {
			return ResponseEntity.badRequest().body(new MessageResponse("username or password are empty"));

		}
		if (reqobj.getUsername().length() < 3) {
			return ResponseEntity.badRequest().body(new MessageResponse("The username length should be 3 or more!."));

		}
		if (reqobj.getPassword().length() < 3) {
			return ResponseEntity.badRequest().body(new MessageResponse("The password length should be 3 or more!."));

		}
		if (operatorsRepository.existsByUsername(reqobj.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("The username is already in use!"));

		}
		if (operatorsRepository.existsByPhone1(reqobj.getPhone1())) {
			return ResponseEntity.badRequest().body(new MessageResponse("The mobile number is already in use!"));

		}
		if (operatorsRepository.existsByEmail1(reqobj.getEmail1())) {
			return ResponseEntity.badRequest().body(new MessageResponse("The mail Id is already in use!"));

		}

		if (userRepository.existsByUsername(reqobj.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(reqobj.getEmail1())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}
//		if (operatorvlanRepository.existsByVlanid(reqobj.getVlanid())) {
//			return ResponseEntity.badRequest().body(new MessageResponse("Vlan ID is already in use!"));
//
//		}
		if (operatorsRepository.existsByOperatorid(reqobj.getOperatorid())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Operator ID is  already in use!"));

		}

		operatorCreation(reqobj);

		return ResponseEntity.ok(new MessageResponse("New Operator created successfully!"));

	}

	public void operatorCreation(CreateOperatorRequest reqobj) {

		Operators obj = new Operators();

		String currentdatetime = Dateformat.getCurrentDatetime();
		obj.setUsername(reqobj.getUsername());
		obj.setPassword(reqobj.getPassword());
		obj.setFirstname(reqobj.getFirstname());
		obj.setLastname(reqobj.getLastname());
		obj.setArea(reqobj.getArea());
		obj.setAddress(reqobj.getAddress());
		obj.setState(reqobj.getState());
		obj.setCompany(reqobj.getCompany());
		obj.setEmail1(reqobj.getEmail1());
		obj.setDepartment(reqobj.getDepartment());
		obj.setNotes(reqobj.getNotes());
		obj.setOperatorid(reqobj.getOperatorid());
		obj.setVlanid(reqobj.getVlanid());
		obj.setTitle(reqobj.getTitle());
		obj.setPhone1(reqobj.getPhone1());
		obj.setCreationdate(currentdatetime);
		obj.setCreationby("admin");
		obj.setWalletbalance("0");
		obj.setIsactive(true);
		obj.setEmail2(" ");
		obj.setMessenger1(" ");
		obj.setMessenger2(" ");
		obj.setPhone2(" ");
		obj.setZipcode(reqobj.getZipcode());

		// insert operator
		operatorsRepository.save(obj);

		Operator_permission opObj = new Operator_permission();
		opObj.setOpid(obj.getId());
		opObj.setWalletrecharge(true);
		opObj.setIptv(reqobj.isSelectIsIptvOpr());
		operator_permissionRepository.save(opObj);

		Set<Role> roles = new HashSet<>();
		Role operatorRole = roleRepository.findByName(ERole.ROLE_OPERATOR)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		roles.add(operatorRole);

		// Create new user's account
		User user = new User(obj.getUsername(), obj.getEmail1(), encoder.encode(obj.getPassword()));
		user.setRoles(roles);
		userRepository.save(user);

		// adding operator vlan
		Operators OprObj = operatorsRepository.findByUsername(obj.getUsername());
		Operatorvlan vlanObj = new Operatorvlan();

		vlanObj.setVlanid(OprObj.getVlanid());
		vlanObj.setOpid(OprObj.getId());
		vlanObj.setCreationdate(Dateformat.getCurrentDatetime());
		vlanObj.setIsactive(true);
		vlanObj.setArea(reqobj.getLanarea());

		operatorvlanRepository.save(vlanObj);

		// adding operator switch details

		if (reqobj.isIswithoutRnet()) {

			Operator_switchport opswitch = new Operator_switchport();

			opswitch.setOpid(OprObj.getId());
			opswitch.setSwitchid(reqobj.getSwitchid());
			opswitch.setPortno(reqobj.getPortno());
			opswitch.setIsactive(true);
			opswitch.setCreationdate(Dateformat.getCurrentDatetime());

			operator_switchportRepository.save(opswitch);
		}

		// adding mainplan status

		List<Quotaplan> mainplanlist = quotaplanRepository.findAllByIsactiveTrue();
		for (Quotaplan qp : mainplanlist) {
			Operatorplanpermission permisobj = new Operatorplanpermission();
			permisobj.setOpid(OprObj.getId());
			permisobj.setPlanid(qp.getId());
			permisobj.setIsactive(true);
			permisobj.setCreationdate(Dateformat.getCurrentDatetime());
			operatorplanpermissionRepository.save(permisobj);
		}

		try {
			if (reqobj.isSelectIsIptvOpr()) {
//				IptvApi.OperatorCreation(obj);

			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	@Override
	public List<Operators> getAllOperatos() {
		return operatorsRepository.getAllOperator();
	}

	@Override
	public List<SpinListDTO> getSpinList() {
		return operatorsRepository.getForSpinList();
	}

	@Override
	public OperatorDTO getOperatorByid(String username) {
		return operatorsRepository.getAllOperatorwithCountsByOpid(username);

	}

	@Override
	public List<OperatorDTO> getAllOperatorwithCounts() {

//		operatorsRepository.sqlGroupbyQuery();
		return operatorsRepository.getAllOperatorwithCounts();
	}

	@Override
	public ResponseEntity<?> editOperators(OperatorDTO reqobj) {

		Operators opObj = new Operators();
		opObj = operatorsRepository.findByUsername(reqobj.getUsername());
		if (reqobj.getMobile() != ""
				&& (operatorsRepository.checkMobilenumber(reqobj.getUsername(), reqobj.getMobile()) > 0)) {
			return ResponseEntity.badRequest().body(new MessageResponse("The mobile number is already in use!"));

		} else if (reqobj.getEmail() != ""
				&& (operatorsRepository.checkEmailaddress(reqobj.getUsername(), reqobj.getEmail()) > 0)) {
			return ResponseEntity.badRequest().body(new MessageResponse("The email Id is already in use!"));

		} else if (reqobj.getOperatorid() != 0
				&& (operatorsRepository.checkOperatorId(reqobj.getUsername(), reqobj.getOperatorid()) > 0)) {
			return ResponseEntity.badRequest().body(new MessageResponse("The Operator Id is already in use!"));

		}

		if (!opObj.getPassword().equals(reqobj.getPassword()) || !opObj.getEmail1().equals(reqobj.getEmail())) {

			Optional<User> loginobj = userRepository.findByUsername(opObj.getUsername());

			loginobj.get().setEmail(reqobj.getEmail());
			loginobj.get().setPassword(encoder.encode(reqobj.getPassword()));
			userRepository.save(loginobj.get());

		}

		Operatorvlan opvlanobj = operatorvlanRepository.findByOpidAndVlanid(opObj.getId(), opObj.getVlanid());
		opvlanobj.setVlanid(reqobj.getVlan());
		opvlanobj.setUpdatedate(Dateformat.getCurrentDatetime());
		opvlanobj.setArea(reqobj.getArea());
		operatorvlanRepository.save(opvlanobj);

		opObj.setFirstname(reqobj.getFirstname());
		opObj.setLastname(reqobj.getLastname());
		opObj.setArea(reqobj.getArea());
		opObj.setAddress(reqobj.getAddress());
		opObj.setPhone1(reqobj.getMobile());
		opObj.setEmail1(reqobj.getEmail());
		opObj.setState(reqobj.getState());
		opObj.setVlanid(reqobj.getVlan());
		opObj.setPassword(reqobj.getPassword());
		opObj.setOperatorid(reqobj.getOperatorid());
		operatorsRepository.save(opObj);
		return ResponseEntity.ok(new MessageResponse("Operator Details Edited Successfully!"));
	}

	@Override
	public List<Operator_permissionDTO> getOperatorPremission() {
		return operatorsRepository.getOperatorPermission();

	}

	@Override
	public List<Map<String, Integer>> getOperatorVlanId(String username) {
		List<Map<String, Integer>> lst1 = new ArrayList<>();
		List<Integer> lst = operatorvlanRepository.getOperatorVlanId(username);

		for (Integer i : lst) {
			Map<String, Integer> map = new HashMap<>();
			map.put("id", i);
			lst1.add(map);
		}
		return lst1;
	}

	@Override
	public ResponseEntity<?> setOperatorVlan(int vlanid, int opid, String area) {
		Operatorvlan obj = new Operatorvlan();

		if (operatorvlanRepository.existsByVlanid(vlanid)) {
			return ResponseEntity.badRequest().body(new MessageResponse("Vlan ID  already in use!"));

		}
		obj.setVlanid(vlanid);
		obj.setOpid(opid);
		obj.setArea(area);
		obj.setCreationdate(Dateformat.getCurrentDatetime());
		obj.setIsactive(true);

		operatorvlanRepository.save(obj);
		return ResponseEntity.ok(new MessageResponse("Opeartor Vlan Successfully created!!"));
	}

	@Override
	public Map<String, Object> getAllPermissionByOp(String username) {

		Operators opobj = operatorsRepository.findByUsername(username);

		Operator_permission obj = operator_permissionRepository.findByOpid(opobj.getId());

		Map<String, Object> map = new HashMap<>();
		map.put("walletRecharge", obj.isWalletrecharge());
		map.put("subwallet", obj.isSubwallet());
		map.put("planchange", obj.isPlanchange());
		map.put("passwordchange", obj.isPasswordchange());
		map.put("billgenerate", obj.isBillgenerate());
		map.put("oponlinerecharge", obj.isOponlinerecharge());
		map.put("subonlinerecharge", obj.isSubonlinerecharge());

		return map;
	}

	@Override
	public ResponseEntity<?> setOperatorPermission(String username, String sts, Boolean value) {
		String result = "Disabled";

		if (value) {
			result = "Enabled";
		}

		Operators opobj = operatorsRepository.findByUsername(username);

		if (sts.equalsIgnoreCase("wallet Recharge")) {
			operator_permissionRepository.setWalletPermission(opobj.getId(), value);

		} else if (sts.equalsIgnoreCase("subscriber online recharge")) {
			operator_permissionRepository.setSubonlinerechargePermission(opobj.getId(), value);

		} else if (sts.equalsIgnoreCase("password change")) {
			operator_permissionRepository.setPasswordchangePermission(opobj.getId(), value);

		} else if (sts.equalsIgnoreCase("subscriber wallet")) {
			operator_permissionRepository.setsubWalletPermission(opobj.getId(), value);

		} else if (sts.equalsIgnoreCase("bill generate")) {
			operator_permissionRepository.setBillgeneratePermission(opobj.getId(), value);

		} else if (sts.equalsIgnoreCase("plan change")) {
			operator_permissionRepository.setPlanchangePermission(opobj.getId(), value);

		} else if (sts.equalsIgnoreCase("operator online recharge")) {
			operator_permissionRepository.setOponlinerechargePermission(opobj.getId(), value);

		}

		return ResponseEntity.ok(new MessageResponse(sts.toUpperCase() + " Successfully " + result + " for "
				+ opobj.getFirstname() + " " + opobj.getLastname()));
	}

	@Override
	public List<OperatorUserCountDTO> getOperatorUserCounts() {
		return operatorsRepository.getOperatorUserCounts();
	}

	@Override
	public List<OnlinepaymentDTO> getAllRechargeHistory(String fdate, String tdate, String username, String role) {
		if (!fdate.equalsIgnoreCase("0000-00-00") & !tdate.equalsIgnoreCase("0000-00-00")) {
			if (role.equalsIgnoreCase("OPERATOR")) {
				return operatorsRepository.getAllRechargeHistoryByOpidFtdate(username, fdate, tdate);
			} else {
				return operatorsRepository.getAllRechargeHistoryFtdate(fdate, tdate);
			}

		} else {
			if (role.equalsIgnoreCase("OPERATOR")) {
				return operatorsRepository.getAllRechargeHistoryByOpid(username);
			} else {
				return operatorsRepository.getAllRechargeHistory();
			}
		}
	}

	@Override
	public List<Operatorvlan> getVlanList(String username) {

		Operators opobj = operatorsRepository.findByUsername(username);

		if (opobj != null) {
			return operatorvlanRepository.findByOpid(opobj.getId());
		}
		return null;

	}

	@Override
	public List getVlanareaList() {

		List<String> arealist = operatorvlanRepository.getVlanareaList();

		List list = new ArrayList<>();

		for (String s : arealist) {
			Map map = new HashMap();

			map.put("area", s);
			map.put("id", s);
			list.add(map);

		}

		return list;

	}

	@Override
	public ResponseEntity<?> updateSwitch(int switchid, int portno, String username) {

		if (operator_switchportRepository.existsBySwitchidandPortno(switchid, portno) == 0) {
			if (username != null) {

				Operators obj = operatorsRepository.findByUsername(username);

				if (obj != null) {

					Operator_switchport switchobj = new Operator_switchport();

					switchobj.setOpid(obj.getId());
					switchobj.setPortno(portno);
					switchobj.setSwitchid(switchid);
					switchobj.setCreationdate(Dateformat.getCurrentDatetime());
					switchobj.setIsactive(true);

					operator_switchportRepository.save(switchobj);

					return ResponseEntity.ok(new MessageResponse("Successfully Updated!!"));

				}

			}
		} else {
			return ResponseEntity.badRequest().body(new MessageResponse("Port No already use for this switch!"));
		}

		return null;
	}

	@Override
	public List<OperatorWalletReportDTO> getOpWalletHistory(String username, String role, String fdate, String tdate) {

		if (!fdate.equals("0000-00-00") && !tdate.equals("0000-00-00")) {
			if (role.equalsIgnoreCase("ADMIN")) {
				return operatorsRepository.getAllOpWalletHistoryFtdate(fdate, tdate);

			} else if (role.equalsIgnoreCase("OPERATOR")) {
				return operatorsRepository.getOpWalletHistoryByUsernameFtdate(username, fdate, tdate);
			}

		} else {
			if (role.equalsIgnoreCase("ADMIN")) {
				return operatorsRepository.getAllOpWalletHistory();

			} else if (role.equalsIgnoreCase("OPERATOR")) {
				return operatorsRepository.getOpWalletHistoryByUsername(username);

			}
		}

		return null;
	}

	@Override
	public ResponseEntity<?> createBulkOperator(@Valid List<CreateOperatorRequest> requestobject) {

		Set<String> operator_username_length_error = new HashSet<>();
		Set<String> password_length_error = new HashSet<>();
		Set<String> duplicate_operator_username_error = new HashSet<>();
		Set<String> duplicate_Mobilephone_error = new HashSet<>();
		Set<String> duplicate_email_error = new HashSet<>();
//		Set<String> duplicate_users_username_error = new HashSet<>();
//		Set<String> duplicate_users_email_error = new HashSet<>();
		Set<String> duplicate_operator_id_error = new HashSet<>();
		List<String> operator_name_list = new ArrayList();

		boolean details_ok = true;

		for (CreateOperatorRequest reqobj : requestobject) {

			System.out.println("username1=" + reqobj.getUsername());

			String users_name = (reqobj.getFirstname()) + " " + (reqobj.getLastname()) + " [" + reqobj.getUsername()
					+ "]";
			operator_name_list.add(users_name);

			if (reqobj.getUsername() == "" || reqobj.getPassword() == "") {
				return ResponseEntity.badRequest().body(new MessageResponse("username or password are empty"));
			}
			if (reqobj.getUsername().length() < 3) {
				operator_username_length_error.add(users_name);
				details_ok = false;
			}
			if (reqobj.getPassword().length() < 3) {
				password_length_error.add(users_name);
				details_ok = false;
			}
			if (operatorsRepository.existsByUsername(reqobj.getUsername())) {
				duplicate_operator_username_error.add(users_name);
				details_ok = false;
			}
			if (operatorsRepository.existsByPhone1(reqobj.getPhone1())) {
				duplicate_Mobilephone_error.add(users_name);
				details_ok = false;
			}
			if (operatorsRepository.existsByEmail1(reqobj.getEmail1())) {
				duplicate_email_error.add(users_name);
				details_ok = false;
			}
			if (userRepository.existsByUsername(reqobj.getUsername())) {
				duplicate_operator_username_error.add(users_name);
				details_ok = false;
			}
			if (userRepository.existsByEmail(reqobj.getEmail1())) {
				duplicate_email_error.add(users_name);
				details_ok = false;
			}
//		if (operatorvlanRepository.existsByVlanid(reqobj.getVlanid())) {
//			return ResponseEntity.badRequest().body(new MessageResponse("Vlan ID is already in use!"));
//
//		   }
			if (operatorsRepository.existsByOperatorid(reqobj.getOperatorid())) {
				duplicate_operator_id_error.add(users_name);
				details_ok = false;
			}
		}

		if (!details_ok) {
			List<Map> operator_error_list = new ArrayList<>();

			Map map = new HashMap();

//	 	   for(String object:operator_name_list) {
//			   
//			   List<String> Error_list = new ArrayList<>();
//			   
//	             if(operator_username_length_error.contains(object)) {
//			    	 Error_list.add("Username length must be  in 6 to 10 characters");
//			       }
//		         if(password_length_error.contains(object)) {
//		        	 Error_list.add("Password length must be  in 6 to 10 characters");
//				   }
//				 if(duplicate_operator_username_error.contains(object)) {
//					 Error_list.add("Username is already exist in our database");
//				   }
//				 if(duplicate_Mobilephone_error.contains(object)) {
//					 Error_list.add("mobile number is already exist in our database");
//				   }
//				 if(duplicate_email_error.contains(object)) {
//					 Error_list.add("Email is already exist in our database");
//				   }
//				 if(duplicate_operator_id_error.contains(object)) {
//					 Error_list.add("Operator id is already exist in our database");
//				   }
			//
//				 map.put("Name", object);
//				 map.put("Error", Error_list);
//				 operator_error_list.add(map);
//			 }

			if (operator_username_length_error.size() >= 1) {
				map.put("operator_username_length_Error", operator_username_length_error);
			}
			if (password_length_error.size() >= 1) {
				map.put("password_length_Error", password_length_error);
			}
			if (duplicate_operator_username_error.size() >= 1) {
				map.put("duplicate_operator_username_Error", duplicate_operator_username_error);
			}
			if (duplicate_Mobilephone_error.size() >= 1) {
				map.put("duplicate_Mobilephone_Error", duplicate_Mobilephone_error);
			}
			if (duplicate_email_error.size() >= 1) {
				map.put("duplicate_email_Error", duplicate_email_error);
			}
//	          if(duplicate_users_username_Error.length() >= 1) {
//			map.put("duplicate_users_username_Error", duplicate_users_username_Error);
//	          }if(duplicate_users_email_Error.length() >= 1) {
//			map.put("duplicate_users_email_Error", duplicate_users_email_Error);
//	          }
			if (duplicate_operator_id_error.size() >= 1) {
				map.put("duplicate_operator_id_Error", duplicate_operator_id_error);
			}

			operator_error_list.add(map);

			System.out.println("error_list=" + operator_error_list);
			System.out.println("error_list_size=" + operator_error_list.size());

			return ResponseEntity.ok(new MessageResponseForBulk(operator_error_list));

		} else if (details_ok) {

			for (CreateOperatorRequest reqobj : requestobject) {

				operatorCreation(reqobj);

			}
		}
		List<Map> success = new ArrayList<>();
		Map map1 = new HashMap();
		map1.put("response", "New Operator created successfully!");
		success.add(map1);
		return ResponseEntity.ok(new MessageResponseForBulk(success));
	}

	@Override
	public ResponseEntity<?> addupload(String opusername, String filetype, MultipartFile file, String fileName) {

		if (opusername.equalsIgnoreCase(null)) {
			return ResponseEntity.ok(new MessageResponse("Operator username is null"));
		}

		try {
			if (filetype.equalsIgnoreCase("image")) {

				String uploadDir = "/var/www/html/RCRMPACKAGE/ad_image/" + opusername;

				FileUploadUtil.saveFile(uploadDir, fileName, file);

				upload_add(opusername, fileName, filetype);
			}
			if (filetype.equalsIgnoreCase("video")) {

				String uploadDir = "/var/www/html/RCRMPACKAGE/ad_video/" + opusername;

				FileUploadUtil.saveFile(uploadDir, fileName, file);

				upload_add(opusername, fileName, filetype);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return ResponseEntity.ok(new MessageResponse("Ad uploaded successfully"));
	}

	public void upload_add(String opusername, String fileName, String filetype) {

		Adds obj = new Adds();
		obj.setUsername(opusername);
		obj.setFile_name(fileName);
		obj.setIs_active(true);
		if (filetype.equalsIgnoreCase("image")) {
			obj.setIs_image(true);
		} else {
			obj.setIs_image(false);
		}
		obj.setCreated_date(Dateformat.getCurrentDatetime());
		addsRepository.save(obj);

	}

	@Override
	public List<Map> get_ads(String opusername, String filetype) {

		List<Map> ad_List = new ArrayList();
		List<Adds> list = new ArrayList();
		List<Map> image_list = new ArrayList();
		List<Map> video_list = new ArrayList();
		String ad_url = "";

		list = addsRepository.get_imageAd(opusername, filetype);

		Clientinfo client_obj = new Clientinfo();

		client_obj = clientinfoRepository.findOneClient();

		for (Adds obj : list) {
			Map map1 = new HashMap();

			String folder = "";
			if (!obj.isIs_image()) {
				folder = "ad_video";
			} else {
				folder = "ad_image";
			}
			ad_url = "http://" + client_obj.getLinkurl() + "/RCRMPACKAGE/" + folder + "/" + opusername + "/"
					+ obj.getFile_name();

			map1.put("id", obj.getId());
			map1.put("ad_url", ad_url);
			map1.put("updated_date", obj.getUpdated_date());
			if (!obj.isIs_image()) {
				video_list.add(map1);
			} else {
				image_list.add(map1);
			}

		}
		Map map = new HashMap();
		map.put("image_ad", image_list);
		map.put("video_ad", video_list);
		ad_List.add(map);

		return ad_List;
	}

	@Override
	public ResponseEntity<?> delete_adlist(List<Long> id) {
		List<Long> Failed_to_delete = new ArrayList();
		List<Long> File_does_not_exist = new ArrayList();
		String filePath = "";

		for (Long obj : id) {
			Adds ad_obj = addsRepository.findById(obj).get();

			if (!ad_obj.isIs_image()) {
				filePath = "/var/www/html/RCRMPACKAGE/ad_video/" + ad_obj.getUsername() + "/" + ad_obj.getFile_name();
			} else {
				filePath = "/var/www/html/RCRMPACKAGE/ad_image/" + ad_obj.getUsername() + "/" + ad_obj.getFile_name();
			}
			File file = new File(filePath);
			if (file.exists()) {
				if (file.delete()) {
					addsRepository.deleteById(obj);
				} else {
					Failed_to_delete.add(obj);
				}
			} else {
				File_does_not_exist.add(obj);
			}
		}
		if (Failed_to_delete.size() != 0) {
			return ResponseEntity.ok(new MessageResponse("Failed to delete the files " + Failed_to_delete));
		} else if (File_does_not_exist.size() != 0) {
			return ResponseEntity.ok(new MessageResponse("File does not exist " + File_does_not_exist));
		}
		return ResponseEntity.ok(new MessageResponse("Ad deleted successfully"));
	}

	@Override
	public ResponseEntity<?> delete_ad(Long id) {

		String filePath = "";

		Adds ad_obj = addsRepository.findById(id).get();

		if (!ad_obj.isIs_image()) {
			filePath = "/var/www/html/RCRMPACKAGE/ad_video/" + ad_obj.getUsername() + "/" + ad_obj.getFile_name();
		} else {
			filePath = "/var/www/html/RCRMPACKAGE/ad_image/" + ad_obj.getUsername() + "/" + ad_obj.getFile_name();
		}

		File file = new File(filePath);

		if (file.exists()) {
			if (file.delete()) {
				addsRepository.deleteById(id);
				return ResponseEntity.ok(new MessageResponse("Ad deleted successfully"));
			} else {
				return ResponseEntity.ok(new MessageResponse("Failed to delete"));
			}
		} else {
			return ResponseEntity.ok(new MessageResponse("File does not exist"));
		}
	}
}
