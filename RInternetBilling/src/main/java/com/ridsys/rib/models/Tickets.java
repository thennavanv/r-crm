package com.ridsys.rib.models;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ridsys.rib.DTO.TicketDTO;
import com.ridsys.rib.DTO.TicketDetailDTO;
import com.ridsys.rib.DTO.TicketSubjectTypeDTO;

@Entity
@Table(name = "tickets")
@NamedNativeQuery(name = "Tickets.getTicketSubjectList", query = "SELECT id,subjectname AS name FROM ticket_subject WHERE isdelete=0", resultSetMapping = "Mapping.TicketSubjectTypeDTO")
@NamedNativeQuery(name = "Tickets.getTicketTypeList", query = "SELECT id,typename AS name FROM ticket_type WHERE isdelete=0", resultSetMapping = "Mapping.TicketSubjectTypeDTO")
@SqlResultSetMapping(name = "Mapping.TicketSubjectTypeDTO", classes = {
		@ConstructorResult(targetClass = TicketSubjectTypeDTO.class, columns = {
				@ColumnResult(name = "id", type = Integer.class), @ColumnResult(name = "name") }) })

@NamedNativeQuery(name = "Tickets.getTicketDetails", query = "SELECT(SELECT COUNT(id)+1 FROM tickets WHERE username=:username)AS ticketcount,(SELECT subjectname FROM ticket_subject WHERE id=:subjectid)AS subjectname,(SELECT typename FROM ticket_type WHERE id=:typeid)AS typename", resultSetMapping = "Mapping.TicketDetailDTO")
@SqlResultSetMapping(name = "Mapping.TicketDetailDTO", classes = {
		@ConstructorResult(targetClass = TicketDetailDTO.class, columns = {
				@ColumnResult(name = "ticketcount", type = Long.class), @ColumnResult(name = "subjectname"),
				@ColumnResult(name = "typename") }) })

//
@NamedNativeQuery(name = "Tickets.getTicketDetailByCode", query = "SELECT ui.vlanid,t.username,op.username AS opname,tt.typename,ts.subjectname FROM tickets t LEFT JOIN userinfo ui ON ui.username=t.username LEFT JOIN operators op ON op.id=t.opid LEFT JOIN ticket_type tt ON tt.id=t.type_id LEFT JOIN ticket_subject ts ON ts.id=t.subject_id WHERE t.code=:ticketcode", resultSetMapping = "Mapping.TicketDetailBycode")
@SqlResultSetMapping(name = "Mapping.TicketDetailBycode", classes = {
		@ConstructorResult(targetClass = TicketDTO.class, columns = {
				@ColumnResult(name = "vlanid", type = String.class), @ColumnResult(name = "username"),
				@ColumnResult(name = "opname"), @ColumnResult(name = "subjectname"),
				@ColumnResult(name = "typename") }) })

@NamedNativeQuery(name = "Tickets.getEmployeeTicketwithLimit", query = "SELECT t.id,t.ticket_description,t.createddate,tt.typename,ts.subjectname,date_format(t.createddate,'%d %M %Y') as createddate1,CASE WHEN (t.status=1 and t.isreassign=0) THEN   \"New\" WHEN t.status=2 THEN   \"Assigned\" WHEN t.status=3 THEN   \"Opened\" WHEN t.status=4 THEN   \"Finished\" WHEN t.status=5 THEN   \"Closed\"  WHEN (t.status=1 and t.isreassign=1) THEN   \"Reassigned\" END AS status FROM ticket_history t  LEFT JOIN ticket_type tt ON tt.id=t.type_id LEFT JOIN ticket_subject ts ON ts.id=t.subject_id LEFT JOIN employeeinfo e ON e.id=t.emp_id WHERE  t.isdelete=0 AND e.username=:username AND t.status IN (4,2,5) ORDER BY t.id DESC LIMIT 3;", resultSetMapping = "Mapping.EmpticketDetails")
@SqlResultSetMapping(name = "Mapping.EmpticketDetails", classes = {
		@ConstructorResult(targetClass = TicketDTO.class, columns = { @ColumnResult(name = "ticket_description"),
				@ColumnResult(name = "typename"), @ColumnResult(name = "subjectname"),
				@ColumnResult(name = "createddate1"), @ColumnResult(name = "status"),
				@ColumnResult(name = "createddate", type = String.class) }) })

