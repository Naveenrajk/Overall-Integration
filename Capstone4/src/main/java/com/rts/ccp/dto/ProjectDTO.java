package com.rts.ccp.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.rts.ccp.bean.User;

import lombok.Getter;
import lombok.Setter;
 

@Getter
@Setter
public class ProjectDTO {
 
    private Long projectId;
    private String projectName;

    private Date startDate;

    private Date endDate;

    private long department;
    String departmentName;
 
	public ProjectDTO() {
		super();
	}
 
	public ProjectDTO(Long projectId, String projectName, Date startDate, Date endDate, long department,
			String departmentName) {
		super();
		this.projectId = projectId;
		this.projectName = projectName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.department = department;
		this.departmentName = departmentName;
	}
 

}