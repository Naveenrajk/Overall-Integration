package com.rts.ccp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCountDTO {
	
	int existingEmployeeCount;
	int newEmployeeCount;
	int registeredEmployeeCount;
	
	
	public UserCountDTO() {
		super();
	}
 
 
	public UserCountDTO(int existingEmployeeCount, int newEmployeeCount, int registeredEmployeeCount) {
		super();
		this.existingEmployeeCount = existingEmployeeCount;
		this.newEmployeeCount = newEmployeeCount;
		this.registeredEmployeeCount = registeredEmployeeCount;
	}
 
 
	
	
	
 
}
 