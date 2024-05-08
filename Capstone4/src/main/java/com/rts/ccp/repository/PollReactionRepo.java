package com.rts.ccp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.rts.ccp.bean.PollReaction;

public interface PollReactionRepo extends CrudRepository<PollReaction, Long>{

   @Query(value = "select COUNT(*)  FROM tbl_poll_reaction WHERE poll_id=?", nativeQuery = true)
   public long getReactionCount(long pollId);
   
   @Modifying
   @Query(value= "delete from tbl_poll_reaction order by reaction_id desc limit 1;", nativeQuery = true)
   public int deletebylastId();
   
   @Query(value = "select *  FROM tbl_poll_reaction WHERE poll_id=?", nativeQuery = true)
   public List<PollReaction> getByPollId(long pollId);
   
   @Query(value = "select reaction_id  FROM tbl_poll_reaction WHERE poll_id=? and user_id=?", nativeQuery = true)
	public long getReactionByUserPoll(long pollId,long userId);
}
