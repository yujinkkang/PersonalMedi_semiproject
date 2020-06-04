package com.med.check.biz;

import java.util.List;

import com.med.check.dto.CheckDto;

public interface CheckBiz {
	
	public List<CheckDto> checkList(); //모든 list
	
	public List<CheckDto> checkMemberList(int member_seq);//회원별 list
	
	public CheckDto selectOne(int checkup_seq);//list 중에 선택했을시 db하나
	
	public int checkBasicInsert(CheckDto dto);//기본입력
	
	public int checkPersonalInsert(CheckDto dto);//개인입력
	
	public int checkDetailInsert(CheckDto dto);//정밀입력
	
	public int checkUpdate(CheckDto dto);
	
	public int checkDelete(int checkup_seq);
	
	public List<CheckDto> checkDetail(int member_seq);
	
	public List<CheckDto> checkDetail2(int member_seq);
}