@NamedNativeQuery(name = "Tickets.getTotalTicketByAdminFtdate", query = "SELECT t.id,t.rating,t.opid,ui.id AS customerid,ui.vlanid,t.code,t.username,e.username AS employeename,t.ticket_description AS ticketdescription,ts.subjectname,tt.typename,op.username AS opname,t.clientcomments,t.empcomments,t.createddate,t.assigneddate,t.accepteddate,t.finisheddate,t.closeddate,CASE WHEN (t.status=1 and t.isreassign=0) THEN \"New\" WHEN t.status=2 THEN \"Assigned\" WHEN t.status=3 THEN \"Opened\" WHEN t.status=4 THEN \"Finished\" WHEN t.status=5 THEN \"Closed\"  WHEN (t.status=1 and t.isreassign=1) THEN \"Reassigned\" END AS status,t.isreassign,t.isactive FROM tickets t LEFT JOIN operators op ON op.id=t.opid LEFT JOIN userinfo ui ON ui.username=t.username LEFT JOIN ticket_type tt ON tt.id=t.type_id LEFT JOIN ticket_subject ts ON ts.id=t.subject_id LEFT JOIN employeeinfo e ON e.id=t.emp_id WHERE  t.isdelete=0 AND DATE(t.createddate)>=:fdate AND DATE(t.createddate)<=:tdate OR  DATE(t.assigneddate)>=:fdate AND DATE(t.assigneddate)<=:tdate OR  DATE(t.accepteddate)>=:fdate AND DATE(t.accepteddate)<=:tdate OR  DATE(t.finisheddate)>=:fdate AND DATE(t.finisheddate)<=:tdate OR  DATE(t.closeddate)>=:fdate AND DATE(t.closeddate)<=:tdate", resultSetMapping = "Mapping.TicketDTO")
@NamedNativeQuery(name = "Tickets.getTotalTicketByAdmin", query = "SELECT t.id,t.rating,t.opid,ui.id AS customerid,ui.vlanid,t.code,t.username,e.username AS employeename,t.ticket_description AS ticketdescription,ts.subjectname,tt.typename,op.username AS opname,t.clientcomments,t.empcomments,t.createddate,t.assigneddate,t.accepteddate,t.finisheddate,t.closeddate,CASE WHEN (t.status=1 and t.isreassign=0) THEN \"New\" WHEN t.status=2 THEN \"Assigned\" WHEN t.status=3 THEN \"Opened\" WHEN t.status=4 THEN \"Finished\" WHEN t.status=5 THEN \"Closed\"  WHEN (t.status=1 and t.isreassign=1) THEN \"Reassigned\" END AS status,t.isreassign,t.isactive FROM tickets t LEFT JOIN operators op ON op.id=t.opid LEFT JOIN userinfo ui ON ui.username=t.username LEFT JOIN ticket_type tt ON tt.id=t.type_id LEFT JOIN ticket_subject ts ON ts.id=t.subject_id LEFT JOIN employeeinfo e ON e.id=t.emp_id WHERE  t.isdelete=0", resultSetMapping = "Mapping.TicketDTO")
@NamedNativeQuery(name = "Tickets.getTotalTicketByOpidFtdate", query = "SELECT t.id,t.rating,t.opid,ui.id AS customerid,ui.vlanid,t.code,t.username,e.username AS employeename,t.ticket_description AS ticketdescription,ts.subjectname,tt.typename,op.username AS opname,t.clientcomments,t.empcomments,t.createddate,t.assigneddate,t.accepteddate,t.finisheddate,t.closeddate,CASE WHEN (t.status=1 and t.isreassign=0) THEN \\\"New\\\" WHEN t.status=2 THEN \\\"Assigned\\\" WHEN t.status=3 THEN \\\"Opened\\\" WHEN t.status=4 THEN \\\"Finished\\\" WHEN t.status=5 THEN \\\"Closed\\\"  WHEN (t.status=1 and t.isreassign=1) THEN \\\"Reassigned\\\" END AS status,t.isreassign,t.isactive FROM tickets t LEFT JOIN operators op ON op.id=t.opid LEFT JOIN userinfo ui ON ui.username=t.username LEFT JOIN ticket_type tt ON tt.id=t.type_id LEFT JOIN ticket_subject ts ON ts.id=t.subject_id LEFT JOIN employeeinfo e ON e.id=t.emp_id WHERE  t.isdelete=0 AND op.username=:username  AND DATE(t.createddate)>=:fdate AND DATE(t.createddate)<=:tdate OR  DATE(t.assigneddate)>=:fdate AND DATE(t.assigneddate)<=:tdate OR  DATE(t.accepteddate)>=:fdate AND DATE(t.accepteddate)<=:tdate OR  DATE(t.finisheddate)>=:fdate AND DATE(t.finisheddate)<=:tdate OR  DATE(t.closeddate)>=:fdate AND DATE(t.closeddate)<=:tdate", resultSetMapping = "Mapping.TicketDTO")
@NamedNativeQuery(name = "Tickets.getTotalTicketByOpid", query = "SELECT t.id,t.rating,t.opid,ui.id AS customerid,ui.vlanid,t.code,t.username,e.username AS employeename,t.ticket_description AS ticketdescription,ts.subjectname,tt.typename,op.username AS opname,t.clientcomments,t.empcomments,t.createddate,t.assigneddate,t.accepteddate,t.finisheddate,t.closeddate,CASE WHEN (t.status=1 and t.isreassign=0) THEN \"New\" WHEN t.status=2 THEN \"Assigned\" WHEN t.status=3 THEN \"Opened\" WHEN t.status=4 THEN \"Finished\" WHEN t.status=5 THEN \"Closed\"  WHEN (t.status=1 and t.isreassign=1) THEN \"Reassigned\" END AS status,t.isreassign,t.isactive FROM tickets t LEFT JOIN operators op ON op.id=t.opid LEFT JOIN userinfo ui ON ui.username=t.username LEFT JOIN ticket_type tt ON tt.id=t.type_id LEFT JOIN ticket_subject ts ON ts.id=t.subject_id LEFT JOIN employeeinfo e ON e.id=t.emp_id WHERE  t.isdelete=0 AND op.username=:username", resultSetMapping = "Mapping.TicketDTO")
@NamedNativeQuery(name = "Tickets.getTotalTicketByUserFtdate", query = "SELECT t.id,t.rating,t.opid,ui.id AS customerid,ui.vlanid,t.code,t.username,e.username AS employeename,t.ticket_description AS ticketdescription,ts.subjectname,tt.typename,op.username AS opname,t.clientcomments,t.empcomments,t.createddate,t.assigneddate,t.accepteddate,t.finisheddate,t.closeddate,CASE WHEN (t.status=1 and t.isreassign=0) THEN \\\\\\\"New\\\\\\\" WHEN t.status=2 THEN \\\\\\\"Assigned\\\\\\\" WHEN t.status=3 THEN \\\\\\\"Opened\\\\\\\" WHEN t.status=4 THEN \\\\\\\"Finished\\\\\\\" WHEN t.status=5 THEN \\\\\\\"Closed\\\\\\\"  WHEN (t.status=1 and t.isreassign=1) THEN \\\\\\\"Reassigned\\\\\\\" END AS status,t.isreassign,t.isactive FROM tickets t LEFT JOIN operators op ON op.id=t.opid LEFT JOIN userinfo ui ON ui.username=t.username LEFT JOIN ticket_type tt ON tt.id=t.type_id LEFT JOIN ticket_subject ts ON ts.id=t.subject_id LEFT JOIN employeeinfo e ON e.id=t.emp_id WHERE  t.isdelete=0 AND t.username=:username  AND DATE(t.createddate)>=:fdate AND DATE(t.createddate)<=:tdate OR  DATE(t.assigneddate)>=:fdate AND DATE(t.assigneddate)<=:tdate OR  DATE(t.accepteddate)>=:fdate AND DATE(t.accepteddate)<=:tdate OR  DATE(t.finisheddate)>=:fdate AND DATE(t.finisheddate)<=:tdate OR  DATE(t.closeddate)>=:fdate AND DATE(t.closeddate)<=:tdate", resultSetMapping = "Mapping.TicketDTO")
@NamedNativeQuery(name = "Tickets.getTotalTicketByUser", query = "SELECT t.id,t.rating,t.opid,ui.id AS customerid,ui.vlanid,t.code,t.username,e.username AS employeename,t.ticket_description AS ticketdescription,ts.subjectname,tt.typename,op.username AS opname,t.clientcomments,t.empcomments,t.createddate,t.assigneddate,t.accepteddate,t.finisheddate,t.closeddate,CASE WHEN (t.status=1 and t.isreassign=0) THEN \\\"New\\\" WHEN t.status=2 THEN \\\"Assigned\\\" WHEN t.status=3 THEN \\\"Opened\\\" WHEN t.status=4 THEN \\\"Finished\\\" WHEN t.status=5 THEN \\\"Closed\\\"  WHEN (t.status=1 and t.isreassign=1) THEN \\\"Reassigned\\\" END AS status,t.isreassign,t.isactive FROM tickets t LEFT JOIN operators op ON op.id=t.opid LEFT JOIN userinfo ui ON ui.username=t.username LEFT JOIN ticket_type tt ON tt.id=t.type_id LEFT JOIN ticket_subject ts ON ts.id=t.subject_id LEFT JOIN employeeinfo e ON e.id=t.emp_id WHERE  t.isdelete=0 AND t.username=:username", resultSetMapping = "Mapping.TicketDTO")
@NamedNativeQuery(name = "Tickets.getTotalTicketByEmp", query = "SELECT t.id,t.rating,t.opid,ui.id AS customerid,ui.vlanid,t.code,t.username,e.username AS employeename,t.ticket_description AS ticketdescription,ts.subjectname,tt.typename,op.username AS opname,t.clientcomments,t.empcomments,t.createddate,t.assigneddate,t.accepteddate,t.finisheddate,t.closeddate,CASE WHEN (t.status=1 and t.isreassign=0) THEN \"New\" WHEN t.status=2 THEN \"Assigned\" WHEN t.status=3 THEN \"Opened\" WHEN t.status=4 THEN \"Finished\" WHEN t.status=5 THEN \"Closed\"  WHEN (t.status=1 and t.isreassign=1) THEN \"Reassigned\" END AS status,t.isreassign,t.isactive FROM tickets t LEFT JOIN operators op ON op.id=t.opid LEFT JOIN userinfo ui ON ui.username=t.username LEFT JOIN ticket_type tt ON tt.id=t.type_id LEFT JOIN ticket_subject ts ON ts.id=t.subject_id LEFT JOIN employeeinfo e ON e.id=t.emp_id WHERE  t.isdelete=0 AND e.username=:username", resultSetMapping = "Mapping.TicketDTO")

