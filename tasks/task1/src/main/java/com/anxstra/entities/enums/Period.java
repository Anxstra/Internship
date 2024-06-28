package com.anxstra.entities.enums;

import java.time.temporal.ChronoUnit;

public enum Period {
    DAY("Day", ChronoUnit.DAYS),
    WEEK("Week", ChronoUnit.WEEKS),
    MONTH("Month", ChronoUnit.MONTHS),
    YEAR("Year", ChronoUnit.YEARS);

    private final String periodName;

    private final ChronoUnit unit;

    Period(String periodName, ChronoUnit unit) {
        this.periodName = periodName;
        this.unit = unit;
    }

    @Override
    public String toString() {
        return periodName;
    }

    public ChronoUnit unit() {
        return unit;
    }
}
