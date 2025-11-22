package com.financial.system.financial.system.service.projection;

import com.financial.system.financial.system.model.RecurrenceType;
import com.financial.system.financial.system.model.RecurringTransaction;
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

        BigDecimal currentBalance = transactionRep.sumBalanceOfPerson(personId);

        List<RecurringTransaction> recurringList = recurringRep.findByPersonIdAndActiveTrue(personId);

        for (var r : recurringList) {
            LocalDate start = r.getStartDate();
            LocalDate end = r.getEndDate() != null && r.getEndDate().isBefore(until) ? r.getEndDate() : until;

            if (start == null || start.isAfter(end)) continue;

            var policy = policies.get(r.getRecurrenceType());
            if (policy == null) continue;

            var dates = policy.generateOccurrences(start, end);

            for (var date : dates) {
                if (r.getType() == TransactionType.INCOME)
                    currentBalance = currentBalance.add(r.getAmount());
                else
                    currentBalance = currentBalance.subtract(r.getAmount());
            }
        }

        return currentBalance;
    }
}
