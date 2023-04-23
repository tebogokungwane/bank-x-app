package com.absa.bank.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonIgnore
    @ManyToOne
    private Account fromAccount;

    @JsonIgnore
    @ManyToOne
    private Account toAccount;

    @Column(nullable = false)
    private Long amount;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    private Boolean processed;

    private String accountNumber;

    public Transaction(String fromAccountNumber, Long amount, TransactionType transactionType) {
    }


    public Long getFromAccountId() {
        return 0L;
    }

    public Long getToAccountId() {
        return 0L;
    }
}

