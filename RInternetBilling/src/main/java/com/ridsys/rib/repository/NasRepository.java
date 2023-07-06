package com.ridsys.rib.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ridsys.rib.models.Nas;

@Repository
public interface NasRepository extends JpaRepository<Nas, Long> {

	Nas findById(int id);
	
	List<Nas> findByIsactive(boolean isactive);

	Nas findByNasname(String nasname);

	Boolean existsByNasname(String nasname);

	Boolean existsByPorts(String ports);

	Boolean existsBySecret(String secret);

	@Query(value = "SELECT * FROM nas WHERE isactive=1 LIMIT 1", nativeQuery = true)
	Nas findAllByIsactivelimit();

	@Query(value = " SELECT COUNT(id) FROM nas WHERE nasname!=:nasname AND nasname=:newnasname", nativeQuery = true)
	int existsByNotInNasName(String nasname, String newnasname);

	@Query(value = " SELECT COUNT(id) FROM nas WHERE ports!=:port AND ports=:newport", nativeQuery = true)
	int existsByNotInPorts(String port, String newport);

	@Query(value = " SELECT COUNT(id) FROM nas WHERE secret!=:secret AND secret=:newsecret", nativeQuery = true)
	int existsByNotInSecret(String secret, String newsecret);

	@Query(nativeQuery = true)
	List<Nas> getAllNaslist();

	@Query(value = "SELECT COUNT(ra.radacctid) online FROM userinfo ui,userbillinfo ubi,radacct ra,(SELECT username,MAX(acctstarttime) acctstarttime FROM radacct WHERE acctstoptime is null GROUP BY username) rat WHERE ui.is_delete=0 AND ui.username=ubi.username AND ubi.quotaexpirydate>now() AND ra.username=rat.username AND ra.acctstarttime=rat.acctstarttime AND ra.username=ui.username AND ui.opid>0 AND ra.acctstoptime IS null AND ui.verificationstatus>0 AND ra.nasipaddress=(SELECT nasname FROM nas WHERE id=:nasid)", nativeQuery = true)
	int activeUserCountByNas(long nasid);

//	Boolean deleteById(int id);

}
