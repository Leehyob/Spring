package kr.trip.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TravelPlanVO {

	/*
	 * plan_id int AI PK 
member_email varchar(50) 
regdate datetime 
go datetime 
end datetime 
day int 
tpa_id int
	 * */
	
	private int plan_id;
	private String member_email;
	private Date regdate;
	private Date go;
	private Date end;
	private int day;
	private int tpa_id;
	
}