@NamedNativeQuery(name = "Tickets.getTicketListByAdmin", query = "SELECT t.id,t.rating,t.opid,ui.id AS customerid,ui.vlanid,t.code,t.username,e.username AS employeename,t.ticket_description AS ticketdescription,ts.subjectname,tt.typename,op.username AS opname,t.clientcomments,t.empcomments,t.createddate,t.assigneddate,t.accepteddate,t.finisheddate,t.closeddate,CASE WHEN t.status=1 THEN \"New\" WHEN t.status=2 THEN \"Assigned\" WHEN t.status=3 THEN \"Opened\" WHEN t.status=4 THEN \"Finished\" WHEN t.status=5 THEN \"Closed\" END AS status,t.isreassign,t.isactive FROM tickets t LEFT JOIN operators op ON op.id=t.opid LEFT JOIN userinfo ui ON ui.username=t.username LEFT JOIN ticket_type tt ON tt.id=t.type_id LEFT JOIN ticket_subject ts ON ts.id=t.subject_id LEFT JOIN employeeinfo e ON e.id=t.emp_id WHERE t.status=:status AND t.isdelete=0", resultSetMapping = "Mapping.TicketDTO")
@NamedNativeQuery(name = "Tickets.getTicketListByAdminFtdate", query = "SELECT t.id,t.rating,t.opid,ui.id AS customerid,ui.vlanid,t.code,t.username,e.username AS employeename,t.ticket_description AS ticketdescription,ts.subjectname,tt.typename,op.username AS opname,t.clientcomments,t.empcomments,t.createddate,t.assigneddate,t.accepteddate,t.finisheddate,t.closeddate,CASE WHEN t.status=1 THEN \\\"New\\\" WHEN t.status=2 THEN \\\"Assigned\\\" WHEN t.status=3 THEN \\\"Opened\\\" WHEN t.status=4 THEN \\\"Finished\\\" WHEN t.status=5 THEN \\\"Closed\\\" END AS status,t.isreassign,t.isactive FROM tickets t LEFT JOIN operators op ON op.id=t.opid LEFT JOIN userinfo ui ON ui.username=t.username LEFT JOIN ticket_type tt ON tt.id=t.type_id LEFT JOIN ticket_subject ts ON ts.id=t.subject_id LEFT JOIN employeeinfo e ON e.id=t.emp_id WHERE t.status=:status AND t.isdelete=0 AND IF(t.status=2,DATE(assigneddate)>=:fdate AND DATE(assigneddate)<=:tdate,IF(t.status=3,DATE(accepteddate)>=:fdate AND DATE(accepteddate)<=:tdate,IF(t.status=4,DATE(finisheddate)>=:fdate AND DATE(finisheddate)<=:tdate,IF(status=5,DATE(closeddate)>=:fdate AND DATE(closeddate)<=:tdate,DATE(t.createddate)>=:fdate AND DATE(t.createddate)<=:tdate)))) ", resultSetMapping = "Mapping.TicketDTO")
@NamedNativeQuery(name = "Tickets.getTicketListByOpid", query = "SELECT t.id,t.rating,t.opid,ui.id AS customerid,ui.vlanid,t.code,t.username,e.username AS employeename,t.ticket_description AS ticketdescription,ts.subjectname,tt.typename,op.username AS opname,t.clientcomments,t.empcomments,t.createddate,t.assigneddate,t.accepteddate,t.finisheddate,t.closeddate,CASE WHEN t.status=1 THEN \\\"New\\\" WHEN t.status=2 THEN \\\"Assigned\\\" WHEN t.status=3 THEN \\\"Opened\\\" WHEN t.status=4 THEN \\\"Finished\\\" WHEN t.status=5 THEN \\\"Closed\\\" END AS status,t.isreassign,t.isactive FROM tickets t LEFT JOIN operators op ON op.id=t.opid LEFT JOIN userinfo ui ON ui.username=t.username LEFT JOIN ticket_type tt ON tt.id=t.type_id LEFT JOIN ticket_subject ts ON ts.id=t.subject_id LEFT JOIN employeeinfo e ON e.id=t.emp_id WHERE t.status=:status AND op.username=:username AND t.isdelete=0", resultSetMapping = "Mapping.TicketDTO")
@NamedNativeQuery(name = "Tickets.getTicketListByOpidFtdate", query = "SELECT t.id,t.rating,t.opid,ui.id AS customerid,ui.vlanid,t.code,t.username,e.username AS employeename,t.ticket_description AS ticketdescription,ts.subjectname,tt.typename,op.username AS opname,t.clientcomments,t.empcomments,t.createddate,t.assigneddate,t.accepteddate,t.finisheddate,t.closeddate,CASE WHEN t.status=1 THEN \\\\\\\"New\\\\\\\" WHEN t.status=2 THEN \\\\\\\"Assigned\\\\\\\" WHEN t.status=3 THEN \\\\\\\"Opened\\\\\\\" WHEN t.status=4 THEN \\\\\\\"Finished\\\\\\\" WHEN t.status=5 THEN \\\\\\\"Closed\\\\\\\" END AS status,t.isreassign,t.isactive FROM tickets t LEFT JOIN operators op ON op.id=t.opid LEFT JOIN userinfo ui ON ui.username=t.username LEFT JOIN ticket_type tt ON tt.id=t.type_id LEFT JOIN ticket_subject ts ON ts.id=t.subject_id LEFT JOIN employeeinfo e ON e.id=t.emp_id WHERE t.status=:status AND op.username=:username AND t.isdelete=0  AND IF(t.status=2,DATE(assigneddate)>=:fdate AND DATE(assigneddate)<=:tdate,IF(t.status=3,DATE(accepteddate)>=:fdate AND DATE(accepteddate)<=:tdate,IF(t.status=4,DATE(finisheddate)>=:fdate AND DATE(finisheddate)<=:tdate,IF(status=5,DATE(closeddate)>=:fdate AND DATE(closeddate)<=:tdate,DATE(t.createddate)>=:fdate AND DATE(t.createddate)<=:tdate))))", resultSetMapping = "Mapping.TicketDTO")
@NamedNativeQuery(name = "Tickets.getTicketListByUser", query = "SELECT t.id,t.rating,t.opid,ui.id AS customerid,ui.vlanid,t.code,t.username,e.username AS employeename,t.ticket_description AS ticketdescription,ts.subjectname,tt.typename,op.username AS opname,t.clientcomments,t.empcomments,t.createddate,t.assigneddate,t.accepteddate,t.finisheddate,t.closeddate,CASE WHEN t.status=1 THEN \\\"New\\\" WHEN t.status=2 THEN \\\"Assigned\\\" WHEN t.status=3 THEN \\\"Opened\\\" WHEN t.status=4 THEN \\\"Finished\\\" WHEN t.status=5 THEN \\\"Closed\\\" END AS status,t.isreassign,t.isactive FROM tickets t LEFT JOIN operators op ON op.id=t.opid LEFT JOIN userinfo ui ON ui.username=t.username LEFT JOIN ticket_type tt ON tt.id=t.type_id LEFT JOIN ticket_subject ts ON ts.id=t.subject_id LEFT JOIN employeeinfo e ON e.id=t.emp_id WHERE t.status=:status AND t.username=:username AND t.isdelete=0", resultSetMapping = "Mapping.TicketDTO")
@NamedNativeQuery(name = "Tickets.getTicketListByUserFtdate", query = "SELECT t.id,t.rating,t.opid,ui.id AS customerid,ui.vlanid,t.code,t.username,e.username AS employeename,t.ticket_description AS ticketdescription,ts.subjectname,tt.typename,op.username AS opname,t.clientcomments,t.empcomments,t.createddate,t.assigneddate,t.accepteddate,t.finisheddate,t.closeddate,CASE WHEN t.status=1 THEN \\\\\\\"New\\\\\\\" WHEN t.status=2 THEN \\\\\\\"Assigned\\\\\\\" WHEN t.status=3 THEN \\\\\\\"Opened\\\\\\\" WHEN t.status=4 THEN \\\\\\\"Finished\\\\\\\" WHEN t.status=5 THEN \\\\\\\"Closed\\\\\\\" END AS status,t.isreassign,t.isactive FROM tickets t LEFT JOIN operators op ON op.id=t.opid LEFT JOIN userinfo ui ON ui.username=t.username LEFT JOIN ticket_type tt ON tt.id=t.type_id LEFT JOIN ticket_subject ts ON ts.id=t.subject_id LEFT JOIN employeeinfo e ON e.id=t.emp_id WHERE t.status=:status AND t.username=:username AND t.isdelete=0   AND IF(t.status=2,DATE(assigneddate)>=:fdate AND DATE(assigneddate)<=:tdate,IF(t.status=3,DATE(accepteddate)>=:fdate AND DATE(accepteddate)<=:tdate,IF(t.status=4,DATE(finisheddate)>=:fdate AND DATE(finisheddate)<=:tdate,IF(status=5,DATE(closeddate)>=:fdate AND DATE(closeddate)<=:tdate,DATE(t.createddate)>=:fdate AND DATE(t.createddate)<=:tdate))))", resultSetMapping = "Mapping.TicketDTO")
@NamedNativeQuery(name = "Tickets.getTicketListByEmp", query = "SELECT t.id,t.rating,t.opid,ui.id AS customerid,ui.vlanid,t.code,t.username,e.username AS employeename,t.ticket_description AS ticketdescription,ts.subjectname,tt.typename,op.username AS opname,t.clientcomments,t.empcomments,t.createddate,t.assigneddate,t.accepteddate,t.finisheddate,t.closeddate,CASE WHEN t.status=1 THEN \"New\" WHEN t.status=2 THEN \"Assigned\" WHEN t.status=3 THEN \"Opened\" WHEN t.status=4 THEN \"Finished\" WHEN t.status=5 THEN \"Closed\" END AS status,t.isreassign,t.isactive FROM tickets t LEFT JOIN operators op ON op.id=t.opid LEFT JOIN userinfo ui ON ui.username=t.username LEFT JOIN ticket_type tt ON tt.id=t.type_id LEFT JOIN ticket_subject ts ON ts.id=t.subject_id LEFT JOIN employeeinfo e ON e.id=t.emp_id WHERE t.status=:status AND e.username=:username AND t.isdelete=0", resultSetMapping = "Mapping.TicketDTO")
@NamedNativeQuery(name = "Tickets.getTicketListByEmpFtdate", query = "SELECT t.id,t.rating,t.opid,ui.id AS customerid,ui.vlanid,t.code,t.username,e.username AS employeename,t.ticket_description AS ticketdescription,ts.subjectname,tt.typename,op.username AS opname,t.clientcomments,t.empcomments,t.createddate,t.assigneddate,t.accepteddate,t.finisheddate,t.closeddate,CASE WHEN t.status=1 THEN \\\"New\\\" WHEN t.status=2 THEN \\\"Assigned\\\" WHEN t.status=3 THEN \\\"Opened\\\" WHEN t.status=4 THEN \\\"Finished\\\" WHEN t.status=5 THEN \\\"Closed\\\" END AS status,t.isreassign,t.isactive FROM tickets t LEFT JOIN operators op ON op.id=t.opid LEFT JOIN userinfo ui ON ui.username=t.username LEFT JOIN ticket_type tt ON tt.id=t.type_id LEFT JOIN ticket_subject ts ON ts.id=t.subject_id LEFT JOIN employeeinfo e ON e.id=t.emp_id WHERE t.status=:status AND e.username=:username AND t.isdelete=0   AND IF(t.status=2,DATE(assigneddate)>=:fdate AND DATE(assigneddate)<=:tdate,IF(t.status=3,DATE(accepteddate)>=:fdate AND DATE(accepteddate)<=:tdate,IF(t.status=4,DATE(finisheddate)>=:fdate AND DATE(finisheddate)<=:tdate,IF(status=5,DATE(closeddate)>=:fdate AND DATE(closeddate)<=:tdate,DATE(t.createddate)>=:fdate AND DATE(t.createddate)<=:tdate))))", resultSetMapping = "Mapping.TicketDTO")

