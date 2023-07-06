package com.ridsys.rib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ridsys.rib.models.Operator_permission;

@Repository
public interface Operator_permissionRepository extends JpaRepository<Operator_permission, Long> {

	Operator_permission findByOpid(int opid);

	@Query(value = "UPDATE operator_permission set ott=:status WHERE opid=:opid", nativeQuery = true)
	void setOttPermission(int opid, boolean status);

	@Query(value = "UPDATE operator_permission set vod=:status WHERE opid=:opid", nativeQuery = true)
	void setVodPermission(int opid, boolean status);

	@Query(value = "UPDATE operator_permission set sms=:status WHERE opid=:opid", nativeQuery = true)
	void setSmsPermission(int opid, boolean status);

	@Query(value = "UPDATE operator_permission set email=:status WHERE opid=:opid", nativeQuery = true)
	void setEmailPermission(int opid, boolean status);

	@Query(value = "UPDATE operator_permission set walletRecharge=:status WHERE opid=:opid", nativeQuery = true)
	void setWalletPermission(int opid, boolean status);

	@Query(value = "UPDATE operator_permission set subwallet=:status WHERE opid=:opid", nativeQuery = true)
	void setsubWalletPermission(int opid, boolean status);

	@Query(value = "UPDATE operator_permission set planchange=:status WHERE opid=:opid", nativeQuery = true)
	void setPlanchangePermission(int opid, boolean status);

	@Query(value = "UPDATE operator_permission set passwordchange=:status WHERE opid=:opid", nativeQuery = true)
	void setPasswordchangePermission(int opid, boolean status);

	@Query(value = "UPDATE operator_permission set billgenerate=:status WHERE opid=:opid", nativeQuery = true)
	void setBillgeneratePermission(int opid, boolean status);

	@Query(value = "UPDATE operator_permission set oponlinerecharge=:status WHERE opid=:opid", nativeQuery = true)
	void setOponlinerechargePermission(int opid, boolean status);

	@Query(value = "UPDATE operator_permission set subonlinerecharge=:status WHERE opid=:opid", nativeQuery = true)
	void setSubonlinerechargePermission(int opid, boolean status);

}
