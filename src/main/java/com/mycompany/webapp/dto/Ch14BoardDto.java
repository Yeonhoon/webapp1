package com.mycompany.webapp.dto;

import java.util.Date;

public class Ch14BoardDto {
	
	//DB 테이블의 컬럼을 저장하기 위해 DTO 생성하기
	
	private int bno;
	private String btitle;
	private String bcontent;
	private String bwriter;
	private Date bdate;
	private int bhitcount;
	private String battach_savefile_name;
	private String battach_original_name;
	private String battach_type;
	
	
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getBtitle() {
		return btitle;
	}
	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}
	public String getBcontent() {
		return bcontent;
	}
	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}
	public String getBwriter() {
		return bwriter;
	}
	public void setBwriter(String bwriter) {
		this.bwriter = bwriter;
	}
	public Date getBdate() {
		return bdate;
	}
	public void setBdate(Date bdate) {
		this.bdate = bdate;
	}
	public int getBhitcount() {
		return bhitcount;
	}
	public void setBhitcount(int bhitcount) {
		this.bhitcount = bhitcount;
	}
	public String getBattach_savefile_name() {
		return battach_savefile_name;
	}
	public void setBattach_savefile_name(String battach_savefile_name) {
		this.battach_savefile_name = battach_savefile_name;
	}
	public String getBattach_original_name() {
		return battach_original_name;
	}
	public void setBattach_original_name(String battach_original_name) {
		this.battach_original_name = battach_original_name;
	}
	public String getBattach_type() {
		return battach_type;
	}
	public void setBattach_type(String battach_type) {
		this.battach_type = battach_type;
	}
	
}
