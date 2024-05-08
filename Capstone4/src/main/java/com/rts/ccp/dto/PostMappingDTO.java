package com.rts.ccp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostMappingDTO {

		
		private long id;
		
		private String name;
		
		private String value;
		
		
	 
		public PostMappingDTO() {
			super();
		}
	 
		public PostMappingDTO(long id, String name, String value) {
			super();
			this.id = id;
			this.name = name;
			this.value = value;
		}
	 
		
		
	 
	}

