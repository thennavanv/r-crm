package com.ridsys.rib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ridsys.rib.models.Radgroupcheck;

@Repository
public interface RadgroupcheckRepository extends JpaRepository<Radgroupcheck, Long>{
	Boolean existsByGroupname(String groupusername);
}
