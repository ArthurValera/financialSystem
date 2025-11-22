package com.financial.system.financial.system.dto;

import com.financial.system.financial.system.model.TransactionType;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionUpdateDTO(@NotNull Long id,
                                   String description,
                                   BigDecimal amount,
                                   LocalDate dueDate,
                                   LocalDate paymentDate,
                                   TransactionType type,
                                   String note,
                                   Long categoryId,
                                   Long personId) {
}
