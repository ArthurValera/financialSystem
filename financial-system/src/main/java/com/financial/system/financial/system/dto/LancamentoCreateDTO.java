package com.financial.system.financial.system.dto;

import com.financial.system.financial.system.model.TipoLancamento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record LancamentoCreateDTO(@NotBlank
                                  String descricao,
                                  @NotNull
                                  BigDecimal valor,
                                  @NotNull
                                  LocalDate dataVencimento,
                                  @NotNull
                                  LocalDate dataPagamento,
                                  @NotNull
                                  TipoLancamento tipo,

                                  String observacao,

                                  @NotNull
                                  Long categoriaId,
                                  @NotNull
                                  Long pessoaId) {

}
