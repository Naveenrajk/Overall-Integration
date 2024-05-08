package com.rts.ccp.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rts.ccp.bean.Department;
import com.rts.ccp.bean.Post;
import com.rts.ccp.bean.Project;
import com.rts.ccp.bean.Region;
import com.rts.ccp.bean.User;
import com.rts.ccp.dto.PostDTO;
import com.rts.ccp.dto.PostMappingDTO;
import com.rts.ccp.repository.DepartmentRepo;
import com.rts.ccp.repository.PostRepo;
import com.rts.ccp.repository.ProjectRepo;
import com.rts.ccp.repository.RegionRepo;
import com.rts.ccp.repository.UserRepo;


@Service
public class PostService {

	@Autowired
	PostRepo postrepo;

	@Autowired
	RegionRepo rgRepo;

	@Autowired
	Post post;

	@Autowired
	Region region;
	
	@Autowired
	Department department;

	@Autowired
	Project project;
	
	@Autowired
	DepartmentRepo dtRepo;

	@Autowired
	ProjectRepo ptRepo;
	
	@Autowired 
	User user;
	
	@Autowired
	UserRepo userRepo;
		
	
//	public boolean addPost(PostDTO postdto) throws Exception {
////		  Long nextPostId = postrepo.count()+ 1; 
//		 
//		  
//		  if (postdto.getPostContent() == null || postdto.getPostContent().isEmpty()) {
//		    throw new IllegalArgumentException("Post content cannot be null or empty");
//		  }
//		  
//		 
//		  Post post = new Post();
////		  post.setPostId(nextPostId);
//		  post.setPostContent(postdto.getPostContent());
//		  post.setDateTime(postdto.getDateTime());
//		  post.setStatus(postdto.isStatus());
//		  post.setCommentStatus(postdto.isCommentStatus());
//
//
//		 
////		  // Handle region
//		  Optional<Region> optionalRegion = rgRepo.findById(postdto.getRegion());
//		  if (optionalRegion.isPresent()) {
//		    post.setRegion(optionalRegion.get());
//		  } else {
//		    throw new Exception("Region with ID " + postdto.getRegion() + " not found");
//		  }
//		  
//		 
//		  Optional<Department> optionalDepartment = dtRepo.findById(postdto.getDepartment());
//		  if (optionalDepartment.isPresent()) {
//		    post.setDepartment(optionalDepartment.get());
//		  } else {
//		    throw new Exception("Department with ID " + postdto.getDepartment() + " not found");
//		  }
//		 
//		
//		  Optional<Project> optionalProject = ptRepo.findById(postdto.getProject());
//		  if (optionalProject.isPresent()) {
//		    post.setProject(optionalProject.get());
//		  } else {
//		    throw new Exception("Project with ID " + postdto.getProject() + " not found");
//		  }
//	
//		  
//		  Optional<User> optionalUser = userRepo.findById(postdto.getUser());
//		  if (optionalUser.isPresent()) {
//			    post.setUser(optionalUser.get());
//			  } else {
//				    throw new Exception("Region with ID " + postdto.getUser() + " not found");
//
//			  }
//
//		  postrepo.save(post);
//		  return true;
//		}
//
	
	
	
	

	//Apr 30
	public boolean addPost(PostDTO postdto) {
		 Post post = new Post();
//	  post.setPostId(nextPostId);
	  post.setPostContent(postdto.getPostContent());
	  post.setDateTime(postdto.getDateTime());
	  post.setStatus(postdto.isStatus());
	  post.setCommentStatus(postdto.isCommentStatus());
 
		if (postdto.getValue().equalsIgnoreCase("Region")) {
			Region region = rgRepo.findById(postdto.getId()).get();
			post.setRegion(region);
			post.setDepartment(null);
			post.setProject(null);
			
		} else if (postdto.getValue().equalsIgnoreCase("Department")) {
			post.setRegion(null);
			Department department = dtRepo.findById(postdto.getId()).get();
			post.setDepartment(department);
			post.setProject(null);
		} else if (postdto.getValue().equalsIgnoreCase("Project")) {
			post.setRegion(null);
			post.setDepartment(null);
			Project project = ptRepo.findById(postdto.getId()).get();
			post.setProject(project);
		}
 
		User user = userRepo.findById(postdto.getUser()).orElse(null);
		post.setUser(user);
 
		postrepo.save(post);
		return true;
	}
	
	public boolean updatepost(Post post) {
		postrepo.save(post);
		return true;
	}	
	
