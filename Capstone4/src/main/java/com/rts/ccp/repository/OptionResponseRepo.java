package com.rts.ccp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.rts.ccp.bean.OptionResponse;

public interface OptionResponseRepo extends JpaRepository<OptionResponse, Long> {

	@Query(value = "select * from tbl_option_response where poll_id = ? ", nativeQuery = true)
	public List<OptionResponse> getPollResponse(int pollId);

	@Modifying
	@Query(value = "update tbl_option_response set option_id = ? where user_id = ? and poll_id=?", nativeQuery = true)
	public int updatebyId(long optionId, long userId, long pollId);
	
	@Query(value = "select count(option_id) from tbl_option_response where poll_id = ? ", nativeQuery = true)
	public long getVoteCount(long pollId);
}