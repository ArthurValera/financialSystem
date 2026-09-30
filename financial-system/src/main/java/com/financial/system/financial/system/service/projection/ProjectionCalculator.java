package com.financial.system.financial.system.service.projection;

import com.financial.system.financial.system.model.RecurrenceType;
import com.financial.system.financial.system.model.RecurringTransaction;
import com.financial.system.financial.system.model.Transaction;
import com.financial.system.financial.system.model.TransactionType;
import com.financial.system.financial.system.repository.RecurringTransactionRep;
import com.financial.system.financial.system.repository.TransactionRep;
import com.financial.system.financial.system.service.recurrence.RecurrencePolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class ProjectionCalculator {

    @Autowired
    private RecurringTransactionRep recurringRep;

    @Autowired
    private TransactionRep transactionRep;

    @Autowired
    private Map<RecurrenceType, RecurrencePolicy> policies;

    public BigDecimal projectBalance(Long personId, LocalDate until) {

        LocalDate today = LocalDate.now();

        BigDecimal projected = transactionRep.sumBalanceOfPerson(personId, today);

        for (var t : transactionRep.findScheduledByPerson(personId, today, until)) {
            projected = (t.getType() == TransactionType.INCOME)
                    ? projected.add(t.getAmount())
                    : projected.subtract(t.getAmount());
        }

        List<RecurringTransaction> recurringList = recurringRep.findByPersonIdAndActiveTrue(personId);

        for (var r : recurringList) {
            LocalDate start = r.getStartDate();
            LocalDate end = (r.getEndDate() != null && r.getEndDate().isBefore(until))
                    ? r.getEndDate()
                    : until;

            if (start == null || start.isAfter(end)) continue;

            var policy = policies.get(r.getRecurrenceType());
            if (policy == null) continue;

            long futureOccurrences = policy.generateOccurrences(start, end).stream()
                    .filter(date -> date.isAfter(today))
                    .count();

            if (futureOccurrences == 0) continue;

            BigDecimal delta = r.getAmount().multiply(BigDecimal.valueOf(futureOccurrences));

            projected = (r.getType() == TransactionType.INCOME)
                    ? projected.add(delta)
                    : projected.subtract(delta);
        }

        return projected;
    }
}