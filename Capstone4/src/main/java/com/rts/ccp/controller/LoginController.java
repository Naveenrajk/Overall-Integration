package com.rts.ccp.controller;
 
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rts.ccp.bean.Login;
import com.rts.ccp.bean.User;
import com.rts.ccp.config.PasswordEncryption;
import com.rts.ccp.dto.ClaimsDTO;
import com.rts.ccp.dto.LoginDTO;
import com.rts.ccp.dto.ProjectDTO;
import com.rts.ccp.repository.UserRepo;
import com.rts.ccp.service.ClaimsService;
import com.rts.ccp.service.EmailSendService;
import com.rts.ccp.service.LoginService;
import com.rts.ccp.util.JwtUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
@RestController
@CrossOrigin("http://localhost:4200/")
public class LoginController {
 
    @Autowired
    private LoginService loginService;
    
    @Autowired
    private UserRepo userRepo;
 
    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private EmailSendService sendService;
    
    @Autowired
    private PasswordEncryption passwordEncryption;
    
    
    
    @PostMapping("/insertLogin")
    public void performInsert(@RequestBody LoginDTO logindto) {
    	loginService.saveOrUpdateLogin(logindto);
    }

 
    @DeleteMapping("/Logins/{emailId}")
    public void performDelete(@PathVariable String emailId) {
        loginService.deleteLoginByEmailId(emailId);
    }
 
    @GetMapping("/Logins")
    public List<Login> viewAllLogins() {
        return loginService.getAllLogins();
    }
 
        
    //new method
    
    @PostMapping("/login")
	public ResponseEntity<Map<String, String>> verifyLogin(@RequestBody Login loginRequest) {
    	passwordEncryption.display("Password@123",1l);
    	ClaimsService claimsService = new ClaimsService();
    	if (loginService.verifyLogin(loginRequest.getUserEmailId()).equals("Failed")) {
			Map<String, String> response = new HashMap<>();
			response.put("message", "Email not found. Please Contact Admin");
			return ResponseEntity.badRequest().body(response);
		} else {
			if (loginService.isLoginDisabled(loginRequest.getUserEmailId())) {
				Map<String, String> response = new HashMap<>();
				response.put("message", "Login disabled. Try again after 5 minutes.");
				return ResponseEntity.badRequest().body(response);
			}
 
			String email = loginService.verifyLogin(loginRequest.getUserEmailId(), loginRequest.getPassword());
 
			if (email != null) {
				Map<String, String> response = new HashMap<>();
				loginService.resetLoginAttempts(loginRequest.getUserEmailId());
				if(loginService.verifyNewUser(email)) {
					response.put("email", email);
					response.put("message", "newUser");
					return ResponseEntity.ok(response);
				}
				String userType = loginService.getUserTypeByEmail(email);
				User user = userRepo.findByUserEmailId(email);
				System.out.println(user.getUserEmailId());
				String status = loginService.verifyTimeExpiery(email);
				ClaimsDTO userClaims = claimsService.getEmployee(user,status);
				String token = jwtUtil.generateToken(userClaims);
				System.out.println(token);
				Long userId = loginService.getUserIdByEmail(email);
				response.put("token", token);
//				response.put("userType", userType);
//				response.put("email", email);
//				response.put("userId", Long.toString(userId));
//				response.put("ForcePass", loginService.verifyTimeExpiery(email));
				sendService.sendOtpService(email);
				
				return ResponseEntity.ok(response);
			} else {
//				passwordEncryption.display("Password@123");
				loginService.updateFailedLoginAttempts(loginRequest.getUserEmailId());
				Map<String, String> response = new HashMap<>();
 
				int attempts = loginService.getLoginAttempts(loginRequest.getUserEmailId());
 
				if (attempts < 3) {
					response.put("message", "Login failed. Invalid credentials.");
				} else if (attempts == 3) {
					response.put("message", "Login failed. 2 attempts remaining.");
				} else if (attempts == 4) {
					response.put("message", "Login failed. 1 attempts remaining.");
				} else if (attempts == 5) {
					response.put("message", "Login failed. 1 attempts remaining.");
				}
				return ResponseEntity.badRequest().body(response);
 
			}
		}
	}
 
       
    }