package com.anxstra.dao.repositories;

import com.anxstra.entities.Event;
import com.anxstra.entities.enums.Currency;

import java.time.LocalDate;
import java.util.Optional;

public interface EventRepository {

    Optional<Event> findLatestByPeriodAndCurrency(LocalDate from, LocalDate to, Currency currency);
}
