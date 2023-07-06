package com.ridsys.rib.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ridsys.rib.payload.request.IptvPlanActivation;
import com.ridsys.rib.service.IptvPlanManageService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/iptvplan")
public class IptvPlanManageController {
	
	@Autowired
	IptvPlanManageService iptvPlanManageService;
	
	@PostMapping("/iptvplanActivation")
	public String iptvplanActivation(@RequestBody IptvPlanActivation reqobj) {
		System.out.println(reqobj);
		return iptvPlanManageService.iptvplanActivation(reqobj);
	}

}
