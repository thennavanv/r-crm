package com.ridsys.rib.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ridsys.rib.models.Billing_plans_profiles;


@Repository
public interface Billing_plans_profilesRepository extends JpaRepository<Billing_plans_profiles, Long> {
	Boolean existsByPlanname(String planname);

	Boolean deleteByPlanname(String planname);

	List<Billing_plans_profiles> findAllByPlanname(String planname);
}
