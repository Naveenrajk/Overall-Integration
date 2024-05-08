package com.rts.ccp.bean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "tbl_password_manager")
@Component
@Getter
@Setter
public class PasswordManager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "password_id")
    private Long passwordId;

    @ManyToOne
    @JoinColumn(name = "login_id")
    private Login loginId;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;

    @Column(name = "last_password_1")
    private String lastPassword1;

    @Column(name = "last_password_2")
    private String lastPassword2;

    @Column(name = "last_password_3")
    private String lastPassword3;
    
    

	public PasswordManager(Long passwordId, Login loginId, LocalDateTime creationDate, LocalDateTime expirationDate,
			String lastPassword1, String lastPassword2, String lastPassword3) {
		super();
		this.passwordId = passwordId;
		this.loginId = loginId;
		this.creationDate = creationDate;
		this.expirationDate = expirationDate;
		this.lastPassword1 = lastPassword1;
		this.lastPassword2 = lastPassword2;
		this.lastPassword3 = lastPassword3;
	}

	public PasswordManager() {
		super();
	}

	}