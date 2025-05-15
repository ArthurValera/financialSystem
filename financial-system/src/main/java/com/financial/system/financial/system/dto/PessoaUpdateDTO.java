package com.financial.system.financial.system.dto;

import jakarta.validation.constraints.NotNull;

public record PessoaUpdateDTO(@NotNull Long id,
                              String nome) {
}
