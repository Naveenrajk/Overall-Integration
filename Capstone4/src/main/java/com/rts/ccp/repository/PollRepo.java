package com.rts.ccp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rts.ccp.bean.Poll;

public interface PollRepo extends JpaRepository<Poll, Long> {

	@Query(value = "select * from tbl_poll where poll_id=?", nativeQuery = true)
	public List<Poll> findByPollId(long pollId);

	List<Poll> findByPollQuestionContaining(String pollQuestion);

	@Query(value = "update tbl_poll set poll_question = ? where poll_id = ?;", nativeQuery = true)
	public long findByPollIdQuestion(long pollId);
	
	@Query(value = "select * FROM tbl_poll where region_id=? order by poll_id desc", nativeQuery = true)
	public List<Poll> findByRegionId(long regionId);
}
