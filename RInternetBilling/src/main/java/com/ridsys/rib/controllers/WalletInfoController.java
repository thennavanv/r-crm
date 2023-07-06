package com.ridsys.rib.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ridsys.rib.DTO.WalletInfoDTO;
import com.ridsys.rib.DTO.WalletupdatelogDTO;
import com.ridsys.rib.service.WalletInfoService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/walletinfo")
public class WalletInfoController {

	@Autowired
	WalletInfoService service;

	@GetMapping("/balance")
	public WalletInfoDTO getGetBalanceWithRecentHistory(@RequestParam String role, @RequestParam String username) {
		if (username != "" && role != "") {
			return service.getRecentOnlineWalletUpdateHistory(username, role);

		}
		return null;

	}

	@GetMapping("/list")
	public List<WalletupdatelogDTO> getWalletupdatelog(@RequestParam("role") String role,
			@RequestParam("username") String username, @RequestParam("fdate") String fdate,
			@RequestParam("tdate") String tdate) {

		if (username != "" && role != "") {
			return service.getWalletUpdateHistory(username, role, fdate, tdate);
		}
		return null;

	}

	@GetMapping("/offline/history")
	public List<WalletupdatelogDTO> getOfflineWalletupdatelog(@Valid @RequestParam("username") String username,
			@RequestParam("fdate") String fdate, @RequestParam("tdate") String tdate,
			@RequestParam("role") String role) {

		if (username != "") {
			return service.getOfflineWalletUpdateHistory(username, fdate, tdate, role);
		}
		return null;

	}

	@GetMapping("/today/walletamount")
	public Map<String, Double> getWalletAmoutDatewise(@RequestParam("username") String username,
			@RequestParam("role") String role) {

		return service.getWalletAmoutDatewise(role);
	}
}
