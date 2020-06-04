package com.med.question.biz;

import java.util.List;
import java.util.Map;

import com.med.question.dto.QuestionDto;

public interface QuestionBiz {
	
	int pageCount = 10;		//페이지당 레코드 수
	int groupSize = 5;		//페이지당 보여줄 번호
	
	public int totalCount();							//전체 개수 return
	public int totalCount(QuestionDto dto);
	
	public List<QuestionDto> selectList(int pageNum);
	public List<QuestionDto> selectList(int pageNum, QuestionDto dto);
	public int totalPage(int pageNum);
	public int makeGroup(int pageNum);
	public Map<String, Integer> makeGroupNum(int pageNum);
	
	public int totalPage(int pageNum, QuestionDto dto);
	public Map<String, Integer> makeGroupNum(int pageNum, QuestionDto dto);
	
	public QuestionDto selectOne(int question_boardno);
	public int insert(QuestionDto dto);
	public int update(QuestionDto dto);
	public int delete(int question_boardno);
	public int questionProc(QuestionDto dto);
	public int updateReadcount(int question_boardno);
	
}
