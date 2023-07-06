package com.ridsys.rib.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ridsys.rib.DTO.OnlineRechargelistDTO;
import com.ridsys.rib.DTO.OnlinepaymentDTO;
import com.ridsys.rib.models.Online_payment_log;

@Repository
public interface Online_payment_logRepository extends JpaRepository<Online_payment_log, Long> {

	@Query(value = "SELECT * FROM online_payment_log where role=:role AND DATE(tstarttime)>=:fdate AND DATE(tstarttime)<=:tdate ORDER BY id DESC", nativeQuery = true)
	List<Online_payment_log> getOnlineRechargeHistoryByAdminFtdate(String role, String fdate, String tdate);

	@Query(value = "SELECT * FROM online_payment_log where role=:role  ORDER BY id DESC", nativeQuery = true)
	List<Online_payment_log> getOnlineRechargeHistoryByAdmin(String role);

	@Query(value = "SELECT * FROM online_payment_log where role=:role AND username=:username AND DATE(tstarttime)>=:fdate AND DATE(tstarttime)<=:tdate ORDER BY id DESC", nativeQuery = true)
	List<Online_payment_log> getOnlineRechargeHistoryFtdate(String username, String role, String fdate, String tdate);

	@Query(value = "SELECT * FROM online_payment_log where role=:role AND username=:username AND DATE(tstarttime)>=:fdate AND DATE(tstarttime)<=:tdate AND orderid IN(SELECT transactionid FROM online_plan_payment)  ORDER BY id DESC", nativeQuery = true)
	List<Online_payment_log> getOnlineRechargeHistoryUserFtdate(String username, String role, String fdate,
			String tdate);

	@Query(value = "SELECT * FROM online_payment_log where role=:role AND username=:username ORDER BY id DESC", nativeQuery = true)
	List<Online_payment_log> getOnlineRechargeHistory(String username, String role);

	@Query(value = "SELECT * FROM online_payment_log where role=:role AND username=:username AND orderid IN(SELECT transactionid FROM online_plan_payment) ORDER BY id DESC", nativeQuery = true)
	List<Online_payment_log> getOnlineRechargeHistoryUser(String username, String role);

	@Query(value = "SELECT * FROM online_payment_log where orderid=:orderid", nativeQuery = true)
	Online_payment_log findByOrderid(String orderid);

	@Query(value = "UPDATE online_payment_log SET status=:status,resmsg=:resmsg,errormsg=:errormsg,response=:response,tendtime=:tendtime,paymentid=:paymentid WHERE orderid=:orderid", nativeQuery = true)
	void updatePaymentResponse(String status, String resmsg, String errormsg, String response, String tendtime,
			String paymentid, String orderid);

	@Query(value = "select count(id) count from online_payment_log where status='Initiated' and orderid=:order_id", nativeQuery = true)
	int paymentStatusCheckByOrderId(String order_id);

	@Query(nativeQuery = true)
	List<OnlinepaymentDTO> getOnlinePaymentHistoryAllFtdate(String fdate, String tdate);

	@Query(nativeQuery = true)
	List<OnlinepaymentDTO> getOnlinePaymentHistoryFtdate(String fdate, String tdate, String username, String role);

	@Query(nativeQuery = true)
	List<OnlinepaymentDTO> getOnlinePaymentHistoryAll();

	@Query(nativeQuery = true)
	List<OnlinepaymentDTO> getOnlinePaymentHistory(String username, String role);

	@Query(nativeQuery = true)
	List<OnlineRechargelistDTO> getOnlineUserRechargeHistory(String username);

	@Query(nativeQuery = true)
	List<OnlineRechargelistDTO> getOnlineUserRechargeHistoryFtdate(String username, String fdate, String tdate);

	@Query(nativeQuery = true)
	List<OnlineRechargelistDTO> getOnlineUserRechargeHistoryAll();

	@Query(nativeQuery = true)
	List<OnlineRechargelistDTO> getOnlineUserRechargeHistoryAllFtdate(String fdate, String tdate);

	@Query(nativeQuery = true)
	List<OnlineRechargelistDTO> getOnlineUserRechargeHistorByOpid(String username);

	@Query(nativeQuery = true)
	List<OnlineRechargelistDTO> getOnlineUserRechargeHistoryByOpidFtdate(String username, String fdate, String tdate);
}
