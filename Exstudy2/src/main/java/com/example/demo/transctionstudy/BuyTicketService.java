package com.example.demo.transctionstudy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuyTicketService implements IBuyTicketService{
	@Autowired Transaction1Dao transaction1Dao;
	@Autowired Transaction2Dao transaction2Dao;
	
	@Override
	public int buy(String consumerId, int amount, String error) {
		try {
			transaction1Dao.pay(consumerId, amount);
			// 의도적 에러 발생
			if (error.equals("1")) {int n = 10 / 0;}
			
			transaction2Dao.pay(consumerId, amount);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}
}
