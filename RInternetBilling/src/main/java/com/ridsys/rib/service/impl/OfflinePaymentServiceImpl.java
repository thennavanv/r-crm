package com.ridsys.rib.service.impl;

import java.util.List;

import org.decimal4j.util.DoubleRounder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ridsys.rib.DTO.OfflinePaymentDTO;
import com.ridsys.rib.comm.Dateformat;
import com.ridsys.rib.comm.OnlinePaymentAssets;
import com.ridsys.rib.models.Offline_payment_log;
import com.ridsys.rib.models.Operators;
import com.ridsys.rib.models.Walletupdatelog;
import com.ridsys.rib.payload.request.OfflinePayment;
import com.ridsys.rib.payload.response.MessageResponse;
import com.ridsys.rib.repository.Offline_payment_logRepository;
import com.ridsys.rib.repository.OperatorsRepository;
import com.ridsys.rib.repository.WalletupdatelogRepository;
import com.ridsys.rib.service.OfflinePaymentService;

@Service
public class OfflinePaymentServiceImpl implements OfflinePaymentService {

	@Autowired
	private Offline_payment_logRepository offline_payment_logRepository;

	@Autowired
	private OperatorsRepository operatorsRepository;

	@Autowired
	private WalletupdatelogRepository walletupdatelogRepository;

	@Override
	public ResponseEntity<?> operatorPaymentRequest(OfflinePayment body) {
		double amount = 0;
		try {
			amount = Double.parseDouble(body.getAmount());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new MessageResponse("Invalid amount"));
		}

		if (amount <= 0) {
			return ResponseEntity.badRequest().body(new MessageResponse("Invalid amount"));
		}

		if (body.getTorole().trim().equals("") || body.getTousername().trim().equals("")
				|| Integer.parseInt(body.getPayment_type()) == 0 || body.getFromrole().trim().equals("")
				|| body.getFromusername().trim().equals("")) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error with your wallet recharge please try again later"));
		}

		final String transactionid = "OFR" + OnlinePaymentAssets.generateOrderID();
		final String creationdate = Dateformat.getCurrentDatetime();

		Offline_payment_log obj = new Offline_payment_log(transactionid, body.getAmount(), body.getTorole(),
				body.getTousername(), Integer.parseInt(body.getPayment_type()), creationdate, body.getFromrole(),
				body.getFromusername(), body.getDescription());

		Operators opobj = new Operators();
		opobj = operatorsRepository.findByUsername(body.getTousername());

		double walletbalance = 0;
		try {
			walletbalance = Double.parseDouble(opobj.getWalletbalance());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error with your recharge to this operator"));
		}

		double newbalance = DoubleRounder.round((walletbalance + amount), 2);

		Walletupdatelog wul = new Walletupdatelog(body.getTorole(), body.getTousername(), opobj.getWalletbalance(),
				body.getAmount(), String.valueOf(newbalance), transactionid, 1, 1, body.getFromrole(),
				body.getFromusername(), creationdate);

		offline_payment_logRepository.save(obj);

		walletupdatelogRepository.save(wul);

		operatorsRepository.walletBalanceCredit(opobj.getId(), amount);

		return ResponseEntity.ok(new MessageResponse("Wallet Recharged Successfully Done!.."));

	}

	@Override
	public List<Offline_payment_log> getOfflineRechargeList() {
		return offline_payment_logRepository.findAll();
	}

	@Override
	public List<OfflinePaymentDTO> offlineRechargeList(String username, String role, String fdate, String tdate) {

		if (!fdate.equalsIgnoreCase("0000-00-00") && !tdate.equalsIgnoreCase("0000-00-00")) {
			if (role.equalsIgnoreCase("ADMIN")) {
				return offline_payment_logRepository.getOfflineHistoryAllFtdate(fdate, tdate);

			} else if (role.equalsIgnoreCase("OPERATOR")) {
				return offline_payment_logRepository.getOfflineHistoryFtdate(fdate, tdate, username);

			}

		} else {
			if (role.equalsIgnoreCase("ADMIN")) {
				return offline_payment_logRepository.getOfflineHistoryAll();

			} else if (role.equalsIgnoreCase("OPERATOR")) {
				return offline_payment_logRepository.getOfflineHistory(username);

			}
		}

		return null;
	}

}
