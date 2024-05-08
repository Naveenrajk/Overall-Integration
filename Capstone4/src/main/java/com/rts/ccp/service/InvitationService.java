package com.rts.ccp.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.rts.ccp.bean.Department;
import com.rts.ccp.bean.Invitation;
import com.rts.ccp.bean.Login;
import com.rts.ccp.bean.User;
import com.rts.ccp.dto.DepartmentDTO;
import com.rts.ccp.dto.InvitationDTO;
import com.rts.ccp.dto.UserDTO;
import com.rts.ccp.repository.InvitationRepo;
import com.rts.ccp.repository.LoginRepo;
import com.rts.ccp.repository.UserRepo;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class InvitationService {

    @Autowired
    private InvitationRepo invitationRepo;
    
    @Autowired
    private UserRepo userRepo;
    
    @Autowired
    private InvitationDTO invitationdto;
    
    @Autowired
    private Invitation userInvitation;
    
    @Autowired
    private InvitationRepo invitaionRepo;
    
    @Autowired
    private JavaMailSender mailSender;  
    
//    @Autowired
//    private User sender;
    
    @Autowired
    private User user;
    
    private String invitationCode;
    

    public boolean saveOrUpdateInvitation(Invitation invitation) {
        invitationRepo.save(invitation);
        return true;
    }

    public boolean deleteInvitationById(Long invitationId) {
        invitationRepo.deleteById(invitationId);
        return true;
    }

    public List<InvitationDTO> getAllInvitations() {
    	Iterator<User> userIterator = userRepo.findAll().iterator();
		Iterator<Invitation> iterator = invitationRepo.findAll().iterator();
		List<InvitationDTO> inviteList = new ArrayList<>();
		while (iterator.hasNext()) { 
			Invitation invitation = iterator.next();
			InvitationDTO invitationdto = new InvitationDTO();
			invitationdto.setInvitationId(invitation.getInvitationId());
			if(invitation.getSender()!=null) {
				invitationdto.setSender(invitation.getSender().getUserEmailId());
			}else {
				invitationdto.setSender("nosender@gmail.com");
			}
			invitationdto.setRecipientEmail(invitation.getRecipientEmail());
			invitationdto.setInvitationCode(invitation.getInvitationCode());
			invitationdto.setSentTime(invitation.getSentTime());
			
			invitationdto.setAccepted(invitation.isAccepted());
			invitationdto.setAcceptedTime(invitation.getAcceptedTime());
			inviteList.add(invitationdto);
		}
		return inviteList;
	}
    
    public String isAccepted(String email) {
    	User user = userRepo.findByUserEmailId(email);    	
    	Invitation invitation = invitaionRepo.findUserByEmail(email);    	
    	if(user.equals(invitation)) 
    		return "True";
    	else 
    		return "False";    	
    }
    public String inviteUser(String email,Long senderId) throws MessagingException {
    	String inviteCode = generateCode(senderId);
    	User user = userRepo.findByUserEmailId(email);    	
    	Invitation invitation = invitaionRepo.findUserByEmail(email);    	
    	if(user ==null && invitation ==null) {
    		User sender = userRepo.findByUserId(senderId);
    		inviteUserByMail(email,inviteCode);
    		try {
    			userInvitation.setInvitationId( getAllInvitations().get(getAllInvitations().size()-1).getInvitationId()+1);
    		}catch(IndexOutOfBoundsException e) {
    			userInvitation.setInvitationId(0l);
    		}
//    		userInvitation.setInvitationId( getAllInvitations().get(getAllInvitations().size()-1).getInvitationId()+1);
    		
    		userInvitation.setRecipientEmail(email);
    		userInvitation.setSentTime(LocalDateTime.now());
    		userInvitation.setInvitationCode(inviteCode);
    		userInvitation.setSender(sender);
    		saveOrUpdateInvitation(userInvitation);
    		return "Pass";
    	}else if(invitation !=null) {
    		return "sent";
    	}
    	else {
    		return "Failed";
    	}
    	
    }
    
    private String generateCode(Long senderId) {
        SecureRandom random = new SecureRandom();
        int otp = 100000 + random.nextInt(900000);
        User usercode = userRepo.findByUserId(senderId);    
        String code = usercode.getUserFirstName()+"@"+otp;
		return code;
        
    }
    private void inviteUserByMail(String email,String inviteCode) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject("Invitation Regarding CCP");
        mimeMessageHelper.setText("Follow the link to create your profile : http://localhost:4200/userregister"
        		+ "\n Your Invitation code is : "+inviteCode);
        mailSender.send(mimeMessage);
    }
}