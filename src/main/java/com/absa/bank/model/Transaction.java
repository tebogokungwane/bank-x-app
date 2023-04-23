package com.absa.bank.model;

import com.absa.bank.entity.Account;
import com.absa.bank.entity.TransactionType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Transaction {

    private String fromAccountNumber;
    private String toAccountNumber;
    private Long amount;
    private String description;
    private Account fromAccount;
    private Account toAccount;
    private TransactionType type;
    private LocalDateTime dateTime;
    private Boolean processed;
    private String accountNumber;
    private Long fromAccountId;
    private Long toAccountId;


    public Transaction(String fromAccountNumber, String toAccountNumber, Long amount, TransactionType transactionType) {
    }
}
