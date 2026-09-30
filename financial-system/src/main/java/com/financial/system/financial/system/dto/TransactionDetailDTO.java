package com.financial.system.financial.system.dto;

import com.financial.system.financial.system.model.Transaction;
import com.financial.system.financial.system.model.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionDetailDTO(Long id,
                                   String description,
                                   BigDecimal amount,
                                   LocalDate dueDate,
                                   LocalDate paymentDate,
                                   TransactionType type,
                                   String note,
                                   Long categoryId,
                                   String categoryName,
                                   Long personId,
                                   String personName,
                                   boolean active) {

    public TransactionDetailDTO(Transaction transaction){
        this(transaction.getId(),
                transaction.getDescription(),
                transaction.getAmount(),
                transaction.getDueDate(),
                transaction.getPaymentDate(),
                transaction.getType(),
                transaction.getNote(),
                transaction.getCategory() != null ? transaction.getCategory().getId() : null,
                transaction.getCategory() != null ? transaction.getCategory().getName() : null,
                transaction.getPerson() != null ? transaction.getPerson().getId() : null,
                transaction.getPerson() != null ? transaction.getPerson().getName() : null,
                transaction.isActive());
    }
}
