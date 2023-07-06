package com.ridsys.rib.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ridsys.rib.models.Iptvpackagetype;

public interface IptvpackagetypeRepository extends JpaRepository<Iptvpackagetype, Long>{

	Iptvpackagetype findById(int parseInt);
}
