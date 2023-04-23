package com.absa.bank.model;

import com.absa.bank.entity.Account;
import com.absa.bank.entity.TransactionType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransactionRequest {

    private String fromAccountNumber;
    private Long amount;
    private TransactionType transactionType;
    private String toAccountNumber;
    private String description;
    private Account fromAccount;
    private Account toAccount;
    private TransactionType type;
    private LocalDateTime dateTime;
    private Boolean processed;
    private String accountNumber;
    private Long fromAccountId;
    private Long toAccountId;

}
