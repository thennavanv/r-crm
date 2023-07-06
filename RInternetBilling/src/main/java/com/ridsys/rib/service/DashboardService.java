package com.ridsys.rib.service;

import java.util.List;
import java.util.Map;

import com.ridsys.rib.DTO.DashboardDTO;
import com.ridsys.rib.DTO.PlanDetailDTO;
import com.ridsys.rib.DTO.UserDetailsDTO;
import com.ridsys.rib.comm.General;
import com.ridsys.rib.models.Clientinfo;

public interface DashboardService {

	DashboardDTO getDashboardDatas(String role, String username);

	Map<String, Long> getExpiredStatus(String username, String role);

	List<PlanDetailDTO> getPlanwiseCustomercount(String username, String role, String sts);

	List<General> getMaxDataUsers(String username, String role);

	Map<String, Long> getDatewiseCustomerCount(String username, String role, String day);

	List<UserDetailsDTO> getShortableUserlist(String username, String role, String statusname, int count,
			String subject);

	Map<String, Long> getUserCreatedCount(String username, String roleString);

	List<String> getSearchContents(String username, String role);

	Clientinfo getClientinfo();
}
