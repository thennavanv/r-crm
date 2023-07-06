package com.ridsys.rib.service;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.ridsys.rib.DTO.QuotaplanmanageloghistoryDTO;
import com.ridsys.rib.DTO.SubscriptionInfoDTO;
import com.ridsys.rib.models.Graceperiodextendhistory;
import com.ridsys.rib.models.Quotaplancancelhistory;
import com.ridsys.rib.models.Quotaplanmanagelog;
import com.ridsys.rib.payload.request.CancelInvoice;
import com.ridsys.rib.payload.request.QuatoplanactivationRequest;

public interface QuotaplanmanagingService {

	ResponseEntity<?> quotaPlanActive(@Valid QuatoplanactivationRequest requestobject);

	List<QuotaplanmanageloghistoryDTO> getQuotaPlanManageLogHistoryByRoleCreationbyusername(String role,
			String username,String fdate,String tdate);
	
	List<QuotaplanmanageloghistoryDTO> getQuotaPlanManageLogHistoryForOpAndUser(String role,
			String username,String fdate,String tdate);

	SubscriptionInfoDTO getSubscriptionInfoByRoleCreationbyusername(String username);

	List<Quotaplanmanagelog> getSubscriberPackageHistory(String username, String role);
	
	ResponseEntity<?> quotaPlanActive2(@Valid QuatoplanactivationRequest requestobject);
	
	ResponseEntity<?> expirydateExtend(Map<String,?> obj);
	
	ResponseEntity<?> cancelinvoice(CancelInvoice obj);
	
	List<Graceperiodextendhistory> getgraceplanextendhistory(String role, String username, String fdate, String tdate);

	List<Quotaplancancelhistory> getquotaplancancelhistory(String role, String username, String fdate, String tdate);

}
