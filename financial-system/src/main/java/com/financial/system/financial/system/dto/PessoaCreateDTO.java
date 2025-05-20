package com.financial.system.financial.system.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PessoaCreateDTO(@NotBlank String nome,
                              @NotNull @Valid
                              EnderecoDTO endereco) {

}
