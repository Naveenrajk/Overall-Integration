package com.rts.ccp.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class ClaimsDTO {

	private Long userId;
	 
	private String userFirstName;
 
	private String userLastName;
 
	private String userEmailId;
 
	private String userType;
 
	private Long userMobileNumber;
	
	private String region;
	private String regionName;
	private String department;
	private String departmentName ;
	private String project;
	private String projectName;
	private String forcePass;
	public ClaimsDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ClaimsDTO(Long userId, String userFirstName, String userLastName, String userEmailId, String userType,
			Long userMobileNumber, String region, String regionName, String department, String departmentName,
			String project, String projectName, String forcePass) {
		super();
		this.userId = userId;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.userEmailId = userEmailId;
		this.userType = userType;
		this.userMobileNumber = userMobileNumber;
		this.region = region;
		this.regionName = regionName;
		this.department = department;
		this.departmentName = departmentName;
		this.project = project;
		this.projectName = projectName;
		this.forcePass = forcePass;
	}
}
