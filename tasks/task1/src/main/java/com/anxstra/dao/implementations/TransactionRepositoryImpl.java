package com.anxstra.dao.implementations;

import com.anxstra.dao.repositories.TransactionRepository;
import com.anxstra.entities.Transaction;
import com.anxstra.gson.config.DBReader;
import com.anxstra.utils.DateUtils;
import com.anxstra.utils.GsonUtils;

import java.time.LocalDate;
import java.util.List;

public class TransactionRepositoryImpl implements TransactionRepository {

    @Override
    public List<Transaction> findAllByCreditIdAndPeriod(Integer creditId, LocalDate from, LocalDate to) {
        return DBReader.getJsonArray("transactions")
                       .asList()
                       .stream()
                       .map(el -> GsonUtils.deserialize(el, Transaction.class))
                       .filter(transaction -> transaction.getCreditId()
                                                         .equals(creditId)
                               && DateUtils.isBetween(transaction.getDate(), from, to))
                       .toList();
    }

    @Override
    public LocalDate getLastTransactionDateByCreditId(Integer creditId) {
        return DBReader.getJsonArray("transactions")
                       .asList()
                       .stream()
                       .map(el -> GsonUtils.deserialize(el, Transaction.class))
                       .map(Transaction::getDate)
                       .max(LocalDate::compareTo)
                       .orElse(LocalDate.now());
    }
}
