package com.absa.bank.entity;

import com.absa.bank.service.AccountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String accountNumber;
    private Long balance;
    private double interestRate;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "fromAccount")
    private List<Transaction> sentTransactions;

    @OneToMany(mappedBy = "toAccount")
    private List<Transaction> receiveTransactions;


    public void debit(Long amount) {
    }

    public void credit(Long amount) {
    }
}
