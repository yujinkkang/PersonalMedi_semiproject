package com.med.question.dao;

import java.util.List;

import com.med.question.dto.QuestionDto;

public interface QuestionDao {
	
	public int totalCount();
	public List<QuestionDto> selectList(int recordStartNum, int recordEndNum);
	public List<QuestionDto> selectList(QuestionDto dto, int recordStartNum, int recordEndNum);
	
	public int totalCount(QuestionDto dto);
	
	////////////////////////
	public QuestionDto selectOne(int question_boardno);
	public int insert(QuestionDto dto);
	public int update(QuestionDto dto);
	public int delete(int question_groupno);
	
	public int questionUpdate(int question_parentboardno);
	public int questionInsert(QuestionDto dto);
	public int updateReadcount(int question_boardno);


	

}
