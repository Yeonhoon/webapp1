package com.mycompany.webapp.dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class Ch14BoardDto {
	
	//DB 테이블의 컬럼을 저장하기 위해 DTO 생성하기
	
	private int bno;
	private String btitle;
	private String bcontent;
	private String bwriter;
	private Date bdate;
	private int bhitcount;
	private MultipartFile battach;
	private String battachsname;
	private String battachoname;
	private String battachtype;
	
	
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
	public MultipartFile getBattach() {
		return battach;
	}
	public void setBattach(MultipartFile battach) {
		this.battach = battach;
	}
	public String getBattachsname() {
		return battachsname;
	}
	public void setBattachsname(String battachsname) {
		this.battachsname = battachsname;
	}
	public String getBattachoname() {
		return battachoname;
	}
	public void setBattachoname(String battachoname) {
		this.battachoname = battachoname;
	}
	public String getBattachtype() {
		return battachtype;
	}
	public void setBattachtype(String battachtype) {
		this.battachtype = battachtype;
	}
	@Override
	public String toString() {
		return "Ch14BoardDto [bno=" + bno + ", btitle=" + btitle + ", bcontent=" + bcontent + ", bwriter=" + bwriter
				+ ", bdate=" + bdate + ", bhitcount=" + bhitcount + ", battach=" + battach + ", battachsname="
				+ battachsname + ", battachoname=" + battachoname + ", battachtype=" + battachtype + "]";
	}

	
}
