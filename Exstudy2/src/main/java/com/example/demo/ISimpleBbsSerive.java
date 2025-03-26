package com.example.demo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.example.demo.JDBCStudy.SimpleBbsDto;

public interface ISimpleBbsSerive {
	public List<SimpleBbsDto> list();
	public SimpleBbsDto view(String id);
	public int write(Map<String, String> map);
	public int delete(@Param("_id") String id);
	public int count();
}
