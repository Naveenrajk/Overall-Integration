package com.rts.ccp.bean;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Response {
	String message;

	public Response() {
		super();
		
	}

	public Response(String message) {
		super();
		this.message = message;
	}
	

}
