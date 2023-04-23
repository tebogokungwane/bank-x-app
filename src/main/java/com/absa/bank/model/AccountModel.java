package com.absa.bank.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountModel {

    private String accountNumber;
    private BigDecimal balance;
    private BigDecimal interestRate;
}