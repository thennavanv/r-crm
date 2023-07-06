package com.ridsys.rib.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ridsys.rib.models.Providerinfo;

public interface ProviderinfoRepository extends JpaRepository<Providerinfo, Long> {
	
	List<Providerinfo> findByIsactive(boolean isactive);
	
	Boolean existsByProvidername(String providername);

}
