package com.rts.ccp.dto;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class PasswordManagerDTO {
    private Long passwordId;
    
    private Long user;
    
    private Timestamp creationDate;
    private Timestamp expirationDate;
    private String lastPassword1;
    private String lastPassword2;
    private String lastPassword3;
	public PasswordManagerDTO() {
		super();

	}
	public PasswordManagerDTO(Long passwordId, Long user, Timestamp creationDate, Timestamp expirationDate,
			String lastPassword1, String lastPassword2, String lastPassword3) {
		super();
		this.passwordId = passwordId;
		this.user = user;
		this.creationDate = creationDate;
		this.expirationDate = expirationDate;
		this.lastPassword1 = lastPassword1;
		this.lastPassword2 = lastPassword2;
		this.lastPassword3 = lastPassword3;
	}
	
}
