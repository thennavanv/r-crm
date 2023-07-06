package com.ridsys.rib.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ridsys.rib.DTO.QuotaplanmanageloghistoryDTO;
import com.ridsys.rib.DTO.SubscriptionInfoDTO;
import com.ridsys.rib.models.Quotaplanmanagelog;

@Repository
public interface QuotaplanmanagelogRepository extends JpaRepository<Quotaplanmanagelog, Long> {

//	@Query(value = "select case when (qpml.quotaexpirydate > now()) then true else false end from quotaplanmanagelog qpml where qpml.username=':username' order by id desc limit 1", nativeQuery = true)
//	Boolean checkExpiry(String username);

	Quotaplanmanagelog findByRechargeid(String rechargeid);
	
	boolean deleteByRechargeid(String rechargeid);

	@Query(nativeQuery = true)
	List<QuotaplanmanageloghistoryDTO> getQuotaPlanManageLogHistoryByRoleCreationbyusernameFtdate(String username, String fdate, String tdate);

	@Query(nativeQuery = true)
	List<QuotaplanmanageloghistoryDTO> getQuotaPlanManageLogHistoryByRoleCreationbyusername(String username);

	@Query(nativeQuery = true)
	List<QuotaplanmanageloghistoryDTO> getQuotaPlanManageLogHistoryByUserFtdate(String username, String fdate,
			String tdate);

	@Query(nativeQuery = true)
	List<QuotaplanmanageloghistoryDTO> getQuotaPlanManageLogHistoryByUser(String username);

	@Query(nativeQuery = true)
	List<QuotaplanmanageloghistoryDTO> getQuotaPlanManageLogHistoryFtdate(String fdate, String tdate);

	@Query(nativeQuery = true)
	List<QuotaplanmanageloghistoryDTO> getQuotaPlanManageLogHistory();

	@Query(nativeQuery = true)
	SubscriptionInfoDTO getSubscriptionInfoByCreationbyusername(String username);

	@Query(nativeQuery = true)
	SubscriptionInfoDTO getDatausageByCreationbyusername(String username);

	@Query(value = "SELECT * FROM quotaplanmanagelog WHERE creationby='USER'", nativeQuery = true)
	List<Quotaplanmanagelog> getSusbcriberPackageHistoryByAdmin();

	@Query(nativeQuery = true)
	List<QuotaplanmanageloghistoryDTO> getQuotaPlanManageLogHistoryByAllUserFtdate(String role, String fdate,
			String tdate);

	@Query(nativeQuery = true)
	List<QuotaplanmanageloghistoryDTO> getQuotaPlanManageLogHistoryByAllOpidFtdate(String role, String fdate,
			String tdate);

	@Query(nativeQuery = true)
	List<QuotaplanmanageloghistoryDTO> getQuotaPlanManageLogHistoryByAllOpid(String role);

	@Query(nativeQuery = true)
	List<QuotaplanmanageloghistoryDTO> getQuotaPlanManageLogHistoryByAllUser(String role);

	@Query(nativeQuery = true)
	List<QuotaplanmanageloghistoryDTO> getQuotaPlanManageLogHistoryByCreationUserFtdate(String username, String role,
			String fdate, String tdate);

	@Query(nativeQuery = true)
	List<QuotaplanmanageloghistoryDTO> getQuotaPlanManageLogHistoryByCreationUser(String username, String role);

	@Query(value = "SELECT * FROM quotaplanmanagelog WHERE username=:username AND DATE(creationdate)>=:creationdate", nativeQuery = true)
	List<Quotaplanmanagelog> getUserRecentLog(String username, String creationdate);
}
