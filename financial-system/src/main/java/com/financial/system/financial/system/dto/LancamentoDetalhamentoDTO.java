package com.financial.system.financial.system.dto;

import com.financial.system.financial.system.model.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record LancamentoDetalhamentoDTO(Long id,
                                        String descricao,
                                        BigDecimal valor,
                                        LocalDate dataVencimento,
                                        LocalDate dataPagamento,
                                        TipoLancamento tipo,
                                        String observacao,
                                        Categoria categoria,
                                        Pessoa pessoa,
                                        boolean ativo) {

    public LancamentoDetalhamentoDTO(Lancamento lancamento){
        this(lancamento.getId(),
                lancamento.getDescricao(),
                lancamento.getValor(),
                lancamento.getDataVencimento(),
                lancamento.getDataPagamento(),
                lancamento.getTipo(),
                lancamento.getObservacao(),
                lancamento.getCategoria(),
                lancamento.getPessoa(),
                lancamento.getAtivo());
    }
}
