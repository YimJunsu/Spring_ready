package com.example.demo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.JDBCStudy.ISimpleBbsDao;
import com.example.demo.JDBCStudy.SimpleBbsDto;

@Service
public class SimpleBbsService implements ISimpleBbsSerive{
	@Autowired ISimpleBbsDao iSimpleBbsDao;
	
	@Override
	public List<SimpleBbsDto> list() {
		return iSimpleBbsDao.listDao();
	}
	
	@Override
	public SimpleBbsDto view(String id) {
		return iSimpleBbsDao.viewDao(id);
	}
	
	@Override
	public int write(Map<String, String> map) {
		int nResult = iSimpleBbsDao.writeDao(map);
		return nResult;
	}
	
	@Override
	public int delete(String id) {
		int nResult = iSimpleBbsDao.deleteDao(id);
		return nResult;
	}
	
	@Override
	public int count() {
		int nTotalCount = iSimpleBbsDao.articleCount();
		return nTotalCount;
	}
}
