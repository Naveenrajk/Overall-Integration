package com.rts.ccp.dto;
 
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
 
@Component
@Getter
@Setter
public class UserProjectDTO {
	
	private Long userId;
	
	private String userFirstName;
 
	private String userLastName;
 
	private String userEmailId;
	
	private Long userMobileNumber;
	
	private long department;
	private String departmentName;
 
 
	private long region;
	private String regionName;  
	
	private String projects;
 
	public UserProjectDTO() {
		super();
	}
 
	public UserProjectDTO(Long userId, String userFirstName, String userLastName, String userEmailId,
			Long userMobileNumber, long department, String departmentName, long region, String regionName,
			String projects) {
		super();
		this.userId = userId;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.userEmailId = userEmailId;
		this.userMobileNumber = userMobileNumber;
		this.department = department;
		this.departmentName = departmentName;
		this.region = region;
		this.regionName = regionName;
		this.projects = projects;
	}
 
	
}
 
