package com.ridsys.rib.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.ridsys.rib.models.Nas;
import com.ridsys.rib.models.Radippool;
import com.ridsys.rib.models.Switch;
import com.ridsys.rib.payload.request.CreateSwitchRequest;
import com.ridsys.rib.payload.request.IppoolPayload;

public interface NetworkingService {

	ResponseEntity<?> nasModification(Nas nasobj, String action);

	List<Nas> getNasList();

	ResponseEntity<?> nasDelete(long nasid);

	Map<String, String> ipCalculation(String address, String netmask);

	ResponseEntity<?> ippoolModification(IppoolPayload obj, String action);

	ResponseEntity<?> ippoolDelete(String fromip, String toip);

	List getNetworkDetails(String about);

	ResponseEntity<?> switchModification(CreateSwitchRequest obj);

	ResponseEntity<?> editSwitch(Switch obj);

	ResponseEntity<?> switchDelete(int switchid);

	Map<String, List> getLanPort(int switchid);

	List getIpList(String fromip);

	ResponseEntity<?> setIppoolForUser(Map<String, String> obj);
	
	ResponseEntity<?> insertTables(Map<String,String> obj,String tablename);

}
