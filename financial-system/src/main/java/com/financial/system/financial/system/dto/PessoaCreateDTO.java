package com.financial.system.financial.system.dto;

import jakarta.validation.constraints.NotBlank;

public record PessoaCreateDTO(@NotBlank String nome) {

}
