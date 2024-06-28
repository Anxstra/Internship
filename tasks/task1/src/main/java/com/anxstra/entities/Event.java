package com.anxstra.entities;

import com.anxstra.entities.enums.Currency;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class Event {

    private Integer id;

    private Currency currency;

    private BigDecimal cost;

    private LocalDate date;
}
