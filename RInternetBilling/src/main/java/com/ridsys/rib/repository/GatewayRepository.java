package com.ridsys.rib.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ridsys.rib.models.Gateway;

@Repository
public interface GatewayRepository extends JpaRepository<Gateway, Integer> {

	Gateway findByGatewayname(String gatewayname);

	Boolean existsBygatewayname(String gatewayname);

	@Query(value = "SELECT * FROM gateway WHERE id NOT IN(SELECT gatewayid FROM payment_gateway_master)", nativeQuery = true)
	List<Gateway> findAllByNotPresent();
}
