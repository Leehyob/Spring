package kr.trip.domain;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardVO {

	/*
	 * board_id int AI PK 
member_email varchar(50) 
title varchar(500) 
content varchar(5000) 
image longblob 
regdate datetime 
updateDate datetime 
replyNum int 
viewcnt int 
replyCnt int 
likeNum
	 * */
	
	private int board_id;
	private String member_email;
	private String title;
	private String content;
	private byte[] image;
	private Date regdate;
	private Date updateDate;
	private int replyNum;
	private int viewcnt;
	private int replyCnt;
	private int likeNum;
	
}
