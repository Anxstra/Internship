package com.anxstra.dao.repositories;

import com.anxstra.entities.Transaction;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository {

    List<Transaction> findAllByCreditIdAndPeriod(Integer creditId, LocalDate from, LocalDate to);

    LocalDate getLastTransactionDateByCreditId(Integer creditId);
}
