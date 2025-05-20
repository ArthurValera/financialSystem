package com.financial.system.financial.system.model;

import com.financial.system.financial.system.dto.CategoriaCreateDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "categorias")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;

    public Categoria(){}

    public Categoria(CategoriaCreateDTO data) {
        this.nome = data.nome();
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
