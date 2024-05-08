package com.rts.ccp.dto;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class LoginDTO {
    private Long loginId;
    private String userEmailId;
    private String password;
    private String userType;
    private int loginAttempts;
    private LocalDateTime lastFailedAttempt;
    private Long user;
	public LoginDTO() {
		super();

	}
	public LoginDTO(Long loginId, String userEmailId, String password, String userType, int loginAttempts,
			LocalDateTime lastFailedAttempt, Long user) {
		super();
		this.loginId = loginId;
		this.userEmailId = userEmailId;
		this.password = password;
		this.userType = userType;
		this.loginAttempts = loginAttempts;
		this.lastFailedAttempt = lastFailedAttempt;
		this.user = user;
	}
	
}