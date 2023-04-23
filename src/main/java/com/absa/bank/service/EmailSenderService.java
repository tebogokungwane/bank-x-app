package com.absa.bank.service;

import com.absa.bank.entity.Transaction;

public interface EmailSenderService {

    void sendEmail(String to, String subject, String message, Transaction transaction);
}
