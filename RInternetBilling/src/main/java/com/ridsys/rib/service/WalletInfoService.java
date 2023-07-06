package com.ridsys.rib.service;

import java.util.List;
import java.util.Map;

import com.ridsys.rib.DTO.WalletInfoDTO;
import com.ridsys.rib.DTO.WalletupdatelogDTO;

public interface WalletInfoService {

	List<WalletupdatelogDTO> getWalletUpdateHistory(String username, String role,String fdate,String tdate);

	WalletInfoDTO getRecentOnlineWalletUpdateHistory(String username, String role);

	List<WalletupdatelogDTO> getOfflineWalletUpdateHistory(String username,String fdate,String tdate,String role);
	
	Map<String,Double> getWalletAmoutDatewise(String role);
	
}
