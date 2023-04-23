package com.absa.bank.schedule;

import com.absa.bank.entity.Transaction;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@AllArgsConstructor
public class BankZScheduler {

    private final RestTemplate restTemplate;

    @Scheduled(fixedRate = 24 * 60 * 1000)
    public void runTransactionSchedule(){
        String url = "http://localhost:9090/api/v1/transactions";
        String response = restTemplate.getForObject(url,String.class);
    }
}
