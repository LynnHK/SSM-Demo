package com.bwf.p2p.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	
	private int id;
	
	private String email;
	
	private int emailStatus;
	
	private String identity;
	
	private String inviteId;
	
	private String ip;
	
	private Date loginTime;
	
	private int onLock;
	
	private String password;
	
	private String payPassword;
	
	private int payPWDStatus;
	
	private String mobile;
	
	private int mobileStatus;
	
	private int randomStatus;
	
	private String realName;
	
	private int realNameStatus;
	
	private Date registerTime;
	
	private String remark;
	
	private String sumFriend;
	
	private int userSecure;
	
}
