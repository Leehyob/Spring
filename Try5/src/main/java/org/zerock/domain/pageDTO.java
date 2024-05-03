package org.zerock.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class pageDTO {

	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	
	private Criteria cri;
	private int total;
	
	public pageDTO() {
		
	}
	
	public pageDTO(Criteria cri, int total) {
		this.cri = cri;
		this.total = total;
		
		this.endPage = (int)(Math.ceil(cri.getPageNum()/10.0))*10;
		this.startPage = this.endPage -9;
		
		int realEnd = (int)(Math.ceil(total/(double)cri.getAmount()));
		
		if(realEnd < this.endPage) {
			this.endPage = realEnd;
		}
		
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEnd;
	}
}
