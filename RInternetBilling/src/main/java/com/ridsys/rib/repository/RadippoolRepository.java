package com.ridsys.rib.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ridsys.rib.models.Radippool;

@Repository
public interface RadippoolRepository extends JpaRepository<Radippool, Long> {

	Radippool findById(int id);

	Boolean existsByFromip(String fromip);

	Boolean existsByToip(String toip);

	Boolean existsByPoolname(String poolname);

	@Query(nativeQuery = true)
	List<Radippool> getIppoolList();
	
	Radippool findByFromipAndFramedipaddress(String fromip,String framedipaddress);

	@Query(value = "SELECT IF(f.count=s.count,0,1) FROM (SELECT COUNT(id) AS count FROM radippool WHERE fromip=:fromip AND toip=:toip AND username IS NULL AND isactive=1) f,(SELECT COUNT(id) AS COUNT FROM radippool WHERE fromip=:fromip AND toip=:toip AND isactive=1) s;", nativeQuery = true)
	int getFramedipStatusByfromtoIp(String fromip, String toip);

	@Transactional
	void deleteByFromipAndToip(String fromip, String toip);

	@Query(value = "SELECT COUNT(id) AS count FROM radippool WHERE pool_name!=:poolname AND pool_name=:newpoolname", nativeQuery = true)
	int existsByNotInPoolName(String poolname, String newpoolname);

	@Query(value = "SELECT * FROM radippool WHERE fromip=:fromip AND toip=:toip LIMIT 1", nativeQuery = true)
	Radippool findByFromToIp(String fromip, String toip);

	@Query(value = "SELECT * FROM radippool WHERE isactive=1 GROUP BY pool_name", nativeQuery = true)
	List<Radippool> getIppoolByGroup();
	
	@Query(value = "SELECT * FROM radippool WHERE isactive=1 AND fromip=:fromip AND framedipaddress!=:fromip AND username is null ORDER BY id", nativeQuery = true)
	List<Radippool> getIppoolIplistByFromip(String fromip);

	List<Radippool> findByFromipAndToip(String fromip, String toip);
	
	@Query(value = "SELECT username FROM radippool WHERE fromip=:fromip AND framedipaddress=:framedip",nativeQuery=true)
	String getUsernameByFromAndFramedIp(String fromip,String framedip);
	
	
}
