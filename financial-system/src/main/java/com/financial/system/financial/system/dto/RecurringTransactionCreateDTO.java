package com.financial.system.financial.system.dto;

import com.financial.system.financial.system.model.RecurrenceType;
import com.financial.system.financial.system.model.TransactionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record RecurringTransactionCreateDTO(
        @NotBlank
        String name,
        @NotNull
        BigDecimal amount,
        @NotNull
        LocalDate startDate,
        @NotNull
        LocalDate endDate,
        @NotNull
        RecurrenceType recurrenceType,
        @NotNull
        TransactionType transactionType,
        @NotNull
        Long categoryId,
        @NotNull
        Long personId){
}

