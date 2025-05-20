package com.financial.system.financial.system.dto;

import com.financial.system.financial.system.model.TipoLancamento;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public record LancamentoUpdateDTO(@NotNull Long id,
                                  String descriocao,
                                  BigDecimal valor,
                                  LocalDate dataVencimento,
                                  LocalDate dataPagamento,
                                  TipoLancamento tipo,
                                  String observacao,
                                  Long categoriaId,
                                  Long pessoaId) {
}
