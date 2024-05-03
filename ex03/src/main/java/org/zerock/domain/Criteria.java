package org.zerock.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria {

	private int pageNum;	//페이지 넘버
	private int amount;		//페이지당 글 수
	
	private String type;	//검색 종류 : T(title), C(content), W(writer)
	private String keyword;	//검색 단어
	
	public Criteria() {
		this(1,10);
	}
	
	public Criteria(int pageNum,int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	
	// type -- > TCW	T|C|W 로 반환
	public String[] getTypeArr() {
		return type==null? new String[] {} : type.split("");
	}
	
}
