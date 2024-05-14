package kr.trip.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Content {

	private String content_id;
	private String addr1;
	private String addr2;
	private String phone;
	private String areaname;
	private String contentType;
	private String cExplain;
	
}
