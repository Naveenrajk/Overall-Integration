package com.rts.ccp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rts.ccp.bean.Post;
import com.rts.ccp.dto.PostDTO;
import com.rts.ccp.dto.PostMappingDTO;
import com.rts.ccp.service.PostService;

@RestController
@CrossOrigin("http://localhost:4200/") 
public class PostController {
	
	@Autowired(required=true)
	PostService posts;
	
	@PostMapping("/postInsert")
	public String performInsert(@RequestBody PostDTO postDto) throws Exception {
		posts.addPost(postDto);
		return "post Inserted";
	}
	
	@PostMapping("/draftInsert")
	public String performDraft(@RequestBody PostDTO postDto) throws Exception {
		posts.addPost(postDto);
		return "Draft Inserted";
	}
	
	
	@PutMapping("/post")
	public String performUpdate(@RequestBody Post post) {
		posts.updatepost(post);
		return "post updated";
	}
	

	@GetMapping("/viewAllPost")
	public List<Post> getAllpost(){
           return posts.getAllPost();
		
	}
	
	@DeleteMapping("/post/{postId}")
	public void performDelete(@PathVariable("postId") long postId) {
		posts.deletepost(postId);
		System.out.println("Draft Deleted");
	}
	
	@DeleteMapping("/posts/{postId}")
	public void postDelete(@PathVariable("postId") long postId) {
		posts.postdelete(postId);
		System.out.println("Post Deleted");
	}	
	
	@GetMapping("/published")
	  public ResponseEntity<List<PostDTO>> getPublishedPosts() {
	    List<PostDTO> publishedPosts = posts.getPublishedPosts();
	    return ResponseEntity.ok(publishedPosts);
	  }

	  @GetMapping("/drafts")
	  public ResponseEntity<List<PostDTO>> getDraftPosts() {
	    List<PostDTO> draftPosts = posts.getDraftPosts();
	    return ResponseEntity.ok(draftPosts);
	  }
	  
	  @GetMapping("/postview/{postId}")
		public PostDTO performview(@PathVariable("postId") long postId) {
			return posts.viewpost(postId);
			
			
		}
	  
	  
	  
	  @GetMapping("/getpostregionuser/{userId}")
		public List<PostDTO> viewSurveyRegionUser(@PathVariable("userId") long userId) {
			return posts.getPostRegionUser(userId);

		}
	  
	  
	  
	  @GetMapping("/getpostdepregpro/{userId}")
		public List<PostMappingDTO> getPollRegDepPro(@PathVariable("userId") long userId) {
			return posts.getRegDepPro(userId);
		}
	  
	  
//	    @GetMapping("/posts/user/{userId}/department")
//	    public ResponseEntity<List<PostDTO>> getDepartmentPosts(@PathVariable Long userId,
//	                                                            @RequestParam String departmentName) {
//	        // Access parameter names using reflection or other techniques if necessary
//	        List<PostDTO> postDTOs = posts.getDepartmentPosts(userId, departmentName);
//	        return new ResponseEntity<>(postDTOs, HttpStatus.OK);
//	    }
	  
	  

	  
}
//	@GetMapping("/viewAllPost")
//    public ResponseEntity<List<Map<String, Object>>> getAllPosts() {
//        List<Map<String, Object>> postOutput = posts.getAllPostOutput();
//        return ResponseEntity.ok(postOutput);
//    }
	
	
	
//	@GetMapping("/viewAll")
//	  public List<PostDTO> getPostsWithSpecificColumns() {
//	    return posts.getPostsWithSpecificColumns();
//	  }
//	}
	
//	public String getAllpost() {
//		return posts.getAllPost();
//	}
	
	

