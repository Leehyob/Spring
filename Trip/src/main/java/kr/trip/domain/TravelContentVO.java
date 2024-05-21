package kr.trip.domain;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TravelContentVO {

	/*tContent_id int AI PK 
plan_id int 
content_id varchar(100) 
day int 
time datetime
	 * 
	 * */
	
	private int plan_id;
	private String content_id;
	private int day;
	private Date time;
	private List<ContentVO> contentList;
	
}
