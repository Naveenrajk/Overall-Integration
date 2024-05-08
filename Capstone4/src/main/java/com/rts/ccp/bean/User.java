package com.rts.ccp.bean;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Component;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tbl_user")
@Component
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_first_name")
    private String userFirstName;

    @Column(name = "user_last_name")
    private String userLastName;

    @Column(name = "user_email_id",unique = true)
    private String userEmailId;

    @Column(name = "user_type")
    private String userType;

    @Column(name = "user_mobile_number", unique=true)
    private Long userMobileNumber;

    
//    @JsonIgnore
//    @ManyToOne
//    @JoinColumn(name = "department_id")
//    private Department department;

//    @JsonIgnore
//    @ManyToOne
//    @JoinColumn(name = "region_id")
//    private Region region;
    
    @ManyToMany
    private List<Department> department = new ArrayList<Department>();
    
    @ManyToMany
    private List<Region> region = new ArrayList<Region>();

    @ManyToMany
    private List<Project> project = new ArrayList<Project>();
    
    @Column(name = "dob")
    private Date dob;

    @Column(name = "gender")
    private String gender;

    @Column(name = "about_us")
    private String aboutUs;

    @Lob
    @Column(name = "profile_image",length = 100000)
    private byte[] profileImage;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(Long userId, String userFirstName, String userLastName, String userEmailId, String userType,
			Long userMobileNumber, List<Department> department, List<Region> region, List<Project> project, Date dob,
			String gender, String aboutUs, byte[] profileImage) {
		super();
		this.userId = userId;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.userEmailId = userEmailId;
		this.userType = userType;
		this.userMobileNumber = userMobileNumber;
		this.department = department;
		this.region = region;
		this.project = project;
		this.dob = dob;
		this.gender = gender;
		this.aboutUs = aboutUs;
		this.profileImage = profileImage;
	}

}