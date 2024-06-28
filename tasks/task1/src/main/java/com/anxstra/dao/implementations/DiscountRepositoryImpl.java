package com.anxstra.dao.implementations;

import com.anxstra.dao.repositories.DiscountRepository;
import com.anxstra.entities.Discount;
import com.anxstra.gson.config.DBReader;
import com.anxstra.utils.DateUtils;
import com.anxstra.utils.GsonUtils;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Optional;

public class DiscountRepositoryImpl implements DiscountRepository {

    @Override
    public Optional<Discount> findMaxByDate(LocalDate payment) {
        return DBReader.getJsonArray("discounts")
                       .asList()
                       .stream()
                       .map(el -> GsonUtils.deserialize(el, Discount.class))
                       .filter(discount -> DateUtils.isBetween(payment, discount.getDateFrom(), discount.getDateTo()))
                       .max(Comparator.comparing(Discount::getDiscountAmount));
    }
}
