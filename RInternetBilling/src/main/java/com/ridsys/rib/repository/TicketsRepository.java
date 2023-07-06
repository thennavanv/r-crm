package com.ridsys.rib.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ridsys.rib.DTO.TicketDTO;
import com.ridsys.rib.DTO.TicketDetailDTO;
import com.ridsys.rib.DTO.TicketSubjectTypeDTO;
import com.ridsys.rib.models.Tickets;

@Repository
public interface TicketsRepository extends JpaRepository<Tickets, Long> {

	@Query(nativeQuery = true)
	List<TicketSubjectTypeDTO> getTicketSubjectList();

	@Query(nativeQuery = true)
	List<TicketSubjectTypeDTO> getTicketTypeList();

	@Query(value = "SELECT if(MAX(id) is null,0,MAX(id))as maxid FROM tickets", nativeQuery = true)
	Integer getMaxTicketID();

	@Query(nativeQuery = true)
	TicketDetailDTO getTicketDetails(String username, int subjectid, int typeid);

	@Query(nativeQuery = true)
	List<TicketDTO> getTicketListByAdminFtdate(int status, String fdate, String tdate);

	@Query(nativeQuery = true)
	List<TicketDTO> getTicketListByAdmin(int status);

	@Query(nativeQuery = true)
	List<TicketDTO> getTicketListByOpidFtdate(String username, int status, String fdate, String tdate);

	@Query(nativeQuery = true)
	List<TicketDTO> getTicketListByOpid(String username, int status);

	@Query(nativeQuery = true)
	List<TicketDTO> getTicketListByUserFtdate(String username, int status, String fdate, String tdate);

	@Query(nativeQuery = true)
	List<TicketDTO> getTicketListByUser(String username, int status);

	@Query(nativeQuery = true)
	List<TicketDTO> getTicketListByEmpFtdate(String username, int status, String fdate, String tdate);

	@Query(nativeQuery = true)
	List<TicketDTO> getTicketListByEmp(String username, int status);

	@Query(nativeQuery = true)
	List<TicketDTO> getNewReassignTickListByAdminFtdate(int status, int reassign, String fdate, String tdate);

	@Query(nativeQuery = true)
	List<TicketDTO> getNewReassignTickListByAdmin(int status, int reassign);

	@Query(nativeQuery = true)
	List<TicketDTO> getNewReassignTickListByOpidFtdate(String username, int status, int reassign, String fdate,
			String tdate);

	@Query(nativeQuery = true)
	List<TicketDTO> getNewReassignTickListByOpid(String username, int status, int reassign);

	@Query(nativeQuery = true)
	List<TicketDTO> getNewReassignTickListByUserFtdate(String username, int status, int reassign, String fdate,
			String tdate);

	@Query(nativeQuery = true)
	List<TicketDTO> getNewReassignTickListByUser(String username, int status, int reassign);

	@Query(nativeQuery = true)
	List<TicketDTO> getReassignTickListByEmp(String username);

	@Query(nativeQuery = true)
	List<TicketDTO> getTotalTicketByAdminFtdate(String fdate, String tdate);

	@Query(nativeQuery = true)
	List<TicketDTO> getTotalTicketByAdmin();

	@Query(nativeQuery = true)
	List<TicketDTO> getTotalTicketByOpidFtdate(String username, String fdate, String tdate);

	@Query(nativeQuery = true)
	List<TicketDTO> getTotalTicketByOpid(String username);

	@Query(nativeQuery = true)
	List<TicketDTO> getTotalTicketByUserFtdate(String username, String fdate, String tdate);

	@Query(nativeQuery = true)
	List<TicketDTO> getTotalTicketByUser(String username);

	@Query(nativeQuery = true)
	List<TicketDTO> getTotalTicketByEmp(String username);

	@Query(nativeQuery = true)
	TicketDTO getTicketDetailByCode(String ticketcode);

	@Query(nativeQuery = true)
	List<TicketDTO> getTicketHistoryByAdminFtdate(String fdate, String tdate);

	@Query(nativeQuery = true)
	List<TicketDTO> getTicketHistoryByAdmin();

	@Query(nativeQuery = true)
	List<TicketDTO> getTicketHistoryByOpidFtdate(String username, String fdate, String tdate);

	@Query(nativeQuery = true)
	List<TicketDTO> getTicketHistoryByOpid(String username);

	@Query(nativeQuery = true)
	List<TicketDTO> getTicketHistoryByUserFtdate(String username, String fdate, String tdate);

	@Query(nativeQuery = true)
	List<TicketDTO> getTicketHistoryByUser(String username);

	@Query(nativeQuery = true)
	List<TicketDTO> getTicketHistoryByEmpFtdate(String username, String fdate, String tdate);

	@Query(nativeQuery = true)
	List<TicketDTO> getTicketHistoryByEmp(String username);

	@Query(nativeQuery = true)
	List<TicketDTO> getEmployeeTicketwithLimit(String username);

	@Query(value = "UPDATE tickets SET emp_id=:empid,status=2,assigneddate=now() WHERE code=:ticketcode", nativeQuery = true)
	void ticketAssignforEmp(long empid, String ticketcode);

	@Query(value = "UPDATE tickets SET status=3,accepteddate=now() WHERE code=:ticketcode", nativeQuery = true)
	void ticketOpenforEmp(String ticketcode);

	@Query(value = "UPDATE tickets SET status=4,finisheddate=now(),empcomments=:comments WHERE code=:ticketcode", nativeQuery = true)
	void ticketFinished(String ticketcode, String comments);

	@Query(value = "UPDATE tickets SET status=5,closeddate=now(),clientcomments=:comments WHERE code=:ticketcode", nativeQuery = true)
	void ticketClosed(String ticketcode, String comments);

	@Query(value = "UPDATE tickets SET status=1,reassigneddate=now(),clientcomments=:comments,isreassign=1 WHERE code=:ticketcode", nativeQuery = true)
	void ticketReassignforEmp(String ticketcode, String comments);

	@Query(value = "UPDATE tickets SET rating=:rating WHERE code=:ticketcode", nativeQuery = true)
	void setRatingForTicket(String ticketcode, int rating);
//	

//	@Query(value = "SELECT COUNT(id) FROM tickets WHERE isdelete=0 AND isreassign=:reassign AND status=:status", nativeQuery = true)
//	Integer getReassignCountByAdmin(int status, int reassign);
//
//	@Query(value = "SELECT COUNT(t.id) FROM tickets t LEFT JOIN operators op ON op.id=t.opid WHERE op.username=:username AND isreassign=:reassign AND t.status=:status", nativeQuery = true)
//	Integer getReassignCountByOpid(String username, int status, int reassign);
//
//	@Query(value = "SELECT COUNT(id) FROM tickets WHERE username=:username AND isreassign=:reassign AND status=:status", nativeQuery = true)
//	Integer getReassignCountByUser(String username, int status, int reassign);
}
