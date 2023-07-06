package com.ridsys.rib.service;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.ridsys.rib.DTO.TicketSubjectTypeDTO;
import com.ridsys.rib.models.Ottpackages;
import com.ridsys.rib.models.Ottuserinfo;
import com.ridsys.rib.payload.request.OttplanactivationRequest;

public interface OttplanmanagelogService {

	ResponseEntity<?> activeOttPlan1(OttplanactivationRequest reqobj);

	Map<String, Object> getPlanDetailsByUser(String username);

	ResponseEntity<?> createOttPackage(Map<String, String> obj);

	ResponseEntity<?> deactiveOttPlan(String username, String creationbyrole, String creationbyusername);

	List<Ottpackages> getOttpackageList();

	List<TicketSubjectTypeDTO> getOttPlanList();

	Ottuserinfo getSubsDetails(@Valid String mobile_no);

}
