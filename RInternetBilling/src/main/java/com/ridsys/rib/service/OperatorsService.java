package com.ridsys.rib.service;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.ridsys.rib.DTO.SpinListDTO;
import com.ridsys.rib.DTO.OnlinepaymentDTO;
import com.ridsys.rib.DTO.OperatorDTO;
import com.ridsys.rib.DTO.OperatorUserCountDTO;
import com.ridsys.rib.DTO.OperatorWalletReportDTO;
import com.ridsys.rib.DTO.Operator_permissionDTO;
import com.ridsys.rib.models.Operators;
import com.ridsys.rib.models.Operatorvlan;
import com.ridsys.rib.payload.request.CreateOperatorRequest;

public interface OperatorsService {

	ResponseEntity<?> createOperators(CreateOperatorRequest obj);

	List<Operators> getAllOperatos();

	List<SpinListDTO> getSpinList();

	List<OperatorDTO> getAllOperatorwithCounts();

	OperatorDTO getOperatorByid(String username);

	ResponseEntity<?> editOperators(OperatorDTO editobj);

	List<Operator_permissionDTO> getOperatorPremission();

	List<?> getOperatorVlanId(String username);

//	ResponseEntity<?> setOperatorVlan(int vlanid,int opid);

	ResponseEntity<?> setOperatorVlan(int vlanid, int opid, String area);

	Map<?, ?> getAllPermissionByOp(String username);

	ResponseEntity<?> setOperatorPermission(String username, String status, Boolean value);

	List<OperatorUserCountDTO> getOperatorUserCounts();

	List<OnlinepaymentDTO> getAllRechargeHistory(String fdate, String tdate, String username, String role);

	List<Operatorvlan> getVlanList(String username);

	List getVlanareaList();

	ResponseEntity<?> updateSwitch(int switchid, int portno, String username);

	List<OperatorWalletReportDTO> getOpWalletHistory(String username, String role, String fdate, String tdate);

	ResponseEntity<?> createBulkOperator(@Valid List<CreateOperatorRequest> requestobject);

	ResponseEntity<?> addupload(String opusername, String filetype, MultipartFile file, String fileName);
	
	List<Map> get_ads(String opusername, String filetype);
	
	ResponseEntity<?> delete_adlist(List<Long> id);

	ResponseEntity<?> delete_ad(Long id);

}