	public boolean deletepost(long postId) {
		postrepo.deleteById(postId);
		return true;
	}
	
	
	public boolean postdelete(long postId) {
		postrepo.deleteById(postId);
		return true;
	}
	
//	public boolean deletePostByDTO(PostDTO postDTO) {
//		  // Check for essential fields in the DTO
//		  if (postDTO.getPostId() == 0) {
//		    throw new IllegalArgumentException("PostDTO must have a valid postId for deletion");
//		  }
//
//		  // Attempt to find and delete the post by postId from the DTO
//		  return postrepo.findById(postDTO.getPostId())
//		      .stream()
//		      .findFirst()
//		      .map(post -> {
//		          postrepo.delete(post);
//		          return true;
//		      })
//		      .orElse(false);
//		}


	

	public List<Post> getAllPost(){
		Iterator<Post> it = postrepo.findLatestPosts().iterator();
		ArrayList<Post> list = new ArrayList<>();
		
		while(it.hasNext()) {
			list.add(it.next());
		}
		return list;
	}
	
	public PostDTO viewpost(long postId) {
		Post post=postrepo.findById(postId).get();
		
		PostDTO postDto=new PostDTO();
		
		postDto.setPostContent(post.getPostContent());
		postDto.setPostId(post.getPostId());
		postDto.setDateTime(post.getDateTime());
		postDto.setStatus(post.isStatus());
		postDto.setCommentStatus(post.isCommentStatus());
		
		return postDto;
			
		}
	
//	public List<Post> getPublishedPosts() {
//	    List<Post> allPosts = postrepo.findAll();
//	    return allPosts.stream()
//	    		.filter(Post::isStatus) // Filter for published posts
//                .sorted(Comparator.comparing(Post::getPostId).reversed()) // Sort by dateTime in reverse order
//                .collect(Collectors.toList());
//	  }
	
	

	
//	public List<PostDTO>getPublishedPosts(){
//		Iterator<Post> allPost = postrepo.findAll().iterator();
//		List<PostDTO> postDtoList=new ArrayList<>();
//		while(allPost.hasNext()) {
//			PostDTO postdto=new PostDTO();
//			Post post=allPost.next();
//			Region region=post.getRegion();
//			if(region==null) {
//				postdto.setRegion(0);
//			} else {
//				postdto.setRegion(region.getRegionId());
//				postdto.setRegionName(region.getRegionName());
//			}
//			Department depart=post.getDepartment();
//			if(depart==null) {
//				postdto.setDepartment(0);
//			} else {
//				postdto.setDepartment(depart.getDepartmentId());
//			}
//			Project project=post.getProject();
//			if(project==null) {
//				postdto.setProject(0);
//			} else {
//				postdto.setProject(project.getProjectId());
//			}
//			 post.setPostContent(postdto.getPostContent());
//			  post.setDateTime(postdto.getDateTime());
//			  post.setStatus(postdto.isStatus());
//			  post.setCommentStatus(postdto.isCommentStatus());
//			 User user=post.getUser();
//			 postdto.setUser(user.getUserId());
//			 postdto.setUserFirstName(user.getUserFirstName());
//			 postDtoList.add(postdto);
//		}
//		return postDtoList;
//	}
	
	
	//final j 
	public List<PostDTO> getPublishedPosts() {
	    // Leverage streams for concise filtering, sorting, and mapping
	    return postrepo.findAll().stream()
	            .filter(Post::isStatus)  // Filter for published posts
	            .sorted(Comparator.comparing(Post::getPostId).reversed())  // Sort by postId in reverse order
	            .map(post -> {
	                PostDTO postDTO = new PostDTO();

	                // Handle potential null references for Region, Department, and Project
	                postDTO.setRegion(post.getRegion() != null ? post.getRegion().getRegionId() : 0);
	                postDTO.setDepartment(post.getDepartment() != null ? post.getDepartment().getDepartmentId() : 0);
	                postDTO.setProject(post.getProject() != null ? post.getProject().getProjectId() : 0);

	                // Copy non-null fields from Post to PostDTO (assuming they have matching getters/setters)
	                postDTO.setPostId(post.getPostId());
	                postDTO.setPostContent(post.getPostContent());
	                postDTO.setDateTime(post.getDateTime());
	                postDTO.setStatus(post.isStatus());
	                postDTO.setCommentStatus(post.isCommentStatus());
	                postDTO.setUser(post.getUser() != null ? post.getUser().getUserId() : 0);

	                // Include userFirstName and handle potential null user
	                postDTO.setUserFirstName(post.getUser() != null ? post.getUser().getUserFirstName() : null);

	                // Include getRegionName and handle potential null region
	                postDTO.setRegionName(post.getRegion() != null ? post.getRegion().getRegionName() : null);
	                
	                postDTO.setDepartmentName(post.getDepartment()!= null ? post.getDepartment().getDepartmentName(): null);
	                
	                postDTO.setProjectName(post.getProject()!=null ? post.getProject().getProjectName():null);

	                return postDTO;
	            })
	            .collect(Collectors.toList());
	}


	
	