@NamedNativeQuery(name = "Tickets.getNewReassignTickListByAdminFtdate", query = "SELECT t.id,t.rating,t.opid,ui.id AS customerid,ui.vlanid,t.code,t.username,e.username AS employeename,t.ticket_description AS ticketdescription,ts.subjectname,tt.typename,op.username AS opname,t.clientcomments,t.empcomments,t.createddate,t.assigneddate,t.accepteddate,t.finisheddate,t.closeddate,CASE WHEN t.isreassign=1 THEN \"Reassigned\" WHEN t.isreassign=0 THEN \"New\"  END AS status,t.isreassign,t.isactive FROM tickets t LEFT JOIN operators op ON op.id=t.opid LEFT JOIN userinfo ui ON ui.username=t.username LEFT JOIN ticket_type tt ON tt.id=t.type_id LEFT JOIN ticket_subject ts ON ts.id=t.subject_id LEFT JOIN employeeinfo e ON e.id=t.emp_id WHERE t.status=:status AND t.isdelete=0 AND t.isreassign=:reassign AND if(t.isreassign=1,DATE(t.reassigneddate)>=:fdate and DATE(t.reassigneddate)<=:tdate,DATE(t.createddate)>=:fdate and DATE(t.createddate)<=:tdate)", resultSetMapping = "Mapping.TicketDTO")
@NamedNativeQuery(name = "Tickets.getNewReassignTickListByAdmin", query = "SELECT t.id,t.rating,t.opid,ui.id AS customerid,ui.vlanid,t.code,t.username,e.username AS employeename,t.ticket_description AS ticketdescription,ts.subjectname,tt.typename,op.username AS opname,t.clientcomments,t.empcomments,t.createddate,t.assigneddate,t.accepteddate,t.finisheddate,t.closeddate,CASE WHEN t.isreassign=1 THEN \"Reassigned\" WHEN t.isreassign=0 THEN \"New\"  END AS status,t.isreassign,t.isactive FROM tickets t LEFT JOIN operators op ON op.id=t.opid LEFT JOIN userinfo ui ON ui.username=t.username LEFT JOIN ticket_type tt ON tt.id=t.type_id LEFT JOIN ticket_subject ts ON ts.id=t.subject_id LEFT JOIN employeeinfo e ON e.id=t.emp_id WHERE t.status=:status AND t.isdelete=0 AND t.isreassign=:reassign", resultSetMapping = "Mapping.TicketDTO")
@NamedNativeQuery(name = "Tickets.getNewReassignTickListByOpidFtdate", query = "SELECT t.id,t.rating,t.opid,ui.id AS customerid,ui.vlanid,t.code,t.username,e.username AS employeename,t.ticket_description AS ticketdescription,ts.subjectname,tt.typename,op.username AS opname,t.clientcomments,t.empcomments,t.createddate,t.assigneddate,t.accepteddate,t.finisheddate,t.closeddate,CASE WHEN t.isreassign=1 THEN \\\"Reassigned\\\" WHEN t.isreassign=0 THEN \\\"New\\\"  END AS status,t.isreassign,t.isactive FROM tickets t LEFT JOIN operators op ON op.id=t.opid LEFT JOIN userinfo ui ON ui.username=t.username LEFT JOIN ticket_type tt ON tt.id=t.type_id LEFT JOIN ticket_subject ts ON ts.id=t.subject_id LEFT JOIN employeeinfo e ON e.id=t.emp_id WHERE t.status=:status AND t.isdelete=0 AND t.isreassign=:reassign AND op.username=:username AND if(t.isreassign=1,DATE(t.reassigneddate)>=:fdate and DATE(t.reassigneddate)<=:tdate,DATE(t.createddate)>=:fdate and DATE(t.createddate)<=:tdate)", resultSetMapping = "Mapping.TicketDTO")
@NamedNativeQuery(name = "Tickets.getNewReassignTickListByOpid", query = "SELECT t.id,t.rating,t.opid,ui.id AS customerid,ui.vlanid,t.code,t.username,e.username AS employeename,t.ticket_description AS ticketdescription,ts.subjectname,tt.typename,op.username AS opname,t.clientcomments,t.empcomments,t.createddate,t.assigneddate,t.accepteddate,t.finisheddate,t.closeddate,CASE WHEN t.isreassign=1 THEN \"Reassigned\" WHEN t.isreassign=0 THEN \"New\"  END AS status,t.isreassign,t.isactive FROM tickets t LEFT JOIN operators op ON op.id=t.opid LEFT JOIN userinfo ui ON ui.username=t.username LEFT JOIN ticket_type tt ON tt.id=t.type_id LEFT JOIN ticket_subject ts ON ts.id=t.subject_id LEFT JOIN employeeinfo e ON e.id=t.emp_id WHERE t.status=:status AND t.isdelete=0 AND t.isreassign=:reassign AND op.username=:username", resultSetMapping = "Mapping.TicketDTO")
@NamedNativeQuery(name = "Tickets.getNewReassignTickListByUserFtdate", query = "SELECT t.id,t.rating,t.opid,ui.id AS customerid,ui.vlanid,t.code,t.username,e.username AS employeename,t.ticket_description AS ticketdescription,ts.subjectname,tt.typename,op.username AS opname,t.clientcomments,t.empcomments,t.createddate,t.assigneddate,t.accepteddate,t.finisheddate,t.closeddate,CASE WHEN t.isreassign=1 THEN \\\"Reassigned\\\" WHEN t.isreassign=0 THEN \\\"New\\\"  END AS status,t.isreassign,t.isactive FROM tickets t LEFT JOIN operators op ON op.id=t.opid LEFT JOIN userinfo ui ON ui.username=t.username LEFT JOIN ticket_type tt ON tt.id=t.type_id LEFT JOIN ticket_subject ts ON ts.id=t.subject_id LEFT JOIN employeeinfo e ON e.id=t.emp_id WHERE t.status=:status AND t.isdelete=0 AND t.isreassign=:reassign AND t.username=:username  AND if(t.isreassign=1,DATE(t.reassigneddate)>=:fdate and DATE(t.reassigneddate)<=:tdate,DATE(t.createddate)>=:fdate and DATE(t.createddate)<=:tdate)", resultSetMapping = "Mapping.TicketDTO")
@NamedNativeQuery(name = "Tickets.getNewReassignTickListByUser", query = "SELECT t.id,t.rating,t.opid,ui.id AS customerid,ui.vlanid,t.code,t.username,e.username AS employeename,t.ticket_description AS ticketdescription,ts.subjectname,tt.typename,op.username AS opname,t.clientcomments,t.empcomments,t.createddate,t.assigneddate,t.accepteddate,t.finisheddate,t.closeddate,CASE WHEN t.isreassign=1 THEN \"Reassigned\" WHEN t.isreassign=0 THEN \"New\"  END AS status,t.isreassign,t.isactive FROM tickets t LEFT JOIN operators op ON op.id=t.opid LEFT JOIN userinfo ui ON ui.username=t.username LEFT JOIN ticket_type tt ON tt.id=t.type_id LEFT JOIN ticket_subject ts ON ts.id=t.subject_id LEFT JOIN employeeinfo e ON e.id=t.emp_id WHERE t.status=:status AND t.isdelete=0 AND t.isreassign=:reassign AND t.username=:username", resultSetMapping = "Mapping.TicketDTO")
@NamedNativeQuery(name = "Tickets.getReassignTickListByEmp", query = "SELECT t.id,t.rating,t.opid,ui.id AS customerid,ui.vlanid,t.code,t.username,e.username AS employeename,t.ticket_description AS ticketdescription,ts.subjectname,tt.typename,op.username AS opname,t.clientcomments,t.empcomments,t.createddate,t.assigneddate,t.accepteddate,t.finisheddate,t.closeddate,CASE WHEN t.status=1 THEN \"New\" WHEN t.status=2 THEN \"Assigned\" WHEN t.status=3 THEN \"Opened\" WHEN t.status=4 THEN \"Finished\" WHEN t.status=5 THEN \"Closed\" END AS status,t.isreassign,t.isactive FROM ticket_history t LEFT JOIN operators op ON op.id=t.opid LEFT JOIN userinfo ui ON ui.username=t.username LEFT JOIN ticket_type tt ON tt.id=t.type_id LEFT JOIN ticket_subject ts ON ts.id=t.subject_id LEFT JOIN employeeinfo e ON e.id=t.emp_id WHERE t.status=1 and t.isreassign=1  AND e.username=:username AND t.isdelete=0", resultSetMapping = "Mapping.TicketDTO")

