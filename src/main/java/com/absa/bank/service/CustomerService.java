package com.absa.bank.service;

import com.absa.bank.entity.Customer;
import com.absa.bank.entity.Transaction;

import java.util.List;

public interface CustomerService {

    void createCustomer(Customer customer);
    void moveMoney(Long fromAccountId, Long toAccount, double amount) throws Exception;

    List<Transaction> getAllTransactionList();

}
