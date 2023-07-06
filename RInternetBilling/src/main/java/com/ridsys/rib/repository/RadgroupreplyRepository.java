package com.ridsys.rib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ridsys.rib.models.Radgroupreply;

@Repository
public interface RadgroupreplyRepository extends JpaRepository<Radgroupreply, Long> {
	Boolean existsByGroupname(String groupusername);
}
