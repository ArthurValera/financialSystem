package com.financial.system.financial.system.dto;

import com.financial.system.financial.system.model.TransactionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionCreateDTO(@NotBlank
                                  String description,
                                   @NotNull
                                  BigDecimal amount,
                                   @NotNull
                                  LocalDate dueDate,
                                   @NotNull
                                  LocalDate paymentDate,
                                   @NotNull
                                   TransactionType  type,

                                   String note,

                                   @NotNull
                                  Long categoryId,
                                   @NotNull
                                  Long peopleId) {

}
