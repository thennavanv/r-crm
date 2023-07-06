package com.ridsys.rib.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ridsys.rib.models.Operator_switchport;

public interface Operator_switchportRepository extends JpaRepository<Operator_switchport, Long> {

	@Query(value = "SELECT COUNT(id) FROM operator_switchport WHERE  switchid=:switchid and isactive=true", nativeQuery = true)
	int activeSwitchPort(int switchid);

	boolean existsBySwitchid(int switchid);

	@Query(value = "SELECT COUNT(id) FROM operator_switchport WHERE  switchid=:switchid and portno=:portno AND isactive=true", nativeQuery = true)
	int existsBySwitchidandPortno(int switchid, int portno);

	@Query(value = "SELECT DISTINCT(portno) FROM operator_switchport WHERE  switchid=:switchid and isactive=true", nativeQuery = true)
	List<Integer> getLanPortno(int switchid);

}
