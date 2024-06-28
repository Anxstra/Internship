package com.anxstra.entities;

import com.anxstra.entities.enums.Currency;
import com.anxstra.gson.config.AppConfigurer;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Data
@NoArgsConstructor
public class Transaction {

    private Integer id;

    private LocalDate date;

    private Integer userId;

    private Integer creditId;

    private Currency currency;

    private BigDecimal money;

    public void convert(Event event) {
        Setting setting = AppConfigurer.getSetting();
        BigDecimal convertRatio;
        if (Objects.nonNull(event)) {
            convertRatio = event.getCost();
        } else {
            convertRatio = currency == Currency.USD ? setting.getStartCostUSD() : setting.getStartCostEUR();
        }
        money = money.multiply(convertRatio);
        currency = Currency.BR;
    }
}
