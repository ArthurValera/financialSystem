package com.financial.system.financial.system.service.recurrence;

import java.time.LocalDate;
import java.util.List;

public interface RecurrencePolicy {
    List<LocalDate> generateOccurrences(LocalDate start, LocalDate end);
}
