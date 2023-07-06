package com.ridsys.rib.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ridsys.rib.models.Radacct;
import com.ridsys.rib.service.RadacctService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class RadacctController {
	@Autowired
	RadacctService service;

	@GetMapping("/radacct")
	public List<Radacct> subscriberSessionHistoryForOperator(@RequestParam("username") String username,
			@RequestParam("role") String role, @RequestParam("fdate") String fdate,
			@RequestParam("tdate") String tdate) {

		return service.getSubscriberSessionHistoryByusername(username, role, fdate, tdate);

	}
}
