package com.ridsys.rib.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ridsys.rib.models.Operatorplanpermission;

public interface OperatorplanpermissionRepository  extends JpaRepository<Operatorplanpermission,Long> {

	Operatorplanpermission findByOpidAndPlanid(int opid,int planid);
}
