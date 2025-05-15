package com.financial.system.financial.system.dto;

import com.financial.system.financial.system.model.Pessoa;

public record PessoaDetalhamentoDTO(Long id, String nome, boolean ativo) {

    public PessoaDetalhamentoDTO(Pessoa pessoa){
        this(pessoa.getId(),
                pessoa.getNome(),
                pessoa.getAtivo());
    }
}