	//Draft 
 
//	  public List<Post> getDraftPosts() {
//	    List<Post> allPosts = postrepo.findAll();
//	    return allPosts.stream()
//	                   .filter(post -> !post.isStatus()) // Filter by negation
//	                   .sorted(Comparator.comparing(Post::getPostId).reversed())
//	                   .collect(Collectors.toList());
//	  }
	
	//new Draft 
	public List<PostDTO> getDraftPosts() {
		  List<Post> allPosts = postrepo.findAll();
		  return allPosts.stream()
		          .filter(post -> !post.isStatus()) // Filter for draft posts (negation of published)
		          .sorted(Comparator.comparing(Post::getPostId).reversed()) // Sort by postId in reverse order
		          .map(post -> {
		              PostDTO postDTO = new PostDTO();
		              
		              postDTO.setRegion(post.getRegion() != null ? post.getRegion().getRegionId() : 0);
		                postDTO.setDepartment(post.getDepartment() != null ? post.getDepartment().getDepartmentId() : 0);
		                postDTO.setProject(post.getProject() != null ? post.getProject().getProjectId() : 0);

		              // Copy non-null fields from Post to PostDTO (assuming matching getters/setters)
		              postDTO.setPostId(post.getPostId());
		              postDTO.setPostContent(post.getPostContent());
		              postDTO.setDateTime(post.getDateTime());
		              postDTO.setStatus(post.isStatus()); // Draft posts will have status as false
		              postDTO.setCommentStatus(post.isCommentStatus());
		              postDTO.setUser(post.getUser() != null ? post.getUser().getUserId() : 0);

		              // Include userFirstName and handle potential null user
		              postDTO.setUserFirstName(post.getUser() != null ? post.getUser().getUserFirstName() : null);

		              // Include getRegionName and handle potential null region
		              postDTO.setRegionName(post.getRegion() != null ? post.getRegion().getRegionName() : null);
		              
		              
		              return postDTO;
		          })
		          .collect(Collectors.toList());
		}

	
	
	  
//		public List<PostDTO> getSurveyRegionUser(long userId) {
//			User user=userRepo.getByUserId(userId);
//			Region reg=user.getRegion();
////			Iterator<Post> it1 = postrepo.findByRegionUser(reg.getRegionId(),userId).iterator();
//			Iterator<Post> it1 = postrepo.findByRegion(reg.getRegionId()).iterator();
//		
//			ArrayList<PostDTO> postList = new ArrayList<>();
//			while (it1.hasNext()) {
//				Post post=it1.next();
//				PostDTO postDto=new PostDTO();
//				postDto.setPostId(post.getPostId());
//				postDto.setPostContent(post.getPostContent());
//				postDto.setDateTime(post.getDateTime());
//				postDto.setStatus(post.isStatus());
//				Region region = post.getRegion();
//				postDto.setRegion(region.getRegionId());
//				Department department = post.getDepartment();
//				postDto.setDepartment(department.getDepartmentId());
//				User users = post.getUser();
//				postDto.setUser(users.getUserId());
//				postList.add(postDto);
//			}
//			return postList;
//		}
	  
	  
//	  public List<Post> getSurveyRegionUser(long userId) {
//		    User user = userRepo.getByUserId(userId);
//		    Region reg = user.getRegion();
//
//		    // Retrieve posts using findByUserId (assuming it works as expected)
//		    List<Post> posts = postrepo.findByUserId(userId);
//
//		    // Filter for published posts and posts where the user's region matches (if region information is available)
//		    posts = posts.stream()
//		            .filter(post -> post.isStatus()) // Filter for published posts
//		            .filter(post -> reg != null && post.getRegion() != null && post.getRegion().equals(reg))
//		            .collect(Collectors.toList());
//
//		    // Sort the retrieved posts in descending order by post_id
//		    Collections.sort(posts, Collections.reverseOrder(Comparator.comparing(Post::getPostId)));
//
//		    return posts;
//		}
	

