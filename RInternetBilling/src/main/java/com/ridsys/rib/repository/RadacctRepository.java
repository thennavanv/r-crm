package com.ridsys.rib.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ridsys.rib.models.Radacct;

public interface RadacctRepository extends JpaRepository<Radacct, Long> {

	@Query(value = "SELECT COUNT(ra.radacctid) online FROM userinfo ui,userbillinfo ubi,radacct ra,(SELECT username,MAX(acctstarttime) acctstarttime FROM radacct WHERE acctstoptime is null GROUP BY username) rat WHERE ui.is_delete=0 AND ui.username=ubi.username AND ubi.quotaexpirydate>now() AND ra.username=rat.username AND ra.acctstarttime=rat.acctstarttime AND ra.username=ui.username AND ui.opid>0 AND ra.acctstoptime IS null AND ui.verificationstatus>0", nativeQuery = true)
	long getOnlineUsersCount();

	@Query(value = "SELECT COUNT(ra.radacctid) online FROM userinfo ui,userbillinfo ubi,radacct ra,(SELECT username,MAX(acctstarttime) acctstarttime FROM radacct GROUP BY username) rat WHERE ui.is_delete=0 AND ui.username=ubi.username AND ubi.quotaexpirydate>now() AND ra.username=rat.username AND ra.acctstarttime=rat.acctstarttime AND ra.username=ui.username AND ui.opid=:opid AND ra.acctstoptime IS null", nativeQuery = true)
	long getOnlineUsersCountByOpid(int opid);

	@Query(value = "SELECT COUNT(ra.radacctid) offline FROM userinfo ui,userbillinfo ubi,radacct ra,(SELECT username,MAX(acctstarttime) acctstarttime FROM radacct GROUP BY username) rat WHERE ui.is_delete=0 AND ui.username=ubi.username AND ubi.quotaexpirydate>now() AND ra.username=rat.username AND ra.acctstarttime=rat.acctstarttime AND ra.username=ui.username AND ui.opid>0 AND ra.acctstoptime IS NOT null AND ui.verificationstatus>0", nativeQuery = true)
	long getOfflineUsersCount();

	@Query(value = "SELECT COUNT(ra.radacctid) offline FROM userinfo ui,userbillinfo ubi,radacct ra,(SELECT username,MAX(acctstarttime) acctstarttime FROM radacct GROUP BY username) rat WHERE ui.is_delete=0 AND ui.username=ubi.username AND ubi.quotaexpirydate>now() AND ra.username=rat.username AND ra.acctstarttime=rat.acctstarttime AND ra.username=ui.username AND ui.opid=:opid AND ra.acctstoptime IS NOT null", nativeQuery = true)
	long getOfflineUsersCountByOpid(int opid);

//	@Query(nativeQuery = true,value = "SELECT*FROM radacct WHERE username=:username and DATE(acctstarttime)>=:fdate AND DATE(acctstarttime)<=:tdate")
	@Query(nativeQuery = true)
	List<Radacct> findByUsernameOrderByRadacctidDescFtdate(String username, String fdate, String tdate);

//	@Query(nativeQuery = true,value = "SELECT*FROM radacct WHERE username=:username")
	@Query(nativeQuery = true)
	List<Radacct> findByUsernameOrderByRadacctidDesc(String username);

//	@Query(value = "SELECT * FROM radacct where username IN(SELECT DISTINCT ui.username FROM userinfo ui RIGHT JOIN operators op ON op.id=ui.opid AND op.username=:username) AND DATE(acctstarttime)>=:fdate AND DATE(acctstarttime)<=:tdate ORDER BY radacctid DESC", nativeQuery = true)
	@Query(nativeQuery = true)
	List<Radacct> OperatorSessionAcctHistoryFtdate(String username, String fdate, String tdate);

//	@Query(value = "SELECT * FROM radacct where username IN(SELECT DISTINCT ui.username FROM userinfo ui RIGHT JOIN operators op ON op.id=ui.opid AND op.username=:username)  ORDER BY radacctid DESC", nativeQuery = true)
	@Query(nativeQuery = true)
	List<Radacct> OperatorSessionAcctHistory(String username);

//	@Query(value = "SELECT * FROM radacct WHERE DATE(acctstarttime)>=:fdate AND DATE(acctstarttime)<=:tdate ORDER BY radacctid DESC", nativeQuery = true)
	@Query(nativeQuery = true)
	List<Radacct> SessionAcctHistoryFtdate(String fdate, String tdate);

//	@Query(value = "SELECT * FROM radacct ORDER BY radacctid DESC", nativeQuery = true)
	@Query(nativeQuery = true)
	List<Radacct> SessionAcctHistory();

	@Query(value = "DELETE FROM radacct WHERE DATE(acctstarttime)=CURDATE() or DATE(acctstarttime)=(CURDATE()-INTERVAL 1 DAY) and acctterminatecause='User-Error' AND acctstoptime is not null", nativeQuery = true)
	void deleteUserErrorRadacctHistory();

}
