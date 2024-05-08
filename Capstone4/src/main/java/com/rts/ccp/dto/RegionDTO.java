package com.rts.ccp.dto;
 
import java.util.List;
 
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
 
@Component
@Getter
@Setter
public class RegionDTO {
    private Long regionId;
    private String regionName;
    private String regionLocation;
    private String regionTimezone;
    private List<Long> departments;
    
	public RegionDTO() {
		super();

	}
	
	public RegionDTO(Long regionId, String regionName, String regionLocation, String regionTimezone,
			List<Long> departments) {
		super();
		this.regionId = regionId;
		this.regionName = regionName;
		this.regionLocation = regionLocation;
		this.regionTimezone = regionTimezone;
		this.departments = departments;
	}
	
}