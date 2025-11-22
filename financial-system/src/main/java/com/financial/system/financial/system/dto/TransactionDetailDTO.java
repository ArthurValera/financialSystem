package com.financial.system.financial.system.dto;

import com.financial.system.financial.system.model.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionDetailDTO(Long id,
                                   String description,
                                   BigDecimal amount,
                                   LocalDate dueDate,
                                   LocalDate paymentDate,
                                   TransactionType type,
                                   String note,
                                   Category category,
                                   Person person,
                                   boolean active) {

    public TransactionDetailDTO(Transaction transaction){
        this(transaction.getId(),
                transaction.getDescription(),
                transaction.getAmount(),
                transaction.getDueDate(),
                transaction.getPaymentDate(),
                transaction.getType(),
                transaction.getNote(),
                transaction.getCategory(),
                transaction.getPerson(),
                transaction.isActive());
    }
}
