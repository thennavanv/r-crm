package com.ridsys.rib.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ridsys.rib.models.Dictionary;

@Repository
public interface DictionaryRepository extends JpaRepository<Dictionary, Long> {

	@Query(value = "SELECT distinct(Vendor) as Vendor FROM dictionary WHERE Vendor LIKE %:searchvalue% ORDER BY Vendor ASC", nativeQuery = true)
	List<String> findByVendorLike(String searchvalue);

	@Query(value = "SELECT distinct(Attribute) as Attribute FROM dictionary WHERE Attribute LIKE %:searchvalue% ORDER BY Vendor ASC", nativeQuery = true)
	List<String> findByAttributeLike(String searchvalue);

}
