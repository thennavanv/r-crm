package com.ridsys.rib.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ridsys.rib.DTO.DashboardDTO;
import com.ridsys.rib.DTO.PlanDetailDTO;
import com.ridsys.rib.DTO.UserDetailsDTO;
import com.ridsys.rib.DTO.CustomerStatDTO;
import com.ridsys.rib.comm.Dateformat;
import com.ridsys.rib.comm.General;
import com.ridsys.rib.comm.IptvApi;
import com.ridsys.rib.models.Clientinfo;
import com.ridsys.rib.models.Operators;
import com.ridsys.rib.models.Userinfo;
import com.ridsys.rib.repository.ClientinfoRepository;
import com.ridsys.rib.repository.OperatorsRepository;
import com.ridsys.rib.repository.RadacctRepository;
import com.ridsys.rib.repository.UserbillinfoRepository;
import com.ridsys.rib.repository.UserinfoRepository;
import com.ridsys.rib.service.DashboardService;

@Service
public class DashboardServiceImpl implements DashboardService {

	@Autowired
	private UserinfoRepository userinfoRepository;

	@Autowired
	private UserbillinfoRepository userbillinfoRepository;

	@Autowired
	private RadacctRepository radacctRepository;

	@Autowired
	private OperatorsRepository operatorsRepository;

	@Autowired
	private ClientinfoRepository clientinfoRepository;

	@Override
	public DashboardDTO getDashboardDatas(String role, String username) {

		long total = 0;
		long active = 0;
		long deactive = 0;
		long online = 0;
		long offline = 0;
		long block = 0;
		long newcus = 0;
		long delete = 0;

		if (role.equals("ADMIN") || role.equals("EMPLOYEE")) {
			total = userinfoRepository.getTotalUsersCount();
			active = userbillinfoRepository.getActiveUsersCount();
			deactive = userbillinfoRepository.getDeactiveUsersCount();
			online = radacctRepository.getOnlineUsersCount();
			offline = radacctRepository.getOfflineUsersCount();
			block = userinfoRepository.getUnderVerificationCountAdmin();
			newcus = userinfoRepository.getNewCustomerAll();
			delete = userinfoRepository.getDeletedCustomerAll();
		}

		if (role.equals("OPERATOR")) {
			Operators opobj = operatorsRepository.findByUsername(username);
			if (opobj != null) {
				total = userinfoRepository.getTotalUsersCountByOpid(opobj.getId());
				active = userbillinfoRepository.getActiveUsersCountByOpid(opobj.getId());
				deactive = userbillinfoRepository.getDeactiveUsersCountByOpid(opobj.getId());
				online = radacctRepository.getOnlineUsersCountByOpid(opobj.getId());
				offline = radacctRepository.getOfflineUsersCountByOpid(opobj.getId());
				block = userinfoRepository.getUnderVerificationCountOperator(opobj.getId());
				newcus = userinfoRepository.getNewCustomerByOperator(opobj.getId());
				delete = userinfoRepository.getDeletedCustomerByOperator(opobj.getId());
			}
		}

		DashboardDTO dashboardDTO = new DashboardDTO();
		dashboardDTO.setCardcountinfo(total, active, deactive, online, offline, block, newcus, delete);

		return dashboardDTO;
	}

	@Override
	public Map<String, Long> getExpiredStatus(String username, String role) {

		CustomerStatDTO cusDTO = new CustomerStatDTO();

//		Operators operators = operatorsRepository.findById(2);
//		IptvApi.OperatorCreation(operators);

		if (role.equalsIgnoreCase("ADMIN") || role.equalsIgnoreCase("EMPLOYEE")) {
			cusDTO = userbillinfoRepository.getExpiryStatusAdmin();

		} else if (role.equalsIgnoreCase("OPERATOR")) {
			cusDTO = userbillinfoRepository.getExpiryStatusByOpid(username);

		}

		cusDTO.setShortableCustExpired(cusDTO.getToday(), cusDTO.getYesterday(), cusDTO.getTomorrow(),
				cusDTO.getLastweek(), cusDTO.getNextweek());

		return cusDTO.getShortableCustExpired();
	}

	@Override
	public List<PlanDetailDTO> getPlanwiseCustomercount(String username, String role, String sts) {

		List<PlanDetailDTO> PlanCountList = new ArrayList<>();

		if (sts.equalsIgnoreCase("ACTIVE")) {
			if (role.equalsIgnoreCase("ADMIN") || role.equalsIgnoreCase("EMPLOYEE")) {
				PlanCountList = userbillinfoRepository.getPlanwiseCustomercountTodayAdmin();

			} else if (role.equalsIgnoreCase("OPERATOR")) {
				PlanCountList = userbillinfoRepository.getPlanwiseCustomercountTodayByOpid(username);

			}
		} else if (sts.equalsIgnoreCase("TOTAL")) {

			if (role.equalsIgnoreCase("ADMIN") || role.equalsIgnoreCase("EMPLOYEE")) {
				PlanCountList = userbillinfoRepository.getPlanwiseCustomerAllcountAdmin();

			} else if (role.equalsIgnoreCase("OPERATOR")) {
				PlanCountList = userbillinfoRepository.getPlanwiseCustomerAllcountByOpid(username);

			}
		}

		return PlanCountList;
	}

