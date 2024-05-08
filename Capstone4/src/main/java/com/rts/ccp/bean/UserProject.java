package com.rts.ccp.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProject {
	
	long user_user_id;
	long project_project_id;
	
	public UserProject() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserProject(long user_user_id, long project_project_id) {
		super();
		this.user_user_id = user_user_id;
		this.project_project_id = project_project_id;
	}

}
