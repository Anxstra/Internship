package com.anxstra.utils;

import com.anxstra.entities.Credit;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateUtils {

    private DateUtils() {
    }

    public static boolean isBetween(LocalDate date, LocalDate start, LocalDate end) {
        return !date.isBefore(start) && !date.isAfter(end);
    }

    public static int getPeriodCount(Credit credit, LocalDate to) {
        int result = 0;
        switch (credit.getPeriod()) {
            case DAY -> result = (int) ChronoUnit.DAYS.between(credit.getDate(), to.plusDays(1));
            case WEEK -> result = (int) ChronoUnit.WEEKS.between(credit.getDate(), to.plusDays(1));
            case MONTH -> result = (int) ChronoUnit.MONTHS.between(credit.getDate(), to.plusDays(1));
            case YEAR -> result = (int) ChronoUnit.YEARS.between(credit.getDate(), to.plusDays(1));
        }
        return result;
    }
}
