package com.rts.ccp.bean;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tbl_poll_option")
@Getter
@Setter
public class PollOption {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long optionId;

	private String value;

//	@JsonIgnore
//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinColumn(name = "option_id")
//	private List<OptionResponse> optionResponse;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "poll_id")
	private Poll pollId;

	@Lob()
	@Column(name = "picture", columnDefinition = "longblob")
	private byte[] picture;

	private String pictureType;

	private String pictureName;

	public PollOption() {
		super();
	}

	public PollOption(long optionId, String value, Poll pollId, byte[] picture, String pictureType, String pictureName) {
		super();
		this.optionId = optionId;
		this.value = value;
		this.pollId = pollId;
		this.picture = picture;
		this.pictureType = pictureType;
		this.pictureName = pictureName;
	}

}
