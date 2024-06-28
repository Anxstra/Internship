package com.anxstra.dao.repositories;

import com.anxstra.entities.Credit;

import java.time.LocalDate;
import java.util.List;

public interface CreditRepository {

    List<Credit> findAllByUserIdAndPeriod(Integer userId, LocalDate from, LocalDate to);
}
