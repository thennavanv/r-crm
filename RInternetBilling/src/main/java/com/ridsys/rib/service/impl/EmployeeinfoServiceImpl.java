package com.ridsys.rib.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ridsys.rib.DTO.TicketDTO;
import com.ridsys.rib.comm.Dateformat;
import com.ridsys.rib.models.ERole;
import com.ridsys.rib.models.Employeeinfo;
import com.ridsys.rib.models.Role;
import com.ridsys.rib.models.User;
import com.ridsys.rib.payload.response.MessageResponse;
import com.ridsys.rib.repository.EmployeeinfoRepository;
import com.ridsys.rib.repository.RoleRepository;
import com.ridsys.rib.repository.TicketsRepository;
import com.ridsys.rib.repository.UserRepository;
import com.ridsys.rib.service.EmployeeinfoService;

@Service
public class EmployeeinfoServiceImpl implements EmployeeinfoService {

	@Autowired
	EmployeeinfoRepository employeeinfoRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	private TicketsRepository ticketRepository;

	@Override
	public ResponseEntity<?> createEmployee(Employeeinfo obj) {

		if (obj.getUsername() == "" || obj.getPassword() == "") {
			return ResponseEntity.badRequest().body(new MessageResponse("username or password are empty"));

		}
		if (obj.getUsername().length() < 6) {
			return ResponseEntity.badRequest().body(new MessageResponse("The username length should be 6 or more!."));

		}
		if (obj.getPassword().length() < 6) {
			return ResponseEntity.badRequest().body(new MessageResponse("The password length should be 6 or more!."));

		}

		if (employeeinfoRepository.existsByUsername(obj.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("The username is already in use!"));

		}
		if (employeeinfoRepository.existsByEmpid(obj.getEmpid())) {
			return ResponseEntity.badRequest().body(new MessageResponse("The employee ID is already in use!"));

		}
		if (employeeinfoRepository.existsByMobilephone(obj.getMobilephone())) {
			return ResponseEntity.badRequest().body(new MessageResponse("The mobile number is already in use!"));

		}
		if (employeeinfoRepository.existsByEmail(obj.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("The mail Id is already in use!"));

		}
		if (userRepository.existsByUsername(obj.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}
		if (userRepository.existsByEmail(obj.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}
		String currentdatetime = Dateformat.getCurrentDatetime();
		obj.setCreateddate(currentdatetime);
		obj.setCreationby("admin");
		obj.setIsactive(true);
		employeeinfoRepository.save(obj);

		Set<Role> roles = new HashSet<>();
	 	Role empRole = roleRepository.findByName(ERole.ROLE_EMPLOYEE)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		roles.add(empRole);

		// Create new user's account
		User user = new User(obj.getUsername(), obj.getEmail(), encoder.encode(obj.getPassword()));
		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("New Employee created successfully!"));

	}

	@Override
	public List<Map<String, Object>> getEmployeeTicketwithLimit(String username) {
		List<TicketDTO> lst = ticketRepository.getEmployeeTicketwithLimit(username);
		List<Map<String, Object>> lst1 = new ArrayList<>();
		for (TicketDTO t : lst) {
			Map<String, Object> map = new HashMap<>();
			map.put("ticketDiscription", t.getTicketDescription());
			map.put("subject", t.getSubject());
			map.put("type", t.getType());
			map.put("status", t.getStatus());
			map.put("createddate", t.getCreateddate());
			map.put("time", Dateformat.timeCalculate(t.getCloseddate()));

			lst1.add(map);
		}
		return lst1;
	}

	@Override
	public List<Employeeinfo> getEmployeeList() {
		return employeeinfoRepository.findAllByIsdelete(false);
	}
}
