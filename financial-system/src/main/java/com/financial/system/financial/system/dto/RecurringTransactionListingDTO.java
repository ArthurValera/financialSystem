package com.financial.system.financial.system.dto;

import com.financial.system.financial.system.model.RecurrenceType;
import com.financial.system.financial.system.model.RecurringTransaction;
import com.financial.system.financial.system.model.Transaction;
import com.financial.system.financial.system.model.TransactionType;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record RecurringTransactionListingDTO(Long id,
                                             String name,
                                             BigDecimal amount,
                                             LocalDate startDate,
                                             LocalDate endDate,
                                             RecurrenceType rType,
                                             TransactionType tType,
                                             String categoryName,
                                             String peopleName) {

    public RecurringTransactionListingDTO(RecurringTransaction Rtransaction) {
        this(
                Rtransaction.getId(),
                Rtransaction.getName(),
                Rtransaction.getAmount(),
                Rtransaction.getStartDate(),
                Rtransaction.getEndDate(),
                Rtransaction.getRecurrenceType(),
                Rtransaction.getType(),
                Rtransaction.getCategory() != null ? Rtransaction.getCategory().getName() : null,
                Rtransaction.getPerson() != null ? Rtransaction.getPerson().getName() : null
        );
    }
}