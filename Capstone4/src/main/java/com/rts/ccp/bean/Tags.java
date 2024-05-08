package com.rts.ccp.bean;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="tbl_tags")
@Component
@Getter
@Setter
public class Tags {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long tagId;
    @ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="user_id")
	private User user;
    
//    @ManyToOne(cascade = CascadeType.MERGE)
//    @JoinColumn(name="user_first_name")
//    private User userName;
	
	public Tags() {
		super();
		
	}

	public Tags(long tagId, User user) {
		super();
		this.tagId = tagId;
		this.user = user;
	}
	
}
