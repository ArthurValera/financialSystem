package com.financial.system.financial.system.model;

import com.financial.system.financial.system.dto.EnderecoDTO;
import com.financial.system.financial.system.dto.PessoaUpdateDTO;
import com.financial.system.financial.system.dto.PessoaCreateDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

@Table(name = "pessoas")
@Entity(name = "Pessoa")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Boolean ativo;
    @Embedded
    private Endereco endereco;

    public Pessoa(PessoaCreateDTO data){
        this.ativo = true;
        this.nome = data.nome();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void update(@Valid PessoaUpdateDTO data){
        if(data.nome() != null){
            this.nome = data.nome();
        }
    }

    public void delete() {
        this.ativo = false;
    }
}
