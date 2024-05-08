package com.rts.ccp.bean;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tbl_department")

@Component
@Getter
@Setter
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private Long departmentId;

    @Column(name = "department_name")
    private String departmentName;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

//    @JsonIgnoreProperties("department")
//    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
//    private List<User> users;

    @JsonIgnoreProperties("department")
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Project> projects;

    @JsonIgnoreProperties("department")
    @ManyToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<User> users = new ArrayList<User>();
    
	public Department() {
		super();
	}

	public Department(Long departmentId, String departmentName, Region region, List<Project> projects,
			List<User> users) {
		super();
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.region = region;
		this.projects = projects;
		this.users = users;
	}

}