package com.rts.ccp.controller;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rts.ccp.bean.Invitation;
import com.rts.ccp.bean.Response;
import com.rts.ccp.dto.InvitationDTO;
import com.rts.ccp.service.InvitationService;

import jakarta.mail.MessagingException;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200/")
public class InvitationController {

    @Autowired 
    private InvitationService invitationService;

    @PostMapping("/insertInvitations")
    public void performInsert(@RequestBody Invitation invitation) {
        invitationService.saveOrUpdateInvitation(invitation);
    }

    @PutMapping("/updateInvitations")
    public void performUpdate(@RequestBody Invitation invitation) {
        invitationService.saveOrUpdateInvitation(invitation);
    }

    @DeleteMapping("/deleteInvitations/{invitationId}")
    public void performDelete(@PathVariable Long invitationId) {
        invitationService.deleteInvitationById(invitationId);
    }

    @GetMapping("/findAllInvitations")
    public List<InvitationDTO> viewAllInvitations() {
        return invitationService.getAllInvitations();
    }
    
    @GetMapping("/inviteUserByMail/{email}/{senderId}")
    public ResponseEntity<Response> userInvite(@PathVariable("email") String email, @PathVariable("senderId") String senderId) throws MessagingException{
    	
    	if(email==null) {
    		Response res=new Response("Email is required.");
            return ResponseEntity.ok(res);
    	}else if(invitationService.inviteUser(email,Long.parseLong(senderId)).equals("Pass")) {
    		Response res=new Response("Invited sent successfully");
            return ResponseEntity.ok(res);
    	}else if(invitationService.inviteUser(email,Long.parseLong(senderId)).equals("sent")) {
    		Response res=new Response("User is Already Invited");
            return ResponseEntity.ok(res);
    	}else {
    		Response res=new Response("User already exist");
            return ResponseEntity.ok(res);
    	}
    }
}