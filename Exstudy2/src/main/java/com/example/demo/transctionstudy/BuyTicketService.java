package com.example.demo.transctionstudy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

@Service
// 인터페이스 제거, 다른 방법 사용
public class BuyTicketService{
	@Autowired Transaction1Dao transaction1Dao;
	@Autowired Transaction2Dao transaction2Dao;
	// 트렌잭션 템플릿 사용으로 변경
	// 트랜잭션 매니저 변수 선언, 스프링 자동 주입
	//@Autowired PlatformTransactionManager transactionManager;
	// 트랜잭션 매니저 사용 설정, 설정값 기본
	//@Autowired TransactionDefinition definition;
	@Autowired TransactionTemplate transactionTemplate;
	// 인터페이스 사용 X로 인한 오버라이드 제거
	//@Override
//	@Transactional(propagation = Propagation.REQUIRED) // 트랜잭션의 전파 속성 지정,선언적 방법 적용
	@Transactional(propagation = Propagation.REQUIRES_NEW) // 트랜잭션의 전파 속성 변경 <- 포함되는 트랜잭션과 포함시키는 쪽의 트랜잭션을 별도의 트랜잭션으로 처리하겠다는 표시
	public int buy(String consumerid, int amount, String error) {
	    // 트랜잭션 설정
	    //TransactionStatus status = transactionManager.getTransaction(definition);
	    
	    try {
	        // 로그 추가
	        System.out.println("consumerid: " + consumerid + ", amount: " + amount);
	        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				// 트랜잭션 템플릿 메서드 재정의
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus arg) {
	        	transaction1Dao.pay(consumerid, amount);  // 첫 번째 트랜잭션 처리
	        	// 의도적 에러 발생
		        if ("1".equals(error)) {int n = 10 / 0;}
		        transaction2Dao.pay(consumerid, amount);  // 두 번째 트랜잭션 처리
				}
			});
	        // transactionManager.commit(status);  // 트랜잭션 커밋
	        return 1;
	    } catch (Exception e) {
	        System.out.println("[PlatformTransactionManager] Rollback - " + e.getMessage());
	        // transactionManager.rollback(status);  // 트랜잭션 롤백
	        return 0;
	    }
	}
}
