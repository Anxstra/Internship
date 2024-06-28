package com.anxstra.entities;

import com.anxstra.entities.enums.Period;
import com.google.gson.annotations.Expose;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;

@Data
@NoArgsConstructor
public class Credit {

    private Integer id;

    private Integer userId;

    private LocalDate date;

    @Expose(serialize = false, deserialize = false)
    private LocalDate repaymentDate;

    @Expose(serialize = false, deserialize = false)
    private int processedTransactions;

    private Period period;

    private BigDecimal money;

    private BigDecimal rate;

    public void applyTransaction(Transaction transaction) {
        money = BigDecimal.ZERO.max(money.subtract(transaction.getMoney()));
        processedTransactions++;
        if (money.equals(BigDecimal.ZERO)) {
            repaymentDate = transaction.getDate();
        }
    }

    public void increaseDebt(Discount discount) {
        BigDecimal currentRate = rate;
        if (Objects.nonNull(discount)) {
            currentRate = BigDecimal.ZERO.max(currentRate.subtract(discount.getDiscountAmount()));
        }
        money = money.multiply(new BigDecimal(1).add(currentRate.divide(new BigDecimal(100), 4,
                RoundingMode.HALF_UP)));
    }
}
