package com.ridsys.rib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ridsys.rib.models.Online_plan_payment;

@Repository
public interface Online_plan_paymentRepository extends  JpaRepository<Online_plan_payment,Long> {
	
	Online_plan_payment findByTransactionid(String transactionid);
	
//	@Query(value = "UPDATE online_plan_payment SET	status=:status AND updateddate=now() WHERE ",nativeQuery = true)
//	void updatePlanPaymentDetails(String status,String updateddate);

}
