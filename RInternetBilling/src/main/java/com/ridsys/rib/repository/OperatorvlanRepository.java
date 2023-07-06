package com.ridsys.rib.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ridsys.rib.models.Operatorvlan;

@Repository
public interface OperatorvlanRepository extends JpaRepository<Operatorvlan, Long> {

	Operatorvlan findById(int id);

	boolean existsByVlanid(int vlanid);

	List<Operatorvlan> findByOpid(int opid);
	
	Operatorvlan findByOpidAndVlanid(int opid,int vlanid);

	@Query(value = "SELECT ov.vlanid FROM operator_vlan ov LEFT JOIN operators opr ON opr.id=ov.opid WHERE opr.username=:username ORDER BY ov.vlanid", nativeQuery = true)
	List<Integer> getOperatorVlanId(String username);

	@Query(value = "SELECT DISTINCT area FROM operator_vlan WHERE area IS NOT NULL AND isactive=1", nativeQuery = true)
	List<String> getVlanareaList();
}