	@Override
	public List<General> getMaxDataUsers(String username, String role) {

		List<General> MaxDataUserList = new ArrayList<>();
		String t1 = Dateformat.todayDate() + " 00:00:00";
		String t2 = Dateformat.todayDate() + " 23:59:59";

		if (role.equalsIgnoreCase("ADMIN") || role.equalsIgnoreCase("EMPLOYEE")) {
			MaxDataUserList = userbillinfoRepository.getMaxDataUsersAdmin();

		} else if (role.equalsIgnoreCase("OPERATOR")) {
			MaxDataUserList = userbillinfoRepository.getMaxDataUsersByOpid(username);

		}

		return MaxDataUserList;
	}

	@Override
	public Map<String, Long> getDatewiseCustomerCount(String username, String role, String day) {

		CustomerStatDTO cusObj = new CustomerStatDTO();
		String t1 = "";
		String t2 = "";

		if (day.equalsIgnoreCase("TODAY")) {
			t1 = Dateformat.todayDate() + " 00:00:00";
			t2 = Dateformat.todayDate() + " 23:59:59";

		} else if (day.equalsIgnoreCase("YESTERDAY")) {
			t1 = Dateformat.yesterday() + " 00:00:00";
			t2 = Dateformat.yesterday() + " 23:59:59";

		} else if (day.equalsIgnoreCase("LASTWEEK")) {
			t1 = Dateformat.weekfirst() + " 00:00:00";
			t2 = Dateformat.weekEnd() + " 23:59:59";
		}

		if (role.equalsIgnoreCase("ADMIN") || role.equalsIgnoreCase("EMPLOYEE")) {
			cusObj = userbillinfoRepository.getDatewiseCustomerCountAdmin(t1);

		} else if (role.equalsIgnoreCase("OPERATOR")) {
			cusObj = userbillinfoRepository.getDatewiseCustomerCountByOpid(username, t1);
		}
		cusObj.setSortTableCustomerCout(cusObj.getToday(), cusObj.getYesterday(), cusObj.getTomorrow(),
				cusObj.getLastweek(), cusObj.getNextweek());

		return cusObj.getSortTableCustomerCout();
	}

