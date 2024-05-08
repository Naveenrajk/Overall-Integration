package com.rts.ccp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rts.ccp.bean.Region;

@Repository
public interface RegionRepo extends JpaRepository<Region, Long> {
	
	@Query(value="select * from tbl_user_region where user_user_id=?",nativeQuery=true)
	public List<List<Long>> findUserRegionId(long userId);
	

}