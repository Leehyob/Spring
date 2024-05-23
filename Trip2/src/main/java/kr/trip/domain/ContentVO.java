package kr.trip.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Data
public class ContentVO {
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
