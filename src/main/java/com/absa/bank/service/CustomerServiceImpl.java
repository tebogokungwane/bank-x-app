package com.absa.bank.service;

import com.absa.bank.entity.Account;
import com.absa.bank.entity.Customer;
import com.absa.bank.entity.Transaction;
import com.absa.bank.entity.TransactionType;
import com.absa.bank.repository.AccountRepository;
import com.absa.bank.repository.CustomerRepository;
import com.absa.bank.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CustomerServiceImpl implements CustomerService, EmailSenderService{

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private SmsService smsService;

    @Override
    public void createCustomer(Customer customer) {

        customer.setPhoneNumber("0681835644");
        List<Account> accounts = new ArrayList<>();
        Account currentAccount = new Account();
        currentAccount.setAccountNumber("123456789");
        currentAccount.setAccountType(AccountType.CURRENT);
        currentAccount.setBalance((long) 0);
        currentAccount.setCustomer(customer);
        currentAccount.setInterestRate((00.5));
        accounts.add(currentAccount);

        Account savingAccount = new Account();
        savingAccount.setAccountType(AccountType.SAVINGS);
        savingAccount.setBalance((long) 500.00);
        savingAccount.setCustomer(customer);
        savingAccount.setAccountNumber("987654321");
        savingAccount.setInterestRate((00.5));
        accounts.add(savingAccount);
        customer.setAccounts(accounts);

        customerRepository.save(customer);
        smsService.sendSMS(customer.getPhoneNumber(),customer.getFirstName() +"You have successfully registered");
    }

    @Override
    public void moveMoney(Long fromAccountId, Long toAccountId, double amount) throws Exception {

        Optional<Account> fromAccount = Optional.ofNullable(accountRepository.findAccountById(fromAccountId).orElseThrow(() -> new Exception("Invalid fromAccountId")));
        Optional<Account> toAccount = Optional.ofNullable(accountRepository.findAccountById(toAccountId).orElseThrow(() -> new Exception("Invalid toAccountId")));


        fromAccount.get().setBalance((long) (fromAccount.get().getBalance() - (amount + (amount * 0.05))));
        toAccount.get().setBalance((long) (toAccount.get().getBalance() - (amount + (amount * 0.05))));

        accountRepository.save(fromAccount.get());
        accountRepository.save(toAccount.get());

        Transaction transaction = new Transaction();
        transaction.setFromAccount(fromAccount.get());
        transaction.setProcessed(true);
        transaction.setToAccount(toAccount.get());
        transaction.setAmount((long) amount);
        transaction.setType(TransactionType.TRANSFER);
        transaction.setDateTime(LocalDateTime.now());
        transaction.setAccountNumber(fromAccount.get().getAccountNumber());
        transactionRepository.save(transaction);

        //smsService.sendSMS(transaction.getFromAccount().getCustomer().getPhoneNumber(),"A transfer was successfully made of "+transaction.getAmount());


    }

    @Override
    public List<Transaction> getAllTransactionList() {
        return transactionRepository.findAll();
    }




    @Override
    public void sendEmail(String to, String subject, String message, Transaction transaction) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo("tjkungwane@gmail.com");//transaction.getFromAccount().getCustomer().getEmail()
        mailMessage.setSubject("Transaction Notification");
        mailMessage.setText("Your transaction of R" +transaction.getAmount() + "has been transferred");

        mailSender.send(mailMessage);
    }
}
