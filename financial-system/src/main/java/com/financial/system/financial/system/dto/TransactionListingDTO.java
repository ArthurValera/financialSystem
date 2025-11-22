package com.financial.system.financial.system.dto;

import com.financial.system.financial.system.model.Transaction;
import com.financial.system.financial.system.model.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionListingDTO(Long id,
                                    String description,
                                    BigDecimal amount,
                                    LocalDate dueDate,
                                    LocalDate paymentDate,
                                    TransactionType type,
                                    String categoryName,
                                    String personName) {

    public TransactionListingDTO(Transaction transaction) {
        this(
                transaction.getId(),
                transaction.getDescription(),
                transaction.getAmount(),
                transaction.getDueDate(),
                transaction.getPaymentDate(),
                transaction.getType(),
                transaction.getCategory() != null ? transaction.getCategory().getName() : null,
                transaction.getPerson() != null ? transaction.getPerson().getName() : null
        );
    }
}
