package com.ridsys.rib.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

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

import com.ridsys.rib.DTO.SpinListDTO;
import com.ridsys.rib.DTO.OnlinepaymentDTO;
import com.ridsys.rib.DTO.OperatorDTO;
import com.ridsys.rib.DTO.OperatorUserCountDTO;
import com.ridsys.rib.DTO.OperatorWalletReportDTO;
import com.ridsys.rib.DTO.Operator_permissionDTO;
import com.ridsys.rib.models.Operators;
import com.ridsys.rib.models.Operatorvlan;
import com.ridsys.rib.payload.request.CreateOperatorRequest;
import com.ridsys.rib.service.OperatorsService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/operator")
public class OperatorController {

	private OperatorsService service;

	public OperatorController(OperatorsService service) {
		super();
		this.service = service;
	}

	@PostMapping("/create")
	public ResponseEntity<?> createOperator(@Valid @RequestBody CreateOperatorRequest requestobject) {
		return service.createOperators(requestobject);
	}

	@GetMapping("/list")
	public List<Operators> getOperatorList() {
		return service.getAllOperatos();
	}

	@GetMapping("/spinlist")
	public List<SpinListDTO> getSpinList() {
		return service.getSpinList();
	}

	@GetMapping("/getOperatorByid")
	public OperatorDTO getOperatorByid(@Valid @RequestParam("username") String username) {
		return service.getOperatorByid(username);
	}

	@GetMapping("/operatorList")
	public List<OperatorDTO> getAllOperatorwithCounts() {
		return service.getAllOperatorwithCounts();
	}

	@PostMapping("/editOperator")
	public ResponseEntity<?> editOperator(@Valid @RequestBody OperatorDTO editobj) throws IOException {
		return service.editOperators(editobj);

	}

	@GetMapping("/operatorPermission")
	public List<Operator_permissionDTO> getOperatorPermission() {
		return service.getOperatorPremission();
	}

	@GetMapping("/operatorVlan")
	public List<?> getOperatorVlanId(@Valid @RequestParam("username") String username) {
		return service.getOperatorVlanId(username);
	}

	@PostMapping("/setVlan")
	public ResponseEntity<?> setOperatorVlan(@Valid @RequestBody Map<String, String> obj) {
//		return service.setOperatorVlan(Integer.parseInt(obj.get("vlanid")), Integer.parseInt(obj.get("opid")));
		return service.setOperatorVlan(Integer.parseInt(obj.get("vlanid")), Integer.parseInt(obj.get("opid")),
				obj.get("area"));

	}

	@GetMapping("/permissions")
	public Map<?, ?> getAllPermissionByOp(@Valid @RequestParam("username") String username) {
		return service.getAllPermissionByOp(username);
	}

	@PostMapping("/setOperatorPermission")
	public ResponseEntity<?> setOperatorPermission(@Valid @RequestBody Map<String, String> obj) {
		return service.setOperatorPermission(obj.get("username"), obj.get("status"),
				Boolean.parseBoolean(obj.get("value")));
	}

	@GetMapping("/userCounts")
	public List<OperatorUserCountDTO> getOperatorUserCounts() {
		return service.getOperatorUserCounts();
	}

	@GetMapping("/recharge/history")
	public List<OnlinepaymentDTO> getAllRechargeHistory(@RequestParam("username") String username,
			@RequestParam("role") String role, @RequestParam("fdate") String fdate,
			@RequestParam("tdate") String tdate) {
		return service.getAllRechargeHistory(fdate, tdate, username, role);
	}

	@GetMapping("/vlanlist")
	public List<Operatorvlan> getVlanList(@RequestParam("username") String username) {
		return service.getVlanList(username);
	}

	@GetMapping("/vlan/arealist")
	private List getVlanareaList() {
		return service.getVlanareaList();
	}

	@GetMapping("/switchUpdate")
	private ResponseEntity<?> updateSwitch(@Valid @RequestParam("switchid") String switchid,
			@RequestParam("portno") String portno, @RequestParam("username") String username) {
		return service.updateSwitch(Integer.parseInt(switchid), Integer.parseInt(portno), username);
	}

	@GetMapping("/walletHistory")
	private List<OperatorWalletReportDTO> getOpWalletHistory(@RequestParam("username") String username,
			@RequestParam("role") String role, @RequestParam("fdate") String fdate,
			@RequestParam("tdate") String tdate) {

		return service.getOpWalletHistory(username, role, fdate, tdate);

	}

	@PostMapping("/createBulkOperator")
	public ResponseEntity<?> createBulkOperator(@Valid @RequestBody List<CreateOperatorRequest> requestobject) {
		return service.createBulkOperator(requestobject);
	}

	@PostMapping(value = "adupload", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<?> addupload(@RequestParam("opusername") String opusername,
			@RequestPart("filetype") String filetype, @RequestPart("file") MultipartFile file) throws IOException {
		// long fileSizeBytes = file.getSize();
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		System.out.println(file.getOriginalFilename()+"        "+fileName);
		return service.addupload(opusername, filetype, file, fileName);
	}

	@GetMapping("/getads")
	public List<Map> get_ads(@RequestParam("opusername") String opusername, @RequestParam("filetype") String filetype) {
		return service.get_ads(opusername, filetype);
	}

	@DeleteMapping("deleteadlist")
	public ResponseEntity<?> delete_adlist(@RequestBody List<Integer> id) {
		return service.delete_adlist(id);
	}

	@DeleteMapping("deletead")
	public ResponseEntity<?> delete_ad(@RequestParam("id") Integer id) {
		return service.delete_ad(id);
	}

}
