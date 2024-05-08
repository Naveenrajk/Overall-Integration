package com.rts.ccp.repository;

import java.util.List;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.rts.ccp.bean.Responses;


public interface ResponseRepo extends CrudRepository<Responses, Long> {

	
	@Query(value = "select * from tbl_response where responsedetail_id=?", nativeQuery = true)
	public List<Responses> findByResponseDetailId(long responseDetailId);
}
