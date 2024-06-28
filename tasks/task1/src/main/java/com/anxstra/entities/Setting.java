package com.anxstra.entities;

import com.anxstra.entities.enums.SortBy;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class Setting {

    private LocalDate dateFrom;

    private LocalDate dateTo;

    private ShowFor showFor;

    private SortBy sortBy;

    private String[] useDepartments;

    private BigDecimal startCostEUR;

    private BigDecimal startCostUSD;
}
