package com.rts.ccp.bean;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tbl_region")

@Component
@Getter
@Setter
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region_id")
    private Long regionId;

    @Column(name = "region_name",unique = true)
    private String regionName;

    @Column(name = "region_location")
    private String regionLocation;

    @Column(name = "region_timezone")
    private String regionTimezone;

    @JsonIgnoreProperties("region")
    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL)
    private List<Department> departments;
    
    @JsonIgnoreProperties("region")
    @ManyToMany(mappedBy = "region", cascade = CascadeType.ALL)
    private List<User> user = new ArrayList<User>();

	public Region() {
		super();
	}

	public Region(Long regionId, String regionName, String regionLocation, String regionTimezone,
			List<Department> departments, List<User> user) {
		super();
		this.regionId = regionId;
		this.regionName = regionName;
		this.regionLocation = regionLocation;
		this.regionTimezone = regionTimezone;
		this.departments = departments;
		this.user = user;
	}

	
}