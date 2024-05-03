package org.zerock.service;

import java.util.List;

import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyPageDTO;
import org.zerock.domain.ReplyVO;

public interface ReplyService {

	public int register(ReplyVO reply);
	
	public ReplyVO get(long rno);
	
	public int modify(ReplyVO reply);
	
	public int remove(long rno);
	
	public ReplyPageDTO getList(Criteria cri, long bno);
}
