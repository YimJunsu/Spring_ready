package com.example.demo.transctionstudy;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Transaction1Dao {
	public void pay(String consumerId, int amount);
}
