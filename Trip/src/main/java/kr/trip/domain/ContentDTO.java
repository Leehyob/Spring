package kr.trip.domain;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ContentDTO {

	public Response response;
	
	@Data
	@AllArgsConstructor
	public class Response{
		private Header header;
		private Body body;
		
		@Data
		@AllArgsConstructor
		public class Header{
			private String resultCode;
			private String resultMsg;
		}
		
		@Data
		@AllArgsConstructor
		public class Body{
			private Items items;
			private int numOfRows;
			private int pageNo;
			private int totalCount;
			
			@Data
			@AllArgsConstructor
			public class Items{
				private List<ContentVO> item;
			}
		}
	}
	
}
