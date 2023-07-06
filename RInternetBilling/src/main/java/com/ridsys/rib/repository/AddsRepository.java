package com.ridsys.rib.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ridsys.rib.models.Adds;

@Repository
public interface AddsRepository extends JpaRepository< Adds, Long> {

//	@Query(value = "select count(id) from adds where username =:opusername", nativeQuery = true)
//	int getcount(String opusername);

	@Query(value = "select * from adds where username =:opusername and if(:type='image',is_image=1,if(:type='video',is_image=0,is_active=1)) and is_active = 1", nativeQuery = true)
	List<Adds> get_imageAd(String opusername,String type);
	
}
