package com.ridsys.rib.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ridsys.rib.DTO.TicketDTO;
import com.ridsys.rib.DTO.TicketDetailDTO;
import com.ridsys.rib.DTO.TicketSubjectTypeDTO;
import com.ridsys.rib.comm.Dateformat;
import com.ridsys.rib.models.Employeeinfo;
import com.ridsys.rib.models.Tickets;
import com.ridsys.rib.payload.response.MessageResponse;
import com.ridsys.rib.repository.EmployeeinfoRepository;
import com.ridsys.rib.repository.TicketsRepository;
import com.ridsys.rib.repository.UserinfoRepository;
import com.ridsys.rib.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketsRepository ticketRepository;

	@Autowired
	private UserinfoRepository userinfoRepository;

	@Autowired
	private EmployeeinfoRepository employeeinfoRepository;

	@Override
	public Map<String, List<TicketSubjectTypeDTO>> getTicketSubjectList() {
		List<TicketSubjectTypeDTO> list = ticketRepository.getTicketSubjectList();
		List<TicketSubjectTypeDTO> list1 = ticketRepository.getTicketTypeList();
		Map<String, List<TicketSubjectTypeDTO>> map = new HashMap<>();
		map.put("subject", list);
		map.put("type", list1);
		return map;
	}

	@Override
	public ResponseEntity<?> createTicket(Tickets obj) {

		// 1-created 2-assigend 3-opened 4-finished 5-closed

		int maxid = ticketRepository.getMaxTicketID() + 1;
		String code = String.format("%05d", maxid);

		int opid = userinfoRepository.findByUsername(obj.getUsername()).getOpid();
		if (opid <= 0) {
			return ResponseEntity.badRequest().body(new MessageResponse("Ticket Not Generated! Not a valid user!"));
		}

		Tickets tickObj = new Tickets();
		tickObj.setCode("TICK" + code);
		tickObj.setUsername(obj.getUsername());
		tickObj.setOpid(opid);
		tickObj.setTicketDescription(obj.getTicketDescription());
		tickObj.setSubjectId(obj.getSubjectId());
		tickObj.setTypeId(obj.getTypeId());
		tickObj.setStatus(1);
		tickObj.setCreateddate(Dateformat.getCurrentDatetime());
		tickObj.setIsactive(true);
		tickObj.setIsreassign(false);
		ticketRepository.save(tickObj);

		return ResponseEntity.ok(new MessageResponse("Ticket Successfully created!!"));
	}

	@Override
	public TicketDetailDTO getCurrentTicketDetails(String username, int subjectid, int typeid) {
		return ticketRepository.getTicketDetails(username, subjectid, typeid);
	}

	@Override
	public List<TicketDTO> getTicketList(String username, String role, int status, String fdate, String tdate) {

		if (!fdate.equals("0000-00-00") && !tdate.equals("0000-00-00")) {
			if (role.equalsIgnoreCase("ADMIN")) {
				if (status == 0) {
					return ticketRepository.getTotalTicketByAdminFtdate(fdate, tdate);
				} else if (status == 6) {
					return ticketRepository.getNewReassignTickListByAdminFtdate(1, 1, fdate, tdate);
				} else if (status == 1) {
					return ticketRepository.getNewReassignTickListByAdminFtdate(1, 0, fdate, tdate);
				} else {
					return ticketRepository.getTicketListByAdminFtdate(status, fdate, tdate);
				}
			} else if (role.equalsIgnoreCase("OPERATOR")) {
				if (status == 0) {
					return ticketRepository.getTotalTicketByOpidFtdate(username, fdate, tdate);
				} else if (status == 6) {
					return ticketRepository.getNewReassignTickListByOpidFtdate(username, 1, 1, fdate, tdate);
				} else if (status == 1) {
					return ticketRepository.getNewReassignTickListByOpidFtdate(username, 1, 0, fdate, tdate);
				} else {
					return ticketRepository.getTicketListByOpidFtdate(username, status, fdate, tdate);

				}
			} else if (role.equalsIgnoreCase("USER")) {
				if (status == 0) {
					return ticketRepository.getTotalTicketByUserFtdate(username, fdate, tdate);
				} else if (status == 6) {
					return ticketRepository.getNewReassignTickListByUserFtdate(username, 1, 1, fdate, tdate);
				} else if (status == 1) {
					return ticketRepository.getNewReassignTickListByUserFtdate(username, 1, 0, fdate, tdate);
				} else {
					return ticketRepository.getTicketListByUserFtdate(username, status, fdate, tdate);
				}
			} else if (role.equalsIgnoreCase("EMPLOYEE")) {

				return ticketRepository.getTicketListByEmpFtdate(username, status, fdate, tdate);
			}
		} else {
			if (role.equalsIgnoreCase("ADMIN")) {
				if (status == 0) {
					return ticketRepository.getTotalTicketByAdmin();
				} else if (status == 6) {
					return ticketRepository.getNewReassignTickListByAdmin(1, 1);
				} else if (status == 1) {
					return ticketRepository.getNewReassignTickListByAdmin(1, 0);
				} else {
					return ticketRepository.getTicketListByAdmin(status);
				}
			} else if (role.equalsIgnoreCase("OPERATOR")) {
				if (status == 0) {
					return ticketRepository.getTotalTicketByOpid(username);
				} else if (status == 6) {
					return ticketRepository.getNewReassignTickListByOpid(username, 1, 1);
				} else if (status == 1) {
					return ticketRepository.getNewReassignTickListByOpid(username, 1, 0);
				} else {
					return ticketRepository.getTicketListByOpid(username, status);

				}
			} else if (role.equalsIgnoreCase("USER")) {
				if (status == 0) {
					return ticketRepository.getTotalTicketByUser(username);
				} else if (status == 6) {
					return ticketRepository.getNewReassignTickListByUser(username, 1, 1);
				} else if (status == 1) {
					return ticketRepository.getNewReassignTickListByUser(username, 1, 0);
				} else {
					return ticketRepository.getTicketListByUser(username, status);
				}
			} else if (role.equalsIgnoreCase("EMPLOYEE")) {

				return ticketRepository.getTicketListByEmp(username, status);
			}
		}
		return null;

	}

	@Override
	public Map<String, Integer> getTicketcountByStatus(String username, String role) {

		Map<String, Integer> map = new HashMap<>();

		if (role.equalsIgnoreCase("ADMIN")) {
			map.put("New", ticketRepository.getNewReassignTickListByAdmin(1, 0).size());
			map.put("Assigned", ticketRepository.getTicketListByAdmin(2).size());
			map.put("Opened", ticketRepository.getTicketListByAdmin(3).size());
			map.put("Finished", ticketRepository.getTicketListByAdmin(4).size());
			map.put("Solved", ticketRepository.getTicketListByAdmin(5).size());// closed
			map.put("Reassigned", ticketRepository.getNewReassignTickListByAdmin(1, 1).size());
			map.put("Total", ticketRepository.getTotalTicketByAdmin().size());

		} else if (role.equalsIgnoreCase("OPERATOR")) {
			map.put("New", ticketRepository.getNewReassignTickListByOpid(username, 1, 0).size());
			map.put("Assigned", ticketRepository.getTicketListByOpid(username, 2).size());
			map.put("Opened", ticketRepository.getTicketListByOpid(username, 3).size());
			map.put("Finished", ticketRepository.getTicketListByOpid(username, 4).size());
			map.put("Solved", ticketRepository.getTicketListByOpid(username, 5).size());// closed
			map.put("Reassigned", ticketRepository.getNewReassignTickListByOpid(username, 1, 1).size());
			map.put("Total", ticketRepository.getTotalTicketByOpid(username).size());
			// true

		} else if (role.equalsIgnoreCase("USER")) {
			map.put("New", ticketRepository.getNewReassignTickListByUser(username, 1, 0).size());
			map.put("Assigned", ticketRepository.getTicketListByUser(username, 2).size());
			map.put("Opened", ticketRepository.getTicketListByUser(username, 3).size());
			map.put("Finished", ticketRepository.getTicketListByUser(username, 4).size());
			map.put("Solved", ticketRepository.getTicketListByUser(username, 5).size());// closed
			map.put("Reassigned", ticketRepository.getNewReassignTickListByUser(username, 1, 1).size());
			map.put("Total", ticketRepository.getTotalTicketByUser(username).size());

		} else if (role.equalsIgnoreCase("EMPLOYEE")) {
			map.put("New", ticketRepository.getNewReassignTickListByAdmin(1, 0).size());
			map.put("Assigned", ticketRepository.getTicketListByEmp(username, 2).size());
			map.put("Opened", ticketRepository.getTicketListByEmp(username, 3).size());
			map.put("Finished", ticketRepository.getTicketListByEmp(username, 4).size());
			map.put("Solved", ticketRepository.getTicketListByEmp(username, 5).size());// closed
			map.put("Reopened", ticketRepository.getReassignTickListByEmp(username).size());
			map.put("Reassigned", ticketRepository.getNewReassignTickListByAdmin(1, 1).size());
			map.put("Total", ticketRepository.getTotalTicketByEmp(username).size());

		}

		return map;
	}

	@Override
	public Map<String, Object> getTicketDetailByCode(String ticketcode) {

		Map<String, Object> map = new HashMap<>();

		TicketDTO obj = ticketRepository.getTicketDetailByCode(ticketcode);

		map.put("vlanid", obj.getVlanid());
		map.put("username", obj.getUsername());
		map.put("opname", obj.getOpname());
		map.put("subject", obj.getSubject());
		map.put("type", obj.getType());
		map.put("employee", employeeinfoRepository.getEmployeeList());
		return map;
	}

	@Override
	public ResponseEntity<?> employeeAssign(String empid, String ticketid, String status, String comments) {
		Optional<Employeeinfo> obj = employeeinfoRepository.findById(Long.parseLong(empid));

		if (status.equalsIgnoreCase("ASSIGNED")) {
			ticketRepository.ticketAssignforEmp(Long.parseLong(empid), ticketid);

			return ResponseEntity
					.ok(new MessageResponse("Ticket Successfully Assigned For " + obj.get().getEmpname() + " !!"));
		} else if (status.equalsIgnoreCase("OPENED")) {

			ticketRepository.ticketOpenforEmp(ticketid);

			return ResponseEntity.ok(new MessageResponse("Ticket Successfully Opened!!"));
		} else if (status.equalsIgnoreCase("FINISHED")) {
			ticketRepository.ticketFinished(ticketid, comments);

			return ResponseEntity.ok(new MessageResponse("Ticket Successfully Finished!!"));
		} else if (status.equalsIgnoreCase("CLOSED")) {
			ticketRepository.ticketClosed(ticketid, comments);

			return ResponseEntity.ok(new MessageResponse("Ticket Successfully Closed!!"));
		} else if (status.equalsIgnoreCase("REASSIGNED")) {
			ticketRepository.ticketReassignforEmp(ticketid, comments);

			return ResponseEntity.ok(new MessageResponse("Ticket Successfully Reassigned"));
		}

		return null;
	}

	@Override
	public List<TicketDTO> getTicketHistory(String username, String role, String fdate, String tdate) {

		if (!fdate.equals("0000-00-00") && !tdate.equals("0000-00-00")) {
			if (role.equalsIgnoreCase("ADMIN")) {
				return ticketRepository.getTicketHistoryByAdminFtdate(fdate, tdate);

			} else if (role.equalsIgnoreCase("OPERATOR")) {
				return ticketRepository.getTicketHistoryByOpidFtdate(username, fdate, tdate);

			} else if (role.equalsIgnoreCase("USER")) {
				return ticketRepository.getTicketHistoryByUserFtdate(username, fdate, tdate);

			} else if (role.equalsIgnoreCase("EMPLOYEE")) {
				return ticketRepository.getTicketHistoryByEmpFtdate(username, fdate, tdate);
			}
		} else {
			if (role.equalsIgnoreCase("ADMIN")) {
				return ticketRepository.getTicketHistoryByAdmin();

			} else if (role.equalsIgnoreCase("OPERATOR")) {
				return ticketRepository.getTicketHistoryByOpid(username);

			} else if (role.equalsIgnoreCase("USER")) {
				return ticketRepository.getTicketHistoryByUser(username);

			} else if (role.equalsIgnoreCase("EMPLOYEE")) {
				return ticketRepository.getTicketHistoryByEmp(username);
			}
		}
		return null;

	}

	@Override
	public void setRatingForTicket(String ticketid, String rating) {
		ticketRepository.setRatingForTicket(ticketid, Integer.parseInt(rating));
	}

}
