package com.financial.system.financial.system.dto;

import com.financial.system.financial.system.model.RecurrenceType;
import com.financial.system.financial.system.model.TransactionType;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record RecurringTransactionUpdateDTO(
        String name,
        BigDecimal amount,
        LocalDate startDate,
        LocalDate endDate,
        RecurrenceType recurrenceType,
        TransactionType transactionType,
        Long categoryId,
        Long personId){
}
