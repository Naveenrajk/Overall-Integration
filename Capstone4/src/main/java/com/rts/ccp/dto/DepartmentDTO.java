package com.rts.ccp.dto;
import java.util.List;

import com.rts.ccp.bean.Project;
import com.rts.ccp.bean.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentDTO {
    private Long departmentId;

    private String departmentName;

    private long region;
    private String regionName;
    
    private List<Long> regionList;

    private List<User> users;

    private List<Project> projects;
 
 
	public DepartmentDTO() {
		super();
	}


	public DepartmentDTO(Long departmentId, String departmentName, long region, String regionName,
			List<Long> regionList, List<User> users, List<Project> projects) {
		super();
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.region = region;
		this.regionName = regionName;
		this.regionList = regionList;
		this.users = users;
		this.projects = projects;
	}

}