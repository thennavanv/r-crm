package com.ridsys.rib.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ridsys.rib.DTO.CustomerStatDTO;
import com.ridsys.rib.DTO.UserListDTO;
import com.ridsys.rib.DTO.UserProfileInfoDTO;
import com.ridsys.rib.DTO.UserStatusDTO;
import com.ridsys.rib.models.Userinfo;

@Repository
public interface UserinfoRepository extends JpaRepository<Userinfo, Long> {
	Userinfo findByUsername(String username);

	Userinfo findById(int id);

	Userinfo findByMobilephone(String mobile);

	Boolean existsByUsername(String username);

	Boolean existsByMobilephone(String mobilephone);

	Boolean existsByWorkphone(String workphone);

	Boolean existsByEmail(String email);

	Boolean existsById(int id);

	List<Userinfo> findByOpid(int opid);

	@Query(nativeQuery = true)
	UserProfileInfoDTO getUserInfoWithCurrentStatusByUsername(String username);

	@Query(nativeQuery = true)
	List<UserListDTO> gatUserListWithCurrentStatusAll();

	@Query(nativeQuery = true)
	List<UserListDTO> gatUserListWithCurrentStatusAllFtdate(String fdate, String tdate);

	@Query(nativeQuery = true)
	List<UserListDTO> gatUserListWithCurrentStatusAllByOpid(int opid);

	@Query(nativeQuery = true)
	List<UserListDTO> gatUserListWithCurrentStatusAllByOpidFtdate(int opid, String fdate, String tdate);

	@Query(nativeQuery = true)
	List<UserListDTO> gatUserListWithCurrentStatusNotExp();

	@Query(nativeQuery = true)
	List<UserListDTO> gatUserListWithCurrentStatusNotExpByOpid(int opid);

	@Query(nativeQuery = true)
	List<UserListDTO> gatUserListWithCurrentStatusExp();

	@Query(nativeQuery = true)
	List<UserListDTO> gatUserListWithCurrentStatusExpByOpid(int opid);

	@Query(nativeQuery = true)
	List<UserListDTO> gatUserListWithCurrentStatusOnline();

	@Query(nativeQuery = true)
	List<UserListDTO> gatUserListWithCurrentStatusOnlineByOpid(int opid);

	@Query(nativeQuery = true)
	List<UserListDTO> gatUserListWithCurrentStatusOffline();

	@Query(nativeQuery = true)
	List<UserListDTO> gatUserListWithCurrentStatusOfflineByOpid(int opid);

	@Query(nativeQuery = true)
	List<UserListDTO> gatUserListWithCurrentStatusUnderverif();

	@Query(nativeQuery = true)
	List<UserListDTO> gatUserListWithCurrentStatusUnVerfByOpid(int opid);

	@Query(nativeQuery = true)
	List<UserListDTO> gatUserListWithCurrentStatusNew();

	@Query(nativeQuery = true)
	List<UserListDTO> gatUserListWithCurrentStatusNewByOpid(int opid);

	@Query(nativeQuery = true)
	List<UserListDTO> gatUserListWithCurrentStatusDelete();

	@Query(nativeQuery = true)
	List<UserListDTO> gatUserListWithCurrentStatusDeleteByOpid(int opid);

	@Query(nativeQuery = true)
	List<UserListDTO> gatUserListWithCurrentStatusPlanWise();

	@Query(nativeQuery = true)
	List<UserListDTO> gatUserListWithCurrentStatusMaxDataUserByOpid(int opid);

	@Query(nativeQuery = true)
	List<UserListDTO> gatUserListWithCurrentStatusMaxDataUserAll();

	@Query(nativeQuery = true)
	List<UserListDTO> gatUserListWithCurrentStatusPlanWiseByOpid(int opid);

	@Query(value = "UPDATE userinfo SET userproofimage1=:filename WHERE username=:username", nativeQuery = true)
	void updateUserProofImage(String username, String filename);

	@Query(value = "UPDATE userinfo SET verificationstatus=2 WHERE username=:username", nativeQuery = true)
	void userApproval(String username);

	@Query(value = "UPDATE userinfo SET olttype=:olttype,ponout=:ponout,userdevice=:userdevice,macaddress=:macaddress,vlanid=:vlanid,verificationstatus=1 WHERE username=:username", nativeQuery = true)
	void updateUserDeviceInfo(String olttype, String ponout, String userdevice, String macaddress, String username,
			String vlanid);

	@Query(value = "SELECT count(id) FROM userinfo WHERE is_delete=0 and opid=:opid", nativeQuery = true)
	long getTotalUsersCountByOpid(int opid);

	@Query(value = "SELECT count(id) FROM userinfo WHERE  is_delete=0 and opid>0", nativeQuery = true)
	long getTotalUsersCount();

