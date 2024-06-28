package com.anxstra.dao.repositories;

import com.anxstra.entities.Discount;

import java.time.LocalDate;
import java.util.Optional;

public interface DiscountRepository {

    Optional<Discount> findMaxByDate(LocalDate paymentDay);
}
