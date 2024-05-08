package com.rts.ccp.controller;
 
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.rts.ccp.bean.Response;
import com.rts.ccp.bean.User;
import com.rts.ccp.bean.UserProject;
import com.rts.ccp.dto.UserCountDTO;
import com.rts.ccp.dto.UserDTO;
import com.rts.ccp.dto.UserProjectDTO;
import com.rts.ccp.service.UserService;
import com.rts.ccp.util.JwtUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
 
@RestController
@CrossOrigin("http://localhost:4200/")
public class UserController {
 
    @Autowired
    private UserService userService;
    
    @Autowired
    private JwtUtil jwtUtil;
 
    @PostMapping("/insertUsers")
    public void performInsert(@RequestBody UserDTO user) {
        userService.saveOrUpdateUser(user);
    }
 
    @PutMapping("/updateUsers")
    public void performUpdate(@RequestBody UserDTO user) {
        userService.saveOrUpdateUser(user);
    }
 
    @DeleteMapping("/deleteUsers/{userId}")
    public void performDelete(@PathVariable Long userId) {
        userService.deleteUserById(userId);
    }
 
//    @GetMapping("/findAllUsers")
//    public List<User> viewAllUsers() {
//        return userService.getAllUsers();
//    }
//    
 
    @GetMapping("/findAllEmployees")
    public List<UserDTO> viewAllEmployees() {
        return userService.getAllEmployees();
    }
    @GetMapping("/MapAllEmployees")
    public List<UserDTO> mapEmployees() {
        return userService.getEmployeesToMap();
    }

    
    @GetMapping("/getEmployeeToGeneratePassword")
    public ResponseEntity<List<UserDTO>> getEmployeeGenerate(@RequestHeader("Authorization") String token){
   
    	String newToken = (token.substring(7)).trim();
        User user = jwtUtil.verifyToken(newToken);
        System.out.println(newToken);
     
        if (user != null) {
            List<UserDTO> employeePasswords = userService.generateEmployeePassword();   
            return ResponseEntity.ok(employeePasswords);
        }
        else {
            
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    
//    @GetMapping("/getEmployeeToGeneratePassword")
//    public List<UserDTO> getEmployeeGenerate(@RequestBody AuthRequest authRequest) String auth) throws Exception {
//        try {
//            jwtUtil.verify(auth); // Verify the authorization token
//            return userService.generateEmployeePassword();
//        } catch (Exception e) {
//            // Handle token verification failure (e.g., invalid or expired token)
//            // You can log the error or return an appropriate response
//            throw new Exception("Unauthorized access. Please provide a valid token.");
//        }
//    }
    
   
  //new method
    
    @GetMapping("/findProject")
    public List<UserProjectDTO> finduserProject() {
    	
    	List<UserDTO> user = viewAllEmployees();
    	
    	System.out.println(user);
    	
    	List<UserProjectDTO> userProject = new ArrayList<>();
//    	userProject.clear();
    	System.out.println(user.size());
    	for(int i=0;i<user.size();i++) {
    		UserProjectDTO userProjectDto=new UserProjectDTO();
    		userProjectDto.setUserId(user.get(i).getUserId());
    		userProjectDto.setUserFirstName(user.get(i).getUserFirstName());
    		userProjectDto.setUserLastName(user.get(i).getUserLastName());
    		userProjectDto.setUserEmailId(user.get(i).getUserEmailId());
    		userProjectDto.setUserMobileNumber(user.get(i).getUserMobileNumber());
    		userProjectDto.setRegionName(userService.findUserRegion(user.get(i).getUserId()));
    		userProjectDto.setDepartmentName(userService.findUserDepartment(user.get(i).getUserId()));
    		userProjectDto.setProjects(userService.findUserProject(user.get(i).getUserId()));
    		userProject.add(i,userProjectDto);
    		System.out.println(userService.findUserProject(i));
             }
    	return userProject;
    }
 

    @PostMapping("/PerformInsert")
	public ResponseEntity<Response> userInsert(@RequestBody UserDTO invite) {		
//		System.out.println(invite.getUserFirstName()+invite.getUserLastName()+invite.getUserEmailId()+invite.getUserMobileNumber());
		String result = userService.saveUser(invite);
		if(result=="success") {
			Response res = new Response("success");
			return ResponseEntity.ok(res);
		}else if(result=="invalid") {
			Response res = new Response("invalid");
			return ResponseEntity.ok(res);
		}
		else {
			Response res = new Response("exist");
			return ResponseEntity.ok(res);
		}
	}
    @GetMapping("/findParticularEmployee/{userId}")
    public UserDTO findParticularEmployee(@PathVariable Long userId) {
        return userService.findParticularEmployee(userId);
    }
    
    @GetMapping("/findEmployeeCount")
    public UserCountDTO findEmployeeCount() {
        return userService.findEmployeeCount();
    }
    
    @PutMapping("/updateProfile")
    public boolean updateUserProfile(MultipartFile file,Long userId) throws IOException {
    	return userService.updateProfile(file, userId);
    }
}