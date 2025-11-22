package com.financial.system.financial.system.service.recurrence;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class WeeklyRecurrencePolicy implements RecurrencePolicy{

    @Override
    public List<LocalDate> generateOccurrences(LocalDate start, LocalDate end) {
        List<LocalDate> dates = new ArrayList<>();
        LocalDate current = start;

        while (!current.isAfter(end)){
            dates.add(current);
            current = current.plusWeeks(1);
        }
        return dates;
    }
}
