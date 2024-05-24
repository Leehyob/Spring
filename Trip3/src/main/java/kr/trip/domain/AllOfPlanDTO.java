package kr.trip.domain;

import java.util.Date;

import lombok.Data;

@Data
public class AllOfPlanDTO {

	private int plan_id;
	private String member_email;
	private Date regdate;
	private Date go;
	private Date end;
	private int tpa_id;
	private int tContent_id;
	private Date day;
	private int dayCnt;
	private Date time;
	private String content_id;
	private String addr1;
	private String addr2;
	private String phone;
	private String cExplain;
	private String areaname;
	private String contentType;
	private Date created_time;
	private int likeNum;
	private byte[] image1;
}