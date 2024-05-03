package org.zerock.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ReplyVO {

	private long rno;
	private long bno;
	
	private String reply;
	private String replyer;
	
	private Date replyDate;
	private Date updateDate;
}
