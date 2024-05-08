package com.rts.ccp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MappingDTO {

	private long id;

	private String value;

	private String name;

	public MappingDTO() {
		super();

	}

	public MappingDTO(long id, String value, String name) {
		super();
		this.id = id;
		this.value = value;
		this.name = name;
	}

	
}
