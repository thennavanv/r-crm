package com.ridsys.rib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ridsys.rib.models.Fup;

@Repository
public interface FupRepository extends JpaRepository<Fup, Long> {

}
