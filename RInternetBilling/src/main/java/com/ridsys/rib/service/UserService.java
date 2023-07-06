package com.ridsys.rib.service;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.ridsys.rib.DTO.UserListDTO;
import com.ridsys.rib.DTO.UserProfileInfoDTO;
import com.ridsys.rib.DTO.UserStatusDTO;
import com.ridsys.rib.payload.request.CreateProfileRequest;
import com.ridsys.rib.payload.request.CreatebulkuserRequest;
import com.ridsys.rib.payload.request.CreateuserRequest;
import com.ridsys.rib.payload.request.UserDeviceInfoUpdateRequest;

public interface UserService {
	ResponseEntity<?> createUser(CreateuserRequest obj);

	ResponseEntity<?> uploaduserimageproof(String username, String filename);

	List<UserListDTO> getUserListWithCurrentStatus(String username, String status,String fdate,String tdate);

	ResponseEntity<?> createUserProfile(CreateProfileRequest obj);

	UserProfileInfoDTO getUserInfoWithCurrentStatusByUsername(String username);
	
	ResponseEntity<?> userApproval(String username);
	
	ResponseEntity<?> updateUserDeviceInfo(UserDeviceInfoUpdateRequest userDeviceInfoUpdateRequest);
	
	ResponseEntity<?> editUserDetails(CreateuserRequest editobj);
	
	List<UserStatusDTO> getUserStatus(String username,String status);
	
	ResponseEntity<?> userReject(String username);
	
	String getOttPlanStatusByUser(int userid);
	
	List<?> checkUserAndroidID(String androidid);
	
	List<?> getUserDetails(int userid);
	
	ResponseEntity<?> setOttPermission(String role,String username,String sts,String val);
	
	Map<String,List> getOperatorBasedUserlist(String username);
	
	ResponseEntity<?> changeOperatoryForUser(Map<String,?> obj);
	
	Map<String, List> getUsernamelist(String username ,String role,String subject);
	
	String changePasswordforOtt(String oldmobile,String newmobile);
	
	List<CreateuserRequest> editUserdetails(@Valid List<CreatebulkuserRequest> requestobject);
	
	ResponseEntity<?> createBulkUser(@Valid List<CreateuserRequest> userdetailobject);
	
	Map getproofimage(String username);

	ResponseEntity<?> delete_image(int id);
	
}
