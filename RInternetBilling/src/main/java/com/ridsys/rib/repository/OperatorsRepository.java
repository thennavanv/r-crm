package com.ridsys.rib.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ridsys.rib.DTO.SpinListDTO;
import com.ridsys.rib.DTO.OnlinepaymentDTO;
import com.ridsys.rib.DTO.OperatorDTO;
import com.ridsys.rib.DTO.OperatorUserCountDTO;
import com.ridsys.rib.DTO.OperatorWalletReportDTO;
import com.ridsys.rib.DTO.Operator_permissionDTO;
import com.ridsys.rib.models.Operators;

@Repository
public interface OperatorsRepository extends JpaRepository<Operators, Long> {

	Operators findById(int id);

	List<Operators> findAllByIsactiveTrueOrderById();

	Operators findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByPhone1(String phone1);

	Boolean existsByEmail1(String email1);

	Boolean existsByOperatorid(int opid);

	@Query(value="SET GLOBAL sql_mode=(SELECT REPLACE(@@sql_mode,'ONLY_FULL_GROUP_BY',''));",nativeQuery = true)
	void sqlGroupbyQuery();
	
	
	@Query(value = "UPDATE operators SET walletbalance=ROUND((walletbalance - :detectionAmount),2) WHERE id=:id", nativeQuery = true)
	void walletBalanceDetection(int id, double detectionAmount);

	@Query(value = "UPDATE operators SET walletbalance=ROUND((walletbalance + :creditAmount),2) WHERE id=:id", nativeQuery = true)
	void walletBalanceCredit(int id, double creditAmount);

	@Query(nativeQuery = true)
	List<SpinListDTO> getForSpinList();

	@Query(nativeQuery = true)
	List<OperatorDTO> getAllOperatorwithCounts();

	@Query(nativeQuery = true)
	OperatorDTO getAllOperatorwithCountsByOpid(String username);

	@Query(value = "select count(id) from operators where phone1=:mobilephone and username!=:username", nativeQuery = true)
	Integer checkMobilenumber(String username, String mobilephone);

	@Query(value = "select count(id) from operators where email1=:email and username!=:username", nativeQuery = true)
	Integer checkEmailaddress(String username, String email);
	
	@Query(value = "select count(id) from operators where operatorid=:operatorid and username!=:username", nativeQuery = true)
	Integer checkOperatorId(String username, int operatorid);

	@Query(nativeQuery = true)
	List<Operator_permissionDTO> getOperatorPermission();

	@Query(nativeQuery = true)
	List<OperatorUserCountDTO> getOperatorUserCounts();

	@Query(nativeQuery = true)
	List<OnlinepaymentDTO> getAllRechargeHistory();

	@Query(nativeQuery = true)
	List<OnlinepaymentDTO> getAllRechargeHistoryFtdate(String fdate, String tdate);

	@Query(nativeQuery = true)
	List<OnlinepaymentDTO> getAllRechargeHistoryByOpid(String username);

	@Query(nativeQuery = true)
	List<OnlinepaymentDTO> getAllRechargeHistoryByOpidFtdate(String username, String fdate, String tdate);
	
	@Query(nativeQuery = true)
	List<Operators> getAllOperator();
	
	@Query(nativeQuery = true)
	List<OperatorWalletReportDTO> getAllOpWalletHistory();
	
	@Query(nativeQuery = true)
	List<OperatorWalletReportDTO> getAllOpWalletHistoryFtdate(String fdate,String tdate);
	
	@Query(nativeQuery = true)
	List<OperatorWalletReportDTO> getOpWalletHistoryByUsername(String username);
	
	@Query(nativeQuery = true)
	List<OperatorWalletReportDTO> getOpWalletHistoryByUsernameFtdate(String username,String fdate,String tdate);
	

}
