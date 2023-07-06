package com.ridsys.rib.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ridsys.rib.DTO.SpinListDTO;
import com.ridsys.rib.models.Payment_type;

@Repository
public interface Payment_typeRepository extends JpaRepository<Payment_type, Long>{

	@Query(nativeQuery = true)
	List<SpinListDTO> getSpinList();
}
