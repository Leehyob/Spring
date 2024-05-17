package kr.trip.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TpaVO {

	/*
	 * tpa_id int PK 
type varchar(100) 
go varchar(100) 
end varchar(100) 
runTime datetime
	 * */
	
	private int tpa_id;
	private String go;
	private String end;
	private Date runTime;
	
}
