package com.med.check.biz;

import java.util.List;

import com.med.check.dao.CheckDao;
import com.med.check.dao.CheckDaoImpl;
import com.med.check.dto.CheckDto;

public class CheckBizImpl implements CheckBiz {
	
	private CheckDao dao = new CheckDaoImpl();

	@Override
	public List<CheckDto> checkList() {
		return dao.checkList();
	}

	@Override
	public List<CheckDto> checkMemberList(int member_seq) {
		return dao.checkMemberList(member_seq);
	}

	@Override
	public CheckDto selectOne(int checkup_seq) {
		return dao.selectOne(checkup_seq);
	}

	@Override
	public int checkBasicInsert(CheckDto dto) {
		return dao.checkBasicInsert(dto);
	}

	@Override
	public int checkPersonalInsert(CheckDto dto) {
		return dao.checkPersonalInsert(dto);
	}

	@Override
	public int checkDetailInsert(CheckDto dto) {
		return dao.checkDetailInsert(dto);
	}

	@Override
	public int checkUpdate(CheckDto dto) {
		return dao.checkUpdate(dto);
	}

	@Override
	public int checkDelete(int checkup_seq) {
		return dao.checkDelete(checkup_seq);
	}
	
	@Override
	public List<CheckDto> checkDetail(int member_seq) {
		return dao.checkDetail(member_seq);
	}
	
	@Override
	public List<CheckDto> checkDetail2(int member_seq) {
		return dao.checkDetail2(member_seq);
	}

}
