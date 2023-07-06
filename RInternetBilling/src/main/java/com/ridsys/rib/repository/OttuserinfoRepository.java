package com.ridsys.rib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ridsys.rib.DTO.OttuserDTO;
import com.ridsys.rib.models.Ottuserinfo;

@Repository
public interface OttuserinfoRepository extends JpaRepository<Ottuserinfo, Long> {

	boolean existsByUsername(String username);

	Ottuserinfo findByUsername(String username);

	Ottuserinfo findByMobilephone(String mobilephone);

	@Query(value = "UPDATE ottuserinfo SET packageid=:packageid,planstartdate=:startdate,planexpirydate=:expirydate,updatedate=now() WHERE username=:username", nativeQuery = true)
	void updateOttuserInfo(long packageid, String startdate, String expirydate, String username);

	@Query(value = "UPDATE ottuserinfo SET planexpirydate=:expirydate,updatedate=:expirydate WHERE username=:username", nativeQuery = true)
	void updateOttuserAfterDeactive(String expirydate, String username);

	@Query(value = "SELECT if(planexpirydate>now(),1,if(planexpirydate is null,0,2)) AS count FROM ottuserinfo oui INNER JOIN userinfo ui ON ui.username=oui.username WHERE ui.username=:username", nativeQuery = true)
	int getOttPlanStatusByUser(String username);

	@Query(value = "SELECT ui.id FROM userinfo ui INNER JOIN ottuserinfo oui on oui.username=ui.username where oui.androidid =:androidid", nativeQuery = true)
	int checkUserAndroidID(String androidid);

	@Query(nativeQuery = true)
	OttuserDTO getUserDetails(int userid);

	@Query(value = "UPDATE ottuserinfo SET isactive=:sts,updatedate=now() WHERE username=:username", nativeQuery = true)
	void setOttUserPermission(String username, boolean sts);

}
