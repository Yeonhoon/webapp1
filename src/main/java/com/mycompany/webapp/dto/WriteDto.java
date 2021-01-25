package com.mycompany.webapp.dto;

import org.springframework.web.multipart.MultipartFile;

public class WriteDto {
	private String utitle;
	private String uwriter;
	private String ucontent;
	private String udate;
	private MultipartFile uphoto;
	
	
	public MultipartFile getUphoto() {
		return uphoto;
	}
	public void setUphoto(MultipartFile uphoto) {
		this.uphoto = uphoto;
	}
	
	public WriteDto(String utitle, String uwriter, String ucontent, String udate, MultipartFile uphoto) {
		super();
		this.utitle = utitle;
		this.uwriter = uwriter;
		this.ucontent = ucontent;
		this.udate = udate;
		this.uphoto = uphoto;
	}
	public String getUtitle() {
		return utitle;
	}
	public void setUtitle(String utitle) {
		this.utitle = utitle;
	}
	public String getUwriter() {
		return uwriter;
	}
	public void setUwriter(String uwriter) {
		this.uwriter = uwriter;
	}
	public String getUcontent() {
		return ucontent;
	}
	public void setUcontent(String ucontent) {
		this.ucontent = ucontent;
	}
	public String getUdate() {
		return udate;
	}
	public void setUdate(String udate) {
		this.udate = udate;
	}
	
	@Override
	public String toString() {
		return "WriteDto [utitle=" + utitle + ", uwriter=" + uwriter + ", ucontent=" + ucontent + ", udate=" + udate
				+ ", uphoto=" + uphoto + "]";
	}

	
	
	
}
