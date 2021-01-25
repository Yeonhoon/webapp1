package com.mycompany.webapp.dto;

public class LoginDto {
	private String uid;
	private String upw;
	
	public LoginDto(String uid) {
		super();
		this.uid = uid;
	}
	
	public LoginDto(String uid, String upw) {
		super();
		this.uid = uid;
		this.upw = upw;
	}
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUpw() {
		return upw;
	}
	public void setUpw(String upw) {
		this.upw = upw;
	}
	@Override
	public String toString() {
		return "LoginDto [uid=" + uid + ", upw=" + upw + "]";
	}
	
		
	
	
}
