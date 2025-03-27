package com.example.demo.transctionstudy;

import com.example.demo.Exstudy2Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

@Service
public class BuyAndLogService {

    private final Exstudy2Application exstudy2Application;

	@Autowired BuyTicketService buyTicketService;
	@Autowired LogWriteService logWriteService;
	@Autowired TransactionTemplate transactionTemplate;

    BuyAndLogService(Exstudy2Application exstudy2Application) {
        this.exstudy2Application = exstudy2Application;
    }
	
	public int buy(String consumerid, int amount, String error) {
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus status) {
					buyTicketService.buy(consumerid, amount, error);
					
					if("2".equals(error)) {int n = 10 / 0;}
					logWriteService.write(consumerid, amount);
				}
			});
			return 1;
		} catch (Exception e) {
			System.out.println("[Transaction Propagation #1] Rollback 오류 메시지 ->" + e.getMessage());
			return 0;
		}
	}
}
