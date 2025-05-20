package com.financial.system.financial.system.dto;

import com.financial.system.financial.system.model.Lancamento;
import com.financial.system.financial.system.model.TipoLancamento;

import java.math.BigDecimal;
import java.time.LocalDate;

public record LancamentoListagemDTO( Long id,
                                     String descricao,
                                     BigDecimal valor,
                                     LocalDate dataVencimento,
                                     LocalDate dataPagamento,
                                     TipoLancamento tipo,
                                     String nomeCategoria,
                                     String nomePessoa) {

    public LancamentoListagemDTO(Lancamento lancamento) {
        this(
                lancamento.getId(),
                lancamento.getDescricao(),
                lancamento.getValor(),
                lancamento.getDataVencimento(),
                lancamento.getDataPagamento(),
                lancamento.getTipo(),
                lancamento.getCategoria() != null ? lancamento.getCategoria().getNome() : null,
                lancamento.getPessoa() != null ? lancamento.getPessoa().getNome() : null
        );
    }
}
