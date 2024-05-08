package com.rts.ccp.dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Component;

import com.rts.ccp.bean.User;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class InvitationDTO {
	private Long invitationId;
	private String sender;
	private String recipientEmail;
	private String invitationCode;
	private LocalDateTime sentTime;
	private boolean accepted;
	private LocalDateTime acceptedTime;
	
	private List<User> users;
	
	
	
	public InvitationDTO(Long invitationId, String sender, String recipientEmail, String invitationCode,
			LocalDateTime sentTime, boolean accepted, LocalDateTime acceptedTime, List<User> users) {
		super();
		this.invitationId = invitationId;
		this.sender = sender;
		this.recipientEmail = recipientEmail;
		this.invitationCode = invitationCode;
		this.sentTime = sentTime;
		this.accepted = accepted;
		this.acceptedTime = acceptedTime;
		this.users = users;
	}
	public InvitationDTO() {
		super();
	}
	
}