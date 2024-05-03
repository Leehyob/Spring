package org.zerock.domain;


import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardVO {

	private long bno;
	private String title;
	private String content;
	private String writer;
	private Date regdate;
	private Date updateData;
}
