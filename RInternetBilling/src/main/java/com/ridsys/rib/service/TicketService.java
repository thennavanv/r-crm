package com.ridsys.rib.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.ridsys.rib.DTO.TicketDTO;
import com.ridsys.rib.DTO.TicketDetailDTO;
import com.ridsys.rib.DTO.TicketSubjectTypeDTO;
import com.ridsys.rib.models.Tickets;

public interface TicketService {

	Map<String, List<TicketSubjectTypeDTO>> getTicketSubjectList();

	ResponseEntity<?> createTicket(Tickets obj);

	TicketDetailDTO getCurrentTicketDetails(String username, int subjectid, int typeid);

	List<TicketDTO> getTicketList(String username, String role, int status,String fdate,String tdate);

	Map<String, Integer> getTicketcountByStatus(String username, String role);

	Map<String,Object> getTicketDetailByCode(String ticketcode);

	ResponseEntity<?> employeeAssign(String empid, String ticketcode, String status, String comments);

	List<TicketDTO> getTicketHistory(String username, String role,String fdate,String tdate);

	void setRatingForTicket(String ticketid, String rating);

}
