package com.ridsys.rib.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ridsys.rib.models.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

	@Query(value = "SELECT max(id) FROM invoice WHERE invoice_no=:invoice_no", nativeQuery = true)
	int getRecentIdByInvoiceNo(int invoice_no);

	List<Invoice> findByUserid(int userid);

	Invoice findById(int id);

	@Query(value = "SELECT gstin FROM invoice WHERE invoice_no=:invoice_no", nativeQuery = true)
	String getInvoiceGstinByUser(int invoice_no);

	@Transactional
	@Query(value="DELETE FROM invoice WHERE referenceid=:referenceid",nativeQuery=true)
	void deleteByReferenceid(String referenceid);

	Invoice findByReferenceid(String referenceid);

}
