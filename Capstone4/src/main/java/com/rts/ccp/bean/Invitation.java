package com.rts.ccp.bean;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

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

@Entity
@Table(name = "tbl_invitation")
@Component
@Getter
@Setter
public class Invitation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invitation_id")
    private Long invitationId;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;

    @Column(name = "recipient_email")
    private String recipientEmail;

    @Column(name = "invitation_code")
    private String invitationCode;

    @Column(name = "sent_time")
    private LocalDateTime sentTime;

    @Column(name = "accepted")
    private boolean accepted;

    @Column(name = "accepted_time")
    private LocalDateTime acceptedTime;

	public Invitation() {
		super();
	}

	public Invitation(Long invitationId, User sender, String recipientEmail, String invitationCode, LocalDateTime sentTime,
			boolean accepted, LocalDateTime acceptedTime) {
		super();
		this.invitationId = invitationId;
		this.sender = sender;
		this.recipientEmail = recipientEmail;
		this.invitationCode = invitationCode;
		this.sentTime = sentTime;
		this.accepted = accepted;
		this.acceptedTime = acceptedTime;
	}

}