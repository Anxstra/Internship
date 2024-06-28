package com.anxstra.entities;

import com.anxstra.entities.enums.DiscountType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class Discount {

    private Integer id;

    private DiscountType type;

    private LocalDate dateFrom;

    private LocalDate dateTo;

    private BigDecimal discountAmount;
}
