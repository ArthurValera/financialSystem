package com.financial.system.financial.system.dto;

import com.financial.system.financial.system.model.Pessoa;

public record PessoaListagemDTO(Long id,
                                String nome) {

    public PessoaListagemDTO(Pessoa pessoa){
        this(pessoa.getId(), pessoa.getNome());
    }
}
