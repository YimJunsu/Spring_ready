package com.example.demo.transctionstudy;

public interface IBuyTicketService {
	// 인터페이스 제거 다른 방법 사용
	public int buy(String consumerid, int money, String error);
}
