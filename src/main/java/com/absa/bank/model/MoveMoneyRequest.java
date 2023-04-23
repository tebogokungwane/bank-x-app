package com.absa.bank.model;

import lombok.Data;

@Data
public class MoveMoneyRequest {

    private Long fromAccountId;
    private Long toAccountId;
    private double amount;
}

