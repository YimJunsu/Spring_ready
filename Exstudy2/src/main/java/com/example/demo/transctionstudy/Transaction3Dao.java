package com.example.demo.transctionstudy;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Transaction3Dao {
	public void pay(String consumerid, int amount);
}
