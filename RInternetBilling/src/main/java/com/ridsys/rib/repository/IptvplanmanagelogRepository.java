package com.ridsys.rib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ridsys.rib.models.Iptvplanmanagelog;

@Repository
public interface IptvplanmanagelogRepository  extends JpaRepository<Iptvplanmanagelog, Long>{

}
