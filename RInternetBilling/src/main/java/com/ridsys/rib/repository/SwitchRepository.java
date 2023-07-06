package com.ridsys.rib.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ridsys.rib.models.Branchinfo;
import com.ridsys.rib.models.Switch;

public interface SwitchRepository extends JpaRepository<Switch, Long> {

	@Query(nativeQuery = true)
	List<Switch> getSwitchlist();

	boolean existsBySwitchname(String switchname);

	boolean existsBySwitchip(String switchip);

	boolean existsById(int id);

	List<Switch> findByIsactive(boolean isactive);

	Switch findById(int id);

	@Query(value = " SELECT COUNT(id) FROM switch WHERE switchname!=:switchname AND switchname=:newswitchname", nativeQuery = true)
	int existsByNotInSwitchName(String switchname, String newswitchname);

	@Query(value = " SELECT COUNT(id) FROM switch WHERE switchip!=:switchip AND switchip=:newswitchip", nativeQuery = true)
	int existsByNotInSwitchIp(String switchip, String newswitchip);

	@Transactional
	void deleteById(int id);
}
