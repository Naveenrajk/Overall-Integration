package com.rts.ccp.config;
import java.security.MessageDigest;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;





@Service
public class PasswordEncryption {
	
	public void display(String password,Long login) {
		System.out.println(encrypt(password,login));
	}
	
	
	 private static String ENCRYPTION_KEY = "TWF0dGhld0dheWF0aHJpRGhhcnNoYW5hQXJhdmluZERvbW5pYw==";

	    public String encrypt(String password,Long login) {
	        try {
	        	final String Final_ENCRYPTION_KEY = ENCRYPTION_KEY+login.toString();
	        	
	        	System.out.println(Final_ENCRYPTION_KEY);
	            MessageDigest digest = MessageDigest.getInstance("SHA-256");
	            digest.update(Final_ENCRYPTION_KEY.getBytes());
	            byte[] encryptedBytes = digest.digest(password.getBytes());
	            return Base64.getEncoder().encodeToString(encryptedBytes);
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	            // Handle exception
	            return null;
	        }
	    }

	    public boolean decrypt(String password, String encryptedPassword,Long loginId) {
	        String decryptedPassword = encrypt(password,loginId);
	        System.out.println(password);
	        System.out.println(decryptedPassword);
	        System.out.println(encryptedPassword);
	        return decryptedPassword != null && decryptedPassword.equals(encryptedPassword);
	    }
}