@NamedNativeQuery(name = "Tickets.getTicketHistoryByAdminFtdate", query = "SELECT t.id,if(t.rating is null,0,t.rating) AS rating,t.opid,ui.id AS customerid,ui.vlanid,t.code,t.username,e.username AS employeename,t.ticket_description AS ticketdescription,ts.subjectname,tt.typename,op.username AS opname,t.clientcomments,t.empcomments,t.createddate,t.assigneddate,t.accepteddate,t.finisheddate,t.closeddate,CASE WHEN (t.status=1 and t.isreassign=0) THEN \"New\" WHEN t.status=2 THEN \"Assigned\" WHEN t.status=3 THEN \"Opened\" WHEN t.status=4 THEN \"Finished\" WHEN t.status=5 THEN \"Closed\"  WHEN (t.status=1 and t.isreassign=1) THEN \"Reassigned\" END AS status,t.isreassign,t.isactive FROM ticket_history t LEFT JOIN operators op ON op.id=t.opid LEFT JOIN userinfo ui ON ui.username=t.username LEFT JOIN ticket_type tt ON tt.id=t.type_id LEFT JOIN ticket_subject ts ON ts.id=t.subject_id LEFT JOIN employeeinfo e ON e.id=t.emp_id WHERE  t.isdelete=0 AND DATE(t.createddate)>=:fdate AND DATE(t.createddate)<=:tdate", resultSetMapping = "Mapping.TicketDTO")
@NamedNativeQuery(name = "Tickets.getTicketHistoryByAdmin", query = "SELECT t.id,if(t.rating is null,0,t.rating) AS rating,t.opid,ui.id AS customerid,ui.vlanid,t.code,t.username,e.username AS employeename,t.ticket_description AS ticketdescription,ts.subjectname,tt.typename,op.username AS opname,t.clientcomments,t.empcomments,t.createddate,t.assigneddate,t.accepteddate,t.finisheddate,t.closeddate,CASE WHEN (t.status=1 and t.isreassign=0) THEN \"New\" WHEN t.status=2 THEN \"Assigned\" WHEN t.status=3 THEN \"Opened\" WHEN t.status=4 THEN \"Finished\" WHEN t.status=5 THEN \"Closed\"  WHEN (t.status=1 and t.isreassign=1) THEN \"Reassigned\" END AS status,t.isreassign,t.isactive FROM ticket_history t LEFT JOIN operators op ON op.id=t.opid LEFT JOIN userinfo ui ON ui.username=t.username LEFT JOIN ticket_type tt ON tt.id=t.type_id LEFT JOIN ticket_subject ts ON ts.id=t.subject_id LEFT JOIN employeeinfo e ON e.id=t.emp_id WHERE  t.isdelete=0 ", resultSetMapping = "Mapping.TicketDTO")
@NamedNativeQuery(name = "Tickets.getTicketHistoryByOpidFtdate", query = "SELECT t.id,if(t.rating is null,0,t.rating) AS rating,t.opid,ui.id AS customerid,ui.vlanid,t.code,t.username,e.username AS employeename,t.ticket_description AS ticketdescription,ts.subjectname,tt.typename,op.username AS opname,t.clientcomments,t.empcomments,t.createddate,t.assigneddate,t.accepteddate,t.finisheddate,t.closeddate,CASE WHEN (t.status=1 and t.isreassign=0) THEN \\\"New\\\" WHEN t.status=2 THEN \\\"Assigned\\\" WHEN t.status=3 THEN \\\"Opened\\\" WHEN t.status=4 THEN \\\"Finished\\\" WHEN t.status=5 THEN \\\"Closed\\\"  WHEN (t.status=1 and t.isreassign=1) THEN \\\"Reassigned\\\" END AS status,t.isreassign,t.isactive FROM ticket_history t LEFT JOIN operators op ON op.id=t.opid LEFT JOIN userinfo ui ON ui.username=t.username LEFT JOIN ticket_type tt ON tt.id=t.type_id LEFT JOIN ticket_subject ts ON ts.id=t.subject_id LEFT JOIN employeeinfo e ON e.id=t.emp_id WHERE  t.isdelete=0 AND op.username=:username AND DATE(t.createddate)>=:fdate AND DATE(t.createddate)<=:tdate", resultSetMapping = "Mapping.TicketDTO")
@NamedNativeQuery(name = "Tickets.getTicketHistoryByOpid", query = "SELECT t.id,if(t.rating is null,0,t.rating) AS rating,t.opid,ui.id AS customerid,ui.vlanid,t.code,t.username,e.username AS employeename,t.ticket_description AS ticketdescription,ts.subjectname,tt.typename,op.username AS opname,t.clientcomments,t.empcomments,t.createddate,t.assigneddate,t.accepteddate,t.finisheddate,t.closeddate,CASE WHEN (t.status=1 and t.isreassign=0) THEN \"New\" WHEN t.status=2 THEN \"Assigned\" WHEN t.status=3 THEN \"Opened\" WHEN t.status=4 THEN \"Finished\" WHEN t.status=5 THEN \"Closed\"  WHEN (t.status=1 and t.isreassign=1) THEN \"Reassigned\" END AS status,t.isreassign,t.isactive FROM ticket_history t LEFT JOIN operators op ON op.id=t.opid LEFT JOIN userinfo ui ON ui.username=t.username LEFT JOIN ticket_type tt ON tt.id=t.type_id LEFT JOIN ticket_subject ts ON ts.id=t.subject_id LEFT JOIN employeeinfo e ON e.id=t.emp_id WHERE  t.isdelete=0 AND op.username=:username", resultSetMapping = "Mapping.TicketDTO")
@NamedNativeQuery(name = "Tickets.getTicketHistoryByUserFtdate", query = "SELECT t.id,if(t.rating is null,0,t.rating) AS rating,t.opid,ui.id AS customerid,ui.vlanid,t.code,t.username,e.username AS employeename,t.ticket_description AS ticketdescription,ts.subjectname,tt.typename,op.username AS opname,t.clientcomments,t.empcomments,t.createddate,t.assigneddate,t.accepteddate,t.finisheddate,t.closeddate,CASE WHEN (t.status=1 and t.isreassign=0) THEN \\\"New\\\" WHEN t.status=2 THEN \\\"Assigned\\\" WHEN t.status=3 THEN \\\"Opened\\\" WHEN t.status=4 THEN \\\"Finished\\\" WHEN t.status=5 THEN \\\"Closed\\\"  WHEN (t.status=1 and t.isreassign=1) THEN \\\"Reassigned\\\" END AS status,t.isreassign,t.isactive FROM ticket_history t LEFT JOIN operators op ON op.id=t.opid LEFT JOIN userinfo ui ON ui.username=t.username LEFT JOIN ticket_type tt ON tt.id=t.type_id LEFT JOIN ticket_subject ts ON ts.id=t.subject_id LEFT JOIN employeeinfo e ON e.id=t.emp_id WHERE  t.isdelete=0 AND t.username=:username AND DATE(t.createddate)>=:fdate AND DATE(t.createddate)<=:tdate", resultSetMapping = "Mapping.TicketDTO")
@NamedNativeQuery(name = "Tickets.getTicketHistoryByUser", query = "SELECT t.id,if(t.rating is null,0,t.rating) AS rating,t.opid,ui.id AS customerid,ui.vlanid,t.code,t.username,e.username AS employeename,t.ticket_description AS ticketdescription,ts.subjectname,tt.typename,op.username AS opname,t.clientcomments,t.empcomments,t.createddate,t.assigneddate,t.accepteddate,t.finisheddate,t.closeddate,CASE WHEN (t.status=1 and t.isreassign=0) THEN \"New\" WHEN t.status=2 THEN \"Assigned\" WHEN t.status=3 THEN \"Opened\" WHEN t.status=4 THEN \"Finished\" WHEN t.status=5 THEN \"Closed\"  WHEN (t.status=1 and t.isreassign=1) THEN \"Reassigned\" END AS status,t.isreassign,t.isactive FROM ticket_history t LEFT JOIN operators op ON op.id=t.opid LEFT JOIN userinfo ui ON ui.username=t.username LEFT JOIN ticket_type tt ON tt.id=t.type_id LEFT JOIN ticket_subject ts ON ts.id=t.subject_id LEFT JOIN employeeinfo e ON e.id=t.emp_id WHERE  t.isdelete=0 AND t.username=:username", resultSetMapping = "Mapping.TicketDTO")
@NamedNativeQuery(name = "Tickets.getTicketHistoryByEmpFtdate", query = "SELECT t.id,if(t.rating is null,0,t.rating) AS rating,t.opid,ui.id AS customerid,ui.vlanid,t.code,t.username,e.username AS employeename,t.ticket_description AS ticketdescription,ts.subjectname,tt.typename,op.username AS opname,t.clientcomments,t.empcomments,t.createddate,t.assigneddate,t.accepteddate,t.finisheddate,t.closeddate,CASE WHEN (t.status=1 and t.isreassign=0) THEN \\\"New\\\" WHEN t.status=2 THEN \\\"Assigned\\\" WHEN t.status=3 THEN \\\"Opened\\\" WHEN t.status=4 THEN \\\"Finished\\\" WHEN t.status=5 THEN \\\"Closed\\\"  WHEN (t.status=1 and t.isreassign=1) THEN \\\"Reassigned\\\" END AS status,t.isreassign,t.isactive FROM ticket_history t LEFT JOIN operators op ON op.id=t.opid LEFT JOIN userinfo ui ON ui.username=t.username LEFT JOIN ticket_type tt ON tt.id=t.type_id LEFT JOIN ticket_subject ts ON ts.id=t.subject_id LEFT JOIN employeeinfo e ON e.id=t.emp_id WHERE  t.isdelete=0 AND e.username=:username AND DATE(t.createddate)>=:fdate AND DATE(t.createddate)<=:tdate", resultSetMapping = "Mapping.TicketDTO")
@NamedNativeQuery(name = "Tickets.getTicketHistoryByEmp", query = "SELECT t.id,if(t.rating is null,0,t.rating) AS rating,t.opid,ui.id AS customerid,ui.vlanid,t.code,t.username,e.username AS employeename,t.ticket_description AS ticketdescription,ts.subjectname,tt.typename,op.username AS opname,t.clientcomments,t.empcomments,t.createddate,t.assigneddate,t.accepteddate,t.finisheddate,t.closeddate,CASE WHEN (t.status=1 and t.isreassign=0) THEN \"New\" WHEN t.status=2 THEN \"Assigned\" WHEN t.status=3 THEN \"Opened\" WHEN t.status=4 THEN \"Finished\" WHEN t.status=5 THEN \"Closed\"  WHEN (t.status=1 and t.isreassign=1) THEN \"Reassigned\" END AS status,t.isreassign,t.isactive FROM ticket_history t LEFT JOIN operators op ON op.id=t.opid LEFT JOIN userinfo ui ON ui.username=t.username LEFT JOIN ticket_type tt ON tt.id=t.type_id LEFT JOIN ticket_subject ts ON ts.id=t.subject_id LEFT JOIN employeeinfo e ON e.id=t.emp_id WHERE  t.isdelete=0 AND e.username=:username", resultSetMapping = "Mapping.TicketDTO")
@SqlResultSetMapping(name = "Mapping.TicketDTO", classes = {
		@ConstructorResult(targetClass = TicketDTO.class, columns = { @ColumnResult(name = "id", type = Integer.class),
				@ColumnResult(name = "opid", type = Integer.class),
				@ColumnResult(name = "customerid", type = Integer.class),
				@ColumnResult(name = "vlanid", type = String.class), @ColumnResult(name = "code", type = String.class),
				@ColumnResult(name = "username"), @ColumnResult(name = "employeename"),
				@ColumnResult(name = "ticketdescription"), @ColumnResult(name = "subjectname"),
				@ColumnResult(name = "typename"), @ColumnResult(name = "opname"),
				@ColumnResult(name = "clientcomments"), @ColumnResult(name = "empcomments", type = String.class),
				@ColumnResult(name = "createddate", type = String.class),
				@ColumnResult(name = "assigneddate", type = String.class),
				@ColumnResult(name = "accepteddate", type = String.class),
				@ColumnResult(name = "finisheddate", type = String.class),
				@ColumnResult(name = "closeddate", type = String.class),
				@ColumnResult(name = "status", type = String.class),
				@ColumnResult(name = "isreassign", type = Boolean.class),
				@ColumnResult(name = "isactive", type = Boolean.class),
				@ColumnResult(name = "rating", type = Integer.class) }) })

