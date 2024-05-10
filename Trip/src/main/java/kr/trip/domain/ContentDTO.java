package kr.trip.domain;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ContentDTO {

	private Response response;
	
	@Data
	@AllArgsConstructor
	class Response{
		private Header header;
		private Body body;
		
		@Data
		@AllArgsConstructor
		class Header{
			private String resultCode;
			private String resultMsg;
		}
		
		@Data
		@AllArgsConstructor
		class Body{
			private Items items;
			private int numOfRows;
			private int pageNo;
			private int totalCount;
			
			@Data
			@AllArgsConstructor
			class Items{
				private List<ContentVO> item;
			}
		}
	}
	
}
