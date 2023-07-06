package com.ridsys.rib.service.impl;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ridsys.rib.DTO.OnlineOfflineDTO;
import com.ridsys.rib.DTO.WalletInfoDTO;
import com.ridsys.rib.DTO.WalletupdatelogDTO;
import com.ridsys.rib.comm.Dateformat;
import com.ridsys.rib.models.Operators;
import com.ridsys.rib.models.Userinfo;
import com.ridsys.rib.repository.OperatorsRepository;
import com.ridsys.rib.repository.UserinfoRepository;
import com.ridsys.rib.repository.WalletupdatelogRepository;
import com.ridsys.rib.service.WalletInfoService;

@Service
public class WalletInfoServiceImpl implements WalletInfoService {

	@Autowired
	WalletupdatelogRepository walletupdatelogRepository;

	@Autowired
	OperatorsRepository operatorsRepository;

	@Autowired
	UserinfoRepository userinfoRepository;

	@Override
	public WalletInfoDTO getRecentOnlineWalletUpdateHistory(String username, String role) {

		String walletbalance = "0";
		String LastRecharge = "0";
		List<WalletupdatelogDTO> recenthistory = new ArrayList<WalletupdatelogDTO>();

		if (role.equalsIgnoreCase("OPERATOR")) {
			Operators operator = operatorsRepository.findByUsername(username);
			walletbalance = operator.getWalletbalance();
			recenthistory = walletupdatelogRepository.getOperatorRecentOnlineWalletUpdateHistory(username, role);

		} else if (role.equalsIgnoreCase("USER")) {
			Userinfo userinfo = userinfoRepository.findByUsername(username);
			walletbalance = userinfo.getWalletbalance();
			recenthistory = walletupdatelogRepository.getUserRecentOnlineWalletUpdateHistory(username, role);
			if (walletupdatelogRepository.countByUsername(username) > 0) {
				LastRecharge = walletupdatelogRepository.getUserLastRechargeAmount(username);
			}
		}
		return new WalletInfoDTO(Double.parseDouble(walletbalance), Double.parseDouble(LastRecharge), recenthistory);
	}

	@Override
	public List<WalletupdatelogDTO> getWalletUpdateHistory(String username, String role, String fdate, String tdate) {

		if (!fdate.equals("0000-00-00") && !tdate.equals("0000-00-00")) {
			if (role.equalsIgnoreCase("OPERATOR")) {
				return walletupdatelogRepository.getOperatorWalletUpdateHistoryFtdate(username, role, fdate, tdate);

			} else if (role.equalsIgnoreCase("USER")) {
				return walletupdatelogRepository.getUserWalletUpdateHistoryFtdate(username, role, fdate, tdate);

			}

		} else {
			if (role.equalsIgnoreCase("OPERATOR")) {
				return walletupdatelogRepository.getOperatorWalletUpdateHistory(username, role);

			} else if (role.equalsIgnoreCase("USER")) {
				return walletupdatelogRepository.getUserWalletUpdateHistory(username, role);

			}
		}

		return null;

	}

	@Override
	public List<WalletupdatelogDTO> getOfflineWalletUpdateHistory(String username, String fdate, String tdate,
			String role) {

		if (!fdate.equals("0000-00-00") && !tdate.equals("0000-00-00")) {
			if (role.equalsIgnoreCase("ADMIN")) {
				return walletupdatelogRepository.getOfflineWalletUpdateHistoryAllFtdate(fdate, tdate);
			} else {
				return walletupdatelogRepository.getOfflineWalletUpdateHistoryFtdate(username, fdate, tdate);
			}
		} else {
			if (role.equalsIgnoreCase("ADMIN")) {
				return walletupdatelogRepository.getOfflineWalletUpdateHistoryAll();
			} else {
				return walletupdatelogRepository.getOfflineWalletUpdateHistory(username);
			}
		}

	}

	@Override
	public Map<String, Double> getWalletAmoutDatewise(String role) {
		Map<String, Double> walletamountMap =new HashMap();

		try {

			OnlineOfflineDTO obj = new OnlineOfflineDTO();
			String t1 = Dateformat.todayDate() + " 00:00:00";
			String t2 = Dateformat.todayDate() + " 23:59:59";

			if (role.equalsIgnoreCase("ADMIN")) {

				obj = walletupdatelogRepository.getWalletAmountDatewise(t1, t2);
				walletamountMap.put("online", (double) Math.round(obj.getOffline()));
				walletamountMap.put("lcoOffline", (double) Math.round(obj.getLcoOnline()));
				walletamountMap.put("subOnline", (double) Math.round(obj.getSubOnline()));
				walletamountMap.put("Total", (double) Math.round(obj.getOffline() + obj.getLcoOnline() + obj.getSubOnline()));

			}
		} catch (Exception e) {
			System.out.println("Exception at getting today wallet balance:" + e);
		}

		return walletamountMap;
	}

}
