package com.example.demo.transctionstudy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogWriteService {

	@Autowired Transaction3Dao transaction3Dao;
	
	public int write(String consumerid, int amount) {
		try {
			transaction3Dao.pay(consumerid, amount);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}
	
}
