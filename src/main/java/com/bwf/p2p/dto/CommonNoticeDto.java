package com.bwf.p2p.dto;

import java.util.Date;

import lombok.Data;

@Data
public class CommonNoticeDto {
	
	private String id;
	
	private String uniqid;
	
	private String title;
	
	private String description;
	
	private String url;
	
	private String imgUrl;
	
	private int mark;
	
	private String type;
	
	private int delFlag;
	
	private Date created;
	
	private String createdBy;
	
	private Date updated;
	
	private String updatedBy;

}