public class Tickets {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String code;
	private String username;
	private String ticketDescription;
	private int emp_id;
	private int opid;
	private int subjectId;
	private int typeId;
	private int status;
	private int rating;
	private String clientcomments;
	private String empcomments;
	private boolean isreassign;
	private boolean isactive;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String createddate;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String assigneddate;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String accepteddate;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String finisheddate;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String closeddate;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String reassigneddate;

	public Tickets() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Tickets(int id, String code, String username, String ticketDescription, int emp_id, int opid, int subjectId,
			int typeId, int status, String clientcomments, String empcomments, boolean isreassign, boolean isactive,
			String createddate, String assigneddate, String accepteddate, String finisheddate, String closeddate,
			String reassigneddate, int rating) {
		super();
		this.id = id;
		this.code = code;
		this.username = username;
		this.ticketDescription = ticketDescription;
		this.emp_id = emp_id;
		this.opid = opid;
		this.subjectId = subjectId;
		this.typeId = typeId;
		this.status = status;
		this.clientcomments = clientcomments;
		this.empcomments = empcomments;
		this.isreassign = isreassign;
		this.isactive = isactive;
		this.createddate = createddate;
		this.assigneddate = assigneddate;
		this.accepteddate = accepteddate;
		this.finisheddate = finisheddate;
		this.closeddate = closeddate;
		this.reassigneddate = reassigneddate;
		this.rating = rating;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTicketDescription() {
		return ticketDescription;
	}

	public void setTicketDescription(String ticketDescription) {
		this.ticketDescription = ticketDescription;
	}

	public int getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}

