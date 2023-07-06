package com.ridsys.rib.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ridsys.rib.models.Radacct;
import com.ridsys.rib.repository.RadacctRepository;
import com.ridsys.rib.service.RadacctService;

@Service
public class RadacctServiceImpl implements RadacctService {
	@Autowired
	RadacctRepository radacctRepository;

	@Override
	public List<Radacct> getSubscriberSessionHistoryByusername(String username, String role, String fdate,
			String tdate) {

		if (!fdate.equals("0000-00-00") && !tdate.equals("0000-00-00")) {
			if (role.equalsIgnoreCase("USER")) {
				return radacctRepository.findByUsernameOrderByRadacctidDescFtdate(username, fdate, tdate);

			} else if (role.equalsIgnoreCase("OPERATOR")) {
				return radacctRepository.OperatorSessionAcctHistoryFtdate(username, fdate, tdate);

			} else if (role.equalsIgnoreCase("ADMIN") || role.equalsIgnoreCase("EMPLOYEE")) {
				return radacctRepository.SessionAcctHistoryFtdate(fdate, tdate);
			}
		} else {
			if (role.equalsIgnoreCase("USER")) {
				return radacctRepository.findByUsernameOrderByRadacctidDesc(username);

			} else if (role.equalsIgnoreCase("OPERATOR")) {
				return radacctRepository.OperatorSessionAcctHistory(username);

			} else if (role.equalsIgnoreCase("ADMIN") || role.equalsIgnoreCase("EMPLOYEE")) {
				return radacctRepository.SessionAcctHistory();
			}
		}

		return null;
	}

}