	public List<PostDTO> getPostRegionUser(long userId) {
	    User user = userRepo.getByUserId(userId);
	    Region userRegion = user.getRegion().get(0);

	    // Retrieve all published posts
	    List<Post> posts = postrepo.findAll().stream()
	        .filter(Post::isStatus) // Filter for published posts
	        .collect(Collectors.toList());

	    // Filter for posts where the post's region matches the user's region (if available)
	    List<PostDTO> postDTOs = posts.stream()
	        .filter(post -> userRegion != null && post.getRegion() != null && post.getRegion().equals(userRegion))
	        .map(post -> {
	            PostDTO postDTO = new PostDTO();

	            // Handle potential null references for Region, Department, and Project
	            postDTO.setRegion(post.getRegion() != null ? post.getRegion().getRegionId() : 0);
	            postDTO.setDepartment(post.getDepartment() != null ? post.getDepartment().getDepartmentId() : 0);
	            postDTO.setProject(post.getProject() != null ? post.getProject().getProjectId() : 0);

	            // Copy non-null fields from Post to PostDTO (assuming they have matching getters/setters)
	            postDTO.setPostId(post.getPostId());
	            postDTO.setPostContent(post.getPostContent());
	            postDTO.setDateTime(post.getDateTime());
	            postDTO.setStatus(post.isStatus());
	            postDTO.setCommentStatus(post.isCommentStatus());
	            postDTO.setUser(post.getUser() != null ? post.getUser().getUserId() : 0);

	            // Include userFirstName and handle potential null user
	            postDTO.setUserFirstName(post.getUser() != null ? post.getUser().getUserFirstName() : null);

	            // Include getRegionName and handle potential null region
	            postDTO.setRegionName(post.getRegion() != null ? post.getRegion().getRegionName() : null);

	            return postDTO;
	        })
	        .collect(Collectors.toList());

	    // Sort the retrieved posts in descending order by post_id
	    Collections.sort(postDTOs, Collections.reverseOrder(Comparator.comparing(PostDTO::getPostId)));

	    return postDTOs;
	}

	  
//
//	public List<PostDTO> getDepartmentPosts(long userId, String department) {
//        User user = userRepo.getByUserId(userId);
//
//        // Retrieve posts using findByUserIdAndDepartment
//        List<Post> posts = postrepo.findByUserIdAndDepartment(userId, department);
//
//        // Filter for published posts
//        List<PostDTO> postDTOs = posts.stream()
//            .filter(post -> post.isStatus()) // Filter for published posts
//            .map(post -> {
//                PostDTO postDTO = new PostDTO();
//                
//                postDTO.setRegion(post.getRegion() != null ? post.getRegion().getRegionId() : 0);
//	            postDTO.setDepartment(post.getDepartment() != null ? post.getDepartment().getDepartmentId() : 0);
//	            postDTO.setProject(post.getProject() != null ? post.getProject().getProjectId() : 0);
//
//                // Handle potential null references
//                postDTO.setPostId(post.getPostId());
//                postDTO.setPostContent(post.getPostContent());
//                postDTO.setDateTime(post.getDateTime());
//                postDTO.setStatus(post.isStatus());
//                postDTO.setCommentStatus(post.isCommentStatus());
//                postDTO.setUser(post.getUser() != null ? post.getUser().getUserId() : 0);
//
//                // Include userFirstName and handle potential null user
//                postDTO.setUserFirstName(post.getUser() != null ? post.getUser().getUserFirstName() : null);
//
//                // No region information included
//
//                // Set department ID
//                postDTO.setDepartment(post.getDepartment() != null ? post.getDepartment().getDepartmentId() : 0);
//
//                // Set project ID (if available)
//                postDTO.setProject(post.getProject() != null ? post.getProject().getProjectId() : 0);
//
//                return postDTO;
//            })
//            .collect(Collectors.toList());
//
//        // Sort the retrieved posts in descending order by post_id
//        Collections.sort(postDTOs, Collections.reverseOrder(Comparator.comparing(PostDTO::getPostId)));
//
//        return postDTOs;
//    }

	  
	  
	  
	  //post under the single dropDown 
	  
	  public List<PostMappingDTO> getRegDepPro(long userId){
			User user=userRepo.findById(userId).get();
			List<PostMappingDTO> listMapping=new ArrayList();
			PostMappingDTO mappingRegionDTO=new PostMappingDTO();
			PostMappingDTO mappingDepartmentDTO=new PostMappingDTO();
			Region region=user.getRegion().get(0);
			mappingRegionDTO.setId(region.getRegionId());
			mappingRegionDTO.setName(region.getRegionName());
			mappingRegionDTO.setValue("Region");
			Department department=user.getDepartment().get(0);
			mappingDepartmentDTO.setId(department.getDepartmentId());
			mappingDepartmentDTO.setName(department.getDepartmentName());
			mappingDepartmentDTO.setValue("Department");
			listMapping.add(mappingRegionDTO);
			listMapping.add(mappingDepartmentDTO);
			Iterator<Project> it=user.getProject().iterator();
			while(it.hasNext()) {
				PostMappingDTO mapDTO=new PostMappingDTO();
				Project project=it.next();
				mapDTO.setId(project.getProjectId());
				mapDTO.setName(project.getProjectName());
				mapDTO.setValue("Project");
				listMapping.add(mapDTO);
			}
			
			return listMapping;
		}
	  
	  
	

	}

	  
	  
	 	  


