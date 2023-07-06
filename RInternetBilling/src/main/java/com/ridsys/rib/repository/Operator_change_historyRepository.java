package com.ridsys.rib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ridsys.rib.models.Operator_change_history;

@Repository
public interface Operator_change_historyRepository extends JpaRepository<Operator_change_history, Long>{

}
