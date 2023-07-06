package com.ridsys.rib.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ridsys.rib.DTO.UserListDTO;
import com.ridsys.rib.DTO.UserProfileInfoDTO;
import com.ridsys.rib.DTO.UserStatusDTO;
import com.ridsys.rib.comm.FileUploadUtil;
import com.ridsys.rib.payload.request.CreateProfileRequest;
import com.ridsys.rib.payload.request.CreatebulkuserRequest;
import com.ridsys.rib.payload.request.CreateuserRequest;
import com.ridsys.rib.payload.request.UserDeviceInfoUpdateRequest;
import com.ridsys.rib.payload.response.MessageResponse;
import com.ridsys.rib.repository.UserinfoRepository;
import com.ridsys.rib.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

	private UserService service;

	@Autowired
	UserinfoRepository userinfoRepository;

	public UserController(UserService service) {
		super();
		this.service = service;
	}

	@PostMapping("/create")
	public ResponseEntity<?> createUser(@Valid @RequestBody CreateuserRequest requestobject) {
		return service.createUser(requestobject);

	}

	@PostMapping(value = "/upload/proofimage", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<?> createUserNew(@RequestPart("username") String username,
			@RequestPart("file") MultipartFile file) throws IOException {

		if (username != null && !username.equals("")) {
			String fileName = StringUtils.cleanPath(file.getOriginalFilename());

//		String uploadDir = "/home/user/RCRMPACKAGE/user-proofimage/" + username;

			String uploadDir = "/var/www/html/RCRMPACKAGE/user-proofimage/" + username;

			FileUploadUtil.saveFile(uploadDir, fileName, file);

			return service.uploaduserimageproof(username, fileName);
		} else {
			return ResponseEntity.badRequest().body(new MessageResponse("Check Your Username!"));
		}

	}

	@PostMapping("/approval")
	public ResponseEntity<?> userApproval(@Valid @RequestBody Map<String, String> userinfo) {
		System.out.println("USERNAME: " + userinfo.get("username"));
		return service.userApproval(userinfo.get("username"));

	}

	@PostMapping("/updateUserDeviceInfo")
	public ResponseEntity<?> updateUserDeviceInfo(
			@Valid @RequestBody UserDeviceInfoUpdateRequest userDeviceInfoUpdateRequest) {

		return service.updateUserDeviceInfo(userDeviceInfoUpdateRequest);

	}

	@GetMapping("/list")
	public List<UserListDTO> getUserList(@Valid @RequestParam String username, @RequestParam String status,
			@RequestParam("fdate") String fdate, @RequestParam("tdate") String tdate) throws IOException {
		return service.getUserListWithCurrentStatus(username, status, fdate, tdate);

	}

	@PostMapping("/profile/create")
	public ResponseEntity<?> newProfile(@Valid @RequestBody CreateProfileRequest createProfileDTO) {
		return service.createUserProfile(createProfileDTO);

	}

	@GetMapping("/profileInfo")
	public UserProfileInfoDTO getUserInfo(@Valid @RequestParam("username") String username) {
		if (username != "") {
			return service.getUserInfoWithCurrentStatusByUsername(username);
		}
		return null;

	}

	@PostMapping("/editUser")
	public ResponseEntity<?> editUser(@Valid @RequestBody CreateuserRequest editobj) throws IOException {
		return service.editUserDetails(editobj);

	}

	@GetMapping("/userStatus")
	public List<UserStatusDTO> getUserStatus(@Valid @RequestParam("username") String username,
			@RequestParam("status") String status) {
		return service.getUserStatus(username, status);
	}

	@PostMapping("/userReject")
	public ResponseEntity<?> userReject(@Valid @RequestBody Map<String, String> obj) {
		return service.userReject(obj.get("username"));

	}

	@PostMapping("/setOttPermission")
	public ResponseEntity<?> setOttPermission(@Valid @RequestBody Map<String, String> obj) {
		return service.setOttPermission(obj.get("role"), obj.get("username"), obj.get("status"), obj.get("value"));
	}

	@GetMapping("/userlistByoperator")
	public Map<String, List> getOperatorBasedUserlist(@RequestParam("username") String username) {
		return service.getOperatorBasedUserlist(username);
	}

	@PostMapping("/operator/change")
	public ResponseEntity<?> changeOperatoryForUser(@RequestBody Map<String, ?> obj) {
		return service.changeOperatoryForUser(obj);
	}

	@GetMapping("/username/list")
	public Map<String, List> getUsernamelist(@RequestParam("username") String username,
			@RequestParam("role") String role, @RequestParam("subject") String subject) {
		return service.getUsernamelist(username, role, subject);
	}

	@PostMapping("/forgotpassword")
	public String changePasswordforOtt(@RequestParam("oldmobile") String oldmobile,
			@RequestParam("newmobile") String newmobile) {
		return service.changePasswordforOtt(oldmobile, newmobile);
	}

	@PostMapping("/createBulkUser")
	public ResponseEntity<?> edituserdetail(@Valid @RequestBody List<CreatebulkuserRequest> requestobject) {
		List<CreateuserRequest> userdetailobject = service.editUserdetails(requestobject);
		return service.createBulkUser(userdetailobject);
	}

	@GetMapping("/getproofimage")
	public Map getproofimage(@RequestParam("username") String username) {
		return service.getproofimage(username);
	}

	@DeleteMapping("/deleteimage")
	public ResponseEntity<?> deleteimage(@RequestParam("id") int id) {
		if (id != 0) {
			return service.delete_image(id);
		}else {
			return ResponseEntity.badRequest().body(new MessageResponse("Selected Image not Deletable!"));
		}
		
	}

}
