package com.med.question.biz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.med.question.dao.QuestionDao;
import com.med.question.dao.QuestionDaoImpl;
import com.med.question.dto.QuestionDto;

public class QuestionBizImpl implements QuestionBiz {
	
	private QuestionDao dao = new QuestionDaoImpl();

	@Override
	public int totalCount() {
		return dao.totalCount();
	}
	
	@Override
	public int totalCount(QuestionDto dto) {
		return dao.totalCount(dto);
	}
	
	@Override
	public List<QuestionDto> selectList(int pageNum) {
		int recordStartNum = 0;	//게시글 시작 번호
		int recordEndNum = 0;	//게시글 끝 번호

		recordEndNum = pageNum * pageCount;
		recordStartNum = recordEndNum - (pageCount-1);
		
		if (recordEndNum > totalCount()) {
			recordEndNum = totalCount();
		}

		return dao.selectList(recordStartNum, recordEndNum);
	}
	
	@Override
	public List<QuestionDto> selectList(int pageNum, QuestionDto dto) {
		int recordStartNum = 0;	//게시글 시작 번호
		int recordEndNum = 0;	//게시글 끝 번호

		recordEndNum = pageNum * pageCount;
		recordStartNum = recordEndNum - (pageCount-1);
		
		if (recordEndNum > totalCount(dto)) {
			recordEndNum = totalCount(dto);
		}

		return dao.selectList(dto, recordStartNum, recordEndNum);
	}

	@Override
	public int totalPage(int pageNum) {
		int total_page = 0;
		total_page = totalCount() / pageCount + (totalCount() % pageCount > 0 ? 1 : 0);
		
		if (pageNum > total_page) {
			pageNum = total_page;
		}
		
		return total_page;	
	}
	
	@Override
	public int totalPage(int pageNum, QuestionDto dto) {
		int total_page = 0;
		total_page = totalCount(dto) / pageCount + (totalCount(dto) % pageCount > 0 ? 1 : 0);
		
		if (pageNum > total_page) {
			pageNum = total_page;
		}
		
		return total_page;
	}

	@Override
	public int makeGroup(int pageNum) {
		int groupNum = 0;
		groupNum = pageNum / groupSize + (pageNum % groupSize > 0 ? 1:0);
		
		return groupNum;
	}
	
	public Map<String, Integer> makeGroupNum(int pageNum){
		int groupNum = makeGroup(pageNum);
		
		int groupEndNum = 0;
		groupEndNum = groupNum * groupSize;
		
		int groupStartNum = 0;
		groupStartNum = groupEndNum - (groupSize - 1);
		
		if (groupEndNum > totalPage(pageNum)) {
			groupEndNum = totalPage(pageNum);
		}
		
		int prevNum = groupStartNum - groupSize;
		int nextNum = groupStartNum + groupSize;
		
		if(prevNum < 1) {
			prevNum = 1;
		}
		
		if (nextNum > totalPage(pageNum)) {
			nextNum = totalPage(pageNum);
		}
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("groupEndNum", groupEndNum);
		map.put("groupStartNum", groupStartNum);
		map.put("prevNum", prevNum);
		map.put("nextNum", nextNum);
		
		return map;
	}
	
	@Override
	public Map<String, Integer> makeGroupNum(int pageNum, QuestionDto dto){
		int groupNum = makeGroup(pageNum);
		
		int groupEndNum = 0;
		groupEndNum = groupNum * groupSize;
		
		int groupStartNum = 0;
		groupStartNum = groupEndNum - (groupSize - 1);
		
		if (groupEndNum > totalPage(pageNum, dto)) {
			groupEndNum = totalPage(pageNum, dto);
		}
		
		int prevNum = groupStartNum - groupSize;
		int nextNum = groupStartNum + groupSize;
		
		if(prevNum < 1) {
			prevNum = 1;
		}
		
		if (nextNum > totalPage(pageNum, dto)) {
			nextNum = totalPage(pageNum, dto);
		}
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("groupEndNum", groupEndNum);
		map.put("groupStartNum", groupStartNum);
		map.put("prevNum", prevNum);
		map.put("nextNum", nextNum);
		
		return map;
	}
	
	@Override
	public QuestionDto selectOne(int question_boardno) {
		// TODO Auto-generated method stub
		return dao.selectOne(question_boardno);
	}

	@Override
	public int insert(QuestionDto dto) {
		// TODO Auto-generated method stub
		return dao.insert(dto);
	}

	@Override
	public int update(QuestionDto dto) {
		// TODO Auto-generated method stub
		return dao.update(dto);
	}

	@Override
	public int delete(int question_boardno) {
		// TODO Auto-generated method stub
		return dao.delete(question_boardno);
	}

	public int questionProc(QuestionDto dto) {
		int updateRes= dao.questionUpdate(dto.getQuestion_boardno());
		int insertRes= dao.questionInsert(dto);
		return updateRes+insertRes;
	}
	public int updateReadcount(int question_boardno) {
		return dao.updateReadcount(question_boardno);
	}


}
