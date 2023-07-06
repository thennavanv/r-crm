package com.ridsys.rib.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ridsys.rib.models.Nas;
import com.ridsys.rib.models.Radippool;
import com.ridsys.rib.models.Switch;
import com.ridsys.rib.payload.request.CreateSwitchRequest;
import com.ridsys.rib.payload.request.IppoolPayload;
import com.ridsys.rib.repository.RadippoolRepository;
import com.ridsys.rib.repository.SwitchRepository;
import com.ridsys.rib.service.NetworkingService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/network")
public class NetworkingController {

	@Autowired
	private NetworkingService service;

	@Autowired
	private SwitchRepository switchRepository;

	@Autowired
	private RadippoolRepository radippoolRepository;

	@PostMapping("/nas/{action}")
	private ResponseEntity<?> nasModification(@Valid @RequestBody Nas nasObj, @PathVariable String action) {
		return service.nasModification(nasObj, action);
	}

	@GetMapping("/nas/list")
	private List<Nas> getNasList() {
		return service.getNasList();
	}

	@DeleteMapping("/nas/delete")
	private ResponseEntity<?> nasDelete(@Valid @RequestParam("id") long nasid) {
		return service.nasDelete(nasid);
	}

	@GetMapping("/ipcalc")
	private Map<String, String> ipCalculation(@RequestParam("address") String address,
			@RequestParam("netmask") String netmask) {
		return service.ipCalculation(address, netmask);
	}

	@PostMapping("/ippool/{action}")
	private ResponseEntity<?> ippoolModification(@Valid @RequestBody IppoolPayload obj, @PathVariable String action) {
		return service.ippoolModification(obj, action);
	}

	@GetMapping("/ippool/list")
	private List<Radippool> getIppoolList() {
		return radippoolRepository.getIppoolList();
	}

	@DeleteMapping("/ippool/delete")
	private ResponseEntity<?> ippoolDelete(@Valid @RequestParam("fromip") String fromip,
			@RequestParam("toip") String toip) {
		return service.ippoolDelete(fromip, toip);
	}

	@GetMapping("/detail/{about}")
	private List getNasDetail(@PathVariable String about) {
		return service.getNetworkDetails(about);
	}

	@PostMapping("/switch/create")
	private ResponseEntity<?> switchModification(@Valid @RequestBody CreateSwitchRequest obj) {
		return service.switchModification(obj);
	}

	@GetMapping("/switch/list")
	private List<Switch> getSwitchlist() {
		return switchRepository.getSwitchlist();
	}

	@GetMapping("/switch/listbyid")
	private Switch getSwitchById(@RequestParam("id") int id) {
		return switchRepository.findById(id);
	}

	@PostMapping("/switch/update")
	private ResponseEntity<?> editSwitch(@RequestBody Switch obj) {
		return service.editSwitch(obj);
	}

	@DeleteMapping("/switch/delete")
	private ResponseEntity<?> switchDelete(@Valid @RequestParam("id") int switchid) {
		return service.switchDelete(switchid);
	}

	@GetMapping("/getLanPort")
	public Map<String, List> getLanPort(@RequestParam("switchid") int swichid) {
		return service.getLanPort(swichid);
	}

	@GetMapping("/ippool/iplist")
	private List getIpList(@RequestParam("fromip") String fromip) {
		return service.getIpList(fromip);
	}

	@PostMapping("/ippool/add")
	private ResponseEntity<?> setIppoolForUser(@RequestBody Map<String, String> obj) {
		return service.setIppoolForUser(obj);
	}

	@PostMapping("/create/{table}")
	private ResponseEntity<?> insertTables(@RequestBody Map<String, String> obj, @PathVariable String table) {
		return service.insertTables(obj, table);
	}
}
