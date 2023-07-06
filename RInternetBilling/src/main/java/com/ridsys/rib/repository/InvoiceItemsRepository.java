package com.ridsys.rib.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ridsys.rib.models.Invoice_items;

public interface InvoiceItemsRepository extends JpaRepository<Invoice_items, Long> {

	@Transactional
	@Query(value = "DELETE FROM invoice_items WHERE invoice_id=:invoiceid", nativeQuery = true)
	void deleteByInvoiceid(int invoiceid);
}
