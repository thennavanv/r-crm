package com.ridsys.rib.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ridsys.rib.DTO.OfflinePaymentDTO;
import com.ridsys.rib.models.Offline_payment_log;

@Repository
public interface Offline_payment_logRepository extends JpaRepository<Offline_payment_log, Long> {

	@Query(nativeQuery = true)
	List<OfflinePaymentDTO> getOfflineHistoryAll();

	@Query(nativeQuery = true)
	List<OfflinePaymentDTO> getOfflineHistoryAllFtdate(String fdate, String tdate);

	@Query(nativeQuery = true)
	List<OfflinePaymentDTO> getOfflineHistory(String username);

	@Query(nativeQuery = true)
	List<OfflinePaymentDTO> getOfflineHistoryFtdate(String fdate, String tdate, String username);

}
