package com.anxstra.dao.implementations;

import com.anxstra.dao.repositories.EventRepository;
import com.anxstra.entities.Event;
import com.anxstra.entities.enums.Currency;
import com.anxstra.gson.config.DBReader;
import com.anxstra.utils.DateUtils;
import com.anxstra.utils.GsonUtils;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Optional;

public class EventRepositoryImpl implements EventRepository {

    @Override
    public Optional<Event> findLatestByPeriodAndCurrency(LocalDate from, LocalDate to, Currency currency) {
        return DBReader.getJsonArray("events")
                       .asList()
                       .stream()
                       .map(el -> GsonUtils.deserialize(el, Event.class))
                       .filter(event -> DateUtils.isBetween(event.getDate(), from, to) && currency == event.getCurrency())
                       .max(Comparator.comparing(Event::getDate));
    }
}
