package com.ridsys.rib.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ridsys.rib.models.Employeeinfo;
import com.ridsys.rib.service.EmployeeinfoService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

	private EmployeeinfoService service;

	public EmployeeController(EmployeeinfoService service) {
		super();
		this.service = service;
	}

	@PostMapping("/create")
	public ResponseEntity<?> createEmployee(@Valid @RequestBody Employeeinfo obj) {
		return service.createEmployee(obj);
	}

	@GetMapping("/employeeTicket")
	public List<?> getEmployeeTickets(@Valid @RequestParam("username") String username) {
		return service.getEmployeeTicketwithLimit(username);

	}

	@GetMapping("/list")
	public List<Employeeinfo> getEmployeeList() {
		return service.getEmployeeList();
	}

//	@GetMapping("/test")
//	public void getTicketDetails() {
//
//		HttpHeaders headers = new HttpHeaders();
//		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//		RestTemplate rt = new RestTemplate();
//		HttpEntity<String> entity = new HttpEntity<String>(headers);
//		String result = rt.exchange("http://localhost:8080/R-OTT/getActiveAdds.do?request_ip=192.168.1.108&screen=HOME",
//				HttpMethod.GET, entity,String.class).getBody();
////
////
//		ObjectMapper objectMapper = new ObjectMapper();
//		Map<String, Object> jsonMap = objectMapper.readValue(result,
//		    new TypeReference<Map<String, Object>>(){});
////		
//		System.out.println("result---" + result);
//
////	}
//
//		String uri="http://localhost:8080/R-OTT/getActiveAdds.do?request_ip=192.168.1.108&screen=HOME";
//		RestTemplate rt=new RestTemplate();
//		String result=rt.getForObject(uri,String.class);
////		

//	}
}
