package com.ridsys.rib.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ridsys.rib.models.Userproof;

@Repository
public interface UserproofRepository extends JpaRepository<Userproof,Integer> {

	@Query(value = "select * from userproof where username =:username AND imageurl NOT IN(SELECT userproofimage1 FROM userinfo WHERE username=:username) ORDER BY id DESC", nativeQuery = true)
	List<Userproof> getUserProofImage(String username);
	
	
	@Query(value = "select count(id) from userproof where username =:username", nativeQuery = true)
	int getcount(String username);
}