	@Override
	public List<UserDetailsDTO> getShortableUserlist(String username, String role, String statusname, int count,
			String subject) {

		String t1 = Dateformat.todayDate() + " 00:00:00";
		String t2 = Dateformat.todayDate() + " 23:59:59";
		String y1 = Dateformat.yesterday() + " 00:00:00";
		String y2 = Dateformat.yesterday() + " 23:59:59";
		String trw1 = Dateformat.tomorrow() + " 00:00:00";
		String trw2 = Dateformat.tomorrow() + " 23:59:59";
		String week1 = Dateformat.weekfirst() + " 00:00:00";
		String week2 = Dateformat.yesterday() + " 23:59:59";
		String week3 = Dateformat.weeklast() + " 23:59:59";

		statusname = statusname.trim();

		if (role.equalsIgnoreCase("ADMIN")) {

			if (subject.equalsIgnoreCase("expiry")) {

				if (statusname.equalsIgnoreCase("today")) {
					return userbillinfoRepository.getExpiredUserList(t1, t2);

				} else if (statusname.equalsIgnoreCase("Yesterday")) {
					return userbillinfoRepository.getExpiredUserList(y1, y2);

				} else if (statusname.equalsIgnoreCase("Tomorrow")) {
					return userbillinfoRepository.getExpiredUserList(trw1, trw2);

				} else if (statusname.equalsIgnoreCase("Last 7 days")) {
					return userbillinfoRepository.getExpiredUserList(week1, week2);

				} else if (statusname.equalsIgnoreCase("Next 7 days")) {
					return userbillinfoRepository.getExpiredUserList(t1, week3);

				}
			} else if (subject.equalsIgnoreCase("custstatic")) {

				if (statusname.equalsIgnoreCase("new")) {
					return userbillinfoRepository.getStatictisUserlist(0);

				} else if (statusname.equalsIgnoreCase("reject")) {
					return userbillinfoRepository.getStatictisUserlist(1);

				} else if (statusname.equalsIgnoreCase("active")) {
					return userbillinfoRepository.getStatictisActiveUserlist();

				} else if (statusname.equalsIgnoreCase("expiry")) {
					return userbillinfoRepository.getStatictisExpiryUserlist(t1);

				} else if (statusname.equalsIgnoreCase("fup exceed")) {
					return userbillinfoRepository.getStatictisFupUserList();

				}
			} else if (subject.equalsIgnoreCase("newuser")) {

				if (statusname.equalsIgnoreCase("Today")) {
					return userbillinfoRepository.getUserCreatedListToday();

				} else if (statusname.equalsIgnoreCase("Yesterday")) {
					return userbillinfoRepository.getUserCreatedListYester();

				} else if (statusname.equalsIgnoreCase("Last Week")) {
					return userbillinfoRepository.getUserCreatedListWweek();

				} else if (statusname.equalsIgnoreCase("Last Month")) {
					return userbillinfoRepository.getUserCreatedListMonth();

				} else if (statusname.equalsIgnoreCase("Last 2Month")) {
					return userbillinfoRepository.getUserCreatedListTwoMonth();

				}
			}

		} else if (role.equalsIgnoreCase("OPERATOR")) {

			if (subject.equalsIgnoreCase("expiry")) {

				if (statusname.equalsIgnoreCase("today")) {
					return userbillinfoRepository.getExpiredUserListByOpid(username, t1, t2);

				} else if (statusname.equalsIgnoreCase("Yesterday")) {
					return userbillinfoRepository.getExpiredUserListByOpid(username, y1, y2);

				} else if (statusname.equalsIgnoreCase("Tomorrow")) {
					return userbillinfoRepository.getExpiredUserListByOpid(username, trw1, trw2);

				} else if (statusname.equalsIgnoreCase("Last 7 days")) {
					return userbillinfoRepository.getExpiredUserListByOpid(username, week1, week2);

				} else if (statusname.equalsIgnoreCase("Next 7 days")) {
					return userbillinfoRepository.getExpiredUserListByOpid(username, t1, week3);

				}
			} else if (subject.equalsIgnoreCase("custstatic")) {

				if (statusname.equalsIgnoreCase("new")) {
					return userbillinfoRepository.getStatictisUserlistByOpid(0, username);

				} else if (statusname.equalsIgnoreCase("reject")) {
					return userbillinfoRepository.getStatictisUserlistByOpid(1, username);

				} else if (statusname.equalsIgnoreCase("active")) {
					return userbillinfoRepository.getStatictisActiveUserlistByOpid(username);

				} else if (statusname.equalsIgnoreCase("expiry")) {
					return userbillinfoRepository.getStatictisExpiryUserlistByOpid(t1, username);

				} else if (statusname.equalsIgnoreCase("fup exceed")) {
					return userbillinfoRepository.getStatictisFupUserListByOpid(username);
				}

			} else if (subject.equalsIgnoreCase("newuser")) {

				if (statusname.equalsIgnoreCase("Today")) {
					return userbillinfoRepository.getUserCreatedListTodayByOpid(username);

				} else if (statusname.equalsIgnoreCase("Yesterday")) {
					return userbillinfoRepository.getUserCreatedListYesterByOpid(username);

				} else if (statusname.equalsIgnoreCase("Last Week")) {
					return userbillinfoRepository.getUserCreatedListWweekByOpid(username);

				} else if (statusname.equalsIgnoreCase("Last Month")) {
					return userbillinfoRepository.getUserCreatedListMonthByOpid(username);

				} else if (statusname.equalsIgnoreCase("Last 2Month")) {
					return userbillinfoRepository.getUserCreatedListTwoMonthByOpid(username);

				}
			}
		}

		return null;
	}

	@Override
	public Map<String, Long> getUserCreatedCount(String username, String role) {

		CustomerStatDTO obj = new CustomerStatDTO();
		if (role.equalsIgnoreCase("ADMIN")) {
			obj = userbillinfoRepository.getUserCreatedCount();

		} else if (role.equalsIgnoreCase("OPERATOR")) {
			obj = userbillinfoRepository.getUserCreatedCountByOpid(username);

		}

		Map<String, Long> map = new HashMap<>();
		map.put("today", obj.getToday());
		map.put("yesterday", obj.getYesterday());
		map.put("lastweek", obj.getTomorrow());
		map.put("lastmonth", obj.getLastweek());
		map.put("lasttwomonth", obj.getNextweek());

		return map;
	}

	@Override
	public List<String> getSearchContents(String username, String role) {

		List<String> searchlist = new ArrayList();

		if (role.equalsIgnoreCase("ADMIN")) {

			List<Userinfo> userlist = userinfoRepository.findAllByIsDelete(false);

			for (Userinfo u : userlist) {
				searchlist.add(u.getUsername() + "-" + u.getMobilephone());
			}
			return searchlist;

		} else if (role.equalsIgnoreCase("OPERATOR")) {

			Operators opobj = operatorsRepository.findByUsername(username);

			if (opobj != null) {
				List<Userinfo> userlist = userinfoRepository.findByOpidAndIsDelete(opobj.getId(), false);

				for (Userinfo u : userlist) {
					searchlist.add(u.getUsername() + "-" + u.getMobilephone());
				}

			}

			return searchlist;
		}

		return searchlist;
	}

	@Override
	public Clientinfo getClientinfo() {
		return clientinfoRepository.findOneClient();
	}
}
