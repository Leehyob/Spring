package org.zerock.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageDTO {
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	
	private int total;
	private Criteria cri;
	
	public PageDTO() {
	}
	
	public PageDTO(Criteria cri,int total) {
		this.cri = cri;
		this.total = total;
		
		
		this.endPage = (int)(Math.ceil(cri.getPageNum()/10.0))*10;
		//	this.endPage = (int)(Math.ceil(cri.getPageNum()/(double)getAmount)*10-->원하는 노출 페이지에 따라 다르게 설정;
		//	if 5페이지만 나오게 하고 싶다
		//	this.endPage = (int)(Math.ceil(cri.getPageNum()/(double)getAmount)*5
		
		this.startPage = endPage -9;
		
		int realEnd = (int)(Math.ceil((total*1.0)/cri.getAmount()));
		// == int realEnd = (int)(Math.ceil(total/(double)cri.getAmount());
		
		if(realEnd < this.endPage) {
			this.endPage = realEnd;
		}
		
		this.prev = this.startPage>1;
		this.next = this.endPage < realEnd;
		
	}
	
}
