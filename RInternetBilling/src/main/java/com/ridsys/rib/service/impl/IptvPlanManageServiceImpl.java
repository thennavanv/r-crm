package com.ridsys.rib.service.impl;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.decimal4j.util.DoubleRounder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ridsys.rib.comm.Dateformat;
import com.ridsys.rib.models.Iptvplanmanagelog;
import com.ridsys.rib.models.Walletupdatelog;
import com.ridsys.rib.payload.request.IptvPlanActivation;
import com.ridsys.rib.repository.IptvpackagetypeRepository;
import com.ridsys.rib.repository.IptvplanmanagelogRepository;
import com.ridsys.rib.repository.OperatorsRepository;
import com.ridsys.rib.repository.WalletupdatelogRepository;
import com.ridsys.rib.service.IptvPlanManageService;

@Service
public class IptvPlanManageServiceImpl implements IptvPlanManageService {

	@Autowired
	IptvpackagetypeRepository iptvPackageType_repo;

	@Autowired
	IptvplanmanagelogRepository iptvPlanManageLog_repo;

	@Autowired
	WalletupdatelogRepository walletupdatelog_repo;

	@Autowired
	OperatorsRepository operatorsRepository;

	@Override
	public String iptvplanActivation(@Valid IptvPlanActivation obj) {
		Iptvplanmanagelog object = new Iptvplanmanagelog();

		try {
			object.setPlancost(Double.parseDouble(obj.getPrice()));
			object.setPlancuscost(Double.parseDouble(obj.getCuscost()));
			object.setPlandiscount(0);
			object.setPlanid(Integer.parseInt(obj.getPackageid()));
			object.setPlanlcocomm(String.valueOf(obj.getLcocomm()));
			object.setPlanmrp(Double.parseDouble(obj.getMrp()));
			object.setPlanmsocost(Double.parseDouble(obj.getMsocost()));
			object.setPlantax(Double.parseDouble(obj.getTax()));
			object.setQuotaexpirydate(obj.getExpirydate());
			object.setRechargeid(obj.getPlanrechargeid());
			object.setUsername(obj.getUsername());
			
			System.out.println(obj.getCreationby()+"   "+obj.getCreationbyusername());
			object.setCreationby(obj.getCreationby());
			object.setCreationbyusername(obj.getCreationbyusername());
			object.setCreationdate(Dateformat.getCurrentDatetime());
			object.setPlantimebank(Integer.parseInt(obj.getDays()));
			object.setPlanname(obj.getPlanname());
			object.setRemarks(obj.getRemarks());

			object.setIptvpackagetype(iptvPackageType_repo.findById(Integer.parseInt(obj.getPackagetypeid())));


			iptvPlanManageLog_repo.save(object);

			Double walletbalance = Double.parseDouble(obj.getWalletbalance());

			Double newbalance = DoubleRounder.round((walletbalance - Double.parseDouble(obj.getMsocost())), 2);

			// Deduct from operator log
			Walletupdatelog wallelog = new Walletupdatelog();
			wallelog = new Walletupdatelog("OPERATOR", obj.getOpusername(), String.valueOf(walletbalance),
					String.valueOf(obj.getMsocost()), String.valueOf(newbalance),
					String.valueOf(obj.getPlanrechargeid()), 2, 2, String.valueOf(obj.getCreationby()),
					String.valueOf(obj.getCreationbyusername()), Dateformat.getCurrentDatetime());

			walletupdatelog_repo.save(wallelog);

			operatorsRepository.walletBalanceDetection(Integer.parseInt(obj.getOpid()), Double.parseDouble(obj.getMsocost()));

			return "success";
		} catch (Exception e) {
			return "failed";
		}

	}

}
