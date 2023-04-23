package com.absa.bank.service;

import com.absa.bank.entity.Account;
import com.absa.bank.entity.Transaction;
import com.absa.bank.entity.TransactionType;
import com.absa.bank.repository.AccountRepository;
import com.absa.bank.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.absa.bank.model.TransactionRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;


    public void processTransaction(TransactionRequest request) throws Exception {

        Optional<Account> byAccountNumber = accountRepository.findByAccountNumber(request.getFromAccountNumber());
        if(byAccountNumber.isEmpty()){
            throw new ChangeSetPersister.NotFoundException();
        }

        Long currentBalance = byAccountNumber.get().getBalance();
        Long updateBalance;


        if(request.getTransactionType() == TransactionType.CREDIT){
            updateBalance = currentBalance + request.getAmount();

        }else {
            updateBalance = currentBalance - request.getAmount();
        }
        byAccountNumber.get().setBalance(updateBalance);
        accountRepository.save(byAccountNumber.get());

        Transaction transaction = new Transaction();
        transaction.setFromAccount(request.getFromAccount());
        transaction.setProcessed(true);
        transaction.setToAccount(request.getToAccount());
        transaction.setAmount(request.getAmount());
        transaction.setType(request.getTransactionType());
        transaction.setDateTime(LocalDateTime.now());
        transaction.setAccountNumber("Bank Z");
        transactionRepository.save(transaction);
    }

    public List<Transaction> getListOfTransactionForBankZ(){

        return transactionRepository.findByAccountNumber("Bank Z");
    }
}
