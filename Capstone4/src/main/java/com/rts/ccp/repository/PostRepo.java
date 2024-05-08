package com.rts.ccp.repository;
	
	import java.util.List;
	
	import org.springframework.data.jpa.repository.JpaRepository;
	import org.springframework.data.jpa.repository.Query;
	import org.springframework.stereotype.Repository;

import com.rts.ccp.bean.Post;
import com.rts.ccp.dto.PostDTO;


	
	
	@Repository
	public interface PostRepo extends JpaRepository<Post, Long> {
		
//		@Query(value = "SELECT post_id, post_content, date_time, status FROM tbl_posts", nativeQuery = true)
//		  List<Post> findPostsWithSpecificColumnsUsingNativeQuery();
		
		@Query("SELECT p FROM Post p ORDER BY p.dateTime DESC")
	    List<Post> findLatestPosts();
		
		@Query(value = "select * FROM tbl_posts where user_id=? ORDER BY post_id desc", nativeQuery = true)
	    List<Post> findByUserId(long userId);
		
		@Query(value = "select * FROM tbl_posts where region_id=? and user_id!=?", nativeQuery = true)
		public List<Post> findByRegionUser(long userId,long regionId);
		
		@Query(value = "select * FROM tbl_posts where region_id=?", nativeQuery = true)
		public List<PostDTO> findByRegion(long regionId);
		
	// last code
		 @Query("SELECT p FROM Post p WHERE p.user.userId = ?1 AND p.department.departmentName = ?2")
		    List<Post> findByUserIdAndDepartment(Long userId, String departmentName);
		}
		
		

