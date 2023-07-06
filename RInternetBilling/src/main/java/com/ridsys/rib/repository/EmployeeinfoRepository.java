package com.ridsys.rib.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ridsys.rib.DTO.TicketSubjectTypeDTO;
import com.ridsys.rib.models.Employeeinfo;

@Repository
public interface EmployeeinfoRepository extends JpaRepository<Employeeinfo, Long> {

	Boolean existsByUsername(String username);
	
	Boolean existsByMobilephone(String mobile);
	
	Boolean existsByEmpid(int empid);
	
	Boolean existsByEmail(String email);
	
	List<Employeeinfo> findAllByIsdelete(boolean isdelete);
	
	@Query(nativeQuery=true)
	public List<TicketSubjectTypeDTO> getEmployeeList();
}
