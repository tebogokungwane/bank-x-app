package com.absa.bank.controller;

import com.absa.bank.entity.Customer;
import com.absa.bank.entity.Transaction;
import com.absa.bank.model.MoveMoneyRequest;
import com.absa.bank.service.CustomerService;
import com.absa.bank.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class BankingController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/customers")
    @ResponseStatus(HttpStatus.CREATED)
    public String createCustomer(@RequestBody Customer customer){
        log.info("inside the BankingController--> createCustomer()");
        customerService.createCustomer(customer);
        return "Customer saved successfully";

    }

    @PostMapping("/moveMoney")
    @ResponseStatus(HttpStatus.CREATED)
    public String moveMoney(@RequestBody MoveMoneyRequest request) throws Exception {
        customerService.moveMoney(request.getFromAccountId(), request.getToAccountId(), request.getAmount());
        return "Money transferred successfully";

    }

    @PostMapping("/transactions")
    public ResponseEntity<String> processTransactions(@RequestBody List<Transaction> transactions){

        return ResponseEntity.ok("Transactions processed successfully");
    }

    @GetMapping("/transactions")
    public List<Transaction> getAllTransactions(){
        return customerService.getAllTransactionList();
    }

    @GetMapping("/transactions/bankx")
    public List<Transaction> getAllTransactionForBankZ(){
        return transactionService.getListOfTransactionForBankZ();
    }
}

