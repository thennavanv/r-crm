package com.ridsys.rib.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ridsys.rib.models.PaymentGatewayMaster;

@Repository
public interface PaymentGatewayMasterRepository extends JpaRepository<PaymentGatewayMaster, Long> {

	PaymentGatewayMaster findById(int id);

	@Query(value = "SELECT COUNT(id)>0 FROM payment_gateway_master WHERE isactive=1", nativeQuery = true)
	int checkPaymentGatewayStatus();

	PaymentGatewayMaster findByIsactive(boolean isactive);

	Boolean existsById(int id);

	@Transactional
	void deleteById(int id);

//	@Query(value = "SELECT id,gatewayname,apikey,salt,mode,merchant,extra1,extra2,isactive,created_date FROM payment_gateway_master WHERE isactive=1", nativeQuery = true)
//	PaymentGatewayMaster findActivePaymentGatewayInfo();
//
//	@Query(value = "SELECT id,gatewayname,apikey,salt,mode,merchant,extra1,extra2,isactive,created_date FROM payment_gateway_master WHERE gatewayname=:gatewayname", nativeQuery = true)
//	PaymentGatewayMaster findByGatewayname(String gatewayname);

	@Query(value = "UPDATE payment_gateway_master SET isactive=0", nativeQuery = true)
	void updateisactive();
}
