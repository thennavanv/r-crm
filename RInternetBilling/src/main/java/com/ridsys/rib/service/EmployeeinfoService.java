package com.ridsys.rib.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ridsys.rib.models.Employeeinfo;

public interface EmployeeinfoService {

	public ResponseEntity<?> createEmployee(Employeeinfo obj);

	List<?> getEmployeeTicketwithLimit(String username);

	List<Employeeinfo> getEmployeeList();

}