	@Query(value = "UPDATE userinfo SET walletbalance=ROUND((walletbalance - :detectionAmount),2) WHERE id=:id", nativeQuery = true)
	void walletBalanceDetection(int id, double detectionAmount);

	@Query(value = "UPDATE userinfo SET walletbalance=ROUND((walletbalance + :creditAmount),2) WHERE id=:id", nativeQuery = true)
	void walletBalanceCredit(int id, double creditAmount);

	@Query(value = "SELECT COUNT(id) FROM userinfo WHERE verificationstatus=1 AND is_delete=0", nativeQuery = true)
	long getUnderVerificationCountAdmin();

	@Query(value = "SELECT COUNT(id) FROM userinfo WHERE verificationstatus=1 AND is_delete=0 AND  opid=:opid", nativeQuery = true)
	long getUnderVerificationCountOperator(int opid);

	@Query(value = "SELECT COUNT(id) FROM userinfo WHERE verificationstatus=0 AND is_delete=0", nativeQuery = true)
	long getNewCustomerAll();

	@Query(value = "SELECT COUNT(id) FROM userinfo WHERE verificationstatus=0 AND is_delete=0 AND  opid=:opid", nativeQuery = true)
	long getNewCustomerByOperator(int opid);

	@Query(value = "SELECT COUNT(id) FROM userinfo WHERE is_delete=1", nativeQuery = true)
	long getDeletedCustomerAll();

	@Query(value = "SELECT COUNT(id) FROM userinfo WHERE is_delete=1 AND  opid=:opid", nativeQuery = true)
	long getDeletedCustomerByOperator(int opid);

	@Query(value = "select count(id) from userinfo where mobilephone=:mobilephone and username!=:username", nativeQuery = true)
	Integer checkMobilenumber(String username, String mobilephone);

	@Query(value = "select count(id) from userinfo where email=:email and username!=:username", nativeQuery = true)
	Integer checkEmailaddress(String username, String email);

	@Query(value = "select count(id) from userinfo where homephone=:homephone and username!=:username", nativeQuery = true)
	Integer checkHomenumber(String username, String homephone);
	
	@Query(value = "select count(id) from userinfo where deviceno=:deviceno and username!=:username", nativeQuery = true)
	Integer checkDeviceNo(String username, String deviceno);

	@Query(nativeQuery = true)
	List<UserStatusDTO> getUserStatusAll(String username);

	@Query(nativeQuery = true)
	List<UserStatusDTO> getUserStatusActive(String username, String t1);

	@Query(nativeQuery = true)
	List<UserStatusDTO> getUserStatusDeactive(String username, String t1);

	@Query(nativeQuery = true)
	List<UserStatusDTO> getUserStatusNew(String username);

	@Query(value = "UPDATE userinfo SET verificationstatus=1 WHERE username=:username", nativeQuery = true)
	void userReject(String username);

	@Query(nativeQuery = true)
	List<UserListDTO> getExpiryUserDetailsBefore(int day);

	@Query(nativeQuery = true)
	List<UserListDTO> mvusersms();

	@Query(nativeQuery = true)
	List<UserListDTO> getExpiryUserDetailsAfter(int day);

	@Query(nativeQuery = true)
	List<UserListDTO> getTodayRechargedUserDetails();

	@Query(value = "SELECT gstin FROM userinfo WHERE username=:username", nativeQuery = true)
	String getGstNumberByUser(String username);

//	@Query(value = "SELECT ui.username FROM userinfo ui ,operators op WHERE ui.opid=op.id AND op.username=:username AND ui.is_delete=0 ORDER BY ui.firstname", nativeQuery = true)
//	List<String> getUsernameListByOpid(String username);
//
//	@Query(value = "SELECT ui.username FROM userinfo ui WHERE ui.is_delete=0 ORDER BY ui.firstname", nativeQuery = true)
//	List<String> getUsernameListAll();

	@Query(nativeQuery = true)
	List<Userinfo> getExpiredUserForSMS(String username);

	@Query(nativeQuery = true)
	List<Userinfo> getExpiringUserForSMS(String username);

	@Query(nativeQuery = true)
	List<Userinfo> getRenewalUserForSMS(String username);

	@Query(nativeQuery = true)
	List<Userinfo> getAllUserForSMS(String username);

	@Query(nativeQuery = true)
	List<Userinfo> getNewActiveUserForSMS(String username);

	@Query(nativeQuery = true)
	UserListDTO getUserDetailtByUsername(String username);

	@Query(value = "SELECT*FROM userinfo WHERE is_delete=:isdelete", nativeQuery = true)
	List<Userinfo> findAllByIsDelete(boolean isdelete);

	@Query(value = "SELECT*FROM userinfo WHERE is_delete=:isdelete AND opid=:opid", nativeQuery = true)
	List<Userinfo> findByOpidAndIsDelete(int opid, boolean isdelete);



}