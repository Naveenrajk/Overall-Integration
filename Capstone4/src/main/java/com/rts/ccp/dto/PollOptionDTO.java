package com.rts.ccp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PollOptionDTO {

	private long optionId;

	private String value;

//	private List<OptionResponse> optionResponse;

	private long pollId;
	
    private byte[] picture;
	
	private boolean nullPicture;
	
	private String pictureName;
	
	private String pictureType;
	


	public PollOptionDTO() {
		super();
	}

	

	public PollOptionDTO(long optionId, String value, long pollId, byte[] picture, boolean nullPicture, String pictureName,
			String pictureType) {
		super();
		this.optionId = optionId;
		this.value = value;
		this.pollId = pollId;
		this.picture = picture;
		this.nullPicture = nullPicture;
		this.pictureName = pictureName;
		this.pictureType = pictureType;
	}
	

}
