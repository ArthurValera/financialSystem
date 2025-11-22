package com.financial.system.financial.system.service.recurrence;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class MonthlyRecurrencePolicy implements RecurrencePolicy {

    @Override
    public List<LocalDate> generateOccurrences(LocalDate start, LocalDate end) {
        List<LocalDate> dates = new ArrayList<>();
        LocalDate current = start;

        while (!current.isAfter(end)) {
            dates.add(current);

            YearMonth nextMonth = YearMonth.from(current).plusMonths(1);

            int day = Math.min(current.getDayOfMonth(), nextMonth.lengthOfMonth());

            current = nextMonth.atDay(day);
        }

        return dates;
    }
}
