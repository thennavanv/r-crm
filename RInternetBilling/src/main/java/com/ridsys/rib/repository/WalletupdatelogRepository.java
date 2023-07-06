package com.ridsys.rib.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ridsys.rib.DTO.OnlineOfflineDTO;
import com.ridsys.rib.DTO.WalletupdatelogDTO;
import com.ridsys.rib.models.Walletupdatelog;

@Repository
public interface WalletupdatelogRepository extends JpaRepository<Walletupdatelog, Long> {

	@Query(nativeQuery = true)
	List<WalletupdatelogDTO> getOperatorWalletUpdateHistory(String username, String role);

	@Query(nativeQuery = true)
	List<WalletupdatelogDTO> getOperatorWalletUpdateHistoryFtdate(String username, String role, String fdate,
			String tdate);

	@Query(nativeQuery = true)
	List<WalletupdatelogDTO> getUserWalletUpdateHistory(String username, String role);

	@Query(nativeQuery = true)
	List<WalletupdatelogDTO> getUserWalletUpdateHistoryFtdate(String username, String role, String fdate, String tdate);

	@Query(nativeQuery = true)
	List<WalletupdatelogDTO> getOperatorRecentOnlineWalletUpdateHistory(String username, String role);

	@Query(nativeQuery = true)
	List<WalletupdatelogDTO> getUserRecentOnlineWalletUpdateHistory(String username, String role);

	@Query(nativeQuery = true)
	List<WalletupdatelogDTO> getOfflineWalletUpdateHistoryAllFtdate(String fdate, String tdate);

	@Query(nativeQuery = true)
	List<WalletupdatelogDTO> getOfflineWalletUpdateHistoryFtdate(String username, String fdate, String tdate);

	@Query(nativeQuery = true)
	List<WalletupdatelogDTO> getOfflineWalletUpdateHistoryAll();

	@Query(nativeQuery = true)
	List<WalletupdatelogDTO> getOfflineWalletUpdateHistory(String username);

	@Query(value = "SELECT amount FROM walletupdatelog WHERE username=:username ORDER BY id DESC LIMIT 1", nativeQuery = true)
	String getUserLastRechargeAmount(String username);

	@Query(nativeQuery = true)
	Integer countByUsername(String username);

	@Query(nativeQuery = true)
	OnlineOfflineDTO getWalletAmountDatewise(String t1, String t2);
}
