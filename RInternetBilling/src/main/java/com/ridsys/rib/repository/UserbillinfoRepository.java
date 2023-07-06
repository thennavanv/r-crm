package com.ridsys.rib.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ridsys.rib.DTO.PlanDetailDTO;
import com.ridsys.rib.DTO.UserDetailsDTO;
import com.ridsys.rib.DTO.CustomerStatDTO;
import com.ridsys.rib.comm.General;
import com.ridsys.rib.models.Userbillinfo;

@Repository
public interface UserbillinfoRepository extends JpaRepository<Userbillinfo, Long> {
	Boolean existsByUsername(String username);

	Userbillinfo findByUsername(String username);

	@Query(value = "UPDATE userbillinfo SET planname=:planname,planid=:planid,quotastartdate=:quotastartdate,quotaexpirydate=:quotaexpirydate WHERE username=:username", nativeQuery = true)
	void updateCurrentPlanDetailsForUser(String username, String planname, String planid, String quotastartdate,
			String quotaexpirydate);

	@Query(value = "SELECT count(ubi.id) FROM userinfo ui, userbillinfo ubi WHERE ui.is_delete=0 and ui.username=ubi.username AND ui.opid>0 AND ubi.quotaexpirydate>now() AND ui.verificationstatus=2", nativeQuery = true)
	long getActiveUsersCount();

	@Query(value = "SELECT count(ubi.id) FROM userinfo ui, userbillinfo ubi WHERE ui.is_delete=0 and ui.username=ubi.username AND ui.opid=:opid AND ubi.quotaexpirydate>now()", nativeQuery = true)
	long getActiveUsersCountByOpid(int opid);

	@Query(value = "SELECT count(ubi.id) FROM userinfo ui, userbillinfo ubi WHERE ui.is_delete=0 and ui.username=ubi.username AND ui.opid>0 AND ubi.quotaexpirydate<now() AND ui.verificationstatus=2", nativeQuery = true)
	long getDeactiveUsersCount();

	@Query(value = "SELECT count(ubi.id) FROM userinfo ui, userbillinfo ubi WHERE ui.is_delete=0 and ui.username=ubi.username AND ui.opid=:opid AND ubi.quotaexpirydate<now()", nativeQuery = true)
	long getDeactiveUsersCountByOpid(int opid);

	@Query(nativeQuery = true)
	CustomerStatDTO getExpiryStatusAdmin();

	@Query(nativeQuery = true)
	CustomerStatDTO getExpiryStatusByOpid(String username);

	@Query(nativeQuery = true)
	List<PlanDetailDTO> getPlanwiseCustomercountTodayAdmin();

	@Query(nativeQuery = true)
	List<PlanDetailDTO> getPlanwiseCustomercountTodayByOpid(String username);

	@Query(nativeQuery = true)
	List<PlanDetailDTO> getPlanwiseCustomerAllcountAdmin();

	@Query(nativeQuery = true)
	List<PlanDetailDTO> getPlanwiseCustomerAllcountByOpid(String username);

	@Query(nativeQuery = true)
	List<General> getMaxDataUsersAdmin();

	@Query(nativeQuery = true)
	List<General> getMaxDataUsersByOpid(String username);

	@Query(nativeQuery = true)
	CustomerStatDTO getDatewiseCustomerCountAdmin(String today);

	@Query(nativeQuery = true)
	CustomerStatDTO getDatewiseCustomerCountByOpid(String username, String today);

	@Query(nativeQuery = true)
	List<UserDetailsDTO> getExpiredUserList(String start, String end);

	@Query(nativeQuery = true)
	List<UserDetailsDTO> getExpiredUserListByOpid(String username, String start, String end);

	@Query(nativeQuery = true)
	List<UserDetailsDTO> getStatictisUserlist(int status);
	
	@Query(nativeQuery = true)
	List<UserDetailsDTO> getStatictisExpiryUserlist(String date);
	
	@Query(nativeQuery = true)
	List<UserDetailsDTO> getStatictisActiveUserlist();
	
	@Query(nativeQuery = true)
	List<UserDetailsDTO> getStatictisUserlistByOpid(int status,String username);
	
	@Query(nativeQuery = true)
	List<UserDetailsDTO> getStatictisExpiryUserlistByOpid(String date,String username);
	
	@Query(nativeQuery = true)
	List<UserDetailsDTO> getStatictisActiveUserlistByOpid(String username);
	
	@Query(nativeQuery = true)
	List<UserDetailsDTO> getStatictisFupUserList();
	
	@Query(nativeQuery = true)
	List<UserDetailsDTO> getStatictisFupUserListByOpid(String username);
	
	@Query(nativeQuery = true)
	CustomerStatDTO getUserCreatedCount();
	
	@Query(nativeQuery = true)
	CustomerStatDTO getUserCreatedCountByOpid(String username);
		
	@Query(nativeQuery = true)
	List<UserDetailsDTO> getUserCreatedListToday();
	
	@Query(nativeQuery = true)
	List<UserDetailsDTO> getUserCreatedListYester();
	
	@Query(nativeQuery = true)
	List<UserDetailsDTO> getUserCreatedListWweek();
	
	@Query(nativeQuery = true)
	List<UserDetailsDTO> getUserCreatedListMonth();
	
	@Query(nativeQuery = true)
	List<UserDetailsDTO> getUserCreatedListTwoMonth();
	
	@Query(nativeQuery = true)
	List<UserDetailsDTO> getUserCreatedListTodayByOpid(String username);
	
	@Query(nativeQuery = true)
	List<UserDetailsDTO> getUserCreatedListYesterByOpid(String username);
	
	@Query(nativeQuery = true)
	List<UserDetailsDTO> getUserCreatedListWweekByOpid(String username);
	
	@Query(nativeQuery = true)
	List<UserDetailsDTO> getUserCreatedListMonthByOpid(String username);
	
	@Query(nativeQuery = true)
	List<UserDetailsDTO> getUserCreatedListTwoMonthByOpid(String username);
}
