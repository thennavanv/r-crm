package com.ridsys.rib.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ridsys.rib.models.InvoiceFetching;

@Repository
public interface InvoiceFetchingRepository extends JpaRepository<InvoiceFetching, Long> {

	InvoiceFetching findById(int id);

	List<InvoiceFetching> findAllByOrderByInvoiceDate();

	List<InvoiceFetching> findByUseridOrderByInvoiceDate(int userid);
	
	boolean deleteByReferenceid(String referenceid);


	@Query(value = "SELECT * FROM invoice WHERE user_id IN(SELECT ui.id FROM userinfo ui ,operators op WHERE op.id=ui.opid AND op.username=:username) ORDER BY invoice_date", nativeQuery = true)
	List<InvoiceFetching> findByOpid(String username);

	@Query(nativeQuery = true)
	InvoiceFetching getUserDetails(int userid, String referenceid);

	@Query(value = "SELECT * FROM invoice WHERE DATE(creationdate)>=:fdate AND DATE(creationdate)<=:tdate ORDER BY invoice_date", nativeQuery = true)
	List<InvoiceFetching> findAllByFtdate(String fdate, String tdate);

	@Query(value = "SELECT * FROM invoice WHERE DATE(creationdate)>=:fdate AND DATE(creationdate)<=:tdate AND user_id IN (SELECT ui.id FROM userinfo ui,operators op WHERE op.id=ui.opid AND op.username=:username) ORDER BY invoice_date", nativeQuery = true)
	List<InvoiceFetching> findByOpidFtdate(String username, String fdate, String tdate);

	@Query(value = "SELECT * FROM invoice WHERE DATE(creationdate)>=:fdate AND DATE(creationdate)<=:tdate AND user_id IN(SELECT id FROM userinfo WHERE username=:username) ORDER BY invoice_date", nativeQuery = true)
	List<InvoiceFetching> findByUseridFtdate(String username, String fdate, String tdate);

	@Query(value = "SELECT iv.* FROM invoice iv,userinfo ui,operators op WHERE op.id=ui.opid AND ui.id=iv.user_id AND DATE(creationdate)>=:fdate AND DATE(creationdate)<=:tdate ORDER BY op.id", nativeQuery = true)
	List<InvoiceFetching> findAllOrderByOpidFtdate(String fdate, String tdate);

	@Query(value = "SELECT iv.* FROM invoice iv,userinfo ui,operators op WHERE op.id=ui.opid AND ui.id=iv.user_id ORDER BY op.id", nativeQuery = true)
	List<InvoiceFetching> findAllOrderByOpid();

	@Query(value = "SELECT * FROM invoice WHERE user_id IN (SELECT id FROM userinfo WHERE gstin IS NOT NULL)", nativeQuery = true)
	List<InvoiceFetching> findAllByWithGst();

	@Query(value = "SELECT * FROM invoice WHERE user_id IN (SELECT id FROM userinfo WHERE gstin IS  NULL)", nativeQuery = true)
	List<InvoiceFetching> findAllByWithOutGst();

	@Query(value = "SELECT * FROM invoice WHERE user_id IN (SELECT id FROM userinfo WHERE gstin IS NOT NULL) AND DATE(creationdate)>=:fdate AND DATE(creationdate)<=:tdate ORDER BY id", nativeQuery = true)
	List<InvoiceFetching> findAllByWithGstFtdate(String fdate, String tdate);

	@Query(value = "SELECT * FROM invoice WHERE user_id IN (SELECT id FROM userinfo WHERE gstin IS  NULL) AND DATE(creationdate)>=:fdate AND DATE(creationdate)<=:tdate ORDER BY id", nativeQuery = true)
	List<InvoiceFetching> findAllByWithOutGstFtdate(String fdate, String tdate);

	@Query(value = "SELECT * FROM invoice WHERE user_id IN(SELECT ui.id FROM userinfo ui ,operators op WHERE op.id=ui.opid AND op.username=:username AND ui.gstin IS NOT NULL) ORDER BY invoice_date", nativeQuery = true)
	List<InvoiceFetching> findByOpidWithGst(String username);

	@Query(value = "SELECT * FROM invoice WHERE user_id IN(SELECT ui.id FROM userinfo ui ,operators op WHERE op.id=ui.opid AND op.username=:username AND ui.gstin IS NULL) ORDER BY invoice_date", nativeQuery = true)
	List<InvoiceFetching> findByOpidWithOutGst(String username);

	@Query(value = "SELECT * FROM invoice WHERE DATE(creationdate)>=:fdate AND DATE(creationdate)<=:tdate AND user_id IN (SELECT ui.id FROM userinfo ui,operators op WHERE op.id=ui.opid AND op.username=:username AND ui.gstin IS NOT NULL) ORDER BY invoice_date", nativeQuery = true)
	List<InvoiceFetching> findByOpidWithGstFtdate(String username, String fdate, String tdate);

	@Query(value = "SELECT * FROM invoice WHERE DATE(creationdate)>=:fdate AND DATE(creationdate)<=:tdate AND user_id IN (SELECT ui.id FROM userinfo ui,operators op WHERE op.id=ui.opid AND op.username=:username AND ui.gstin IS NUL) ORDER BY invoice_date", nativeQuery = true)
	List<InvoiceFetching> findByOpidWithOutGstFtdate(String username, String fdate, String tdate);
}
