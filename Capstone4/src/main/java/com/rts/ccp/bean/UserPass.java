package com.rts.ccp.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPass {
	
	private Long userId;

	public UserPass(Long userId) {
		super();
		this.userId = userId;
	}

	public UserPass() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
