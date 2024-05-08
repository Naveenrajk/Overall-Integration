package com.rts.ccp.bean;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "tbl_login")
@Component
@Getter
@Setter
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "login_id")
    private Long loginId;

    @Column(name = "user_email_id",unique = true)
    private String userEmailId;

    @Column(name = "password")
    private String password;
 
    @Column(name = "user_type")
    private String userType;

    @Column(name = "login_attempts")
    private int loginAttempts=0;

    @Column(name = "last_failed_attempt")
    private LocalDateTime lastFailedAttempt;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    public Login() {
        super();
    }
    
	public Login(Long loginId, String userEmailId, String password, String userType, int loginAttempts,
			LocalDateTime lastFailedAttempt, User user) {
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