	public int getOpid() {
		return opid;
	}

	public void setOpid(int opid) {
		this.opid = opid;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getClientcomments() {
		return clientcomments;
	}

	public void setClientcomments(String clientcomments) {
		this.clientcomments = clientcomments;
	}

	public String getEmpcomments() {
		return empcomments;
	}

	public void setEmpcomments(String empcomments) {
		this.empcomments = empcomments;
	}

	public boolean isIsreassign() {
		return isreassign;
	}

	public void setIsreassign(boolean isreassign) {
		this.isreassign = isreassign;
	}

	public boolean isIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	public String getCreateddate() {
		return createddate;
	}

	public void setCreateddate(String createddate) {
		this.createddate = createddate;
	}

	public String getAssigneddate() {
		return assigneddate;
	}

	public void setAssigneddate(String assigneddate) {
		this.assigneddate = assigneddate;
	}

	public String getAccepteddate() {
		return accepteddate;
	}

	public void setAccepteddate(String accepteddate) {
		this.accepteddate = accepteddate;
	}

	public String getFinisheddate() {
		return finisheddate;
	}

	public void setFinisheddate(String finisheddate) {
		this.finisheddate = finisheddate;
	}

	public String getCloseddate() {
		return closeddate;
	}

	public void setCloseddate(String closeddate) {
		this.closeddate = closeddate;
	}

	public String getReassigneddate() {
		return reassigneddate;
	}

	public void setReassigneddate(String reassigneddate) {
		this.reassigneddate = reassigneddate;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

}
