package com.rts.ccp.dto;
import java.util.ArrayList;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
	private Long userId;
	private String userFirstName;
	private String userLastName;
	private String userEmailId;
	private String userType;
	private Long userMobileNumber;
//	private long department;
//	private String departmentName;
 

//	private long region;
//	private String regionName;

 
	private List<Long> region = new ArrayList<>();
	private List<String> regionName = new ArrayList<>();
	private List<Long> department = new ArrayList<>();
	private List<String> departmentName = new ArrayList<>();
	private List<Long> project = new ArrayList<>();
	private List<String> projectName = new ArrayList<>();
	public UserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
 
	public UserDTO(Long userId, String userFirstName, String userLastName, String userEmailId, String userType,
			Long userMobileNumber, List<Long> region, List<String> regionName, List<Long> department,
			List<String> departmentName, List<Long> project, List<String> projectName) {
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
	}
 
	



}