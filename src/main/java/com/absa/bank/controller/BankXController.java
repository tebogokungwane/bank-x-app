package com.absa.bank.controller;

import com.absa.bank.model.TransactionRequest;
import com.absa.bank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bankz")
public class BankXController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/debit-credit")
    public void debitAccount(@RequestBody TransactionRequest transactionRequest) throws Exception {
        transactionService.processTransaction(transactionRequest);
    }

}
