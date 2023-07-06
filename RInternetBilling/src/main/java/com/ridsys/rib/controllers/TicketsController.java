package com.ridsys.rib.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ridsys.rib.DTO.TicketDTO;
import com.ridsys.rib.DTO.TicketDetailDTO;
import com.ridsys.rib.DTO.TicketSubjectTypeDTO;
import com.ridsys.rib.models.Tickets;
import com.ridsys.rib.service.TicketService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/ticket")
public class TicketsController {

	private TicketService service;

	public TicketsController(TicketService service) {
		super();
		this.service = service;
	}

	@GetMapping("/property")
	public Map<String, List<TicketSubjectTypeDTO>> getTicketSubjectList() {
		return service.getTicketSubjectList();
	}

	@PostMapping("/create")
	public ResponseEntity<?> createTicket(@Valid @RequestBody Tickets obj) {
		return service.createTicket(obj);
	}

	@GetMapping("/ticketDetail")
	public TicketDetailDTO getCurrentTicketDetails(@Valid @RequestParam("username") String username,
			@RequestParam("typeid") int typeid, @RequestParam("subjectid") int subjectid) {

		return service.getCurrentTicketDetails(username, subjectid, typeid);
	}

	@GetMapping("/list")
	public List<TicketDTO> getTicketList(@Valid @RequestParam("role") String role,
			@RequestParam("username") String username, @RequestParam("status") int status,
			@RequestParam("fdate") String fdate, @RequestParam("tdate") String tdate) {
		return service.getTicketList(username, role, status, fdate, tdate);
	}

	@GetMapping("/count")
	public Map<String, Integer> getTicketcountByStatus(@Valid @RequestParam("role") String role,
			@RequestParam("username") String username) {
		return service.getTicketcountByStatus(username, role);
	}

	@GetMapping("/detail")
	public Map<String, Object> getTicketDetailByCode(@Valid @RequestParam("ticketid") String ticketcode) {
		return service.getTicketDetailByCode(ticketcode);
	}

	@PostMapping("/ticketUpdate")
	public ResponseEntity<?> employeeAssign(@Valid @RequestBody Map<String, String> obj) {
		return service.employeeAssign(obj.get("employeeid"), obj.get("ticketid"), obj.get("status"),
				obj.get("comments"));
	}

	@GetMapping("/history")
	public List<TicketDTO> getTicketHistory(@Valid @RequestParam("role") String role,
			@RequestParam("username") String username, @RequestParam("fdate") String fdate,
			@RequestParam("tdate") String tdate) {
		return service.getTicketHistory(username, role, fdate, tdate);
	}

	@PostMapping("/rating")
	public void setRatingForTicket(@Valid @RequestBody Map<String, String> obj) {
		service.setRatingForTicket(obj.get("ticketid"), obj.get("rating"));
	}

}
