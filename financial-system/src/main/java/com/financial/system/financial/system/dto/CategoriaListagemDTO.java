package com.financial.system.financial.system.dto;

import com.financial.system.financial.system.model.Categoria;

public record CategoriaListagemDTO(Long id, String nome) {
    public CategoriaListagemDTO(Categoria categoria) {
        this(categoria.getId(),
                categoria.getNome());
    }
}
